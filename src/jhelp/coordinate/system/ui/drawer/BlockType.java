package jhelp.coordinate.system.ui.drawer;

/**
 * Block type
 *
 * @author JHelp <br>
 */
public enum BlockType
{
   /** Block with one child, the child is "above" the symbol */
   FIRST_ABOVE,
   /** Block with one child same level as symbol */
   FIRST_NORMAL,
   /** Block with no children */
   NO_FIRST_NO_SECOND,
   /** Block with 2 children, the second is "above" the first */
   SECOND_ABOVE,
   /** Block with 2 children as same level */
   SECOND_ASIDE,
   /** block with 2 children second bellow the first */
   SECOND_BELOW
}