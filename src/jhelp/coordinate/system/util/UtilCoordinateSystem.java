package jhelp.coordinate.system.util;

import jhelp.coordinate.system.model.formula.Addition;
import jhelp.coordinate.system.model.formula.Binary;
import jhelp.coordinate.system.model.formula.Constant;
import jhelp.coordinate.system.model.formula.Cosinus;
import jhelp.coordinate.system.model.formula.Divide;
import jhelp.coordinate.system.model.formula.Exponential;
import jhelp.coordinate.system.model.formula.Fixed;
import jhelp.coordinate.system.model.formula.Formula;
import jhelp.coordinate.system.model.formula.Logarithm;
import jhelp.coordinate.system.model.formula.Minus;
import jhelp.coordinate.system.model.formula.Multiplication;
import jhelp.coordinate.system.model.formula.Number;
import jhelp.coordinate.system.model.formula.Sinus;
import jhelp.coordinate.system.model.formula.Subtraction;
import jhelp.coordinate.system.model.formula.Tangent;
import jhelp.coordinate.system.model.formula.Unary;
import jhelp.coordinate.system.model.formula.Undefined;
import jhelp.coordinate.system.model.formula.Variable;
import jhelp.coordinate.system.model.formula.VariableType;
import jhelp.util.math.formal.Function;
import jhelp.util.math.formal.VariableList;
import jhelp.util.text.UtilText;

/**
 * Utilities tools for coordinate system
 *
 * @author JHelp <br>
 */
public class UtilCoordinateSystem
{
   /** 10 constant */
   private static final jhelp.util.math.formal.Constant TEN      = new jhelp.util.math.formal.Constant(10);
   /** Variable used */
   private static final jhelp.util.math.formal.Variable VARIABLE = new jhelp.util.math.formal.Variable("x");

   /**
    * Transform a formula to a function
    *
    * @param formula
    *           Formula to transform
    * @return Function created
    */
   private static Function formulaToFunctionInternal(final Formula formula)
   {
      Binary binary;
      Unary unary;
      Constant constant;

      switch(formula.getType())
      {
         case ADDITION:
            binary = (Binary) formula;
            return new jhelp.util.math.formal.Addition(UtilCoordinateSystem.formulaToFunctionInternal(binary.getFirst()),
                  UtilCoordinateSystem.formulaToFunctionInternal(binary.getSecond()));
         case COSINUS:
            unary = (Unary) formula;
            return new jhelp.util.math.formal.Cosinus(UtilCoordinateSystem.formulaToFunctionInternal(unary.getParameter()));
         case DIVIDE:
            binary = (Binary) formula;
            return new jhelp.util.math.formal.Division(UtilCoordinateSystem.formulaToFunctionInternal(binary.getFirst()),
                  UtilCoordinateSystem.formulaToFunctionInternal(binary.getSecond()));
         case E:
            return jhelp.util.math.formal.Constant.E;
         case EXPONENTIAL:
            unary = (Unary) formula;
            return new jhelp.util.math.formal.Exponential(UtilCoordinateSystem.formulaToFunctionInternal(unary.getParameter()));
         case LOGARITHM:
            unary = (Unary) formula;
            return new jhelp.util.math.formal.Logarithm(UtilCoordinateSystem.formulaToFunctionInternal(unary.getParameter()));
         case MINUS:
            unary = (Unary) formula;
            return new jhelp.util.math.formal.MinusUnary(UtilCoordinateSystem.formulaToFunctionInternal(unary.getParameter()));
         case MULTIPLICATION:
            binary = (Binary) formula;
            return new jhelp.util.math.formal.Multiplication(UtilCoordinateSystem.formulaToFunctionInternal(binary.getFirst()),
                  UtilCoordinateSystem.formulaToFunctionInternal(binary.getSecond()));
         case NUMBER:
            constant = (Constant) formula;
            return new jhelp.util.math.formal.Constant(constant.getValue());
         case PARENTHESIS:
            unary = (Unary) formula;
            return UtilCoordinateSystem.formulaToFunctionInternal(unary.getParameter());
         case PI:
            return jhelp.util.math.formal.Constant.PI;
         case POWER:
            binary = (Binary) formula;
            return Function.createPower(UtilCoordinateSystem.formulaToFunctionInternal(binary.getFirst()),
                  UtilCoordinateSystem.formulaToFunctionInternal(binary.getSecond()));
         case SINUS:
            unary = (Unary) formula;
            return new jhelp.util.math.formal.Sinus(UtilCoordinateSystem.formulaToFunctionInternal(unary.getParameter()));
         case SUBSTRACTION:
            binary = (Binary) formula;
            return new jhelp.util.math.formal.Subtraction(UtilCoordinateSystem.formulaToFunctionInternal(binary.getFirst()),
                  UtilCoordinateSystem.formulaToFunctionInternal(binary.getSecond()));
         case TANGENT:
            unary = (Unary) formula;
            return new jhelp.util.math.formal.Tangent(UtilCoordinateSystem.formulaToFunctionInternal(unary.getParameter()));
         case TEN_POWER:
            unary = (Unary) formula;
            return Function.createPower(UtilCoordinateSystem.TEN, UtilCoordinateSystem.formulaToFunctionInternal(unary.getParameter()));
         case VARIABLE:
            return UtilCoordinateSystem.VARIABLE;
         default:
            return jhelp.util.math.formal.Constant.UNDEFINED;
      }
   }

   /**
    * Transform a function to formula
    *
    * @param function
    *           Function to transform
    * @param variableType
    *           Variable type used for create variable
    * @return Created function
    */
   private static Formula functionToFormulaInternal(final Function function, final VariableType variableType)
   {
      if(function instanceof jhelp.util.math.formal.Addition)
      {
         final jhelp.util.math.formal.Addition addition = (jhelp.util.math.formal.Addition) function;
         final Addition result = new Addition();
         result.setFirst(UtilCoordinateSystem.functionToFormulaInternal(addition.getParameter1(), variableType));
         result.setSecond(UtilCoordinateSystem.functionToFormulaInternal(addition.getParameter2(), variableType));
         return result;
      }

      if(function instanceof jhelp.util.math.formal.Constant)
      {
         final jhelp.util.math.formal.Constant constant = (jhelp.util.math.formal.Constant) function;

         if(jhelp.util.math.formal.Constant.PI.equals(constant))
         {
            return Fixed.PI();
         }

         if(jhelp.util.math.formal.Constant.E.equals(constant))
         {
            return Fixed.E();
         }

         final Number number = new Number();
         number.setValue(constant.obtainRealValueNumber());
         return number;
      }

      if(function instanceof jhelp.util.math.formal.Cosinus)
      {
         final jhelp.util.math.formal.Cosinus cosinus = (jhelp.util.math.formal.Cosinus) function;
         final Cosinus result = new Cosinus();
         result.setParameter(UtilCoordinateSystem.functionToFormulaInternal(cosinus.getParameter(), variableType));
         return result;
      }

      if(function instanceof jhelp.util.math.formal.Division)
      {
         final jhelp.util.math.formal.Division division = (jhelp.util.math.formal.Division) function;
         final Divide result = new Divide();
         result.setFirst(UtilCoordinateSystem.functionToFormulaInternal(division.getParameter1(), variableType));
         result.setSecond(UtilCoordinateSystem.functionToFormulaInternal(division.getParameter2(), variableType));
         return result;
      }

      if(function instanceof jhelp.util.math.formal.Exponential)
      {
         final jhelp.util.math.formal.Exponential exponential = (jhelp.util.math.formal.Exponential) function;
         final Exponential result = new Exponential();
         result.setParameter(UtilCoordinateSystem.functionToFormulaInternal(exponential.getParameter(), variableType));
         return result;
      }

      if(function instanceof jhelp.util.math.formal.Logarithm)
      {
         final jhelp.util.math.formal.Logarithm logarithm = (jhelp.util.math.formal.Logarithm) function;
         final Logarithm result = new Logarithm();
         result.setParameter(UtilCoordinateSystem.functionToFormulaInternal(logarithm.getParameter(), variableType));
         return result;
      }

      if(function instanceof jhelp.util.math.formal.MinusUnary)
      {
         final jhelp.util.math.formal.MinusUnary minus = (jhelp.util.math.formal.MinusUnary) function;
         final Minus result = new Minus();
         result.setParameter(UtilCoordinateSystem.functionToFormulaInternal(minus.getParameter(), variableType));
         return result;
      }

      if(function instanceof jhelp.util.math.formal.Multiplication)
      {
         final jhelp.util.math.formal.Multiplication multiplication = (jhelp.util.math.formal.Multiplication) function;
         final Multiplication result = new Multiplication();
         result.setFirst(UtilCoordinateSystem.functionToFormulaInternal(multiplication.getParameter1(), variableType));
         result.setSecond(UtilCoordinateSystem.functionToFormulaInternal(multiplication.getParameter2(), variableType));
         return result;
      }

      if(function instanceof jhelp.util.math.formal.Sinus)
      {
         final jhelp.util.math.formal.Sinus sinus = (jhelp.util.math.formal.Sinus) function;
         final Sinus result = new Sinus();
         result.setParameter(UtilCoordinateSystem.functionToFormulaInternal(sinus.getParameter(), variableType));
         return result;
      }

      if(function instanceof jhelp.util.math.formal.Subtraction)
      {
         final jhelp.util.math.formal.Subtraction subtraction = (jhelp.util.math.formal.Subtraction) function;
         final Subtraction result = new Subtraction();
         result.setFirst(UtilCoordinateSystem.functionToFormulaInternal(subtraction.getParameter1(), variableType));
         result.setSecond(UtilCoordinateSystem.functionToFormulaInternal(subtraction.getParameter2(), variableType));
         return result;
      }

      if(function instanceof jhelp.util.math.formal.Tangent)
      {
         final jhelp.util.math.formal.Tangent tangent = (jhelp.util.math.formal.Tangent) function;
         final Tangent result = new Tangent();
         result.setParameter(UtilCoordinateSystem.functionToFormulaInternal(tangent.getParameter(), variableType));
         return result;
      }

      if(function instanceof jhelp.util.math.formal.Variable)
      {
         return new Variable(variableType);
      }

      return Undefined.UNDEFINED();
   }

   /**
    * Extract the variable inside the function.<br>
    * The function MUST have no more than one variable, else an exception is return<br>
    * If the function have no variable, it returns {@code null}
    *
    * @param function
    *           Function to extract its variable
    * @return Extracted variable OR {@code null} if no variable
    * @throws IllegalArgumentException
    *            if function have more than one variable
    */
   public static jhelp.util.math.formal.Variable extractVariableInsideFunction(final Function function)
   {
      final VariableList variableList = function.variableList();
      final int number = variableList.numberOfVariables();

      switch(number)
      {
         case 0:
            return null;
         case 1:
            return variableList.get(0);
         default:
            throw new IllegalArgumentException(UtilText.concatenate("Given function '", function, "' have more than one variable"));
      }
   }

   /**
    * Transform a formula to a function<br>
    * The given formula MUST NOT contains an {@link Undefined}
    *
    * @param formula
    *           Formula to convert
    * @return Converted function
    * @throws IllegalArgumentException
    *            if formula contains at least one {@link Undefined}
    */
   public static Function formulaToFunction(final Formula formula)
   {
      if(formula.containsUndefined())
      {
         throw new IllegalArgumentException("Given formula contains at least an UNDEFINED, so can't be converted");
      }

      return UtilCoordinateSystem.formulaToFunctionInternal(formula).simplifyMaximum();
   }

   /**
    * Transform a function to formula<br>
    * The function MUST NOT have more than one different variable. By example <code>x*x+(1/x)</code> or <code>3+8</code> are
    * allowed but <code>x+y</code> is forbidden
    *
    * @param function
    *           Function to transform
    * @param variableType
    *           Variable type to use for create variable
    * @return Created formula
    * @throws IllegalArgumentException
    *            if function have more than one variable
    */
   public static Formula functionToFormula(Function function, final VariableType variableType)
   {
      function = function.simplifyMaximum();
      UtilCoordinateSystem.extractVariableInsideFunction(function);
      return UtilCoordinateSystem.functionToFormulaInternal(function, variableType);
   }
}