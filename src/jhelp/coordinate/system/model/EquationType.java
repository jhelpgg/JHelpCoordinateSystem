package jhelp.coordinate.system.model;

/**
 * Type of equation
 *
 * @author JHelp <br>
 */
public enum EquationType
{
   /**
    * Indicates a parametric equation, they are :<br>
    * <table border=2>
    * <tr>
    * <th>Y=f(t)<br>
    * X=f(t)</th>
    * </tr>
    * </table>
    * Where t in inside a given interval
    */
   PAREMETRIC_EQUATION,
   /** Indicates an equation of type Y=f(X), they may have a specified X interval, by default "R" is taken */
   Y_IS_FUNCTION_OF_X
}