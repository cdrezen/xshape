package xshape.DragDrop;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;

import xshape.ShapeIcon;

public class ShapeIconTransferable implements Transferable {

    public static final DataFlavor SHAPEICON_FLAVOR = new DataFlavor(ShapeIcon.class, "xshape/ShapeIcon");
    private ShapeIcon shapeIcon;

    public ShapeIconTransferable(ShapeIcon shapeIcon) {
        this.shapeIcon = shapeIcon;
    }

    @Override
    public DataFlavor[] getTransferDataFlavors() {
        return new DataFlavor[]{SHAPEICON_FLAVOR};
    }

    @Override
    public boolean isDataFlavorSupported(DataFlavor flavor) {
        return flavor.equals(SHAPEICON_FLAVOR);
    }

    @Override
    public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
        return shapeIcon;
    }
}