package jhelp.coordinate.system.model.formula;

/**
 * Represents a division
 *
 * @author JHelp <br>
 */
public class Divide
      extends Binary
{
   /**
    * Create a new instance of Divide
    */
   public Divide()
   {
      super(FormulaType.DIVIDE);
   }

   /**
    * Create an instance <br>
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
      return new Divide();
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