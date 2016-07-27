package jhelp.coordinate.system.model.formula;

import jhelp.util.HashCode;

/**
 * Describe formula with one parameter
 *
 * @author JHelp <br>
 */
public abstract class Unary
      extends Formula
{
   /** Parameter */
   private Formula parameter;

   /**
    * Create a new instance of Unary
    *
    * @param formulaType
    *           Formula type
    */
   public Unary(final FormulaType formulaType)
   {
      super(formulaType);
      this.setParameter(Undefined.UNDEFINED());
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
      stringBuilder.append('(');
      this.parameter.appendInString(stringBuilder);
      stringBuilder.append(')');
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
      hashCode.add(this.parameter.hashCode());
   }

   /**
    * Create a new instance
    *
    * @return New instance
    */
   protected abstract Unary createInstance();

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
      return this.parameter.equalsInternal(((Unary) formula).parameter);
   }

   /**
    * Replace (based on {@link #equals(Object)}) a formula by an other one <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param searched
    *           Searched formula to replace
    * @param replace
    *           Formula to replace with
    * @return Formula with replacement
    * @see jhelp.coordinate.system.model.formula.Formula#replaceInternal(jhelp.coordinate.system.model.formula.Formula,
    *      jhelp.coordinate.system.model.formula.Formula)
    */
   @Override
   protected Formula replaceInternal(final Formula searched, final Formula replace)
   {
      final Unary unary = this.createInstance();
      unary.setParameter(this.parameter.replace(searched, replace));
      return unary;
   }

   /**
    * Replace (based on ==) a formula by an other one <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param searched
    *           Searched formula to replace
    * @param replace
    *           Formula to replace with
    * @return Formula with replacement
    * @see jhelp.coordinate.system.model.formula.Formula#replaceInternal(jhelp.coordinate.system.model.formula.Formula,
    *      jhelp.coordinate.system.model.formula.Formula)
    */
   @Override
   protected Formula replaceStrictInternal(final Formula searched, final Formula replace)
   {
      final Unary unary = this.createInstance();
      unary.setParameter(this.parameter.replaceStrict(searched, replace));
      return unary;
   }

   /**
    * Search a formula equals to given one <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param searched
    *           Searched formula
    * @return Found formula <b>OR</b> {@code null} if not found
    * @see jhelp.coordinate.system.model.formula.Formula#searchInternal(jhelp.coordinate.system.model.formula.Formula)
    */
   @Override
   protected Formula searchInternal(final Formula searched)
   {
      return this.parameter.search(searched);
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
      this.parameter.changeVariableType(variableType);
   }

   /**
    * Indicates if contains an undefined <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @return {@code true} if contains an undefined
    * @see jhelp.coordinate.system.model.formula.Formula#containsUndefined()
    */
   @Override
   public boolean containsUndefined()
   {
      return this.parameter.containsUndefined();
   }

   /**
    * Unary parameter
    *
    * @return Unary parameter
    */
   public Formula getParameter()
   {
      return this.parameter;
   }

   /**
    * Change parameter
    *
    * @param parameter
    *           New parameter
    */
   public void setParameter(final Formula parameter)
   {
      if(parameter == null)
      {
         throw new NullPointerException("parameter musn't be null");
      }

      this.parameter = parameter;
      this.parameter.setParent(this);
   }
}