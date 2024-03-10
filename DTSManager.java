/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1;

import java.awt.Color;
import java.awt.Component;
import java.awt.List;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.TransferHandler;

/**
 *
 * @author lakshhkhatri
 */
public class DTSManager extends TransferHandler {

    DragAndDrop myDragAndDrop;

    public DTSManager(DragAndDrop myDragAndDrop) {
        this.myDragAndDrop = myDragAndDrop;
    }

    // enable copy mode.
    @Override
    public int getSourceActions(JComponent c) {

        return TransferHandler.COPY; // can be changed to move; or copyOrMove
    }

    // to ensure that only string imports are done. 
    @Override
    public boolean canImport(TransferSupport support) {

        Boolean allow = false;
        if (support.isDataFlavorSupported(DataFlavor.stringFlavor)) {
            allow = true;
        }
        return allow;

    }

    /* public boolean validateExport(ArrayList<String> selectedItems) {
    boolean duplicateExists  = false;
    
    for (String item : selectedItems) {
    for (int i = 0; i < myDragAndDrop.modelForSelected.getSize(); i++) {
    if (item.equals(myDragAndDrop.modelForSelected.get(i))) {
    duplicateExists = true;
    }
    }
    }
    
    return !duplicateExists;
    }*/
    // prepare data for exporting to other jlist
    @Override
    protected Transferable createTransferable(JComponent c) {

        JList dragSource = (JList) c; // get jlist
        ArrayList<String> values = (ArrayList) dragSource.getSelectedValuesList(); // retreive a list of all selected items

        // check whether the source was the main list or selected list (re-order within selected list)
        String exportData = ",";
        if (dragSource.toString().equals(myDragAndDrop.returnToList(0))) {
            // main list
            exportData += "main,";
        } else {
            exportData += "selected,";
        }
        // package items into a string for efficient export

        for (String item : values) {
            exportData += item + ",";
        }

        return new StringSelection(exportData);
    }

    // to handle importing
    @Override
    public boolean importData(TransferSupport support) {

        ArrayList<String> exportedList = null;
        try {
            // retreive exportred data
            Transferable exportedData = support.getTransferable();
            String exportedDataString = (String) exportedData.getTransferData(DataFlavor.stringFlavor);
            exportedList = SequenceInterpretor.dividor(exportedDataString, ",");

        } catch (UnsupportedFlavorException ex) {
            Logger.getLogger(DTSManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(DTSManager.class.getName()).log(Level.SEVERE, null, ex);
        }

        //13.2 Get Drop Traget List Box & Drop Location
        JList dropTarget = (JList) support.getComponent(); // jlist
        JList.DropLocation dropLocation = (JList.DropLocation) support.getDropLocation(); // specific location within jlist

        //13.3 Move/Insert? Also get the Index Position
        // though we only use insert, the replace functionality is maintainted in the project for potential future use.
        boolean isReplace = !dropLocation.isInsert();
        int Index = dropLocation.getIndex();
        DefaultListModel TagetDLModel
                = (DefaultListModel) dropTarget.getModel();

        // uncomment if I want to use the replace operations. if not, the default will be to insert.
        /*   if (isReplace) { // replace operation
        for (String item : exportedList) {
        TagetDLModel.set(Index++, item);
        }
        } else { // insert operation
        for (String item : exportedList) {
        TagetDLModel.add(Index++, item);
        
        }
        }
         */
        // to check if the drop location/target is the main list. if its the main list, then we do not import the item.
        if (dropTarget.toString().equals(myDragAndDrop.returnToList(0))) {
            // do not import/insert.
            // instead, we delete from the selected list
            //JList srcList = myDragAndDrop.returnSelectedQList();

        } else {

            if (exportedList.get(0).equals("main")) {  // if the source is the main list, check for duplicate
                for (int i = 1; i < exportedList.size(); i++) {
                    String item = exportedList.get(i);

                    if (isValidItem(item)) {
                        TagetDLModel.add(Index++, item);
                    } else {

                        String infoMessage = item + " already exists in selected questions list. PLease choose another country!";
                        String titleBar = "Error: Duplicate Question";
                        JOptionPane.showMessageDialog(null, infoMessage, "InfoBox: " + titleBar, JOptionPane.INFORMATION_MESSAGE);
                    }

                }

            } else { //  if source is not main list
                for (int i = 1; i < exportedList.size(); i++) {
                    String item = exportedList.get(i);
                    TagetDLModel.add(Index++, item);
                }

            }

        }
        return true;
    }

    public boolean isValidItem(String item) {
        return !myDragAndDrop.modelForSelected.contains(item);

    }

    // to remove elements when moved from selected to main list
    @Override
    protected void exportDone(JComponent c, Transferable data, int action) {

        JList srcList = (JList) c;

        if (srcList.toString().equals(myDragAndDrop.returnToList(1))) {
            int[] indices = srcList.getSelectedIndices();
            DefaultListModel SrcDLModel = (DefaultListModel) srcList.getModel();
            for (int i = indices.length; i > 0; i--) {
                SrcDLModel.removeElementAt(indices[i - 1]);
            }

        }

    }

    public static class CellColorRenderer extends DefaultListCellRenderer {

        DragAndDrop myDragAndDrop;

        CellColorRenderer(DragAndDrop myDragAndDrop) {
            this.myDragAndDrop = myDragAndDrop;
        }

        @Override
        public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
            Component c = super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            DefaultListModel<String> mySelectedModel = myDragAndDrop.modelForSelected;

            // match indices
            ArrayList<Integer> matchIndices = new ArrayList();

            // find the index in the mainList of all items in the selected list
            for (int i = 0; i < mySelectedModel.getSize(); i++) {
                matchIndices.add(myDragAndDrop.modelForMain.indexOf(mySelectedModel.get(i)));
            }

            if (!isSelected) {
                if (matchIndices.contains(index)) {
                    c.setBackground(Color.GREEN);
                } else {
                    c.setBackground(Color.WHITE);
                    list.repaint();
                }
            }

            return c;
        }
    }

}
