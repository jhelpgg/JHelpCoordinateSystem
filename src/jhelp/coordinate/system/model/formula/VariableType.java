package jhelp.coordinate.system.model.formula;

/**
 * Type of variable
 *
 * @author JHelp <br>
 */
public enum VariableType
{
   /** Parametric t */
   T("t"),
   /** Parametric angle &theta; */
   THETA("θ"),
   /** X coordinate */
   X("x");

   /** Symbol */
   private final String symbol;

   /**
    * Create a new instance of VariableType
    *
    * @param symbol
    *           Symbol
    */
   VariableType(final String symbol)
   {
      this.symbol = symbol;
   }

   /**
    * Symbol
    *
    * @return Symbol
    */
   public String getSymbol()
   {
      return this.symbol;
   }
}