/*
 * ReportManagerImpl.java
 *
 * Created on September 18, 2000, 2:01 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.esd.reports;

import com.esd.util.Constants;
import com.esd.util.Log4jUtil;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperPrintManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Duminda
 */
public class ReportManagerImpl implements ReportManager {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private static Object[][] resultArray;
    private String selectQueryForRetrieveData = "SELECT pcms_phone_bill.epfNumber,pcms_phone_bill.billingMonth,pcms_employee.title," +
            "pcms_employee.firstName,pcms_employee.surname,pcms_phone_bill.phoneNumber, pcms_phone_bill.totalBill,pcms_phone_bill.reimbursement, pcms_phone_bill.deductionFromSalary " +
            "FROM pcms_employee,pcms_phone_bill WHERE pcms_phone_bill.billingMonth =? AND pcms_phone_bill.billingYear=? " +
            "AND pcms_employee.epfNumber = pcms_phone_bill.epfNumber ORDER BY pcms_phone_bill.epfNumber";

    public ReportManagerImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Financial_InventoryPU");

        entityManager = entityManagerFactory.createEntityManager();



    }

    public boolean getItemsViewReport() {

        try {

            String classPath = System.getProperty("user.dir");

            Map<String, Object> params = new HashMap<String, Object>();

            String dataSource = classPath.trim() + "\\build\\classes\\com\\esd\\reports\\ViewItemsReport.jrxml";


            //     String dataSource =classPath.trim() + "\\src\\com\\reload\\report\\DailyBusinessProfit.jrxml";


            JasperReport jasperReport = JasperCompileManager.compileReport(dataSource);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ConnectionManager.getConnection());



            // Get the out for jasper report
            JasperViewer.viewReport(jasperPrint, false);



        } catch (JRException ex) {

            Log4jUtil.logErrorMessage("  public boolean getItemsViewReport() " + ex);
            return false;

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("  public boolean getItemsViewReport() " + ex);
            return false;

        }
        return false;
    }

    public boolean getMonthlyExpensesReport(String month, String year) {

        String classPath = System.getProperty("user.dir");

        try {

            Map<String, Object> params = new HashMap<String, Object>();

            String monthYear = month + "-" + year;

            params.put("monthYear", monthYear);
            params.put("expenseMonth", month);
            params.put("expenseYear", year);

            String dataSource = classPath.trim() + "\\build\\classes\\com\\esd\\reports\\MonthlyExpensesReport.jrxml";

            //   String dataSource =classPath.trim() + "\\src\\com\\reload\\report\\MonthlyBusinessProfit.jrxml";


            JasperReport jasperReport = JasperCompileManager.compileReport(dataSource);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ConnectionManager.getConnection());

            // Get the out for jasper report
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {

            Log4jUtil.logErrorMessage("public boolean getMonthlyExpensesReport(String month, String year) " + ex);
            return false;

        } catch (Exception ex) {
            Log4jUtil.logErrorMessage("public boolean getMonthlyExpensesReport(String month, String year) " + ex);
            return false;

        }


        return true;
    }

    public boolean getMonthlyBusinessReport(String month, String year) {


        String classPath = System.getProperty("user.dir");

        try {

            Map<String, Object> params = new HashMap<String, Object>();

            String monthYear = month + "-" + year;

            params.put("monthYear", monthYear);
            params.put("businessMonth", month);
            params.put("businessYear", year);

            String dataSource = classPath.trim() + "\\build\\classes\\com\\esd\\reports\\ViewMonthlyBusinessReport.jrxml";

            //   String dataSource =classPath.trim() + "\\src\\com\\reload\\report\\MonthlyBusinessProfit.jrxml";


            JasperReport jasperReport = JasperCompileManager.compileReport(dataSource);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ConnectionManager.getConnection());

            // Get the out for jasper report
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {

            Log4jUtil.logErrorMessage("public boolean getMonthlyExpensesReport(String month, String year) " + ex);
            return false;

        } catch (Exception ex) {
            Log4jUtil.logErrorMessage("public boolean getMonthlyExpensesReport(String month, String year) " + ex);
            return false;

        }


        return true;

    }

    public boolean getMonthlyBusinessReportForCashier(String month, String year) {


        String classPath = System.getProperty("user.dir");

        try {

            Map<String, Object> params = new HashMap<String, Object>();

            String monthYear = month + "-" + year;

            params.put("monthYear", monthYear);
            params.put("businessMonth", month);
            params.put("businessYear", year);

            String dataSource = classPath.trim() + "\\build\\classes\\com\\esd\\reports\\CashierBusinessReport.jrxml";

            //   String dataSource =classPath.trim() + "\\src\\com\\reload\\report\\MonthlyBusinessProfit.jrxml";


            JasperReport jasperReport = JasperCompileManager.compileReport(dataSource);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ConnectionManager.getConnection());

            // Get the out for jasper report
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {

            Log4jUtil.logErrorMessage("public boolean getMonthlyBusinessReportForCashier(String month, String year) " + ex);
            return false;

        } catch (Exception ex) {
            Log4jUtil.logErrorMessage("public boolean getMonthlyBusinessReportForCashier(String month, String year) " + ex);
            return false;

        }


        return true;

    }

    public boolean printCustomerInvoice(String invoiceId, String username, String invoiceTotalAmount, String invoiceReceiveAmount, String invoiceBalanceAmount) {


        String classPath = System.getProperty("user.dir");

        try {

            Map<String, Object> params = new HashMap<String, Object>();

            Map nameList = new HashMap();

            params.put("invoiceId", invoiceId);
            params.put("username", username);
            params.put("totalAmount", invoiceTotalAmount);
            params.put("receiveAmount", invoiceReceiveAmount);
            params.put("balanceAmount", invoiceBalanceAmount);


            String dataSource = classPath.trim() + "\\build\\classes\\com\\esd\\reports\\customerInvoice.jrxml";


            JasperReport jasperReport = JasperCompileManager.compileReport(dataSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ConnectionManager.getConnection());

    //        JasperViewer.viewReport(jasperPrint, false);

//            PrinterJob ppjj = PrinterJob.getPrinterJob();
//
//            String selectedPrintService = null;
//
//
//            int i = 0;
//            for (PrintService ps : PrinterJob.lookupPrintServices()) {
//
//                PrintService printServiceName = ps;
//
//
//                if (printServiceName.getName().indexOf("SRP-275") != -1) {
//
//                    selectedPrintService = printServiceName.getName();
//
//                    ppjj.setPrintService(ps);
//
//                    break;
//                }
//                i++;
//
//            }


//            DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
//PrintRequestAttributeSet aset = new HashPrintRequestAttributeSet();
//aset.add(MediaSizeName.INVOICE);
//
//
//PrintService[] pservices = PrintServiceLookup.lookupPrintServices( flavor, aset);
//if (pservices.length > 0) {
//DocPrintJob pj = pservices[0].createPrintJob();
//    System.out.println("dsadsadsad "+ pservices[0]);
//// JasperPrintManager.printReport(jasperPrint, false);
//}

            JasperPrintManager.printReport(jasperPrint, false);



        } catch (JRException ex) {

            Log4jUtil.logErrorMessage("public boolean printCustomerInvoice(String invoiceId, String username) " + ex);
            return false;

        } catch (Exception ex) {


            Log4jUtil.logErrorMessage("public booleanprintCustomerInvoice(String invoiceId, String username) " + ex);
            return false;

        }


        return true;
    }

    public boolean printCustomerInvoice(String invoiceId, String userName) {


        String classPath = System.getProperty("user.dir");

        try {

            Map<String, Object> params = new HashMap<String, Object>();

            Map nameList = new HashMap();

            params.put("invoiceId", invoiceId);
            params.put("username", userName);


            String dataSource = classPath.trim() + "\\build\\classes\\com\\esd\\reports\\customerInvoiceReprint.jrxml";

            JasperReport jasperReport = JasperCompileManager.compileReport(dataSource);

            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ConnectionManager.getConnection());

            JasperViewer.viewReport(jasperPrint, false);

//            JasperPrintManager.printReport(jasperPrint, false);



        } catch (JRException ex) {

            Log4jUtil.logErrorMessage("public boolean printCustomerInvoice(String invoiceId, String username) " + ex);
            return false;

        } catch (Exception ex) {


            Log4jUtil.logErrorMessage("public booleanprintCustomerInvoice(String invoiceId, String username) " + ex);
            return false;

        }


        return true;
    }

    public boolean getDailyItemBusinessReport(String date) {


        String classPath = System.getProperty("user.dir");

        try {

            Map<String, Object> params = new HashMap<String, Object>();

            params.put("date", date);


            String dataSource = classPath.trim() + "\\build\\classes\\com\\esd\\reports\\ViewDailyBusinessItemsReport.jrxml";

            //   String dataSource =classPath.trim() + "\\src\\com\\reload\\report\\MonthlyBusinessProfit.jrxml";


            JasperReport jasperReport = JasperCompileManager.compileReport(dataSource);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ConnectionManager.getConnection());

            // Get the out for jasper report
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {

            Log4jUtil.logErrorMessage("public boolean getMonthlyBusinessReportForCashier(String month, String year) " + ex);
            return false;

        } catch (Exception ex) {
            Log4jUtil.logErrorMessage("public boolean getMonthlyBusinessReportForCashier(String month, String year) " + ex);
            return false;

        }


        return true;
    }

    public boolean getDailyStoresMgt(String storesMgtDate) {


        String classPath = System.getProperty("user.dir");

        try {

            Map<String, Object> params = new HashMap<String, Object>();

            params.put("purchaseDate", storesMgtDate);


            String dataSource = classPath.trim() + "\\build\\classes\\com\\esd\\reports\\storesMgtReport.jrxml";

            //   String dataSource =classPath.trim() + "\\src\\com\\reload\\report\\MonthlyBusinessProfit.jrxml";


            JasperReport jasperReport = JasperCompileManager.compileReport(dataSource);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ConnectionManager.getConnection());

            // Get the out for jasper report
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {

            Log4jUtil.logErrorMessage("public boolean getMonthlyBusinessReportForCashier(String month, String year) " + ex);
            return false;

        } catch (Exception ex) {
            Log4jUtil.logErrorMessage("public boolean getMonthlyBusinessReportForCashier(String month, String year) " + ex);
            return false;

        }

        return true;


    }

    public boolean getItemCodeStoresMgtReport(Integer itemCode) {


        String classPath = System.getProperty("user.dir");

        try {

            Map<String, Object> params = new HashMap<String, Object>();

            params.put("itemCode", itemCode);


            String dataSource = classPath.trim() + "\\build\\classes\\com\\esd\\reports\\storesMgtReportByItemCode.jrxml";


            JasperReport jasperReport = JasperCompileManager.compileReport(dataSource);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ConnectionManager.getConnection());

            // Get the out for jasper report
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {
            System.out.println("fsfsdfsfdsffsdfsfsdf  " + ex);
            Log4jUtil.logErrorMessage("public boolean getItemCodeStoresMgtReport(Integer itemCode) " + ex);
            return false;

        } catch (Exception ex) {
            System.out.println("eeeeeeeeeeeeeeeeeeee " + ex);
            Log4jUtil.logErrorMessage("public booleangetItemCodeStoresMgtReport(Integer itemCode) " + ex);
            return false;

        }

        return true;


    }

    public boolean getStoresReport() {

        try {

            String classPath = System.getProperty("user.dir");

            Map<String, Object> params = new HashMap<String, Object>();

            String dataSource = classPath.trim() + "\\build\\classes\\com\\esd\\reports\\View_Stores_Report.jrxml";


            //     String dataSource =classPath.trim() + "\\src\\com\\reload\\report\\DailyBusinessProfit.jrxml";


            JasperReport jasperReport = JasperCompileManager.compileReport(dataSource);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ConnectionManager.getConnection());



            // Get the out for jasper report
            JasperViewer.viewReport(jasperPrint, false);



        } catch (JRException ex) {

            Log4jUtil.logErrorMessage("  public boolean getStoresReport() " + ex);
            return false;

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("  public boolean getStoresReport() " + ex);
            return false;

        }
        return false;
    }

    public boolean getReOrderReport() {
        try {

            String classPath = System.getProperty("user.dir");

            Map<String, Object> params = new HashMap<String, Object>();

            String dataSource = classPath.trim() + "\\build\\classes\\com\\esd\\reports\\ReOrder_Stores_Report.jrxml";


            //     String dataSource =classPath.trim() + "\\src\\com\\reload\\report\\DailyBusinessProfit.jrxml";


            JasperReport jasperReport = JasperCompileManager.compileReport(dataSource);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ConnectionManager.getConnection());



            // Get the out for jasper report
            JasperViewer.viewReport(jasperPrint, false);



        } catch (JRException ex) {

            Log4jUtil.logErrorMessage("  public boolean getReOrderReport() " + ex);
            return false;

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("  public boolean getReOrderReport() " + ex);
            return false;

        }
        return false;

    }

    public boolean getItemRejectionReport(String month, String year, int itemCode) {

        String classPath = System.getProperty("user.dir");

        try {
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("itemCode", new Integer(itemCode));
            params.put("month", month);
            params.put("year", year);
            params.put("COMPANY_NAME", Constants.COMPANY_NAME);
            params.put("COMPANY_ADDRESS", Constants.COMPANY_ADDRESS);
            params.put("EMAIL_ADDRESS", Constants.EMAIL_ADDRESS);
            params.put("MOBILE_NUMBER", Constants.MOBILE_NUMBER);
            params.put("OFFICE_NUMBER", Constants.OFFICE_NUMBER);
            


            String dataSource = classPath.trim() + "\\build\\classes\\com\\esd\\reports\\itemRejectionReport.jrxml";


            JasperReport jasperReport = JasperCompileManager.compileReport(dataSource);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ConnectionManager.getConnection());

            // Get the out for jasper report
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {

            Log4jUtil.logErrorMessage("public boolean getItemRejectionReport(String month, String year, int itemCode) " + ex);
            return false;

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public boolean getItemRejectionReport(String month, String year, int itemCode) " + ex);
            return false;

        }

        return true;

    }

      public boolean getSupplierRejectionReport(String month, String year, String supplierCode) {

        String classPath = System.getProperty("user.dir");

        try {
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("supplierCode", supplierCode);
            params.put("month", month);
            params.put("year", year);
            params.put("COMPANY_NAME", Constants.COMPANY_NAME);
            params.put("COMPANY_ADDRESS", Constants.COMPANY_ADDRESS);
            params.put("EMAIL_ADDRESS", Constants.EMAIL_ADDRESS);
            params.put("MOBILE_NUMBER", Constants.MOBILE_NUMBER);
            params.put("OFFICE_NUMBER", Constants.OFFICE_NUMBER);



            String dataSource = classPath.trim() + "\\build\\classes\\com\\esd\\reports\\supplierRejectionReport.jrxml";


            JasperReport jasperReport = JasperCompileManager.compileReport(dataSource);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ConnectionManager.getConnection());

            // Get the out for jasper report
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {

            Log4jUtil.logErrorMessage("public boolean getSupplierRejectionReport(String month, String year, String itemCode) " + ex);
            return false;

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public boolean getSupplierRejectionReport(String month, String year, String itemCode) " + ex);
            return false;

        }

        return true;

    }


      public boolean getSupplierDeliveredReport(String month, String year, String supplierCode) {

        String classPath = System.getProperty("user.dir");

        try {
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("supplierCode", supplierCode);
            params.put("month", month);
            params.put("year", year);
            params.put("COMPANY_NAME", Constants.COMPANY_NAME);
            params.put("COMPANY_ADDRESS", Constants.COMPANY_ADDRESS);
            params.put("EMAIL_ADDRESS", Constants.EMAIL_ADDRESS);
            params.put("MOBILE_NUMBER", Constants.MOBILE_NUMBER);
            params.put("OFFICE_NUMBER", Constants.OFFICE_NUMBER);



            String dataSource = classPath.trim() + "\\build\\classes\\com\\esd\\reports\\supplierDeliveredReport.jrxml";


            JasperReport jasperReport = JasperCompileManager.compileReport(dataSource);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ConnectionManager.getConnection());

            // Get the out for jasper report
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {

            Log4jUtil.logErrorMessage("public boolean getSupplierDeliveredReport(String month, String year, int itemCode) " + ex);
            return false;

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public boolean getSupplierDeliveredReport(String month, String year, int itemCode) " + ex);
            return false;

        }

        return true;

    }

      public boolean viewSuppliersReport(){

                  String classPath = System.getProperty("user.dir");

        try {
            Map<String, Object> params = new HashMap<String, Object>();

            params.put("COMPANY_NAME", Constants.COMPANY_NAME);
            params.put("COMPANY_ADDRESS", Constants.COMPANY_ADDRESS);
            params.put("EMAIL_ADDRESS", Constants.EMAIL_ADDRESS);
            params.put("MOBILE_NUMBER", Constants.MOBILE_NUMBER);
            params.put("OFFICE_NUMBER", Constants.OFFICE_NUMBER);



            String dataSource = classPath.trim() + "\\build\\classes\\com\\esd\\reports\\viewSuppliers.jrxml";


            JasperReport jasperReport = JasperCompileManager.compileReport(dataSource);

            // Fill the report
            JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params, ConnectionManager.getConnection());

            // Get the out for jasper report
            JasperViewer.viewReport(jasperPrint, false);

        } catch (JRException ex) {

            Log4jUtil.logErrorMessage("public boolean viewSuppliersReport() " + ex);
            return false;

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public boolean viewSuppliersReport() " + ex);
            return false;

        }

        return true;

      }
//    public Object[][] getDataForCustomerInvoice(String invoiceId) {
//
//        /* Get data from database and assign to the object array
//         */
//        try {
//
//            if (entityManager != null) {
//
//                System.out.println("iiiiiiii  " + invoiceId);
//
//                CustomerInvoice customerInvoice = entityManager.find(CustomerInvoice.class, new Integer(invoiceId));
//
//                if (customerInvoice != null) {
//
//                    List<InvoiceItems> invoiceItems = customerInvoice.getInvoiceItemsList();
//
//                    if (invoiceItems != null && invoiceItems.size() > 0) {
//
//                        resultArray = new Object[invoiceItems.size()][3];
//
//                        Iterator it = invoiceItems.iterator();
//                        int i = 0;
//                        while (it.hasNext()) {
//
//                            InvoiceItems invoiceItemsTmp = (InvoiceItems) it.next();
//
//                            Items item = entityManager.find(Items.class, invoiceItemsTmp.getInvoiceItemsPK().getItemCode());
//
//                            Integer itemCode = item.getItemCode();
//                            String itemName = item.getItemName();
//
//                            double discountPercent = customerInvoice.getDiscountPercent();
//
//                            double salesPrice = item.getSalesPrice();
//
//                            int quantity = invoiceItemsTmp.getQuantity();
//
//                            double pricePerOne = 0.0;
//
//                            if (discountPercent > 0) {
//
//                                pricePerOne = salesPrice - salesPrice * discountPercent / 100;
//
//
//                            } else {
//
//                                pricePerOne = salesPrice;
//
//
//                            }
//
//                            double totalPerItemValue = pricePerOne * quantity;
//
//                            String totalPerItem = Integer.toString(quantity) + " * " + Double.toString(pricePerOne);
//
//                            resultArray[i][0] = itemCode;
//                            resultArray[i][1] = totalPerItem;
//                            resultArray[i][2] = totalPerItemValue;
//
//                            System.out.println("i C  " + resultArray[i][0]);
//                            System.out.println("2 C " + resultArray[i][1]);
//                            System.out.println("3 C " + resultArray[i][2]);
//                        }
//
//                        return resultArray;
//
//                    } else {
//
//                        return null;
//                    }
//                } else {
//                    return null;
//                }
//            } else {
//                return null;
//            }
//        } catch (Exception ex) {
//            System.out.println("dsadsahdfsadkjash Exception " + ex);
//            return null;
//        }
//
//    }
}
