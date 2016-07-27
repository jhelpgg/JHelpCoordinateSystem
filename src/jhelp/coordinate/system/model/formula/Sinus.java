package jhelp.coordinate.system.model.formula;

/**
 * Represents sinus
 *
 * @author JHelp <br>
 */
public class Sinus
      extends Unary
{
   /**
    * Create a new instance of Sinus
    */
   public Sinus()
   {
      super(FormulaType.SINUS);
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
      return new Sinus();
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
      return "sin";
   }
}