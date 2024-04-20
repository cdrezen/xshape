package xshape;

import java.awt.event.ActionListener;
import java.io.IOException;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.border.Border;

public class MenuBar extends JMenuBar
{
    public MenuBar(ActionListener editListener)
    {
        this.add(createMenuButton("OpenFile.png"));
        this.add(createMenuButton("DocumentOK.png"));
        this.add(createMenuButton("Undo.png"));
        this.add(createMenuButton("Redo.png"));
        this.add(createMenuButton("PropertyPublic.png", editListener));
    }

    private JButton createMenuButton(String filename)
    {
        return createMenuButton(filename, null);
    }

    private JButton createMenuButton(String filename, ActionListener actionListener)
    {
        ImageIcon icon = null;

        try 
        {
            icon = ResourceHelper.getIconResource(filename);
        } 
        catch (IOException e) {
            e.printStackTrace();
        }

        JButton button = new JButton(icon);
        if(actionListener != null) button.addActionListener(actionListener);

        //Border emptyBorder = BorderFactory.createEmptyBorder(4, 4, 4 ,4);
        //.setBorder(emptyBorder);
        //.setBorderPainted(false);

        button.setFocusable(false);

        return button;
    }
}
