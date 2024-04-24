package xshape.DragDrop;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import xshape.ShapeIcon;
import xshape.Shape;

public class ShapeTransferable implements Transferable {

    public static final DataFlavor SHAPE_FLAVOR = new DataFlavor(Shape.class, "xshape/Shape");
    private Shape shape;

    public ShapeTransferable(Shape shape) {
        this.shape = shape;
    }

    public ShapeTransferable(ShapeIcon shapeIcon) {
        this.shape = shapeIcon.getShape();
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{SHAPE_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(SHAPE_FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        return shape;
    }
}