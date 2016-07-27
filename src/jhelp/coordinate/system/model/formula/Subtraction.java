package jhelp.coordinate.system.model.formula;

/**
 * Represents a subtraction
 *
 * @author JHelp <br>
 */
public class Subtraction
      extends Binary
{
   /**
    * Create a new instance of Subtraction
    */
   public Subtraction()
   {
      super(FormulaType.SUBSTRACTION);
   }

   /**
    * Create a new instance <br>
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
      return new Subtraction();
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
      return "-";
   }
}