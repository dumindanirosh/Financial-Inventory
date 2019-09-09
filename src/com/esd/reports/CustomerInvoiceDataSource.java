/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.reports;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRField;

/**
 *
 * @author Administrator
 */
public class CustomerInvoiceDataSource implements JRDataSource {

    private Object[][] resultArray;
    private int index = -1;

    public CustomerInvoiceDataSource(Object[][] invoiceData) {
        this.resultArray = invoiceData;
    }

    public boolean next() throws JRException {

        index++;

        return (index < resultArray.length);

    }

    public Object getFieldValue(JRField field) throws JRException {

        String fieldName = field.getName();
        System.out.println("ffffffffffff  "+ fieldName);
        Object value = null;

        if (fieldName.equals("itemCode")) {
            value = resultArray[index][0];

        } else if (fieldName.equals("totalPerItem")) {
            value = resultArray[index][1];
        } else if (fieldName.equals("totalPerItemValue")) {
            value = resultArray[index][2];
        }
        return value;
    }

    public static CustomerInvoiceDataSource CebDataSource() {
        return null;
    }
}
