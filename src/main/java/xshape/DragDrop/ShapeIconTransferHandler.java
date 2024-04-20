package xshape.DragDrop;

import java.awt.Component;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

import xshape.ShapeIcon;
import xshape.Whiteboard;

public class ShapeIconTransferHandler extends TransferHandler {

    @Override
    public boolean canImport(TransferSupport support) {
        return (support.getComponent() instanceof Whiteboard) && support.isDataFlavorSupported(ShapeIconTransferable.SHAPEICON_FLAVOR);
    }

    @Override //appelée au drop
    public boolean importData(TransferSupport support) 
    {
        if (!canImport(support)) return false;
        
        try
        {
            Object value = support.getTransferable().getTransferData(ShapeIconTransferable.SHAPEICON_FLAVOR);
            if (!(value instanceof ShapeIcon)) return false;

            Component component = support.getComponent();

            if (component instanceof Whiteboard) {
                ((Whiteboard)component).addShape(((ShapeIcon)value).getShape().clone());
                return true;
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public int getSourceActions(JComponent c) {
        return DnDConstants.ACTION_COPY;
    }

    @Override
    protected Transferable createTransferable(JComponent c) {
        Transferable t = null;
        if (c instanceof JButton) 
        {    
            JButton button = (JButton)c;

            try 
            {
                ShapeIcon icon = (ShapeIcon)button.getIcon();
                t = new ShapeIconTransferable(icon);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        return t;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        //appelé après à la fin du drop
    }
}