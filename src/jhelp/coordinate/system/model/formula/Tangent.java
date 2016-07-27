package jhelp.coordinate.system.model.formula;

/**
 * Represents tangent
 *
 * @author JHelp <br>
 */
public class Tangent
      extends Unary
{
   /**
    * Create a new instance of Tangent
    */
   public Tangent()
   {
      super(FormulaType.TANGENT);
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
      return new Tangent();
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
      return "tan";
   }
}