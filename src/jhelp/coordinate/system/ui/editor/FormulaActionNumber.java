package jhelp.coordinate.system.ui.editor;

/**
 * Action for append a digit
 *
 * @author JHelp <br>
 */
public class FormulaActionNumber
      extends FormulaAction
{
   /** Digit to append */
   private final int number;

   /**
    * Create a new instance of FormulaActionNumber
    *
    * @param formulaEditor
    *           Editor parent
    * @param number
    *           Digit to append
    */
   public FormulaActionNumber(final FormulaEditor formulaEditor, final int number)
   {
      super(formulaEditor);
      this.number = number;
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
      formulaEditor.appendNumber(this.number);
   }
}