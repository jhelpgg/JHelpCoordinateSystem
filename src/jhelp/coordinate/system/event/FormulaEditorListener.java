package jhelp.coordinate.system.event;

import jhelp.coordinate.system.model.formula.Formula;

/**
 * Listener of formula editor events
 *
 * @author JHelp <br>
 */
public interface FormulaEditorListener
{
   /**
    * Called when formula validate
    *
    * @param formula
    *           Formula typed
    */
   public void formulaTyped(Formula formula);
}