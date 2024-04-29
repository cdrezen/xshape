package xshape.Command;

import xshape.EditPanel;
import xshape.MenuBar;
import xshape.Canvas;

public abstract class Command {
    Canvas canvas;
    EditPanel panel;
    MenuBar menu;
    
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
