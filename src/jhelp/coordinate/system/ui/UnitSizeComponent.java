package jhelp.coordinate.system.ui;

import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import jhelp.coordinate.system.resources.CoordinateSystemResources;
import jhelp.util.gui.UtilGUI;

/**
 * Component for change unit size
 *
 * @author JHelp <br>
 */
public class UnitSizeComponent
      extends JSpinner
      implements ChangeListener
{
   /** Coordinate system to update */
   private final CoordinateSystem coordinateSystem;

   /**
    * Create a new instance of UnitSizeComponent
    *
    * @param coordinateSystem
    *           Coordinate system to update
    */
   public UnitSizeComponent(final CoordinateSystem coordinateSystem)
   {
      super(new SpinnerNumberModel(coordinateSystem.getUnitSize(), 1, Math.max(1024, coordinateSystem.getUnitSize() << 1), 1));
      this.coordinateSystem = coordinateSystem;
      this.addChangeListener(this);
      UtilGUI.addTitle(this, CoordinateSystemResources.RESOURCE_TEXT.getText(CoordinateSystemResources.KEY_TEXT_UNIT_SIZE_COMPONENT_TITLE));
   }

   /**
    * Called when user change the value <br>
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
      this.coordinateSystem.setUnitSize((Integer) this.getValue());
   }
}