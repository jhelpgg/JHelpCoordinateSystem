package jhelp.coordinate.system.model.formula;

import jhelp.util.HashCode;

/**
 * Fixed value symbol like &pi; e
 *
 * @author JHelp <br>
 */
public class Fixed
      extends Constant
{
   /**
    * Create a new e
    *
    * @return New e
    */
   public static final Fixed E()
   {
      return new Fixed(FormulaType.E, Math.E, "e");
   }

   /**
    * Create new &pi;
    *
    * @return New &pi;
    */
   public static final Fixed PI()
   {
      return new Fixed(FormulaType.PI, Math.PI, "π");
   }

   /** Textual symbol */
   private final String symbol;
   /** Value */
   private final double value;

   /**
    * Create a new instance of Fixed
    *
    * @param formulaType
    *           Formula type
    * @param value
    *           Value
    * @param symbol
    *           Textual symbol
    */
   private Fixed(final FormulaType formulaType, final double value, final String symbol)
   {
      super(formulaType);
      this.value = value;
      this.symbol = symbol;
   }

   /**
    * Compute specific hash code part <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param hashCode
    *           hash code to complete
    * @see jhelp.coordinate.system.model.formula.Formula#computeHashCode(jhelp.util.HashCode)
    */
   @Override
   protected void computeHashCode(final HashCode hashCode)
   {
      hashCode.add(this.symbol);
   }

   /**
    * Indicates if given formula equals to this one.<br>
    * Note the given formula is guarantee to be good instance and not {@code null}, so cast can be done safely (No need to
    * check) <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param formula
    *           Formula to compare with
    * @return {@code true} if given formula equals to this one
    * @see jhelp.coordinate.system.model.formula.Formula#equalsInternal(jhelp.coordinate.system.model.formula.Formula)
    */
   @Override
   protected boolean equalsInternal(final Formula formula)
   {
      return this.symbol.equals(((Fixed) formula).symbol);
   }

   /**
    * Duplicate the fixed
    *
    * @return Clone
    */
   public Fixed duplicate()
   {
      return new Fixed(this.getType(), this.value, this.symbol);
   }

   /**
    * Value <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @return Value
    * @see jhelp.coordinate.system.model.formula.Constant#getValue()
    */
   @Override
   public double getValue()
   {
      return this.value;
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
      return this.symbol;
   }
}