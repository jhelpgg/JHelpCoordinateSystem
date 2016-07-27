package jhelp.coordinate.system.model.formula;

/**
 * Represents a logarithm
 *
 * @author JHelp <br>
 */
public class Logarithm
      extends Unary
{
   /**
    * Create a new instance of Logarithm
    */
   public Logarithm()
   {
      super(FormulaType.LOGARITHM);
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
      return new Logarithm();
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
      return "log";
   }
}