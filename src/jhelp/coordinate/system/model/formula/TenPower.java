package jhelp.coordinate.system.model.formula;

/**
 * Represents a 10<sup>x</sup>
 *
 * @author JHelp <br>
 */
public class TenPower
      extends Unary
{
   /**
    * Create a new instance of TenPower
    */
   public TenPower()
   {
      super(FormulaType.TEN_POWER);
   }

   /**
    * Create a new instance <br>
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
      return new TenPower();
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
      return "10";
   }
}