package jhelp.coordinate.system.ui.editor;

import jhelp.coordinate.system.model.formula.Divide;

/**
 * Append a division
 *
 * @author JHelp <br>
 */
public class FormulaActionDivide
      extends FormulaAction
{
   /**
    * Create a new instance of FormulaActionDivide
    *
    * @param formulaEditor
    *           Editor parent
    */
   public FormulaActionDivide(final FormulaEditor formulaEditor)
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
      formulaEditor.appendBinary(new Divide());
   }
}