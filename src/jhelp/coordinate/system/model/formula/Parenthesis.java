package jhelp.coordinate.system.model.formula;

/**
 * Represents explicit surround parenthesis
 *
 * @author JHelp <br>
 */
public class Parenthesis
      extends Unary
{
   /**
    * Create a new instance of Parenthesis
    */
   public Parenthesis()
   {
      super(FormulaType.PARENTHESIS);
   }

   /**
    * Create new instance <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @return new instance
    * @see jhelp.coordinate.system.model.formula.Unary#createInstance()
    */
   @Override
   protected Unary createInstance()
   {
      return new Parenthesis();
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