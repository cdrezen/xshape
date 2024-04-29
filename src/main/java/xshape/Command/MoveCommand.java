package xshape.Command;

import xshape.EditPanel;
import xshape.MenuBar;
import xshape.Model.Shape;

import java.awt.Graphics;
import java.awt.Point;

import xshape.Canvas;

public class MoveCommand extends Command {
    private Point from;
    private Point to;

    public MoveCommand(Canvas canvas, Point from, Point to) {
        super(canvas);
        this.selectedShape = canvas.getSelection();
        this.from = from;
        this.to = to;
        System.out.printf("init\nto=(%d, %d) from=(%d, %d)\n", to.x, to.y, from.x, from.y);
    }

    @Override
    void undo() {
        System.out.printf("redo\nto=(%d, %d) from=(%d, %d)\n", to.x, to.y, from.x, from.y);
        selectedShape.setPos(from.x, from.y);
        canvas.repaint();
    }

    @Override
    void execute() {
        System.out.printf("execute/redo\nto=(%d, %d) from=(%d, %d)\n", to.x, to.y, from.x, from.y);
        selectedShape.setPos(to.x, to.y);
        canvas.repaint();
    }
}