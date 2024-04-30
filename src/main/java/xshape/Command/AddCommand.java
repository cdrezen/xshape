package xshape.Command;

import xshape.Canvas;
import xshape.Model.Shape;
import xshape.Model.ShapeGroup;

public class AddCommand extends Command {
    Shape shape;
    ShapeGroup shapeGroup;
    Shape newShape;

    public AddCommand(Canvas canvas, Shape s) {
        super(canvas);
        shape = s;
    }

    @Override
    void undo() {
        canvas.removeShape(shape);
    }

    @Override
    void execute() {
        if (first) {
            first = false;
            canvas.addShape(shape);
            shapeGroup = canvas.getShapes();
            newShape = (shapeGroup.getShapeGroup().get(shapeGroup.getShapeGroup().size() - 1));
            return;
        }
        canvas.simpleAdd(newShape);
    }

}
