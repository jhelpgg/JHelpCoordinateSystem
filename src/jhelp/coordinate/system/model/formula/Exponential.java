package jhelp.coordinate.system.model.formula;

/**
 * Represents exponential
 *
 * @author JHelp <br>
 */
public class Exponential
      extends Unary
{
   /**
    * Create a new instance of Exponential
    */
   public Exponential()
   {
      super(FormulaType.EXPONENTIAL);
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
      return new Exponential();
   }

   /**
    * Textual symbol <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @return Textual symbol
    * @see jhelp.coordinate.system.model.formula.Formula#textualSymbol()
    */
   @Override
   public String textualSymbol()
   {
      return "exp";
   }
}