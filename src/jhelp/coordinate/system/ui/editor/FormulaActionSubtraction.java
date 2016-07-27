package jhelp.coordinate.system.ui.editor;

import jhelp.coordinate.system.model.formula.Subtraction;

/**
 * Action for append a subtraction
 *
 * @author JHelp <br>
 */
public class FormulaActionSubtraction
      extends FormulaAction
{
   /**
    * Create a new instance of FormulaActionSubtraction
    *
    * @param formulaEditor
    *           Editor parent
    */
   public FormulaActionSubtraction(final FormulaEditor formulaEditor)
   {
      super(formulaEditor);
   }

   /**
    * Called when user activate action <br>
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
      formulaEditor.appendBinary(new Subtraction());
   }
}