package jhelp.coordinate.system.ui.editor;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import javax.swing.Action;
import javax.swing.ActionMap;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import jhelp.coordinate.system.event.FormulaEditorListener;
import jhelp.coordinate.system.model.formula.Binary;
import jhelp.coordinate.system.model.formula.Fixed;
import jhelp.coordinate.system.model.formula.Formula;
import jhelp.coordinate.system.model.formula.FormulaNature;
import jhelp.coordinate.system.model.formula.FormulaType;
import jhelp.coordinate.system.model.formula.Number;
import jhelp.coordinate.system.model.formula.Unary;
import jhelp.coordinate.system.model.formula.Undefined;
import jhelp.coordinate.system.model.formula.Variable;
import jhelp.coordinate.system.model.formula.VariableType;
import jhelp.coordinate.system.ui.drawer.FormulaDrawer;
import jhelp.gui.action.GenericAction;
import jhelp.gui.smooth.JHelpConstantsSmooth;
import jhelp.util.gui.UtilGUI;

/**
 * Component that edit a formula.<br>
 * It contains an area where formula is draw and a keybord for enter the formula.<br>
 * Every button in keyboard have a short key indicated in blue. An additional sort key is delete for delete<br>
 * The edition is made by block, the actual edited block is indicated by a red rectangle.<br>
 * For exit a block and go in upper level use "Esc" (Or keyboard Escape)
 *
 * @author JHelp <br>
 */
public class FormulaEditor
      extends JPanel
{
   /**
    * Action for delete last edition
    *
    * @author JHelp <br>
    */
   class ActionDelete
         extends GenericAction
   {
      /**
       * Create a new instance of ActionDelete
       */
      ActionDelete()
      {
         super("delete");
         this.setShortcut(UtilGUI.createKeyStroke(UtilGUI.CHARACTER_DELETE));
      }

      /**
       * Called when user activate the action <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       *
       * @param actionEvent
       *           Event description
       * @see jhelp.gui.action.GenericAction#doActionPerformed(java.awt.event.ActionEvent)
       */
      @Override
      protected void doActionPerformed(final ActionEvent actionEvent)
      {
         FormulaEditor.this.delete();
      }
   }

   /** The current formula */
   private Formula               formula;
   /** Component that draw the formula */
   private final FormulaDrawer   formulaDrawer;
   /** Part of formula currently edited */
   private Formula               formulaEdited;
   /** Listener of formula validation */
   private FormulaEditorListener formulaEditorListener;
   /** Indicates that current number already have a decimal separator */
   private boolean               hasComma;
   /** Current number edited */
   private final StringBuilder   number;
   /** Variable type used for create variable */
   private VariableType          variableType;

   /**
    * Create a new instance of FormulaEditor
    */
   public FormulaEditor()
   {
      super(new BorderLayout());

      this.formula = Undefined.UNDEFINED();
      this.formulaEdited = this.formula;
      this.number = new StringBuilder();
      this.hasComma = false;
      this.variableType = VariableType.X;

      this.formulaDrawer = new FormulaDrawer();
      this.add(this.formulaDrawer, BorderLayout.CENTER);

      final JPanel keyboard = new JPanel(new GridLayout(0, 7));

      keyboard.add(this.createButton("7", ButtonType.NUMBER, '7', new FormulaActionNumber(this, 7)));
      keyboard.add(this.createButton("8", ButtonType.NUMBER, '8', new FormulaActionNumber(this, 8)));
      keyboard.add(this.createButton("9", ButtonType.NUMBER, '9', new FormulaActionNumber(this, 9)));
      keyboard.add(this.createButton("/", ButtonType.BINARY, '/', new FormulaActionDivide(this)));
      keyboard.add(this.createButton("<html>x<sup>y</sup></html>", ButtonType.BINARY, 'p', new FormulaActionPower(this)));
      keyboard.add(this.createButton("exp", ButtonType.UNARY, 'x', new FormulaActionExponential(this)));
      keyboard.add(this.createButton("<html>&pi;</html>", ButtonType.FIXED, '$', new FormulaActionFixed(this, Fixed.PI())));
      //
      keyboard.add(this.createButton("4", ButtonType.NUMBER, '4', new FormulaActionNumber(this, 4)));
      keyboard.add(this.createButton("5", ButtonType.NUMBER, '5', new FormulaActionNumber(this, 5)));
      keyboard.add(this.createButton("6", ButtonType.NUMBER, '6', new FormulaActionNumber(this, 6)));
      keyboard.add(this.createButton("*", ButtonType.BINARY, '*', new FormulaActionMultiplication(this)));
      keyboard.add(this.createButton("sin", ButtonType.UNARY, 's', new FormulaActionSinus(this)));
      keyboard.add(this.createButton("ln", ButtonType.UNARY, 'l', new FormulaActionLogarithm(this)));
      keyboard.add(this.createButton("e", ButtonType.FIXED, 'e', new FormulaActionFixed(this, Fixed.E())));
      //
      keyboard.add(this.createButton("1", ButtonType.NUMBER, '1', new FormulaActionNumber(this, 1)));
      keyboard.add(this.createButton("2", ButtonType.NUMBER, '2', new FormulaActionNumber(this, 2)));
      keyboard.add(this.createButton("3", ButtonType.NUMBER, '3', new FormulaActionNumber(this, 3)));
      keyboard.add(this.createButton("+", ButtonType.BINARY, '+', new FormulaActionAddition(this)));
      keyboard.add(this.createButton("cos", ButtonType.UNARY, 'c', new FormulaActionCosinus(this)));
      keyboard.add(this.createButton("<html>10<sup>x</sup></html>", ButtonType.UNARY, 'w', new FormulaActionTenPower(this)));
      keyboard.add(this.createButton("Esc", ButtonType.ESCAPE, UtilGUI.CHARACTER_ESCAPE, new FormulaActionEscape(this)));
      //
      keyboard.add(this.createButton("0", ButtonType.NUMBER, '0', new FormulaActionNumber(this, 0)));
      keyboard.add(this.createButton(".", ButtonType.COMMA, '.', new FormulaActionComma(this)));
      keyboard.add(this.createButton("()", ButtonType.UNARY, '(', new FormulaActionParenthesis(this)));
      keyboard.add(this.createButton("-", ButtonType.BINARY, '-', new FormulaActionSubtraction(this)));
      keyboard.add(this.createButton("tan", ButtonType.UNARY, 't', new FormulaActionTangent(this)));
      keyboard.add(this.createButton(this.variableType.getSymbol(), ButtonType.VARIABLE, 'v', new FormulaActionVariable(this)));
      keyboard.add(this.createButton("OK", ButtonType.VALIDATE, '\n', new FormulaActionValidate(this)));

      this.add(keyboard, BorderLayout.SOUTH);

      final ActionDelete actionDelete = new ActionDelete();
      this.getActionMap().put(actionDelete.getName(), actionDelete);
      this.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(actionDelete.getShortcut(), actionDelete.getName());

      this.updateEditor();
   }

   /**
    * Create a keyboard button
    *
    * @param text
    *           Button text
    * @param buttonType
    *           Button type
    * @param shortcut
    *           Shortcut
    * @param formulaAction
    *           Action to play if button clicked or shortcut pressed
    * @return Created button
    */
   private JButton createButton(final String text, final ButtonType buttonType, final char shortcut, final FormulaAction formulaAction)
   {
      final ActionButton actionButton = new ActionButton(text, buttonType, shortcut, this.formulaDrawer, formulaAction);
      this.getActionMap().put(text, actionButton);
      this.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT).put(actionButton.getShortcut(), text);
      final JButton button = new JButton(actionButton);
      button.setFont(JHelpConstantsSmooth.FONT_BUTTON.getFont());
      button.setHorizontalTextPosition(SwingConstants.LEFT);
      return button;
   }

   /**
    * Update the editor :Refresh the formula, enable/disable buttons
    */
   private void updateEditor()
   {
      this.updateShowFormula();
      this.updateKeyboard();
   }

   /**
    * Update the keyboard.<br>
    * It enable/disable buttons to avoid a maximum of syntax error on not let the user free to do the mess
    */
   private void updateKeyboard()
   {
      final ActionMap actionMap = this.getActionMap();
      ActionButton actionButton;
      Action action;

      for(final Object key : actionMap.keys())
      {
         action = actionMap.get(key);

         if((action != null) && ((action instanceof ActionButton)))
         {
            actionButton = (ActionButton) action;

            switch(actionButton.getButtonType())
            {
               case BINARY:
                  actionButton.setEnabled(this.canAppendBinary());
               break;
               case COMMA:
                  actionButton.setEnabled(this.canAppendComma());
               break;
               case ESCAPE:
                  actionButton.setEnabled(this.canEscape());
               break;
               case FIXED:
                  actionButton.setEnabled(this.canAppendUnaryFixedVariable());
               break;
               case NUMBER:
                  actionButton.setEnabled(this.canAppendNumber());
               break;
               case UNARY:
                  actionButton.setEnabled(this.canAppendUnaryFixedVariable());
               break;
               case VALIDATE:
                  actionButton.setEnabled(this.canValidate());
               break;
               case VARIABLE:
                  actionButton.setEnabled(this.canAppendUnaryFixedVariable());
               break;
               default:
               break;
            }
         }
      }
   }

   /**
    * Update formula draw
    */
   private void updateShowFormula()
   {
      this.formulaDrawer.drawFormula(this.formula, this.formulaEdited);
   }

   /**
    * Append a binary formula (Such as *, /, ...)
    *
    * @param binary
    *           Binary to append
    */
   void appendBinary(final Binary binary)
   {
      if(!this.canAppendBinary())
      {
         return;
      }

      this.resetEdition();
      binary.setFirst(this.formulaEdited);
      this.formula = this.formula.replaceStrict(this.formulaEdited, binary);
      this.formulaEdited = Undefined.UNDEFINED();
      this.updateEditor();
   }

   /**
    * Append a decimal separator to current number
    */
   void appendComma()
   {
      if(!this.canAppendComma())
      {
         return;
      }

      this.hasComma = true;
      this.number.append('.');

      switch(this.formulaEdited.getType())
      {
         case UNDEFINED:
            this.formulaEdited = new Number();
            this.formula = this.formula.replace(Undefined.UNDEFINED(), this.formulaEdited);
         break;
         case NUMBER:
         break;
         default:
            return;
      }

      ((Number) this.formulaEdited).setValue(this.number.toString());
      this.updateEditor();
   }

   /**
    * Append a fixed value like &pi;, e
    *
    * @param fixed
    *           Fixed value
    */
   void appendFixed(final Fixed fixed)
   {
      if(!this.canAppendUnaryFixedVariable())
      {
         return;
      }

      this.resetEdition();
      this.formulaEdited = fixed;
      this.formula = this.formula.replace(Undefined.UNDEFINED(), this.formulaEdited);

      this.updateEditor();
   }

   /**
    * Append a digit to current number
    *
    * @param number
    *           Digit to append
    */
   void appendNumber(final int number)
   {
      if(!this.canAppendNumber())
      {
         return;
      }

      this.number.append(number);

      switch(this.formulaEdited.getType())
      {
         case UNDEFINED:
            this.formulaEdited = new Number();
            this.formula = this.formula.replace(Undefined.UNDEFINED(), this.formulaEdited);
         break;
         case NUMBER:
         break;
         default:
            return;
      }

      ((Number) this.formulaEdited).setValue(this.number.toString());
      this.updateEditor();
   }

   /**
    * Append an unary formula, like cos, sin, ....
    *
    * @param unary
    *           Formula to append
    */
   void appendUnary(final Unary unary)
   {
      if(!this.canAppendUnaryFixedVariable())
      {
         return;
      }

      this.resetEdition();
      this.formulaEdited = unary;
      this.formula = this.formula.replace(Undefined.UNDEFINED(), this.formulaEdited);
      this.formulaEdited = Undefined.UNDEFINED();
      this.updateEditor();
   }

   /**
    * Append a variable
    */
   void appendVariable()
   {
      if(!this.canAppendUnaryFixedVariable())
      {
         return;
      }

      this.resetEdition();
      this.formulaEdited = new Variable(this.variableType);
      this.formula = this.formula.replace(Undefined.UNDEFINED(), this.formulaEdited);

      this.updateEditor();
   }

   /**
    * Indicates if it is allow to append a binary now
    *
    * @return {@code true} if it is allow to append a binary now
    */
   boolean canAppendBinary()
   {
      return this.formulaEdited.getType() != FormulaType.UNDEFINED;
   }

   /**
    * Indicates if it is allow to append a digit separator now
    *
    * @return {@code true} if it is allow to append a digit separator now
    */
   boolean canAppendComma()
   {
      return (this.canAppendNumber()) && (!this.hasComma);
   }

   /**
    * Indicates if it is allow to append a digit now
    *
    * @return {@code true} if it is allow to append a digit now
    */
   boolean canAppendNumber()
   {
      final FormulaType formulaType = this.formulaEdited.getType();
      return (formulaType == FormulaType.UNDEFINED) || (formulaType == FormulaType.NUMBER);
   }

   /**
    * Indicates if it is allow to append a fixed value now
    *
    * @return {@code true} if it is allow to append a fixed value now
    */
   boolean canAppendUnaryFixedVariable()
   {
      return this.formulaEdited.getType() == FormulaType.UNDEFINED;
   }

   /**
    * Indicates if inside a block that we can escape
    *
    * @return {@code true} if inside a block that we can escape
    */
   boolean canEscape()
   {
      return this.formulaEdited.getParent() != null;
   }

   /**
    * Indicates if formula can be transfer to listener.<br>
    * That is to say the formula look complete (No {@link Undefined} inside)
    *
    * @return {@code true} if formula can be transfer to listener.
    */
   boolean canValidate()
   {
      return !this.formula.containsUndefined();
   }

   /**
    * Delete last edition
    */
   void delete()
   {
      FormulaNature formulaNature = this.formulaEdited.getType().getNature();
      int length;
      Formula searched;
      Formula parent;

      switch(formulaNature)
      {
         case BINARY:
         case BINARY_UPPER:
         case BYNARY_UNDER:
         case CONSTANT_LETTER:
         case UNARY:
         case UNARY_UPPER:
         case VARIABLE:
            this.resetEdition();
            this.formula = this.formula.replaceStrict(this.formulaEdited, Undefined.UNDEFINED());
            this.formulaEdited = Undefined.UNDEFINED();
         break;
         case CONSTANT:
            length = this.number.length();
            this.hasComma &= this.number.charAt(length - 1) != '.';
            this.number.delete(length - 1, length);

            if(this.number.length() > 0)
            {
               ((Number) this.formulaEdited).setValue(this.number.toString());
            }
            else
            {
               this.resetEdition();
               this.formula = this.formula.replaceStrict(this.formulaEdited, Undefined.UNDEFINED());
               this.formulaEdited = Undefined.UNDEFINED();
            }
         break;
         case UNDEFINED:
            this.resetEdition();
            searched = this.formula.search(this.formulaEdited);

            if(searched != null)
            {
               parent = searched.getParent();

               if(parent != null)
               {
                  formulaNature = parent.getType().getNature();

                  if((formulaNature == FormulaNature.BINARY) || (formulaNature == FormulaNature.BINARY_UPPER) || (formulaNature == FormulaNature.BYNARY_UNDER))
                  {
                     final Binary binary = (Binary) parent;
                     this.formulaEdited = binary.getFirst();
                     this.formula = this.formula.replaceStrict(parent, this.formulaEdited);

                     if(this.formulaEdited.getType() == FormulaType.NUMBER)
                     {
                        final Number number = (Number) this.formulaEdited;
                        this.number.append(number.textualSymbol());
                        this.hasComma = this.number.indexOf(".") >= 0;
                     }
                  }
                  else
                  {
                     this.formulaEdited = parent;
                     this.delete();
                     return;
                  }
               }
            }
         break;
         default:
         break;
      }

      this.updateEditor();
   }

   /**
    * Escape current block
    */
   void doEscape()
   {
      if(!this.canEscape())
      {
         return;
      }

      this.resetEdition();
      this.formulaEdited = this.formulaEdited.getParent();
      this.updateEditor();
   }

   /**
    * Validate the formula and alert listener
    */
   void doValidate()
   {
      if(!this.canValidate())
      {
         return;
      }

      if(this.formulaEditorListener != null)
      {
         this.formulaEditorListener.formulaTyped(this.formula);
      }

      this.formula = Undefined.UNDEFINED();
      this.formulaEdited = this.formula;
      this.resetEdition();
      this.updateEditor();
   }

   /**
    * Reset number edition at zero
    */
   void resetEdition()
   {
      this.number.delete(0, this.number.length());
      this.hasComma = false;
   }

   /**
    * Current formula
    *
    * @return Current formula
    */
   public Formula getFormula()
   {
      return this.formula;
   }

   /**
    * Variable type used for variable creation
    *
    * @return Variable type used for variable creation
    */
   public VariableType getVariableType()
   {
      return this.variableType;
   }

   /**
    * Defines/change/remove the formula editor listener.<br>
    * If the listener is {@code null} it will remove the listener
    *
    * @param formulaEditorListener
    *           Listener to register. It can be {@code null} to remove listener
    */
   public void setFormulaEditorListener(final FormulaEditorListener formulaEditorListener)
   {
      this.formulaEditorListener = formulaEditorListener;
   }

   /**
    * Change the variable type to use in variable creation.<br>
    * It will also replace already created variables
    *
    * @param variableType
    *           New variable type
    */
   public void setVariableType(final VariableType variableType)
   {
      if(this.variableType == variableType)
      {
         return;
      }

      this.variableType = variableType;
      this.formula.changeVariableType(this.variableType);
      this.updateEditor();
   }
}