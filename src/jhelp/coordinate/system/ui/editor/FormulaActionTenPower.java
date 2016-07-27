package jhelp.coordinate.system.ui.editor;

import jhelp.coordinate.system.model.formula.TenPower;

/**
 * Action for append a 10 power of
 *
 * @author JHelp <br>
 */
public class FormulaActionTenPower
      extends FormulaAction
{
   /**
    * Create a new instance of FormulaActionTenPower
    *
    * @param formulaEditor
    *           Editor parent
    */
   public FormulaActionTenPower(final FormulaEditor formulaEditor)
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
      formulaEditor.appendUnary(new TenPower());
   }
}