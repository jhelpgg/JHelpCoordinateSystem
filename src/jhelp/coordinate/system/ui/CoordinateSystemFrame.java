package jhelp.coordinate.system.ui;

import java.awt.BorderLayout;
import java.awt.Color;

import jhelp.coordinate.system.event.FormulaEditorListener;
import jhelp.coordinate.system.model.EquationYfunctionOfX;
import jhelp.coordinate.system.model.formula.Formula;
import jhelp.coordinate.system.resources.CoordinateSystemResources;
import jhelp.coordinate.system.ui.editor.FormulaEditor;
import jhelp.coordinate.system.util.UtilCoordinateSystem;
import jhelp.gui.ButtonChooseColor;
import jhelp.gui.JHelpFrame;

/**
 * Main frame of the stand alone example for test, give an how to use components for developers and a tool for see equation
 *
 * @author JHelp <br>
 */
public class CoordinateSystemFrame
      extends JHelpFrame
      implements FormulaEditorListener
{
   /** Button for choose next equation color */
   private ButtonChooseColor             buttonChooseColor;
   /** Coordinate system that show equations */
   private CoordinateSystem              coordinateSystem;
   /** Settings of coordinate system */
   private CoordinateSystemSettingsPanel coordinateSystemSettingsPanel;
   /** Editor of formula */
   private FormulaEditor                 formulaEditor;

   /**
    * Create a new instance of CoordinateSystemFrame
    */
   public CoordinateSystemFrame()
   {
      super(CoordinateSystemResources.RESOURCE_TEXT.getText(CoordinateSystemResources.KEY_TEXT_COORDINATE_SYSTEM_FRAME_TITLE), true, true);
   }

   /**
    * Add components listeners <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @see jhelp.gui.JHelpFrame#addListeners()
    */
   @Override
   protected void addListeners()
   {
      this.formulaEditor.setFormulaEditorListener(this);
   }

   /**
    * Create components <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @see jhelp.gui.JHelpFrame#createComponents()
    */
   @Override
   protected void createComponents()
   {
      this.coordinateSystem = new CoordinateSystem(512, 512, 64, 4);
      this.coordinateSystemSettingsPanel = new CoordinateSystemSettingsPanel(this.coordinateSystem);
      this.formulaEditor = new FormulaEditor();
      this.buttonChooseColor = new ButtonChooseColor(Color.BLUE);
   }

   /**
    * Layout components in frame <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @see jhelp.gui.JHelpFrame#layoutComponents()
    */
   @Override
   protected void layoutComponents()
   {
      this.setLayout(new BorderLayout());
      this.coordinateSystemSettingsPanel.add(this.buttonChooseColor);
      this.add(this.coordinateSystemSettingsPanel, BorderLayout.NORTH);
      this.add(this.coordinateSystem.getComponent(), BorderLayout.CENTER);
      this.add(this.formulaEditor, BorderLayout.SOUTH);
   }

   /**
    * Called when a formula is send <br>
    * <br>
    * <b>Parent documentation:</b><br>
    * {@inheritDoc}
    *
    * @param formula
    *           Formula send
    * @see jhelp.coordinate.system.event.FormulaEditorListener#formulaTyped(jhelp.coordinate.system.model.formula.Formula)
    */
   @Override
   public void formulaTyped(final Formula formula)
   {
      this.coordinateSystem.addEquation(
            new EquationYfunctionOfX(UtilCoordinateSystem.formulaToFunction(formula), formula.toString(), this.buttonChooseColor.getColor().getRGB()));
   }
}