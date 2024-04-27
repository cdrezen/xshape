package xshape.Command;

import java.awt.Shape;

import xshape.EditPanel;
import xshape.MenuBar;
import xshape.Whiteboard;

public class Move extends Command {

    public Move(Whiteboard canvas, EditPanel panel, MenuBar menu) {
        super(canvas, panel, menu);
    }

    @Override
    void undo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'undo'");
    }

    @Override
    void redo() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'redo'");
    }

    @Override
    void execute() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'execute'");
    }
    
}
