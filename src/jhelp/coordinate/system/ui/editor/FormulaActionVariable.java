package jhelp.coordinate.system.ui.editor;

/**
 * Action for append a variable
 *
 * @author JHelp <br>
 */
public class FormulaActionVariable
      extends FormulaAction
{
   /**
    * Create a new instance of FormulaActionVariable
    *
    * @param formulaEditor
    *           Editor parent
    */
   public FormulaActionVariable(final FormulaEditor formulaEditor)
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
    *           Parent editor
    * @see jhelp.coordinate.system.ui.editor.FormulaAction#playAction(jhelp.coordinate.system.ui.editor.FormulaEditor)
    */
   @Override
   protected void playAction(final FormulaEditor formulaEditor)
   {
      formulaEditor.appendVariable();
   }
}