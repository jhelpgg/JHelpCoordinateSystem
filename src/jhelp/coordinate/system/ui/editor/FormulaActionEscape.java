package jhelp.coordinate.system.ui.editor;

/**
 * Action for escape current block
 *
 * @author JHelp <br>
 */
public class FormulaActionEscape
      extends FormulaAction
{
   /**
    * Create a new instance of FormulaActionEscape
    *
    * @param formulaEditor
    *           Editor parent
    */
   public FormulaActionEscape(final FormulaEditor formulaEditor)
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
      formulaEditor.doEscape();
   }
}