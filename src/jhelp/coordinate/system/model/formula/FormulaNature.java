package jhelp.coordinate.system.model.formula;

/**
 * Nature of a formula
 *
 * @author JHelp <br>
 */
public enum FormulaNature
{
   /** Formula with 2 parameters at same level */
   BINARY,
   /** Formula with 2 parameters, the second "above" the first */
   BINARY_UPPER,
   /** Formula with 2 parameters, the second bellow the first */
   BYNARY_UNDER,
   /** Constant number */
   CONSTANT,
   /** Constant fixed value (&pi;, e) */
   CONSTANT_LETTER,
   /** Formula with one parameter */
   UNARY,
   /** Formula with one parameter "above" the symbol */
   UNARY_UPPER,
   /** Undefined formula */
   UNDEFINED,
   /** The variable */
   VARIABLE
}