package xshape.Command;

import xshape.EditPanel;
import xshape.MenuBar;
import xshape.Model.Shape;
import xshape.Canvas;

public abstract class Command {
    Canvas canvas;
    EditPanel panel;
    MenuBar menu;
    Shape selectedShape;
    Shape shape;
    Boolean first = true;
    public Command(Canvas canvas) {
        this.canvas = canvas;
    }

    public Command(Canvas canvas, EditPanel panel, MenuBar menu) {
        this.canvas = canvas;
        this.panel = panel;
        this.menu = menu;
    }

    abstract void undo();

    abstract void execute();

    public Canvas getCanvas() {
        return canvas;
    }

}
