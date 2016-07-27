package jhelp.coordinate.system.model.formula;

/**
 * Represents X<sup>Y</sup>
 * 
 * @author JHelp <br>
 */
public class Power
      extends Binary
{
   /**
    * Create a new instance of Power
    */
   public Power()
   {
      super(FormulaType.POWER);
   }

   /**
    * Create new instance <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    * 
    * @return New instance
    * @see jhelp.coordinate.system.model.formula.Binary#createInstance()
    */
   @Override
   protected Binary createInstance()
   {
      return new Power();
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
      return "";
   }
}