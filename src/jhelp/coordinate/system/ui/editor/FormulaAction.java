package jhelp.coordinate.system.ui.editor;

/**
 * Action when a button is pressed
 *
 * @author JHelp <br>
 */
public abstract class FormulaAction
{
   /** Editor parent */
   private final FormulaEditor formulaEditor;

   /**
    * Create a new instance of FormulaAction
    *
    * @param formulaEditor
    *           Editor parent
    */
   public FormulaAction(final FormulaEditor formulaEditor)
   {
      this.formulaEditor = formulaEditor;
   }

   /**
    * Called when user activate action
    *
    * @param formulaEditor
    *           Editor parent
    * @see jhelp.coordinate.system.ui.editor.FormulaAction#playAction(jhelp.coordinate.system.ui.editor.FormulaEditor)
    */
   protected abstract void playAction(FormulaEditor formulaEditor);

   /**
    * Called when user activate action
    */
   public final void playAction()
   {
      this.playAction(this.formulaEditor);
   }
}