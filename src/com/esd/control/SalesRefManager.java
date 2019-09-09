/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.control;

import com.esd.pojo.InvoiceSales;
import com.esd.pojo.Salesref;
import java.util.List;

/**
 *
 * @author Duminda
 */
public interface SalesRefManager {
    
    public String addSalesRef(Salesref  salesref);
    public String addSalesPoints(InvoiceSales  invoiceSales);
    public String updateSalesRef(Salesref  salesref);
    public String deleteSalesRef(Salesref  salesref);
   
    public List<Salesref> loadSalesRef();
}
