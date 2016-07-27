package jhelp.coordinate.system.ui.editor;

import jhelp.coordinate.system.model.formula.Cosinus;

/**
 * Append a cosinus
 *
 * @author JHelp <br>
 */
public class FormulaActionCosinus
      extends FormulaAction
{
   /**
    * Create a new instance of FormulaActionCosinus
    *
    * @param formulaEditor
    *           Editor parent
    */
   public FormulaActionCosinus(final FormulaEditor formulaEditor)
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
      formulaEditor.appendUnary(new Cosinus());
   }
}