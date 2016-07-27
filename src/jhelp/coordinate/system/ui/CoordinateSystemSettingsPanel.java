package jhelp.coordinate.system.ui;

import java.awt.FlowLayout;

import javax.swing.JPanel;

/**
 * Default panel of coordinate system settings edition
 *
 * @author JHelp <br>
 */
public class CoordinateSystemSettingsPanel
      extends JPanel
{
   /**
    * Create a new instance of CoordinateSystemSettingsPanel
    *
    * @param coordinateSystem
    *           Coordinate system to update
    */
   public CoordinateSystemSettingsPanel(final CoordinateSystem coordinateSystem)
   {
      super(new FlowLayout());
      this.add(new OriginComponent(coordinateSystem));
      this.add(new UnitSizeComponent(coordinateSystem));
      this.add(new PointSizeComponent(coordinateSystem));
   }
}