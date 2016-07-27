package jhelp.coordinate.system.model.formula;

/**
 * Represents a minus
 *
 * @author JHelp <br>
 */
public class Minus
      extends Unary
{
   /**
    * Create a new instance of Minus
    */
   public Minus()
   {
      super(FormulaType.MINUS);
   }

   /**
    * Create new instance <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @return New instance
    * @see jhelp.coordinate.system.model.formula.Unary#createInstance()
    */
   @Override
   protected Unary createInstance()
   {
      return new Minus();
   }

   /**
    * Textual representation <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @return Textual representation
    * @see jhelp.coordinate.system.model.formula.Formula#textualSymbol()
    */
   @Override
   public String textualSymbol()
   {
      return "-";
   }
}