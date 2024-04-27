package xshape;

import java.awt.BorderLayout;
import java.awt.Checkbox;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;
import java.awt.Point;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import xshape.Model.Shape;

public class EditPanel extends JPanel
{
    private JSlider posXSlider;
    private JSlider posYSlider;
    private JSlider widthSlider;
    private JSlider heightSlider;
    private JSlider rotationSlider;
    private JSlider scaleSlider;

    Dimension lastsz = null;
    int lastscale = 4;

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

        ChangeListener wListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) 
            {
                //Dbg
                lastsz = new Dimension(widthSlider.getValue(), heightSlider.getValue());
                //
                shape.setSize(widthSlider.getValue(), shape.size().height);
                canvas.repaint();
            }
        };

        ChangeListener hListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) 
            {
                //Dbg
                lastsz = new Dimension(widthSlider.getValue(), heightSlider.getValue());
                //
                shape.setSize(shape.size().width, heightSlider.getValue());
                canvas.repaint();
            }
        };

        ChangeListener scaleListener = new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) 
            {
                int scale = scaleSlider.getValue();

                System.out.println("revert:" + (double)(1.0/lastscale)/4);

                //shape.scale((double)(1.0/lastscale)/10);
                shape.scale((double)scale/4);

                System.out.println((double)scale/4);

                lastscale = scale;
                canvas.repaint();
            }
        };


        posXSlider = buildSlider(0, maxSize.width, shape.position().x, sliderListener);
        posYSlider = buildSlider(0, maxSize.height, shape.position().y, sliderListener);
        widthSlider = buildSlider(0, maxSize.width, shape.size().width, wListener);
        heightSlider = buildSlider(0, maxSize.height, shape.size().height, hListener);
        rotationSlider = buildSlider(0, 360, 0, sliderListener);
        scaleSlider = buildSlider(1, 8, 4, scaleListener);

        this.add(new Label("X value:"));
        this.add(posXSlider);
        this.add(new Label("Y value:"));
        this.add(posYSlider);
        this.add(new Label("Width:"));
        this.add(widthSlider);
        this.add(new Label("Height:"));
        this.add(heightSlider);
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
        return new int[]{posXSlider.getValue(), posYSlider.getValue(), widthSlider.getValue(), heightSlider.getValue()};
    }
}
