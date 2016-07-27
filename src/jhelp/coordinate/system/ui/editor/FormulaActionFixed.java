package jhelp.coordinate.system.ui.editor;

import jhelp.coordinate.system.model.formula.Fixed;

/**
 * Action for append a fixed value
 *
 * @author JHelp <br>
 */
public class FormulaActionFixed
      extends FormulaAction
{
   /** Value to append */
   private final Fixed fixed;

   /**
    * Create a new instance of FormulaActionFixed
    *
    * @param formulaEditor
    *           Editor parent
    * @param fixed
    *           Value to append
    */
   public FormulaActionFixed(final FormulaEditor formulaEditor, final Fixed fixed)
   {
      super(formulaEditor);

      if(fixed == null)
      {
         throw new NullPointerException("fixed musn't be null");
      }

      this.fixed = fixed;
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
      formulaEditor.appendFixed(this.fixed.duplicate());
   }
}