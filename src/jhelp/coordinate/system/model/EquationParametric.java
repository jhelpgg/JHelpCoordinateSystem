package jhelp.coordinate.system.model;

import jhelp.coordinate.system.util.UtilCoordinateSystem;
import jhelp.util.math.formal.Function;
import jhelp.util.math.formal.Variable;

/**
 * Parametric equation to represents equation type X=f(t), Y=g(t), where t inside[minimum, maximum]
 *
 * @author JHelp <br>
 */
public class EquationParametric
      extends EquationDescription
{
   /** Function for X */
   private final Function functionX;
   /** Function for Y */
   private final Function functionY;
   /** Value of X, if X is constant */
   private final double   valueX;
   /** Value of Y, if Y is constant */
   private final double   valueY;
   /** Variable to replace inside {@link #functionX} */
   private final Variable variableX;
   /** Variable to replace inside {@link #functionY} */
   private final Variable variableY;

   /**
    * Create a new instance of EquationParametric
    *
    * @param functionX
    *           Function for X. The function must depends at maximum of one parameter
    * @param functionY
    *           Function for Y. The function must depends at maximum of one parameter
    * @param name
    *           Equation name
    * @param color
    *           Equation color
    * @param minimum
    *           Minimum value of interval of variable
    * @param maximum
    *           Maximum value of interval of variable
    * @throws IllegalArgumentException
    *            If one function have more than one variable
    */
   public EquationParametric(final Function functionX, final Function functionY, final String name, final int color, final double minimum, final double maximum)
   {
      super(EquationType.PAREMETRIC_EQUATION, name, color, new Interval(minimum, maximum));

      this.functionX = functionX.simplifyMaximum();
      this.variableX = UtilCoordinateSystem.extractVariableInsideFunction(this.functionX);

      if(this.variableX == null)
      {
         this.valueX = this.functionX.obtainRealValueNumber();
      }
      else
      {
         this.valueX = 0;
      }

      this.functionY = functionY.simplifyMaximum();
      this.variableY = UtilCoordinateSystem.extractVariableInsideFunction(this.functionY);

      if(this.variableY == null)
      {
         this.valueY = this.functionY.obtainRealValueNumber();
      }
      else
      {
         this.valueY = 0;
      }
   }

   /**
    * Compute X value for a given t
    *
    * @param t
    *           Variable value
    * @return X computed
    */
   public double functionX(final double t)
   {
      if(this.variableX == null)
      {
         return this.valueX;
      }

      return this.functionX.replace(this.variableX, t).simplifyMaximum().obtainRealValueNumber();
   }

   /**
    * Compute Y value for a given t
    *
    * @param t
    *           Variable value
    * @return Y computed
    */
   public double functionY(final double t)
   {
      if(this.variableY == null)
      {
         return this.valueY;
      }

      return this.functionY.replace(this.variableY, t).simplifyMaximum().obtainRealValueNumber();
   }
}