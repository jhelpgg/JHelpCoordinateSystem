package jhelp.coordinate.system.model;

import jhelp.coordinate.system.util.UtilCoordinateSystem;
import jhelp.util.math.formal.Function;
import jhelp.util.math.formal.Variable;

/**
 * Equation type y=f(x)
 *
 * @author JHelp <br>
 */
public class EquationYfunctionOfX
      extends EquationDescription
{
   /** Function for compute Y */
   private final Function function;
   /** Y value if constant */
   private final double   value;
   /** Variable to replace inside {@link #function} */
   private final Variable variable;

   /**
    * Create a new instance of EquationYfunctionOfX
    *
    * @param function
    *           Function for Y
    * @param name
    *           Equation name
    * @param color
    *           Equation color IllegalArgumentException If function have more than one variable
    */
   public EquationYfunctionOfX(final Function function, final String name, final int color)
   {
      this(function, name, color, Interval.R);
   }

   /**
    * Create a new instance of EquationYfunctionOfX
    *
    * @param function
    *           Function for Y
    * @param name
    *           Equation name
    * @param color
    *           Equation color
    * @param interval
    *           Interval limit of X where compute the equation IllegalArgumentException If function have more than one variable
    */
   public EquationYfunctionOfX(final Function function, final String name, final int color, final Interval interval)
   {
      super(EquationType.Y_IS_FUNCTION_OF_X, name, color, interval);

      this.function = function.simplifyMaximum();
      this.variable = UtilCoordinateSystem.extractVariableInsideFunction(this.function);

      if(this.variable == null)
      {
         this.value = this.function.obtainRealValueNumber();
      }
      else
      {
         this.value = 0;
      }
   }

   /**
    * Compute Y value
    *
    * @param x
    *           X
    * @return Y computed
    */
   public double function(final double x)
   {
      if(this.variable == null)
      {
         return this.value;
      }

      return this.function.replace(this.variable, x).simplifyMaximum().obtainRealValueNumber();
   }
}