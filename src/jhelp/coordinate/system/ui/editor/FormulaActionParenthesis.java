package jhelp.coordinate.system.ui.editor;

import jhelp.coordinate.system.model.formula.Parenthesis;

/**
 * Action for append parenthesis
 *
 * @author JHelp <br>
 */
public class FormulaActionParenthesis
      extends FormulaAction
{
   /**
    * Create a new instance of FormulaActionParenthesis
    *
    * @param formulaEditor
    *           Editor parent
    */
   public FormulaActionParenthesis(final FormulaEditor formulaEditor)
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
      formulaEditor.appendUnary(new Parenthesis());
   }
}