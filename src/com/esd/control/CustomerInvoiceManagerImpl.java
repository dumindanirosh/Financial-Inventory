/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.control;

import com.esd.pojo.CompanyBusiness;
import com.esd.pojo.CustomerInvoice;
import com.esd.pojo.InvoiceItems;
import com.esd.pojo.InvoiceItemsPK;
import com.esd.pojo.Items;
import com.esd.pojo.Stores;
import com.esd.util.Constants;
import com.esd.util.Log4jUtil;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Duminda
 */
public class CustomerInvoiceManagerImpl implements CustomerInvoiceManager {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private UserActivityImpl userActivityImpl;
    private int invoiceId;

    public CustomerInvoiceManagerImpl() {

        entityManagerFactory = Persistence.createEntityManagerFactory("Financial_InventoryPU");

        entityManager = entityManagerFactory.createEntityManager();
    }

    public String addCustomerInvoice(CustomerInvoice customerInvoice, List<InvoiceItems> invoiceItems, CompanyBusiness companyBusiness) {

        EntityTransaction entityTransaction = null;

        try {

            if (entityManager != null) {


                entityTransaction = entityManager.getTransaction();

                if (!entityTransaction.isActive()) {

                    entityTransaction.begin();

                }

                Query lastRowQuery = entityManager.createQuery("SELECT c FROM CustomerInvoice c");

                int rows = lastRowQuery.getResultList().size();

                invoiceId = rows + 1;

                Iterator<InvoiceItems> it = invoiceItems.iterator();
                List<InvoiceItems> invoiceItemsList = new Vector<InvoiceItems>();

                while (it.hasNext()) {

                    InvoiceItems invoiceItem = it.next();

                    InvoiceItemsPK invoiceItemsPK = invoiceItem.getInvoiceItemsPK();

                    invoiceItemsPK.setInvoiceId(invoiceId);

                    invoiceItemsList.add(invoiceItem);

                    invoiceItem.getQuantity();

                    Stores storesTmp = entityManager.find(Stores.class, invoiceItem.getInvoiceItemsPK().getItemCode());


                    if (storesTmp != null) {

                        int availableQuantity = storesTmp.getQuantity();
                        int buyQuantity = invoiceItem.getQuantity();

                        int finalQuantity = availableQuantity - buyQuantity;

                        if (finalQuantity >= 0) {
                            storesTmp.setQuantity(finalQuantity);

                            entityManager.merge(storesTmp);

                            entityManager.flush();
                        } else {
                            storesTmp.setQuantity(0);
                        }
                    }
                }

                customerInvoice.setInvoiceId(invoiceId);
                customerInvoice.setInvoiceItemsList(invoiceItemsList);


                try {


                    Query businesDateQuery = entityManager.createQuery("SELECT c FROM CompanyBusiness c WHERE c.businessDate = :businessDate");
                    businesDateQuery.setParameter("businessDate", companyBusiness.getBusinessDate());


                    CompanyBusiness companyBusinessTmp = (CompanyBusiness) businesDateQuery.getSingleResult();

                    if (companyBusinessTmp != null) {

                        double businessAmountTmp = companyBusiness.getBusinessAmount() + companyBusinessTmp.getBusinessAmount();
                        double profitAmountTmp = companyBusiness.getBusinessProfit() + companyBusinessTmp.getBusinessProfit();
                        double cashierAmountTmp = companyBusiness.getCashierAmount() + companyBusinessTmp.getCashierAmount();
                        double discountAmountTmp = companyBusiness.getDiscountAmount() + companyBusinessTmp.getDiscountAmount();

                        companyBusinessTmp.setBusinessAmount(businessAmountTmp);
                        companyBusinessTmp.setCashierAmount(cashierAmountTmp);
                        companyBusinessTmp.setBusinessProfit(profitAmountTmp);
                        companyBusinessTmp.setDiscountAmount(discountAmountTmp);
                        companyBusinessTmp.setEnteredBy(companyBusiness.getEnteredBy());
                        companyBusinessTmp.setEnteredDate(new Date());

                        entityManager.merge(companyBusinessTmp);
                        entityManager.flush();

                    }

                    entityManager.persist(customerInvoice);

                    entityManager.flush();

                    entityTransaction.commit();

                    Integer newInvoice = new Integer(invoiceId);

                    return newInvoice.toString();


                } catch (NoResultException ex) {


                    String oldCashierAmount = getCashierAmount();

                    double oldCashierValue = Double.parseDouble(oldCashierAmount);

                    double newCashValue = oldCashierValue + companyBusiness.getCashierAmount();

                    companyBusiness.setCashierAmount(newCashValue);

                    entityManager.persist(companyBusiness);
                    entityManager.flush();

                    entityManager.persist(customerInvoice);

                    entityManager.flush();

                    entityTransaction.commit();

                    Integer newInvoice = new Integer(invoiceId);

                    return newInvoice.toString();

                }


            } else {
                Log4jUtil.logErrorMessage("public String addCustomerInvoice(CustomerInvoice customerInvoice) entity manager object null ");
                return Constants.ERROR;
            }

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public String addCustomerInvoice(CustomerInvoice customerInvoice) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }
    }

    public CustomerInvoice getCustomerInvoiceForInvoiceId(String invoiceId) {
        try {
            Query q = entityManager.createQuery("SELECT c FROM CustomerInvoice c WHERE c.invoiceId = :invoiceId");
            q.setParameter("invoiceId", new Integer(invoiceId));

            CustomerInvoice customerInvoice = (CustomerInvoice)q.getSingleResult();
            return customerInvoice;
        } catch (Exception ex) {
            Log4jUtil.logErrorMessage(" public CustomerInvoice getCustomerInvoiceForInvoiceId(String invoiceId) " + ex);
            return null;
        }
    }

    public String editCustomerInvoice(CustomerInvoice editCustomerInvoice, List<InvoiceItems> invoiceItems, CompanyBusiness companyBusiness) {


        EntityTransaction entityTransaction = null;

        try {

            if (entityManager != null) {


                entityTransaction = entityManager.getTransaction();

                if (!entityTransaction.isActive()) {

                    entityTransaction.begin();

               }

                    CustomerInvoice customerInvoiceTmp = entityManager.find(CustomerInvoice.class, editCustomerInvoice.getInvoiceId());

                    if(customerInvoiceTmp != null){
                     entityManager.merge(editCustomerInvoice);
                     entityManager.flush();
                    entityTransaction.commit();
                    }
                return Constants.CUSTOMER_INVOICE_UPDATED_SUCCESSFULLY;

            } else {
                Log4jUtil.logErrorMessage("public String editCustomerInvoice(CustomerInvoice customerInvoice) entity manager object null ");
                return Constants.ERROR;
            }

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public String editCustomerInvoice(CustomerInvoice customerInvoice) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }
    }

    public String getCashierAmount() {

        try {

            Query q = entityManager.createQuery("SELECT c.cashierAmount FROM CompanyBusiness c ORDER BY c.companyBusinessId DESC");
            q.setMaxResults(1);
            Double companyBusiness = (Double) q.getSingleResult();

            String cashierAmount = Double.toString(companyBusiness);
            return cashierAmount;

        } catch (Exception ex) {

            return null;
        }
    }

    public List<CustomerInvoice> loadCustomerInvoicesByIdPPeriod(String period) {

        try {
            String start = period.substring(0, period.indexOf("-"));
            String end   = period.substring(period.indexOf("-") + 1, period.length());


            Query q = entityManager.createQuery("SELECT c FROM CustomerInvoice c WHERE c.invoiceId BETWEEN " + start.trim() + " AND " + end.trim());

            List<CustomerInvoice> ls = q.getResultList();

            return ls;
        } catch (Exception ex) {
                            Log4jUtil.logErrorMessage(" List<CustomerInvoice> loadCustomerInvoicesByIdPPeriod(String period) " + ex);
            return null;
        }
    }
}
