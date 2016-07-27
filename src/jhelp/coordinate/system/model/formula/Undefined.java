package jhelp.coordinate.system.model.formula;

import jhelp.util.HashCode;

/**
 * Undefined value (Used in edition)
 *
 * @author JHelp <br>
 */
public class Undefined
      extends Formula
{
   /**
    * Create a new undefined
    *
    * @return Created undefined
    */
   public static final Undefined UNDEFINED()
   {
      return new Undefined();
   }

   /**
    * Create a new instance of Undefined
    */
   private Undefined()
   {
      super(FormulaType.UNDEFINED);
   }

   /**
    * Append string representation <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param stringBuilder
    *           Builder where append string representation
    * @see jhelp.coordinate.system.model.formula.Formula#appendInString(java.lang.StringBuilder)
    */
   @Override
   protected void appendInString(final StringBuilder stringBuilder)
   {
      stringBuilder.append('?');
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
      // Nothing to do
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
    * @return {@code true} because all already checked (See method description)
    * @see jhelp.coordinate.system.model.formula.Formula#equalsInternal(jhelp.coordinate.system.model.formula.Formula)
    */
   @Override
   protected boolean equalsInternal(final Formula formula)
   {
      // Already checked :)
      return true;
   }

   /**
    * Indicates if their undefined inside <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @return {@code true} obviously
    * @see jhelp.coordinate.system.model.formula.Formula#containsUndefined()
    */
   @Override
   public boolean containsUndefined()
   {
      return true;
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