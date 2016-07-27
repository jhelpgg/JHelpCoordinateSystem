package jhelp.coordinate.system.ui.editor;

import jhelp.coordinate.system.model.formula.Logarithm;

/**
 * Action for append logarithm
 *
 * @author JHelp <br>
 */
public class FormulaActionLogarithm
      extends FormulaAction
{
   /**
    * Create a new instance of FormulaActionLogarithm
    *
    * @param formulaEditor
    *           Editor parent
    */
   public FormulaActionLogarithm(final FormulaEditor formulaEditor)
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
      formulaEditor.appendUnary(new Logarithm());
   }
}