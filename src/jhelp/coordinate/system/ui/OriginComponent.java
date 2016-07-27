package jhelp.coordinate.system.ui;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Point;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import jhelp.coordinate.system.resources.CoordinateSystemResources;
import jhelp.util.gui.UtilGUI;

/**
 * Component for change origin position
 *
 * @author JHelp <br>
 */
public class OriginComponent
      extends JPanel
      implements ChangeListener
{
   /** Coordinate system to update */
   private final CoordinateSystem coordinateSystem;
   /** Change position X */
   private final JSpinner         spinnerPercentX;
   /** Change position Y */
   private final JSpinner         spinnerPercentY;

   /**
    * Create a new instance of OriginComponent
    *
    * @param coordinateSystem
    *           Coordinate system to update
    */
   public OriginComponent(final CoordinateSystem coordinateSystem)
   {
      super(new FlowLayout());
      this.coordinateSystem = coordinateSystem;
      final Point origin = this.coordinateSystem.getOrigin();
      final Dimension size = this.coordinateSystem.getSize();
      final int percentX = (origin.x * 100) / size.width;
      final int percentY = (origin.y * 100) / size.height;
      this.spinnerPercentX = this.createAndAddSpinner("X", percentX);
      this.spinnerPercentY = this.createAndAddSpinner("Y", percentY);
      UtilGUI.addTitle(this, CoordinateSystemResources.RESOURCE_TEXT.getText(CoordinateSystemResources.KEY_TEXT_ORIGIN_COMPONENT_TITLE));
   }

   /**
    * Create a spinner for a position coordinate and add it to the main panel
    *
    * @param name
    *           Changed coordinate
    * @param percent
    *           Start percent
    * @return Created spinner
    */
   private JSpinner createAndAddSpinner(final String name, final int percent)
   {
      final JSpinner spinner = new JSpinner(new SpinnerNumberModel(percent, 0, 100, 1));
      spinner.addChangeListener(this);
      final JPanel panel = new JPanel(new FlowLayout());
      panel.add(spinner);
      panel.add(new JLabel("%"));
      UtilGUI.addSubTitle(panel, name);
      this.add(panel);
      return spinner;
   }

   /**
    * Called when user change value of one spinner <br>
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
      final int percentX = (Integer) this.spinnerPercentX.getValue();
      final int percentY = (Integer) this.spinnerPercentY.getValue();
      this.coordinateSystem.setOrigin(percentX * 0.01, percentY * 0.01);
   }
}