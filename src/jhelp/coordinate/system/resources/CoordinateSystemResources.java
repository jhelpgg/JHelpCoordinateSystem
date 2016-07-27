package jhelp.coordinate.system.resources;

import jhelp.util.resources.ResourceText;
import jhelp.util.resources.Resources;

/**
 * Access to resources embed
 *
 * @author JHelp <br>
 */
public class CoordinateSystemResources
{
   /** Title of the tool example key text */
   public static final String       KEY_TEXT_COORDINATE_SYSTEM_FRAME_TITLE = "CoordinateSystemFrame.title";
   /** Title of origin settings */
   public static final String       KEY_TEXT_ORIGIN_COMPONENT_TITLE        = "OriginComponent.title";
   /** Title of point size settings */
   public static final String       KEY_TEXT_POINT_SIZE_COMPONENT_TITLE    = "PointSizeComponent.title";
   /** Title of unit size settings */
   public static final String       KEY_TEXT_UNIT_SIZE_COMPONENT_TITLE     = "UnitSizeComponent.title";
   /** Access to resources text */
   public static final ResourceText RESOURCE_TEXT;
   /** Access to resources */
   public static final Resources    RESOURCES;

   static
   {
      RESOURCES = new Resources(CoordinateSystemResources.class);
      RESOURCE_TEXT = CoordinateSystemResources.RESOURCES.obtainResourceText("texts");
   }
}