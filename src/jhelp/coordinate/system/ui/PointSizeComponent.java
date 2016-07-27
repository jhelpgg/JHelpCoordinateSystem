package jhelp.coordinate.system.ui;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import jhelp.coordinate.system.resources.CoordinateSystemResources;
import jhelp.util.gui.UtilGUI;

/**
 * Component for change the size of a point in {@link CoordinateSystem}
 *
 * @author JHelp <br>
 */
public class PointSizeComponent
      extends JSpinner
      implements ChangeListener
{
   /** Coordinate system to update */
   private final CoordinateSystem coordinateSystem;

   /**
    * Create a new instance of PointSizeComponent
    *
    * @param coordinateSystem
    *           Coordinate system to update
    */
   public PointSizeComponent(final CoordinateSystem coordinateSystem)
   {
      super(new SpinnerNumberModel(coordinateSystem.getPointSize(), 1, Math.max(16, coordinateSystem.getPointSize()), 1));
      this.coordinateSystem = coordinateSystem;
      this.addChangeListener(this);
      UtilGUI.addTitle(this, CoordinateSystemResources.RESOURCE_TEXT.getText(CoordinateSystemResources.KEY_TEXT_POINT_SIZE_COMPONENT_TITLE));
   }

   /**
    * Called when value changed by the user <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param changeEvent
    *           Event description
    * @see javax.swing.event.ChangeListener#stateChanged(javax.swing.event.ChangeEvent)
    */
   @Override
   public void stateChanged(final ChangeEvent changeEvent)
   {
      this.coordinateSystem.setPointSize((Integer) this.getValue());
   }
}