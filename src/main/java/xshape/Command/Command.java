package xshape.Command;

import xshape.EditPanel;
import xshape.MenuBar;
import xshape.Whiteboard;

public abstract class Command {
    Whiteboard canvas;
    EditPanel panel;
    MenuBar menu;
    
    public Command(Whiteboard canvas) {
        this.canvas = canvas;
    }

    public Command(Whiteboard canvas, EditPanel panel, MenuBar menu) {
        this.canvas = canvas;
        this.panel = panel;
        this.menu = menu;
    }

    abstract void undo();

    abstract void execute();

    public Whiteboard getCanvas() {
        return canvas;
    }

}
