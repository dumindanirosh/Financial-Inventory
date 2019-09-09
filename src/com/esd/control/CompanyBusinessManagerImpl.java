/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.control;

import com.esd.pojo.CompanyBusiness;
import com.esd.pojo.Stores;
import com.esd.util.Constants;
import com.esd.util.Log4jUtil;
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
public class CompanyBusinessManagerImpl implements CompanyBusinessManager {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private UserActivityImpl userActivityImpl;

    public CompanyBusinessManagerImpl() {
           entityManagerFactory = Persistence.createEntityManagerFactory("Financial_InventoryPU");


        entityManager = entityManagerFactory.createEntityManager();
    }

    public String updateCompanyBusinessForEditCustomerInvoice(CompanyBusiness companyBusiness) {

        EntityTransaction entityTransaction = null;

        try {

            if (entityManager != null) {


                entityTransaction = entityManager.getTransaction();

                if (!entityTransaction.isActive()) {

                    entityTransaction.begin();

                }
                boolean dataExisted = false;
                CompanyBusiness companyBusinessTmp = null;
                try {
                    Query q = entityManager.createQuery("SELECT c FROM CompanyBusiness c WHERE c.businessDate = :businessDate");
                    q.setParameter("businessDate", companyBusiness.getBusinessDate());

                    companyBusinessTmp = (CompanyBusiness) q.getSingleResult();
                    dataExisted = true;
                } catch (NoResultException ex) {
                    dataExisted = false;

                }
                if(companyBusinessTmp != null){

                double businessAmount = companyBusinessTmp.getBusinessAmount() ;
                double businessProfit = companyBusinessTmp.getBusinessProfit();
                double discountAmount = companyBusinessTmp.getDiscountAmount();

                double updatedBusinessAmount = businessAmount - companyBusiness.getBusinessAmount();
                double updatedBusniessProfit = businessProfit - companyBusiness.getBusinessProfit();
                double updatedBusinessDiscount = discountAmount - companyBusiness.getDiscountAmount();

                companyBusinessTmp.setBusinessAmount(updatedBusinessAmount);
                companyBusinessTmp.setBusinessProfit(updatedBusniessProfit);
                companyBusinessTmp.setDiscountAmount(updatedBusinessDiscount);
                
                entityManager.merge(companyBusinessTmp);
                entityManager.flush();
                entityTransaction.commit();
                }
                return Constants.CONTACTS;


            } else {
                Log4jUtil.logErrorMessage("public String updateCompanyBusinessForEditCustomerInvoice(CompanyBusiness companyBusiness) entity manager object null ");
                return Constants.ERROR;
            }

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public String updateCompanyBusinessForEditCustomerInvoice(CompanyBusiness companyBusiness) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }

    }
}
