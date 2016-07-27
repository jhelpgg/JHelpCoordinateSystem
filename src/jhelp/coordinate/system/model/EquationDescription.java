package jhelp.coordinate.system.model;

/**
 * Describe an equation
 *
 * @author JHelp <br>
 */
public abstract class EquationDescription
{
   /** Equation color */
   private int                color;
   /** Equation type */
   private final EquationType equationType;
   /** Definition range */
   private final Interval     interval;
   /** Equation name */
   private final String       name;

   /**
    * Create a new instance of EquationDescription
    *
    * @param equationType
    *           Equation type
    * @param name
    *           Equation name
    * @param color
    *           Equation color
    * @param interval
    *           Definition range
    */
   public EquationDescription(final EquationType equationType, final String name, final int color, final Interval interval)
   {
      if(equationType == null)
      {
         throw new NullPointerException("equationType musn't be null");
      }

      if(name == null)
      {
         throw new NullPointerException("name musn't be null");
      }

      if(interval == null)
      {
         throw new NullPointerException("interval musn't be null");
      }

      this.equationType = equationType;
      this.name = name;
      this.color = color;
      this.interval = interval;
   }

   /**
    * Equation type
    *
    * @return Equation type
    */
   public EquationType geEquationType()
   {
      return this.equationType;
   }

   /**
    * Equation color
    *
    * @return Equation color
    */
   public int getColor()
   {
      return this.color;
   }

   /**
    * Definition range
    *
    * @return Definition range
    */
   public Interval getInterval()
   {
      return this.interval;
   }

   /**
    * Equation name
    *
    * @return Equation name
    */
   public String getName()
   {
      return this.name;
   }

   /**
    * cange the color
    *
    * @param color
    *           New color
    */
   public void setColor(final int color)
   {
      this.color = color;
   }
}