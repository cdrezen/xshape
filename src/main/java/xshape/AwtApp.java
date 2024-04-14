package xshape;

import java.awt.*;
import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JComponent;
import javax.swing.JFrame;

class GUIHelper {
    public static void showOnFrame(JComponent component, String frameName) {
        JFrame frame = new JFrame(frameName);
        WindowAdapter wa = new WindowAdapter() {
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        };
        frame.addWindowListener(wa);
        frame.getContentPane().add(component);
        frame.pack();
        frame.setVisible(true);
    }
}

public class AwtApp extends XShape {
    class JCanvas extends JPanel {
        XShape _xshape = null;
        public JCanvas(XShape xs) {
            _xshape = xs;
        }
        public void paint(Graphics g) {
            super.paint(g);
            AwtContext.instance().graphics(g);
            _xshape.draw();
        }
    }
    
    @Override
    protected ShapeFactory createFactory() {
        return new ShapeFactoryAwt();
    }

    @Override
    void run() {
        JCanvas jc = new JCanvas(this);
        jc.setBackground(Color.WHITE);
        jc.setPreferredSize(new Dimension(500, 500));
        GUIHelper.showOnFrame(jc, "XShape Swing/AWT Rendering");        
    }
}

