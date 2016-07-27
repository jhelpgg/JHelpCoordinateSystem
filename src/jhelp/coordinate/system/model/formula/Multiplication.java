package jhelp.coordinate.system.model.formula;

/**
 * Represents a multiplication
 *
 * @author JHelp <br>
 */
public class Multiplication
      extends Binary
{
   /**
    * Create a new instance of Multiplication
    */
   public Multiplication()
   {
      super(FormulaType.MULTIPLICATION);
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
      return new Multiplication();
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
      return "*";
   }
}