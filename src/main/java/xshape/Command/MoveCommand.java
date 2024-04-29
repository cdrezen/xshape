package xshape.Command;

import xshape.EditPanel;
import xshape.MenuBar;
import xshape.Whiteboard;

public class MoveCommand extends Command {
    private int x, y;

    public MoveCommand(Whiteboard canvas, EditPanel panel, MenuBar menu) {
        super(canvas, panel, menu);
    }

    public MoveCommand(Whiteboard canvas, int x, int y) {
        super(canvas);
        this.x = x;
        this.y = y;
    }

    @Override
    void undo() {

    }

    @Override
    void execute() {
        canvas.getSelection().setCenterToPos(x,y);
    }
    
}
