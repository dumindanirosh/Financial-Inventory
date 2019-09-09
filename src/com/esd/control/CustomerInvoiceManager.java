/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esd.control;

import com.esd.pojo.CompanyBusiness;
import com.esd.pojo.CustomerInvoice;
import com.esd.pojo.InvoiceItems;
import java.util.List;

/**
 *
 * @author Duminda
 */
public interface CustomerInvoiceManager {

    public String addCustomerInvoice(CustomerInvoice customerInvoice, List<InvoiceItems> invoiceItems, CompanyBusiness companyBusiness);


   public String editCustomerInvoice(CustomerInvoice customerInvoice, List<InvoiceItems> invoiceItems, CompanyBusiness companyBusiness);


    public CustomerInvoice getCustomerInvoiceForInvoiceId(String invoiceId);

    public String getCashierAmount();

    public List<CustomerInvoice> loadCustomerInvoicesByIdPPeriod(String period);




}
