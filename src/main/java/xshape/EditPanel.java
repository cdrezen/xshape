package xshape;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Label;

import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class EditPanel extends JPanel
{
    private JSlider posXSlider;
    private JSlider posYSlider;
    private JSlider widthSlider;
    private JSlider heightSlider;

    public EditPanel(Shape shape, Whiteboard canvas)
    {
        Dimension maxSize = canvas.getSize();
        this.setLayout(new GridLayout(4, 0));

        ChangeListener sliderListener = new ChangeListener() {

            @Override
            public void stateChanged(ChangeEvent e) {
                shape.setPos(posXSlider.getValue(), posYSlider.getValue());
                shape.setSize(widthSlider.getValue(), heightSlider.getValue());
                canvas.repaint();
            }
        };

        posXSlider = buildSlider(0, maxSize.width, shape.getPos().x, sliderListener);
        posYSlider = buildSlider(0, maxSize.height, shape.getPos().y, sliderListener);
        widthSlider = buildSlider(0, maxSize.width, shape.getSize().width, sliderListener);
        heightSlider = buildSlider(0, maxSize.height, shape.getSize().height, sliderListener);

        this.add(new Label("X value:"));
        this.add(posXSlider);
        this.add(new Label("Y value:"));
        this.add(posYSlider);
        this.add(new Label("Width:"));
        this.add(widthSlider);
        this.add(new Label("Height:"));
        this.add(heightSlider);
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
