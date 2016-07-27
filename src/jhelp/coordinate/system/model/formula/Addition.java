package jhelp.coordinate.system.model.formula;

/**
 * Represents an addition
 *
 * @author JHelp <br>
 */
public class Addition
      extends Binary
{
   /**
    * Create a new instance of Addition
    */
   public Addition()
   {
      super(FormulaType.ADDITION);
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
      return new Addition();
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
      return "+";
   }
}