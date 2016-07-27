package jhelp.coordinate.system.model.formula;

/**
 * Formula type
 *
 * @author JHelp <br>
 */
public enum FormulaType
{
   /** Addition */
   ADDITION(FormulaNature.BINARY),
   /** Cosinus */
   COSINUS(FormulaNature.UNARY),
   /** Division */
   DIVIDE(FormulaNature.BYNARY_UNDER),
   /** Constant e */
   E(FormulaNature.CONSTANT_LETTER),
   /** Exponential */
   EXPONENTIAL(FormulaNature.UNARY),
   /** Logarithm */
   LOGARITHM(FormulaNature.UNARY),
   /** Unary minus */
   MINUS(FormulaNature.UNARY),
   /** Multiplication */
   MULTIPLICATION(FormulaNature.BINARY),
   /** Constant number */
   NUMBER(FormulaNature.CONSTANT),
   /** Explicit surround parenthesis */
   PARENTHESIS(FormulaNature.UNARY),
   /** Constant &pi; */
   PI(FormulaNature.CONSTANT_LETTER),
   /** x<sup>y</sup> */
   POWER(FormulaNature.BINARY_UPPER),
   /** Sinus */
   SINUS(FormulaNature.UNARY),
   /** Subtraction */
   SUBSTRACTION(FormulaNature.BINARY),
   /** Tangent */
   TANGENT(FormulaNature.UNARY),
   /** 10<sup>x</sup> */
   TEN_POWER(FormulaNature.UNARY_UPPER),
   /** Undefined formula */
   UNDEFINED(FormulaNature.UNDEFINED),
   /** The variable */
   VARIABLE(FormulaNature.VARIABLE);

   /** Formula nature */
   private final FormulaNature formulaNature;

   /**
    * Create a new instance of FormulaType
    *
    * @param formulaNature
    *           Formula nature
    */
   FormulaType(final FormulaNature formulaNature)
   {
      this.formulaNature = formulaNature;
   }

   /**
    * Formula nature
    *
    * @return Formula nature
    */
   public FormulaNature getNature()
   {
      return this.formulaNature;
   }
}