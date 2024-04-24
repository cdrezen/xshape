package xshape.DragDrop;

import java.awt.Component;
import java.awt.datatransfer.Transferable;
import java.awt.dnd.DnDConstants;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.TransferHandler;

import xshape.Shape;
import xshape.ShapeIcon;
import xshape.ShapeToolBar;
import xshape.Whiteboard;

public class ShapeTransferHandler extends TransferHandler {

    @Override
    public boolean canImport(TransferSupport support) {
        return (support.getComponent() instanceof Whiteboard || support.getComponent() instanceof ShapeToolBar) 
                 && support.isDataFlavorSupported(ShapeTransferable.SHAPE_FLAVOR);
    }

    @Override //appelée au drop
    public boolean importData(TransferSupport support) 
    {
        if (!canImport(support)) return false;
        
        try
        {
            Object value = support.getTransferable().getTransferData(ShapeTransferable.SHAPE_FLAVOR);
            if (!(value instanceof Shape)) return false;

            Component component = support.getComponent();

            //drop from JButton to Whiteboard
            if (component instanceof Whiteboard) {
                ((Whiteboard)component).addShape(((Shape)value).clone());
                return true;
            }

            //drop from Whiteboard to ShapeToolBar
            if(component instanceof ShapeToolBar)
            {
                ((ShapeToolBar)component).add(((Shape)value).clone());
                return true;
            }
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public int getSourceActions(JComponent component) {
        return DnDConstants.ACTION_COPY_OR_MOVE;
    }

    @Override
    protected Transferable createTransferable(JComponent component) 
    {
        Transferable transferable = null;

        //drag from JButton
        if (component instanceof JButton) 
        {    
            JButton button = (JButton)component;

            try 
            {
                ShapeIcon icon = (ShapeIcon)button.getIcon();
                transferable = new ShapeTransferable(icon);
            }
            catch (Exception e)
            {
                e.printStackTrace();
            }
        }
        
        //drag from Whiteboard
        if(component instanceof Whiteboard)
        {
            Whiteboard canvas = (Whiteboard)component;
            Shape selectedShape = canvas.getSelection();

            if (selectedShape != null)
            {
                transferable = new ShapeTransferable(selectedShape);
            }
        }
        return transferable;
    }

    @Override
    protected void exportDone(JComponent source, Transferable data, int action) {
        //appelé après à la fin du drop
    }
}