package jhelp.coordinate.system.ui.editor;

import jhelp.coordinate.system.model.formula.Tangent;

/**
 * Action for append tangent
 *
 * @author JHelp <br>
 */
public class FormulaActionTangent
      extends FormulaAction
{
   /**
    * Create a new instance of FormulaActionTangent
    *
    * @param formulaEditor
    *           Editor parent
    */
   public FormulaActionTangent(final FormulaEditor formulaEditor)
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
      formulaEditor.appendUnary(new Tangent());
   }
}