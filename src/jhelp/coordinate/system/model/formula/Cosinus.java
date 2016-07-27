package jhelp.coordinate.system.model.formula;

/**
 * Represents cosinus
 *
 * @author JHelp <br>
 */
public class Cosinus
      extends Unary
{
   /**
    * Create a new instance of Cosinus
    */
   public Cosinus()
   {
      super(FormulaType.COSINUS);
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
      return new Cosinus();
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
      return "cos";
   }
}