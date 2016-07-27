package jhelp.coordinate.system.model;

/**
 * Represents an interval between 2 values
 *
 * @author JHelp <br>
 */
public class Interval
{
   /** Represents all values */
   public static final Interval R = new Interval(Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
   /** Maximum */
   private final double         maximum;
   /** Minimum */
   private final double         minimum;

   /**
    * Create a new instance of Interval
    *
    * @param minimum
    *           Minimum
    * @param maximum
    *           Maximum
    */
   public Interval(final double minimum, final double maximum)
   {
      this.minimum = Math.min(minimum, maximum);
      this.maximum = Math.max(minimum, maximum);
   }

   /**
    * Maximum
    *
    * @return Maximum
    */
   public double getMaximum()
   {
      return this.maximum;
   }

   /**
    * Minimum
    *
    * @return Minimum
    */
   public double getMinimum()
   {
      return this.minimum;
   }
}