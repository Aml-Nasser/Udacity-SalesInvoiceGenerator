package SIG.controller;

import SIG.model.*;
import SIG.view.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 *
 * @author Aml
 */
public class Controller implements ActionListener, ListSelectionListener {

    private InvoiceFrame frame;
    private InvoiceHeader header;
    private InvoiceItem item;
    private String name ;
    private AddInvoiceDialog invDialog;
    private AddLineDialog itemDialog;

    public Controller(InvoiceFrame frame) {
        this.frame = frame;

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {

            case "New Invoice" -> newInvoice();
            case "Delete Invoice" -> deleteInvoice();
            
            case "New Line" -> newLine();
            case "Delete Line" -> deleteLine();
            
            case "createInvoice" -> addInvOk();
            case "cancelInvoice" -> cancelInvoice();
            
            case "createLine" -> createLine();
            case "cancelLine" -> cancelLine();  
                
            case "Open File" -> {
                frame.setInvoices(null);
                FileOperations fileOperations = new FileOperations(frame);
                ArrayList<InvoiceHeader> inv= fileOperations.readFile();
                frame.setInvoices(inv);
                ShowInvTable invoiceTable = new ShowInvTable(inv);
                frame.setHeaderTabel(invoiceTable);
                frame.getTableInvoiceHeader().setModel(invoiceTable);
                frame.getHeaderTabel().fireTableDataChanged();
            }

            case "Save File" -> {
                FileOperations fileOperations1 = new FileOperations(frame);
                fileOperations1.saveFile(frame.getInvoices());
            }

                
        }
    }
    
        ////Invoices////
    private void newInvoice() {
        invDialog = new AddInvoiceDialog(frame);
        invDialog.setVisible(true);

    }
      
    private void deleteInvoice() {
         int row = frame.getTableInvoiceHeader().getSelectedRow();
        if(row!= -1){
            frame.getInvoices().remove(row);
            frame.getHeaderTabel().fireTableDataChanged();
            
        }
    }
      ////Lines////
    private void newLine() {
        itemDialog = new AddLineDialog(frame);
        itemDialog.setVisible(true);
        
    }
    
    private void deleteLine() {
        int invoiceSelected= frame.getTableInvoiceHeader().getSelectedRow();
          int row = frame.getTableInvoiceLines().getSelectedRow();
          
        if((invoiceSelected!=-1) && (row!= -1)){
            InvoiceHeader invoice = frame.getInvoices().get(invoiceSelected);
            invoice.getItems().remove(row);
            frame.getHeaderTabel().fireTableDataChanged();
            ShowLineTable line = new ShowLineTable(invoice.getItems());
            frame.getTableInvoiceLines().setModel(line);
            line.fireTableDataChanged();
    }
    }

        
    public void addInvOk() {
      String date= invDialog.getInvoiceDate().getText();
      String customer = invDialog.getCustomerName().getText();
     
      int num= frame.getTotalInvNum();
      num++;
        InvoiceHeader newInvoice = new InvoiceHeader(num,customer,date);
        frame.getInvoices().add(newInvoice);
        frame.getHeaderTabel().fireTableDataChanged();
        
        //close the dialoge//
        invDialog.setVisible(false);
        invDialog.dispose();
        invDialog=null;
        
    }
        private void cancelInvoice() {
        invDialog.setVisible(false);
        invDialog.dispose();
        invDialog=null;
    }
        
public InvoiceHeader getInvoiceByNum(int num){
    for(InvoiceHeader inv: frame.getInvoices()){
        if(num==inv.getNum()){
            return inv;
        }
    } 
        return null;
}

    @Override
    public void valueChanged(ListSelectionEvent e) {
    }

    private void createLine() {
        
      int invoiceSelected= frame.getTableInvoiceHeader().getSelectedRow();
        if(invoiceSelected!=-1){
            InvoiceHeader invoice = frame.getInvoices().get(invoiceSelected);
            String item= itemDialog.getItemName().getText();
            String unitPrice = itemDialog.getUnitPrice().getText();
            String quantity = itemDialog.getQuantity().getText();
            double itemUnitPrice = Double.parseDouble(unitPrice);
            int itemQuantity = Integer.parseInt(quantity);
            InvoiceItem newLine = new InvoiceItem(item,itemQuantity,itemUnitPrice,invoice);
            invoice.getItems().add(newLine);
            ShowLineTable line = new ShowLineTable(invoice.getItems());
            frame.getHeaderTabel().fireTableDataChanged();
            frame.getTableInvoiceLines().setModel(line);
            line.fireTableDataChanged();

        }
        itemDialog.setVisible(false);
        itemDialog.dispose();
        itemDialog=null;
        
    }

    private void cancelLine() {
        itemDialog.setVisible(false);
        itemDialog.dispose();
        itemDialog=null;
    }
}
