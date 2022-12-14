/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SIG.model;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Aml
 */
public class ShowLineTable extends AbstractTableModel {

    private String[] colums = {"Num", "Item", "Unit Price", "Quantity", "Total"};
    private ArrayList<InvoiceItem> items;

    public ShowLineTable(ArrayList<InvoiceItem> items) {
        this.items = items;
    }

    @Override
    public int getRowCount() {
        return items.size();
    }

    @Override
    public int getColumnCount() {
        return colums.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        InvoiceItem item = items.get(rowIndex);
        switch(columnIndex){
            case 0 -> {return item.getInvoice().getNum();}
                
            case 1 -> {return item.getItemName();}
                
            case 2 -> {return item.getUnitPrice();}
                
            case 3 -> {return item.getQuantity();}
            
            case 4 -> {return item.getTotalLine();}
            
        }
        
        return null;

    }

    @Override
    public String getColumnName(int column) {
        return colums[column];
    }
}
