package jhelp.coordinate.system.ui.editor;

/**
 * Action for validate formula
 *
 * @author JHelp <br>
 */
public class FormulaActionValidate
      extends FormulaAction
{
   /**
    * Create a new instance of FormulaActionValidate
    *
    * @param formulaEditor
    *           Editor parent
    */
   public FormulaActionValidate(final FormulaEditor formulaEditor)
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
      formulaEditor.doValidate();
   }
}