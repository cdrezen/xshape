package xshape;

import xshape.Model.ShapeGroup;

public class Editor implements CanvasObserver
{
    // Mediator for the GUI components

    Canvas canvas;
    ShapeToolBar toolBar;
    MenuBar menu;
    EditPanel editPanel;

    public Editor(Canvas canvas, ShapeToolBar toolBar, MenuBar menu){//, EditPanel editPanel) {
        this.canvas = canvas;
        this.toolBar = toolBar;
        this.menu = menu;
        //this.editPanel = editPanel;
        canvas.subscribe(this);
    }

    @Override
    public void onDragShapeStart(Canvas canvas, ShapeGroup dragedShapes) {
        // TODO Auto-generated method stub
        System.out.printf("shape(s) drag start (nb:%d) %s\n", dragedShapes.getChildren().length, dragedShapes.position().toString());
    }

    @Override
    public void onDragShapeEnd(Canvas canvas, ShapeGroup dragedShapes) {
        // TODO Auto-generated method stub
        System.out.printf("shape(s) drag end (nb:%d) %s\n", dragedShapes.getChildren().length, dragedShapes.position().toString());
    }

    @Override
    public void onDoPopMenu(Canvas canvas, ContextMenu menu) {
        // TODO Auto-generated method stub
        System.out.println("pop");
    }




}
