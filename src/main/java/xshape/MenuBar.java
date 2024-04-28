package xshape;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.border.Border;

import xshape.Model.Cercle;
import xshape.Model.Polygon;
import xshape.Model.Rectangle;
import xshape.Model.Shape;
import xshape.Save.JsontoShape;
import xshape.Save.SaveToJson;
import xshape.Save.ShapeSave;

public class MenuBar extends JMenuBar {
    Whiteboard canvas;

    public MenuBar(Whiteboard canvas) {
        this.canvas = canvas;
        this.add(createMenuButton("OpenFile.png", open));
        this.add(createMenuButton("DocumentOK.png", save));
        this.add(createMenuButton("Undo.png"));
        this.add(createMenuButton("Redo.png"));
        this.add(createMenuButton("PropertyPublic.png", edit));
        this.add(createMenuButton("Composition.png", group));
        this.add(createMenuButton("Ungroup.png", ungroup));
    }

    private JButton createMenuButton(String filename) {
        return createMenuButton(filename, null);
    }

    private JButton createMenuButton(String filename, ActionListener actionListener) {
        ImageIcon icon = null;

        try {
            icon = ResourceHelper.getIconResource(filename);
        } catch (IOException e) {
            e.printStackTrace();
        }

        JButton button = new JButton(icon);
        if (actionListener != null)
            button.addActionListener(actionListener);

        // Border emptyBorder = BorderFactory.createEmptyBorder(4, 4, 4 ,4);
        // .setBorder(emptyBorder);
        // .setBorderPainted(false);

        button.setFocusable(false);

        return button;
    }

    ActionListener edit = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            Shape selectedShape = canvas.getSelection();
            if (selectedShape != null) {
                EditPanel edit = new EditPanel(selectedShape, canvas);
                int choice = JOptionPane.showConfirmDialog(getParent(),
                        edit,
                        "Edit shape",
                        JOptionPane.OK_CANCEL_OPTION);

                if (choice == JOptionPane.OK_OPTION) {
                    int[] res = edit.result();
                    // edit shape with result
                }

            }
        }
    };

    ActionListener group = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            canvas._shapes.group(canvas._selectedShapes);
            // canvas._selectedShapes.group();
        }
    };

    ActionListener ungroup = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            canvas._shapes.ungroup(canvas._selectedShapes);
            // canvas._selectedShapes.ungroup();
        }
    };

    ActionListener save = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            SaveToJson saveToJson = new SaveToJson();
            saveToJson.save(canvas.getShapeGroup());
        }
    };

    ActionListener open = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            ArrayList<Shape> shapes;
            JsontoShape j = new JsontoShape();
            try {
                shapes = j.getShape("canvas.json");
                canvas.removeAllShape();
                for(Shape s : shapes){
                    canvas.addShapeFromJson(s);
                }
            } catch (Exception ex) {
                System.out.println("Erreur restoration de canavs depuis un json");

            }
            
        }
    };
}
