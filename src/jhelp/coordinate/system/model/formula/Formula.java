package jhelp.coordinate.system.model.formula;

import jhelp.util.HashCode;

/**
 * Represents a formula
 *
 * @author JHelp <br>
 */
public abstract class Formula
{
   /** Formula type */
   private final FormulaType formulaType;
   /** Formula parent */
   private Formula           parent;

   /**
    * Create a new instance of Formula
    *
    * @param formulaType
    *           Formula type
    */
   public Formula(final FormulaType formulaType)
   {
      if(formulaType == null)
      {
         throw new NullPointerException("formulaType musn't be null");
      }

      this.formulaType = formulaType;
   }

   /**
    * Append string representation
    *
    * @param stringBuilder
    *           Builder where append string representation
    */
   protected abstract void appendInString(StringBuilder stringBuilder);

   /**
    * Compute specific hash code part
    *
    * @param hashCode
    *           hash code to complete
    */
   protected abstract void computeHashCode(HashCode hashCode);

   /**
    * Indicates if given formula equals to this one.<br>
    * Note the given formula is guarantee to be good instance and not {@code null}, so cast can be done safely (No need to
    * check)
    *
    * @param formula
    *           Formula to compare with
    * @return {@code true} if given formula equals to this one
    */
   protected abstract boolean equalsInternal(Formula formula);

   /**
    * Replace (based on {@link #equals(Object)}) a formula by an other one
    *
    * @param searched
    *           Searched formula to replace
    * @param replace
    *           Formula to replace with
    * @return Formula with replacement
    */
   protected Formula replaceInternal(final Formula searched, final Formula replace)
   {
      return this;
   }

   /**
    * Replace (based on ==) a formula by an other one
    *
    * @param searched
    *           Searched formula to replace
    * @param replace
    *           Formula to replace with
    * @return Formula with replacement
    */
   protected Formula replaceStrictInternal(final Formula searched, final Formula replace)
   {
      return this;
   }

   /**
    * Search a formula equals to given one
    *
    * @param searched
    *           Searched formula
    * @return Found formula <b>OR</b> {@code null} if not found
    */
   protected Formula searchInternal(final Formula searched)
   {
      return null;
   }

   /**
    * Change the parent
    *
    * @param parent
    *           New parent
    */
   protected void setParent(final Formula parent)
   {
      this.parent = parent;
   }

   /**
    * Change a variable type of all variable
    *
    * @param variableType
    *           New variable type
    */
   public void changeVariableType(final VariableType variableType)
   {
   }

   /**
    * Indicates if contains an undefined
    *
    * @return {@code true} if contains an undefined
    */
   public abstract boolean containsUndefined();

   /**
    * Indicates if given object is a formula equals to this one <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param object
    *           Object to compare with
    * @return {@code true} if equals
    * @see java.lang.Object#equals(java.lang.Object)
    */
   @Override
   public boolean equals(final Object object)
   {
      if(this == object)
      {
         return true;
      }

      if(null == object)
      {
         return false;
      }

      if(this.getClass().equals(object.getClass()) == false)
      {
         return false;
      }

      return this.equalsInternal((Formula) object);
   }

   /**
    * Parent
    *
    * @return Parent
    */
   public Formula getParent()
   {
      return this.parent;
   }

   /**
    * Formula type
    *
    * @return Formula type
    */
   public FormulaType getType()
   {
      return this.formulaType;
   }

   /**
    * compute hash code <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @return hash code
    * @see java.lang.Object#hashCode()
    */
   @Override
   public int hashCode()
   {
      final HashCode hashCode = new HashCode();
      hashCode.add(this.getClass().getName());
      this.computeHashCode(hashCode);
      return hashCode.getHashCode();
   }

   /**
    * Replace (based on {@link #equals(Object)}) a formula by an other one
    *
    * @param searched
    *           Searched formula to replace
    * @param replace
    *           Formula to replace with
    * @return Formula with replacement
    */
   public Formula replace(final Formula searched, final Formula replace)
   {
      if(this.equals(searched) == true)
      {
         replace.parent = this.parent;
         return replace;
      }

      return this.replaceInternal(searched, replace);
   }

   /**
    * Replace (based on ==) a formula by an other one
    *
    * @param searched
    *           Searched formula to replace
    * @param replace
    *           Formula to replace with
    * @return Formula with replacement
    */
   public Formula replaceStrict(final Formula searched, final Formula replace)
   {
      if(this == searched)
      {
         replace.parent = this.parent;
         return replace;
      }

      return this.replaceStrictInternal(searched, replace);
   }

   /**
    * Search a formula equals to given one
    *
    * @param searched
    *           Searched formula
    * @return Found formula <b>OR</b> {@code null} if not found
    */
   public Formula search(final Formula searched)
   {
      if(this.equals(searched) == true)
      {
         return this;
      }

      return this.searchInternal(searched);
   }

   /**
    * Textual symbol
    *
    * @return Textual symbol
    */
   public abstract String textualSymbol();

   /**
    * String representation <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @return String representation
    * @see java.lang.Object#toString()
    */
   @Override
   public String toString()
   {
      final StringBuilder stringBuilder = new StringBuilder();
      this.appendInString(stringBuilder);
      return stringBuilder.toString();
   }
}