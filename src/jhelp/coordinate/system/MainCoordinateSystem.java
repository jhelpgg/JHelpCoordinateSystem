package jhelp.coordinate.system;

import jhelp.coordinate.system.ui.CoordinateSystemFrame;
import jhelp.util.gui.UtilGUI;

/**
 * Launch a stand alone example for test, give an how to use components for developers and a tool for see equation
 *
 * @author JHelp <br>
 */
public class MainCoordinateSystem
{
   /**
    * Launch a stand alone example for test, give an how to use components for developers and a tool for see equation
    *
    * @param args
    *           Unused
    */
   public static void main(final String[] args)
   {
      // Put future frame in actual system look an feel
      UtilGUI.initializeGUI();

      // Create and launch the frame where lies the tool
      final CoordinateSystemFrame coordinateSystemFrame = new CoordinateSystemFrame();
      coordinateSystemFrame.setVisible(true);
   }
}