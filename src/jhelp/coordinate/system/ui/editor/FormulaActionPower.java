package jhelp.coordinate.system.ui.editor;

import jhelp.coordinate.system.model.formula.Power;

/**
 * Action for append a power
 *
 * @author JHelp <br>
 */
public class FormulaActionPower
      extends FormulaAction
{
   /**
    * Create a new instance of FormulaActionPower
    *
    * @param formulaEditor
    *           Editor parent
    */
   public FormulaActionPower(final FormulaEditor formulaEditor)
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
      formulaEditor.appendBinary(new Power());
   }
}