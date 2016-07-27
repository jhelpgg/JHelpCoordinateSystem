package jhelp.coordinate.system.ui.editor;

import jhelp.coordinate.system.model.formula.Multiplication;

/**
 * Action for append a multiplication
 *
 * @author JHelp <br>
 */
public class FormulaActionMultiplication
      extends FormulaAction
{
   /**
    * Create a new instance of FormulaActionMultiplication
    *
    * @param formulaEditor
    *           Editor parent
    */
   public FormulaActionMultiplication(final FormulaEditor formulaEditor)
   {
      super(formulaEditor);
   }

   /**
    * Called when user activate the action <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param formulaEditor
    *           Editor parent
    * @see jhelp.coordinate.system.ui.editor.FormulaAction#playAction(jhelp.coordinate.system.ui.editor.FormulaEditor)
    */
   @Override
   protected void playAction(final FormulaEditor formulaEditor)
   {
      formulaEditor.appendBinary(new Multiplication());
   }
}