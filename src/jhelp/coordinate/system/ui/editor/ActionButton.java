package jhelp.coordinate.system.ui.editor;

import java.awt.event.ActionEvent;

import javax.swing.JComponent;

import jhelp.gui.action.GenericAction;
import jhelp.util.gui.JHelpFont;
import jhelp.util.gui.JHelpImage;
import jhelp.util.gui.UtilGUI;

/**
 * Action associate to a key board button
 *
 * @author JHelp <br>
 */
public class ActionButton
      extends GenericAction
{
   /** Font used for create icon that shows the short cut */
   private static final JHelpFont ICON_SMALL = new JHelpFont("Arial", 16);

   /**
    * Create Icon for show the short cut
    *
    * @param text
    *           Component text
    * @param shortcut
    *           Short cut
    * @return Created Icon
    */
   private static JHelpImage createIcon(final String text, final char shortcut)
   {
      final JHelpImage image = ActionButton.ICON_SMALL.createImage(String.valueOf(shortcut), 0xFF224488, 0xFFDDDDDD);
      final int width = image.getWidth();
      final int height = image.getHeight();
      final JHelpImage icon = new JHelpImage(width << 1, height << 1);
      icon.startDrawMode();
      icon.drawImage(width, 0, image);
      icon.endDrawMode();
      return icon;
   }

   /** Button type */
   private final ButtonType    buttonType;
   /** Component to focus after action (Trick for shortcut) */
   private final JComponent    componentToFocus;
   /** Action to play if action activate by user */
   private final FormulaAction formulaAction;
   /** Shortcut associated */
   private final char          shortcut;

   /**
    * Create a new instance of ActionButton
    *
    * @param text
    *           Button text
    * @param buttonType
    *           Button type
    * @param shortcut
    *           Shortcut associated
    * @param componentToFocus
    *           Component to focus after action
    * @param formulaAction
    *           Action to play if action activate by user
    */
   public ActionButton(final String text, final ButtonType buttonType, final char shortcut, final JComponent componentToFocus,
         final FormulaAction formulaAction)
   {
      super(text, ActionButton.createIcon(text, shortcut));

      if(buttonType == null)
      {
         throw new NullPointerException("buttonType musn't be null");
      }

      if(formulaAction == null)
      {
         throw new NullPointerException("formulaAction musn't be null");
      }

      this.buttonType = buttonType;
      this.shortcut = shortcut;
      this.componentToFocus = componentToFocus;
      this.formulaAction = formulaAction;
      this.setShortcut(UtilGUI.createKeyStroke(shortcut));
   }

   /**
    * Called when user activate action <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param actionEvent
    *           Action description
    * @see jhelp.gui.action.GenericAction#doActionPerformed(java.awt.event.ActionEvent)
    */
   @Override
   protected void doActionPerformed(final ActionEvent actionEvent)
   {
      this.formulaAction.playAction();

      if(this.componentToFocus != null)
      {
         this.componentToFocus.requestFocusInWindow();
      }
   }

   /**
    * Button type
    *
    * @return Button type
    */
   public ButtonType getButtonType()
   {
      return this.buttonType;
   }

   /**
    * Shortcut
    *
    * @return Shortcut
    */
   public char getShortcutChar()
   {
      return this.shortcut;
   }
}