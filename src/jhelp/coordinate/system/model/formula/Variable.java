package jhelp.coordinate.system.model.formula;

import jhelp.util.HashCode;

/**
 * Represents the variable
 *
 * @author JHelp <br>
 */
public class Variable
      extends Formula
{
   /** Variable type */
   private VariableType variableType;

   /**
    * Create a new instance of Variable
    *
    * @param variableType
    *           Variable type
    */
   public Variable(final VariableType variableType)
   {
      super(FormulaType.VARIABLE);

      if(variableType == null)
      {
         throw new NullPointerException("variableType musn't be null");
      }

      this.variableType = variableType;
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
      stringBuilder.append(this.variableType.getSymbol());
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
    * @return {@code true} if given formula equals to this one
    * @see jhelp.coordinate.system.model.formula.Formula#equalsInternal(jhelp.coordinate.system.model.formula.Formula)
    */
   @Override
   protected boolean equalsInternal(final Formula formula)
   {
      // Already checked :)
      return true;
   }

   /**
    * Change a variable type of all variable <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param variableType
    *           New variable type
    * @see jhelp.coordinate.system.model.formula.Formula#changeVariableType(jhelp.coordinate.system.model.formula.VariableType)
    */
   @Override
   public void changeVariableType(final VariableType variableType)
   {
      this.setVariableType(variableType);
   }

   /**
    * Indicates if contains undefined <br>
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
    * Variable type
    *
    * @return Variable type
    */
   public VariableType getVariableType()
   {
      return this.variableType;
   }

   /**
    * Change variable type
    *
    * @param variableType
    *           New variable type
    */
   public void setVariableType(final VariableType variableType)
   {
      if(variableType == null)
      {
         throw new NullPointerException("variableType musn't be null");
      }

      this.variableType = variableType;
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
      return this.variableType.getSymbol();
   }
}