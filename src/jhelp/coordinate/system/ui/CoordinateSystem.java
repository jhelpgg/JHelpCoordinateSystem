package jhelp.coordinate.system.ui;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JComponent;

import jhelp.coordinate.system.model.EquationDescription;
import jhelp.coordinate.system.model.EquationParametric;
import jhelp.coordinate.system.model.EquationYfunctionOfX;
import jhelp.coordinate.system.model.Interval;
import jhelp.gui.LabelJHelpImage;
import jhelp.util.debug.Debug;
import jhelp.util.debug.DebugLevel;
import jhelp.util.gui.JHelpImage;
import jhelp.util.math.UtilMath;
import jhelp.util.thread.ThreadManager;
import jhelp.util.thread.ThreadedSimpleTask;
import jhelp.util.thread.ThreadedVerySimpleTask;

/**
 * The coordinate system it self.<br>
 * It draw the coordinate system and equations on it
 *
 * @author JHelp <br>
 */
public class CoordinateSystem
{
   /**
    * Event manager called when component resize
    *
    * @author JHelp <br>
    */
   class EventManager
         implements ComponentListener
   {
      /**
       * Create a new instance of EventManager
       */
      EventManager()
      {
      }

      /**
       * Called when component is hidden <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       *
       * @param componentEvent
       *           Event description
       * @see java.awt.event.ComponentListener#componentHidden(java.awt.event.ComponentEvent)
       */
      @Override
      public void componentHidden(final ComponentEvent componentEvent)
      {
         // Nothing to do
      }

      /**
       * Called when component is moved <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       *
       * @param componentEvent
       *           Event description
       * @see java.awt.event.ComponentListener#componentMoved(java.awt.event.ComponentEvent)
       */
      @Override
      public void componentMoved(final ComponentEvent componentEvent)
      {
         // Nothing to do
      }

      /**
       * Called when component is resized <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       *
       * @param componentEvent
       *           Event description
       * @see java.awt.event.ComponentListener#componentResized(java.awt.event.ComponentEvent)
       */
      @Override
      public void componentResized(final ComponentEvent componentEvent)
      {
         final Component component = componentEvent.getComponent();
         CoordinateSystem.this.resize(component.getWidth(), component.getHeight());
      }

      /**
       * Called when component is shown <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       *
       * @param componentEvent
       *           Event description
       * @see java.awt.event.ComponentListener#componentShown(java.awt.event.ComponentEvent)
       */
      @Override
      public void componentShown(final ComponentEvent componentEvent)
      {
         // Nothing to do
      }
   }

   /**
    * Task for draw an equation
    *
    * @author JHelp <br>
    */
   class TaskDrawEquation
         extends ThreadedSimpleTask<EquationDescription>
   {
      /**
       * Create a new instance of TaskDrawEquation
       */
      TaskDrawEquation()
      {
      }

      /**
       * Draw an equation <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       *
       * @param equationDescription
       *           Equation to draw
       * @see jhelp.util.thread.ThreadedSimpleTask#doSimpleAction(java.lang.Object)
       */
      @Override
      protected void doSimpleAction(final EquationDescription equationDescription)
      {
         CoordinateSystem.this.doDrawEquation(equationDescription);
      }
   }

   /**
    * Task that update the coordinate system image
    *
    * @author JHelp <br>
    */
   class TaskUpdateImage
         extends ThreadedVerySimpleTask
   {
      /**
       * Create a new instance of TaskUpdateImage
       */
      TaskUpdateImage()
      {
      }

      /**
       * Update the coordinate system image <br>
       * <br>
       * <b>Parent documentation:</b><br>
       * {@inheritDoc}
       *
       * @see jhelp.util.thread.ThreadedVerySimpleTask#doVerySimpleAction()
       */
      @Override
      protected void doVerySimpleAction()
      {
         CoordinateSystem.this.doUpdateImage();
      }
   }

   /** Embed component minimum size */
   private static final int                MINIUM_SIZE = 128;

   /** Equations draw on coordinate system */
   private final List<EquationDescription> equations;
   /** Manager of events */
   private final EventManager              eventManager;
   /** Current image height */
   private int                             height;
   /** Image where coordinate system draw */
   private JHelpImage                      image;
   /** Embed component to use in Swing interface */
   private final LabelJHelpImage           labelJHelpImage;
   /** Position of origin X */
   private int                             originX;
   /** Position of origin Y */
   private int                             originY;
   /** Size of one point */
   private int                             pointSize;
   /** Task for draw one equation */
   private final TaskDrawEquation          taskDrawEquation;
   /** Task for update all image */
   private final TaskUpdateImage           taskUpdateImage;
   /** Size, in pixels, of one unit */
   private int                             unitSize;
   /** Coordinate system image width */
   private int                             width;

   /**
    * Create a new instance of CoordinateSystem
    *
    * @param width
    *           Initial component width
    * @param height
    *           Initial component height
    * @param unitSize
    *           Number of pixel for one unit
    * @param pointSize
    *           Point size
    */
   public CoordinateSystem(final int width, final int height, final int unitSize, final int pointSize)
   {
      this.equations = new ArrayList<EquationDescription>();
      this.eventManager = new EventManager();
      this.taskUpdateImage = new TaskUpdateImage();
      this.taskDrawEquation = new TaskDrawEquation();
      this.width = Math.max(CoordinateSystem.MINIUM_SIZE, width);
      this.height = Math.max(CoordinateSystem.MINIUM_SIZE, height);
      this.originX = this.width >> 1;
      this.originY = this.height >> 1;
      this.image = new JHelpImage(this.width, this.height);
      this.unitSize = Math.max(1, unitSize);
      this.pointSize = Math.max(1, pointSize);
      this.labelJHelpImage = new LabelJHelpImage(this.image);
      this.labelJHelpImage.addComponentListener(this.eventManager);
      this.updateImage();
   }

   /**
    * Draw coordinate system it self
    */
   private void drawCoordinates()
   {
      this.drawLine(0, this.originY, this.width, this.originY, this.pointSize, 0xFF000000);
      this.drawLine(this.originX, 0, this.originX, this.height, this.pointSize, 0xFF000000);
      int before0 = (this.originX / this.unitSize) + 1;
      int min = this.originX - (before0 * this.unitSize);
      int after0 = ((this.width - this.originX) / this.unitSize) + 1;
      int count = before0 + after0;

      for(int i = 0, x = min; i < count; i++, x += this.unitSize)
      {
         this.image.drawLine(x, 0, x, this.height, 0x88888888);
         this.drawLine(x, this.originY - this.pointSize, x, this.originY + this.pointSize, this.pointSize, 0xFF0000FF);
      }

      before0 = (this.originY / this.unitSize) + 1;
      min = this.originY - (before0 * this.unitSize);
      after0 = ((this.height - this.originY) / this.unitSize) + 1;
      count = before0 + after0;

      for(int i = 0, y = min; i < count; i++, y += this.unitSize)
      {
         this.image.drawLine(0, y, this.width, y, 0x88888888);
         this.drawLine(this.originX - this.pointSize, y, this.originX + this.pointSize, y, this.pointSize, 0xFF0000FF);
      }
   }

   /**
    * Draw one equation
    *
    * @param equationDescription
    *           Equation to draw
    */
   private void drawEquation(final EquationDescription equationDescription)
   {
      switch(equationDescription.geEquationType())
      {
         case Y_IS_FUNCTION_OF_X:
            this.drawFunctionOfX((EquationYfunctionOfX) equationDescription);
         break;
         case PAREMETRIC_EQUATION:
            this.drawEquationParametric((EquationParametric) equationDescription);
         break;
         default:
            Debug.println(DebugLevel.WARNING, "Equation type ", equationDescription.geEquationType(), " not draw for now !");
      }
   }

   /**
    * Draw a parametric equation
    *
    * @param equationParametric
    *           Equation to draw
    */
   private void drawEquationParametric(final EquationParametric equationParametric)
   {
      final Interval interval = equationParametric.getInterval();
      final double min = interval.getMinimum();
      final double max = interval.getMaximum();
      final double step = (max - min) / 1000.0;
      final int color = equationParametric.getColor();
      int x1, x2, y1, y2;
      x1 = this.convertSystemToScreenX(equationParametric.functionX(min));
      y1 = this.convertSystemToScreenY(equationParametric.functionY(min));

      for(double t = min; t <= max; t += step)
      {
         x2 = this.convertSystemToScreenX(equationParametric.functionX(t));
         y2 = this.convertSystemToScreenY(equationParametric.functionY(t));

         if((x1 != Integer.MAX_VALUE) && (x2 != Integer.MAX_VALUE) && (y1 != Integer.MAX_VALUE) && (y2 != Integer.MAX_VALUE))
         {
            this.drawLine(x1, y1, x2, y2, this.pointSize, color);
         }

         x1 = x2;
         y1 = y2;
      }
   }

   /**
    * Draw equation of type : y=f(x)
    *
    * @param equationYfunctionOfX
    *           Equation to draw
    */
   private void drawFunctionOfX(final EquationYfunctionOfX equationYfunctionOfX)
   {
      final Interval interval = equationYfunctionOfX.getInterval();
      final double minX = Math.max(this.convertScreenToSystemX(0), interval.getMinimum());
      final double maxX = Math.min(this.convertScreenToSystemX(this.width), interval.getMaximum());
      final double step = 1.0 / this.unitSize;
      final int color = equationYfunctionOfX.getColor();
      int x1, x2, y1, y2;
      x1 = this.convertSystemToScreenX(minX);
      y1 = this.convertSystemToScreenY(equationYfunctionOfX.function(minX));

      for(double x = minX; x <= maxX; x += step)
      {
         x2 = this.convertSystemToScreenX(x);
         y2 = this.convertSystemToScreenY(equationYfunctionOfX.function(x));

         if((y1 != Integer.MAX_VALUE) && (y2 != Integer.MAX_VALUE))
         {
            this.drawLine(x1, y1, x2, y2, this.pointSize, color);
         }

         x1 = x2;
         y1 = y2;
      }
   }

   /**
    * Draw a segment for first point to second
    *
    * @param x1
    *           First point X
    * @param y1
    *           First point Y
    * @param x2
    *           Second point X
    * @param y2
    *           Second point Y
    * @param thick
    *           Segment thickness
    * @param color
    *           Segment color
    */
   private void drawLine(final int x1, final int y1, final int x2, final int y2, final int thick, final int color)
   {
      if(thick < 2)
      {
         this.image.drawLine(x1, y1, x2, y2, color);
      }
      else
      {
         this.image.drawThickLine(x1, y1, x2, y2, thick, color);
      }
   }

   /**
    * Launch an update of all image
    */
   private void updateImage()
   {
      ThreadManager.THREAD_MANAGER.doThread(this.taskUpdateImage, null);
   }

   /**
    * Draw one equation
    *
    * @param equationDescription
    *           Equation to draw
    */
   void doDrawEquation(final EquationDescription equationDescription)
   {
      synchronized(this.labelJHelpImage)
      {
         this.image.startDrawMode();
         this.drawEquation(equationDescription);
         this.image.endDrawMode();
      }
   }

   /**
    * Update the all image
    */
   void doUpdateImage()
   {
      synchronized(this.labelJHelpImage)
      {
         this.image.startDrawMode();
         this.image.clear(0xFFFFFFFF);
         this.drawCoordinates();

         synchronized(this.equations)
         {
            for(final EquationDescription equationDescription : this.equations)
            {
               this.drawEquation(equationDescription);
            }
         }

         this.image.endDrawMode();
      }
   }

   /**
    * Resize the image
    *
    * @param width
    *           New width
    * @param height
    *           New height
    */
   void resize(int width, int height)
   {
      width = Math.max(CoordinateSystem.MINIUM_SIZE, width);
      height = Math.max(CoordinateSystem.MINIUM_SIZE, height);

      if((this.width == width) && (this.height == height))
      {
         return;
      }

      synchronized(this.labelJHelpImage)
      {
         this.originX = (this.originX * width) / this.width;
         this.originY = (this.originY * height) / this.height;
         this.width = width;
         this.height = height;
         this.image = new JHelpImage(this.width, this.height);
         this.labelJHelpImage.setJHelpImage(this.image);
      }

      this.updateImage();
   }

   /**
    * Add an equation to draw
    *
    * @param equationDescription
    *           Equation to add
    */
   public void addEquation(final EquationDescription equationDescription)
   {
      if(equationDescription == null)
      {
         throw new NullPointerException("equationDescription musn't be null");
      }

      synchronized(this.equations)
      {
         this.equations.add(equationDescription);
      }

      ThreadManager.THREAD_MANAGER.doThread(this.taskDrawEquation, equationDescription);
   }

   /**
    * Convert screen coordinate X to system coordinate X.<br>
    * Note : if {@link Integer#MAX_VALUE} or {@link Integer#MIN_VALUE} given it returns {@link Double#NaN}
    *
    * @param x
    *           Screen coordinate X
    * @return Converted system coordinate X
    */
   public double convertScreenToSystemX(final int x)
   {
      if((x == Integer.MAX_VALUE) || (x == Integer.MIN_VALUE))
      {
         return Double.NaN;
      }

      return (x - this.originX) / (double) this.unitSize;
   }

   /**
    * Convert screen coordinate Y to system coordinate Y.<br>
    * Note : if {@link Integer#MAX_VALUE} or {@link Integer#MIN_VALUE} given it returns {@link Double#NaN}
    *
    * @param y
    *           Screen coordinate Y
    * @return Converted system coordinate Y
    */
   public double convertScreenToSystemY(final int y)
   {
      if((y == Integer.MAX_VALUE) || (y == Integer.MIN_VALUE))
      {
         return Double.NaN;
      }

      return (this.originY - y) / (double) this.unitSize;
   }

   /**
    * Convert system coordinate X to screen coordinate X.<br>
    * Note : if {@link Double#NEGATIVE_INFINITY}, {@link Double#NaN} or {@link Double#POSITIVE_INFINITY} given it returns
    * {@link Integer#MAX_VALUE}
    *
    * @param x
    *           System coordinate X
    * @return Converted screen coordinate X
    */
   public int convertSystemToScreenX(final double x)
   {
      if(Double.isFinite(x) == true)
      {
         return (int) ((x * this.unitSize) + this.originX);
      }

      return Integer.MAX_VALUE;
   }

   /**
    * Convert system coordinate Y to screen coordinate Y.<br>
    * Note : if {@link Double#NEGATIVE_INFINITY}, {@link Double#NaN} or {@link Double#POSITIVE_INFINITY} given it returns
    * {@link Integer#MAX_VALUE}
    *
    * @param y
    *           System coordinate Y
    * @return Converted screen coordinate Y
    */
   public int convertSystemToScreenY(final double y)
   {
      if(Double.isFinite(y) == true)
      {
         return (int) (this.originY - (y * this.unitSize));
      }

      return Integer.MAX_VALUE;
   }

   /**
    * Embed component to use in swing interface
    *
    * @return Embed component
    */
   public JComponent getComponent()
   {
      return this.labelJHelpImage;
   }

   /**
    * Origin position
    *
    * @return Origin position
    */
   public Point getOrigin()
   {
      return new Point(this.originX, this.originY);
   }

   /**
    * Point size
    *
    * @return Point size
    */
   public int getPointSize()
   {
      return this.pointSize;
   }

   /**
    * Current image size
    *
    * @return Current image size
    */
   public Dimension getSize()
   {
      return new Dimension(this.width, this.height);
   }

   /**
    * Size of unit in pixels
    *
    * @return Size of unit in pixels
    */
   public int getUnitSize()
   {
      return this.unitSize;
   }

   /**
    * Remove an equation
    *
    * @param equationDescription
    *           Equation to remove
    */
   public void removeEquation(final EquationDescription equationDescription)
   {
      boolean removed;

      synchronized(this.equations)
      {
         removed = this.equations.remove(equationDescription);
      }

      if(removed == true)
      {
         this.updateImage();
      }
   }

   /**
    * Change origin position proportional to image dimension.<br>
    * That is to say 0.5 means to middle, 0.25 the quart, ...
    *
    * @param widthFactor
    *           Factor to apply to the width
    * @param heightFactor
    *           Factor to apply to the height
    */
   public void setOrigin(final double widthFactor, final double heightFactor)
   {
      this.setOrigin((int) (this.width * widthFactor), (int) (this.height * heightFactor));
   }

   /**
    * Change origin position
    *
    * @param x
    *           New origin X
    * @param y
    *           New origin Y
    */
   public void setOrigin(final int x, final int y)
   {
      if((this.originX == x) && (this.originY == y))
      {
         return;
      }

      this.originX = x;
      this.originY = y;
      this.updateImage();
   }

   /**
    * Change point size
    *
    * @param pointSize
    *           New point size
    */
   public void setPointSize(int pointSize)
   {
      pointSize = Math.max(1, pointSize);

      if(this.pointSize == pointSize)
      {
         return;
      }

      this.pointSize = pointSize;
      this.updateImage();
   }

   /**
    * Change unit size
    *
    * @param unitSize
    *           New unit size in pixels
    */
   public void setUnitSize(int unitSize)
   {
      unitSize = Math.max(1, unitSize);

      if(UtilMath.equals(this.unitSize, unitSize) == true)
      {
         return;
      }

      this.unitSize = unitSize;
      this.updateImage();
   }
}