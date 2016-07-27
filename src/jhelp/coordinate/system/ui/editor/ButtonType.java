package jhelp.coordinate.system.ui.editor;

/**
 * Key board button type
 *
 * @author JHelp <br>
 */
public enum ButtonType
{
   /** Indicates a binary operation like *, +, ... */
   BINARY,
   /** Indicates the decimal separator */
   COMMA,
   /** Indicates the escape button */
   ESCAPE,
   /** Indicate a fix vale like &pi;, e, ... */
   FIXED,
   /** Indicates a digit {0, 1, ..., 9} */
   NUMBER,
   /** Indicates an unary operation like sin, cos, ... */
   UNARY,
   /** Indicates the validate button */
   VALIDATE,
   /** Indicates the variable button */
   VARIABLE
}