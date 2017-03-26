package jhelp.coordinate.system.model.formula;

import jhelp.util.HashCode;

/**
 * Represents a binary formula like *, +, ...
 *
 * @author JHelp <br>
 */
public abstract class Binary
      extends Formula
{
   /** First/left parameter */
   private Formula first;
   /** Second/right parameter */
   private Formula second;

   /**
    * Create a new instance of Binary
    *
    * @param formulaType
    *           Formula type
    */
   public Binary(final FormulaType formulaType)
   {
      super(formulaType);
      this.setFirst(Undefined.UNDEFINED());
      this.setSecond(Undefined.UNDEFINED());
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
      FormulaNature formulaNature = this.first.getType().getNature();
      boolean parenthesis = (formulaNature == FormulaNature.BINARY) || (formulaNature == FormulaNature.BINARY_UPPER)
            || (formulaNature == FormulaNature.BYNARY_UNDER);

      if(parenthesis)
      {
         stringBuilder.append('(');
      }

      this.first.appendInString(stringBuilder);

      if(parenthesis)
      {
         stringBuilder.append(')');
      }

      stringBuilder.append(this.textualSymbol());

      formulaNature = this.second.getType().getNature();
      parenthesis = (formulaNature == FormulaNature.BINARY) || (formulaNature == FormulaNature.BINARY_UPPER) || (formulaNature == FormulaNature.BYNARY_UNDER);

      if(parenthesis)
      {
         stringBuilder.append('(');
      }

      this.second.appendInString(stringBuilder);

      if(parenthesis)
      {
         stringBuilder.append(')');
      }
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
      hashCode.add(this.first.hashCode());
      hashCode.add(this.second.hashCode());
   }

   /**
    * Create a new instance of same type
    *
    * @return New instance
    */
   protected abstract Binary createInstance();

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
      final Binary binary = (Binary) formula;
      return (this.first.equalsInternal(binary.first)) && (this.second.equalsInternal(binary.second));
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
      final Binary binary = this.createInstance();
      binary.setFirst(this.first.replace(searched, replace));
      binary.setSecond(this.second.replace(searched, replace));
      return binary;
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
    * @see jhelp.coordinate.system.model.formula.Formula#replaceStrictInternal(Formula, Formula)
    */
   @Override
   protected Formula replaceStrictInternal(final Formula searched, final Formula replace)
   {
      final Binary binary = this.createInstance();
      binary.setFirst(this.first.replaceStrict(searched, replace));
      binary.setSecond(this.second.replaceStrict(searched, replace));
      return binary;
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
      final Formula formula = this.first.search(searched);

      if(formula != null)
      {
         return formula;
      }

      return this.second.search(searched);
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
      this.first.changeVariableType(variableType);
      this.second.changeVariableType(variableType);
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
      return (this.first.containsUndefined()) || (this.second.containsUndefined());
   }

   /**
    * First/left formula
    *
    * @return First/left formula
    */
   public Formula getFirst()
   {
      return this.first;
   }

   /**
    * Second/right formula
    *
    * @return Second/right formula
    */
   public Formula getSecond()
   {
      return this.second;
   }

   /**
    * Change first/left formula
    *
    * @param first
    *           New first/left formula
    */
   public void setFirst(final Formula first)
   {
      if(first == null)
      {
         throw new NullPointerException("first musn't be null");
      }

      this.first = first;
      this.first.setParent(this);
   }

   /**
    * Change second/right formula
    *
    * @param second
    *           New second/right formula
    */
   public void setSecond(final Formula second)
   {
      if(second == null)
      {
         throw new NullPointerException("second musn't be null");
      }

      this.second = second;
      this.second.setParent(this);
   }
}