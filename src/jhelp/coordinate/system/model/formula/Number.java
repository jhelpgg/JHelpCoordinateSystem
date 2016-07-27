package jhelp.coordinate.system.model.formula;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import jhelp.util.HashCode;
import jhelp.util.math.UtilMath;

/**
 * Represents a number
 *
 * @author JHelp <br>
 */
public class Number
      extends Constant
{
   /** Format a number */
   private static final DecimalFormat DECIMAL_FORMAT;
   static
   {
      DECIMAL_FORMAT = new DecimalFormat();

      final DecimalFormatSymbols decimalFormatSymbols = new DecimalFormatSymbols();
      decimalFormatSymbols.setGroupingSeparator(' ');
      Number.DECIMAL_FORMAT.setDecimalFormatSymbols(decimalFormatSymbols);
      Number.DECIMAL_FORMAT.setGroupingUsed(false);

      Number.DECIMAL_FORMAT.setMinimumIntegerDigits(1);
      Number.DECIMAL_FORMAT.setMinimumFractionDigits(1);
      Number.DECIMAL_FORMAT.setMaximumFractionDigits(3);
   }

   /** String representation */
   private String representation;
   /** Value */
   private double value;

   /**
    * Create a new instance of Number
    */
   public Number()
   {
      super(FormulaType.NUMBER);
      this.value = 0.0;
      this.representation = "0";
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
      hashCode.add(this.value);
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
      return UtilMath.equals(this.value, ((Number) formula).value);
   }

   /**
    * Value <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @return value
    * @see jhelp.coordinate.system.model.formula.Constant#getValue()
    */
   @Override
   public double getValue()
   {
      return this.value;
   }

   /**
    * Change the value
    *
    * @param value
    *           new value
    */
   public void setValue(final double value)
   {
      this.value = value;
      this.representation = Number.DECIMAL_FORMAT.format(value);
   }

   /**
    * Change value
    *
    * @param value
    *           New value
    */
   public void setValue(final String value)
   {
      try
      {
         this.value = Double.parseDouble(value);
         this.representation = value;
      }
      catch(final Exception exception)
      {
      }
   }

   /**
    * Textual representation <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @return Textual representation
    * @see jhelp.coordinate.system.model.formula.Formula#textualSymbol()
    */
   @Override
   public String textualSymbol()
   {
      return this.representation;
   }
}