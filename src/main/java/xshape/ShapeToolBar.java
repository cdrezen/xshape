package xshape;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.beans.PropertyChangeListener;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JToolBar;
import javax.swing.TransferHandler;
import javax.swing.UIManager;
import javax.swing.JSlider;

public class ShapeToolBar extends JToolBar 
{
    public ShapeToolBar()
    {
        super();
        this.setOrientation(VERTICAL);

        MouseAdapter mouseDragAdapter = new MouseAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                JButton button = (JButton) e.getSource();
                TransferHandler handle = button.getTransferHandler();
                handle.exportAsDrag(button, e, TransferHandler.COPY);
            }
        };
        //this.setLayout(new GridLayout(0, 1));
        Rectangle shape = new ShapeFactory().createRectangle(4, 4, 50, 50);
        JButton rectBtn = new JButton(new ShapeIcon(shape));//("CheckBox.icon")));
        rectBtn.setTransferHandler(new TransferHandler("icon"));
        rectBtn.addMouseMotionListener(mouseDragAdapter);
        this.add(rectBtn);
        
        this.add(new JButton(UIManager.getIcon("OptionPane.errorIcon")));
        this.add(new JButton(UIManager.getIcon("OptionPane.warningIcon")));
        this.add(new JButton(UIManager.getIcon("FileView.directoryIcon")));
        //this.add(new JButton(UIManager.getIcon("CheckBoxMenuItem.checkIcon")));
        //this.setMaximumSize(new Dimension(rectBtn.getMaximumSize().width, getMaximumSize().height));

        JSlider szSlider = new JSlider(JSlider.HORIZONTAL,0,50,10);
        szSlider.setSize(rectBtn.getSize());
        this.add(szSlider);



        //this.add(a);
    }
}