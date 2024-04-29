package xshape.Command;

import java.awt.Color;


import xshape.Canvas;
import xshape.Model.Shape;

public class ColorCommand extends Command {
    Color color;
    Color oldColor;

    public ColorCommand(Canvas canvas, Color color) {
        super(canvas);
        oldColor = canvas.getSelection().getColor();
        selectedShape = canvas.getSelection();
        this.color = color;

    }

    @Override
    void undo() {
        selectedShape.setColor(oldColor);
        System.out.println("new" + color+ ", old :" + oldColor);
        canvas.repaint();
    }

    @Override
    void execute() {
        selectedShape.setColor(color);
        canvas.repaint();
    }
    
}
