/*
 * ReportManager.java
 *
 * Created on September 18, 2000, 2:00 AM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.esd.reports;

import java.util.Date;

/**
 *
 * @author Duminda
 */
public interface ReportManager {

//    public boolean getDailyBusinessReport(Date businessDate, String reloadType);
//
    public boolean getMonthlyExpensesReport(String month, String year);
//
//    public boolean getUserActivityReport(String reloadType, String month, String year);

    public boolean getItemsViewReport();

    public boolean getMonthlyBusinessReport(String month, String year);

    public boolean getMonthlyBusinessReportForCashier(String month, String year);

    public boolean printCustomerInvoice(String invoiceId, String username, String invoiceTotalAmount, String invoiceReceiveAmount, String invoiceBalanceAmount);

    public boolean printCustomerInvoice(String invoiceId, String userName);

    public boolean getDailyItemBusinessReport(String date);

    public boolean getDailyStoresMgt(String storesMgtDate);

    public boolean getItemCodeStoresMgtReport(Integer itemCode);

    public boolean getStoresReport();

    public boolean getReOrderReport();

    public  boolean getItemRejectionReport(String month, String year, int itemCode);

    public  boolean getSupplierRejectionReport(String month, String year, String supplierCode);

     public  boolean getSupplierDeliveredReport(String month, String year, String supplierCode);

     public boolean viewSuppliersReport();
}
