package xshape.Command;

import javax.lang.model.util.Elements.Origin;

import xshape.Canvas;
import xshape.Model.Shape;

public class DeleteCommand extends Command {

    Shape getChildrenShape[];
    Shape canvasShape;
    Originator originator;
    Memento m1;

    public DeleteCommand(Canvas canvas) {
        super(canvas);
        canvasShape = canvas.getShapes();
        selectedShape = canvas.getSelection();
        getChildrenShape = selectedShape.getChildren();
        // originator = new Originator();
        // originator.setState(selectShape);
        // m1 = originator.createMemento();
    }

    @Override
    void undo() {
        canvas.simpleAdd(selectedShape);
        canvas.repaint();
    }

    @Override
    void execute() {
        if(first){
            first = false;
            canvasShape.remove(getChildrenShape);
            canvas.nullSelect();
            canvas.repaint();
            return;
        }
        canvas.removeShape(selectedShape);
        canvas.repaint();
        
        // canvas._shapes.remove(canvas._selectedShapes.getChildren());
        // canvas._selectedShapes = null;
        // canvas.repaint();
    }
    
}
