/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * MainJFrame.java
 *
 * Created on Aug 28, 2009, 3:24:23 PM
 */
package com.esd.ui;

import com.esd.control.*;
import com.esd.pojo.*;
import com.esd.reports.ReportManager;
import com.esd.reports.ReportManagerImpl;
import com.esd.util.Constants;
import com.esd.util.Log4jUtil;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.print.PrinterJob;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Duminda
 */
public class MainJFrame extends javax.swing.JFrame {
    
    private String username;
    private String password;
    private ItemManager itemManager;
    private ReportManager reportManager;
    private Vector<Items> itemLists;
    private Vector<ItemType> itemTypeLists;
    private List<Items> itemNameLists;
    private AuthenticationManager authenticationManager;
    private StoresManager storesManager;
    private CustomerInvoiceManager customerInvoiceManager;
    private ExpenseManager expenseManager;
    private NewOrdersManager newOrdersManager;
    private String userType;
    private MainJFrame mainJFrame;
    private Vector<Stores> storesList;
    private Vector<CustomerInvoice> invoicesList;
    private Vector invoiceColumns;
    private Vector editInvoiceCoumns;
    private Vector rows;
    private Vector editCustomerInvoiceRow;
    private LoginDialog loginDialog;
    private boolean isItemAdd = true;
    private boolean isInvoiceAdded = true;
    private static double totalAmountValue;
    private double totalCostValue;
    private List<InvoiceItems> editCustomerInvoiceItems;
    private double returnValue = 0.0;
    private boolean isEditCustomerInvoice = false;
    private boolean isDiscountAdded = false;
    private boolean printerEnabled = true;
    private Vector<Login> userList;
    private double invoiceTotalAmount;
    private Vector<InvoiceItems> editNewInvoiceItemsList;
    private boolean isOpenCashDrawyer = true;
    private ReturnExchangeManager returnExchangeManager;
    private int noOfItems = 0;
    private ArrayList<Double> itemWiseDiscountList;
    private CustomerInvoice editCustomerInvoice;
    private CompanyBusinessManager companyBusinessManager;
    //  private  CustomerInvoice customerInvoice;
    private SupplierManager supplietManager;
    private List<Supplier> supplierList;
    private Supplier selectedSupplierObj;
    private SalesRefManager salesRefManager;
    List<Salesref> salesRefList;

    /**
     * Creates new form MainJFrame
     */
   
     public MainJFrame() {
        initComponents();
         Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();
        
        setSize(width, height);
        
     }
    public MainJFrame(String username, String password, String userType, LoginDialog loginDialog) {
        initComponents();
        
        logOffBtn.setVisible(false);
        logOffMI.setVisible(false);
        
        if (userType.equals("Administrator")) {
        } else if (userType.equals("Cashier")) {
            monthBusinessProfitBtn.setVisible(false);
            monthBusinessProfitMI.setVisible(false);
            itemCostButton.setVisible(false);
            itemCostMI.setVisible(false);
            userAccountsMI.setVisible(false);
            cashAmountLb.setVisible(false);
            cashAmountValueLb.setVisible(false);
            reportsJM.setVisible(false);
            businessBtn.setVisible(false);
            
            
            itemCostLabel.setVisible(false);
            itemCostLb.setVisible(false);
        } else {
            monthBusinessProfitBtn.setVisible(false);
            monthBusinessProfitMI.setVisible(false);
            itemCostButton.setVisible(false);
            itemCostMI.setVisible(false);
            newItemMI.setVisible(false);
            newItemBtn.setVisible(false);
            userAccountsMI.setVisible(false);
            editCustomerInvoiceMI.setVisible(false);
            
            updateCashAmountMI.setVisible(false);
            
            editItemMI.setVisible(false);
            
            itemCostLabel.setVisible(false);
            itemCostLb.setVisible(false);
            cashAmountLb.setVisible(false);
            cashAmountValueLb.setVisible(false);
            reportsJM.setVisible(false);
            businessBtn.setVisible(false);
            
        }
        
        totalAmountValue = 0;
        totalCostValue = 0;
        
        SimpleDateFormat todayDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        String todayDate = todayDateFormat.format(new Date());
        todayDateLb.setText(todayDate);
        
        Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
        int width = (int) dimension.getWidth();
        int height = (int) dimension.getHeight();
        
        setSize(width, height);
        
        this.username = username;
        this.userType = userType;
        this.password = password;
        this.mainJFrame = this;
        this.loginDialog = loginDialog;
        
        itemManager = new ItemManagerImpl();
        reportManager = new ReportManagerImpl();
        authenticationManager = new AuthenticationManagerImpl();
        storesManager = new StoresManagerImpl();
        customerInvoiceManager = new CustomerInvoiceManagerImpl();
        expenseManager = new ExpenseManagerImpl();
        newOrdersManager = new NewOrdersManagerImpl();
        returnExchangeManager = new ReturnExchangeImpl();
        companyBusinessManager = new CompanyBusinessManagerImpl();
        supplietManager = new SupplierManagerImpl();
        salesRefManager = new SalesRefManagerImpl();;
        
        welcomeLb.setText(username);
        returnItemValueLb.setVisible(false);
        returnValueLb.setVisible(false);
        editCusInvoiceIdLb.setVisible(false);
        editInvoiceIdValueLb.setVisible(false);
        
        invoiceColumns = new Vector();
        editInvoiceCoumns = new Vector();
        
        
        rows = new Vector();
        
        invoiceColumns.addElement("Item Code");
        invoiceColumns.addElement("Item Name");
        invoiceColumns.addElement("Price Per One");
        invoiceColumns.addElement("Quantity");
        invoiceColumns.addElement("Amount");
        invoiceColumns.addElement("Discount");
        
        editCustomerInvoiceRow = new Vector();
        
        
        editInvoiceCoumns.addElement("Item Code");
        editInvoiceCoumns.addElement("Item Name");
        editInvoiceCoumns.addElement("Price Per One");
        editInvoiceCoumns.addElement("Quantity");
        editInvoiceCoumns.addElement("sales Price");
        editInvoiceCoumns.addElement("Discount");
        
        
        loadStores();
        
        
        loadItemCodesToInvoice();
        
        loadItemCodesToUpdateStores();
        
        String cashierBalance = customerInvoiceManager.getCashierAmount();
        
        
        if (cashierBalance != null) {
            
            cashAmountValueLb.setText(cashierBalance);
            
        } else {
            
            cashAmountValueLb.setText("0.0");
            
        }
        
        
        itemWiseDiscountList = new ArrayList<Double>();
        
        
        invoiceItemCodeCb.requestFocus();
        
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        newItemBtn = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        itemCostButton = new javax.swing.JButton();
        jLabel16 = new javax.swing.JLabel();
        welcomeLb = new javax.swing.JLabel();
        updateStoresBtn = new javax.swing.JButton();
        customerInvoiceBtn = new javax.swing.JButton();
        monthBusinessProfitBtn = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        businessBtn = new javax.swing.JButton();
        jLabel51 = new javax.swing.JLabel();
        todayDateLb = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        cashAmountLb = new javax.swing.JLabel();
        cashAmountValueLb = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel48 = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        logOffBtn = new javax.swing.JButton();
        jLabel50 = new javax.swing.JLabel();
        jDesktopPane1 = new javax.swing.JDesktopPane();
        newItemIF = new javax.swing.JInternalFrame();
        jPanel6 = new javax.swing.JPanel();
        jPanel7 = new javax.swing.JPanel();
        newItemCodeTf = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        newItemNameTf = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        newSalesPriceTf = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        newItemTypeCb = new javax.swing.JComboBox();
        jLabel4 = new javax.swing.JLabel();
        editItemIF = new javax.swing.JInternalFrame();
        jPanel9 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        editItemCodeCb = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        editItemnameCb = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        editSalesPricetf = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        editItemTypeCb = new javax.swing.JComboBox();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        updateStoresIF = new javax.swing.JInternalFrame();
        jPanel11 = new javax.swing.JPanel();
        jPanel12 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        upStoresItemNameTf = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        upStoresQuantityTf = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        upStoresReOrderTf = new javax.swing.JTextField();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        upStoresItemCodeCb = new javax.swing.JComboBox();
        customerInvoiceIF = new javax.swing.JInternalFrame();
        jPanel14 = new javax.swing.JPanel();
        jPanel16 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        invoiceTable = new javax.swing.JTable();
        jPanel20 = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        reAmountTf = new javax.swing.JTextField();
        jLabel21 = new javax.swing.JLabel();
        balanceLb = new javax.swing.JLabel();
        jButton14 = new javax.swing.JButton();
        newInvoiceBtn = new javax.swing.JButton();
        jLabel34 = new javax.swing.JLabel();
        discountCb = new javax.swing.JComboBox();
        discountChkbx = new javax.swing.JCheckBox();
        discountBtn = new javax.swing.JButton();
        discountLb = new javax.swing.JLabel();
        percentRadio = new javax.swing.JRadioButton();
        textAmountRadio = new javax.swing.JRadioButton();
        textDiscountTf = new javax.swing.JTextField();
        itemWiseChkBx = new javax.swing.JCheckBox();
        itemWisePercentRB = new javax.swing.JRadioButton();
        itemWiseTextRB = new javax.swing.JRadioButton();
        itemWiseDiscountTF = new javax.swing.JTextField();
        discountItemWiseCB = new javax.swing.JComboBox();
        businessByLB = new javax.swing.JLabel();
        businessByCB = new javax.swing.JComboBox();
        jPanel21 = new javax.swing.JPanel();
        jButton13 = new javax.swing.JButton();
        jPanel48 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        removeItemCb = new javax.swing.JComboBox();
        itemRemoveBtn = new javax.swing.JButton();
        jPanel51 = new javax.swing.JPanel();
        returnValueLb = new javax.swing.JLabel();
        returnItemValueLb = new javax.swing.JLabel();
        jPanel17 = new javax.swing.JPanel();
        jPanel19 = new javax.swing.JPanel();
        totalAmountLb = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jPanel18 = new javax.swing.JPanel();
        jPanel15 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        invoiceItemCodeCb = new javax.swing.JComboBox();
        jLabel14 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        itemNameLb = new javax.swing.JLabel();
        itemTypeLb = new javax.swing.JLabel();
        salesPriceLb = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        noOfItemsTf = new javax.swing.JTextField();
        itemAddTableBtn = new javax.swing.JButton();
        itemCostLabel = new javax.swing.JLabel();
        itemCostLb = new javax.swing.JLabel();
        barCodeChkbx = new javax.swing.JCheckBox();
        editCusInvoiceIdLb = new javax.swing.JLabel();
        editInvoiceIdValueLb = new javax.swing.JLabel();
        customerInvoiceChkbx = new javax.swing.JCheckBox();
        openCashDrawyerChkbx = new javax.swing.JCheckBox();
        jLabel54 = new javax.swing.JLabel();
        noOfIemsLb = new javax.swing.JLabel();
        jButton16 = new javax.swing.JButton();
        editCustomerInvoiceIF = new javax.swing.JInternalFrame();
        jPanel23 = new javax.swing.JPanel();
        jPanel24 = new javax.swing.JPanel();
        jLabel22 = new javax.swing.JLabel();
        invoiceIdCb = new javax.swing.JComboBox();
        editInvoiceSearchBtn = new javax.swing.JButton();
        jLabel79 = new javax.swing.JLabel();
        loadInvoiceIdCb = new javax.swing.JComboBox();
        loadInvoiceIdBtn = new javax.swing.JButton();
        jPanel25 = new javax.swing.JPanel();
        jLabel23 = new javax.swing.JLabel();
        editDate = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        editEnteredByLb = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        editTotalAmountTf = new javax.swing.JTextField();
        jLabel43 = new javax.swing.JLabel();
        editDiscountTf = new javax.swing.JTextField();
        discountPercentageTf = new javax.swing.JTextField();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        editInvoiceTable = new javax.swing.JTable();
        jPanel33 = new javax.swing.JPanel();
        jPanel41 = new javax.swing.JPanel();
        jPanel42 = new javax.swing.JPanel();
        jLabel45 = new javax.swing.JLabel();
        returnInvoiceItemCodeCb = new javax.swing.JComboBox();
        jLabel49 = new javax.swing.JLabel();
        returnNoOfItemsTf = new javax.swing.JTextField();
        itemAddTableBtn1 = new javax.swing.JButton();
        jPanel47 = new javax.swing.JPanel();
        jPanel49 = new javax.swing.JPanel();
        jButton25 = new javax.swing.JButton();
        jPanel50 = new javax.swing.JPanel();
        returnAmountLb = new javax.swing.JLabel();
        buyNewitemsBtn = new javax.swing.JButton();
        invoiceReprintBtn = new javax.swing.JButton();
        jLabel44 = new javax.swing.JLabel();
        newExpensesIF = new javax.swing.JInternalFrame();
        jPanel27 = new javax.swing.JPanel();
        jPanel28 = new javax.swing.JPanel();
        jLabel24 = new javax.swing.JLabel();
        nexpenseDate = new datechooser.beans.DateChooserCombo();
        jLabel27 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        nexDes = new javax.swing.JTextArea();
        jLabel28 = new javax.swing.JLabel();
        nExAmount = new javax.swing.JTextField();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jLabel57 = new javax.swing.JLabel();
        expenseTypeCb = new javax.swing.JComboBox();
        itemCostIF = new javax.swing.JInternalFrame();
        jPanel29 = new javax.swing.JPanel();
        jPanel30 = new javax.swing.JPanel();
        jLabel29 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        icItemNameTf = new javax.swing.JTextField();
        icSalesPriceTf = new javax.swing.JTextField();
        icItemCodeCb = new javax.swing.JComboBox();
        icCostPriceTf = new javax.swing.JTextField();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jLabel32 = new javax.swing.JLabel();
        ictypeTf = new javax.swing.JTextField();
        newOrdersIF = new javax.swing.JInternalFrame();
        jPanel35 = new javax.swing.JPanel();
        jPanel36 = new javax.swing.JPanel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        noCustomerNameTf = new javax.swing.JTextField();
        noContactNumberTf = new javax.swing.JTextField();
        noDate = new datechooser.beans.DateChooserCombo();
        jButton6 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        noOrderDeailsTa = new javax.swing.JTextArea();
        viewExpenseIF = new javax.swing.JInternalFrame();
        jPanel37 = new javax.swing.JPanel();
        jPanel38 = new javax.swing.JPanel();
        jLabel39 = new javax.swing.JLabel();
        reExpMonthCb = new javax.swing.JComboBox();
        jLabel40 = new javax.swing.JLabel();
        reExpYearCb = new javax.swing.JComboBox();
        jButton12 = new javax.swing.JButton();
        viewMonthlyBusinessIF = new javax.swing.JInternalFrame();
        jPanel39 = new javax.swing.JPanel();
        jPanel40 = new javax.swing.JPanel();
        jLabel41 = new javax.swing.JLabel();
        reMonthlyBusinessMonthCb = new javax.swing.JComboBox();
        jLabel42 = new javax.swing.JLabel();
        reMonthlyBusinessYearCb = new javax.swing.JComboBox();
        jButton15 = new javax.swing.JButton();
        viewMonthlyBusinessForCashierIF = new javax.swing.JInternalFrame();
        jPanel43 = new javax.swing.JPanel();
        jPanel44 = new javax.swing.JPanel();
        jLabel46 = new javax.swing.JLabel();
        reMonthlyBusinessMonthCb1 = new javax.swing.JComboBox();
        jLabel47 = new javax.swing.JLabel();
        reMonthlyBusinessYearCb1 = new javax.swing.JComboBox();
        jButton17 = new javax.swing.JButton();
        dailtItemBusinessIF = new javax.swing.JInternalFrame();
        jPanel13 = new javax.swing.JPanel();
        jLabel53 = new javax.swing.JLabel();
        dailyBusinesItemsDf = new datechooser.beans.DateChooserCombo();
        jButton23 = new javax.swing.JButton();
        updateCashAmount = new javax.swing.JInternalFrame();
        jPanel22 = new javax.swing.JPanel();
        jLabel58 = new javax.swing.JLabel();
        updateCashTf = new javax.swing.JTextField();
        jButton24 = new javax.swing.JButton();
        customerExchangeIF = new javax.swing.JInternalFrame();
        jPanel31 = new javax.swing.JPanel();
        jLabel59 = new javax.swing.JLabel();
        exInvoiceIdTf = new javax.swing.JTextField();
        jLabel60 = new javax.swing.JLabel();
        exchangeItemCodeTf = new javax.swing.JTextField();
        jLabel61 = new javax.swing.JLabel();
        exchangeNoofItemTf = new javax.swing.JTextField();
        jLabel62 = new javax.swing.JLabel();
        exchangeNewItemCodeTf = new javax.swing.JTextField();
        jButton26 = new javax.swing.JButton();
        Cancel = new javax.swing.JButton();
        storesIF = new javax.swing.JInternalFrame();
        jPanel32 = new javax.swing.JPanel();
        jLabel63 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        itemNameStMgtTf = new javax.swing.JTextField();
        jLabel66 = new javax.swing.JLabel();
        purchaseQuantityTf = new javax.swing.JTextField();
        jLabel67 = new javax.swing.JLabel();
        rejectedTf = new javax.swing.JTextField();
        jLabel68 = new javax.swing.JLabel();
        acceptedTf = new javax.swing.JTextField();
        purchaseDateStMgtDc = new datechooser.beans.DateChooserCombo();
        jLabel69 = new javax.swing.JLabel();
        sellTf = new javax.swing.JTextField();
        jButton3 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        itemCodeStMgtCb = new javax.swing.JComboBox();
        jButton29 = new javax.swing.JButton();
        avilableQyantityTf = new javax.swing.JTextField();
        jLabel71 = new javax.swing.JLabel();
        jButton30 = new javax.swing.JButton();
        jLabel80 = new javax.swing.JLabel();
        storesMgtSupplierCb = new javax.swing.JComboBox();
        viewStoresMgtIF = new javax.swing.JInternalFrame();
        jPanel34 = new javax.swing.JPanel();
        jLabel70 = new javax.swing.JLabel();
        viewStoresMgtDf = new datechooser.beans.DateChooserCombo();
        jButton28 = new javax.swing.JButton();
        jLabel72 = new javax.swing.JLabel();
        viewStMgtCb = new javax.swing.JComboBox();
        jLabel73 = new javax.swing.JLabel();
        viewStMgtTf = new javax.swing.JTextField();
        byDateChBx = new javax.swing.JCheckBox();
        byCodeChbx = new javax.swing.JCheckBox();
        newItemTypeIF = new javax.swing.JInternalFrame();
        jPanel45 = new javax.swing.JPanel();
        jLabel74 = new javax.swing.JLabel();
        itemTypeNameTf = new javax.swing.JTextField();
        itemTypeSaveBtn = new javax.swing.JButton();
        itemTypeCancelBtn = new javax.swing.JButton();
        editItemTypeIF = new javax.swing.JInternalFrame();
        jPanel46 = new javax.swing.JPanel();
        jLabel75 = new javax.swing.JLabel();
        editOldTypeNameCb = new javax.swing.JComboBox();
        jLabel76 = new javax.swing.JLabel();
        newTypeNameTF = new javax.swing.JTextField();
        editNewTypeNameTF = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        addSupplierIF = new javax.swing.JInternalFrame();
        jPanel52 = new javax.swing.JPanel();
        addSupplierLB = new javax.swing.JLabel();
        addSuuplierCodeTF = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        addSupplierNameTF = new javax.swing.JTextField();
        addSupplierContactNumberLB = new javax.swing.JLabel();
        addSupplierContactNumberTF = new javax.swing.JTextField();
        addSupplierSaveBtn = new javax.swing.JButton();
        addSupplierCancelBtn = new javax.swing.JButton();
        editDeleteSupplierIF = new javax.swing.JInternalFrame();
        jPanel53 = new javax.swing.JPanel();
        addSupplierLB1 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        editDeleteSupplierNameTF = new javax.swing.JTextField();
        addSupplierContactNumberLB1 = new javax.swing.JLabel();
        editDeleteSupplierContactNumberTF = new javax.swing.JTextField();
        editSupplierBtn = new javax.swing.JButton();
        editDelteSupplierCancelBtn = new javax.swing.JButton();
        deleteSupplierBtn = new javax.swing.JButton();
        editDeleteSupplierCodeCb = new javax.swing.JComboBox();
        itemRejectionReportIF = new javax.swing.JInternalFrame();
        jPanel54 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        itemRejectionReportItemCodeCb = new javax.swing.JComboBox();
        jPanel56 = new javax.swing.JPanel();
        jLabel86 = new javax.swing.JLabel();
        itemRejectionReportMonthCb = new javax.swing.JComboBox();
        jLabel87 = new javax.swing.JLabel();
        itemRejectionReportYearCb = new javax.swing.JComboBox();
        itemRectionReportBtn = new javax.swing.JButton();
        supplierRejectionReportIF = new javax.swing.JInternalFrame();
        jPanel55 = new javax.swing.JPanel();
        jLabel84 = new javax.swing.JLabel();
        supplierRejectionReportSupplierCodeCb = new javax.swing.JComboBox();
        jPanel57 = new javax.swing.JPanel();
        jLabel88 = new javax.swing.JLabel();
        supplierRejectionReportMonthCb = new javax.swing.JComboBox();
        jLabel89 = new javax.swing.JLabel();
        supplierRejectionReportYearCb = new javax.swing.JComboBox();
        supplierRectionReportBtn = new javax.swing.JButton();
        supplierDeliveredReportIF = new javax.swing.JInternalFrame();
        jPanel58 = new javax.swing.JPanel();
        jLabel85 = new javax.swing.JLabel();
        supplierDeliveredReportSupplierCodeCb = new javax.swing.JComboBox();
        jPanel59 = new javax.swing.JPanel();
        jLabel90 = new javax.swing.JLabel();
        supplierDeliveredReportMonthCb = new javax.swing.JComboBox();
        jLabel91 = new javax.swing.JLabel();
        supplierDeliveredReportYearCb = new javax.swing.JComboBox();
        supplierDeliveredReportBtn = new javax.swing.JButton();
        salesRefIF = new javax.swing.JInternalFrame();
        editSalesRefTB = new javax.swing.JTabbedPane();
        jPanel60 = new javax.swing.JPanel();
        jPanel61 = new javax.swing.JPanel();
        jLabel92 = new javax.swing.JLabel();
        newSalesRefTF = new javax.swing.JTextField();
        saveSalesRefBtn = new javax.swing.JButton();
        jTabbedPane2 = new javax.swing.JTabbedPane();
        jPanel62 = new javax.swing.JPanel();
        editSalesRefNameLB = new javax.swing.JLabel();
        editSalesRefLoadUsersBtn = new javax.swing.JButton();
        jLabel94 = new javax.swing.JLabel();
        editSalesRefStatusCB = new javax.swing.JCheckBox();
        editSalesRefUpdateBtn = new javax.swing.JButton();
        editSalesRefDeleteBtn = new javax.swing.JButton();
        editSalesRefCB = new javax.swing.JComboBox();
        displayStatusBtn = new javax.swing.JButton();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        userAccountsMI = new javax.swing.JMenu();
        jMenuItem2 = new javax.swing.JMenuItem();
        jMenuItem4 = new javax.swing.JMenuItem();
        jMenuItem20 = new javax.swing.JMenuItem();
        openCashDrawyerMI = new javax.swing.JMenuItem();
        jMenuItem5 = new javax.swing.JMenuItem();
        jMenuItem11 = new javax.swing.JMenuItem();
        logOffMI = new javax.swing.JMenuItem();
        jMenuItem7 = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        newItemMI = new javax.swing.JMenuItem();
        editItemMI = new javax.swing.JMenuItem();
        jMenuItem10 = new javax.swing.JMenuItem();
        itemCostMI = new javax.swing.JMenuItem();
        jMenuItem14 = new javax.swing.JMenuItem();
        jMenuItem15 = new javax.swing.JMenuItem();
        itemRejectionsMI = new javax.swing.JMenuItem();
        jMenu3 = new javax.swing.JMenu();
        addSupplierMI = new javax.swing.JMenuItem();
        editDeleteSupplierMI = new javax.swing.JMenuItem();
        jMenuItem18 = new javax.swing.JMenuItem();
        jMenuItem19 = new javax.swing.JMenuItem();
        jMenuItem17 = new javax.swing.JMenuItem();
        jMenu4 = new javax.swing.JMenu();
        updateStoresMI = new javax.swing.JMenuItem();
        jMenuItem6 = new javax.swing.JMenuItem();
        jMenuItem12 = new javax.swing.JMenuItem();
        jMenuItem13 = new javax.swing.JMenuItem();
        updateStoresReportMI = new javax.swing.JMenuItem();
        jMenuItem16 = new javax.swing.JMenuItem();
        jMenu5 = new javax.swing.JMenu();
        customerInvoiceMI = new javax.swing.JMenuItem();
        editCustomerInvoiceMI = new javax.swing.JMenuItem();
        jMenuItem22 = new javax.swing.JMenuItem();
        jMenuItem8 = new javax.swing.JMenuItem();
        jMenu7 = new javax.swing.JMenu();
        jMenuItem23 = new javax.swing.JMenuItem();
        jMenuItem1 = new javax.swing.JMenuItem();
        updateCashAmountMI = new javax.swing.JMenuItem();
        reportsJM = new javax.swing.JMenu();
        monthBusinessProfitMI = new javax.swing.JMenuItem();
        jMenuItem3 = new javax.swing.JMenuItem();
        jMenuItem9 = new javax.swing.JMenuItem();
        jMenuItem21 = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("In & Out Main UI");

        newItemBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/newItem1.jpg"))); // NOI18N
        newItemBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/newItem2.jpg"))); // NOI18N
        newItemBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newItemBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(newItemBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(newItemBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        itemCostButton.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/itemCost1.jpg"))); // NOI18N
        itemCostButton.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/itemCost2.jpg"))); // NOI18N
        itemCostButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCostButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel8Layout = new javax.swing.GroupLayout(jPanel8);
        jPanel8.setLayout(jPanel8Layout);
        jPanel8Layout.setHorizontalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(itemCostButton, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        jPanel8Layout.setVerticalGroup(
            jPanel8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel8Layout.createSequentialGroup()
                .addComponent(itemCostButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jLabel16.setFont(new java.awt.Font("Times New Roman", 1, 14)); // NOI18N
        jLabel16.setText("Welcome :");

        welcomeLb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        welcomeLb.setText("jLabel17");

        updateStoresBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/updateStores1.jpg"))); // NOI18N
        updateStoresBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/updateStores2.jpg"))); // NOI18N
        updateStoresBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStoresBtnActionPerformed(evt);
            }
        });

        customerInvoiceBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/invoice1.jpg"))); // NOI18N
        customerInvoiceBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/invoice2.jpg"))); // NOI18N
        customerInvoiceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerInvoiceBtnActionPerformed(evt);
            }
        });

        monthBusinessProfitBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profit1.jpg"))); // NOI18N
        monthBusinessProfitBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/profit2.jpg"))); // NOI18N
        monthBusinessProfitBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthBusinessProfitBtnActionPerformed(evt);
            }
        });

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/expense1.jpg"))); // NOI18N
        jButton1.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/expense2.jpg"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        businessBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/business1.jpg"))); // NOI18N
        businessBtn.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/images/business2.jpg"))); // NOI18N
        businessBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                businessBtnActionPerformed(evt);
            }
        });

        jLabel51.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel51.setText("Date");

        todayDateLb.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        todayDateLb.setText("Today");

        jLabel52.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel52.setText("* Please Keep  System Date/Time Correctly.");

        cashAmountLb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        cashAmountLb.setText("Cashier Amount :");

        cashAmountValueLb.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        cashAmountValueLb.setForeground(new java.awt.Color(0, 153, 0));
        cashAmountValueLb.setText("cashierAmountLb");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jPanel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(updateStoresBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(customerInvoiceBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(businessBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(monthBusinessProfitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 106, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(51, 51, 51)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(cashAmountLb)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cashAmountValueLb))
                            .addComponent(jLabel52, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel51)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(todayDateLb, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(135, 135, 135))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addComponent(jLabel16)
                        .addGap(9, 9, 9)
                        .addComponent(welcomeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(44, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(monthBusinessProfitBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(businessBtn)
                            .addComponent(jButton1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jPanel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(11, 11, 11)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel16, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(welcomeLb, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(updateStoresBtn)
                            .addComponent(customerInvoiceBtn)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel51)
                            .addComponent(todayDateLb))
                        .addGap(18, 18, 18)
                        .addComponent(jLabel52)
                        .addGap(11, 11, 11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(cashAmountLb)
                            .addComponent(cashAmountValueLb))))
                .addContainerGap())
        );

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/shut_down.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel48.setText("Shut Down");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel48)
                .addGap(20, 20, 20))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 79, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel48)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 88, Short.MAX_VALUE)
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 83, Short.MAX_VALUE)
        );

        logOffBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/iconLargeLogout.gif"))); // NOI18N
        logOffBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOffBtnActionPerformed(evt);
            }
        });

        jLabel50.setText("Log Off");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(logOffBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(55, 55, 55))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jLabel50)
                        .addContainerGap())))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(20, 20, 20))
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(logOffBtn)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel50)
                        .addContainerGap())))
        );

        newItemIF.setTitle("New Item");

        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Add New Item", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11), new java.awt.Color(0, 51, 51))); // NOI18N
        jPanel6.setForeground(new java.awt.Color(0, 102, 102));

        newItemCodeTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newItemCodeTfActionPerformed(evt);
            }
        });

        jLabel1.setText("Item Bar Code");

        jLabel2.setText("Item Name");

        jLabel3.setText("Sales Price");

        jButton4.setText("Save");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        jButton4.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton4KeyPressed(evt);
            }
        });

        jButton5.setText("Cancel");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        newItemTypeCb.setRequestFocusEnabled(false);

        jLabel4.setText("Item Type");

        javax.swing.GroupLayout jPanel7Layout = new javax.swing.GroupLayout(jPanel7);
        jPanel7.setLayout(jPanel7Layout);
        jPanel7Layout.setHorizontalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3))
                        .addGap(57, 57, 57))
                    .addGroup(jPanel7Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(newSalesPriceTf)
                    .addComponent(newItemNameTf)
                    .addComponent(newItemCodeTf, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(newItemTypeCb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(91, 91, 91))
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addGap(174, 174, 174)
                .addComponent(jButton4)
                .addGap(18, 18, 18)
                .addComponent(jButton5)
                .addContainerGap(119, Short.MAX_VALUE))
        );
        jPanel7Layout.setVerticalGroup(
            jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel7Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newItemCodeTf, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newItemNameTf, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addGap(12, 12, 12)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newSalesPriceTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(newItemTypeCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4))
                .addGap(27, 27, 27)
                .addGroup(jPanel7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton4)
                    .addComponent(jButton5))
                .addContainerGap(24, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel6Layout = new javax.swing.GroupLayout(jPanel6);
        jPanel6.setLayout(jPanel6Layout);
        jPanel6Layout.setHorizontalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addGap(81, 81, 81)
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(60, Short.MAX_VALUE))
        );
        jPanel6Layout.setVerticalGroup(
            jPanel6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel6Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout newItemIFLayout = new javax.swing.GroupLayout(newItemIF.getContentPane());
        newItemIF.getContentPane().setLayout(newItemIFLayout);
        newItemIFLayout.setHorizontalGroup(
            newItemIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newItemIFLayout.createSequentialGroup()
                .addGap(325, 325, 325)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(233, Short.MAX_VALUE))
        );
        newItemIFLayout.setVerticalGroup(
            newItemIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newItemIFLayout.createSequentialGroup()
                .addGap(129, 129, 129)
                .addComponent(jPanel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(155, Short.MAX_VALUE))
        );

        jDesktopPane1.add(newItemIF);
        newItemIF.setBounds(0, 0, 1160, 560);

        editItemIF.setTitle("Edit Item");
        editItemIF.setNormalBounds(new java.awt.Rectangle(0, 0, 1160, 564));
        editItemIF.setPreferredSize(new java.awt.Dimension(1160, 564));

        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Edit Item", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel9.setPreferredSize(new java.awt.Dimension(586, 247));

        jLabel5.setText("Item Code");

        editItemCodeCb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                editItemCodeCbItemStateChanged(evt);
            }
        });

        jLabel6.setText("Item Name");

        editItemnameCb.setEditable(true);
        editItemnameCb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                editItemnameCbItemStateChanged(evt);
            }
        });
        editItemnameCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editItemnameCbActionPerformed(evt);
            }
        });
        editItemnameCb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                editItemnameCbKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                editItemnameCbKeyReleased(evt);
            }
        });

        jLabel7.setText("Sales Price");

        editSalesPricetf.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                editSalesPricetfKeyReleased(evt);
            }
        });

        jLabel8.setText("Item Type");

        jButton7.setText("Save");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });
        jButton7.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton7KeyPressed(evt);
            }
        });

        jButton8.setText("Cancel");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel8))
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(74, 74, 74)
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8))
                    .addGroup(jPanel10Layout.createSequentialGroup()
                        .addGap(40, 40, 40)
                        .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(editItemTypeCb, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editSalesPricetf, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editItemnameCb, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editItemCodeCb, javax.swing.GroupLayout.Alignment.LEADING, 0, 206, Short.MAX_VALUE))))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(editItemCodeCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(editItemnameCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(editSalesPricetf, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(editItemTypeCb, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton7)
                    .addComponent(jButton8))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel9Layout = new javax.swing.GroupLayout(jPanel9);
        jPanel9.setLayout(jPanel9Layout);
        jPanel9Layout.setHorizontalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel9Layout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(120, Short.MAX_VALUE))
        );
        jPanel9Layout.setVerticalGroup(
            jPanel9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel9Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel10, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout editItemIFLayout = new javax.swing.GroupLayout(editItemIF.getContentPane());
        editItemIF.getContentPane().setLayout(editItemIFLayout);
        editItemIFLayout.setHorizontalGroup(
            editItemIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editItemIFLayout.createSequentialGroup()
                .addGap(330, 330, 330)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(228, Short.MAX_VALUE))
        );
        editItemIFLayout.setVerticalGroup(
            editItemIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editItemIFLayout.createSequentialGroup()
                .addGap(133, 133, 133)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(151, Short.MAX_VALUE))
        );

        jDesktopPane1.add(editItemIF);
        editItemIF.setBounds(0, 0, 1160, 560);

        updateStoresIF.setTitle("Update Stores");

        jPanel12.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Update Stores", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel9.setText("Item Code");

        jLabel10.setText("Item Name");

        upStoresItemNameTf.setEditable(false);

        jLabel11.setText("Quantity");

        jLabel12.setText("Re-Order Level");

        jButton9.setText("Update");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });
        jButton9.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton9KeyPressed(evt);
            }
        });

        jButton10.setText("Cancel");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });
        jButton10.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton10KeyPressed(evt);
            }
        });

        upStoresItemCodeCb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                upStoresItemCodeCbItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(57, 57, 57)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel12)
                    .addComponent(jLabel9)
                    .addComponent(jLabel10)
                    .addComponent(jLabel11))
                .addGap(50, 50, 50)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(upStoresItemCodeCb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel12Layout.createSequentialGroup()
                        .addGap(31, 31, 31)
                        .addComponent(jButton9)
                        .addGap(18, 18, 18)
                        .addComponent(jButton10))
                    .addComponent(upStoresQuantityTf)
                    .addComponent(upStoresReOrderTf, javax.swing.GroupLayout.DEFAULT_SIZE, 216, Short.MAX_VALUE)
                    .addComponent(upStoresItemNameTf))
                .addGap(47, 47, 47))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(upStoresItemCodeCb, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(upStoresItemNameTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(upStoresQuantityTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel12)
                    .addComponent(upStoresReOrderTf, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton10)
                    .addComponent(jButton9))
                .addContainerGap(26, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel11Layout = new javax.swing.GroupLayout(jPanel11);
        jPanel11.setLayout(jPanel11Layout);
        jPanel11Layout.setHorizontalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel11Layout.createSequentialGroup()
                .addContainerGap(145, Short.MAX_VALUE)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(136, 136, 136))
        );
        jPanel11Layout.setVerticalGroup(
            jPanel11Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel11Layout.createSequentialGroup()
                .addGap(52, 52, 52)
                .addComponent(jPanel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(84, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout updateStoresIFLayout = new javax.swing.GroupLayout(updateStoresIF.getContentPane());
        updateStoresIF.getContentPane().setLayout(updateStoresIFLayout);
        updateStoresIFLayout.setHorizontalGroup(
            updateStoresIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateStoresIFLayout.createSequentialGroup()
                .addGap(183, 183, 183)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(225, Short.MAX_VALUE))
        );
        updateStoresIFLayout.setVerticalGroup(
            updateStoresIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateStoresIFLayout.createSequentialGroup()
                .addContainerGap(72, Short.MAX_VALUE)
                .addComponent(jPanel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(64, 64, 64))
        );

        jDesktopPane1.add(updateStoresIF);
        updateStoresIF.setBounds(0, 0, 1160, 560);

        customerInvoiceIF.setTitle("Customer Invoice");
        customerInvoiceIF.setMaximumSize(new java.awt.Dimension(1160, 561));
        customerInvoiceIF.setVisible(true);

        invoiceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Code", "Item Name", "Price per One", "Quantity", "Amount", "Discount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, true, true, true, true, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(invoiceTable);

        jLabel20.setText("Receieved Amount");

        reAmountTf.setEditable(false);
        reAmountTf.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        reAmountTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reAmountTfActionPerformed(evt);
            }
        });

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel21.setText("Balance");

        balanceLb.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        balanceLb.setForeground(new java.awt.Color(255, 51, 51));
        balanceLb.setText("0.0");

        jButton14.setText("Balance");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });
        jButton14.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton14KeyPressed(evt);
            }
        });

        newInvoiceBtn.setText("New");
        newInvoiceBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newInvoiceBtnActionPerformed(evt);
            }
        });
        newInvoiceBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                newInvoiceBtnKeyPressed(evt);
            }
        });

        jLabel34.setText("Discount ");

        discountCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10" }));
        discountCb.setEnabled(false);
        discountCb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                discountCbItemStateChanged(evt);
            }
        });
        discountCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountCbActionPerformed(evt);
            }
        });

        discountChkbx.setText("Enable");
        discountChkbx.setEnabled(false);
        discountChkbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountChkbxActionPerformed(evt);
            }
        });

        discountBtn.setText("Discount");
        discountBtn.setEnabled(false);
        discountBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountBtnActionPerformed(evt);
            }
        });
        discountBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                discountBtnKeyPressed(evt);
            }
        });

        discountLb.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        discountLb.setForeground(new java.awt.Color(0, 204, 204));
        discountLb.setText("0.0");

        percentRadio.setText("Percent(%)");
        percentRadio.setEnabled(false);
        percentRadio.setName("discountRadio"); // NOI18N
        percentRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                percentRadioActionPerformed(evt);
            }
        });

        textAmountRadio.setText("Text Amount");
        textAmountRadio.setEnabled(false);
        textAmountRadio.setName("discountRadio"); // NOI18N
        textAmountRadio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                textAmountRadioActionPerformed(evt);
            }
        });

        textDiscountTf.setEnabled(false);

        itemWiseChkBx.setText("Item Wise Discount");
        itemWiseChkBx.setEnabled(false);
        itemWiseChkBx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemWiseChkBxActionPerformed(evt);
            }
        });

        itemWisePercentRB.setSelected(true);
        itemWisePercentRB.setText("PerCent(%)");
        itemWisePercentRB.setEnabled(false);
        itemWisePercentRB.setName("itemWiseDiscount"); // NOI18N
        itemWisePercentRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemWisePercentRBActionPerformed(evt);
            }
        });

        itemWiseTextRB.setText("TextAmount");
        itemWiseTextRB.setEnabled(false);
        itemWiseTextRB.setName("itemWiseDiscount"); // NOI18N
        itemWiseTextRB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemWiseTextRBActionPerformed(evt);
            }
        });

        itemWiseDiscountTF.setEnabled(false);
        itemWiseDiscountTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemWiseDiscountTFActionPerformed(evt);
            }
        });

        discountItemWiseCB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                discountItemWiseCBActionPerformed(evt);
            }
        });

        businessByLB.setText("Business By");

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(114, 114, 114)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel34)
                            .addComponent(itemWiseChkBx))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(discountChkbx)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(textAmountRadio)
                                    .addComponent(percentRadio)))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addComponent(discountItemWiseCB, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(itemWisePercentRB)
                                    .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jButton14)
                                        .addComponent(itemWiseTextRB))))))
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(businessByLB)
                            .addComponent(jLabel21))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(balanceLb, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(businessByCB, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(reAmountTf, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 146, Short.MAX_VALUE)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(itemWiseDiscountTF)
                            .addComponent(textDiscountTf)
                            .addComponent(discountCb, 0, 54, Short.MAX_VALUE))
                        .addGap(8, 8, 8)
                        .addComponent(discountBtn)
                        .addGap(18, 18, 18)
                        .addComponent(discountLb, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(newInvoiceBtn))
                .addContainerGap())
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel34)
                            .addComponent(discountCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(discountChkbx)
                            .addComponent(percentRadio))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(textAmountRadio)
                            .addComponent(textDiscountTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel20Layout.createSequentialGroup()
                                        .addComponent(itemWisePercentRB)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(itemWiseTextRB))
                                    .addComponent(itemWiseDiscountTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(discountItemWiseCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(itemWiseChkBx))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 6, Short.MAX_VALUE)
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(businessByCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(businessByLB))
                        .addGap(18, 18, 18))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(discountBtn)
                            .addComponent(discountLb, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(43, 43, 43)))
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel20)
                    .addComponent(reAmountTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton14)
                    .addComponent(newInvoiceBtn))
                .addGap(9, 9, 9)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel21)
                    .addComponent(balanceLb, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        jButton13.setText("Refresh");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel21Layout = new javax.swing.GroupLayout(jPanel21);
        jPanel21.setLayout(jPanel21Layout);
        jPanel21Layout.setHorizontalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton13)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel21Layout.setVerticalGroup(
            jPanel21Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel21Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        jLabel77.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel77.setText("Remove Item");

        jLabel78.setText("Item Code :");

        itemRemoveBtn.setText("Remove");
        itemRemoveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRemoveBtnActionPerformed(evt);
            }
        });

        returnValueLb.setFont(new java.awt.Font("Arial Black", 0, 12)); // NOI18N
        returnValueLb.setForeground(new java.awt.Color(0, 153, 153));
        returnValueLb.setText("0.0");

        returnItemValueLb.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        returnItemValueLb.setForeground(new java.awt.Color(0, 153, 153));
        returnItemValueLb.setText("Return Item Value : ");

        javax.swing.GroupLayout jPanel51Layout = new javax.swing.GroupLayout(jPanel51);
        jPanel51.setLayout(jPanel51Layout);
        jPanel51Layout.setHorizontalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addComponent(returnValueLb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(returnItemValueLb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(148, Short.MAX_VALUE))
        );
        jPanel51Layout.setVerticalGroup(
            jPanel51Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel51Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(returnItemValueLb)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(returnValueLb)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel48Layout = new javax.swing.GroupLayout(jPanel48);
        jPanel48.setLayout(jPanel48Layout);
        jPanel48Layout.setHorizontalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel48Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(itemRemoveBtn)
                .addGap(132, 132, 132))
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel48Layout.createSequentialGroup()
                                .addComponent(jLabel78)
                                .addGap(18, 18, 18)
                                .addComponent(removeItemCb, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel77)))
                    .addGroup(jPanel48Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel51, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel48Layout.setVerticalGroup(
            jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel48Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel77)
                .addGap(18, 18, 18)
                .addGroup(jPanel48Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel78)
                    .addComponent(removeItemCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addComponent(itemRemoveBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel51, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel16Layout = new javax.swing.GroupLayout(jPanel16);
        jPanel16.setLayout(jPanel16Layout);
        jPanel16Layout.setHorizontalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel16Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 710, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(135, 135, 135))
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGap(46, 46, 46)
                .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, 252, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(67, 67, 67))
        );
        jPanel16Layout.setVerticalGroup(
            jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel16Layout.createSequentialGroup()
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel16Layout.createSequentialGroup()
                        .addGap(27, 27, 27)
                        .addComponent(jPanel48, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel16Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 85, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(5, 5, 5))
        );

        jPanel17.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jPanel17.setPreferredSize(new java.awt.Dimension(478, 206));

        jPanel19.setBackground(new java.awt.Color(0, 0, 0));
        jPanel19.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        totalAmountLb.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        totalAmountLb.setForeground(new java.awt.Color(51, 204, 0));
        totalAmountLb.setText("0.0");

        javax.swing.GroupLayout jPanel19Layout = new javax.swing.GroupLayout(jPanel19);
        jPanel19.setLayout(jPanel19Layout);
        jPanel19Layout.setHorizontalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(35, 35, 35)
                .addComponent(totalAmountLb, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel19Layout.setVerticalGroup(
            jPanel19Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel19Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(totalAmountLb, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(41, Short.MAX_VALUE))
        );

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel19.setText("Total Amount");

        javax.swing.GroupLayout jPanel17Layout = new javax.swing.GroupLayout(jPanel17);
        jPanel17.setLayout(jPanel17Layout);
        jPanel17Layout.setHorizontalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGap(34, 34, 34)
                .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );
        jPanel17Layout.setVerticalGroup(
            jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel17Layout.createSequentialGroup()
                .addGroup(jPanel17Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addGap(43, 43, 43)
                        .addComponent(jLabel19, javax.swing.GroupLayout.PREFERRED_SIZE, 52, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel17Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel18.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel13.setText("Item Code");

        invoiceItemCodeCb.setEditable(true);
        invoiceItemCodeCb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                invoiceItemCodeCbItemStateChanged(evt);
            }
        });
        invoiceItemCodeCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceItemCodeCbActionPerformed(evt);
            }
        });
        invoiceItemCodeCb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                invoiceItemCodeCbKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                invoiceItemCodeCbKeyReleased(evt);
            }
        });

        jLabel14.setText("Item Name");

        jLabel15.setText("Item Type");

        jLabel17.setText("Sales Price");

        itemNameLb.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        itemNameLb.setText("Item Name");

        itemTypeLb.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        itemTypeLb.setText("Item Type");

        salesPriceLb.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        salesPriceLb.setForeground(new java.awt.Color(0, 102, 102));
        salesPriceLb.setText("Sales Price");

        jLabel18.setText("No of Items");

        noOfItemsTf.setEnabled(false);
        noOfItemsTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                noOfItemsTfActionPerformed(evt);
            }
        });

        itemAddTableBtn.setText("Add");
        itemAddTableBtn.setEnabled(false);
        itemAddTableBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAddTableBtnActionPerformed(evt);
            }
        });
        itemAddTableBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                itemAddTableBtnKeyPressed(evt);
            }
        });

        itemCostLabel.setText("Item Cost");

        itemCostLb.setFont(new java.awt.Font("Tahoma", 1, 11)); // NOI18N
        itemCostLb.setText("Item Cost");

        javax.swing.GroupLayout jPanel15Layout = new javax.swing.GroupLayout(jPanel15);
        jPanel15.setLayout(jPanel15Layout);
        jPanel15Layout.setHorizontalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel13)
                                .addGap(18, 18, 18)
                                .addComponent(invoiceItemCodeCb, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel15Layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(noOfItemsTf)))
                        .addGap(38, 38, 38))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel15Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(itemAddTableBtn)
                        .addGap(79, 79, 79)))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel14)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jLabel17)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(itemCostLabel)
                            .addComponent(jLabel15))))
                .addGap(34, 34, 34)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(itemCostLb, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(itemTypeLb, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(salesPriceLb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(itemNameLb, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );
        jPanel15Layout.setVerticalGroup(
            jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel15Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel13)
                    .addComponent(invoiceItemCodeCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14)
                    .addComponent(itemNameLb))
                .addGap(18, 18, 18)
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel17)
                    .addComponent(salesPriceLb)
                    .addComponent(jLabel18)
                    .addComponent(noOfItemsTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(28, 28, 28)
                        .addComponent(itemAddTableBtn))
                    .addGroup(jPanel15Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel15)
                            .addComponent(itemTypeLb))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel15Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(itemCostLabel)
                            .addComponent(itemCostLb, javax.swing.GroupLayout.PREFERRED_SIZE, 14, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel18Layout = new javax.swing.GroupLayout(jPanel18);
        jPanel18.setLayout(jPanel18Layout);
        jPanel18Layout.setHorizontalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel18Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, 421, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel18Layout.setVerticalGroup(
            jPanel18Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel18Layout.createSequentialGroup()
                .addComponent(jPanel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        barCodeChkbx.setSelected(true);
        barCodeChkbx.setText("Barcode On");
        barCodeChkbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                barCodeChkbxActionPerformed(evt);
            }
        });

        editCusInvoiceIdLb.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        editCusInvoiceIdLb.setText("Invoice Id :");

        editInvoiceIdValueLb.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        editInvoiceIdValueLb.setText("Invoice Id");

        customerInvoiceChkbx.setSelected(true);
        customerInvoiceChkbx.setText("Customer Invoice");
        customerInvoiceChkbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerInvoiceChkbxActionPerformed(evt);
            }
        });

        openCashDrawyerChkbx.setSelected(true);
        openCashDrawyerChkbx.setText("Open Cash Drawyer");
        openCashDrawyerChkbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openCashDrawyerChkbxActionPerformed(evt);
            }
        });

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel54.setText("No of Items :");

        noOfIemsLb.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        noOfIemsLb.setText("0");

        jButton16.setText("Find By Item Name");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel14Layout = new javax.swing.GroupLayout(jPanel14);
        jPanel14.setLayout(jPanel14Layout);
        jPanel14Layout.setHorizontalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(barCodeChkbx)
                .addGap(18, 18, 18)
                .addComponent(customerInvoiceChkbx)
                .addGap(36, 36, 36)
                .addComponent(openCashDrawyerChkbx)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(91, 91, 91)
                .addComponent(editCusInvoiceIdLb, javax.swing.GroupLayout.PREFERRED_SIZE, 86, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editInvoiceIdValueLb)
                .addGap(48, 48, 48)
                .addComponent(jLabel54)
                .addGap(18, 18, 18)
                .addComponent(noOfIemsLb, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(491, 491, 491))
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, 1029, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(160, 160, 160)
                        .addComponent(jPanel17, javax.swing.GroupLayout.PREFERRED_SIZE, 459, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(432, 432, 432))
        );
        jPanel14Layout.setVerticalGroup(
            jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel14Layout.createSequentialGroup()
                .addGap(11, 11, 11)
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(barCodeChkbx)
                        .addComponent(editInvoiceIdValueLb)
                        .addComponent(editCusInvoiceIdLb)
                        .addComponent(customerInvoiceChkbx)
                        .addComponent(openCashDrawyerChkbx)
                        .addComponent(jLabel54)
                        .addComponent(jButton16))
                    .addComponent(noOfIemsLb, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel14Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addComponent(jPanel18, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel14Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel17, 0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout customerInvoiceIFLayout = new javax.swing.GroupLayout(customerInvoiceIF.getContentPane());
        customerInvoiceIF.getContentPane().setLayout(customerInvoiceIFLayout);
        customerInvoiceIFLayout.setHorizontalGroup(
            customerInvoiceIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerInvoiceIFLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        customerInvoiceIFLayout.setVerticalGroup(
            customerInvoiceIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerInvoiceIFLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        jDesktopPane1.add(customerInvoiceIF);
        customerInvoiceIF.setBounds(0, 0, 1160, 560);

        editCustomerInvoiceIF.setTitle("Edit Customer Invoice");

        jLabel22.setText("Invoice Id");

        invoiceIdCb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                invoiceIdCbItemStateChanged(evt);
            }
        });
        invoiceIdCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceIdCbActionPerformed(evt);
            }
        });

        editInvoiceSearchBtn.setText("Load Data");
        editInvoiceSearchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editInvoiceSearchBtnActionPerformed(evt);
            }
        });
        editInvoiceSearchBtn.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                editInvoiceSearchBtnKeyPressed(evt);
            }
        });

        jLabel79.setText("Load Invoices");

        loadInvoiceIdCb.setEditable(true);
        loadInvoiceIdCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1 - 100", "100 - 200", "200- 300", "300 - 400", "400 - 500" }));

        loadInvoiceIdBtn.setText("Load Ids");
        loadInvoiceIdBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loadInvoiceIdBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel22)
                    .addComponent(jLabel79))
                .addGap(29, 29, 29)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(loadInvoiceIdCb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(invoiceIdCb, 0, 163, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editInvoiceSearchBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(loadInvoiceIdBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(95, Short.MAX_VALUE))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addContainerGap(12, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel79)
                    .addComponent(loadInvoiceIdCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(loadInvoiceIdBtn))
                .addGap(18, 18, 18)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(invoiceIdCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel22)
                    .addComponent(editInvoiceSearchBtn)))
        );

        jLabel23.setText("Invoiced Date");

        editDate.setText("Date");

        jLabel25.setText("Entered By");

        editEnteredByLb.setText("enteredByLb");

        jLabel26.setText("Total Amount");

        editTotalAmountTf.setEditable(false);

        jLabel43.setText("Discount Amount");

        editDiscountTf.setEditable(false);

        discountPercentageTf.setEditable(false);

        jLabel55.setText("%");

        jLabel56.setText("P");

        javax.swing.GroupLayout jPanel25Layout = new javax.swing.GroupLayout(jPanel25);
        jPanel25.setLayout(jPanel25Layout);
        jPanel25Layout.setHorizontalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel23)
                    .addComponent(jLabel25)
                    .addComponent(jLabel26)
                    .addComponent(jLabel43))
                .addGap(76, 76, 76)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(editEnteredByLb)
                        .addComponent(editDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel25Layout.createSequentialGroup()
                        .addComponent(editDiscountTf, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 23, Short.MAX_VALUE)
                        .addComponent(jLabel56, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(discountPercentageTf, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel55, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(editTotalAmountTf, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(28, 28, 28))
        );
        jPanel25Layout.setVerticalGroup(
            jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel25Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel23)
                    .addComponent(editDate))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editEnteredByLb)
                    .addComponent(jLabel25))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel26)
                    .addComponent(editTotalAmountTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel43)
                    .addGroup(jPanel25Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(editDiscountTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel55)
                        .addComponent(discountPercentageTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel56)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        editInvoiceTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item Code", "Item Name", "Price Per One", "Quantity", "Amount", "Discount"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Double.class, java.lang.Integer.class, java.lang.Double.class, java.lang.Double.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(editInvoiceTable);

        javax.swing.GroupLayout jPanel26Layout = new javax.swing.GroupLayout(jPanel26);
        jPanel26.setLayout(jPanel26Layout);
        jPanel26Layout.setHorizontalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel26Layout.setVerticalGroup(
            jPanel26Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel26Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel41.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Return Items", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel45.setText("Item Code");

        returnInvoiceItemCodeCb.setEditable(true);
        returnInvoiceItemCodeCb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                returnInvoiceItemCodeCbItemStateChanged(evt);
            }
        });
        returnInvoiceItemCodeCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnInvoiceItemCodeCbActionPerformed(evt);
            }
        });
        returnInvoiceItemCodeCb.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                returnInvoiceItemCodeCbKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                returnInvoiceItemCodeCbKeyReleased(evt);
            }
        });

        jLabel49.setText("No of Items");

        returnNoOfItemsTf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnNoOfItemsTfActionPerformed(evt);
            }
        });

        itemAddTableBtn1.setText("Return Item");
        itemAddTableBtn1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemAddTableBtn1ActionPerformed(evt);
            }
        });
        itemAddTableBtn1.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                itemAddTableBtn1KeyPressed(evt);
            }
        });

        javax.swing.GroupLayout jPanel42Layout = new javax.swing.GroupLayout(jPanel42);
        jPanel42.setLayout(jPanel42Layout);
        jPanel42Layout.setHorizontalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel42Layout.createSequentialGroup()
                                .addComponent(jLabel45)
                                .addGap(18, 18, 18)
                                .addComponent(returnInvoiceItemCodeCb, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel42Layout.createSequentialGroup()
                                .addComponent(jLabel49)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(returnNoOfItemsTf))))
                    .addGroup(jPanel42Layout.createSequentialGroup()
                        .addGap(94, 94, 94)
                        .addComponent(itemAddTableBtn1)))
                .addContainerGap(93, Short.MAX_VALUE))
        );
        jPanel42Layout.setVerticalGroup(
            jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel42Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel45)
                    .addComponent(returnInvoiceItemCodeCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel42Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel49)
                    .addComponent(returnNoOfItemsTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(itemAddTableBtn1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel41Layout = new javax.swing.GroupLayout(jPanel41);
        jPanel41.setLayout(jPanel41Layout);
        jPanel41Layout.setHorizontalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel41Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );
        jPanel41Layout.setVerticalGroup(
            jPanel41Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel41Layout.createSequentialGroup()
                .addComponent(jPanel42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel33Layout = new javax.swing.GroupLayout(jPanel33);
        jPanel33.setLayout(jPanel33Layout);
        jPanel33Layout.setHorizontalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );
        jPanel33Layout.setVerticalGroup(
            jPanel33Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel33Layout.createSequentialGroup()
                .addComponent(jPanel41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton25.setText("Refresh");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel49Layout = new javax.swing.GroupLayout(jPanel49);
        jPanel49.setLayout(jPanel49Layout);
        jPanel49Layout.setHorizontalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton25)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel49Layout.setVerticalGroup(
            jPanel49Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel49Layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        jPanel50.setBackground(new java.awt.Color(0, 0, 0));

        returnAmountLb.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        returnAmountLb.setForeground(new java.awt.Color(0, 204, 51));
        returnAmountLb.setText("0.0");

        javax.swing.GroupLayout jPanel50Layout = new javax.swing.GroupLayout(jPanel50);
        jPanel50.setLayout(jPanel50Layout);
        jPanel50Layout.setHorizontalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGap(44, 44, 44)
                .addComponent(returnAmountLb, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
        );
        jPanel50Layout.setVerticalGroup(
            jPanel50Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel50Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(returnAmountLb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(33, Short.MAX_VALUE))
        );

        buyNewitemsBtn.setText("Buy New Items");
        buyNewitemsBtn.setEnabled(false);
        buyNewitemsBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buyNewitemsBtnActionPerformed(evt);
            }
        });

        invoiceReprintBtn.setText("Invoice Reprint");
        invoiceReprintBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                invoiceReprintBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel47Layout = new javax.swing.GroupLayout(jPanel47);
        jPanel47.setLayout(jPanel47Layout);
        jPanel47Layout.setHorizontalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel47Layout.createSequentialGroup()
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                        .addGap(211, 211, 211)
                        .addComponent(invoiceReprintBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 188, Short.MAX_VALUE)
                        .addComponent(buyNewitemsBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(241, 241, 241))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                        .addContainerGap(446, Short.MAX_VALUE)
                        .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(201, 201, 201)))
                .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18))
        );
        jPanel47Layout.setVerticalGroup(
            jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel47Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel50, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jPanel49, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel47Layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(jPanel47Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(invoiceReprintBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(buyNewitemsBtn, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 48, Short.MAX_VALUE))))
                .addContainerGap())
        );

        jLabel44.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        jLabel44.setText("Return Value");

        javax.swing.GroupLayout jPanel23Layout = new javax.swing.GroupLayout(jPanel23);
        jPanel23.setLayout(jPanel23Layout);
        jPanel23Layout.setHorizontalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addGap(219, 219, 219)
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(148, 148, 148))
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(313, 313, 313)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel23Layout.createSequentialGroup()
                    .addGap(20, 20, 20)
                    .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(230, Short.MAX_VALUE)))
        );
        jPanel23Layout.setVerticalGroup(
            jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel23Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jPanel25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel23Layout.createSequentialGroup()
                        .addComponent(jPanel24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 25, Short.MAX_VALUE)
                .addComponent(jLabel44, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(174, Short.MAX_VALUE))
            .addGroup(jPanel23Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel23Layout.createSequentialGroup()
                    .addContainerGap(275, Short.MAX_VALUE)
                    .addComponent(jPanel47, javax.swing.GroupLayout.PREFERRED_SIZE, 213, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(21, 21, 21)))
        );

        javax.swing.GroupLayout editCustomerInvoiceIFLayout = new javax.swing.GroupLayout(editCustomerInvoiceIF.getContentPane());
        editCustomerInvoiceIF.getContentPane().setLayout(editCustomerInvoiceIFLayout);
        editCustomerInvoiceIFLayout.setHorizontalGroup(
            editCustomerInvoiceIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editCustomerInvoiceIFLayout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(jPanel23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        editCustomerInvoiceIFLayout.setVerticalGroup(
            editCustomerInvoiceIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editCustomerInvoiceIFLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel23, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        jDesktopPane1.add(editCustomerInvoiceIF);
        editCustomerInvoiceIF.setBounds(0, 0, 1160, 560);

        newExpensesIF.setTitle("Cash Withdraw");

        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Cash Withdraw", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel24.setText("Date");

        jLabel27.setText("Expense Description");

        nexDes.setColumns(20);
        nexDes.setRows(5);
        jScrollPane3.setViewportView(nexDes);

        jLabel28.setText("Expense Amount");

        jButton18.setText("Save");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setText("Cancel");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jLabel57.setText("Expense Type");

        expenseTypeCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Other Expenses", "Bank Transer", "Administrator Withdraw" }));

        javax.swing.GroupLayout jPanel28Layout = new javax.swing.GroupLayout(jPanel28);
        jPanel28.setLayout(jPanel28Layout);
        jPanel28Layout.setHorizontalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel28Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel27)
                            .addComponent(jLabel57)
                            .addComponent(jLabel24))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 223, Short.MAX_VALUE)
                            .addComponent(expenseTypeCb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(nexpenseDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel28Layout.createSequentialGroup()
                        .addComponent(jLabel28)
                        .addGap(18, 18, 18)
                        .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel28Layout.createSequentialGroup()
                                .addGap(34, 34, 34)
                                .addComponent(jButton18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton19))
                            .addComponent(nExAmount))))
                .addContainerGap(261, Short.MAX_VALUE))
        );
        jPanel28Layout.setVerticalGroup(
            jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel28Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel24)
                    .addComponent(nexpenseDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel57)
                    .addComponent(expenseTypeCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel27)
                    .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel28)
                    .addComponent(nExAmount, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel28Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton19)
                    .addComponent(jButton18))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel27Layout = new javax.swing.GroupLayout(jPanel27);
        jPanel27.setLayout(jPanel27Layout);
        jPanel27Layout.setHorizontalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(136, 136, 136)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(142, Short.MAX_VALUE))
        );
        jPanel27Layout.setVerticalGroup(
            jPanel27Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel27Layout.createSequentialGroup()
                .addGap(43, 43, 43)
                .addComponent(jPanel28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout newExpensesIFLayout = new javax.swing.GroupLayout(newExpensesIF.getContentPane());
        newExpensesIF.getContentPane().setLayout(newExpensesIFLayout);
        newExpensesIFLayout.setHorizontalGroup(
            newExpensesIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newExpensesIFLayout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        newExpensesIFLayout.setVerticalGroup(
            newExpensesIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newExpensesIFLayout.createSequentialGroup()
                .addGap(79, 79, 79)
                .addComponent(jPanel27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(132, Short.MAX_VALUE))
        );

        jDesktopPane1.add(newExpensesIF);
        newExpensesIF.setBounds(0, 0, 1160, 560);

        itemCostIF.setTitle("Item Cost");

        jPanel30.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Item Cost", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11)))); // NOI18N

        jLabel29.setText("Item Code");

        jLabel30.setText("Item Name");

        jLabel31.setText("Sales Price");

        jLabel33.setText("Cost Price");

        icItemNameTf.setEditable(false);

        icSalesPriceTf.setEditable(false);

        icItemCodeCb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                icItemCodeCbItemStateChanged(evt);
            }
        });
        icItemCodeCb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                icItemCodeCbActionPerformed(evt);
            }
        });

        jButton20.setText("Save");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });
        jButton20.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                jButton20KeyPressed(evt);
            }
        });

        jButton21.setText("Cancel");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jLabel32.setText("Type");

        ictypeTf.setEditable(false);

        javax.swing.GroupLayout jPanel30Layout = new javax.swing.GroupLayout(jPanel30);
        jPanel30.setLayout(jPanel30Layout);
        jPanel30Layout.setHorizontalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel30Layout.createSequentialGroup()
                .addContainerGap(193, Short.MAX_VALUE)
                .addComponent(jButton20)
                .addGap(26, 26, 26)
                .addComponent(jButton21)
                .addGap(128, 128, 128))
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel29)
                    .addComponent(jLabel30)
                    .addComponent(jLabel31)
                    .addComponent(jLabel32)
                    .addComponent(jLabel33))
                .addGap(26, 26, 26)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(ictypeTf, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addComponent(icCostPriceTf, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(icSalesPriceTf)
                        .addComponent(icItemNameTf)
                        .addComponent(icItemCodeCb, 0, 206, Short.MAX_VALUE)))
                .addGap(102, 102, 102))
        );
        jPanel30Layout.setVerticalGroup(
            jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel30Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel29)
                    .addComponent(icItemCodeCb, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel30)
                    .addComponent(icItemNameTf, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel31)
                    .addComponent(icSalesPriceTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(24, 24, 24)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel32)
                    .addComponent(ictypeTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(15, 15, 15)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel33)
                    .addComponent(icCostPriceTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(27, 27, 27)
                .addGroup(jPanel30Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton21)
                    .addComponent(jButton20))
                .addContainerGap(45, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel29Layout = new javax.swing.GroupLayout(jPanel29);
        jPanel29.setLayout(jPanel29Layout);
        jPanel29Layout.setHorizontalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(104, 104, 104)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );
        jPanel29Layout.setVerticalGroup(
            jPanel29Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel29Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jPanel30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout itemCostIFLayout = new javax.swing.GroupLayout(itemCostIF.getContentPane());
        itemCostIF.getContentPane().setLayout(itemCostIFLayout);
        itemCostIFLayout.setHorizontalGroup(
            itemCostIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemCostIFLayout.createSequentialGroup()
                .addGap(298, 298, 298)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(167, Short.MAX_VALUE))
        );
        itemCostIFLayout.setVerticalGroup(
            itemCostIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemCostIFLayout.createSequentialGroup()
                .addContainerGap(48, Short.MAX_VALUE)
                .addComponent(jPanel29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(66, 66, 66))
        );

        jDesktopPane1.add(itemCostIF);
        itemCostIF.setBounds(0, 0, 1160, 560);

        newOrdersIF.setTitle("New Orders");

        jPanel36.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "New Orders", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N
        jPanel36.setMaximumSize(new java.awt.Dimension(511, 332));
        jPanel36.setMinimumSize(new java.awt.Dimension(511, 332));

        jLabel35.setText("Date");

        jLabel36.setText("Customer Name");

        jLabel37.setText("Contact Number");

        jLabel38.setText("Order Details");

        jButton6.setText("Save");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton11.setText("Cancel");

        noOrderDeailsTa.setColumns(20);
        noOrderDeailsTa.setRows(5);
        jScrollPane4.setViewportView(noOrderDeailsTa);

        javax.swing.GroupLayout jPanel36Layout = new javax.swing.GroupLayout(jPanel36);
        jPanel36.setLayout(jPanel36Layout);
        jPanel36Layout.setHorizontalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel35)
                    .addComponent(jLabel36)
                    .addComponent(jLabel37)
                    .addComponent(jLabel38))
                .addGap(32, 32, 32)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel36Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jButton6)
                        .addGap(34, 34, 34)
                        .addComponent(jButton11))
                    .addComponent(noCustomerNameTf)
                    .addComponent(noContactNumberTf)
                    .addComponent(noDate, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 240, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(81, Short.MAX_VALUE))
        );
        jPanel36Layout.setVerticalGroup(
            jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel36Layout.createSequentialGroup()
                .addGap(42, 42, 42)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel35)
                    .addComponent(noDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel36)
                    .addComponent(noCustomerNameTf, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel37)
                    .addComponent(noContactNumberTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel38)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 89, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addGroup(jPanel36Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton6)
                    .addComponent(jButton11))
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout jPanel35Layout = new javax.swing.GroupLayout(jPanel35);
        jPanel35.setLayout(jPanel35Layout);
        jPanel35Layout.setHorizontalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addContainerGap(172, Short.MAX_VALUE)
                .addComponent(jPanel36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(161, 161, 161))
        );
        jPanel35Layout.setVerticalGroup(
            jPanel35Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel35Layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addComponent(jPanel36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );

        javax.swing.GroupLayout newOrdersIFLayout = new javax.swing.GroupLayout(newOrdersIF.getContentPane());
        newOrdersIF.getContentPane().setLayout(newOrdersIFLayout);
        newOrdersIFLayout.setHorizontalGroup(
            newOrdersIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newOrdersIFLayout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(149, Short.MAX_VALUE))
        );
        newOrdersIFLayout.setVerticalGroup(
            newOrdersIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newOrdersIFLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(jPanel35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(88, Short.MAX_VALUE))
        );

        jDesktopPane1.add(newOrdersIF);
        newOrdersIF.setBounds(0, 0, 1160, 560);

        viewExpenseIF.setTitle("View Expenses Report");

        jPanel38.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "View Expenses", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel39.setText("Month");

        reExpMonthCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "Octomber", "November", "December" }));

        jLabel40.setText("Year");

        reExpYearCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2008", "2009", "2010", "2011", "2012", "2013", " " }));
        reExpYearCb.setSelectedIndex(1);

        jButton12.setText("Generate Report");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel38Layout = new javax.swing.GroupLayout(jPanel38);
        jPanel38.setLayout(jPanel38Layout);
        jPanel38Layout.setHorizontalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel39)
                .addGap(18, 18, 18)
                .addComponent(reExpMonthCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel40)
                .addGap(18, 18, 18)
                .addComponent(reExpYearCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(jButton12)
                .addGap(95, 95, 95))
        );
        jPanel38Layout.setVerticalGroup(
            jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel38Layout.createSequentialGroup()
                .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel38Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel40)
                            .addComponent(jLabel39))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel38Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel38Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reExpMonthCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reExpYearCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)))
                .addComponent(jButton12)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout jPanel37Layout = new javax.swing.GroupLayout(jPanel37);
        jPanel37.setLayout(jPanel37Layout);
        jPanel37Layout.setHorizontalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel37Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel37Layout.setVerticalGroup(
            jPanel37Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel37Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jPanel38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout viewExpenseIFLayout = new javax.swing.GroupLayout(viewExpenseIF.getContentPane());
        viewExpenseIF.getContentPane().setLayout(viewExpenseIFLayout);
        viewExpenseIFLayout.setHorizontalGroup(
            viewExpenseIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewExpenseIFLayout.createSequentialGroup()
                .addGap(355, 355, 355)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(364, Short.MAX_VALUE))
        );
        viewExpenseIFLayout.setVerticalGroup(
            viewExpenseIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewExpenseIFLayout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(jPanel37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        jDesktopPane1.add(viewExpenseIF);
        viewExpenseIF.setBounds(0, 0, 1160, 560);

        viewMonthlyBusinessIF.setTitle("View Monthly Business and Profit Report");

        jPanel40.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "View Monthly Business & Profit", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel41.setText("Month");

        reMonthlyBusinessMonthCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "Octomber", "November", "December" }));

        jLabel42.setText("Year");

        reMonthlyBusinessYearCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2008", "2009", "2010", "2011", "2012", "2013", " " }));
        reMonthlyBusinessYearCb.setSelectedIndex(1);

        jButton15.setText("Generate Report");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel40Layout = new javax.swing.GroupLayout(jPanel40);
        jPanel40.setLayout(jPanel40Layout);
        jPanel40Layout.setHorizontalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel41)
                .addGap(18, 18, 18)
                .addComponent(reMonthlyBusinessMonthCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel42)
                .addGap(18, 18, 18)
                .addComponent(reMonthlyBusinessYearCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(jButton15)
                .addGap(95, 95, 95))
        );
        jPanel40Layout.setVerticalGroup(
            jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel40Layout.createSequentialGroup()
                .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel40Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel42)
                            .addComponent(jLabel41))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel40Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel40Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reMonthlyBusinessMonthCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reMonthlyBusinessYearCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)))
                .addComponent(jButton15)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout jPanel39Layout = new javax.swing.GroupLayout(jPanel39);
        jPanel39.setLayout(jPanel39Layout);
        jPanel39Layout.setHorizontalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel39Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel39Layout.setVerticalGroup(
            jPanel39Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel39Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jPanel40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout viewMonthlyBusinessIFLayout = new javax.swing.GroupLayout(viewMonthlyBusinessIF.getContentPane());
        viewMonthlyBusinessIF.getContentPane().setLayout(viewMonthlyBusinessIFLayout);
        viewMonthlyBusinessIFLayout.setHorizontalGroup(
            viewMonthlyBusinessIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewMonthlyBusinessIFLayout.createSequentialGroup()
                .addGap(355, 355, 355)
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(364, Short.MAX_VALUE))
        );
        viewMonthlyBusinessIFLayout.setVerticalGroup(
            viewMonthlyBusinessIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewMonthlyBusinessIFLayout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(jPanel39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        jDesktopPane1.add(viewMonthlyBusinessIF);
        viewMonthlyBusinessIF.setBounds(0, 0, 1160, 560);

        viewMonthlyBusinessForCashierIF.setTitle("View Monthly Business Report");

        jPanel44.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "View Monthly Business", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel46.setText("Month");

        reMonthlyBusinessMonthCb1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "Octomber", "November", "December" }));

        jLabel47.setText("Year");

        reMonthlyBusinessYearCb1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2008", "2009", "2010", "2011", "2012", "2013", " " }));
        reMonthlyBusinessYearCb1.setSelectedIndex(1);

        jButton17.setText("Generate Report");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel44Layout = new javax.swing.GroupLayout(jPanel44);
        jPanel44.setLayout(jPanel44Layout);
        jPanel44Layout.setHorizontalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel46)
                .addGap(18, 18, 18)
                .addComponent(reMonthlyBusinessMonthCb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel47)
                .addGap(18, 18, 18)
                .addComponent(reMonthlyBusinessYearCb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(jButton17)
                .addGap(95, 95, 95))
        );
        jPanel44Layout.setVerticalGroup(
            jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel44Layout.createSequentialGroup()
                .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel44Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel47)
                            .addComponent(jLabel46))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel44Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel44Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(reMonthlyBusinessMonthCb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(reMonthlyBusinessYearCb1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)))
                .addComponent(jButton17)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout jPanel43Layout = new javax.swing.GroupLayout(jPanel43);
        jPanel43.setLayout(jPanel43Layout);
        jPanel43Layout.setHorizontalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel43Layout.createSequentialGroup()
                .addGap(47, 47, 47)
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(48, Short.MAX_VALUE))
        );
        jPanel43Layout.setVerticalGroup(
            jPanel43Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel43Layout.createSequentialGroup()
                .addContainerGap(23, Short.MAX_VALUE)
                .addComponent(jPanel44, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19))
        );

        javax.swing.GroupLayout viewMonthlyBusinessForCashierIFLayout = new javax.swing.GroupLayout(viewMonthlyBusinessForCashierIF.getContentPane());
        viewMonthlyBusinessForCashierIF.getContentPane().setLayout(viewMonthlyBusinessForCashierIFLayout);
        viewMonthlyBusinessForCashierIFLayout.setHorizontalGroup(
            viewMonthlyBusinessForCashierIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewMonthlyBusinessForCashierIFLayout.createSequentialGroup()
                .addGap(355, 355, 355)
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(364, Short.MAX_VALUE))
        );
        viewMonthlyBusinessForCashierIFLayout.setVerticalGroup(
            viewMonthlyBusinessForCashierIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewMonthlyBusinessForCashierIFLayout.createSequentialGroup()
                .addGap(131, 131, 131)
                .addComponent(jPanel43, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(178, Short.MAX_VALUE))
        );

        jDesktopPane1.add(viewMonthlyBusinessForCashierIF);
        viewMonthlyBusinessForCashierIF.setBounds(0, 0, 1160, 560);

        dailtItemBusinessIF.setTitle("Daily Sold Item Summary");

        jPanel13.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "Daily Item Summary", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel53.setText("Date");

        jButton23.setText("View Report");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(87, 87, 87)
                .addComponent(jLabel53)
                .addGap(42, 42, 42)
                .addComponent(dailyBusinesItemsDf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(123, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel13Layout.createSequentialGroup()
                .addContainerGap(179, Short.MAX_VALUE)
                .addComponent(jButton23)
                .addGap(160, 160, 160))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel13Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(dailyBusinesItemsDf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel53))
                .addGap(35, 35, 35)
                .addComponent(jButton23)
                .addContainerGap(63, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout dailtItemBusinessIFLayout = new javax.swing.GroupLayout(dailtItemBusinessIF.getContentPane());
        dailtItemBusinessIF.getContentPane().setLayout(dailtItemBusinessIFLayout);
        dailtItemBusinessIFLayout.setHorizontalGroup(
            dailtItemBusinessIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dailtItemBusinessIFLayout.createSequentialGroup()
                .addGap(337, 337, 337)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(363, Short.MAX_VALUE))
        );
        dailtItemBusinessIFLayout.setVerticalGroup(
            dailtItemBusinessIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(dailtItemBusinessIFLayout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jPanel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );

        jDesktopPane1.add(dailtItemBusinessIF);
        dailtItemBusinessIF.setBounds(0, 0, 1160, 560);

        updateCashAmount.setTitle("Update Cash Amount");

        jPanel22.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Update Cash Amount", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel58.setText("Cash Amount");

        jButton24.setText("Update");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel22Layout = new javax.swing.GroupLayout(jPanel22);
        jPanel22.setLayout(jPanel22Layout);
        jPanel22Layout.setHorizontalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addContainerGap(46, Short.MAX_VALUE)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addComponent(jLabel58, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(updateCashTf, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(78, 78, 78))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel22Layout.createSequentialGroup()
                        .addComponent(jButton24)
                        .addGap(126, 126, 126))))
        );
        jPanel22Layout.setVerticalGroup(
            jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel22Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(jPanel22Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(updateCashTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel58))
                .addGap(18, 18, 18)
                .addComponent(jButton24)
                .addGap(54, 54, 54))
        );

        javax.swing.GroupLayout updateCashAmountLayout = new javax.swing.GroupLayout(updateCashAmount.getContentPane());
        updateCashAmount.getContentPane().setLayout(updateCashAmountLayout);
        updateCashAmountLayout.setHorizontalGroup(
            updateCashAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, updateCashAmountLayout.createSequentialGroup()
                .addContainerGap(391, Short.MAX_VALUE)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(373, 373, 373))
        );
        updateCashAmountLayout.setVerticalGroup(
            updateCashAmountLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(updateCashAmountLayout.createSequentialGroup()
                .addGap(167, 167, 167)
                .addComponent(jPanel22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(186, Short.MAX_VALUE))
        );

        jDesktopPane1.add(updateCashAmount);
        updateCashAmount.setBounds(0, 0, 1160, 560);

        customerExchangeIF.setTitle("Customer Return Exchange");

        jPanel31.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Customer Return Exchange", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel59.setText("Customer Invoice Id");

        jLabel60.setText("Return Item Code");

        jLabel61.setText("No of Items");

        exchangeNoofItemTf.setText("1");

        jLabel62.setText("New Item Code");

        jButton26.setText("Exchange");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        Cancel.setText("Cancel");
        Cancel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel60)
                    .addComponent(jLabel59)
                    .addComponent(jLabel62)
                    .addComponent(jLabel61))
                .addGap(30, 30, 30)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jButton26)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Cancel))
                    .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(exInvoiceIdTf)
                        .addComponent(exchangeNoofItemTf)
                        .addComponent(exchangeNewItemCodeTf)
                        .addComponent(exchangeItemCodeTf, javax.swing.GroupLayout.DEFAULT_SIZE, 199, Short.MAX_VALUE)))
                .addContainerGap(45, Short.MAX_VALUE))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exInvoiceIdTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel59))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exchangeItemCodeTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel60))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exchangeNewItemCodeTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel62))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(exchangeNoofItemTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel61))
                .addGap(26, 26, 26)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(Cancel)
                    .addComponent(jButton26))
                .addContainerGap(39, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout customerExchangeIFLayout = new javax.swing.GroupLayout(customerExchangeIF.getContentPane());
        customerExchangeIF.getContentPane().setLayout(customerExchangeIFLayout);
        customerExchangeIFLayout.setHorizontalGroup(
            customerExchangeIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerExchangeIFLayout.createSequentialGroup()
                .addGap(370, 370, 370)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(328, Short.MAX_VALUE))
        );
        customerExchangeIFLayout.setVerticalGroup(
            customerExchangeIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(customerExchangeIFLayout.createSequentialGroup()
                .addGap(151, 151, 151)
                .addComponent(jPanel31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(131, Short.MAX_VALUE))
        );

        jDesktopPane1.add(customerExchangeIF);
        customerExchangeIF.setBounds(0, 0, 1160, 560);

        storesIF.setResizable(true);
        storesIF.setTitle("Stores Management");

        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Stores management", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel63.setText("Item Code");

        jLabel64.setText("Item Name ");

        jLabel65.setText("Purchase Date");

        itemNameStMgtTf.setEditable(false);

        jLabel66.setText("Purchase Quantity");

        jLabel67.setText("Rejected Quantity");

        jLabel68.setText("Accepted Quantity");

        jLabel69.setText("Sell Quantity");

        jButton3.setText("Save");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton27.setText("Cancel");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        itemCodeStMgtCb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                itemCodeStMgtCbItemStateChanged(evt);
            }
        });

        jButton29.setText("Serach");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jLabel71.setText("Avilable Total Quantity");

        jButton30.setText("Search");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jLabel80.setText("Supplier");

        storesMgtSupplierCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-" }));
        storesMgtSupplierCb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                storesMgtSupplierCbItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel32Layout = new javax.swing.GroupLayout(jPanel32);
        jPanel32.setLayout(jPanel32Layout);
        jPanel32Layout.setHorizontalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel63)
                            .addComponent(jLabel64)
                            .addComponent(jLabel65)
                            .addComponent(jLabel66)
                            .addComponent(jLabel68)
                            .addComponent(jLabel69)
                            .addComponent(jLabel71)
                            .addComponent(jLabel67)
                            .addComponent(jLabel80))
                        .addGap(48, 48, 48)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(storesMgtSupplierCb, 0, 205, Short.MAX_VALUE)
                            .addComponent(itemCodeStMgtCb, 0, 205, Short.MAX_VALUE)
                            .addComponent(sellTf, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(purchaseDateStMgtDc, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(acceptedTf, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(rejectedTf, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(purchaseQuantityTf, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(itemNameStMgtTf, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE)
                            .addComponent(avilableQyantityTf, javax.swing.GroupLayout.DEFAULT_SIZE, 205, Short.MAX_VALUE))
                        .addGap(29, 29, 29)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton30, 0, 1, Short.MAX_VALUE)
                            .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGap(248, 248, 248)
                        .addComponent(jButton3)
                        .addGap(26, 26, 26)
                        .addComponent(jButton27)))
                .addContainerGap())
        );
        jPanel32Layout.setVerticalGroup(
            jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel32Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel80)
                    .addComponent(storesMgtSupplierCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton29)
                    .addGroup(jPanel32Layout.createSequentialGroup()
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel63)
                            .addComponent(itemCodeStMgtCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel64)
                            .addComponent(itemNameStMgtTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel65)
                            .addComponent(purchaseDateStMgtDc, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel66)
                    .addComponent(purchaseQuantityTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(rejectedTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel67))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel68)
                    .addComponent(acceptedTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel69)
                    .addComponent(sellTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(avilableQyantityTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton30))
                    .addComponent(jLabel71))
                .addGap(27, 27, 27)
                .addGroup(jPanel32Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton27)
                    .addComponent(jButton3))
                .addContainerGap())
        );

        javax.swing.GroupLayout storesIFLayout = new javax.swing.GroupLayout(storesIF.getContentPane());
        storesIF.getContentPane().setLayout(storesIFLayout);
        storesIFLayout.setHorizontalGroup(
            storesIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, storesIFLayout.createSequentialGroup()
                .addContainerGap(308, Short.MAX_VALUE)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(294, 294, 294))
        );
        storesIFLayout.setVerticalGroup(
            storesIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(storesIFLayout.createSequentialGroup()
                .addGap(78, 78, 78)
                .addComponent(jPanel32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        jDesktopPane1.add(storesIF);
        storesIF.setBounds(0, 0, 1160, 560);

        viewStoresMgtIF.setTitle("View Stores Management");

        jPanel34.setBorder(javax.swing.BorderFactory.createTitledBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED), "View Stores Management", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 11))); // NOI18N

        jLabel70.setText("Date");

        jButton28.setText("View Report");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jLabel72.setText("Item Code");

        viewStMgtCb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                viewStMgtCbItemStateChanged(evt);
            }
        });

        jLabel73.setText("Item Name");

        byDateChBx.setSelected(true);
        byDateChBx.setText("By Date");
        byDateChBx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byDateChBxActionPerformed(evt);
            }
        });

        byCodeChbx.setText("By Item Code");
        byCodeChbx.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                byCodeChbxActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel34Layout = new javax.swing.GroupLayout(jPanel34);
        jPanel34.setLayout(jPanel34Layout);
        jPanel34Layout.setHorizontalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel70)
                .addGap(18, 18, 18)
                .addComponent(viewStoresMgtDf, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel72)
                    .addComponent(jLabel73))
                .addGap(26, 26, 26)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(viewStMgtCb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(viewStMgtTf, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE))
                .addGap(62, 62, 62))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel34Layout.createSequentialGroup()
                .addContainerGap(226, Short.MAX_VALUE)
                .addComponent(jButton28)
                .addGap(226, 226, 226))
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addComponent(byDateChBx)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 196, Short.MAX_VALUE)
                .addComponent(byCodeChbx)
                .addGap(103, 103, 103))
        );
        jPanel34Layout.setVerticalGroup(
            jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel34Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(byDateChBx)
                    .addComponent(byCodeChbx))
                .addGap(18, 18, 18)
                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addComponent(viewStoresMgtDf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel34Layout.createSequentialGroup()
                        .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel34Layout.createSequentialGroup()
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel72)
                                    .addComponent(viewStMgtCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel34Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel73)
                                    .addComponent(viewStMgtTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jLabel70))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 49, Short.MAX_VALUE)
                        .addComponent(jButton28)
                        .addGap(39, 39, 39))))
        );

        javax.swing.GroupLayout viewStoresMgtIFLayout = new javax.swing.GroupLayout(viewStoresMgtIF.getContentPane());
        viewStoresMgtIF.getContentPane().setLayout(viewStoresMgtIFLayout);
        viewStoresMgtIFLayout.setHorizontalGroup(
            viewStoresMgtIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewStoresMgtIFLayout.createSequentialGroup()
                .addGap(337, 337, 337)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(250, Short.MAX_VALUE))
        );
        viewStoresMgtIFLayout.setVerticalGroup(
            viewStoresMgtIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(viewStoresMgtIFLayout.createSequentialGroup()
                .addGap(135, 135, 135)
                .addComponent(jPanel34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(144, Short.MAX_VALUE))
        );

        jDesktopPane1.add(viewStoresMgtIF);
        viewStoresMgtIF.setBounds(0, 0, 1160, 560);

        newItemTypeIF.setTitle("New Item Type");

        jPanel45.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Add New Item Type"));

        jLabel74.setText("Type Name");

        itemTypeSaveBtn.setText("Save");
        itemTypeSaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTypeSaveBtnActionPerformed(evt);
            }
        });

        itemTypeCancelBtn.setText("Cancel");
        itemTypeCancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemTypeCancelBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel45Layout = new javax.swing.GroupLayout(jPanel45);
        jPanel45.setLayout(jPanel45Layout);
        jPanel45Layout.setHorizontalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jLabel74, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel45Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addComponent(itemTypeSaveBtn)
                        .addGap(36, 36, 36)
                        .addComponent(itemTypeCancelBtn))
                    .addComponent(itemTypeNameTf, javax.swing.GroupLayout.PREFERRED_SIZE, 296, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(167, Short.MAX_VALUE))
        );
        jPanel45Layout.setVerticalGroup(
            jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel45Layout.createSequentialGroup()
                .addGap(68, 68, 68)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel74)
                    .addComponent(itemTypeNameTf, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(jPanel45Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(itemTypeSaveBtn)
                    .addComponent(itemTypeCancelBtn))
                .addContainerGap(143, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout newItemTypeIFLayout = new javax.swing.GroupLayout(newItemTypeIF.getContentPane());
        newItemTypeIF.getContentPane().setLayout(newItemTypeIFLayout);
        newItemTypeIFLayout.setHorizontalGroup(
            newItemTypeIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, newItemTypeIFLayout.createSequentialGroup()
                .addContainerGap(229, Short.MAX_VALUE)
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(228, 228, 228))
        );
        newItemTypeIFLayout.setVerticalGroup(
            newItemTypeIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(newItemTypeIFLayout.createSequentialGroup()
                .addGap(101, 101, 101)
                .addComponent(jPanel45, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(89, Short.MAX_VALUE))
        );

        jDesktopPane1.add(newItemTypeIF);
        newItemTypeIF.setBounds(0, 0, 1160, 550);

        editItemTypeIF.setTitle("Edit ItemType");

        jPanel46.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel75.setText("Type Name");

        jLabel76.setText("New Type Name");

        editNewTypeNameTF.setText("Save");
        editNewTypeNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editNewTypeNameTFActionPerformed(evt);
            }
        });

        jButton31.setText("Cancel");

        javax.swing.GroupLayout jPanel46Layout = new javax.swing.GroupLayout(jPanel46);
        jPanel46.setLayout(jPanel46Layout);
        jPanel46Layout.setHorizontalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(58, 58, 58)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel75)
                    .addComponent(jLabel76))
                .addGap(26, 26, 26)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel46Layout.createSequentialGroup()
                        .addComponent(editNewTypeNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(47, 47, 47)
                        .addComponent(jButton31, javax.swing.GroupLayout.PREFERRED_SIZE, 113, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(newTypeNameTF)
                        .addComponent(editOldTypeNameCb, 0, 382, Short.MAX_VALUE)))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        jPanel46Layout.setVerticalGroup(
            jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel46Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel75)
                    .addComponent(editOldTypeNameCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel76)
                    .addComponent(newTypeNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(45, 45, 45)
                .addGroup(jPanel46Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editNewTypeNameTF)
                    .addComponent(jButton31))
                .addContainerGap(71, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout editItemTypeIFLayout = new javax.swing.GroupLayout(editItemTypeIF.getContentPane());
        editItemTypeIF.getContentPane().setLayout(editItemTypeIFLayout);
        editItemTypeIFLayout.setHorizontalGroup(
            editItemTypeIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editItemTypeIFLayout.createSequentialGroup()
                .addGap(201, 201, 201)
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(330, Short.MAX_VALUE))
        );
        editItemTypeIFLayout.setVerticalGroup(
            editItemTypeIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editItemTypeIFLayout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(jPanel46, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(197, Short.MAX_VALUE))
        );

        jDesktopPane1.add(editItemTypeIF);
        editItemTypeIF.setBounds(0, 0, 1160, 560);

        addSupplierIF.setTitle("Add Supplier");

        jPanel52.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Add Supplier"));

        addSupplierLB.setText("Supplier Code");

        jLabel81.setText("Supplier Name");

        addSupplierNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSupplierNameTFActionPerformed(evt);
            }
        });

        addSupplierContactNumberLB.setText("Contact Number");

        addSupplierSaveBtn.setText("Save");
        addSupplierSaveBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSupplierSaveBtnActionPerformed(evt);
            }
        });

        addSupplierCancelBtn.setText("Cancel");
        addSupplierCancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSupplierCancelBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel52Layout = new javax.swing.GroupLayout(jPanel52);
        jPanel52.setLayout(jPanel52Layout);
        jPanel52Layout.setHorizontalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addComponent(addSupplierContactNumberLB)
                        .addGap(18, 18, 18)
                        .addComponent(addSupplierContactNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel52Layout.createSequentialGroup()
                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addSupplierLB)
                            .addComponent(jLabel81))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addSuuplierCodeTF)
                            .addComponent(addSupplierNameTF))))
                .addGap(98, 98, 98))
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(addSupplierSaveBtn)
                .addGap(105, 105, 105)
                .addComponent(addSupplierCancelBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel52Layout.setVerticalGroup(
            jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel52Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSupplierLB)
                    .addComponent(addSuuplierCodeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel81)
                    .addComponent(addSupplierNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSupplierContactNumberLB)
                    .addComponent(addSupplierContactNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(jPanel52Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSupplierSaveBtn)
                    .addComponent(addSupplierCancelBtn))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout addSupplierIFLayout = new javax.swing.GroupLayout(addSupplierIF.getContentPane());
        addSupplierIF.getContentPane().setLayout(addSupplierIFLayout);
        addSupplierIFLayout.setHorizontalGroup(
            addSupplierIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(addSupplierIFLayout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(274, Short.MAX_VALUE))
        );
        addSupplierIFLayout.setVerticalGroup(
            addSupplierIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, addSupplierIFLayout.createSequentialGroup()
                .addContainerGap(68, Short.MAX_VALUE)
                .addComponent(jPanel52, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );

        jDesktopPane1.add(addSupplierIF);
        addSupplierIF.setBounds(0, 0, 1160, 560);

        editDeleteSupplierIF.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        editDeleteSupplierIF.setTitle("Edit Delete Supplier");

        jPanel53.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Edit Delete Supplier"));

        addSupplierLB1.setText("Supplier Code");

        jLabel82.setText("Supplier Name");

        editDeleteSupplierNameTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editDeleteSupplierNameTFActionPerformed(evt);
            }
        });

        addSupplierContactNumberLB1.setText("Contact Number");

        editSupplierBtn.setText("Edit");
        editSupplierBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSupplierBtnActionPerformed(evt);
            }
        });

        editDelteSupplierCancelBtn.setText("Cancel");
        editDelteSupplierCancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editDelteSupplierCancelBtnActionPerformed(evt);
            }
        });

        deleteSupplierBtn.setText("Delete");
        deleteSupplierBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteSupplierBtnActionPerformed(evt);
            }
        });

        editDeleteSupplierCodeCb.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                editDeleteSupplierCodeCbItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout jPanel53Layout = new javax.swing.GroupLayout(jPanel53);
        jPanel53.setLayout(jPanel53Layout);
        jPanel53Layout.setHorizontalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel53Layout.createSequentialGroup()
                        .addComponent(addSupplierContactNumberLB1)
                        .addGap(18, 18, 18)
                        .addComponent(editDeleteSupplierContactNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel53Layout.createSequentialGroup()
                        .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(addSupplierLB1)
                            .addComponent(jLabel82))
                        .addGap(27, 27, 27)
                        .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(editDeleteSupplierCodeCb, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(editDeleteSupplierNameTF))))
                .addGap(98, 98, 98))
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addGap(217, 217, 217)
                .addComponent(editSupplierBtn)
                .addGap(16, 16, 16)
                .addComponent(deleteSupplierBtn)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(editDelteSupplierCancelBtn)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel53Layout.setVerticalGroup(
            jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel53Layout.createSequentialGroup()
                .addGap(82, 82, 82)
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSupplierLB1)
                    .addComponent(editDeleteSupplierCodeCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(26, 26, 26)
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel82)
                    .addComponent(editDeleteSupplierNameTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(addSupplierContactNumberLB1)
                    .addComponent(editDeleteSupplierContactNumberTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(62, 62, 62)
                .addGroup(jPanel53Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editSupplierBtn)
                    .addComponent(editDelteSupplierCancelBtn)
                    .addComponent(deleteSupplierBtn))
                .addContainerGap(69, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout editDeleteSupplierIFLayout = new javax.swing.GroupLayout(editDeleteSupplierIF.getContentPane());
        editDeleteSupplierIF.getContentPane().setLayout(editDeleteSupplierIFLayout);
        editDeleteSupplierIFLayout.setHorizontalGroup(
            editDeleteSupplierIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(editDeleteSupplierIFLayout.createSequentialGroup()
                .addGap(210, 210, 210)
                .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(288, Short.MAX_VALUE))
        );
        editDeleteSupplierIFLayout.setVerticalGroup(
            editDeleteSupplierIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, editDeleteSupplierIFLayout.createSequentialGroup()
                .addContainerGap(70, Short.MAX_VALUE)
                .addComponent(jPanel53, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(97, 97, 97))
        );

        jDesktopPane1.add(editDeleteSupplierIF);
        editDeleteSupplierIF.setBounds(0, 0, 1160, 560);

        itemRejectionReportIF.setTitle("Item Rejection Report");

        jPanel54.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Item Rejection Report"));

        jLabel83.setText("Item ");

        jLabel86.setText("Month");

        itemRejectionReportMonthCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "Octomber", "November", "December" }));

        jLabel87.setText("Year");

        itemRejectionReportYearCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2008", "2009", "2010", "2011", "2012", "2013", " " }));
        itemRejectionReportYearCb.setSelectedIndex(3);

        itemRectionReportBtn.setText("Generate Report");
        itemRectionReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRectionReportBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel56Layout = new javax.swing.GroupLayout(jPanel56);
        jPanel56.setLayout(jPanel56Layout);
        jPanel56Layout.setHorizontalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel86)
                .addGap(18, 18, 18)
                .addComponent(itemRejectionReportMonthCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel87)
                .addGap(18, 18, 18)
                .addComponent(itemRejectionReportYearCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel56Layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(itemRectionReportBtn)
                .addGap(95, 95, 95))
        );
        jPanel56Layout.setVerticalGroup(
            jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel56Layout.createSequentialGroup()
                .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel56Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel87)
                            .addComponent(jLabel86))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel56Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel56Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(itemRejectionReportMonthCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(itemRejectionReportYearCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)))
                .addComponent(itemRectionReportBtn)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout jPanel54Layout = new javax.swing.GroupLayout(jPanel54);
        jPanel54.setLayout(jPanel54Layout);
        jPanel54Layout.setHorizontalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel83)
                        .addGap(37, 37, 37)
                        .addComponent(itemRejectionReportItemCodeCb, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel54Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel54Layout.setVerticalGroup(
            jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel54Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(jPanel54Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel83)
                    .addComponent(itemRejectionReportItemCodeCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel56, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout itemRejectionReportIFLayout = new javax.swing.GroupLayout(itemRejectionReportIF.getContentPane());
        itemRejectionReportIF.getContentPane().setLayout(itemRejectionReportIFLayout);
        itemRejectionReportIFLayout.setHorizontalGroup(
            itemRejectionReportIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, itemRejectionReportIFLayout.createSequentialGroup()
                .addContainerGap(309, Short.MAX_VALUE)
                .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(164, 164, 164))
        );
        itemRejectionReportIFLayout.setVerticalGroup(
            itemRejectionReportIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(itemRejectionReportIFLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jPanel54, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        jDesktopPane1.add(itemRejectionReportIF);
        itemRejectionReportIF.setBounds(0, 0, 1160, 560);

        supplierRejectionReportIF.setTitle("Supplier Rejection Report");

        jPanel55.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Supplier Rejection Report"));

        jLabel84.setText("Supplier");

        jLabel88.setText("Month");

        supplierRejectionReportMonthCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "Octomber", "November", "December" }));

        jLabel89.setText("Year");

        supplierRejectionReportYearCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2008", "2009", "2010", "2011", "2012", "2013", " " }));
        supplierRejectionReportYearCb.setSelectedIndex(3);

        supplierRectionReportBtn.setText("Generate Report");
        supplierRectionReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierRectionReportBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel57Layout = new javax.swing.GroupLayout(jPanel57);
        jPanel57.setLayout(jPanel57Layout);
        jPanel57Layout.setHorizontalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel88)
                .addGap(18, 18, 18)
                .addComponent(supplierRejectionReportMonthCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel89)
                .addGap(18, 18, 18)
                .addComponent(supplierRejectionReportYearCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel57Layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(supplierRectionReportBtn)
                .addGap(95, 95, 95))
        );
        jPanel57Layout.setVerticalGroup(
            jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel57Layout.createSequentialGroup()
                .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel57Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel89)
                            .addComponent(jLabel88))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel57Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel57Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(supplierRejectionReportMonthCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(supplierRejectionReportYearCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)))
                .addComponent(supplierRectionReportBtn)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout jPanel55Layout = new javax.swing.GroupLayout(jPanel55);
        jPanel55.setLayout(jPanel55Layout);
        jPanel55Layout.setHorizontalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel55Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel84)
                        .addGap(37, 37, 37)
                        .addComponent(supplierRejectionReportSupplierCodeCb, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel55Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel55Layout.setVerticalGroup(
            jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel55Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(jPanel55Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel84)
                    .addComponent(supplierRejectionReportSupplierCodeCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel57, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout supplierRejectionReportIFLayout = new javax.swing.GroupLayout(supplierRejectionReportIF.getContentPane());
        supplierRejectionReportIF.getContentPane().setLayout(supplierRejectionReportIFLayout);
        supplierRejectionReportIFLayout.setHorizontalGroup(
            supplierRejectionReportIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, supplierRejectionReportIFLayout.createSequentialGroup()
                .addContainerGap(297, Short.MAX_VALUE)
                .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(164, 164, 164))
        );
        supplierRejectionReportIFLayout.setVerticalGroup(
            supplierRejectionReportIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(supplierRejectionReportIFLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jPanel55, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        jDesktopPane1.add(supplierRejectionReportIF);
        supplierRejectionReportIF.setBounds(0, 0, 1160, 560);

        supplierDeliveredReportIF.setTitle("Supplier Delivered Report");

        jPanel58.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED), "Supplier Delivered Report"));

        jLabel85.setText("Supplier");

        jLabel90.setText("Month");

        supplierDeliveredReportMonthCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "January", "Febuary", "March", "April", "May", "June", "July", "August", "September", "Octomber", "November", "December" }));

        jLabel91.setText("Year");

        supplierDeliveredReportYearCb.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "2008", "2009", "2010", "2011", "2012", "2013", " " }));
        supplierDeliveredReportYearCb.setSelectedIndex(3);

        supplierDeliveredReportBtn.setText("Generate Report");
        supplierDeliveredReportBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                supplierDeliveredReportBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel59Layout = new javax.swing.GroupLayout(jPanel59);
        jPanel59.setLayout(jPanel59Layout);
        jPanel59Layout.setHorizontalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addGap(29, 29, 29)
                .addComponent(jLabel90)
                .addGap(18, 18, 18)
                .addComponent(supplierDeliveredReportMonthCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addComponent(jLabel91)
                .addGap(18, 18, 18)
                .addComponent(supplierDeliveredReportYearCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(45, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel59Layout.createSequentialGroup()
                .addContainerGap(110, Short.MAX_VALUE)
                .addComponent(supplierDeliveredReportBtn)
                .addGap(95, 95, 95))
        );
        jPanel59Layout.setVerticalGroup(
            jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel59Layout.createSequentialGroup()
                .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel59Layout.createSequentialGroup()
                        .addGap(35, 35, 35)
                        .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel91)
                            .addComponent(jLabel90))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 43, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel59Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel59Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(supplierDeliveredReportMonthCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(supplierDeliveredReportYearCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(35, 35, 35)))
                .addComponent(supplierDeliveredReportBtn)
                .addGap(39, 39, 39))
        );

        javax.swing.GroupLayout jPanel58Layout = new javax.swing.GroupLayout(jPanel58);
        jPanel58.setLayout(jPanel58Layout);
        jPanel58Layout.setHorizontalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel58Layout.createSequentialGroup()
                        .addGap(83, 83, 83)
                        .addComponent(jLabel85)
                        .addGap(37, 37, 37)
                        .addComponent(supplierDeliveredReportSupplierCodeCb, javax.swing.GroupLayout.PREFERRED_SIZE, 308, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel58Layout.createSequentialGroup()
                        .addGap(136, 136, 136)
                        .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(205, Short.MAX_VALUE))
        );
        jPanel58Layout.setVerticalGroup(
            jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel58Layout.createSequentialGroup()
                .addGap(90, 90, 90)
                .addGroup(jPanel58Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel85)
                    .addComponent(supplierDeliveredReportSupplierCodeCb, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jPanel59, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout supplierDeliveredReportIFLayout = new javax.swing.GroupLayout(supplierDeliveredReportIF.getContentPane());
        supplierDeliveredReportIF.getContentPane().setLayout(supplierDeliveredReportIFLayout);
        supplierDeliveredReportIFLayout.setHorizontalGroup(
            supplierDeliveredReportIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, supplierDeliveredReportIFLayout.createSequentialGroup()
                .addContainerGap(297, Short.MAX_VALUE)
                .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(164, 164, 164))
        );
        supplierDeliveredReportIFLayout.setVerticalGroup(
            supplierDeliveredReportIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(supplierDeliveredReportIFLayout.createSequentialGroup()
                .addGap(96, 96, 96)
                .addComponent(jPanel58, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(97, Short.MAX_VALUE))
        );

        jDesktopPane1.add(supplierDeliveredReportIF);
        supplierDeliveredReportIF.setBounds(0, 0, 1160, 560);

        salesRefIF.setTitle("New Sales Ref");
        salesRefIF.setVisible(false);

        editSalesRefTB.setBackground(new java.awt.Color(204, 204, 255));
        editSalesRefTB.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel92.setText("Sales Ref");

        saveSalesRefBtn.setText("Save");
        saveSalesRefBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                saveSalesRefBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel61Layout = new javax.swing.GroupLayout(jPanel61);
        jPanel61.setLayout(jPanel61Layout);
        jPanel61Layout.setHorizontalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel61Layout.createSequentialGroup()
                        .addGap(92, 92, 92)
                        .addComponent(jLabel92, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(newSalesRefTF, javax.swing.GroupLayout.PREFERRED_SIZE, 292, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel61Layout.createSequentialGroup()
                        .addGap(298, 298, 298)
                        .addComponent(saveSalesRefBtn)))
                .addContainerGap(216, Short.MAX_VALUE))
        );
        jPanel61Layout.setVerticalGroup(
            jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel61Layout.createSequentialGroup()
                .addGap(86, 86, 86)
                .addGroup(jPanel61Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel92)
                    .addComponent(newSalesRefTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(saveSalesRefBtn)
                .addContainerGap(194, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel60Layout = new javax.swing.GroupLayout(jPanel60);
        jPanel60.setLayout(jPanel60Layout);
        jPanel60Layout.setHorizontalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );
        jPanel60Layout.setVerticalGroup(
            jPanel60Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel60Layout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addComponent(jPanel61, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(19, Short.MAX_VALUE))
        );

        editSalesRefTB.addTab("Add Sales Ref", jPanel60);

        jPanel62.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        editSalesRefNameLB.setText("Sales Ref Name");

        editSalesRefLoadUsersBtn.setText("Load Users");
        editSalesRefLoadUsersBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSalesRefLoadUsersBtnActionPerformed(evt);
            }
        });

        jLabel94.setText("User Status");

        editSalesRefStatusCB.setText("User Disable");
        editSalesRefStatusCB.setEnabled(false);

        editSalesRefUpdateBtn.setText("Update");
        editSalesRefUpdateBtn.setEnabled(false);
        editSalesRefUpdateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSalesRefUpdateBtnActionPerformed(evt);
            }
        });

        editSalesRefDeleteBtn.setText("Delete");
        editSalesRefDeleteBtn.setEnabled(false);
        editSalesRefDeleteBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editSalesRefDeleteBtnActionPerformed(evt);
            }
        });

        editSalesRefCB.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                editSalesRefCBItemStateChanged(evt);
            }
        });

        displayStatusBtn.setText("Display Status");
        displayStatusBtn.setEnabled(false);
        displayStatusBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                displayStatusBtnActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel62Layout = new javax.swing.GroupLayout(jPanel62);
        jPanel62.setLayout(jPanel62Layout);
        jPanel62Layout.setHorizontalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addGap(204, 204, 204)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editSalesRefNameLB)
                    .addComponent(jLabel94))
                .addGap(27, 27, 27)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(editSalesRefStatusCB, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel62Layout.createSequentialGroup()
                        .addComponent(editSalesRefUpdateBtn)
                        .addGap(53, 53, 53)
                        .addComponent(editSalesRefDeleteBtn))
                    .addComponent(editSalesRefCB, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(displayStatusBtn)
                .addContainerGap(168, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel62Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(editSalesRefLoadUsersBtn)
                .addGap(328, 328, 328))
        );
        jPanel62Layout.setVerticalGroup(
            jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel62Layout.createSequentialGroup()
                .addGap(80, 80, 80)
                .addComponent(editSalesRefLoadUsersBtn)
                .addGap(18, 18, 18)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editSalesRefNameLB)
                    .addComponent(editSalesRefCB, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(displayStatusBtn))
                .addGap(24, 24, 24)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel94)
                    .addComponent(editSalesRefStatusCB))
                .addGap(18, 18, 18)
                .addGroup(jPanel62Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(editSalesRefUpdateBtn)
                    .addComponent(editSalesRefDeleteBtn))
                .addContainerGap(143, Short.MAX_VALUE))
        );

        jTabbedPane2.addTab("", jPanel62);
        jPanel62.getAccessibleContext().setAccessibleParent(editSalesRefTB);

        editSalesRefTB.addTab("Edit Sales Ref", jTabbedPane2);
        jTabbedPane2.getAccessibleContext().setAccessibleName("");

        javax.swing.GroupLayout salesRefIFLayout = new javax.swing.GroupLayout(salesRefIF.getContentPane());
        salesRefIF.getContentPane().setLayout(salesRefIFLayout);
        salesRefIFLayout.setHorizontalGroup(
            salesRefIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salesRefIFLayout.createSequentialGroup()
                .addGap(158, 158, 158)
                .addComponent(editSalesRefTB, javax.swing.GroupLayout.PREFERRED_SIZE, 790, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(196, Short.MAX_VALUE))
        );
        salesRefIFLayout.setVerticalGroup(
            salesRefIFLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(salesRefIFLayout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(editSalesRefTB, javax.swing.GroupLayout.PREFERRED_SIZE, 435, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(58, Short.MAX_VALUE))
        );

        editSalesRefTB.getAccessibleContext().setAccessibleName("tab");

        jDesktopPane1.add(salesRefIF);
        salesRefIF.setBounds(0, 0, 1160, 560);

        jMenu1.setText("File");

        userAccountsMI.setText("User Accounts");

        jMenuItem2.setText("New User");
        jMenuItem2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem2ActionPerformed(evt);
            }
        });
        userAccountsMI.add(jMenuItem2);

        jMenuItem4.setText("Delete User");
        jMenuItem4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jMenuItem4MouseClicked(evt);
            }
            public void mouseReleased(java.awt.event.MouseEvent evt) {
                jMenuItem4MouseReleased(evt);
            }
        });
        jMenuItem4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem4ActionPerformed(evt);
            }
        });
        userAccountsMI.add(jMenuItem4);

        jMenuItem20.setText("New Sales Ref");
        userAccountsMI.add(jMenuItem20);

        jMenu1.add(userAccountsMI);

        openCashDrawyerMI.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F3, 0));
        openCashDrawyerMI.setText("Open Cash Drawyer");
        openCashDrawyerMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                openCashDrawyerMIActionPerformed(evt);
            }
        });
        jMenu1.add(openCashDrawyerMI);

        jMenuItem5.setText("Change Password");
        jMenuItem5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem5ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem5);

        jMenuItem11.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F5, 0));
        jMenuItem11.setText("Refresh");
        jMenuItem11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem11ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem11);

        logOffMI.setText("Log Off");
        logOffMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logOffMIActionPerformed(evt);
            }
        });
        jMenu1.add(logOffMI);

        jMenuItem7.setText("Exit");
        jMenuItem7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem7ActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItem7);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Items");
        jMenu2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu2ActionPerformed(evt);
            }
        });

        newItemMI.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        newItemMI.setText("New Item");
        newItemMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                newItemMIActionPerformed(evt);
            }
        });
        jMenu2.add(newItemMI);

        editItemMI.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_E, java.awt.event.InputEvent.CTRL_MASK));
        editItemMI.setText("Edit Item");
        editItemMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editItemMIActionPerformed(evt);
            }
        });
        jMenu2.add(editItemMI);

        jMenuItem10.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        jMenuItem10.setText("View Items");
        jMenuItem10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem10ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem10);

        itemCostMI.setText("Item Cost");
        itemCostMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemCostMIActionPerformed(evt);
            }
        });
        jMenu2.add(itemCostMI);

        jMenuItem14.setText("New Item Type");
        jMenuItem14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem14ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem14);

        jMenuItem15.setText("Edit Item Type");
        jMenuItem15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem15ActionPerformed(evt);
            }
        });
        jMenu2.add(jMenuItem15);

        itemRejectionsMI.setText("Item Rejection Report");
        itemRejectionsMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                itemRejectionsMIActionPerformed(evt);
            }
        });
        jMenu2.add(itemRejectionsMI);

        jMenuBar1.add(jMenu2);

        jMenu3.setText("Supplier");

        addSupplierMI.setText("Add Supplier");
        addSupplierMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addSupplierMIActionPerformed(evt);
            }
        });
        jMenu3.add(addSupplierMI);

        editDeleteSupplierMI.setText("Edit Delete Supplier");
        editDeleteSupplierMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editDeleteSupplierMIActionPerformed(evt);
            }
        });
        jMenu3.add(editDeleteSupplierMI);

        jMenuItem18.setText("Supplier Rejections Report");
        jMenuItem18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem18ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem18);

        jMenuItem19.setText("Supplier Reports");
        jMenuItem19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem19ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem19);

        jMenuItem17.setText("Supplier Delivered Report");
        jMenuItem17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem17ActionPerformed(evt);
            }
        });
        jMenu3.add(jMenuItem17);

        jMenuBar1.add(jMenu3);

        jMenu4.setText("Stores");
        jMenu4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu4ActionPerformed(evt);
            }
        });

        updateStoresMI.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_U, java.awt.event.InputEvent.CTRL_MASK));
        updateStoresMI.setText("Update Stores");
        updateStoresMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStoresMIActionPerformed(evt);
            }
        });
        jMenu4.add(updateStoresMI);

        jMenuItem6.setText("Refresh Stores");
        jMenuItem6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem6ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem6);

        jMenuItem12.setText("Stores Management");
        jMenuItem12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem12ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem12);

        jMenuItem13.setText("View Stores Management");
        jMenuItem13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem13ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem13);

        updateStoresReportMI.setText("Update Stores Report");
        updateStoresReportMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateStoresReportMIActionPerformed(evt);
            }
        });
        jMenu4.add(updateStoresReportMI);

        jMenuItem16.setText("ReOrderReport");
        jMenuItem16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem16ActionPerformed(evt);
            }
        });
        jMenu4.add(jMenuItem16);

        jMenuBar1.add(jMenu4);

        jMenu5.setText("Customer");
        jMenu5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu5ActionPerformed(evt);
            }
        });

        customerInvoiceMI.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        customerInvoiceMI.setText("New Customer Invoice");
        customerInvoiceMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                customerInvoiceMIActionPerformed(evt);
            }
        });
        jMenu5.add(customerInvoiceMI);

        editCustomerInvoiceMI.setText("Edit Customer Invoice");
        editCustomerInvoiceMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editCustomerInvoiceMIActionPerformed(evt);
            }
        });
        jMenu5.add(editCustomerInvoiceMI);

        jMenuItem22.setText("New Orders");
        jMenuItem22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem22ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem22);

        jMenuItem8.setText("Return Exchange");
        jMenuItem8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem8ActionPerformed(evt);
            }
        });
        jMenu5.add(jMenuItem8);

        jMenuBar1.add(jMenu5);

        jMenu7.setText("Cash Withdraw");
        jMenu7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenu7ActionPerformed(evt);
            }
        });

        jMenuItem23.setText("Cash Withdraw");
        jMenuItem23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem23ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem23);

        jMenuItem1.setText("View Cash Withdraw Summary");
        jMenuItem1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem1ActionPerformed(evt);
            }
        });
        jMenu7.add(jMenuItem1);

        updateCashAmountMI.setText("Update Cash Amount");
        updateCashAmountMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateCashAmountMIActionPerformed(evt);
            }
        });
        jMenu7.add(updateCashAmountMI);

        jMenuBar1.add(jMenu7);

        reportsJM.setText("Reports");
        reportsJM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                reportsJMActionPerformed(evt);
            }
        });

        monthBusinessProfitMI.setText("Monthly Business/Profit");
        monthBusinessProfitMI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                monthBusinessProfitMIActionPerformed(evt);
            }
        });
        reportsJM.add(monthBusinessProfitMI);

        jMenuItem3.setText("Monthly Business");
        jMenuItem3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem3ActionPerformed(evt);
            }
        });
        reportsJM.add(jMenuItem3);

        jMenuItem9.setText("Daily Item Business Report");
        jMenuItem9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItem9ActionPerformed(evt);
            }
        });
        reportsJM.add(jMenuItem9);

        jMenuItem21.setText("View Sales Point");
        reportsJM.add(jMenuItem21);

        jMenuBar1.add(reportsJM);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, 239, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1159, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jDesktopPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 564, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        
        addNewItems();
        
        
    }//GEN-LAST:event_jButton4ActionPerformed
    
    private void newItemCodeTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newItemCodeTfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_newItemCodeTfActionPerformed
    
    private void newItemBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newItemBtnActionPerformed
        
        newItemIF.setVisible(true);
        itemCostIF.setVisible(false);
        editItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        
        if (newItemTypeCb.getItemCount() == 0) {
            loadItemTypeForAddEditItem("newItem");
        }
        
        
    }//GEN-LAST:event_newItemBtnActionPerformed
    
    private void itemCostButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCostButtonActionPerformed
        
        if (icItemCodeCb.getItemCount() == 0) {
            
            loadItemCodesToItemCost();
            
        }
        
        itemCostIF.setVisible(true);
        editItemIF.setVisible(false);
        newItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        
    }//GEN-LAST:event_itemCostButtonActionPerformed
    
    private void newItemMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newItemMIActionPerformed
        
        newItemIF.setVisible(true);
        itemCostIF.setVisible(false);
        editItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        if (newItemTypeCb.getItemCount() == 0) {
            loadItemTypeForAddEditItem("newItem");
        }
        
    }//GEN-LAST:event_newItemMIActionPerformed
    
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        
        int status = JOptionPane.showConfirmDialog(this, "Are you sure want to Exit the System ?", "Confirm", JOptionPane.YES_NO_OPTION);
        
        if (status == 0) {
            
            System.exit(0);
        }
        
    }//GEN-LAST:event_jButton2ActionPerformed
    
    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        
        newItemCodeTf.setText("");
        newItemNameTf.setText("");
        newSalesPriceTf.setText("");
    }//GEN-LAST:event_jButton5ActionPerformed
    
    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        
        editItems();
        
        
        
    }//GEN-LAST:event_jButton7ActionPerformed
    
    private void editItemCodeCbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_editItemCodeCbItemStateChanged
        
        
        String selectItemValue = (String) editItemCodeCb.getSelectedItem();
        
        Integer itemVaue = new Integer(selectItemValue);
        
        Iterator it = itemLists.iterator();
        
        boolean isNotExist = false;
        
        while (it.hasNext()) {
            
            Items item = (Items) it.next();
            
            
            if (itemVaue.equals(item.getItemCode())) {
                
                editItemnameCb.setSelectedItem(item.getItemName());
                editSalesPricetf.setText(Double.toString(item.getSalesPrice()));
                editItemTypeCb.setSelectedItem(item.getItemType());
                
                isNotExist = false;
                break;
                
            } else {
                
                isNotExist = true;
            }
            
        }
        
        if (isNotExist) {
            
            JOptionPane.showMessageDialog(this, "Invalid Item Code", "Warn",
                    JOptionPane.WARNING_MESSAGE);
        }
        
        
        
    }//GEN-LAST:event_editItemCodeCbItemStateChanged
    
    private void editItemMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editItemMIActionPerformed
        
        if (editItemCodeCb.getItemCount() == 0) {
            
            loadItemCodes();
            
        }
        
        if (editItemTypeCb.getItemCount() == 0) {
            loadItemTypeForAddEditItem("editItem");
        }
        
        editItemIF.setVisible(true);
        newItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        itemCostIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
    }//GEN-LAST:event_editItemMIActionPerformed
    
    private void jMenu2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu2ActionPerformed
    }//GEN-LAST:event_jMenu2ActionPerformed
    
    private void editItemnameCbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_editItemnameCbItemStateChanged
//        String selectItemName = (String) editItemnameCb.getSelectedItem();
//
//        Iterator it = itemNameLists.iterator();
//
//        boolean isExist = false;
//
//        while (it.hasNext()) {
//
//            Items item = (Items) it.next();
//
//
//            if (selectItemName.equals(item.getItemName())) {
//
//
//                editItemCodeCb.setSelectedItem(item.getItemCode().toString());
//                editSalesPricetf.setText(Double.toString(item.getSalesPrice()));
//                editItemTypeCb.setSelectedItem(item.getItemType());
//
//
//                isExist = false;
//                break;
//
//            } else {
//
//                isExist = true;
//            }
//
//        }
//
//        if (isExist) {
//            JOptionPane.showMessageDialog(this, "Invalid Item Name", "Warn",
//                    JOptionPane.WARNING_MESSAGE);
//        }
    }//GEN-LAST:event_editItemnameCbItemStateChanged
    
    private void editItemnameCbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editItemnameCbKeyPressed
    }//GEN-LAST:event_editItemnameCbKeyPressed
    
    private void editSalesPricetfKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editSalesPricetfKeyReleased
        
        
        if (evt.getKeyCode() == 10) {
        }
    }//GEN-LAST:event_editSalesPricetfKeyReleased
    
    private void editItemnameCbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editItemnameCbKeyReleased
        
        if (evt.getKeyCode() == 10) {
        }
        
    }//GEN-LAST:event_editItemnameCbKeyReleased
    
    private void editItemnameCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editItemnameCbActionPerformed
    }//GEN-LAST:event_editItemnameCbActionPerformed
    
    private void jMenuItem10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem10ActionPerformed
        
        reportManager.getItemsViewReport();
    }//GEN-LAST:event_jMenuItem10ActionPerformed
    
    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        
        updateStores();
        
    }//GEN-LAST:event_jButton9ActionPerformed
    
    private void updateStoresBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStoresBtnActionPerformed
        
        updateStoresIF.setVisible(true);
        editItemIF.setVisible(false);
        newItemIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        itemCostIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
    }//GEN-LAST:event_updateStoresBtnActionPerformed
    
    private void updateStoresMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStoresMIActionPerformed
        
        updateStoresIF.setVisible(true);
        editItemIF.setVisible(false);
        newItemIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        itemCostIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        
    }//GEN-LAST:event_updateStoresMIActionPerformed
    
    private void jMenuItem2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem2ActionPerformed
        
        new CreateNewUserFrame(username, userType).setVisible(true);
        
    }//GEN-LAST:event_jMenuItem2ActionPerformed
    
    private void jMenuItem5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem5ActionPerformed
        
        new ChangePasswordFrame(username, password, mainJFrame).setVisible(true);
    }//GEN-LAST:event_jMenuItem5ActionPerformed
    
    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        
        upStoresQuantityTf.setText("");
        upStoresReOrderTf.setText("");
        
    }//GEN-LAST:event_jButton10ActionPerformed
    
    private void jButton7KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton7KeyPressed
        
        if (evt.getKeyCode() == 10) {
            editItems();
        }
    }//GEN-LAST:event_jButton7KeyPressed
    
    private void jButton4KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton4KeyPressed
        
        if (evt.getKeyCode() == 10) {
            addNewItems();
        }
        
    }//GEN-LAST:event_jButton4KeyPressed
    
    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        editSalesPricetf.setText("");
    }//GEN-LAST:event_jButton8ActionPerformed
    
    private void jButton9KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton9KeyPressed
        
        updateStores();
        
    }//GEN-LAST:event_jButton9KeyPressed
    
    private void jButton10KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton10KeyPressed
        
        upStoresQuantityTf.setText("");
        upStoresReOrderTf.setText("");
    }//GEN-LAST:event_jButton10KeyPressed
    
    private void invoiceItemCodeCbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_invoiceItemCodeCbItemStateChanged
        
        if (isItemAdd) {
            
            String selectItemValue = (String) invoiceItemCodeCb.getSelectedItem();
            
            if (selectItemValue.equals("") || isNumber(selectItemValue)) {
                
                if (!selectItemValue.equals("")) {
                    
                    Integer itemVaue = new Integer(selectItemValue);
                    
                    Iterator it = itemLists.iterator();
                    
                    boolean isItemCode = false;
                    
                    while (it.hasNext()) {
                        
                        Items item = (Items) it.next();
                        
                        
                        if (itemVaue.equals(item.getItemCode())) {
                            
                            itemNameLb.setText(item.getItemName());
                            salesPriceLb.setText(Double.toString(item.getSalesPrice()));
                            itemTypeLb.setText(item.getItemType());
                            itemCostLb.setText(Double.toString(item.getItemCost()));
                            
                            isItemCode = true;
                            
                            if (barCodeChkbx.isSelected()) {
                                
                                loadItemsToNewTable(true);
                                
                            }
                            
                            break;
                            
                        }
                        
                    }
                    
                    if (!isItemCode) {
                        
                        itemNameLb.setText("Empty");
                        salesPriceLb.setText("Empty");
                        itemTypeLb.setText("Empty");
                        
                        
                        JOptionPane.showMessageDialog(this, "Invalid Item Code", "Warn",
                                JOptionPane.WARNING_MESSAGE);
                        
                    }
                    
                } else {
                }
                
            } else {
                
                JOptionPane.showMessageDialog(this, "Invalid Format for Item Code", "Warn",
                        JOptionPane.ERROR_MESSAGE);
            }
            
            isItemAdd = false;
            
        } else {
            
            isItemAdd = true;
        }
        
        
        
        
        
        
        
    }//GEN-LAST:event_invoiceItemCodeCbItemStateChanged
    
    private void itemAddTableBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAddTableBtnActionPerformed
        
        String selectItemValue = (String) invoiceItemCodeCb.getSelectedItem();
        
        if (selectItemValue != null && selectItemValue.length() > 0) {
            
            if (isNumber(selectItemValue)) {
                
                String noOfitem = noOfItemsTf.getText();
                
                if (noOfitem != null && noOfitem.length() > 0) {
                    
                    if (isNumber(noOfitem)) {
                        
                        if (noOfitem.indexOf(".") == -1) {
                            
                            loadItemsToNewTable(false);
                            
                        } else {
                            
                            JOptionPane.showMessageDialog(this, "Please enter integer value for No Of Item", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            noOfItemsTf.requestFocus();
                            
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid format for the No of Item", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        noOfItemsTf.requestFocus();
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(this, "No Of Item can't be empty", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    noOfItemsTf.requestFocus();
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Format for the Item Code", "Error",
                        JOptionPane.ERROR_MESSAGE);
                invoiceItemCodeCb.requestFocus();
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Item code can't be empty", "Error",
                    JOptionPane.ERROR_MESSAGE);
            invoiceItemCodeCb.requestFocus();
        }
        
        
    }//GEN-LAST:event_itemAddTableBtnActionPerformed
    
    private void noOfItemsTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_noOfItemsTfActionPerformed
        
        
        String selectItemValue = (String) invoiceItemCodeCb.getSelectedItem();
        
        if (selectItemValue.equals("") || isNumber(selectItemValue)) {
            
            if (!selectItemValue.equals("")) {
                
                String noOfitem = noOfItemsTf.getText();
                
                if (noOfitem != null && noOfitem.length() > 0) {
                    
                    if (isNumber(noOfitem)) {
                        
                        if (noOfitem.indexOf(".") == -1) {
                            
                            loadItemsToNewTable(false);
                            
                        } else {
                            
                            JOptionPane.showMessageDialog(this, "Please enter integer value for No Of Item", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            noOfItemsTf.requestFocus();
                            
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid format for the No of Item", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        noOfItemsTf.requestFocus();
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(this, "No Of Item can't be empty", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    noOfItemsTf.requestFocus();
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "Item Code can't be empty", "Warn",
                        JOptionPane.ERROR_MESSAGE);
                invoiceItemCodeCb.requestFocus();
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Invalid Format for Item Code", "Warn",
                    JOptionPane.ERROR_MESSAGE);
            invoiceItemCodeCb.requestFocus();
        }
        
        
    }//GEN-LAST:event_noOfItemsTfActionPerformed
    
    private void reAmountTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reAmountTfActionPerformed
        
        if (discountChkbx.isSelected()) {
            
            if (isDiscountAdded) {
                addCustomerInvoice();
            } else {
                JOptionPane.showMessageDialog(this, "You are not discount the bill.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                discountBtn.requestFocus();
            }
            
        } else {
            addCustomerInvoice();
        }
        
    }//GEN-LAST:event_reAmountTfActionPerformed
    
    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        
        invoiceRefresh();
        
    }//GEN-LAST:event_jButton13ActionPerformed
    
    private void itemAddTableBtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemAddTableBtnKeyPressed
        
        if (evt.getKeyCode() == 10) {
            
            String noOfitem = noOfItemsTf.getText();
            
            if (noOfitem != null && noOfitem.length() > 0) {
                
                if (isNumber(noOfitem)) {
                    
                    if (noOfitem.indexOf(".") == -1) {
                        
                        loadItemsToNewTable(false);
                        
                    } else {
                        
                        JOptionPane.showMessageDialog(this, "Please enter integer value for No Of Item", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        noOfItemsTf.requestFocus();
                        
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid format for the No of Item", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    noOfItemsTf.requestFocus();
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "No Of Item can't be empty", "Error",
                        JOptionPane.ERROR_MESSAGE);
                noOfItemsTf.requestFocus();
            }
            
        }
    }//GEN-LAST:event_itemAddTableBtnKeyPressed
    
    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        
        if (discountChkbx.isSelected()) {
            
            if (isDiscountAdded) {
                addCustomerInvoice();
            } else {
                JOptionPane.showMessageDialog(this, "You are not discount the bill.", "Error",
                        JOptionPane.ERROR_MESSAGE);
                discountBtn.requestFocus();
            }
            
        } else {
            addCustomerInvoice();
        }
        
    }//GEN-LAST:event_jButton14ActionPerformed
    
    private void jButton14KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton14KeyPressed
        
        if (evt.getKeyCode() == 10) {
            
            
            if (discountChkbx.isSelected()) {
                
                if (isDiscountAdded) {
                    addCustomerInvoice();
                } else {
                    JOptionPane.showMessageDialog(this, "You are not discount the bill.", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    discountBtn.requestFocus();
                }
                
            } else {
                addCustomerInvoice();
            }
            
        }
        
    }//GEN-LAST:event_jButton14KeyPressed
    
    private void newInvoiceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_newInvoiceBtnActionPerformed
        
        invoiceRefresh();
        
    }//GEN-LAST:event_newInvoiceBtnActionPerformed
    
    private void newInvoiceBtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_newInvoiceBtnKeyPressed
        
        if (evt.getKeyCode() == 10) {
            invoiceRefresh();
        }
    }//GEN-LAST:event_newInvoiceBtnKeyPressed
    
    private void customerInvoiceBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerInvoiceBtnActionPerformed
        
        customerInvoiceIF.setVisible(true);
        newItemIF.setVisible(false);
        editItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        itemCostIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        
        invoiceItemCodeCb.requestFocus();
        noOfItemsTf.setText("");
        
    }//GEN-LAST:event_customerInvoiceBtnActionPerformed
    
    private void editInvoiceSearchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editInvoiceSearchBtnActionPerformed
        
        loadDataToEditCustomerInvoice();
        
    }//GEN-LAST:event_editInvoiceSearchBtnActionPerformed
    
    private void editInvoiceSearchBtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editInvoiceSearchBtnKeyPressed
        
        if (evt.getKeyCode() == 10) {
            
            loadDataToEditCustomerInvoice();
            
        }
    }//GEN-LAST:event_editInvoiceSearchBtnKeyPressed
    
    private void invoiceIdCbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_invoiceIdCbItemStateChanged
//        if (isInvoiceAdded) {
//
//            System.out.println("Call back2");
//
//            String selectInvoiceValue = (String) invoiceIdCb.getSelectedItem();
//
//            if (selectInvoiceValue.equals("") || isNumber(selectInvoiceValue)) {
//
//                if (!selectInvoiceValue.equals("")) {
//
//
//                    if (selectInvoiceValue.indexOf(".") == -1 && selectInvoiceValue.indexOf("f") == -1) {
//
//
//                        Integer invoiceValu = new Integer(selectInvoiceValue);
//
//                        Iterator it = invoicesList.iterator();
//
//                        boolean isInvoiceId = false;
//
//                        while (it.hasNext()) {
//
//                            CustomerInvoice customerInvoice = (CustomerInvoice) it.next();
//
//
//                            if (invoiceValu.equals(customerInvoice.getInvoiceId())) {
//
//                                loadDataToEditCustomerInvoice();
//
//                                isInvoiceId = true;
//
//
//                                break;
//
//                            }
//
//                        }
//
//
//                        isInvoiceAdded = false;
//
//                        if (!isInvoiceId) {
//
////                        itemNameLb.setText("Empty");
////                        salesPriceLb.setText("Empty");
////                        itemTypeLb.setText("Empty");
////
//
//                            JOptionPane.showMessageDialog(this, "Invalid Item Code", "Warn",
//                                    JOptionPane.WARNING_MESSAGE);
//
//                        }
//
//
//                    } else {
//                        JOptionPane.showMessageDialog(this, "Please enter integer value for Invoice Id", "Warn",
//                                JOptionPane.ERROR_MESSAGE);
//                        invoiceIdCb.requestFocus();
//                    }
//
//
//
//                } else {
//                    JOptionPane.showMessageDialog(this, "Invoice Id Can't be empty", "Warn",
//                            JOptionPane.ERROR_MESSAGE);
//                    invoiceIdCb.requestFocus();
//                }
//
//            } else {
//
//                JOptionPane.showMessageDialog(this, "Invalid Format for Invoice Id", "Warn",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//
//
//
//        } else {
//
//            isInvoiceAdded = true;
//        }
    }//GEN-LAST:event_invoiceIdCbItemStateChanged
    
    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        
        nexDes.setText("");
        nExAmount.setText("");
        
    }//GEN-LAST:event_jButton19ActionPerformed
    
    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        
        
        Calendar exCalendar = nexpenseDate.getSelectedDate();
//exCalendar.setTimeZone(TimeZone.getTimeZone("GMT + 06:30"));
//exCalendar.setTimeInMillis(System.currentTimeMillis());
        if (exCalendar != null) {
            
            GregorianCalendar grCalendar = new GregorianCalendar();
            grCalendar.setTime(exCalendar.getTime());
            
            int y = grCalendar.get(GregorianCalendar.YEAR);
            int m = grCalendar.get(GregorianCalendar.MONTH);
            int d = grCalendar.get(GregorianCalendar.DATE);
            
            String year = Integer.toString(y);
            
            String monthParam = null;
            
            
            switch (m + 1) {
                
                case 1:
                    monthParam = "January";
                    break;
                case 2:
                    monthParam = "Febuary";
                    break;
                case 3:
                    monthParam = "March";
                    break;
                case 4:
                    monthParam = "April";
                    break;
                case 5:
                    monthParam = "May";
                    break;
                case 6:
                    monthParam = "June";
                    break;
                case 7:
                    monthParam = "July";
                    break;
                case 8:
                    monthParam = "August";
                    break;
                case 9:
                    monthParam = "September";
                    break;
                case 10:
                    monthParam = "Octomber";
                    break;
                case 11:
                    monthParam = "November";
                    break;
                case 12:
                    monthParam = "December";
                    break;
                
            }
            
            
            GregorianCalendar dailyBusinessGrCalendar = new GregorianCalendar(y, m, d);
            
            
            String newDes = nexDes.getText();
            
            if (newDes != null && newDes.length() > 0) {
                
                String amount = nExAmount.getText();
                
                if (amount != null && amount.length() > 0) {
                    
                    if (isNumber(amount)) {
                        
                        String expenseType = (String) expenseTypeCb.getSelectedItem();
                        double amountValue = Double.parseDouble(amount);
                        
                        Expense expense = new Expense();
                        
                        expense.setExpenseDate(nexpenseDate.getSelectedDate().getTime());
                        expense.setExpenseDescription(newDes);
                        expense.setExpenseType(expenseType);
                        expense.setExpenseAmount(amountValue);
                        expense.setExpenseMonth(monthParam);
                        expense.setExpenseYear(year);
                        expense.setEnteredBy(username);
                        expense.setEnteredDate(new Date(System.currentTimeMillis()));
                        
                        
                        String response = expenseManager.addExpense(expense);
                        
                        if (response.equals(Constants.EXPENSE_ADDED_SUCCESSFULLY)) {
                            
                            String oldCashierAmount = cashAmountValueLb.getText();
                            
                            double newCashierAmount = Double.parseDouble(oldCashierAmount) - expense.getExpenseAmount();
                            if (newCashierAmount > 0) {
                                cashAmountValueLb.setText(Double.toString(newCashierAmount));
                            } else {
                                cashAmountValueLb.setText("0.0");
                            }
                            JOptionPane.showMessageDialog(this, "Expense Added Successfully.", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            
                            nexDes.setText("");
                            nExAmount.setText("");
                            
                            
                        } else if (response.equals(Constants.ERROR)) {
                            JOptionPane.showMessageDialog(this, "Expense did not added successfully", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }

                        //    itemLists.add(items);

                    } else {
                        
                        JOptionPane.showMessageDialog(this, "Invalid Format for the Quantity", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        newItemNameTf.requestFocus();
                    }
                    
                } else {
                    
                    JOptionPane.showMessageDialog(this, "Quantity can't be empty", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    newSalesPriceTf.requestFocus();
                }
                
                
                
            } else {
                JOptionPane.showMessageDialog(this, "Expense Description can't be empty", "Error",
                        JOptionPane.ERROR_MESSAGE);
                newItemCodeTf.requestFocus();
            }
            
            
        } else {
            
            JOptionPane.showMessageDialog(this, "Expense Date can't be empty", "Error",
                    JOptionPane.ERROR_MESSAGE);
            nexpenseDate.requestFocus();
            
        }
        
        
        
    }//GEN-LAST:event_jButton18ActionPerformed
    
    private void jMenu4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu4ActionPerformed
        
        updateStoresIF.setVisible(true);
        editItemIF.setVisible(false);
        newItemIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        itemCostIF.setVisible(false);
    }//GEN-LAST:event_jMenu4ActionPerformed
    
    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        
        if (icItemCodeCb.isEnabled()) {
            icItemCodeCb.setSelectedIndex(0);
        }
        
        icCostPriceTf.setText("");
        
    }//GEN-LAST:event_jButton21ActionPerformed
    
    private void icItemCodeCbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_icItemCodeCbItemStateChanged
        
        String selectItemValue = (String) icItemCodeCb.getSelectedItem();
        
        Integer itemValue = new Integer(selectItemValue);
        
        Iterator it = itemLists.iterator();
        
        boolean isNotExist = false;
        
        while (it.hasNext()) {
            
            Items item = (Items) it.next();
            
            
            if (itemValue.equals(item.getItemCode())) {
                
                
                icItemNameTf.setText(item.getItemName());
                icSalesPriceTf.setText(Double.toString(item.getSalesPrice()));
                ictypeTf.setText(item.getItemType());
                
                if (!(item.getItemCost() == 0.0)) {
                    icCostPriceTf.setText(Double.toString(item.getItemCost()));
                } else {
                    icCostPriceTf.setText("Not Updated");
                }
                
                isNotExist = false;
                break;
                
            } else {
                
                isNotExist = true;
            }
            
            
        }
        
        if (isNotExist) {
            
            JOptionPane.showMessageDialog(this, "Invalid Item Code", "Warn",
                    JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_icItemCodeCbItemStateChanged
    
    private void itemCostMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemCostMIActionPerformed
        
        if (icItemCodeCb.getItemCount() == 0) {
            
            loadItemCodesToItemCost();
            
        }
        itemCostIF.setVisible(true);
        editItemIF.setVisible(false);
        newItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
    }//GEN-LAST:event_itemCostMIActionPerformed
    
    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        
        editItemCost();
    }//GEN-LAST:event_jButton20ActionPerformed
    
    private void upStoresItemCodeCbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_upStoresItemCodeCbItemStateChanged
        
        String selectItemValue = (String) upStoresItemCodeCb.getSelectedItem();

//        if(selectItemValue ! = null && selectItemValue.length() > 0){
//
//            if(isNumber(selectItemValue)){
//
//            }
//
//        }else{
//            JOptionPane.showMessageDialog(this, "Item Code Can't be empty","Error", JOptionPane.ERROR_MESSAGE);
//        }

        Integer itemValue = new Integer(selectItemValue);
        Iterator it = itemLists.iterator();
        
        boolean isNotExist = false;
        
        while (it.hasNext()) {
            
            Items item = (Items) it.next();
            
            
            if (itemValue.equals(item.getItemCode())) {
                
                upStoresItemNameTf.setText(item.getItemName());
                
                isNotExist = false;
                break;
                
            } else {
                
                isNotExist = true;
            }
            
        }
        
        Stores stores = storesManager.getStroesDetailsForItemCode(selectItemValue);
        
        
        if (stores.getQuantity() != null) {
            
            
            int storesQuantity = stores.getQuantity().intValue();
            if (storesQuantity != 0) {
                
                upStoresQuantityTf.setText(new Integer(storesQuantity).toString());
            } else {
            }
            
        } else {
        }
        
        if (stores.getReOrder() != null) {
            upStoresReOrderTf.setText(Integer.toString(stores.getReOrder()));
        } else {
        }
//
//Iterator itTmp = storesList.iterator();
//
//        while (itTmp.hasNext()) {
//
//            Stores stores = (Stores) itTmp.next();
//
//
//            if (itemValue.equals(stores.getItemCode())) {
//
//                if (stores.getQuantity() != null) {
//
//
//                    int storesQuantity = stores.getQuantity().intValue();
//                    if (storesQuantity != 0) {
//
//                        upStoresQuantityTf.setText(new Integer(storesQuantity).toString());
//                    } else {
//                    }
//
//                } else {
//                }
//
//                if (stores.getReOrder() != null) {
//                    upStoresReOrderTf.setText(Integer.toString(stores.getReOrder()));
//                } else {
//                }
//
//
//                break;
//
//            }
//
//        }

    }//GEN-LAST:event_upStoresItemCodeCbItemStateChanged
    
    private void jMenuItem7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem7ActionPerformed
        System.exit(0);
    }//GEN-LAST:event_jMenuItem7ActionPerformed
    
    private void logOffMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOffMIActionPerformed
        
        this.setVisible(false);
        loginDialog.setVisible(true);
        
    }//GEN-LAST:event_logOffMIActionPerformed
    
    private void logOffBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logOffBtnActionPerformed
        
        this.setVisible(false);
        loginDialog.setVisible(true);
        
    }//GEN-LAST:event_logOffBtnActionPerformed
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        newExpensesIF.setVisible(true);
        customerInvoiceIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        newItemIF.setVisible(false);
        editItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        itemCostIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        
        nexpenseDate.requestFocus();
        
        
    }//GEN-LAST:event_jButton1ActionPerformed
    
    private void jButton20KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_jButton20KeyPressed
        
        if (evt.getKeyCode() == 10) {
            editItemCost();
        }
        
    }//GEN-LAST:event_jButton20KeyPressed
    
    private void icItemCodeCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_icItemCodeCbActionPerformed
    }//GEN-LAST:event_icItemCodeCbActionPerformed
    
    private void invoiceItemCodeCbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_invoiceItemCodeCbKeyPressed
    }//GEN-LAST:event_invoiceItemCodeCbKeyPressed
    
    private void invoiceItemCodeCbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_invoiceItemCodeCbKeyReleased
    }//GEN-LAST:event_invoiceItemCodeCbKeyReleased
    
    private void invoiceItemCodeCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceItemCodeCbActionPerformed
//            String selectItemValue = (String) invoiceItemCodeCb.getSelectedItem();
//
//            if (isNumber(selectItemValue)) {
//
//                Integer itemVaue = new Integer(selectItemValue);
//
//                Iterator it = itemLists.iterator();
//
//                boolean isItemCode = false;
//
//                while (it.hasNext()) {
//
//                    Items item = (Items) it.next();
//
//
//                    if (itemVaue.equals(item.getItemCode())) {
//
//                        itemNameLb.setText(item.getItemName());
//                        salesPriceLb.setText(Double.toString(item.getSalesPrice()));
//                        itemTypeLb.setText(item.getItemType());
//
//                        isItemCode = true;
//
//                        if (barCodeChkbx.isSelected()) {
//                            loadItemsToNewTable(true);
//                        }
//                        break;
//
//                    }
//
//                }
//
//                if (!isItemCode) {
//
//                    itemNameLb.setText("Empty");
//                    salesPriceLb.setText("Empty");
//                    itemTypeLb.setText("Empty");
//
//
//                    JOptionPane.showMessageDialog(this, "Invalid Item Code", "Warn",
//                            JOptionPane.WARNING_MESSAGE);
//
//                }
//
//            } else {
//                JOptionPane.showMessageDialog(this, "Invalid Format for Item Code", "Warn",
//                        JOptionPane.ERROR_MESSAGE);
//            }
    }//GEN-LAST:event_invoiceItemCodeCbActionPerformed
    
    private void discountChkbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountChkbxActionPerformed
        
        if (discountChkbx.isSelected()) {
//            discountCb.setEnabled(true);
//            discountBtn.setEnabled(true);
//            percentRadio.setSelected(true);
            textAmountRadio.setEnabled(true);
            percentRadio.setEnabled(true);
            itemWiseChkBx.setEnabled(true);
        } else {
            discountCb.setEnabled(false);
            discountBtn.setEnabled(false);
            
            textDiscountTf.setEnabled(false);
            textAmountRadio.setSelected(false);
            percentRadio.setSelected(false);
            textAmountRadio.setEnabled(false);
            percentRadio.setEnabled(false);
            itemWiseChkBx.setEnabled(false);
            
        }
        
    }//GEN-LAST:event_discountChkbxActionPerformed
    
    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        
        Calendar noCalendar = noDate.getSelectedDate();
        
        if (noCalendar != null) {
            
            String customerName = noCustomerNameTf.getText();
            
            if (customerName != null && customerName.length() > 0) {
                
                String contactNumber = noContactNumberTf.getText();
                
                if (contactNumber != null && contactNumber.length() > 0) {
                    
                    String orderDetails = noOrderDeailsTa.getText();
                    
                    if (orderDetails != null && orderDetails.length() > 0) {
                        
                        
                        
                        GregorianCalendar grCalendar = new GregorianCalendar();
                        grCalendar.setTime(noCalendar.getTime());
                        
                        int y = grCalendar.get(GregorianCalendar.YEAR);
                        int m = grCalendar.get(GregorianCalendar.MONTH);
                        int d = grCalendar.get(GregorianCalendar.DATE);
                        
                        String year = Integer.toString(y);
                        
                        String monthParam = null;
                        
                        switch (m + 1) {
                            
                            case 1:
                                monthParam = "January";
                                break;
                            case 2:
                                monthParam = "Febuary";
                                break;
                            case 3:
                                monthParam = "March";
                                break;
                            case 4:
                                monthParam = "April";
                                break;
                            case 5:
                                monthParam = "May";
                                break;
                            case 6:
                                monthParam = "June";
                                break;
                            case 7:
                                monthParam = "July";
                                break;
                            case 8:
                                monthParam = "August";
                                break;
                            case 9:
                                monthParam = "September";
                                break;
                            case 10:
                                monthParam = "Octomber";
                                break;
                            case 11:
                                monthParam = "November";
                                break;
                            case 12:
                                monthParam = "December";
                                break;
                            
                        }
                        
                        
                        GregorianCalendar dailyBusinessGrCalendar = new GregorianCalendar(y, m, d);
                        
                        NewOrders newOrders = new NewOrders();
                        
                        newOrders.setCustomerName(customerName);
                        newOrders.setContactNumber(contactNumber);
                        newOrders.setOrderDetails(orderDetails);
                        newOrders.setOrderMonth(monthParam);
                        newOrders.setOrderYear(Integer.toString(y));
                        newOrders.setEnteredBy(username);
                        newOrders.setEnteredDate(new Date());
                        
                        String response = newOrdersManager.newOrders(newOrders);
                        
                        
                        if (response.equals(Constants.OREDER_ADDED_SUCCESSFULLY)) {
                            
                            
                            JOptionPane.showMessageDialog(this, "New Order Added Successfully.", "Success",
                                    JOptionPane.INFORMATION_MESSAGE);
                            
                            noCustomerNameTf.setText("");
                            noContactNumberTf.setText("");
                            noOrderDeailsTa.setText("");
                            
                            
                            
                        } else if (response.equals(Constants.ERROR)) {
                            JOptionPane.showMessageDialog(this, "Item did not add successfully", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(this, "Order Details can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
                        
                        noOrderDeailsTa.requestFocus();
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Contact Number can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    
                    noContactNumberTf.requestFocus();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Customer Name can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
                
                noCustomerNameTf.requestFocus();
            }
        } else {
            JOptionPane.showMessageDialog(this, "Date can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
            
            noDate.requestFocus();
        }
    }//GEN-LAST:event_jButton6ActionPerformed
    
    private void barCodeChkbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_barCodeChkbxActionPerformed
        
        if (barCodeChkbx.isSelected()) {
            
            noOfItemsTf.setEnabled(false);
            itemAddTableBtn.setEnabled(false);
            
            invoiceItemCodeCb.requestFocus();
            
            
            
        } else {
            
            noOfItemsTf.setEnabled(true);
            itemAddTableBtn.setEnabled(true);
            
            invoiceItemCodeCb.requestFocus();
            
            
            
        }
    }//GEN-LAST:event_barCodeChkbxActionPerformed
    
    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        
        String month = (String) reExpMonthCb.getSelectedItem();
        String year = (String) reExpYearCb.getSelectedItem();
        
        reportManager.getMonthlyExpensesReport(month, year);
        
        
    }//GEN-LAST:event_jButton12ActionPerformed
    
    private void discountCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountCbActionPerformed
    }//GEN-LAST:event_discountCbActionPerformed
    
    private void discountBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountBtnActionPerformed
        //  if (!isDiscountAdded) {
        discount();
        // }
    }//GEN-LAST:event_discountBtnActionPerformed
    
    private void discountCbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_discountCbItemStateChanged
        
        
        if (discountChkbx.isSelected()) {
            
            String selectedDiscount = (String) discountCb.getSelectedItem();
            
            double discountPercent = Double.parseDouble(selectedDiscount);
            
            double totalAmount = Double.parseDouble(totalAmountLb.getText());
            
            
            double discoutAmount = discountPercent / 100 * totalAmount;
            
            double newTotalAmount = totalAmount - discoutAmount;
            
            discoutAmount = round(discoutAmount, 2);
            
            discountLb.setText(Double.toString(discoutAmount));
        }
        
    }//GEN-LAST:event_discountCbItemStateChanged
    
    private void discountBtnKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_discountBtnKeyPressed
        
        if (evt.getKeyCode() == 10) {
            
            discount();
        }
        
    }//GEN-LAST:event_discountBtnKeyPressed
    
    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        
        String month = (String) reMonthlyBusinessMonthCb.getSelectedItem();
        String year = (String) reMonthlyBusinessYearCb.getSelectedItem();
        
        reportManager.getMonthlyBusinessReport(month, year);
        
        
        
    }//GEN-LAST:event_jButton15ActionPerformed
    
    private void monthBusinessProfitMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthBusinessProfitMIActionPerformed
        
        viewMonthlyBusinessIF.setVisible(true);
        newItemIF.setVisible(false);
        itemCostIF.setVisible(false);
        editItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        
    }//GEN-LAST:event_monthBusinessProfitMIActionPerformed
    
    private void reportsJMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_reportsJMActionPerformed
        
        viewMonthlyBusinessForCashierIF.setVisible(true);
        viewMonthlyBusinessIF.setVisible(false);
        newItemIF.setVisible(false);
        itemCostIF.setVisible(false);
        editItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        
    }//GEN-LAST:event_reportsJMActionPerformed
    
    private void businessBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_businessBtnActionPerformed
        
        viewMonthlyBusinessForCashierIF.setVisible(true);
        viewMonthlyBusinessIF.setVisible(false);
        newItemIF.setVisible(false);
        itemCostIF.setVisible(false);
        editItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        
    }//GEN-LAST:event_businessBtnActionPerformed
    
    private void returnInvoiceItemCodeCbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_returnInvoiceItemCodeCbItemStateChanged
//       
//        if(returnInvoiceItemCodeCb.getItemCount() > 0){
//
//           String selectItemValue = (String) returnInvoiceItemCodeCb.getSelectedItem();
//
//            if (selectItemValue.equals("") || isNumber(selectItemValue)) {
//
//                if (!selectItemValue.equals("")) {
//
//                    Integer itemVaue = new Integer(selectItemValue);
//
//                    Iterator it = customerInvoiceItems.iterator();
//
//                    boolean isItemCode = false;
//
//                    while (it.hasNext()) {
//
//                        InvoiceItems invoiceItems = (InvoiceItems) it.next();
//
//
//                        if (itemVaue.equals(invoiceItems.getInvoiceItemsPK().getItemCode())) {
//
//                            isItemCode = true;
//
//                            break;
//
//                        }
//
//                    }
//
//                    if (!isItemCode) {
//
//                        itemNameLb.setText("Empty");
//                        salesPriceLb.setText("Empty");
//                        itemTypeLb.setText("Empty");
//
//
//                        JOptionPane.showMessageDialog(this, "Invalid Item Code", "Warn",
//                                JOptionPane.WARNING_MESSAGE);
//
//                    }
//
//                } else {
//                }
//
//            } else {
//
//                JOptionPane.showMessageDialog(this, "Invalid Format for Item Code", "Warn",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//
//
//        }
    }//GEN-LAST:event_returnInvoiceItemCodeCbItemStateChanged
    
    private void returnInvoiceItemCodeCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnInvoiceItemCodeCbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_returnInvoiceItemCodeCbActionPerformed
    
    private void returnInvoiceItemCodeCbKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_returnInvoiceItemCodeCbKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_returnInvoiceItemCodeCbKeyPressed
    
    private void returnInvoiceItemCodeCbKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_returnInvoiceItemCodeCbKeyReleased
        // TODO add your handling code here:
    }//GEN-LAST:event_returnInvoiceItemCodeCbKeyReleased
    
    private void returnNoOfItemsTfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnNoOfItemsTfActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_returnNoOfItemsTfActionPerformed
    
    private void itemAddTableBtn1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemAddTableBtn1ActionPerformed
        
        int noOfRows = editInvoiceTable.getRowCount();
        
        
        
        if (noOfRows > 0 && returnInvoiceItemCodeCb.getItemCount() > 0) {
            
            
            String selectItemValue = (String) returnInvoiceItemCodeCb.getSelectedItem();
            
            if (selectItemValue.equals("") || isNumber(selectItemValue)) {
                
                if (!selectItemValue.equals("")) {
                    
                    if (selectItemValue.indexOf(".") == -1 && selectItemValue.indexOf("f") == -1 && selectItemValue.indexOf("d") == -1) {
                        
                        Integer selectedItemCode = new Integer(selectItemValue);
                        
                        Iterator it = editCustomerInvoiceItems.iterator();
                        
                        boolean isItemCode = false;
                        InvoiceItems invoiceItems = null;
                        while (it.hasNext()) {
                            
                            invoiceItems = (InvoiceItems) it.next();
                            
                            
                            if (selectedItemCode.equals(invoiceItems.getInvoiceItemsPK().getItemCode())) {
                                
                                isItemCode = true;
                                
                                
                                break;
                                
                            }
                            
                        }
                        
                        if (isItemCode) {
                            
                            String noOfItems = returnNoOfItemsTf.getText();
                            
                            if (noOfItems != null && noOfItems.length() > 0) {
                                
                                if (isNumber(noOfItems)) {
                                    
                                    if (noOfItems.indexOf(".") == -1 && noOfItems.indexOf("f") == -1 && noOfItems.indexOf("d") == -1) {
                                        
                                        int status = JOptionPane.showConfirmDialog(this, "Are you sure return this item ?", "Warn", JOptionPane.YES_NO_OPTION);
                                        
                                        if (status == 0) {
                                            
                                            Integer noOfItemValue = new Integer(noOfItems);
                                            
                                            
                                            int i = 0;
                                            Integer quantity = null;
                                            String itemName = null;
                                            Double salesPrice = null;
                                            double perOnePrice = 0.0;
                                            Double itemDiscount = 0.0;
                                            while (i < noOfRows) {
                                                
                                                Integer tableItemCode = (Integer) editInvoiceTable.getValueAt(i, 0);
                                                
                                                if (tableItemCode.equals(selectedItemCode)) {
                                                    
                                                    itemName = (String) editInvoiceTable.getValueAt(i, 1);
                                                    perOnePrice = (Double) editInvoiceTable.getValueAt(i, 2);
                                                    quantity = (Integer) editInvoiceTable.getValueAt(i, 3);
                                                    salesPrice = (Double) editInvoiceTable.getValueAt(i, 4);
                                                    itemDiscount = (Double) editInvoiceTable.getValueAt(i, 5);
                                                    
                                                    break;
                                                }
                                                
                                                i++;
                                                
                                            }
                                            
                                            if (quantity >= noOfItemValue) {
                                                
                                                double discountPercent = Double.parseDouble(discountPercentageTf.getText());
                                                double discountValue = 0.0;
                                                
                                                
                                                if (discountPercent > 0) {
                                                    
                                                    discountValue = (perOnePrice * noOfItemValue) * discountPercent / 100;
                                                    
                                                    returnValue = returnValue + perOnePrice * noOfItemValue - discountValue;
                                                    
                                                    
                                                } else {
                                                    
                                                    returnValue = returnValue + perOnePrice * noOfItemValue;
                                                }
                                                
                                                
                                                if (itemDiscount.doubleValue() > 0) {
                                                    returnValue = returnValue - (itemDiscount / quantity) * noOfItemValue;
                                                }
                                                
                                                
                                                
                                                Vector row = new Vector();
                                                
                                                Integer itemCode = new Integer(selectItemValue);
                                                Double pricePerOneValue = new Double(perOnePrice);
                                                Double price = new Double(salesPrice);
                                                Integer tableQuantity = new Integer(quantity);
                                                
                                                row.addElement(itemCode);
                                                row.addElement(itemName);
                                                row.addElement(pricePerOneValue);
                                                row.addElement(tableQuantity);
                                                row.addElement(price);
                                                row.addElement(itemDiscount);
                                                
                                                
                                                editCustomerInvoiceRow.remove(row);


//                                                InvoiceItems invoiceItemTmp = new InvoiceItems();
//
//                                                InvoiceItemsPK invoiceItemsPK = new InvoiceItemsPK();
//                                                invoiceItemsPK.setInvoiceId(new Integer((String) invoiceIdCb.getSelectedItem()).intValue());
//                                                invoiceItemsPK.setItemCode(itemCode.intValue());
//
//                                                invoiceItemTmp.setInvoiceItemsPK(invoiceItemsPK);
//




                                                // if (quantity.intValue() > 1) {

                                                
                                                
                                                int newQuantity = quantity.intValue() - noOfItemValue.intValue();
                                                double newSalesPrice = salesPrice - perOnePrice * noOfItemValue.intValue();
                                                double newItemDisocuntValue = 0;
                                                
                                                if (itemDiscount > 0) {
                                                    newItemDisocuntValue = itemDiscount - (itemDiscount / quantity) * noOfItemValue.intValue();
                                                    
                                                }
                                                String totalDiscount = editDiscountTf.getText();
                                                double totalDiscountValue = Double.parseDouble(totalDiscount);
                                                double newTotalDiscountValue = totalDiscountValue - (itemDiscount / quantity) * noOfItemValue.intValue();
                                                editDiscountTf.setText(Double.toString(newTotalDiscountValue));
                                                
                                                double itemRealSalePrice = perOnePrice * noOfItemValue.intValue() - (itemDiscount / quantity) * noOfItemValue.intValue();
                                                String totalInvoiceAmount = editTotalAmountTf.getText();
                                                
                                                double totalInvoiceAmountValue = Double.parseDouble(totalInvoiceAmount);
                                                
                                                double newTotalInvoiceAmountValue = totalInvoiceAmountValue - itemRealSalePrice;
                                                
                                                editTotalAmountTf.setText(Double.toString(newTotalInvoiceAmountValue));



//                                                    if (!(newQuantity == 0)) {
                                                invoiceItems.setQuantity(newQuantity);
                                                invoiceItems.setSalesPrice(newSalesPrice);
                                                invoiceItems.setItemdiscount(newItemDisocuntValue);
                                                
                                                Vector newRow = new Vector();
                                                Double newPrice = new Double(newSalesPrice);
                                                Integer newTableQuantity = new Integer(newQuantity);
                                                Double newItemDiscount = new Double(newItemDisocuntValue);
                                                newRow.addElement(itemCode);
                                                newRow.addElement(itemName);
                                                newRow.addElement(pricePerOneValue);
                                                newRow.addElement(newTableQuantity);
                                                newRow.addElement(newPrice);
                                                newRow.addElement(newItemDiscount);
                                                
                                                editCustomerInvoiceRow.add(newRow);
                                                
                                                if (newQuantity == 0) {
                                                    returnInvoiceItemCodeCb.removeItem(selectItemValue);
                                                }

                                                //   customerInvoiceItems.add(invoiceItemTmp);

//                                                    } else {
//                                                        returnInvoiceItemCodeCb.removeItem(selectItemValue);
//                                                      //  customerInvoiceItems.remove(invoiceItemTmp);
//                                                    }

//
//                                                } else {
//
//                                                    returnInvoiceItemCodeCb.removeItem(selectItemValue);
//                                                   // customerInvoiceItems.remove(invoiceItemTmp);
//
//                                                }

                                                String response = storesManager.addReturnItemsToStores(selectItemValue, noOfItemValue);
                                                
                                                if (response.equals(Constants.STORES_UPDATED_SUCCESSFULLY)) {
                                                    
                                                    JOptionPane.showMessageDialog(this, "Return Item Added to Stores Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                                                } else if (response.equals(Constants.ERROR)) {
                                                    JOptionPane.showMessageDialog(this, "Error in Stores update ", "Error", JOptionPane.ERROR_MESSAGE);
                                                }
                                                
                                                Iterator itTmp = itemLists.iterator();
                                                double returnItemCost = 0;
                                                while (itTmp.hasNext()) {
                                                    Items item = (Items) itTmp.next();
                                                    
                                                    String returnItemCode = item.getItemCode().toString();
                                                    if (selectItemValue.equals(returnItemCode)) {
                                                        returnItemCost = item.getItemCost();
                                                        break;
                                                    }
                                                }
                                                
                                                
                                                
                                                
                                                double returnItemDiscount = (itemDiscount / quantity) * noOfItemValue.intValue();
                                                double rerturnItemBusinessAmount = perOnePrice * noOfItemValue.intValue() - returnItemDiscount;
                                                double returnItemBusinessProfit = rerturnItemBusinessAmount - (returnItemCost * noOfItemValue.intValue());
                                                String businessDate = editDate.getText();
                                                CompanyBusiness companyBusiness = new CompanyBusiness();
                                                companyBusiness.setBusinessDate(businessDate);
                                                companyBusiness.setBusinessAmount(rerturnItemBusinessAmount);
                                                companyBusiness.setBusinessProfit(returnItemBusinessProfit);
                                                companyBusiness.setDiscountAmount(returnItemDiscount);
                                                
                                                companyBusinessManager.updateCompanyBusinessForEditCustomerInvoice(companyBusiness);
                                                
                                                editInvoiceTable.setModel(new DefaultTableModel(editCustomerInvoiceRow, editInvoiceCoumns) {
                                                    @Override
                                                    public boolean isCellEditable(int row, int column) {
                                                        return false;
                                                    }
                                                });
                                                
                                                
                                                returnAmountLb.setText(Double.toString(returnValue));
                                                invoiceIdCb.setEnabled(false);
                                                editInvoiceSearchBtn.setEnabled(false);
                                                buyNewitemsBtn.setEnabled(true);
                                                
                                            } else {
                                                JOptionPane.showMessageDialog(this, "No of item must smaller than or equal to " + quantity, "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                                
                                                returnNoOfItemsTf.requestFocus();
                                                
                                            }
                                            
                                        }
                                        
                                        
                                    } else {
                                        
                                        JOptionPane.showMessageDialog(this, "Please enter integer value for No Of Item", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                        returnNoOfItemsTf.requestFocus();
                                        
                                    }
                                    
                                } else {
                                    JOptionPane.showMessageDialog(this, "Invalid Format for No of Items", "Warn",
                                            JOptionPane.ERROR_MESSAGE);
                                    returnNoOfItemsTf.requestFocus();
                                }
                                
                            } else {
                                JOptionPane.showMessageDialog(this, "No of Items Can't be empty", "Warn",
                                        JOptionPane.ERROR_MESSAGE);
                                returnNoOfItemsTf.requestFocus();
                            }
                            
                        } else {
                            JOptionPane.showMessageDialog(this, "Invalid Item Code", "Warn",
                                    JOptionPane.WARNING_MESSAGE);
                            returnInvoiceItemCodeCb.requestFocus();
                            
                        }
                        
                    } else {
                        JOptionPane.showMessageDialog(this, "Please enter integer value for Invoice Id", "Warn",
                                JOptionPane.ERROR_MESSAGE);
                        returnInvoiceItemCodeCb.requestFocus();
                    }
                    
                    
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Item Code Can't be empty", "Warn",
                            JOptionPane.ERROR_MESSAGE);
                    returnInvoiceItemCodeCb.requestFocus();
                }
                
            } else {
                
                JOptionPane.showMessageDialog(this, "Invalid Format for Item Code", "Warn",
                        JOptionPane.ERROR_MESSAGE);
                returnInvoiceItemCodeCb.requestFocus();
            }
            
            
        } else {
            JOptionPane.showMessageDialog(this, "Invoice items are empty", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
        
        
        
    }//GEN-LAST:event_itemAddTableBtn1ActionPerformed
    
    private void itemAddTableBtn1KeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_itemAddTableBtn1KeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_itemAddTableBtn1KeyPressed
    
    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        
        editInvoiceTable.selectAll();
        editInvoiceTable.removeAll();
        editInvoiceTable.doLayout();
        
        for (int i = 0; i < editInvoiceTable.getRowCount(); i++) {
            editInvoiceTable.removeRowSelectionInterval(i, i);
            editInvoiceTable.revalidate();
        }
        
        System.out.println("rrrrrrrrrr 11  " + editCustomerInvoiceRow.size());
        
        editCustomerInvoiceRow.removeAllElements();
        
        System.out.println("fffffffffffff22 " + editCustomerInvoiceRow.size());
        
        editInvoiceTable.setModel(new DefaultTableModel(editCustomerInvoiceRow, editInvoiceCoumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });


        //    loadDataToEditCustomerInvoice();

        returnAmountLb.setText("0.0");
        returnNoOfItemsTf.setText("");
        invoiceIdCb.setEnabled(true);
        editInvoiceSearchBtn.setEnabled(true);
        buyNewitemsBtn.setEnabled(false);
        
        returnValue = 0.0;
        
        
    }//GEN-LAST:event_jButton25ActionPerformed
    
    private void invoiceIdCbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceIdCbActionPerformed
//        if (isInvoiceAdded) {
//
//            String selectInvoiceValue = (String) invoiceIdCb.getSelectedItem();
//
//            if (selectInvoiceValue.equals("") || isNumber(selectInvoiceValue)) {
//
//                if (!selectInvoiceValue.equals("")) {
//
//                    Integer invoiceValu = new Integer(selectInvoiceValue);
//
//                    Iterator it = invoicesList.iterator();
//
//                    boolean isInvoiceId = false;
//
//                    while (it.hasNext()) {
//
//                        CustomerInvoice customerInvoice = (CustomerInvoice) it.next();
//
//
//                        if (invoiceValu.equals(customerInvoice.getInvoiceId())) {
//
//                            loadDataToEditCustomerInvoice();
//
//                            isInvoiceId = true;
//
//
//                            break;
//
//                        }
//
//                    }
//
//                    if (!isInvoiceId) {
//
////                        itemNameLb.setText("Empty");
////                        salesPriceLb.setText("Empty");
////                        itemTypeLb.setText("Empty");
////
//
//                        JOptionPane.showMessageDialog(this, "Invalid Item Code", "Warn",
//                                JOptionPane.WARNING_MESSAGE);
//
//                    }
//
//                } else {
//                }
//
//            } else {
//
//                JOptionPane.showMessageDialog(this, "Invalid Format for Item Code", "Warn",
//                        JOptionPane.ERROR_MESSAGE);
//            }
//
//            isInvoiceAdded = false;
//
//        } else {
//
//            isInvoiceAdded = true;
//        }
    }//GEN-LAST:event_invoiceIdCbActionPerformed
    
    private void buyNewitemsBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buyNewitemsBtnActionPerformed
        
        String totalInvoiceAmount = editTotalAmountTf.getText();
        
        double totalInvoiceAmountValue = Double.parseDouble(totalInvoiceAmount);
        
        String totalDiscount = editDiscountTf.getText();
        double totalDiscountValue = Double.parseDouble(totalDiscount);
        
        
        editCustomerInvoice.setTotalAmount(totalInvoiceAmountValue);
        editCustomerInvoice.setDiscountAmount(totalDiscountValue);
        
        
        editCustomerInvoice.setInvoiceItemsList(editCustomerInvoiceItems);
        
        
        
        
        String response = customerInvoiceManager.editCustomerInvoice(editCustomerInvoice, editCustomerInvoiceItems, null);
        
        if (response.equals(Constants.CUSTOMER_INVOICE_UPDATED_SUCCESSFULLY)) {
            JOptionPane.showMessageDialog(this, "Customer Invoice Updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
            
            
            editCustomerInvoiceIF.setVisible(false);
            customerInvoiceIF.setVisible(true);
            invoiceItemCodeCb.requestFocus();
            
            String returnAmount = returnAmountLb.getText();
            returnItemValueLb.setVisible(true);
            returnValueLb.setVisible(true);
            
            returnValueLb.setText(returnAmount);
            
        }
//        Date editInvoiceDate = new Datalue = e();
//
//        int tableRows = editInvoiceTable.getRowCount();
//
//        editNewInvoiceItemsList = new Vector();
//
//        if (tableRows > 0) {
//
//
//            for (int x = 0; x < tableRows; x++) {
//
//                InvoiceItemsPK invoiceItemsPK = new InvoiceItemsPK();
//
//
//                InvoiceItems invoiceItems = new InvoiceItems();
//
//                Integer itemCode = (Integer) editInvoiceTable.getValueAt(x, 0);
//                String itemName = (String) editInvoiceTable.getValueAt(x, 1);
//                Double pricePerOne = (Double) editInvoiceTable.getValueAt(x, 2);
//                Integer noOfitems = (Integer) editInvoiceTable.getValueAt(x, 3);
//                Double amount = (Double) editInvoiceTable.getValueAt(x, 4);
//
//                String discountPercent = discountPercentageTf.getText();
//                double discountPercentValue = Double.parseDouble(discountPercent);
//
//                double salesPrice = pricePerOne.doubleValue();
//
//                int quantity = noOfitems.intValue();
//
//                double realPricePerOne = 0.0;
//
//                System.out.println("Edit sales Price " + salesPrice);
//                System.out.println("Edit Quantity " + quantity);
//                System.out.println("Edit Discount " + discountPercentValue);
//                if (discountPercentValue > 0) {
//
//                    realPricePerOne = salesPrice - salesPrice * discountPercentValue / 100;
//
//
//                } else {
//
//                    realPricePerOne = salesPrice;
//
//
//                }
//
//                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
//
//                String companyBusinessDate = simpleDateFormat.format(editInvoiceDate);
//
//                double totalPerItemValue = realPricePerOne * quantity;
//
//                String salesExpression = Integer.toString(quantity) + " * " + Double.toString(realPricePerOne);
//
//                invoiceItemsPK.setItemCode(itemCode);
//
//                invoiceItems.setInvoiceItemsPK(invoiceItemsPK);
//                invoiceItems.setQuantity(noOfitems);
//                invoiceItems.setSalesPrice(amount);
//                invoiceItems.setRealPrice(totalPerItemValue);
//                invoiceItems.setItemName(itemName);
//                invoiceItems.setSalesExpression(salesExpression);
//                invoiceItems.setEnteredDate(companyBusinessDate);
//
//
//                editNewInvoiceItemsList.add(invoiceItems);
//
//
//
//            }
//
//        } else {
//        }
//
//        returnItemValueLb.setVisible(true);
//        returnValueLb.setVisible(true);
//        editCusInvoiceIdLb.setVisible(true);
//        editInvoiceIdValueLb.setVisible(true);
//
//        String selectedInvoiceid = (String) invoiceIdCb.getSelectedItem();
//
//        editInvoiceIdValueLb.setText(selectedInvoiceid);
//        returnValueLb.setText(Double.toString(returnValue));
//
//
//        isEditCustomerInvoice = true;
//
//        editCustomerInvoiceIF.setVisible(false);
//        customerInvoiceIF.setVisible(true);

        
        
        
        
        
    }//GEN-LAST:event_buyNewitemsBtnActionPerformed
    
    private void jMenuItem4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem4ActionPerformed
        
        new DeleteUserJFrame().setVisible(true);
        
    }//GEN-LAST:event_jMenuItem4ActionPerformed
    
    private void jMenuItem4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem4MouseClicked
    }//GEN-LAST:event_jMenuItem4MouseClicked
    
    private void jMenuItem4MouseReleased(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jMenuItem4MouseReleased
    }//GEN-LAST:event_jMenuItem4MouseReleased
    
    private void jMenuItem3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem3ActionPerformed
        
        viewMonthlyBusinessForCashierIF.setVisible(true);
        viewMonthlyBusinessIF.setVisible(false);
        newItemIF.setVisible(false);
        itemCostIF.setVisible(false);
        editItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        
    }//GEN-LAST:event_jMenuItem3ActionPerformed
    
    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        
        String month = (String) reMonthlyBusinessMonthCb1.getSelectedItem();
        String year = (String) reMonthlyBusinessYearCb1.getSelectedItem();
        
        reportManager.getMonthlyBusinessReportForCashier(month, year);
        
    }//GEN-LAST:event_jButton17ActionPerformed
    
    private void monthBusinessProfitBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_monthBusinessProfitBtnActionPerformed
        
        viewMonthlyBusinessIF.setVisible(true);
        newItemIF.setVisible(false);
        itemCostIF.setVisible(false);
        editItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        
    }//GEN-LAST:event_monthBusinessProfitBtnActionPerformed
    
    private void customerInvoiceChkbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerInvoiceChkbxActionPerformed
        
        
        if (customerInvoiceChkbx.isSelected()) {
            printerEnabled = true;
        } else {
            printerEnabled = false;
        }
    }//GEN-LAST:event_customerInvoiceChkbxActionPerformed
    
    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        
        
        Calendar businesCalendar = dailyBusinesItemsDf.getSelectedDate();
        
        if (businesCalendar.getTime() != null) {
            
            GregorianCalendar grCalendar = new GregorianCalendar();
            grCalendar.setTime(businesCalendar.getTime());
            
            int y = grCalendar.get(GregorianCalendar.YEAR);
            int m = grCalendar.get(GregorianCalendar.MONTH);
            int d = grCalendar.get(GregorianCalendar.DATE);
            
            String year = Integer.toString(y);
            
            GregorianCalendar dailyBusinessGrCalendar = new GregorianCalendar(y, m, d);
            
            
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            
            String companyBusinessDate = simpleDateFormat.format(dailyBusinessGrCalendar.getTime());
            
            reportManager.getDailyItemBusinessReport(companyBusinessDate);
            
        } else {
            
            JOptionPane.showMessageDialog(this, "Please Select Date", "Warn", JOptionPane.WARNING_MESSAGE);
            
        }
        
        
        
        
        
        
        
    }//GEN-LAST:event_jButton23ActionPerformed
    
    private void jMenuItem9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem9ActionPerformed
        
        dailtItemBusinessIF.setVisible(true);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        newItemIF.setVisible(false);
        itemCostIF.setVisible(false);
        editItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
    }//GEN-LAST:event_jMenuItem9ActionPerformed
    
    private void jMenu7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu7ActionPerformed
}//GEN-LAST:event_jMenu7ActionPerformed
    
    private void jMenuItem1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem1ActionPerformed
        
        viewExpenseIF.setVisible(true);
        newExpensesIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        newItemIF.setVisible(false);
        editItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        itemCostIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
}//GEN-LAST:event_jMenuItem1ActionPerformed
    
    private void jMenuItem23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem23ActionPerformed
        
        newExpensesIF.setVisible(true);
        customerInvoiceIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        newItemIF.setVisible(false);
        editItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        itemCostIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        
        nexpenseDate.requestFocus();
        
        
    }//GEN-LAST:event_jMenuItem23ActionPerformed
    
    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        
        
        String cashAmount = updateCashTf.getText();
        
        if (cashAmount != null && cashAmount.length() > 0) {
            
            if (isNumber(cashAmount)) {
                
                double cashValue = Double.parseDouble(cashAmount);
                
                String response = expenseManager.updateCashAmount(cashValue);
                
                if (response.equals("cashieramountupdatedsuccessfully")) {
                    
                    String newCashAmount = Double.toString(cashValue);
                    
                    cashAmountValueLb.setText(newCashAmount);
                    
                    updateCashTf.setText("");
                    
                    JOptionPane.showMessageDialog(this, "Cashier Amount Updated Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Error in updating cashier amount ", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "Invalid format for the Cash Amount", "Error", JOptionPane.ERROR_MESSAGE);
                updateCashTf.requestFocus();
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Cash Amount Cant be Empty", "Error", JOptionPane.ERROR_MESSAGE);
            updateCashTf.requestFocus();
        }
    }//GEN-LAST:event_jButton24ActionPerformed
    
    private void updateCashAmountMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateCashAmountMIActionPerformed
        
        updateCashAmount.setVisible(true);
        newExpensesIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        newItemIF.setVisible(false);
        editItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        itemCostIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        
    }//GEN-LAST:event_updateCashAmountMIActionPerformed
    
    private void openCashDrawyerChkbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openCashDrawyerChkbxActionPerformed
        
        if (openCashDrawyerChkbx.isSelected()) {
            isOpenCashDrawyer = true;
        } else {
            isOpenCashDrawyer = false;
        }
        
        
    }//GEN-LAST:event_openCashDrawyerChkbxActionPerformed
    
    private void percentRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_percentRadioActionPerformed
        
        discountCb.setEnabled(true);

//        percentRadio.setEnabled(true);
        percentRadio.setSelected(true);
        
        discountBtn.setEnabled(true);
//
        //   textAmountRadio.setEnabled(false);
        textAmountRadio.setSelected(false);
        textDiscountTf.setEnabled(false);
        textDiscountTf.setText("");
    }//GEN-LAST:event_percentRadioActionPerformed
    
    private void textAmountRadioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_textAmountRadioActionPerformed
        
        textAmountRadio.setEnabled(true);
        textAmountRadio.setSelected(true);
        textDiscountTf.setEnabled(true);
        textDiscountTf.setText("");
        
        discountBtn.setEnabled(true);
        
        discountCb.setEnabled(false);
        discountCb.setSelectedIndex(0);

        //    percentRadio.setEnabled(false);
        percentRadio.setSelected(false);
//

        
    }//GEN-LAST:event_textAmountRadioActionPerformed
    
    private void openCashDrawyerMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_openCashDrawyerMIActionPerformed
        
        openCashDrawer();
        
    }//GEN-LAST:event_openCashDrawyerMIActionPerformed
    
    private void CancelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelActionPerformed
        
        exInvoiceIdTf.setText("");
        exchangeItemCodeTf.setText("");
        exchangeNewItemCodeTf.setText("");
        exchangeNoofItemTf.setText("");
        
        
    }//GEN-LAST:event_CancelActionPerformed
    
    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        
        String exInvoiceId = exInvoiceIdTf.getText();
        
        if (exInvoiceId != null && exInvoiceId.length() > 0) {
            
            if (isNumber(exInvoiceId)) {
                
                
                String oldItemCode = exchangeItemCodeTf.getText();
                
                if (oldItemCode != null && oldItemCode.length() > 0) {
                    
                    if (isNumber(oldItemCode)) {
                        
                        String exchangeNewItem = exchangeNewItemCodeTf.getText();
                        
                        if (exchangeNewItem != null && exchangeNewItem.length() > 0) {
                            
                            if (isNumber(exchangeNewItem)) {
                                
                                String exchangeNoofItem = exchangeNoofItemTf.getText();
                                
                                if (exchangeNoofItem != null && exchangeNoofItem.length() > 0) {
                                    
                                    if (isNumber(exchangeNoofItem)) {
                                        
                                        ReturnExchangePK returnExchangePK = new ReturnExchangePK();
                                        returnExchangePK.setInvoiceId(new Integer(exInvoiceId));
                                        returnExchangePK.setItemCode(new Integer(oldItemCode));
                                        
                                        ReturnExchange returnExchange = new ReturnExchange();
                                        
                                        returnExchange.setReturnExchangePK(returnExchangePK);
                                        returnExchange.setReturnItemCode(new Integer(exchangeNewItem));
                                        returnExchange.setNumberOfItems(new Integer(exchangeNoofItem));
                                        returnExchange.setEnteredBy(username);
                                        returnExchange.setEnteredDate(new Date());
                                        
                                        String response = returnExchangeManager.addReturnExchange(returnExchange);
                                        
                                        if (response.equals(Constants.RETURN_EXCHANGE_ADDED_SUCCESSFULLY)) {
                                            
                                            JOptionPane.showMessageDialog(this, "Exchanged transfered Successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                                            
                                            
                                        } else if (response.equals(Constants.INVALID_INVOICE)) {
                                            
                                            JOptionPane.showMessageDialog(this, "Invalid Invoice ID.", "Exist", JOptionPane.ERROR_MESSAGE);
                                            
                                        } else if (response.equals(Constants.EXIST)) {
                                            
                                            JOptionPane.showMessageDialog(this, "Item already exchanged.", "Exist", JOptionPane.ERROR_MESSAGE);
                                            
                                        } else {
                                            JOptionPane.showMessageDialog(this, "Item exchanged Error", "Exist", JOptionPane.ERROR_MESSAGE);
                                        }
                                        
                                    } else {
                                        JOptionPane.showMessageDialog(this, "Invalid format for the No of Items", "Error", JOptionPane.ERROR_MESSAGE);
                                        exchangeNoofItemTf.requestFocus();
                                    }
                                    
                                } else {
                                    JOptionPane.showMessageDialog(this, "No of Items Cant be Empty", "Error", JOptionPane.ERROR_MESSAGE);
                                    exchangeNoofItemTf.requestFocus();
                                }
                                
                                
                                
                            } else {
                                JOptionPane.showMessageDialog(this, "Invalid format for the New Item Code ", "Error", JOptionPane.ERROR_MESSAGE);
                                exchangeNewItemCodeTf.requestFocus();
                            }
                            
                        } else {
                            JOptionPane.showMessageDialog(this, "New Item Code Cant be Empty", "Error", JOptionPane.ERROR_MESSAGE);
                            exchangeNewItemCodeTf.requestFocus();
                        }
                        
                        
                        
                    } else {
                        JOptionPane.showMessageDialog(this, "Invalid format for the Old Item Code ", "Error", JOptionPane.ERROR_MESSAGE);
                        exchangeItemCodeTf.requestFocus();
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Old Item Code Cant be Empty", "Error", JOptionPane.ERROR_MESSAGE);
                    exchangeItemCodeTf.requestFocus();
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "Invalid format for the InvoiceID", "Error", JOptionPane.ERROR_MESSAGE);
                updateCashTf.requestFocus();
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Invoice ID Cant be Empty", "Error", JOptionPane.ERROR_MESSAGE);
            exInvoiceIdTf.requestFocus();
        }
        
    }//GEN-LAST:event_jButton26ActionPerformed
    
    private void jMenuItem11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem11ActionPerformed
        
        invoiceRefresh();
        
    }//GEN-LAST:event_jMenuItem11ActionPerformed
    
    private void jMenuItem6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem6ActionPerformed
        
        loadStores();
//        refreshStores();

    }//GEN-LAST:event_jMenuItem6ActionPerformed
    
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        
        String selectedSupplierCode = (String) storesMgtSupplierCb.getSelectedItem();
        
        Iterator it = supplierList.iterator();
        
        Supplier supplier = null;
        while (it.hasNext()) {
            supplier = (Supplier) it.next();
            String supplierCode = supplier.getSuppliercode().toString();
            
            if (selectedSupplierCode.equals(supplierCode)) {
                
                break;
            }
        }
        
        
        String itemCode = (String) itemCodeStMgtCb.getSelectedItem();
        String itemName = itemNameStMgtTf.getText();
        
        Calendar exCalendar = purchaseDateStMgtDc.getSelectedDate();
        //exCalendar.setTimeZone(TimeZone.getTimeZone("GMT + 06:30"));
        //exCalendar.setTimeInMillis(System.currentTimeMillis());
        if (exCalendar != null) {
            
            GregorianCalendar grCalendar = new GregorianCalendar();
            grCalendar.setTime(exCalendar.getTime());
            
            int y = grCalendar.get(GregorianCalendar.YEAR);
            int m = grCalendar.get(GregorianCalendar.MONTH);
            int d = grCalendar.get(GregorianCalendar.DATE);
            
            String year = Integer.toString(y);
            
            String monthParam = null;
            
            
            switch (m + 1) {
                
                case 1:
                    monthParam = "January";
                    break;
                case 2:
                    monthParam = "Febuary";
                    break;
                case 3:
                    monthParam = "March";
                    break;
                case 4:
                    monthParam = "April";
                    break;
                case 5:
                    monthParam = "May";
                    break;
                case 6:
                    monthParam = "June";
                    break;
                case 7:
                    monthParam = "July";
                    break;
                case 8:
                    monthParam = "August";
                    break;
                case 9:
                    monthParam = "September";
                    break;
                case 10:
                    monthParam = "Octomber";
                    break;
                case 11:
                    monthParam = "November";
                    break;
                case 12:
                    monthParam = "December";
                    break;
                
            }
            
            
            GregorianCalendar storesMgtCalendar = new GregorianCalendar(y, m, d);
            
            String purQuantity = purchaseQuantityTf.getText();
            
            if (purQuantity != null && purQuantity.length() > 0) {
                
                if (isNumber(purQuantity)) {
                    
                    String rejQuantity = rejectedTf.getText();
                    
                    if (rejQuantity != null && rejQuantity.length() > 0) {
                        
                        if (isNumber(rejQuantity)) {
                            
                            
                            String acceptedQuantity = acceptedTf.getText();
                            
                            if (acceptedQuantity != null && acceptedQuantity.length() > 0) {
                                
                                if (isNumber(acceptedQuantity)) {
                                    
                                    
                                    String sellQuantity = sellTf.getText();
                                    
                                    if (sellQuantity != null && sellQuantity.length() > 0) {
                                        
                                        
                                        if (isNumber(sellQuantity)) {
                                            
                                            String avilableQuantity = avilableQyantityTf.getText();
                                            
                                            if (avilableQuantity != null && avilableQuantity.length() > 0) {
                                                
                                                if (isNumber(avilableQuantity)) {
                                                    
                                                    
                                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                                                    
                                                    String storesMgtDate = simpleDateFormat.format(storesMgtCalendar.getTime());
                                                    
                                                    StoresMgt storesMgt = new StoresMgt();
                                                    
                                                    StoresMgtPK storesMgtPK = new StoresMgtPK();
                                                    storesMgtPK.setItemCode(itemCode);
                                                    storesMgtPK.setPurchaseDate(storesMgtDate);
                                                    storesMgtPK.setSuppliercode(selectedSupplierCode);
                                                    storesMgt.setStoresMgtPK(storesMgtPK);
                                                    storesMgt.setItemName(itemName);
                                                    storesMgt.setPurchaseQuantity(new Integer(purQuantity));
                                                    storesMgt.setRejectedQuantity(new Integer(rejQuantity));
                                                    storesMgt.setAccptedQuantity(new Integer(acceptedQuantity));
                                                    storesMgt.setSellQuantity(new Integer(sellQuantity));
                                                    storesMgt.setPurchaseMonth(monthParam);
                                                    storesMgt.setPurchaseYear(year);
                                                    
                                                    storesMgt.setEnteredBy(username);
                                                    storesMgt.setEnteredDate(new Date());
                                                    
                                                    String response = storesManager.addStoresMgt(storesMgt, avilableQuantity);
                                                    
                                                    
                                                    Iterator storesIt = storesList.iterator();
                                                    
                                                    while (storesIt.hasNext()) {
                                                        
                                                        Stores stores = (Stores) storesIt.next();
                                                        
                                                        if (itemCode.equals(stores.getItemCode().toString())) {
                                                            stores.setQuantity(new Integer(avilableQuantity));
                                                        }
                                                    }
                                                    
                                                    if (response.equals(Constants.STORES_MGT_ADDED)) {
                                                        
                                                        purchaseQuantityTf.setText("");
                                                        rejectedTf.setText("");
                                                        acceptedTf.setText("");
                                                        sellTf.setText("");
                                                        avilableQyantityTf.setText("");
                                                        
                                                        JOptionPane.showMessageDialog(this, "Stores Details Added Successfully", "Success",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                        
                                                    } else if (response.equals(Constants.STORES_MGT_UPDATED)) {
                                                        purchaseQuantityTf.setText("");
                                                        rejectedTf.setText("");
                                                        acceptedTf.setText("");
                                                        sellTf.setText("");
                                                        avilableQyantityTf.setText("");
                                                        
                                                        JOptionPane.showMessageDialog(this, "Stores Details Updated Successfully", "Success",
                                                                JOptionPane.INFORMATION_MESSAGE);
                                                    } else if (response.equals(Constants.ERROR)) {
                                                        JOptionPane.showMessageDialog(this, "Storess Details Updating Error", "Error",
                                                                JOptionPane.ERROR_MESSAGE);
                                                    }
                                                    
                                                } else {
                                                    
                                                    JOptionPane.showMessageDialog(this, "Invalid format for the Avilable quantity", "Error",
                                                            JOptionPane.ERROR_MESSAGE);
                                                    avilableQyantityTf.requestFocus();
                                                    
                                                }
                                            } else {
                                                
                                                JOptionPane.showMessageDialog(this, "Avilable quantity can't be empty", "Error",
                                                        JOptionPane.ERROR_MESSAGE);
                                                avilableQyantityTf.requestFocus();
                                                
                                            }
                                            
                                            
                                            
                                        } else {
                                            
                                            JOptionPane.showMessageDialog(this, "Invalid format for the Sell quantity", "Error",
                                                    JOptionPane.ERROR_MESSAGE);
                                            sellTf.requestFocus();
                                            
                                        }
                                        
                                    } else {
                                        
                                        JOptionPane.showMessageDialog(this, "Sell quantity can't be empty", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                        sellTf.requestFocus();
                                    }
                                    
                                } else {
                                    JOptionPane.showMessageDialog(this, "Invalid format for the accepted quantity", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    acceptedTf.requestFocus();
                                }
                                
                                
                                
                            } else {
                                
                                JOptionPane.showMessageDialog(this, "Accepted quantity can't be empty", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                                acceptedTf.requestFocus();
                            }
                            
                            
                        } else {
                            JOptionPane.showMessageDialog(this, " Invalid format for the Rejected Quantity", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            rejectedTf.requestFocus();
                        }
                        
                        
                        
                    } else {
                        
                        JOptionPane.showMessageDialog(this, "Rejected quantity can't be empty", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        rejectedTf.requestFocus();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid format for the purchase quantity", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    purchaseQuantityTf.requestFocus();
                }
                
            } else {
                
                JOptionPane.showMessageDialog(this, "Purchase Quantity can't be empty", "Error",
                        JOptionPane.ERROR_MESSAGE);
                purchaseQuantityTf.requestFocus();
            }
            
            
        } else {
            
            JOptionPane.showMessageDialog(this, "Purchase Date can't be empty", "Error",
                    JOptionPane.ERROR_MESSAGE);
            purchaseDateStMgtDc.requestFocus();
            
        }
        
        
    }//GEN-LAST:event_jButton3ActionPerformed
    
    private void jMenuItem12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem12ActionPerformed
        
        storesIF.setVisible(true);
        newItemIF.setVisible(false);
        itemCostIF.setVisible(false);
        editItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        
        if (editDeleteSupplierCodeCb.getItemCount() == 0) {
            loadSupplierCodesForStoresMgt();
        }
        if (itemCodeStMgtCb.getItemCount() == 0) {
            
            
            loadItemCodesToStoresMgt();
            
        }
        
        
    }//GEN-LAST:event_jMenuItem12ActionPerformed
    
    private void itemCodeStMgtCbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_itemCodeStMgtCbItemStateChanged
        
        String selectItemValue = (String) itemCodeStMgtCb.getSelectedItem();
        
        Integer itemVaue = new Integer(selectItemValue);
        
        Iterator it = itemLists.iterator();
        
        boolean isNotExist = false;
        
        while (it.hasNext()) {
            
            Items item = (Items) it.next();
            
            
            if (itemVaue.equals(item.getItemCode())) {
                
                itemNameStMgtTf.setText(item.getItemName());
                
                isNotExist = false;
                break;
                
            } else {
                
                isNotExist = true;
            }
            
        }
        
        Iterator storesIt = storesList.iterator();
        
        
        
        while (storesIt.hasNext()) {
            
            Stores stores = (Stores) storesIt.next();
            
            
            if (itemVaue.equals(stores.getItemCode())) {
                
                avilableQyantityTf.setText(stores.getQuantity().toString());
                
                isNotExist = false;
                break;
                
            } else {
                
                isNotExist = true;
            }
            
        }
        
        
        if (isNotExist) {
            
            JOptionPane.showMessageDialog(this, "Invalid Item Code", "Warn",
                    JOptionPane.WARNING_MESSAGE);
        }
        
        
    }//GEN-LAST:event_itemCodeStMgtCbItemStateChanged
    
    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        
        purchaseQuantityTf.setText("");
        rejectedTf.setText("");
        acceptedTf.setText("");
        sellTf.setText("");
        avilableQyantityTf.setText("");
        
    }//GEN-LAST:event_jButton27ActionPerformed
    
    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        
        
        if (byDateChBx.isSelected()) {
            
            Calendar businesCalendar = viewStoresMgtDf.getSelectedDate();
            
            if (businesCalendar.getTime() != null) {
                
                GregorianCalendar grCalendar = new GregorianCalendar();
                grCalendar.setTime(businesCalendar.getTime());
                
                int y = grCalendar.get(GregorianCalendar.YEAR);
                int m = grCalendar.get(GregorianCalendar.MONTH);
                int d = grCalendar.get(GregorianCalendar.DATE);
                
                String year = Integer.toString(y);
                
                GregorianCalendar dailyBusinessGrCalendar = new GregorianCalendar(y, m, d);
                
                
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                
                String storesMgtDate = simpleDateFormat.format(dailyBusinessGrCalendar.getTime());
                
                
                reportManager.getDailyStoresMgt(storesMgtDate);
                
            } else {
                
                JOptionPane.showMessageDialog(this, "Please Select Date", "Warn", JOptionPane.WARNING_MESSAGE);
                
            }
            
        }
        if (byCodeChbx.isSelected()) {
            
            String selectedItemCode = (String) viewStMgtCb.getSelectedItem();
            
            reportManager.getItemCodeStoresMgtReport(new Integer(selectedItemCode));
            
        }
        
        
    }//GEN-LAST:event_jButton28ActionPerformed
    
    private void jMenuItem13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem13ActionPerformed
        
        
        if (viewStMgtCb.getItemCount() == 0) {
            
            
            loadItemCodesToViewStoresMgt();
            
        }
        
        viewStoresMgtIF.setVisible(true);
        
    }//GEN-LAST:event_jMenuItem13ActionPerformed
    
    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        
        String itemCode = (String) itemCodeStMgtCb.getSelectedItem();
        
        Calendar exCalendar = purchaseDateStMgtDc.getSelectedDate();
//exCalendar.setTimeZone(TimeZone.getTimeZone("GMT + 06:30"));
//exCalendar.setTimeInMillis(System.currentTimeMillis());

        GregorianCalendar grCalendar = new GregorianCalendar();
        grCalendar.setTime(exCalendar.getTime());
        
        int y = grCalendar.get(GregorianCalendar.YEAR);
        int m = grCalendar.get(GregorianCalendar.MONTH);
        int d = grCalendar.get(GregorianCalendar.DATE);
        
        String year = Integer.toString(y);
        
        String monthParam = null;
        
        
        switch (m + 1) {
            
            case 1:
                monthParam = "January";
                break;
            case 2:
                monthParam = "Febuary";
                break;
            case 3:
                monthParam = "March";
                break;
            case 4:
                monthParam = "April";
                break;
            case 5:
                monthParam = "May";
                break;
            case 6:
                monthParam = "June";
                break;
            case 7:
                monthParam = "July";
                break;
            case 8:
                monthParam = "August";
                break;
            case 9:
                monthParam = "September";
                break;
            case 10:
                monthParam = "Octomber";
                break;
            case 11:
                monthParam = "November";
                break;
            case 12:
                monthParam = "December";
                break;
            
        }
        
        
        GregorianCalendar storesMgtCalendar = new GregorianCalendar(y, m, d);
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        
        String storesMgtDate = simpleDateFormat.format(storesMgtCalendar.getTime());
        
        StoresMgt storesMgt = storesManager.getStroesDetailsForDate(itemCode, storesMgtDate);
        
        if (storesMgt != null) {
            
            String purchaseQuantity = Integer.toString(storesMgt.getPurchaseQuantity());
            String rejected = Integer.toString(storesMgt.getRejectedQuantity());
            String accepted = Integer.toString(storesMgt.getAccptedQuantity());
            String sell = Integer.toString(storesMgt.getSellQuantity());
            String avilable = storesMgt.getAvilableQuantity();
            
            purchaseQuantityTf.setText(purchaseQuantity);
            rejectedTf.setText(rejected);
            acceptedTf.setText(accepted);
            sellTf.setText(sell);
            avilableQyantityTf.setText(avilable);
            
            
        } else {
            purchaseQuantityTf.setText("");
            rejectedTf.setText("");
            acceptedTf.setText("");
            sellTf.setText("");
            avilableQyantityTf.setText("");
            
            JOptionPane.showMessageDialog(this, "There is no data to relevant fields", "Empty", JOptionPane.ERROR_MESSAGE);
            
            purchaseQuantityTf.requestFocus();
        }
        
    }//GEN-LAST:event_jButton29ActionPerformed
    
    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
        
        String itemCode = (String) itemCodeStMgtCb.getSelectedItem();
        
        
        Stores stores = storesManager.getStroesDetailsForItemCode(itemCode);
        
        if (stores != null) {
            
            String avilableQyantity = Integer.toString(stores.getQuantity());
            
            avilableQyantityTf.setText(avilableQyantity);
            
            purchaseQuantityTf.setText("");
            rejectedTf.setText("");
            acceptedTf.setText("");
            sellTf.setText("");
            
        } else {
            avilableQyantityTf.setText("");
            
            JOptionPane.showMessageDialog(this, "There is no data to relevant fields", "Empty", JOptionPane.ERROR_MESSAGE);
            
            purchaseQuantityTf.requestFocus();
        }
        
    }//GEN-LAST:event_jButton30ActionPerformed
    
    private void byDateChBxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_byDateChBxActionPerformed
        
        
        boolean date = byDateChBx.isSelected();
        
        if (date == true) {
            byDateChBx.setSelected(true);
            byCodeChbx.setSelected(false);
        } else {
            byDateChBx.setSelected(false);
            byCodeChbx.setSelected(true);
        }
    }//GEN-LAST:event_byDateChBxActionPerformed
    
    private void byCodeChbxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_byCodeChbxActionPerformed
        
        
        
        
        boolean code = byCodeChbx.isSelected();
        
        if (code == true) {
            byDateChBx.setSelected(false);
            byCodeChbx.setSelected(true);
            
        } else {
            byDateChBx.setSelected(true);
            byCodeChbx.setSelected(false);
        }
        
        
    }//GEN-LAST:event_byCodeChbxActionPerformed
    
    private void viewStMgtCbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_viewStMgtCbItemStateChanged
        
        
        
        String selectItemValue = (String) viewStMgtCb.getSelectedItem();
        
        Integer itemVaue = new Integer(selectItemValue);
        
        Iterator it = itemLists.iterator();
        
        boolean isNotExist = false;
        
        while (it.hasNext()) {
            
            Items item = (Items) it.next();
            
            
            if (itemVaue.equals(item.getItemCode())) {
                
                viewStMgtTf.setText(item.getItemName());
                
                isNotExist = false;
                break;
                
            } else {
                
                isNotExist = true;
            }
            
        }
        
        if (isNotExist) {
            
            JOptionPane.showMessageDialog(this, "Invalid Item Code", "Warn",
                    JOptionPane.WARNING_MESSAGE);
        }
        
    }//GEN-LAST:event_viewStMgtCbItemStateChanged
    
    private void itemWiseChkBxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemWiseChkBxActionPerformed
        
        if (itemWiseChkBx.isSelected()) {
            
            itemWisePercentRB.setEnabled(true);
            itemWiseTextRB.setEnabled(true);
            itemWiseDiscountTF.setEnabled(true);
            discountBtn.setEnabled(true);
            discountItemWiseCB.setEnabled(true);
            
        } else {
            
            itemWisePercentRB.setEnabled(false);
            itemWiseTextRB.setEnabled(false);
            itemWiseDiscountTF.setEnabled(false);
            discountBtn.setEnabled(false);
            discountItemWiseCB.setEnabled(false);
        }
        
    }//GEN-LAST:event_itemWiseChkBxActionPerformed
    
    private void itemWisePercentRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemWisePercentRBActionPerformed
        
        
        itemWisePercentRB.setSelected(true);
        itemWiseTextRB.setSelected(false);
        discountBtn.setEnabled(true);
    }//GEN-LAST:event_itemWisePercentRBActionPerformed
    
    private void itemWiseTextRBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemWiseTextRBActionPerformed
        
        itemWisePercentRB.setSelected(false);
        discountBtn.setEnabled(true);
        itemWiseTextRB.setSelected(true);
        
    }//GEN-LAST:event_itemWiseTextRBActionPerformed
    
    private void discountItemWiseCBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_discountItemWiseCBActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_discountItemWiseCBActionPerformed
    
    private void itemWiseDiscountTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemWiseDiscountTFActionPerformed
        
        
        if (!isDiscountAdded) {
            discount();
        }
    }//GEN-LAST:event_itemWiseDiscountTFActionPerformed
    
    private void itemTypeSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTypeSaveBtnActionPerformed
        
        
        String itemTypeName = itemTypeNameTf.getText();
        
        if (itemTypeName != null && itemTypeName.length() > 0) {
            
            ItemType itemType = new ItemType();
            itemType.setItemtypename(itemTypeName);
            itemType.setEnteredby(username);
            itemType.setEntereddate(new Date());
            
            String response = itemManager.addItemType(itemType);
            
            if (response.equals(Constants.ITEM_TYPE_ADDED_SUCCESSFULLY)) {
                JOptionPane.showMessageDialog(this, "Item Type Added Successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                itemTypeNameTf.setText("");
                itemTypeNameTf.requestFocus();
            } else if (response.equals(Constants.ITEM_TYPE_EXIST)) {
                JOptionPane.showMessageDialog(this, "Item Type Already exist.", "Warn", JOptionPane.WARNING_MESSAGE);
            } else if (response.equals(Constants.ITEM_TYPE_ADDED_ERROR)) {
                JOptionPane.showMessageDialog(this, "Item Type Added Error", "Warn", JOptionPane.WARNING_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Type Name can't be empty");
            itemTypeNameTf.getText();
        }
        
        
    }//GEN-LAST:event_itemTypeSaveBtnActionPerformed
    
    private void editNewTypeNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editNewTypeNameTFActionPerformed
        
        String newTypeName = newTypeNameTF.getText();
        
        if (newTypeName != null && newTypeName.length() > 0) {
            
            String oldItemTypeName = (String) editOldTypeNameCb.getSelectedItem();
            ItemType itemType = new ItemType();
            itemType.setItemtypename(newTypeName);
            
            String response = itemManager.editItemType(itemType, oldItemTypeName);
            
            if (response.equals(Constants.ITEM_TYPE_UPDATED_SUCCESSFULLY)) {
                JOptionPane.showMessageDialog(this, "Item Type Updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                editOldTypeNameCb.removeItem(oldItemTypeName);
                editOldTypeNameCb.addItem(newTypeName);
                newTypeNameTF.setText("");
            } else if (response.equals(Constants.ITEM_TYPE_NOT_EXIST)) {
                JOptionPane.showMessageDialog(this, "Selected Item Type Name Not exist ", "Error", JOptionPane.ERROR_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error in updating the item type name.Please contact system administrator", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            
            JOptionPane.showMessageDialog(this, "Please enter New Item Type Name", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
    }//GEN-LAST:event_editNewTypeNameTFActionPerformed
    
    private void jMenuItem15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem15ActionPerformed
        
        
        if (editOldTypeNameCb.getItemCount() == 0) {
            
            loadItemTypes();
            
        }
        editItemTypeIF.setVisible(true);
        newItemTypeIF.setVisible(false);
        editItemIF.setVisible(false);
        newItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        itemCostIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        
        
        
    }//GEN-LAST:event_jMenuItem15ActionPerformed
    
    private void jMenuItem14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem14ActionPerformed
        
        newItemTypeIF.setVisible(true);
        editItemTypeIF.setVisible(false);
        editItemIF.setVisible(false);
        newItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        itemCostIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        
        
        
        
    }//GEN-LAST:event_jMenuItem14ActionPerformed
    
    private void itemTypeCancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemTypeCancelBtnActionPerformed
        itemTypeNameTf.setText("");
    }//GEN-LAST:event_itemTypeCancelBtnActionPerformed
    
    private void itemRemoveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRemoveBtnActionPerformed
        
        if (removeItemCb.getItemCount() > 0) {
            
            String selectedItemCode = (String) removeItemCb.getSelectedItem();
            
            Integer itemCode = new Integer(selectedItemCode);
            int noOfRows = invoiceTable.getRowCount();
            boolean notExisted = true;
            System.out.println("No of rows >>> " + noOfRows);
            
            int x = 0;
            while (noOfRows > 0 && x < noOfRows) {
                
                Integer itemCodes = (Integer) invoiceTable.getValueAt(x, 0);
                
                if (itemCodes.intValue() == itemCode.intValue()) {
                    String itemName = (String) invoiceTable.getValueAt(x, 1);
                    Double amount = (Double) invoiceTable.getValueAt(x, 4);
                    Integer removeQuantity = (Integer) invoiceTable.getValueAt(x, 3);
                    Double discount = (Double) invoiceTable.getValueAt(x, 5);
                    
                    double itemRealSellAmount = amount.doubleValue() - discount.doubleValue();
                    
                    String noOfItemsLb = noOfIemsLb.getText();
                    int noOfItemsValue = Integer.parseInt(noOfItemsLb);
                    
                    int newNoOfQtyValue = noOfItemsValue - removeQuantity;
                    
                    noOfIemsLb.setText(Integer.toString(newNoOfQtyValue));

                    //    double newAmountValue = price * quantity;

                    //  amount = amount + newAmountValue;

                    //int newQuantitiy = oldQuantity + quantity;

                    //invoiceTable.setValueAt(new Integer(newQuantitiy), x, 3);
                    //invoiceTable.setValueAt(new Double(amount), x, 4);

                    double totAmountValue = Double.parseDouble(totalAmountLb.getText());
                    
                    System.out.println("totAmountValue >>> " + totAmountValue);
                    totAmountValue = totAmountValue - itemRealSellAmount;
                    
                    String totalAmount = Double.toString(totAmountValue);
                    
                    
                    double roundNewTotal = round(totAmountValue, 2);
                    
                    String newTotalAmount = Double.toString(roundNewTotal);
                    
                    removeItemCb.removeItem(selectedItemCode);
                    discountItemWiseCB.removeItem(itemName);
                    
                    totalAmountValue = roundNewTotal;
                    noOfItems = newNoOfQtyValue;
                    
                    totalAmountLb.setText(newTotalAmount);
                    
                    DefaultTableModel model = new DefaultTableModel();
                    model = (DefaultTableModel) invoiceTable.getModel();
                    
                    model.removeRow(x);
                    
                    noOfRows = noOfRows - 1;
                }
                
                x++;
            }
            
            if (removeItemCb.getItemCount() == 0) {
                invoiceRefresh();
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "There is no Items to remove.", "Error", JOptionPane.ERROR_MESSAGE);
            invoiceRefresh();
        }
        
        
        
        
    }//GEN-LAST:event_itemRemoveBtnActionPerformed
    
    private void jMenu5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenu5ActionPerformed
        
        customerInvoiceIF.setVisible(true);
        newItemIF.setVisible(false);
        editItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        
        invoiceItemCodeCb.requestFocus();
        noOfItemsTf.setText("");
}//GEN-LAST:event_jMenu5ActionPerformed
    
    private void jMenuItem8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem8ActionPerformed
        
        exInvoiceIdTf.requestFocus();
        
        customerExchangeIF.setVisible(true);
        newOrdersIF.setVisible(false);
        
        newItemIF.setVisible(false);
        itemCostIF.setVisible(false);
        editItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
    }//GEN-LAST:event_jMenuItem8ActionPerformed
    
    private void jMenuItem22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem22ActionPerformed
        
        newOrdersIF.setVisible(true);
        
        newItemIF.setVisible(false);
        itemCostIF.setVisible(false);
        editItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
    }//GEN-LAST:event_jMenuItem22ActionPerformed
    
    private void editCustomerInvoiceMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editCustomerInvoiceMIActionPerformed
        
        editCustomerInvoiceIF.setVisible(true);
        customerInvoiceIF.setVisible(false);
        newItemIF.setVisible(false);
        editItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        newExpensesIF.setVisible(false);
        itemCostIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        invoiceItemCodeCb.requestFocus();
        noOfItemsTf.setText("");
        
        
        
        
}//GEN-LAST:event_editCustomerInvoiceMIActionPerformed
    
    private void customerInvoiceMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_customerInvoiceMIActionPerformed
        
        customerInvoiceIF.setVisible(true);
        newItemIF.setVisible(false);
        editItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        itemCostIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        
        invoiceItemCodeCb.requestFocus();
        noOfItemsTf.setText("");
    }//GEN-LAST:event_customerInvoiceMIActionPerformed
    
    private void loadInvoiceIdBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loadInvoiceIdBtnActionPerformed
        
        String selectedInvoiceIdPeriod = (String) loadInvoiceIdCb.getSelectedItem();
        
        if (selectedInvoiceIdPeriod != null && selectedInvoiceIdPeriod.length() > 0) {
            
            invoiceIdCb.removeAllItems();
            List<CustomerInvoice> customerInvoicesList = customerInvoiceManager.loadCustomerInvoicesByIdPPeriod(selectedInvoiceIdPeriod);
            
            if (customerInvoicesList != null && customerInvoicesList.size() > 0) {
                
                System.out.println("customerInvoicesList >> " + customerInvoicesList.size());
                
                Iterator<CustomerInvoice> it = customerInvoicesList.iterator();
                while (it.hasNext()) {
                    
                    CustomerInvoice customerInvoice = it.next();
                    
                    String invoiceId = customerInvoice.getInvoiceId().toString();
                    System.out.println("");
                    invoiceIdCb.addItem(invoiceId);
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "There are no invoiceids for the relevant period", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
    }//GEN-LAST:event_loadInvoiceIdBtnActionPerformed
    
    private void invoiceReprintBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_invoiceReprintBtnActionPerformed
        
        String selectedInvoiceId = (String) invoiceIdCb.getSelectedItem();
        reportManager.printCustomerInvoice(selectedInvoiceId, username);
        
    }//GEN-LAST:event_invoiceReprintBtnActionPerformed
    
    private void updateStoresReportMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateStoresReportMIActionPerformed
        reportManager.getStoresReport();
    }//GEN-LAST:event_updateStoresReportMIActionPerformed
    
    private void addSupplierSaveBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSupplierSaveBtnActionPerformed
        String supplierCode = addSuuplierCodeTF.getText();
        
        if (supplierCode != null && supplierCode.length() > 0) {
            
            String supplierName = addSupplierNameTF.getText();
            
            if (supplierName != null && supplierName.length() > 0) {
                
                String contactNumber = addSupplierContactNumberTF.getText();
                Supplier supplier = new Supplier();
                supplier.setSuppliercode(supplierCode);
                supplier.setSuppliername(supplierName);
                supplier.setSuppliercontactnumber(contactNumber);
                
                String response = supplietManager.addSupplier(supplier);
                
                if (response.equals(Constants.SUPPLIER_ADDED_SUCCESSFULLY)) {
                    addSuuplierCodeTF.setText("");
                    addSupplierNameTF.setText("");
                    addSupplierContactNumberTF.setText("");
                    
                    JOptionPane.showMessageDialog(this, "Supplier Added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else if (response.equals(Constants.SUPPLIER_ALREADY_EXIST)) {
                    JOptionPane.showMessageDialog(this, "Supplier Code Already Exist", "Warn", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Supplier adding error. Please contact system administrator", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "Supplier Name cant be empty.", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Supplier Code cant be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_addSupplierSaveBtnActionPerformed
    
    private void addSupplierNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSupplierNameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_addSupplierNameTFActionPerformed
    
    private void addSupplierCancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSupplierCancelBtnActionPerformed
        
        addSupplierNameTF.setText("");
        addSuuplierCodeTF.setText("");
        addSupplierContactNumberTF.setText("");
        
    }//GEN-LAST:event_addSupplierCancelBtnActionPerformed
    
    private void addSupplierMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addSupplierMIActionPerformed
        
        addSupplierIF.setVisible(true);
        editDeleteSupplierIF.setVisible(false);
        newItemTypeIF.setVisible(false);
        editItemTypeIF.setVisible(false);
        editItemIF.setVisible(false);
        newItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        itemCostIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        
    }//GEN-LAST:event_addSupplierMIActionPerformed
    
    private void editDeleteSupplierNameTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editDeleteSupplierNameTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_editDeleteSupplierNameTFActionPerformed
    
    private void editSupplierBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSupplierBtnActionPerformed
        
        
        String supplierName = editDeleteSupplierNameTF.getText();
        
        if (supplierName != null && supplierName.length() > 0) {
            
            String contactNumber = editDeleteSupplierContactNumberTF.getText();
            Supplier supplier = new Supplier();
            
            selectedSupplierObj.setSuppliername(supplierName);
            selectedSupplierObj.setSuppliercontactnumber(contactNumber);
            
            editDeleteSupplierNameTF.setText("");
            editDeleteSupplierContactNumberTF.setText("");
            
            
            String response = supplietManager.editSupplier(selectedSupplierObj);
            
            if (response.equals(Constants.SUPPLIER_UPDATED_SUCCESSFULLY)) {
                addSuuplierCodeTF.setText("");
                addSupplierNameTF.setText("");
                addSupplierContactNumberTF.setText("");
                
                JOptionPane.showMessageDialog(this, "Supplier Updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else if (response.equals(Constants.SUPPLIER_NOT_EXIST)) {
                JOptionPane.showMessageDialog(this, "Supplier Code Not Exist", "Warn", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Supplier updating error. Please contact system administrator", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Supplier Name cant be empty.", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
        
        
        
        
        
    }//GEN-LAST:event_editSupplierBtnActionPerformed
    
    private void editDelteSupplierCancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editDelteSupplierCancelBtnActionPerformed
    }//GEN-LAST:event_editDelteSupplierCancelBtnActionPerformed
    
    private void editDeleteSupplierMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editDeleteSupplierMIActionPerformed
        
        editDeleteSupplierIF.setVisible(true);
        addSupplierIF.setVisible(false);
        newItemTypeIF.setVisible(false);
        editItemTypeIF.setVisible(false);
        editItemIF.setVisible(false);
        newItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        itemCostIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        
        if (editDeleteSupplierCodeCb.getItemCount() == 0) {
            loadSupplierCodesForEditDeleteSupplier();
        }
        
    }//GEN-LAST:event_editDeleteSupplierMIActionPerformed
    
    private void editDeleteSupplierCodeCbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_editDeleteSupplierCodeCbItemStateChanged
        
        String selectedSupplierCode = (String) editDeleteSupplierCodeCb.getSelectedItem();
        
        Iterator it = supplierList.iterator();
        
        
        while (it.hasNext()) {
            selectedSupplierObj = (Supplier) it.next();
            String supplierCode = selectedSupplierObj.getSuppliercode().toString();
            
            if (selectedSupplierCode.equals(supplierCode)) {
                
                String supplierName = selectedSupplierObj.getSuppliername();
                String contactNumber = selectedSupplierObj.getSuppliercontactnumber();
                
                editDeleteSupplierNameTF.setText(supplierName);
                editDeleteSupplierNameTF.setText(contactNumber);
                break;
            }
        }
        
        
    }//GEN-LAST:event_editDeleteSupplierCodeCbItemStateChanged
        
    private void deleteSupplierBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteSupplierBtnActionPerformed
        
        
        int status = JOptionPane.showConfirmDialog(this, "Are you sure want to Delete this Supplier ?", "Confirm", JOptionPane.YES_NO_OPTION);
        
        if (status == 0) {
            
            
            String selectedSupplierCode = (String) editDeleteSupplierCodeCb.getSelectedItem();
            String response = supplietManager.deleteSupplier(selectedSupplierObj);
            
            if (response.equals(Constants.SUPPLIER_DELETED_SUCCESSFULLY)) {
                editDeleteSupplierNameTF.setText("");
                editDeleteSupplierContactNumberTF.setText("");
                editDeleteSupplierCodeCb.removeItem(selectedSupplierCode);
                
                JOptionPane.showMessageDialog(this, "Supplier Deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else if (response.equals(Constants.SUPPLIER_NOT_EXIST)) {
                JOptionPane.showMessageDialog(this, "Supplier Code Not Exist", "Warn", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Supplier deleteing error. Please contact system administrator", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        }
        
        
    }//GEN-LAST:event_deleteSupplierBtnActionPerformed
    
    private void storesMgtSupplierCbItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_storesMgtSupplierCbItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_storesMgtSupplierCbItemStateChanged
    
    private void jMenuItem16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem16ActionPerformed
        reportManager.getReOrderReport();
    }//GEN-LAST:event_jMenuItem16ActionPerformed
    
    private void itemRejectionsMIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRejectionsMIActionPerformed
        
        itemRejectionReportIF.setVisible(true);
        editDeleteSupplierIF.setVisible(false);
        addSupplierIF.setVisible(false);
        newItemTypeIF.setVisible(false);
        editItemTypeIF.setVisible(false);
        editItemIF.setVisible(false);
        newItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        itemCostIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        
        if (itemRejectionReportItemCodeCb.getItemCount() == 0) {
            loadItemCodesForItemRejection();
        }
        
    }//GEN-LAST:event_itemRejectionsMIActionPerformed
    
    private void itemRectionReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_itemRectionReportBtnActionPerformed
        
        String itemCode = (String) itemRejectionReportItemCodeCb.getSelectedItem();
        
        String month = (String) itemRejectionReportMonthCb.getSelectedItem();
        String year = (String) itemRejectionReportYearCb.getSelectedItem();
        
        reportManager.getItemRejectionReport(month, year, new Integer(itemCode).intValue());
        
    }//GEN-LAST:event_itemRectionReportBtnActionPerformed
    
    private void supplierRectionReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierRectionReportBtnActionPerformed
        
        String supplierCode = (String) supplierRejectionReportSupplierCodeCb.getSelectedItem();
        
        String month = (String) supplierRejectionReportMonthCb.getSelectedItem();
        String year = (String) supplierRejectionReportYearCb.getSelectedItem();
        
        reportManager.getSupplierRejectionReport(month, year, supplierCode);
        
    }//GEN-LAST:event_supplierRectionReportBtnActionPerformed
    
    private void jMenuItem18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem18ActionPerformed
        
        supplierRejectionReportIF.setVisible(true);
        itemRejectionReportIF.setVisible(false);
        editDeleteSupplierIF.setVisible(false);
        addSupplierIF.setVisible(false);
        newItemTypeIF.setVisible(false);
        editItemTypeIF.setVisible(false);
        editItemIF.setVisible(false);
        newItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        itemCostIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        
        if (supplierRejectionReportSupplierCodeCb.getItemCount() == 0) {
            loadSupplierCodesForSupplierRejection();
        }
        
        
    }//GEN-LAST:event_jMenuItem18ActionPerformed
    
    private void supplierDeliveredReportBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_supplierDeliveredReportBtnActionPerformed
        
        String supplierCode = (String) supplierDeliveredReportSupplierCodeCb.getSelectedItem();
        
        String month = (String) supplierDeliveredReportMonthCb.getSelectedItem();
        String year = (String) supplierDeliveredReportYearCb.getSelectedItem();
        
        reportManager.getSupplierDeliveredReport(month, year, supplierCode);
        
    }//GEN-LAST:event_supplierDeliveredReportBtnActionPerformed
    
    private void jMenuItem17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem17ActionPerformed
        
        supplierDeliveredReportIF.setVisible(true);
        supplierRejectionReportIF.setVisible(false);
        itemRejectionReportIF.setVisible(false);
        editDeleteSupplierIF.setVisible(false);
        addSupplierIF.setVisible(false);
        newItemTypeIF.setVisible(false);
        editItemTypeIF.setVisible(false);
        editItemIF.setVisible(false);
        newItemIF.setVisible(false);
        updateStoresIF.setVisible(false);
        customerInvoiceIF.setVisible(false);
        newExpensesIF.setVisible(false);
        editCustomerInvoiceIF.setVisible(false);
        itemCostIF.setVisible(false);
        newOrdersIF.setVisible(false);
        viewExpenseIF.setVisible(false);
        viewMonthlyBusinessIF.setVisible(false);
        viewMonthlyBusinessForCashierIF.setVisible(false);
        dailtItemBusinessIF.setVisible(false);
        updateCashAmount.setVisible(false);
        customerExchangeIF.setVisible(false);
        storesIF.setVisible(false);
        viewStoresMgtIF.setVisible(false);
        
        if (supplierDeliveredReportSupplierCodeCb.getItemCount() == 0) {
            loadSupplierCodesForSupplierDelivered();
        }
        
        
    }//GEN-LAST:event_jMenuItem17ActionPerformed
    
    private void jMenuItem19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItem19ActionPerformed
        
        reportManager.viewSuppliersReport();
    }//GEN-LAST:event_jMenuItem19ActionPerformed
    
    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        FindByItemNameFrame findByItemNameFrame = new FindByItemNameFrame(this);
        findByItemNameFrame.setVisible(true);
        
    }//GEN-LAST:event_jButton16ActionPerformed
    
    private void saveSalesRefBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_saveSalesRefBtnActionPerformed
        
        String salesRefName = newSalesRefTF.getText();
        
        if (salesRefName != null || salesRefName.length() > 0) {
            
            Salesref salesref = new Salesref(salesRefName, true);
            
            String response = salesRefManager.addSalesRef(salesref);
            
            if (response.equals(Constants.SALES_REF_ADDED_SUCCESSFULLY)) {
                newSalesRefTF.setText("");
                
                JOptionPane.showMessageDialog(this, "Sales Ref Added successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
            } else if (response.equals(Constants.USER_EXIST)) {
                JOptionPane.showMessageDialog(this, "Sales Ref Code Already Exist", "Warn", JOptionPane.WARNING_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Sales Ref adding error. Please contact system administrator", "Error", JOptionPane.ERROR_MESSAGE);
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Please enter Sales Ref Name", "Error", JOptionPane.ERROR_MESSAGE);
        }
        
    }//GEN-LAST:event_saveSalesRefBtnActionPerformed
    
    private void editSalesRefLoadUsersBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSalesRefLoadUsersBtnActionPerformed
        
        
        salesRefList = salesRefManager.loadSalesRef();
        
        int noOfItems = editSalesRefCB.getItemCount();
        
        if (noOfItems != 0) {
            
            
            
            editSalesRefCB.removeAllItems();
            
        }
        
        
        Iterator<Salesref> it = salesRefList.iterator();
        
        while (it.hasNext()) {
            Salesref salesref = it.next();
            String salesRefId = salesref.getSalesrefid();
            
            if (salesRefId != null) {
                editSalesRefCB.addItem(salesRefId);
            }
            
        }
        
        int noOfItems2 = editSalesRefCB.getItemCount();
        
        if (noOfItems2 != 0) {
            editSalesRefCB.setEnabled(true);
            editSalesRefStatusCB.setEnabled(true);
            editSalesRefUpdateBtn.setEnabled(true);
            editSalesRefDeleteBtn.setEnabled(true);
            displayStatusBtn.setEnabled(true);
        } else {
            editSalesRefCB.setEnabled(false);
            editSalesRefStatusCB.setEnabled(false);
            editSalesRefUpdateBtn.setEnabled(false);
            editSalesRefDeleteBtn.setEnabled(false);
            displayStatusBtn.setEnabled(false);
        }
        
    }//GEN-LAST:event_editSalesRefLoadUsersBtnActionPerformed
    
    private void editSalesRefUpdateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSalesRefUpdateBtnActionPerformed
        
        
        String selectedSalesRefId = (String) editSalesRefCB.getSelectedItem();
        
        Iterator<Salesref> it = salesRefList.iterator();
        
        while (it.hasNext()) {
            
            Salesref salesref = it.next();
            
            String salesRefId = salesref.getSalesrefid();
            
            if (salesRefId != null && selectedSalesRefId.equals(salesRefId)) {
                if (editSalesRefStatusCB.isSelected()) {
                    salesref.setRefstatus(false);
                } else {
                    salesref.setRefstatus(true);
                }
                
                String response = salesRefManager.updateSalesRef(salesref);
                
                if (response.equals(Constants.SALES_REF_UPDATED_SUCCESSFULLY)) {
                    
                    JOptionPane.showMessageDialog(this, "Sales Ref Updated successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else if (response.equals(Constants.NOT_EXIST)) {
                    JOptionPane.showMessageDialog(this, "Sales Ref Code Not Exist", "Warn", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Sales Ref Updating error. Please contact system administrator", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                break;
                
            }
            
        }
        
        
        
    }//GEN-LAST:event_editSalesRefUpdateBtnActionPerformed
    
    private void editSalesRefDeleteBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editSalesRefDeleteBtnActionPerformed
      
        
        String selectedSalesRefId = (String) editSalesRefCB.getSelectedItem();
        
        Iterator<Salesref> it = salesRefList.iterator();
        
        while (it.hasNext()) {
            
            Salesref salesref = it.next();
            
            String salesRefId = salesref.getSalesrefid();
            
            if (salesRefId != null && selectedSalesRefId.equals(salesRefId)) {
                if (editSalesRefStatusCB.isSelected()) {
                    salesref.setRefstatus(false);
                } else {
                    salesref.setRefstatus(true);
                }
                
                String response = salesRefManager.deleteSalesRef(salesref);
                
                if (response.equals(Constants.SALES_REF_DELETED_SUCCESSFULLY)) {
                    editSalesRefCB.removeItem(selectedSalesRefId);
                    JOptionPane.showMessageDialog(this, "Sales Ref Deleted successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
                } else if (response.equals(Constants.NOT_EXIST)) {
                    JOptionPane.showMessageDialog(this, "Sales Ref Code Not Exist", "Warn", JOptionPane.WARNING_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(this, "Sales Ref Deketing error. Please contact system administrator", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                break;
                
            }
            
        }
        
    }//GEN-LAST:event_editSalesRefDeleteBtnActionPerformed
    
    private void editSalesRefCBItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_editSalesRefCBItemStateChanged
        
        
     
        
        
    }//GEN-LAST:event_editSalesRefCBItemStateChanged

    private void displayStatusBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_displayStatusBtnActionPerformed
      
        String selectedSalesRefId = (String) editSalesRefCB.getSelectedItem();
        
        Iterator<Salesref> it = salesRefList.iterator();
        
        while (it.hasNext()) {
            
            Salesref salesref = it.next();
            
            String salesRefId = salesref.getSalesrefid();
            
            if (salesRefId != null && selectedSalesRefId.equals(salesRefId)) {
                
                editSalesRefStatusCB.setSelected(!salesref.getRefstatus());
                               
                break;
                
            }
            
        }
        
    }//GEN-LAST:event_displayStatusBtnActionPerformed
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainJFrame().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Cancel;
    private javax.swing.JTextField acceptedTf;
    private javax.swing.JButton addSupplierCancelBtn;
    private javax.swing.JLabel addSupplierContactNumberLB;
    private javax.swing.JLabel addSupplierContactNumberLB1;
    private javax.swing.JTextField addSupplierContactNumberTF;
    private javax.swing.JInternalFrame addSupplierIF;
    private javax.swing.JLabel addSupplierLB;
    private javax.swing.JLabel addSupplierLB1;
    private javax.swing.JMenuItem addSupplierMI;
    private javax.swing.JTextField addSupplierNameTF;
    private javax.swing.JButton addSupplierSaveBtn;
    private javax.swing.JTextField addSuuplierCodeTF;
    private javax.swing.JTextField avilableQyantityTf;
    private javax.swing.JLabel balanceLb;
    private javax.swing.JCheckBox barCodeChkbx;
    private javax.swing.JButton businessBtn;
    private javax.swing.JComboBox businessByCB;
    private javax.swing.JLabel businessByLB;
    private javax.swing.JButton buyNewitemsBtn;
    private javax.swing.JCheckBox byCodeChbx;
    private javax.swing.JCheckBox byDateChBx;
    private javax.swing.JLabel cashAmountLb;
    private javax.swing.JLabel cashAmountValueLb;
    private javax.swing.JInternalFrame customerExchangeIF;
    private javax.swing.JButton customerInvoiceBtn;
    private javax.swing.JCheckBox customerInvoiceChkbx;
    private javax.swing.JInternalFrame customerInvoiceIF;
    private javax.swing.JMenuItem customerInvoiceMI;
    private javax.swing.JInternalFrame dailtItemBusinessIF;
    private datechooser.beans.DateChooserCombo dailyBusinesItemsDf;
    private javax.swing.JButton deleteSupplierBtn;
    private javax.swing.JButton discountBtn;
    private javax.swing.JComboBox discountCb;
    private javax.swing.JCheckBox discountChkbx;
    private javax.swing.JComboBox discountItemWiseCB;
    private javax.swing.JLabel discountLb;
    private javax.swing.JTextField discountPercentageTf;
    private javax.swing.JButton displayStatusBtn;
    private javax.swing.JLabel editCusInvoiceIdLb;
    private javax.swing.JInternalFrame editCustomerInvoiceIF;
    private javax.swing.JMenuItem editCustomerInvoiceMI;
    private javax.swing.JLabel editDate;
    private javax.swing.JComboBox editDeleteSupplierCodeCb;
    private javax.swing.JTextField editDeleteSupplierContactNumberTF;
    private javax.swing.JInternalFrame editDeleteSupplierIF;
    private javax.swing.JMenuItem editDeleteSupplierMI;
    private javax.swing.JTextField editDeleteSupplierNameTF;
    private javax.swing.JButton editDelteSupplierCancelBtn;
    private javax.swing.JTextField editDiscountTf;
    private javax.swing.JLabel editEnteredByLb;
    private javax.swing.JLabel editInvoiceIdValueLb;
    private javax.swing.JButton editInvoiceSearchBtn;
    private javax.swing.JTable editInvoiceTable;
    private javax.swing.JComboBox editItemCodeCb;
    private javax.swing.JInternalFrame editItemIF;
    private javax.swing.JMenuItem editItemMI;
    private javax.swing.JComboBox editItemTypeCb;
    private javax.swing.JInternalFrame editItemTypeIF;
    private javax.swing.JComboBox editItemnameCb;
    private javax.swing.JButton editNewTypeNameTF;
    private javax.swing.JComboBox editOldTypeNameCb;
    private javax.swing.JTextField editSalesPricetf;
    private javax.swing.JComboBox editSalesRefCB;
    private javax.swing.JButton editSalesRefDeleteBtn;
    private javax.swing.JButton editSalesRefLoadUsersBtn;
    private javax.swing.JLabel editSalesRefNameLB;
    private javax.swing.JCheckBox editSalesRefStatusCB;
    private javax.swing.JTabbedPane editSalesRefTB;
    private javax.swing.JButton editSalesRefUpdateBtn;
    private javax.swing.JButton editSupplierBtn;
    private javax.swing.JTextField editTotalAmountTf;
    private javax.swing.JTextField exInvoiceIdTf;
    private javax.swing.JTextField exchangeItemCodeTf;
    private javax.swing.JTextField exchangeNewItemCodeTf;
    private javax.swing.JTextField exchangeNoofItemTf;
    private javax.swing.JComboBox expenseTypeCb;
    private javax.swing.JTextField icCostPriceTf;
    private javax.swing.JComboBox icItemCodeCb;
    private javax.swing.JTextField icItemNameTf;
    private javax.swing.JTextField icSalesPriceTf;
    private javax.swing.JTextField ictypeTf;
    private javax.swing.JComboBox invoiceIdCb;
    private javax.swing.JComboBox invoiceItemCodeCb;
    private javax.swing.JButton invoiceReprintBtn;
    private javax.swing.JTable invoiceTable;
    private javax.swing.JButton itemAddTableBtn;
    private javax.swing.JButton itemAddTableBtn1;
    private javax.swing.JComboBox itemCodeStMgtCb;
    private javax.swing.JButton itemCostButton;
    private javax.swing.JInternalFrame itemCostIF;
    private javax.swing.JLabel itemCostLabel;
    private javax.swing.JLabel itemCostLb;
    private javax.swing.JMenuItem itemCostMI;
    private javax.swing.JLabel itemNameLb;
    private javax.swing.JTextField itemNameStMgtTf;
    private javax.swing.JButton itemRectionReportBtn;
    private javax.swing.JInternalFrame itemRejectionReportIF;
    private javax.swing.JComboBox itemRejectionReportItemCodeCb;
    private javax.swing.JComboBox itemRejectionReportMonthCb;
    private javax.swing.JComboBox itemRejectionReportYearCb;
    private javax.swing.JMenuItem itemRejectionsMI;
    private javax.swing.JButton itemRemoveBtn;
    private javax.swing.JButton itemTypeCancelBtn;
    private javax.swing.JLabel itemTypeLb;
    private javax.swing.JTextField itemTypeNameTf;
    private javax.swing.JButton itemTypeSaveBtn;
    private javax.swing.JCheckBox itemWiseChkBx;
    private javax.swing.JTextField itemWiseDiscountTF;
    private javax.swing.JRadioButton itemWisePercentRB;
    private javax.swing.JRadioButton itemWiseTextRB;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JDesktopPane jDesktopPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JMenu jMenu5;
    private javax.swing.JMenu jMenu7;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JMenuItem jMenuItem1;
    private javax.swing.JMenuItem jMenuItem10;
    private javax.swing.JMenuItem jMenuItem11;
    private javax.swing.JMenuItem jMenuItem12;
    private javax.swing.JMenuItem jMenuItem13;
    private javax.swing.JMenuItem jMenuItem14;
    private javax.swing.JMenuItem jMenuItem15;
    private javax.swing.JMenuItem jMenuItem16;
    private javax.swing.JMenuItem jMenuItem17;
    private javax.swing.JMenuItem jMenuItem18;
    private javax.swing.JMenuItem jMenuItem19;
    private javax.swing.JMenuItem jMenuItem2;
    private javax.swing.JMenuItem jMenuItem20;
    private javax.swing.JMenuItem jMenuItem21;
    private javax.swing.JMenuItem jMenuItem22;
    private javax.swing.JMenuItem jMenuItem23;
    private javax.swing.JMenuItem jMenuItem3;
    private javax.swing.JMenuItem jMenuItem4;
    private javax.swing.JMenuItem jMenuItem5;
    private javax.swing.JMenuItem jMenuItem6;
    private javax.swing.JMenuItem jMenuItem7;
    private javax.swing.JMenuItem jMenuItem8;
    private javax.swing.JMenuItem jMenuItem9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel14;
    private javax.swing.JPanel jPanel15;
    private javax.swing.JPanel jPanel16;
    private javax.swing.JPanel jPanel17;
    private javax.swing.JPanel jPanel18;
    private javax.swing.JPanel jPanel19;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel21;
    private javax.swing.JPanel jPanel22;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JPanel jPanel29;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel30;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JPanel jPanel34;
    private javax.swing.JPanel jPanel35;
    private javax.swing.JPanel jPanel36;
    private javax.swing.JPanel jPanel37;
    private javax.swing.JPanel jPanel38;
    private javax.swing.JPanel jPanel39;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel40;
    private javax.swing.JPanel jPanel41;
    private javax.swing.JPanel jPanel42;
    private javax.swing.JPanel jPanel43;
    private javax.swing.JPanel jPanel44;
    private javax.swing.JPanel jPanel45;
    private javax.swing.JPanel jPanel46;
    private javax.swing.JPanel jPanel47;
    private javax.swing.JPanel jPanel48;
    private javax.swing.JPanel jPanel49;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel50;
    private javax.swing.JPanel jPanel51;
    private javax.swing.JPanel jPanel52;
    private javax.swing.JPanel jPanel53;
    private javax.swing.JPanel jPanel54;
    private javax.swing.JPanel jPanel55;
    private javax.swing.JPanel jPanel56;
    private javax.swing.JPanel jPanel57;
    private javax.swing.JPanel jPanel58;
    private javax.swing.JPanel jPanel59;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel60;
    private javax.swing.JPanel jPanel61;
    private javax.swing.JPanel jPanel62;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JTabbedPane jTabbedPane2;
    private javax.swing.JButton loadInvoiceIdBtn;
    private javax.swing.JComboBox loadInvoiceIdCb;
    private javax.swing.JButton logOffBtn;
    private javax.swing.JMenuItem logOffMI;
    private javax.swing.JButton monthBusinessProfitBtn;
    private javax.swing.JMenuItem monthBusinessProfitMI;
    private javax.swing.JTextField nExAmount;
    private javax.swing.JInternalFrame newExpensesIF;
    private javax.swing.JButton newInvoiceBtn;
    private javax.swing.JButton newItemBtn;
    private javax.swing.JTextField newItemCodeTf;
    private javax.swing.JInternalFrame newItemIF;
    private javax.swing.JMenuItem newItemMI;
    private javax.swing.JTextField newItemNameTf;
    private javax.swing.JComboBox newItemTypeCb;
    private javax.swing.JInternalFrame newItemTypeIF;
    private javax.swing.JInternalFrame newOrdersIF;
    private javax.swing.JTextField newSalesPriceTf;
    private javax.swing.JTextField newSalesRefTF;
    private javax.swing.JTextField newTypeNameTF;
    private javax.swing.JTextArea nexDes;
    private datechooser.beans.DateChooserCombo nexpenseDate;
    private javax.swing.JTextField noContactNumberTf;
    private javax.swing.JTextField noCustomerNameTf;
    private datechooser.beans.DateChooserCombo noDate;
    private javax.swing.JLabel noOfIemsLb;
    private javax.swing.JTextField noOfItemsTf;
    private javax.swing.JTextArea noOrderDeailsTa;
    private javax.swing.JCheckBox openCashDrawyerChkbx;
    private javax.swing.JMenuItem openCashDrawyerMI;
    private javax.swing.JRadioButton percentRadio;
    private datechooser.beans.DateChooserCombo purchaseDateStMgtDc;
    private javax.swing.JTextField purchaseQuantityTf;
    private javax.swing.JTextField reAmountTf;
    private javax.swing.JComboBox reExpMonthCb;
    private javax.swing.JComboBox reExpYearCb;
    private javax.swing.JComboBox reMonthlyBusinessMonthCb;
    private javax.swing.JComboBox reMonthlyBusinessMonthCb1;
    private javax.swing.JComboBox reMonthlyBusinessYearCb;
    private javax.swing.JComboBox reMonthlyBusinessYearCb1;
    private javax.swing.JTextField rejectedTf;
    private javax.swing.JComboBox removeItemCb;
    private javax.swing.JMenu reportsJM;
    private javax.swing.JLabel returnAmountLb;
    private javax.swing.JComboBox returnInvoiceItemCodeCb;
    private javax.swing.JLabel returnItemValueLb;
    private javax.swing.JTextField returnNoOfItemsTf;
    private javax.swing.JLabel returnValueLb;
    private javax.swing.JLabel salesPriceLb;
    private javax.swing.JInternalFrame salesRefIF;
    private javax.swing.JButton saveSalesRefBtn;
    private javax.swing.JTextField sellTf;
    private javax.swing.JInternalFrame storesIF;
    private javax.swing.JComboBox storesMgtSupplierCb;
    private javax.swing.JButton supplierDeliveredReportBtn;
    private javax.swing.JInternalFrame supplierDeliveredReportIF;
    private javax.swing.JComboBox supplierDeliveredReportMonthCb;
    private javax.swing.JComboBox supplierDeliveredReportSupplierCodeCb;
    private javax.swing.JComboBox supplierDeliveredReportYearCb;
    private javax.swing.JButton supplierRectionReportBtn;
    private javax.swing.JInternalFrame supplierRejectionReportIF;
    private javax.swing.JComboBox supplierRejectionReportMonthCb;
    private javax.swing.JComboBox supplierRejectionReportSupplierCodeCb;
    private javax.swing.JComboBox supplierRejectionReportYearCb;
    private javax.swing.JRadioButton textAmountRadio;
    private javax.swing.JTextField textDiscountTf;
    private javax.swing.JLabel todayDateLb;
    private javax.swing.JLabel totalAmountLb;
    private javax.swing.JComboBox upStoresItemCodeCb;
    private javax.swing.JTextField upStoresItemNameTf;
    private javax.swing.JTextField upStoresQuantityTf;
    private javax.swing.JTextField upStoresReOrderTf;
    private javax.swing.JInternalFrame updateCashAmount;
    private javax.swing.JMenuItem updateCashAmountMI;
    private javax.swing.JTextField updateCashTf;
    private javax.swing.JButton updateStoresBtn;
    private javax.swing.JInternalFrame updateStoresIF;
    private javax.swing.JMenuItem updateStoresMI;
    private javax.swing.JMenuItem updateStoresReportMI;
    private javax.swing.JMenu userAccountsMI;
    private javax.swing.JInternalFrame viewExpenseIF;
    private javax.swing.JInternalFrame viewMonthlyBusinessForCashierIF;
    private javax.swing.JInternalFrame viewMonthlyBusinessIF;
    private javax.swing.JComboBox viewStMgtCb;
    private javax.swing.JTextField viewStMgtTf;
    private datechooser.beans.DateChooserCombo viewStoresMgtDf;
    private javax.swing.JInternalFrame viewStoresMgtIF;
    private javax.swing.JLabel welcomeLb;
    // End of variables declaration//GEN-END:variables

    private boolean isNumber(String value) {
        
        try {
            
            double number = Double.parseDouble(value);
            return true;
            
        } catch (NumberFormatException ex) {
            
            return false;
            
        }
        
    }
    
    private void loadItemCodes() {
        
        itemLists = (Vector<Items>) itemManager.getItems();
        
        if (itemLists != null && itemLists.size() > 0) {
            
            editItemCodeCb.setEnabled(true);
            editItemnameCb.setEnabled(true);
            
            Iterator it = itemLists.iterator();
            
            while (it.hasNext()) {
                
                Items items = (Items) it.next();
                
                Integer itemCodeValue = items.getItemCode();
                
                String itemCode = null;
                
                if (itemCodeValue < 10) {
                    
                    itemCode = "000" + Integer.toString(itemCodeValue);
                } else if (10 <= itemCodeValue && itemCodeValue < 100) {
                    
                    itemCode = "00" + Integer.toString(itemCodeValue);
                } else if (100 <= itemCodeValue && itemCodeValue < 1000) {
                    
                    itemCode = "0" + Integer.toString(itemCodeValue);
                } else {
                    
                    itemCode = Integer.toString(itemCodeValue);
                }
                
                
                editItemCodeCb.addItem(itemCode);
                editItemnameCb.addItem(items.getItemName());
            }
        } else {
            
            editItemCodeCb.setEnabled(false);
            editItemnameCb.setEnabled(false);
        }
    }
    
    private void loadItemCodesToUpdateStores() {
        
        itemLists = (Vector<Items>) itemManager.getItems();
        
        if (itemLists != null && itemLists.size() > 0) {
            
            upStoresItemCodeCb.setEnabled(true);
            Iterator it = itemLists.iterator();
            
            while (it.hasNext()) {
                
                Items items = (Items) it.next();
                
                int itemCodeValue = items.getItemCode();
                
                String itemCode = null;
                
                if (itemCodeValue < 10) {
                    
                    itemCode = "000" + Integer.toString(itemCodeValue);
                } else if (10 <= itemCodeValue && itemCodeValue < 100) {
                    
                    itemCode = "00" + Integer.toString(itemCodeValue);
                } else if (100 <= itemCodeValue && itemCodeValue < 1000) {
                    
                    itemCode = "0" + Integer.toString(itemCodeValue);
                } else {
                    
                    itemCode = Integer.toString(itemCodeValue);
                }
                
                
                upStoresItemCodeCb.addItem(itemCode);
            }
        } else {
            
            upStoresItemCodeCb.setEnabled(false);
        }
        
    }
    
    private void loadItemTypes() {
        
        itemTypeLists = (Vector<ItemType>) itemManager.getItemType();
        
        if (itemTypeLists != null && itemTypeLists.size() > 0) {
            
            Iterator it = itemTypeLists.iterator();
            
            while (it.hasNext()) {
                
                ItemType itemType = (ItemType) it.next();
                
                String itemTypeName = itemType.getItemtypename();
                
                editOldTypeNameCb.addItem(itemTypeName);
                
            }
        } else {
        }
    }
    
    private void loadItemTypeForAddEditItem(String saveStaus) {
        
        itemTypeLists = (Vector<ItemType>) itemManager.getItemType();
        
        if (itemTypeLists != null && itemTypeLists.size() > 0) {
            
            Iterator it = itemTypeLists.iterator();
            
            while (it.hasNext()) {
                
                ItemType itemType = (ItemType) it.next();
                
                String itemTypeName = itemType.getItemtypename();
                if (saveStaus.equals("newItem")) {
                    newItemTypeCb.addItem(itemTypeName);
                } else if (saveStaus.equals("editItem")) {
                    editItemTypeCb.addItem(itemTypeName);
                }
            }
        } else {
        }
    }

//    private void refreshStores() {
//
//        if (storesList != null && storesList.size() > 0) {
//
//            upStoresItemCodeCb.removeAll();
//
//            upStoresItemCodeCb.setEnabled(true);
//            Iterator it = storesList.iterator();
//
//            while (it.hasNext()) {
//
//                Stores stores = (Stores) it.next();
//                System.out.println("Item Code  " + stores.getItemCode() + "  Size  " + stores.getQuantity());
//                int itemCodeValue = stores.getItemCode();
//
//                String itemCode = null;
//
//                if (itemCodeValue < 10) {
//
//                    itemCode = "000" + Integer.toString(itemCodeValue);
//
//                } else if (10 <= itemCodeValue && itemCodeValue < 100) {
//
//                    itemCode = "00" + Integer.toString(itemCodeValue);
//
//                } else if (100 <= itemCodeValue && itemCodeValue < 1000) {
//
//                    itemCode = "0" + Integer.toString(itemCodeValue);
//
//                } else {
//
//                    itemCode = Integer.toString(itemCodeValue);
//                }
//
//
//                upStoresItemCodeCb.addItem(itemCode);
//            }
//        } else {
//
//            upStoresItemCodeCb.setEnabled(false);
//        }
//
//    }
    private void loadItemCodesToInvoice() {
        
        itemLists = (Vector<Items>) itemManager.getItems();
        
        if (itemLists != null && itemLists.size() > 0) {
            
            invoiceItemCodeCb.setEnabled(true);
            
            Iterator it = itemLists.iterator();
            invoiceItemCodeCb.addItem("");
            while (it.hasNext()) {
                
                Items items = (Items) it.next();
                
                int itemCodeValue = items.getItemCode();
                
                String itemCode = null;
                
                if (itemCodeValue < 10) {
                    
                    itemCode = "000" + Integer.toString(itemCodeValue);
                } else if (10 <= itemCodeValue && itemCodeValue < 100) {
                    
                    itemCode = "00" + Integer.toString(itemCodeValue);
                } else if (100 <= itemCodeValue && itemCodeValue < 1000) {
                    
                    itemCode = "0" + Integer.toString(itemCodeValue);
                } else {
                    
                    itemCode = Integer.toString(itemCodeValue);
                }
                
                invoiceItemCodeCb.addItem(itemCode);
            }
        } else {
            
            invoiceItemCodeCb.setEnabled(false);
        }
        
    }
    
    private void loadStores() {

//        if (storesList != null && storesList.size() > 0) {
//
//            storesList.removeAllElements();
//
//            storesList.clear();
//
////            storesList = null;
////
////            storesList = new Vector<Stores>();
//        }

        
        
        storesList = (Vector<Stores>) storesManager.getStores();
        
        if (storesList != null && storesList.size() > 0) {
            
            upStoresItemCodeCb.setEnabled(true);
            
        } else {
            
            upStoresItemCodeCb.setEnabled(false);
        }
        
    }
    
    private void loadCustomerinvoiceForInvoiceId(String invoiceId) {
//        customerInvoice = customerInvoiceManager.getCustomerInvoiceForInvoiceId(invoiceId);
//
//        if (customerInvoice != null) {
//
//
//        }
    }
    
    private void loadItemCodesToItemCost() {
        
        itemLists = (Vector<Items>) itemManager.getItems();
        
        if (itemLists != null && itemLists.size() > 0) {
            
            icItemCodeCb.setEnabled(true);
            icItemNameTf.setEnabled(true);
            icSalesPriceTf.setEnabled(true);
            
            Iterator it = itemLists.iterator();
            
            while (it.hasNext()) {
                
                Items items = (Items) it.next();
                
                Integer itemCodeValue = items.getItemCode();
                
                String itemCode = null;
                
                
                if (itemCodeValue < 10) {
                    
                    itemCode = "000" + Integer.toString(itemCodeValue);
                } else if (10 <= itemCodeValue && itemCodeValue < 100) {
                    
                    itemCode = "00" + Integer.toString(itemCodeValue);
                } else if (100 <= itemCodeValue && itemCodeValue < 1000) {
                    
                    itemCode = "0" + Integer.toString(itemCodeValue);
                } else {
                    
                    itemCode = Integer.toString(itemCodeValue);
                }
                
                icItemCodeCb.addItem(itemCode);
                icItemNameTf.setText(items.getItemName());
                icSalesPriceTf.setText(Double.toString(items.getSalesPrice()));
                ictypeTf.setText(items.getItemType());
            }
        } else {
            
            icItemCodeCb.setEnabled(false);
            icItemNameTf.setEnabled(false);
            icSalesPriceTf.setEnabled(false);
            
        }
    }
    
    private void addNewItems() {
        
        
        String itemCode = newItemCodeTf.getText();
        
        if (itemCode != null && itemCode.length() > 0) {
            
            if (isNumber(itemCode)) {
                
                int itemCodeValue = Integer.parseInt(itemCode);
                
                String newItem = newItemNameTf.getText();
                if (newItem != null && newItem.length() > 0) {
                    
                    String quantity = newSalesPriceTf.getText();
                    
                    if (quantity != null && quantity.length() > 0) {
                        
                        if (isNumber(quantity)) {
                            
                            double quantityValue = Double.parseDouble(quantity);
                            
                            String itemType = (String) newItemTypeCb.getSelectedItem();
                            
                            Items items = new Items();
                            
                            Date date = new Date();
                            
                            items.setItemCode(itemCodeValue);
                            items.setItemName(newItem);
                            items.setSalesPrice(quantityValue);
                            items.setItemType(itemType);
                            items.setEnteredDate(date);
                            items.setEnteredBy(username);
                            
                            Stores stores = new Stores();
                            
                            
                            stores.setItemCode(items.getItemCode());
                            stores.setQuantity(0);
                            stores.setReOrder(0);
                            stores.setEnteredBy(items.getEnteredBy());
                            stores.setEnteredDate(date);
                            
                            String response = itemManager.addItem(items, stores);
                            
                            if (response.equals(Constants.ITEM_ADDED_SUCCESSFULLY)) {
                                
                                itemLists.add(items);
                                storesList.add(stores);
                                
                                editItemCodeCb.setEnabled(true);
                                editItemnameCb.setEnabled(true);
                                upStoresItemCodeCb.setEnabled(true);
                                icItemCodeCb.setEnabled(true);
                                invoiceItemCodeCb.setEnabled(true);
                                
                                String newItemCode = null;
                                
                                if (itemCodeValue < 10) {
                                    
                                    newItemCode = "000" + Integer.toString(itemCodeValue);
                                } else if (10 <= itemCodeValue && itemCodeValue < 100) {
                                    
                                    newItemCode = "00" + Integer.toString(itemCodeValue);
                                } else if (100 <= itemCodeValue && itemCodeValue < 1000) {
                                    
                                    newItemCode = "0" + Integer.toString(itemCodeValue);
                                } else {
                                    
                                    newItemCode = Integer.toString(itemCodeValue);
                                }
                                
                                invoiceItemCodeCb.addItem(newItemCode);
                                editItemCodeCb.addItem(newItemCode);
                                upStoresItemCodeCb.addItem(newItemCode);
                                icItemCodeCb.addItem(newItemCode);
                                
                                
                                
                                JOptionPane.showMessageDialog(this, "Item Added Successfully.", "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                                
                                newItemCodeTf.setText("");
                                newItemNameTf.setText("");
                                newItemTypeCb.setSelectedIndex(0);
                                newSalesPriceTf.setText("");
                                
                                
                                
                            } else if (response.equals(Constants.ITEM_CODE_EXIST)) {
                                JOptionPane.showMessageDialog(this, "Item Code already exist", "Warn",
                                        JOptionPane.WARNING_MESSAGE);
                                newItemCodeTf.requestFocus();
                            } else if (response.equals(Constants.ERROR)) {
                                JOptionPane.showMessageDialog(this, "Item did not add successfully", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            } else if (response.equals("TRIAL_VERSION")) {
                                JOptionPane.showMessageDialog(this, "TRIAL VERSION.....CONTACT US... 0778 322 535", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                                
                            }
                            
                        } else {
                            
                            JOptionPane.showMessageDialog(this, "Invalid Format for the Sales Price", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            newSalesPriceTf.requestFocus();
                        }
                        
                    } else {
                        
                        JOptionPane.showMessageDialog(this, "Sales Price can't be empty", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        newSalesPriceTf.requestFocus();
                    }
                    
                } else {
                    
                    JOptionPane.showMessageDialog(this, "Item name can't be empty", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    newItemNameTf.requestFocus();
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Format for the Item Code", "Error",
                        JOptionPane.ERROR_MESSAGE);
                newItemCodeTf.requestFocus();
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Item Code can't be empty", "Error",
                    JOptionPane.ERROR_MESSAGE);
            newItemCodeTf.requestFocus();
        }
        
    }
    
    private void editItems() {
        
        String itemCode = (String) editItemCodeCb.getSelectedItem();
        
        if (itemCode != null && itemCode.length() > 0) {
            
            if (isNumber(itemCode)) {
                
                
                Integer itemCodeValue = new Integer(itemCode);
                
                Iterator it = itemLists.iterator();
                
                boolean isExist = false;
                
                Items updateItem = null;
                
                while (it.hasNext()) {
                    
                    updateItem = (Items) it.next();
                    
                    
                    if (itemCodeValue.equals(updateItem.getItemCode())) {
                        
                        isExist = true;
                        break;
                        
                    }
                    
                }
                
                if (isExist) {
                    
                    String newItem = (String) editItemnameCb.getSelectedItem();
                    
                    if (newItem != null && newItem.length() > 0) {
                        
                        String quantity = editSalesPricetf.getText();
                        
                        if (quantity != null && quantity.length() > 0) {
                            
                            if (isNumber(quantity)) {
                                
                                double quantityValue = Double.parseDouble(quantity);
                                
                                String itemType = (String) editItemTypeCb.getSelectedItem();
                                
                                updateItem.setItemName(newItem);
                                updateItem.setSalesPrice(quantityValue);
                                updateItem.setItemType(itemType);
                                updateItem.setEnteredDate(new Date());
                                updateItem.setEnteredBy(username);
                                
                                String response = itemManager.editItem(updateItem);
                                
                                if (response.equals(Constants.ITEM_UPDATED_SUCCESSFULLY)) {
                                    
                                    
                                    JOptionPane.showMessageDialog(this, "Item Updated Successfully.", "Success",
                                            JOptionPane.INFORMATION_MESSAGE);
                                    
                                    newItemCodeTf.setText("");
                                    newItemNameTf.setText("");
                                    newItemTypeCb.setSelectedIndex(0);
                                    newSalesPriceTf.setText("");
                                    
                                } else if (response.equals(Constants.ITEM_NOT_EXIST)) {
                                    JOptionPane.showMessageDialog(this, "Item Code not exist", "Warn",
                                            JOptionPane.WARNING_MESSAGE);
                                } else if (response.equals(Constants.ERROR)) {
                                    JOptionPane.showMessageDialog(this, "Item did not update successfully", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                }
                                
                                
                                
                            } else {
                                
                                JOptionPane.showMessageDialog(this, "Invalid Format for the Quantity", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                                newItemNameTf.requestFocus();
                            }
                            
                        } else {
                            
                            JOptionPane.showMessageDialog(this, "Quantity can't be empty", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            newSalesPriceTf.requestFocus();
                        }
                        
                    } else {
                        
                        JOptionPane.showMessageDialog(this, "Item name can't be empty", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        newItemNameTf.requestFocus();
                    }
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Item Code", "Warn",
                            JOptionPane.WARNING_MESSAGE);
                }
                
                
                
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Format for the Item Code", "Error",
                        JOptionPane.ERROR_MESSAGE);
                newItemCodeTf.requestFocus();
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Item Code can't be empty", "Error",
                    JOptionPane.ERROR_MESSAGE);
            newItemCodeTf.requestFocus();
        }
        
    }
    
    private void updateStores() {
        
        
        String itemCode = (String) upStoresItemCodeCb.getSelectedItem();
        
        if (itemCode != null && itemCode.length() > 0) {
            
            
            Integer itemCodeValue = new Integer(itemCode);
            
            Iterator it = itemLists.iterator();
            
            boolean isExist = false;
            
            Items updateItem = null;
            
            while (it.hasNext()) {
                
                updateItem = (Items) it.next();
                
                
                if (itemCodeValue.equals(updateItem.getItemCode())) {
                    
                    isExist = true;
                    break;
                    
                } else {
                    
                    isExist = false;
                }
                
            }
            
            if (isExist) {
                
                String itemName = (String) upStoresItemNameTf.getText();
                
                String quantity = upStoresQuantityTf.getText();
                
                if (quantity != null && quantity.length() > 0) {
                    
                    if (isNumber(quantity)) {
                        
                        if (quantity.indexOf(".") == -1) {
                            
                            String reOrder = upStoresReOrderTf.getText();
                            
                            if (reOrder != null && reOrder.length() > 0) {
                                
                                if (isNumber(reOrder)) {
                                    
                                    if (reOrder.indexOf(".") == -1) {
                                        
                                        
                                        int quantityValue = Integer.parseInt(quantity);
                                        int reOrderQuantity = Integer.parseInt(reOrder);

                                        //                   if (quantityValue > reOrderQuantity) {

                                        
                                        String itemType = (String) newItemTypeCb.getSelectedItem();
                                        
                                        
                                        Stores stores = storesList.elementAt(upStoresItemCodeCb.getSelectedIndex());
                                        
                                        stores.setItemCode(itemCodeValue);
                                        stores.setItems(updateItem);
                                        stores.setQuantity(quantityValue);
                                        stores.setReOrder(reOrderQuantity);
                                        stores.setEnteredBy(username);
                                        stores.setEnteredDate(new Date());
                                        
                                        String response = storesManager.addItemsToStores(stores);
                                        
                                        if (response.equals(Constants.ITEM_UPDATED_SUCCESSFULLY)) {
                                            
                                            JOptionPane.showMessageDialog(this, "Item Updated to Stores Successfully.", "Success",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                            
                                            
                                            upStoresQuantityTf.setText("");
                                            upStoresReOrderTf.setText("");
                                            
                                        } else if (response.equals(Constants.ITEM_ADDED_SUCCESSFULLY)) {
                                            
                                            
                                            upStoresQuantityTf.setText("");
                                            upStoresReOrderTf.setText("");
                                            
                                            JOptionPane.showMessageDialog(this, "Item Added to Stores Successfully", "Success",
                                                    JOptionPane.INFORMATION_MESSAGE);
                                        } else if (response.equals(Constants.ERROR)) {
                                            JOptionPane.showMessageDialog(this, "Item did not add/update successfully", "Error",
                                                    JOptionPane.ERROR_MESSAGE);
                                        }

//                                        } else {
//
//                                            JOptionPane.showMessageDialog(this, "Current Quantity must greater than Re-Order level", "Warn",
//                                                    JOptionPane.WARNING_MESSAGE);
//
//                                            upStoresReOrderTf.requestFocus();
//
//                                        }

                                        
                                        
                                    } else {
                                        
                                        JOptionPane.showMessageDialog(this, "Please enter integer value for Re-order Level", "Error",
                                                JOptionPane.ERROR_MESSAGE);
                                        upStoresQuantityTf.requestFocus();
                                    }
                                    
                                } else {
                                    JOptionPane.showMessageDialog(this, "Invalid Format for the Re-Order", "Error",
                                            JOptionPane.ERROR_MESSAGE);
                                    upStoresReOrderTf.requestFocus();
                                }
                                
                                
                            } else {
                                JOptionPane.showMessageDialog(this, "Re-Order Level can't be empty", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                                upStoresReOrderTf.requestFocus();
                            }
                            
                            
                            
                        } else {
                            
                            JOptionPane.showMessageDialog(this, "Please enter integer value for quantity", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            upStoresQuantityTf.requestFocus();
                            
                        }
                        
                    } else {
                        
                        JOptionPane.showMessageDialog(this, "Invalid Format for the Quantity", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        newItemNameTf.requestFocus();
                    }
                    
                } else {
                    
                    JOptionPane.showMessageDialog(this, "Quantity can't be empty", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    upStoresQuantityTf.requestFocus();
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Item Code", "Warn",
                        JOptionPane.WARNING_MESSAGE);
                upStoresItemCodeCb.requestFocus();
            }
            
            
        } else {
            JOptionPane.showMessageDialog(this, "Item Code can't be empty", "Error",
                    JOptionPane.ERROR_MESSAGE);
            upStoresItemCodeCb.requestFocus();
        }
        
        
    }
    
    private void loadItemsToNewTable(boolean isBarCodeOn) {
        
        Vector row = new Vector();
        
        int selectedItemCodeIndex = invoiceItemCodeCb.getSelectedIndex();
        
        Items itemsTmp = itemLists.elementAt(selectedItemCodeIndex - 1);
        String selectedItemName = itemsTmp.getItemName();


        // Add data to the discount item wise combo box


        //  discountItemWiseCB.addItem(selectedItemName);

        Integer itemCode = new Integer((String) invoiceItemCodeCb.getSelectedItem());
        Double price = new Double(new Double(salesPriceLb.getText()));
        double costPerOne = Double.parseDouble(itemCostLb.getText());
        
        
        Integer quantity = null;
        
        if (isBarCodeOn) {
            
            quantity = new Integer(new Integer(1));
            
            totalCostValue = totalCostValue + costPerOne;
            
        } else {
            
            quantity = new Integer(new Integer(noOfItemsTf.getText()));
            
            totalCostValue = totalCostValue + costPerOne * quantity.intValue();
        }
        
        int noOfRows = invoiceTable.getRowCount();
        
        boolean notExisted = true;
        
        int x = 0;
        while (noOfRows > 0 && x < noOfRows) {
            
            Integer itemCodes = (Integer) invoiceTable.getValueAt(x, 0);
            
            if (itemCodes.intValue() == itemCode.intValue()) {
                
                Double amount = (Double) invoiceTable.getValueAt(x, 4);
                Integer oldQuantity = (Integer) invoiceTable.getValueAt(x, 3);
                
                
                double newAmountValue = price * quantity;
                
                amount = amount + newAmountValue;
                
                int newQuantitiy = oldQuantity + quantity;
                
                invoiceTable.setValueAt(new Integer(newQuantitiy), x, 3);
                invoiceTable.setValueAt(new Double(amount), x, 4);
                
                totalAmountValue = totalAmountValue + newAmountValue;
                
                String totalAmount = Double.toString(totalAmountValue);
                
                
                if (!isEditCustomerInvoice) {
                    
                    double roundTotal = round(totalAmountValue, 2);
                    
                    totalAmountLb.setText(Double.toString(roundTotal));
                    
                } else {
                    
                    if (totalAmountValue <= returnValue) {
                        totalAmountLb.setText("0.00");
                    } else {
                        
                        double newTotalAmountValue = totalAmountValue - returnValue;
                        
                        double roundNewTotal = round(newTotalAmountValue, 2);
                        
                        String newTotalAmount = Double.toString(roundNewTotal);
                        
                        
                        
                        totalAmountLb.setText(newTotalAmount);
                    }
                }
                
                invoiceItemCodeCb.setSelectedIndex(0);
                invoiceItemCodeCb.requestFocus();
                
                noOfItemsTf.setText("");
                reAmountTf.setEditable(true);
                
                
                notExisted = false;
                break;
            }
            
            x++;
        }
        
        
        if (notExisted) {
            
            discountItemWiseCB.addItem(selectedItemName);
            
            
            row.addElement(itemCode);
            row.add(selectedItemName);
            row.addElement(price);
            row.addElement(quantity);
            
            removeItemCb.addItem(itemCode.toString());
            double priceValue = price.doubleValue();
            int quantityValue = quantity.intValue();
            
            double amountValue = priceValue * quantityValue;
            
            Double amount = new Double(amountValue);
            
            row.addElement(amount);
            row.addElement(0.0);
            
            if (rows != null) {
                rows.addElement(row);
            } else {
                return;
//                rows = new Vector();
//                rows.addElement(row);
            }
            
            totalAmountValue = totalAmountValue + amount;
            
            
            String totalAmount = Double.toString(totalAmountValue);
            
            if (!isEditCustomerInvoice) {
                
                totalAmountLb.setText(totalAmount);
                
            } else {
                
                if (totalAmountValue <= returnValue) {
                    totalAmountLb.setText("0.0");
                } else {
                    
                    double newTotalAmountValue = totalAmountValue - returnValue;
                    
                    String newTotalAmount = Double.toString(newTotalAmountValue);
                    
                    totalAmountLb.setText(newTotalAmount);
                }
            }
            
            invoiceItemCodeCb.setSelectedIndex(0);
            invoiceItemCodeCb.requestFocus();
            
            noOfItemsTf.setText("");
            reAmountTf.setEditable(true);
            discountChkbx.setEnabled(true);
            reAmountTf.requestFocus();
            
            invoiceTable.setModel(new DefaultTableModel(rows, invoiceColumns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            
        }
        
        if (barCodeChkbx.isSelected()) {
            ++noOfItems;
        } else {
            
            noOfItems = noOfItems + quantity.intValue();
        }
        noOfIemsLb.setText(Integer.toString(noOfItems));
        
        String totalAmountCalculated = totalAmountLb.getText();
        double totalAmountCalculatedValue = Double.parseDouble(totalAmountCalculated);
        if (itemWiseDiscountList != null && itemWiseDiscountList.size() > 0) {
            
            Iterator<Double> it = itemWiseDiscountList.iterator();
            
            double totalDiscount = 0;
            while (it.hasNext()) {
                
                double discountValue = it.next();
                
                totalDiscount = totalDiscount + discountValue;
            }
            
            totalAmountCalculatedValue = totalAmountCalculatedValue - totalDiscount;
            
            totalAmountLb.setText(Double.toString(totalAmountCalculatedValue));
            
        }
    }
    
    private void loadDataToEditCustomerInvoice() {
        
        
        
        String selectedInvoiceId = (String) invoiceIdCb.getSelectedItem();
        
        editCustomerInvoice = customerInvoiceManager.getCustomerInvoiceForInvoiceId(selectedInvoiceId);
        
        
        if (editCustomerInvoice != null) {
            editDate.setText(editCustomerInvoice.getInvoicedDate().toString());
            editEnteredByLb.setText(editCustomerInvoice.getEnteredBy());
            editTotalAmountTf.setText(Double.toString(editCustomerInvoice.getTotalAmount()));
            editDiscountTf.setText(Double.toString(editCustomerInvoice.getDiscountAmount()));
            discountPercentageTf.setText(Double.toString(editCustomerInvoice.getDiscountPercent()));
            editCustomerInvoiceRow = new Vector();
            //    editInvoiceCoumns = new Vector();

            if (returnInvoiceItemCodeCb.getItemCount() > 0) {
                returnInvoiceItemCodeCb.removeAllItems();
            }
            
            editCustomerInvoiceItems = editCustomerInvoice.getInvoiceItemsList();
            
            Iterator it = editCustomerInvoiceItems.iterator();
            
            Vector checkExistingItemCodeBlock = new Vector();
            
            while (it.hasNext()) {
                
                InvoiceItems invoiceItemsTmp = (InvoiceItems) it.next();
                
                Vector row = new Vector();
                
                Integer itemCode = invoiceItemsTmp.getInvoiceItemsPK().getItemCode();
                String itemName = invoiceItemsTmp.getItemName();
                
                
                Double price = invoiceItemsTmp.getSalesPrice();
                Integer quantity = invoiceItemsTmp.getQuantity();
                Double pricePerone = price / quantity;
                Double discount = invoiceItemsTmp.getItemdiscount();
                if (discount == null) {
                    discount = new Double("0.0");
                }
                
                row.addElement(itemCode);
                row.addElement(itemName);
                row.addElement(pricePerone);
                row.addElement(quantity);
                row.addElement(price);
                row.addElement(discount);
                
                if (!checkExistingItemCodeBlock.contains(itemCode)) {
                    editCustomerInvoiceRow.add(row);
                    if (quantity != 0) {
                        returnInvoiceItemCodeCb.addItem(itemCode.toString());
                    }
                }
                
                checkExistingItemCodeBlock.add(itemCode);
                
                
                
            }
            
            
            editInvoiceTable.setModel(new DefaultTableModel(editCustomerInvoiceRow, editInvoiceCoumns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            
        } else {
            
            JOptionPane.showMessageDialog(this, "Empty data for selected Invoice Id", "Error",
                    JOptionPane.ERROR_MESSAGE);
            invoiceIdCb.requestFocus();
            
        }
        
        
        
        
        
    }
    
    private void invoiceRefresh() {
        
        isDiscountAdded = false;
        
        totalCostValue = 0.0;
        totalAmountValue = 0.0;
        
        totalAmountLb.setText("0.0");
        totalAmountValue = 0.0;
        noOfItemsTf.setText("");
        reAmountTf.setText("");
        reAmountTf.setEditable(false);
        balanceLb.setText("0.0");
        discountLb.setText("0.0");
        invoiceItemCodeCb.setEnabled(true);
        
        discountCb.setSelectedIndex(0);
        discountCb.setEnabled(false);
        discountBtn.setEnabled(false);
        discountChkbx.setSelected(false);
        discountChkbx.setEnabled(false);
        
        discountBtn.setEnabled(false);
        discountCb.setEnabled(false);
        
        
        percentRadio.setEnabled(false);
        percentRadio.setSelected(false);
        textAmountRadio.setSelected(false);
        textAmountRadio.setEnabled(false);
        textDiscountTf.setEnabled(false);
        textDiscountTf.setText("");
        
        itemWiseChkBx.setSelected(false);
        itemWiseChkBx.setEnabled(false);
        discountItemWiseCB.setEnabled(false);
        itemWisePercentRB.setEnabled(false);
        itemWiseTextRB.setEnabled(false);
        itemWiseDiscountTF.setEnabled(false);
        itemWiseDiscountTF.setText("");
        
        noOfIemsLb.setText("0");
        noOfItems = 0;
        discountItemWiseCB.removeAllItems();
        itemWiseDiscountList.removeAll(itemWiseDiscountList);
        
        rows.removeAllElements();
        
        invoiceTable.setModel(new DefaultTableModel(rows, invoiceColumns) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        });
        
        invoiceItemCodeCb.setSelectedIndex(0);
        invoiceItemCodeCb.requestFocus();
        removeItemCb.removeAllItems();
        
        
        
    }
    
    private void addCustomerInvoice() {
        
        
        String reAmount = reAmountTf.getText();
        
        if (reAmount != null && reAmount.length() > 0) {
            
            if (isNumber(reAmount)) {
                
                String totAmount = totalAmountLb.getText();
                
                double totalAmount = Double.parseDouble(totAmount);
                
                
                double reAmountValue = Double.parseDouble(reAmount);
                
                if (reAmountValue >= totalAmount) {
                    
                    Date businessDate = new Date();
                    
                    
                    GregorianCalendar grCalendar = new GregorianCalendar();
                    grCalendar.setTime(businessDate);
                    
                    int y = grCalendar.get(GregorianCalendar.YEAR);
                    int m = grCalendar.get(GregorianCalendar.MONTH);
                    int d = grCalendar.get(GregorianCalendar.DATE);
                    
                    String year = Integer.toString(y);
                    
                    String monthParam = null;
                    
                    switch (m + 1) {
                        
                        case 1:
                            monthParam = "January";
                            break;
                        case 2:
                            monthParam = "Febuary";
                            break;
                        case 3:
                            monthParam = "March";
                            break;
                        case 4:
                            monthParam = "April";
                            break;
                        case 5:
                            monthParam = "May";
                            break;
                        case 6:
                            monthParam = "June";
                            break;
                        case 7:
                            monthParam = "July";
                            break;
                        case 8:
                            monthParam = "August";
                            break;
                        case 9:
                            monthParam = "September";
                            break;
                        case 10:
                            monthParam = "Octomber";
                            break;
                        case 11:
                            monthParam = "November";
                            break;
                        case 12:
                            monthParam = "December";
                            break;
                        
                    }
                    
                    
                    GregorianCalendar dailyBusinessGrCalendar = new GregorianCalendar(y, m, d);
                    
                    
                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
                    
                    String companyBusinessDate = simpleDateFormat.format(businessDate);
                    
                    double balanceValue = reAmountValue - totalAmount;
                    
                    balanceValue = round(balanceValue, 2);
                    
                    String balance = Double.toString(balanceValue);
                    
                    balanceLb.setText(balance);
                    
                    CustomerInvoice customerInvoice = new CustomerInvoice();
//
//                    if (isEditCustomerInvoice) {
//
//                        customerInvoice.setInvoiceId(new Integer(editInvoiceIdValueLb.getText()));
//
//                    }

                    customerInvoice.setTotalAmount(totalAmount);
                    
                    double discountAmount = 0.0;
                    
                    String discountPercent = (String) discountCb.getSelectedItem();
                    
                    customerInvoice.setDiscountPercent(Double.parseDouble(discountPercent));
                    
                    
                    if (discountChkbx.isSelected()) {
                        
                        discountAmount = Double.parseDouble(discountLb.getText());
                        
                        customerInvoice.setDiscountAmount(discountAmount);
                        
                    }
                    
                    
                    
                    customerInvoice.setEnteredBy(username);
                    customerInvoice.setEnteredDate(businessDate);
                    
                    List<InvoiceItems> invoiceItemsesList = new Vector<InvoiceItems>();
                    
                    int tableRows = invoiceTable.getRowCount();
                    
                    double discountTotal = 0;
                    for (int x = 0; x < tableRows; x++) {
                        
                        InvoiceItemsPK invoiceItemsPK = new InvoiceItemsPK();
                        
                        
                        InvoiceItems invoiceItems = new InvoiceItems();
                        
                        Integer itemCode = (Integer) invoiceTable.getValueAt(x, 0);
                        String itemName = (String) invoiceTable.getValueAt(x, 1);
                        Double pricePerOne = (Double) invoiceTable.getValueAt(x, 2);
                        Integer noOfitems = (Integer) invoiceTable.getValueAt(x, 3);
                        Double amount = (Double) invoiceTable.getValueAt(x, 4);
                        Double discount = (Double) invoiceTable.getValueAt(x, 5);
                        
                        discountTotal = discountTotal + discount;
                        double discountPercentValue = Double.parseDouble(discountPercent);
                        
                        double salesPrice = pricePerOne.doubleValue();
                        
                        int quantity = noOfitems.intValue();
                        
                        double realPricePerOne = 0.0;
                        
                        if (discountPercentValue > 0) {
                            
                            realPricePerOne = salesPrice - salesPrice * discountPercentValue / 100;
                            
                            
                        } else {
                            
                            realPricePerOne = salesPrice;
                            
                            
                        }
                        
                        double totalPerItemValue = realPricePerOne * quantity;
                        
                        String salesExpression = Integer.toString(quantity) + " * " + Double.toString(realPricePerOne);
                        
                        invoiceItemsPK.setItemCode(itemCode);
                        
                        invoiceItems.setInvoiceItemsPK(invoiceItemsPK);
                        invoiceItems.setQuantity(noOfitems);
                        invoiceItems.setSalesPrice(amount);
                        invoiceItems.setRealPrice(totalPerItemValue);
                        invoiceItems.setItemName(itemName);
                        invoiceItems.setSalesExpression(salesExpression);
                        invoiceItems.setEnteredDate(companyBusinessDate);
                        invoiceItems.setItemdiscount(discount);
                        
                        
                        invoiceItemsesList.add(invoiceItems);

//                        if (isEditCustomerInvoice) {
//
//                            Integer invoiceId = new Integer(editInvoiceIdValueLb.getText());
//                            invoiceItemsPK.setInvoiceId(invoiceId);
//
//
//
//                            customerInvoiceItems.add(invoiceItems);
//
//                        }

                    }
                    
                    if (itemWiseChkBx.isSelected()) {
                        customerInvoice.setDiscountAmount(discountTotal);
                    }
                    
                    CompanyBusiness companyBusiness = new CompanyBusiness();
                    
                    
                    
                    double businessProfit = totalAmount - totalCostValue;
                    
                    System.out.println("Total Amount " + totalAmount);
                    System.out.println("Total Cost " + totalCostValue);
                    
                    System.out.println("Business Profit  " + businessProfit);
                    
                    customerInvoice.setInvoicedDate(companyBusinessDate);
                    
                    companyBusiness.setBusinessDate(companyBusinessDate);
                    companyBusiness.setBusinessMonth(monthParam);
                    companyBusiness.setBusinessYear(year);
                    
                    companyBusiness.setBusinessAmount(totalAmount);
                    
                    String returnItemsAmount = returnValueLb.getText();
                    double returnItemsValue = 0;
                    try {
                        returnItemsValue = Double.parseDouble(returnItemsAmount);
                        
                    } catch (NumberFormatException ex) {
                    }
                    if (returnItemsValue > 0) {
                        totalAmount = totalAmount - returnItemsValue;
                    }
                    companyBusiness.setCashierAmount(totalAmount);
                    companyBusiness.setBusinessProfit(businessProfit);
                    
                    
                    if (discountChkbx.isSelected()) {
                        
                        companyBusiness.setDiscountAmount(discountAmount);
                        
                        
                        
                    } else {
                        companyBusiness.setBusinessProfit(businessProfit);
                        companyBusiness.setDiscountAmount(0.0);
                    }
                    if (itemWiseChkBx.isSelected()) {
                        companyBusiness.setDiscountAmount(discountTotal);
                    }
                    companyBusiness.setEnteredBy(username);
                    companyBusiness.setEnteredDate(businessDate);
                    
                    if (!isEditCustomerInvoice) {
                        
                        String response = customerInvoiceManager.addCustomerInvoice(customerInvoice, invoiceItemsesList, companyBusiness);
                        
                        if (!response.equals(Constants.ERROR)) {
                            
                            storesList.removeAllElements();
                            storesList.clear();
                            storesList = (Vector<Stores>) storesManager.getStores();
                            
                            String cashierAmount = cashAmountValueLb.getText();
                            
                            double newCashierAmount = totalAmount + Double.parseDouble(cashierAmount);
                            
                            double roundCashierBalance = round(newCashierAmount, 2);
                            
                            cashAmountValueLb.setText(Double.toString(roundCashierBalance));
                            
                            returnItemValueLb.setVisible(false);
                            returnValueLb.setVisible(false);
                            
                            returnValueLb.setText("0.0");
                            
                            
                            
                            
                            if (isOpenCashDrawyer) {
                                openCashDrawer();
                            }
                            
                            if (printerEnabled) {
                                
                                String invoiceTotalAmountTmp = Double.toString(totalAmount);
                                
                                String invoiceReceiveAmount = Double.toString(reAmountValue);
                                String invoiceBalanceAmount = Double.toString(balanceValue);
                                
                                reportManager.printCustomerInvoice(response, username, invoiceTotalAmountTmp, invoiceReceiveAmount, invoiceBalanceAmount);
                            }

//                            PrintInvoiceJFrame printInvoiceJFrame = new PrintInvoiceJFrame(invoiceBusinessAmount, invoiceDiscountAmount, invoiceFinalAmount, reAmount, invoiceBalanceAmount);
//
//                             printInvoiceJFrame.setVisible(true);


                            //    PrintUtilities.printComponent(printInvoiceJFrame);

                            
                            reAmountTf.setEditable(false);
                            invoiceItemCodeCb.setSelectedIndex(0);
                            reAmountTf.setText("");
                            discountChkbx.setSelected(false);
                            discountCb.setEnabled(false);
                            discountBtn.setEnabled(false);
                            invoiceItemCodeCb.setEnabled(false);
                            totalCostValue = 0.0;
                            totalAmountValue = 0.0;
                            
                            // Add Sales Point
                            
                            
                            newInvoiceBtn.requestFocus();
                            
                        } else {
                            reAmountTf.setEditable(false);
                            invoiceItemCodeCb.setSelectedIndex(0);
                            reAmountTf.setText("");
                            discountChkbx.setSelected(false);
                            discountCb.setEnabled(false);
                            discountBtn.setEnabled(false);
                            invoiceItemCodeCb.setEnabled(false);
                            totalCostValue = 0.0;
                            totalAmountValue = 0.0;
                            
                            newInvoiceBtn.requestFocus();
                            JOptionPane.showMessageDialog(this, "Customer Invoice Error.", "Error", JOptionPane.ERROR_MESSAGE);
                        }
                        
                    } else {
                        //    customerInvoiceManager.editCustomerInvoice(customerInvoice, customerInvoiceItems, companyBusiness);
                    }
                    
                    
                    
                    
                    
                    
                } else {
                    double lapsValue = totalAmount - reAmountValue;
                    
                    String lapsAmount = Double.toString(lapsValue);
                    
                    JOptionPane.showMessageDialog(this, lapsAmount + " rupees wanted", "Error",
                            JOptionPane.ERROR_MESSAGE);
                    reAmountTf.requestFocus();
                }
                
            } else {
                JOptionPane.showMessageDialog(this, "Invalid format for the Received Amount", "Error",
                        JOptionPane.ERROR_MESSAGE);
                reAmountTf.requestFocus();
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Receieved Amount can't be empty", "Error",
                    JOptionPane.ERROR_MESSAGE);
            reAmountTf.requestFocus();
        }
        
        
    }
    
    private void editItemCost() {
        
        String itemCode = (String) icItemCodeCb.getSelectedItem();
        
        if (itemCode != null && itemCode.length() > 0) {
            
            if (isNumber(itemCode)) {
                
                Integer itemCodeValue = new Integer(itemCode);
                
                Iterator it = itemLists.iterator();
                
                boolean isExist = false;
                
                Items updateItem = null;
                
                while (it.hasNext()) {
                    
                    updateItem = (Items) it.next();
                    
                    
                    if (itemCodeValue.equals(updateItem.getItemCode())) {
                        
                        isExist = true;
                        break;
                        
                    }
                    
                }
                
                if (isExist) {
                    
                    String costPrice = icCostPriceTf.getText();
                    
                    if (costPrice != null && costPrice.length() > 0) {
                        
                        if (isNumber(costPrice)) {
                            
                            double costValue = Double.parseDouble(costPrice);
                            
                            String itemType = (String) editItemTypeCb.getSelectedItem();
                            
                            updateItem.setItemCost(costValue);
                            updateItem.setEnteredDate(new Date());
                            updateItem.setEnteredBy(username);
                            
                            String response = itemManager.editItem(updateItem);
                            
                            if (response.equals(Constants.ITEM_UPDATED_SUCCESSFULLY)) {
                                
                                
                                JOptionPane.showMessageDialog(this, "Item Updated Successfully.", "Success",
                                        JOptionPane.INFORMATION_MESSAGE);
                                
                                newItemCodeTf.setText("");
                                newItemNameTf.setText("");
                                newItemTypeCb.setSelectedIndex(0);
                                newSalesPriceTf.setText("");
                                
                            } else if (response.equals(Constants.ITEM_NOT_EXIST)) {
                                JOptionPane.showMessageDialog(this, "Item Code not exist", "Warn",
                                        JOptionPane.WARNING_MESSAGE);
                            } else if (response.equals(Constants.ERROR)) {
                                JOptionPane.showMessageDialog(this, "Item did not update successfully", "Error",
                                        JOptionPane.ERROR_MESSAGE);
                            }
                            
                            
                            
                        } else {
                            
                            JOptionPane.showMessageDialog(this, "Invalid Format for the Item Cost", "Error",
                                    JOptionPane.ERROR_MESSAGE);
                            newItemNameTf.requestFocus();
                        }
                        
                    } else {
                        
                        JOptionPane.showMessageDialog(this, "Item Cost can't be empty", "Error",
                                JOptionPane.ERROR_MESSAGE);
                        newSalesPriceTf.requestFocus();
                    }
                    
                    
                    
                } else {
                    JOptionPane.showMessageDialog(this, "Invalid Item Code", "Warn",
                            JOptionPane.WARNING_MESSAGE);
                }
                
                
                
            } else {
                JOptionPane.showMessageDialog(this, "Invalid Format for the Item Code", "Error",
                        JOptionPane.ERROR_MESSAGE);
                newItemCodeTf.requestFocus();
            }
            
        } else {
            JOptionPane.showMessageDialog(this, "Item Code can't be empty", "Error",
                    JOptionPane.ERROR_MESSAGE);
            newItemCodeTf.requestFocus();
        }
        
    }
    
    private void discount() {
        
        if (discountChkbx.isSelected()) {
            
            if (itemWiseChkBx.isSelected()) {
                
                
                if (discountItemWiseCB.getItemCount() == 0) {
                    JOptionPane.showMessageDialog(this, "There is  no items to discount", "Warn", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                String itemWiseDiscountAmount = itemWiseDiscountTF.getText();
                double itemWiseDiscountValue = 0;
                String selectedItemName = (String) discountItemWiseCB.getSelectedItem();
                
                try {
                    
                    itemWiseDiscountValue = Double.parseDouble(itemWiseDiscountAmount);
                    
                } catch (NumberFormatException ex) {
                    
                    JOptionPane.showMessageDialog(this, "Invalid Format for the Discount Value", "Error", JOptionPane.ERROR_MESSAGE);
                }
                
                int noOfRows = invoiceTable.getRowCount();
                double amountValue = 0;
                int x = 0;
                while (noOfRows > 0 && x < noOfRows) {
                    
                    String itemName = (String) invoiceTable.getValueAt(x, 1);
                    
                    if (itemName.equalsIgnoreCase(selectedItemName)) {
                        
                        Double amount = (Double) invoiceTable.getValueAt(x, 4);
                        
                        amountValue = amount.doubleValue();
                        
                        break;
                    }
                    
                    x++;
                }
                
                double discoutAmount = 0;
                if (itemWisePercentRB.isSelected()) {
                    
                    discoutAmount = (itemWiseDiscountValue / 100) * amountValue;
                    
                } else if (itemWiseTextRB.isSelected()) {
                    
                    discoutAmount = itemWiseDiscountValue;
                }
                
                discountLb.setText(Double.toString(discoutAmount));
                
                itemWiseDiscountList.add(new Double(discoutAmount));
                
                invoiceTable.setValueAt(new Double(discoutAmount), x, 5);
                
                double totalAmount = Double.parseDouble(totalAmountLb.getText());
                
                invoiceTotalAmount = totalAmount;
                
                
                
                double newTotalAmount = totalAmount - discoutAmount;
                
                double roundTotal = round(newTotalAmount, 2);
                
                double roundDiscount = round(discoutAmount, 2);
                
                totalAmountLb.setText(Double.toString(roundTotal));
                
                discountLb.setText(Double.toString(roundDiscount));
                
                discountItemWiseCB.removeItem(selectedItemName);
                
                
                
                
                
                isDiscountAdded = true;
            } else {
                
                
                if (percentRadio.isSelected()) {
                    
                    String selectedDiscount = (String) discountCb.getSelectedItem();
                    
                    double discountPercent = Double.parseDouble(selectedDiscount);
                    
                    double totalAmount = Double.parseDouble(totalAmountLb.getText());
                    
                    invoiceTotalAmount = totalAmount;
                    
                    double discoutAmount = discountPercent / 100 * totalAmount;
                    
                    double newTotalAmount = totalAmount - discoutAmount;
                    
                    double roundTotal = round(newTotalAmount, 2);
                    
                    double roundDiscount = round(discoutAmount, 2);
                    
                    totalAmountLb.setText(Double.toString(roundTotal));
                    
                    discountLb.setText(Double.toString(roundDiscount));
                    
                    discountBtn.setEnabled(false);
                    discountCb.setEnabled(false);
                    discountChkbx.setEnabled(false);
                    
                    percentRadio.setEnabled(false);
                    percentRadio.setSelected(false);
                    textAmountRadio.setEnabled(false);
                    textAmountRadio.setSelected(false);
                    textDiscountTf.setEnabled(false);
                    textDiscountTf.setText("");
                    
                    isDiscountAdded = true;
                } else if (textAmountRadio.isSelected()) {
                    
                    String textDisocuntAmount = textDiscountTf.getText();
                    
                    if (textDisocuntAmount != null && textDisocuntAmount.length() > 0) {
                        
                        if (isNumber(textDisocuntAmount)) {
                            
                            double totalAmount = Double.parseDouble(totalAmountLb.getText());
                            
                            invoiceTotalAmount = totalAmount;
                            
                            double discoutAmount = Double.parseDouble(textDisocuntAmount);
                            
                            double newTotalAmount = totalAmount - discoutAmount;
                            
                            double roundTotal = round(newTotalAmount, 2);
                            
                            double roundDiscount = round(discoutAmount, 2);
                            
                            totalAmountLb.setText(Double.toString(roundTotal));
                            
                            discountLb.setText(Double.toString(roundDiscount));
                            
                            discountBtn.setEnabled(false);
                            discountCb.setEnabled(false);
                            discountChkbx.setEnabled(false);
                            
                            percentRadio.setEnabled(false);
                            percentRadio.setSelected(false);
                            textAmountRadio.setEnabled(false);
                            textAmountRadio.setSelected(false);
                            textDiscountTf.setEnabled(false);
                            textDiscountTf.setText("");
                            
                            isDiscountAdded = true;
                            
                            
                        } else {
                            
                            JOptionPane.showMessageDialog(this, "Invalid format for the discount amount", "Error", JOptionPane.ERROR_MESSAGE);
                            
                        }
                        
                    } else {
                        
                        JOptionPane.showMessageDialog(this, "Discount can't be empty", "Error", JOptionPane.ERROR_MESSAGE);
                    }
                    
                    
                }
            }
            
        }
        
        
        
    }
    
    private void loadItemCodesToStoresMgt() {
        
        itemLists = (Vector<Items>) itemManager.getItems();
        
        if (itemLists != null && itemLists.size() > 0) {
            
            editItemCodeCb.setEnabled(true);
            editItemnameCb.setEnabled(true);
            
            Iterator it = itemLists.iterator();
            
            while (it.hasNext()) {
                
                Items items = (Items) it.next();
                
                Integer itemCodeValue = items.getItemCode();
                
                String itemCode = null;
                
                if (itemCodeValue < 10) {
                    
                    itemCode = "000" + Integer.toString(itemCodeValue);
                } else if (10 <= itemCodeValue && itemCodeValue < 100) {
                    
                    itemCode = "00" + Integer.toString(itemCodeValue);
                } else if (100 <= itemCodeValue && itemCodeValue < 1000) {
                    
                    itemCode = "0" + Integer.toString(itemCodeValue);
                } else {
                    
                    itemCode = Integer.toString(itemCodeValue);
                }
                
                
                itemCodeStMgtCb.addItem(itemCode);
                
            }
        } else {
            
            editItemCodeCb.setEnabled(false);
            editItemnameCb.setEnabled(false);
        }
    }
    
    private void loadItemCodesToViewStoresMgt() {
        
        itemLists = (Vector<Items>) itemManager.getItems();
        
        if (itemLists != null && itemLists.size() > 0) {
            
            viewStMgtCb.setEnabled(true);
            
            
            Iterator it = itemLists.iterator();
            
            while (it.hasNext()) {
                
                Items items = (Items) it.next();
                
                Integer itemCodeValue = items.getItemCode();
                
                String itemCode = null;
                
                if (itemCodeValue < 10) {
                    
                    itemCode = "000" + Integer.toString(itemCodeValue);
                } else if (10 <= itemCodeValue && itemCodeValue < 100) {
                    
                    itemCode = "00" + Integer.toString(itemCodeValue);
                } else if (100 <= itemCodeValue && itemCodeValue < 1000) {
                    
                    itemCode = "0" + Integer.toString(itemCodeValue);
                } else {
                    
                    itemCode = Integer.toString(itemCodeValue);
                }
                
                
                viewStMgtCb.addItem(itemCode);
                
            }
        } else {
            
            viewStMgtCb.setEnabled(false);
            viewStMgtTf.setEnabled(false);
        }
    }
    
    public void loadSupplierCodesForEditDeleteSupplier() {
        
        supplierList = supplietManager.getSupplier();
        
        Iterator<Supplier> it = supplierList.iterator();
        
        while (it.hasNext()) {
            
            Supplier supplier = it.next();
            
            String supplierCode = supplier.getSuppliercode().toString();
            
            
            editDeleteSupplierCodeCb.addItem(supplierCode);
        }
    }
    
    public void loadSupplierCodesForStoresMgt() {
        
        supplierList = supplietManager.getSupplier();
        
        Iterator<Supplier> it = supplierList.iterator();
        
        while (it.hasNext()) {
            
            Supplier supplier = it.next();
            
            String supplierCode = supplier.getSuppliercode().toString();
            
            
            storesMgtSupplierCb.addItem(supplierCode);
        }
    }
    
    public void loadItemCodesForItemRejection() {
        itemLists = (Vector<Items>) itemManager.getItems();
        
        if (itemLists != null && itemLists.size() > 0) {
            
            
            Iterator it = itemLists.iterator();
            
            while (it.hasNext()) {
                
                Items items = (Items) it.next();
                
                Integer itemCodeValue = items.getItemCode();
                
                String itemCode = null;
                
                if (itemCodeValue < 10) {
                    
                    itemCode = "000" + Integer.toString(itemCodeValue);
                } else if (10 <= itemCodeValue && itemCodeValue < 100) {
                    
                    itemCode = "00" + Integer.toString(itemCodeValue);
                } else if (100 <= itemCodeValue && itemCodeValue < 1000) {
                    
                    itemCode = "0" + Integer.toString(itemCodeValue);
                } else {
                    
                    itemCode = Integer.toString(itemCodeValue);
                }
                
                
                itemRejectionReportItemCodeCb.addItem(itemCode);
                
            }
        }
    }
    
    public void loadSupplierCodesForSupplierRejection() {
        
        supplierList = supplietManager.getSupplier();
        
        Iterator<Supplier> it = supplierList.iterator();
        
        while (it.hasNext()) {
            
            Supplier supplier = it.next();
            
            String supplierCode = supplier.getSuppliercode().toString();
            
            supplierRejectionReportSupplierCodeCb.addItem(supplierCode);
        }
    }
    
    public void loadSupplierCodesForSupplierDelivered() {
        
        supplierList = supplietManager.getSupplier();
        
        Iterator<Supplier> it = supplierList.iterator();
        
        while (it.hasNext()) {
            
            Supplier supplier = it.next();
            
            String supplierCode = supplier.getSuppliercode().toString();
            
            supplierDeliveredReportSupplierCodeCb.addItem(supplierCode);
        }
    }
    
    public void loadItemsToNewTableFromFindItemName(Items itemsTmp, String noOfItemsBuy) {
        
        boolean isBarCodeOn = false;
        Vector row = new Vector();
        
        int selectedItemCodeIndex = invoiceItemCodeCb.getSelectedIndex();

        // Items itemsTmp = itemLists.elementAt(selectedItemCodeIndex - 1);
        String selectedItemName = itemsTmp.getItemName();


        // Add data to the discount item wise combo box


        //  discountItemWiseCB.addItem(selectedItemName);

        Integer itemCode = itemsTmp.getItemCode();
        Double price = new Double(itemsTmp.getSalesPrice());
        double costPerOne = itemsTmp.getItemCost();
        
        
        Integer quantity = null;
        
        if (isBarCodeOn) {
            
            quantity = new Integer(new Integer(1));
            
            totalCostValue = totalCostValue + costPerOne;
            
        } else {
            
            quantity = new Integer(new Integer(noOfItemsBuy));
            
            totalCostValue = totalCostValue + costPerOne * quantity.intValue();
        }
        
        int noOfRows = invoiceTable.getRowCount();
        
        boolean notExisted = true;
        
        int x = 0;
        while (noOfRows > 0 && x < noOfRows) {
            
            Integer itemCodes = (Integer) invoiceTable.getValueAt(x, 0);
            
            if (itemCodes.intValue() == itemCode.intValue()) {
                
                Double amount = (Double) invoiceTable.getValueAt(x, 4);
                Integer oldQuantity = (Integer) invoiceTable.getValueAt(x, 3);
                
                
                double newAmountValue = price * quantity;
                
                amount = amount + newAmountValue;
                
                int newQuantitiy = oldQuantity + quantity;
                
                invoiceTable.setValueAt(new Integer(newQuantitiy), x, 3);
                invoiceTable.setValueAt(new Double(amount), x, 4);
                
                totalAmountValue = totalAmountValue + newAmountValue;
                
                String totalAmount = Double.toString(totalAmountValue);
                
                
                if (!isEditCustomerInvoice) {
                    
                    double roundTotal = round(totalAmountValue, 2);
                    
                    totalAmountLb.setText(Double.toString(roundTotal));
                    
                } else {
                    
                    if (totalAmountValue <= returnValue) {
                        totalAmountLb.setText("0.00");
                    } else {
                        
                        double newTotalAmountValue = totalAmountValue - returnValue;
                        
                        double roundNewTotal = round(newTotalAmountValue, 2);
                        
                        String newTotalAmount = Double.toString(roundNewTotal);
                        
                        
                        
                        totalAmountLb.setText(newTotalAmount);
                    }
                }
                
                invoiceItemCodeCb.setSelectedIndex(0);
                invoiceItemCodeCb.requestFocus();
                
                noOfItemsTf.setText("");
                reAmountTf.setEditable(true);
                
                
                notExisted = false;
                break;
            }
            
            x++;
        }
        
        
        if (notExisted) {
            
            discountItemWiseCB.addItem(selectedItemName);
            
            
            row.addElement(itemCode);
            row.add(selectedItemName);
            row.addElement(price);
            row.addElement(quantity);
            
            removeItemCb.addItem(itemCode.toString());
            double priceValue = price.doubleValue();
            int quantityValue = quantity.intValue();
            
            double amountValue = priceValue * quantityValue;
            
            Double amount = new Double(amountValue);
            
            row.addElement(amount);
            row.addElement(0.0);
            
            if (rows != null) {
                rows.addElement(row);
            } else {
                return;
//                rows = new Vector();
//                rows.addElement(row);
            }
            
            totalAmountValue = totalAmountValue + amount;
            
            
            String totalAmount = Double.toString(totalAmountValue);
            
            if (!isEditCustomerInvoice) {
                
                totalAmountLb.setText(totalAmount);
                
            } else {
                
                if (totalAmountValue <= returnValue) {
                    totalAmountLb.setText("0.0");
                } else {
                    
                    double newTotalAmountValue = totalAmountValue - returnValue;
                    
                    String newTotalAmount = Double.toString(newTotalAmountValue);
                    
                    totalAmountLb.setText(newTotalAmount);
                }
            }
            
            invoiceItemCodeCb.setSelectedIndex(0);
            invoiceItemCodeCb.requestFocus();
            
            noOfItemsTf.setText("");
            reAmountTf.setEditable(true);
            discountChkbx.setEnabled(true);
            reAmountTf.requestFocus();
            
            invoiceTable.setModel(new DefaultTableModel(rows, invoiceColumns) {
                @Override
                public boolean isCellEditable(int row, int column) {
                    return false;
                }
            });
            
        }
        
        if (barCodeChkbx.isSelected()) {
            ++noOfItems;
        } else {
            
            noOfItems = noOfItems + quantity.intValue();
        }
        noOfIemsLb.setText(Integer.toString(noOfItems));
        
        String totalAmountCalculated = totalAmountLb.getText();
        double totalAmountCalculatedValue = Double.parseDouble(totalAmountCalculated);
        if (itemWiseDiscountList != null && itemWiseDiscountList.size() > 0) {
            
            Iterator<Double> it = itemWiseDiscountList.iterator();
            
            double totalDiscount = 0;
            while (it.hasNext()) {
                
                double discountValue = it.next();
                
                totalDiscount = totalDiscount + discountValue;
            }
            
            totalAmountCalculatedValue = totalAmountCalculatedValue - totalDiscount;
            
            totalAmountLb.setText(Double.toString(totalAmountCalculatedValue));
            
        }
    }
    
    public static double round(double Rval, int Rpl) {
        double p = (double) Math.pow(10, Rpl);
        Rval = Rval * p;
        double tmp = Math.round(Rval);
        return (double) tmp / p;
        
    }
    
    private void loadBusinessByStaff() {
    }
    
    public void openCashDrawer() {
        
        Runnable openCashDrawer = new Runnable() {
            public synchronized void run() {
                try {
                    
                    PrintService[] printService1 = PrinterJob.lookupPrintServices();
                    
                    PrintService printService = printService1[0];
                    DocPrintJob job = printService.createPrintJob();
                    DocAttributeSet das = new HashDocAttributeSet();
                    PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
                    SimpleDoc doc = new SimpleDoc(new byte[]{0x1B, 0x70, 0x00, 0x32, -0x06}, DocFlavor.BYTE_ARRAY.AUTOSENSE, das);
                    job.print(doc, pras);
                    
                } catch (PrintException ex) {
                    
                    Log4jUtil.logErrorMessage("Opening Cash Drawyer " + ex);
                    
                }
            }
        };
        (new Thread(openCashDrawer)).start();
    }
}
