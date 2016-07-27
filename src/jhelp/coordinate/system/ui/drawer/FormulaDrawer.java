package jhelp.coordinate.system.ui.drawer;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JComponent;

import jhelp.coordinate.system.model.formula.Formula;
import jhelp.coordinate.system.model.formula.Undefined;
import jhelp.util.gui.JHelpImage;

/**
 * Component that draw a formula
 *
 * @author JHelp <br>
 */
public class FormulaDrawer
      extends JComponent
{
   /**
    * Event manager for focus trick
    *
    * @author JHelp <br>
    */
   class EventManager
         implements MouseListener
   {
      /**
       * Create a new instance of EventManager
       */
      EventManager()
      {
      }

      /**
       * Called when mouse clicked <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       *
       * @param e
       *           Event description
       * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseClicked(final MouseEvent e)
      {
         FormulaDrawer.this.requestFocusInWindow();
      }

      /**
       * Called when mouse entered <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       *
       * @param e
       *           Event description
       * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseEntered(final MouseEvent e)
      {
         FormulaDrawer.this.requestFocusInWindow();
      }

      /**
       * Called when mouse exited <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       *
       * @param e
       *           Event description
       * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseExited(final MouseEvent e)
      {
         FormulaDrawer.this.requestFocusInWindow();
      }

      /**
       * Called when mouse pressed <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       *
       * @param e
       *           Event description
       * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
       */
      @Override
      public void mousePressed(final MouseEvent e)
      {
         FormulaDrawer.this.requestFocusInWindow();
      }

      /**
       * Called when mouse released <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       *
       * @param e
       *           Event description
       * @see java.awt.event.MouseListener#mouseClicked(java.awt.event.MouseEvent)
       */
      @Override
      public void mouseReleased(final MouseEvent e)
      {
         FormulaDrawer.this.requestFocusInWindow();
      }
   }

   /** Image where formula draw */
   private JHelpImage   image;
   /** Synchronization lock for avoid issue while drawing */
   private final Object lock;

   /**
    * Create a new instance of FormulaDrawer
    */
   public FormulaDrawer()
   {
      this.lock = new Object();
      this.image = JHelpImage.DUMMY;
      this.drawFormula(Undefined.UNDEFINED(), Undefined.UNDEFINED());
      this.setFocusable(true);
      this.setRequestFocusEnabled(true);
      this.requestFocusInWindow();
      final EventManager eventManager = new EventManager();
      this.addMouseListener(eventManager);
   }

   /**
    * Draw the component <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param graphics
    *           Graphics for draw
    * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
    */
   @Override
   protected void paintComponent(final Graphics graphics)
   {
      synchronized(this.lock)
      {
         if(this.image == null)
         {
            return;
         }

         graphics.drawImage(this.image.getImage(), (this.getWidth() - this.image.getWidth()) >> 1, (this.getHeight() - this.image.getHeight()) >> 1, null);
      }
   }

   /**
    * Change the draw formula
    *
    * @param formula
    *           Formula to draw
    * @param formulaEdited
    *           Edited formula
    */
   public void drawFormula(final Formula formula, final Formula formulaEdited)
   {
      if(formula == null)
      {
         throw new NullPointerException("formula musn't be null");
      }

      Dimension size;

      synchronized(this.lock)
      {
         final FormulaBlock formulaBlock = FormulaBlock.createBlocks(formula, formulaEdited);
         this.image = formulaBlock.computeImage(5, "Arial", 24, 18, 6);
         size = new Dimension(this.image.getWidth(), this.image.getHeight());
      }

      this.setSize(size);
      this.setMinimumSize(size);
      this.setMaximumSize(size);
      this.setPreferredSize(size);
      this.setSize(size);

      this.invalidate();
      this.repaint();
      this.revalidate();
   }
}