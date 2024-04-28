package xshape;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Point;
import java.util.Dictionary;
import java.util.Hashtable;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerModel;
import javax.swing.SpinnerNumberModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.plaf.basic.BasicSliderUI.ScrollListener;

import xshape.Model.Shape;

public class EditPanel extends JPanel
{
    private JSlider posXSlider;
    private JSlider posYSlider;

    private JSpinner widthSpinner;
    private JSpinner heightSpinner;

    private JSlider rotationSlider;
    private JSlider scaleSlider;

    Dimension lastsz = null;
    double lastscale = -1;

    public EditPanel(Shape shape, Whiteboard canvas)
    {
        Dimension maxSize = canvas.getSize();
        this.setLayout(new GridLayout(6, 0));
        
        lastsz = shape.size();//dbg

        ChangeListener sliderListener = new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                shape.setPos(posXSlider.getValue(), posYSlider.getValue());
                //shape.setSize(widthSlider.getValue(), heightSlider.getValue());
                shape.setDegrees(rotationSlider.getValue());
                canvas.repaint();
            }
        };

        ChangeListener szListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) 
            {
                Dimension sz = new Dimension((int)widthSpinner.getValue(), (int)heightSpinner.getValue());//new Dimension(widthSlider.getValue(), heightSlider.getValue());

                //if(lastsz != null) shape.setSize(lastsz.width, lastsz.height);
                shape.setSize(sz.width, sz.height);

                lastsz = sz;
                canvas.repaint();
            }
        };

        final int SCALE_SLIDER_MAX = 20;
        final int SCALE_MID = SCALE_SLIDER_MAX/2;

        ChangeListener scaleListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) 
            {
                int scaleVal = scaleSlider.getValue();
                double scale = (double)scaleVal / SCALE_MID;

                if(scale < 0.1 || 1.0/scale < 0.1) return;

                System.out.println("revert:" + (double)(1.0/lastscale)/SCALE_MID);

                if(lastscale != -1) shape.scale(1/lastscale);
                shape.scale(scale);

                System.out.println(scale);

                lastscale = scale;
                canvas.repaint();
            }
        };


        posXSlider = buildSlider(0, maxSize.width, shape.position().x, sliderListener);
        posYSlider = buildSlider(0, maxSize.height, shape.position().y, sliderListener);

        widthSpinner = new JSpinner(new SpinnerNumberModel(shape.size().width, 0, maxSize.width, 1));
        heightSpinner = new JSpinner(new SpinnerNumberModel(shape.size().height, 0, maxSize.height, 1));
        widthSpinner.addChangeListener(szListener);
        heightSpinner.addChangeListener(szListener);

        rotationSlider = buildSlider(0, 360, 0, sliderListener);

        scaleSlider = buildSlider(1, SCALE_SLIDER_MAX - 1, SCALE_MID, scaleListener);
        scaleSlider.setMajorTickSpacing(1);
        Hashtable<Integer, JLabel> ssLabelTable = new Hashtable<Integer,JLabel>();
        ssLabelTable.put(1, new JLabel(String.valueOf(1/(double)SCALE_MID)));
        ssLabelTable.put(SCALE_MID, new JLabel("1"));
        ssLabelTable.put(SCALE_SLIDER_MAX-1, new JLabel(String.valueOf((double)(SCALE_SLIDER_MAX-1)/SCALE_MID)));
        scaleSlider.setLabelTable(ssLabelTable);

        this.add(new Label("X value:"));
        this.add(posXSlider);
        this.add(new Label("Y value:"));
        this.add(posYSlider);
        this.add(new Label("Width:"));
        //this.add(widthSlider);
        this.add(widthSpinner);
        this.add(new Label("Height:"));
        //this.add(heightSlider);
        this.add(heightSpinner);
        this.add(new Label("Scale:"));
        this.add(scaleSlider);
        this.add(new Label("Rotation:"));
        this.add(rotationSlider);
    }

    private JSlider buildSlider(int min, int max, int value, ChangeListener listener)
    {
        JSlider slider = new JSlider(JSlider.HORIZONTAL, min, max, value);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(100);
        if(listener != null) slider.addChangeListener(listener);
        return slider;
    }

    public int[] result()//tmp deg, faire un objet dedié
    {
        return new int[]{posXSlider.getValue(), posYSlider.getValue(), (int)widthSpinner.getValue(), (int)heightSpinner.getValue()};
    }
}
