/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Program;

import BusinessObjects.Customer;
import BusinessObjects.Invoice;
import BusinessObjects.LineItem;
import Commands.Insert;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

/**
 *
 * @author Alex
 */
public class InvoiceEntry extends JFrame {

    JLabel nameLbl, addressLbl, cityLbl, descriptionLbl, provinceLbl, zipLbl, productCodeLbl, invoiceNumLbl,
            customerNumberLbl, productBoughtLbl, quantityLbl, paymentLbl, priceLbl, depositLbl, allProductsLbl;

    JTextField nameTxt, addressTxt, cityTxt, descriptionTxt, provinceTxt, zipTxt, productCodeTxt, invoiceNumTxt,
            customerNumberTxt, quantityTxt, paymentTxt, priceTxt, depositTxt;

    JTextArea productBoughtTxtArea;

    JComboBox<Object> allProductsCB;

    JButton addCustBtn, findProdBtn, listProdBtn, addInvoiceBtn, showInvoiceBtn, exitBtn, writeInvoiceBtn, nextBtn;

    public InvoiceEntry(String title) {
        super(title);

        //set layout
        setLayout(new GridLayout(19, 2));

        //Instantiate Components
        //Labels
        nameLbl = new JLabel("Name");
        addressLbl = new JLabel("Address");
        cityLbl = new JLabel("City");
        descriptionLbl = new JLabel("Description");
        provinceLbl = new JLabel("Province");
        zipLbl = new JLabel("Zip");
        productCodeLbl = new JLabel("Product Code");
        invoiceNumLbl = new JLabel("Invoice Number");
        customerNumberLbl = new JLabel("Customer Number");
        productBoughtLbl = new JLabel("Product Bought");
        quantityLbl = new JLabel("Quantity");
        paymentLbl = new JLabel("Payment");
        priceLbl = new JLabel("Price");
        depositLbl = new JLabel("Deposit");
        allProductsLbl = new JLabel("All Products");
        //TextFields
        nameTxt = new JTextField();
        addressTxt = new JTextField();
        cityTxt = new JTextField();
        descriptionTxt = new JTextField();
        provinceTxt = new JTextField();
        zipTxt = new JTextField();
        productCodeTxt = new JTextField();
        invoiceNumTxt = new JTextField();
        customerNumberTxt = new JTextField();
        quantityTxt = new JTextField();
        paymentTxt = new JTextField();
        priceTxt = new JTextField();
        depositTxt = new JTextField();
        //TextArea
        productBoughtTxtArea = new JTextArea("\"Text Area\" - Invoice Products and quantity displayed here");
        //ComboBox
        allProductsCB = new JComboBox<>();
        //Buttons
        addCustBtn = new JButton("Add Customer");
        findProdBtn = new JButton("Find Product");
        listProdBtn = new JButton("List Product");
        addInvoiceBtn = new JButton("Add Invoice");
        showInvoiceBtn = new JButton("Show Invoice");
        exitBtn = new JButton("Exit");
        writeInvoiceBtn = new JButton("Write Invoice");
        nextBtn = new JButton("Next");

        add(nameLbl);
        add(nameTxt);
        add(addressLbl);
        add(addressTxt);
        add(cityLbl);
        add(cityTxt);
        add(descriptionLbl);
        add(descriptionTxt);
        add(provinceLbl);
        add(provinceTxt);
        add(zipLbl);
        add(zipTxt);
        add(productCodeLbl);
        add(productCodeTxt);
        add(invoiceNumLbl);
        add(invoiceNumTxt);
        add(customerNumberLbl);
        add(customerNumberTxt);
        add(productBoughtLbl);
        add(productBoughtTxtArea);
        add(quantityLbl);
        add(quantityTxt);
        add(paymentLbl);
        add(paymentTxt);
        add(priceLbl);
        add(priceTxt);
        add(depositLbl);
        add(depositTxt);
        add(allProductsLbl);
        add(allProductsCB);

        add(addCustBtn);
        add(findProdBtn);
        add(listProdBtn);
        add(addInvoiceBtn);
        add(showInvoiceBtn);
        add(exitBtn);
        add(writeInvoiceBtn);
        add(nextBtn);

        getContentPane().setBackground(Color.CYAN);

        //AddAction Listeners
        addCustBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                //int number = Integer.parseInt(customerNumberTxt.getText());
                String name = nameTxt.getText();
                String address = addressTxt.getText();
                String city = cityTxt.getText();
                String province = provinceTxt.getText();
                String zip = zipTxt.getText();
                double deposit = Double.parseDouble(depositTxt.getText());

                Insert addCust = new Insert(new Customer(name, address, city, province, zip, deposit));
                try {
                    addCust.Execute();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Error" + ex.getMessage());

                }
            }
        });

        addInvoiceBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                
                //Invoice
                int customerNumber = Integer.parseInt(customerNumberTxt.getText());
                double payment = Double.parseDouble(paymentTxt.getText());                                
                Invoice newInvoice = new Invoice(customerNumber, payment);
                
                //LineItems
                int invoiceNumber = newInvoice.getInvoiceNumber();
                int productCode = Integer.parseInt(productCodeTxt.getText());
                int quantity = Integer.parseInt(quantityTxt.getText());
                        
                LineItem newItems = new LineItem(invoiceNumber, productCode, quantity);
                
                Insert insertInvoice = new Insert(newInvoice, newItems);
                
                try {
                    insertInvoice.Execute();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(rootPane, "Error! " + ex.getMessage());
                }
                        
            }
        });

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 700);
        setVisible(true);

    }
}
