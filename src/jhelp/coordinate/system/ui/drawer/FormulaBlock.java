package jhelp.coordinate.system.ui.drawer;

import java.awt.geom.Arc2D;

import jhelp.coordinate.system.model.formula.Binary;
import jhelp.coordinate.system.model.formula.Formula;
import jhelp.coordinate.system.model.formula.FormulaType;
import jhelp.coordinate.system.model.formula.Unary;
import jhelp.util.gui.JHelpFont;
import jhelp.util.gui.JHelpImage;
import jhelp.util.math.UtilMath;

/**
 * Formula organize in hierarchy of blocks to draw it more easier
 *
 * @author JHelp <br>
 */
public class FormulaBlock
{
   /**
    * Create formula hierarchy of blocks
    *
    * @param formula
    *           Formula to represents
    * @param formulaSelected
    *           Selected formula inside formula to represents
    * @return created hierarchy
    */
   public static FormulaBlock createBlocks(final Formula formula, final Formula formulaSelected)
   {
      BlockType blockType = BlockType.NO_FIRST_NO_SECOND;
      boolean haveParenthesis = false;
      String symbol = formula.textualSymbol();
      FormulaBlock first = null;
      FormulaBlock second = null;
      final boolean selected = (formula == formulaSelected) || (formula.getType() == FormulaType.UNDEFINED);

      Binary binary;
      Unary unary;

      switch(formula.getType().getNature())
      {
         case BINARY:
            blockType = BlockType.SECOND_ASIDE;
            binary = (Binary) formula;

            first = FormulaBlock.createBlocks(binary.getFirst(), formulaSelected);
            first.haveParenthesis |= first.blockType == BlockType.SECOND_ASIDE;

            second = FormulaBlock.createBlocks(binary.getSecond(), formulaSelected);
            second.haveParenthesis |= second.blockType == BlockType.SECOND_ASIDE;
         break;
         case BINARY_UPPER:
            blockType = BlockType.SECOND_ABOVE;
            binary = (Binary) formula;

            first = FormulaBlock.createBlocks(binary.getFirst(), formulaSelected);
            first.haveParenthesis |= (first.blockType == BlockType.SECOND_ASIDE) || (first.blockType == BlockType.SECOND_BELOW)
                  || (first.blockType == BlockType.SECOND_ABOVE) || (first.blockType == BlockType.FIRST_ABOVE);

            second = FormulaBlock.createBlocks(binary.getSecond(), formulaSelected);
         break;
         case BYNARY_UNDER:
            blockType = BlockType.SECOND_BELOW;
            binary = (Binary) formula;
            first = FormulaBlock.createBlocks(binary.getFirst(), formulaSelected);
            second = FormulaBlock.createBlocks(binary.getSecond(), formulaSelected);
         break;
         case CONSTANT:
         break;
         case CONSTANT_LETTER:
         break;
         case UNARY:
            blockType = BlockType.FIRST_NORMAL;
            haveParenthesis = true;
            unary = (Unary) formula;
            first = FormulaBlock.createBlocks(unary.getParameter(), formulaSelected);
         break;
         case UNARY_UPPER:
            blockType = BlockType.FIRST_ABOVE;
            unary = (Unary) formula;
            first = FormulaBlock.createBlocks(unary.getParameter(), formulaSelected);
         break;
         case UNDEFINED:
            symbol = "?";
         break;
         case VARIABLE:
         break;
         default:
         break;
      }

      return new FormulaBlock(blockType, haveParenthesis, symbol, first, second, selected);
   }

   /** Block type */
   private final BlockType    blockType;
   /** First/left child */
   private final FormulaBlock first;
   /** Indicates if block surround by parenthesis */
   private boolean            haveParenthesis;
   /** Second/right child */
   private final FormulaBlock second;
   /** Indicates if block is selected */
   private final boolean      selected;
   /** Block symbol */
   private final String       symbol;

   /**
    * Create a new instance of FormulaBlock
    *
    * @param blockType
    *           Block type
    * @param haveParenthesis
    *           Indicates if block surround by parenthesis
    * @param symbol
    *           Block symbol
    * @param first
    *           First/left child
    * @param second
    *           Second/right child
    * @param selected
    *           Indicates if block is selected
    */
   private FormulaBlock(final BlockType blockType, final boolean haveParenthesis, final String symbol, final FormulaBlock first, final FormulaBlock second,
         final boolean selected)
   {
      this.blockType = blockType;
      this.haveParenthesis = haveParenthesis;
      this.symbol = symbol;
      this.first = first;
      this.second = second;
      this.selected = selected;
   }

   /**
    * Compute image with the block and all decent draw on it
    *
    * @param parenthesisSize
    *           Parenthesis size in pixels
    * @param fontFamilly
    *           Font family name for texts
    * @param fontsize
    *           Font size
    * @param fontSizeStep
    *           Step to remove at font size for power child
    * @return Computed image
    */
   private JHelpImage computeImageInternal(final int parenthesisSize, final String fontFamilly, final int fontsize, final int fontSizeStep)
   {
      JHelpImage imageFirst = null;
      JHelpImage imageSecond = null;
      JHelpImage mask = null;

      if(this.first != null)
      {
         if(this.blockType == BlockType.FIRST_ABOVE)
         {
            imageFirst = this.first.computeImageInternal(parenthesisSize, fontFamilly, fontsize - fontSizeStep, fontSizeStep);
         }
         else
         {
            imageFirst = this.first.computeImageInternal(parenthesisSize, fontFamilly, fontsize, fontSizeStep);
         }
      }

      if(this.second != null)
      {
         if(this.blockType == BlockType.SECOND_ABOVE)
         {
            imageSecond = this.second.computeImageInternal(parenthesisSize, fontFamilly, fontsize - fontSizeStep, fontSizeStep);
         }
         else
         {
            imageSecond = this.second.computeImageInternal(parenthesisSize, fontFamilly, fontsize, fontSizeStep);
         }
      }

      if(this.symbol.length() > 0)
      {
         final JHelpFont font = new JHelpFont(fontFamilly, fontsize);
         mask = font.createImage(this.symbol, 0xFF000000, 0xFFFFFFFF);
      }

      int width = 2;
      int height = 2;
      int x = 1;
      int y = 1;
      int xSelection = 0;
      int ySelection = 0;
      int limitXSection = 0;
      int limitYSecletion = 0;
      int xParenthisisOpen = 0;
      int xParenthesisClose = 0;

      if(this.selected == true)
      {
         xSelection = x;
         ySelection = y;
         limitXSection = -x;
         limitYSecletion = -y;
         width += 4;
         height += 4;
         x += 2;
         y += 2;
      }

      if(this.haveParenthesis == true)
      {
         xParenthisisOpen = x;
         xParenthesisClose = -x - parenthesisSize;
         x += parenthesisSize + 1;
         width += (parenthesisSize + 1) << 1;

         if((mask != null) && (this.blockType == BlockType.FIRST_NORMAL))
         {
            xParenthisisOpen += mask.getWidth() + +parenthesisSize + 1;
         }
      }

      int more;

      switch(this.blockType)
      {
         case FIRST_ABOVE:
            width += mask.getWidth() + 1;
            height += mask.getHeight();

            width += imageFirst.getWidth();
            height += imageFirst.getHeight() >> 1;
            y += imageFirst.getHeight() >> 1;
         break;
         case FIRST_NORMAL:
            more = 0;

            if(mask != null)
            {
               width += mask.getWidth() + 1;
               more = mask.getHeight();
            }

            width += imageFirst.getWidth();
            height += Math.max(imageFirst.getHeight(), more);
         break;
         case NO_FIRST_NO_SECOND:
            if(mask != null)
            {
               width += mask.getWidth() + 1;
               height += mask.getHeight();
            }
         break;
         case SECOND_ABOVE:
            width += imageFirst.getWidth() + 1 + imageSecond.getWidth();
            height += imageFirst.getHeight() + (imageSecond.getHeight() >> 1);
            y += imageSecond.getHeight() >> 1;
         break;
         case SECOND_ASIDE:
            width += imageFirst.getWidth() + 1 + mask.getWidth() + 1 + imageSecond.getWidth();
            height += UtilMath.maxIntegers(imageFirst.getHeight(), mask.getHeight(), imageSecond.getHeight());
         break;
         case SECOND_BELOW:
            width += Math.max(imageFirst.getWidth(), imageSecond.getWidth());
            height += imageFirst.getHeight() + 3 + imageSecond.getHeight();
         break;
         default:
         break;
      }

      final JHelpImage image = new JHelpImage(width, height, 0xFFFFFFFF);
      image.startDrawMode();

      if(this.selected == true)
      {
         image.drawRectangle(xSelection, ySelection, width + limitXSection, height + limitYSecletion, 0xFFFF0000);
      }

      if(this.haveParenthesis == true)
      {
         image.drawShape(new Arc2D.Double(xParenthisisOpen, ySelection, parenthesisSize, height + limitYSecletion, 90, 180, Arc2D.OPEN), 0xFF000000);
         image.drawShape(new Arc2D.Double(width + xParenthesisClose, ySelection, parenthesisSize, height + limitYSecletion, 90, -180, Arc2D.OPEN), 0xFF000000);
      }

      switch(this.blockType)
      {
         case FIRST_ABOVE:
            image.drawImage(x, y, mask);
            x += mask.getWidth() + 1;
            y -= imageFirst.getHeight() >> 1;
            image.drawImage(x, y, imageFirst);
         break;
         case FIRST_NORMAL:
            if(mask != null)
            {
               image.drawImage(x - parenthesisSize - 1, y + ((height - mask.getHeight()) >> 1), mask);
               x += mask.getWidth() + parenthesisSize + 1;
            }

            image.drawImage(x, y, imageFirst);
         break;
         case NO_FIRST_NO_SECOND:
            image.drawImage(x, y, mask);
         break;
         case SECOND_ABOVE:
            image.drawImage(x, y, imageFirst);
            x += imageFirst.getWidth() + 1;
            y -= imageSecond.getHeight() >> 1;
            image.drawImage(x, y, imageSecond);
         break;
         case SECOND_ASIDE:
            image.drawImage(x, y + ((height - imageFirst.getHeight()) >> 1), imageFirst);
            x += imageFirst.getWidth() + 1;
            image.drawImage(x, y + ((height - mask.getHeight()) >> 1), mask);
            x += mask.getWidth() + 1;
            image.drawImage(x, y + ((height - imageSecond.getHeight()) >> 1), imageSecond);
         break;
         case SECOND_BELOW:
            image.drawImage(x + ((width - imageFirst.getWidth()) >> 1), y, imageFirst);
            y += imageFirst.getHeight() + 1;
            image.drawHorizontalLine(x, x + width, y, 0xFF000000);
            y += 2;
            image.drawImage(x + ((width - imageSecond.getWidth()) >> 1), y, imageSecond);
         break;
         default:
         break;
      }

      image.endDrawMode();
      return image;
   }

   /**
    * Compute image with the block and all decent draw on it<br>
    * The font size will use normal value and go under on each power on using the given step.<br>
    * To keep text readable, the minimum is respect, and a new normal size is computed if need
    *
    * @param parenthesisSize
    *           Parenthesis size in pixels
    * @param fontFamilly
    *           Font family name for texts
    * @param normalFontSize
    *           Start size for text
    * @param minimumFontSize
    *           Minimum size for text never go under
    * @param fontSizeStep
    *           Step to remove at font size for power child
    * @return Computed image
    */
   public JHelpImage computeImage(final int parenthesisSize, final String fontFamilly, final int normalFontSize, final int minimumFontSize,
         final int fontSizeStep)
   {
      String familly = fontFamilly;

      if(familly == null)
      {
         familly = "Arial";
      }

      final int normal = Math.max(normalFontSize, minimumFontSize);
      final int minimum = Math.min(normalFontSize, minimumFontSize);
      final int step = Math.max(1, fontSizeStep);
      final int number = (normal - minimum) / step;
      final int above = this.numberOfAbove();
      int fontSize = normal;

      if(above > number)
      {
         fontSize = (step * above) + minimum;
      }

      return this.computeImageInternal(parenthesisSize, familly, fontSize, fontSizeStep);

   }

   /**
    * Block type
    *
    * @return Block type
    */
   public BlockType getBlockType()
   {
      return this.blockType;
   }

   /**
    * First/left block
    *
    * @return First/left block
    */
   public FormulaBlock getFirst()
   {
      return this.first;
   }

   /**
    * Second/right block
    *
    * @return Second/right block
    */
   public FormulaBlock getSecond()
   {
      return this.second;
   }

   /**
    * Block symbol
    *
    * @return Block symbol
    */
   public String getSymbol()
   {
      return this.symbol;
   }

   /**
    * Indicates if surround with parenthesis
    *
    * @return {@code true} if surround with parenthesis
    */
   public boolean isHaveParenthesis()
   {
      return this.haveParenthesis;
   }

   /**
    * Indicates if selected
    *
    * @return {@code true} if selected
    */
   public boolean isSelected()
   {
      return this.selected;
   }

   /**
    * Number of above level
    *
    * @return Number of above level
    */
   public int numberOfAbove()
   {
      switch(this.blockType)
      {
         case FIRST_ABOVE:
            return 1 + this.first.numberOfAbove();
         case FIRST_NORMAL:
            return this.first.numberOfAbove();
         case NO_FIRST_NO_SECOND:
            return 0;
         case SECOND_ABOVE:
            return 1 + Math.max(this.first.numberOfAbove(), this.second.numberOfAbove());
         case SECOND_ASIDE:
            return Math.max(this.first.numberOfAbove(), this.second.numberOfAbove());
         case SECOND_BELOW:
            return Math.max(this.first.numberOfAbove(), this.second.numberOfAbove());
         default:
            return 0;
      }
   }
}