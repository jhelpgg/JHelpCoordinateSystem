package jhelp.coordinate.system.model.formula;

/**
 * Represents a constant
 *
 * @author JHelp <br>
 */
public abstract class Constant
      extends Formula
{
   /**
    * Create a new instance of Constant
    *
    * @param formulaType
    *           formula type
    */
   public Constant(final FormulaType formulaType)
   {
      super(formulaType);
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
      stringBuilder.append(this.textualSymbol());
   }

   /**
    * Indicates if contains an undefined <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @return {@code false} obviously
    * @see jhelp.coordinate.system.model.formula.Formula#containsUndefined()
    */
   @Override
   public boolean containsUndefined()
   {
      return false;
   }

   /**
    * Constant value
    *
    * @return Constant value
    */
   public abstract double getValue();
}