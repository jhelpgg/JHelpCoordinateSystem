package jhelp.coordinate.system.ui.editor;

import jhelp.coordinate.system.model.formula.Exponential;

/**
 * Action for append exponential
 *
 * @author JHelp <br>
 */
public class FormulaActionExponential
      extends FormulaAction
{
   /**
    * Create a new instance of FormulaActionExponential
    *
    * @param formulaEditor
    *           Editor parent
    */
   public FormulaActionExponential(final FormulaEditor formulaEditor)
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
      formulaEditor.appendUnary(new Exponential());
   }
}