package jhelp.coordinate.system.ui.editor;

import jhelp.coordinate.system.model.formula.Sinus;

/**
 * Action for append sinus
 *
 * @author JHelp <br>
 */
public class FormulaActionSinus
      extends FormulaAction
{
   /**
    * Create a new instance of FormulaActionSinus
    *
    * @param formulaEditor
    *           Editor parent
    */
   public FormulaActionSinus(final FormulaEditor formulaEditor)
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
      formulaEditor.appendUnary(new Sinus());
   }
}