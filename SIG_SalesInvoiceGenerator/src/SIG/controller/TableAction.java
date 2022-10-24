package SIG.controller;

import SIG.model.ShowLineTable;
import SIG.model.InvoiceHeader;
import SIG.model.InvoiceItem;
import SIG.view.InvoiceFrame;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Aml
 */
public class TableAction implements ListSelectionListener{
    private InvoiceFrame frame;

    public TableAction(InvoiceFrame frame) {
        this.frame = frame;
    }
    
    @Override
    public void valueChanged(ListSelectionEvent e) {
        int invoiceIndex = frame.getTableInvoiceHeader().getSelectedRow();
        if(invoiceIndex!= -1){
             InvoiceHeader selectedRow = frame.getInvoices().get(invoiceIndex);
             ArrayList<InvoiceItem> items = selectedRow.getItems();
             frame.getLabelCustomerName().setText(selectedRow.getName());
             frame.getLabelInvoiceNum().setText(""+selectedRow.getNum());
             frame.getLabelInvoiceDate().setText(selectedRow.getDate());
             frame.getLabelTostalCost().setText(""+selectedRow.getTotalInvoice());
             ShowLineTable line = new ShowLineTable(items);
             frame.getTableInvoiceLines().setModel(line);
             line.fireTableDataChanged();
             
        }
    }
    
}
