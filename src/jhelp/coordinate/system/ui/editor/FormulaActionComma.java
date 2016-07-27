package jhelp.coordinate.system.ui.editor;

/**
 * Action for append digit separaton
 *
 * @author JHelp <br>
 */
public class FormulaActionComma
      extends FormulaAction
{
   /**
    * Create a new instance of FormulaActionComma
    *
    * @param formulaEditor
    *           Editor parent
    */
   public FormulaActionComma(final FormulaEditor formulaEditor)
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
      formulaEditor.appendComma();
   }
}