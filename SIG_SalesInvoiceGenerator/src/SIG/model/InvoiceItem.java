/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SIG.model;

/**
 *
 * @author Aml
 */
public class InvoiceItem {
    private InvoiceHeader invoice;
    private String itemName;
    private double unitPrice;
    private int quantity;

    public InvoiceItem() {
    }

    public InvoiceItem(String itemName, int quantity,double unitPrice,InvoiceHeader header) {
        this.invoice= header;
        this.quantity = quantity;
        this.itemName = itemName;
        this.unitPrice = unitPrice;
    }
     public double getTotalLine(){
        return unitPrice * quantity;
    }



    public InvoiceHeader getInvoice() {
        return invoice;
    }

    public void setInvoice(InvoiceHeader invoice) {
        this.invoice = invoice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    @Override
    public String toString() {
        return "sigItem{" + "itemName=" + itemName + ", unitPrice=" + unitPrice + ", quantity=" + quantity + '}';
    }
    public double getTotal(){
        return unitPrice * quantity;
    }

   public String getItemsFromTabel(){
       return invoice.getNum() + "," + itemName + "," + unitPrice + "," +quantity;
   }
  
}
