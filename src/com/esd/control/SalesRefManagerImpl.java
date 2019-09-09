/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.control;

import com.esd.pojo.InvoiceSales;
import com.esd.pojo.Salesref;
import com.esd.util.Constants;
import com.esd.util.Log4jUtil;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Duminda
 */
public class SalesRefManagerImpl implements SalesRefManager {

    private EntityManagerFactory entityManagerFactory;
    private UserActivityImpl userActivityImpl;
    private EntityManager entityManager;

    public SalesRefManagerImpl() {

        entityManagerFactory = Persistence.createEntityManagerFactory("Financial_InventoryPU");

        entityManager = entityManagerFactory.createEntityManager();
    }

    public String addSalesRef(Salesref salesref) {

        EntityTransaction entityTransaction = null;

        try {

            if (entityManager != null) {

                Salesref loginTmp = entityManager.find(Salesref.class, salesref.getSalesrefid());

                if (loginTmp == null) {

                    entityTransaction = entityManager.getTransaction();

                    if (!entityTransaction.isActive()) {

                        entityTransaction.begin();

                    }

                    entityManager.persist(salesref);
                    entityManager.flush();
                    entityTransaction.commit();


                     return Constants.SALES_REF_ADDED_SUCCESSFULLY;

                } else {

                    return Constants.USER_EXIST;

                }

            } else {

                Log4jUtil.logErrorMessage("public String addSalesRef(Salesref salesref)  entity manager object null");
                return Constants.ERROR;

            }

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public String addSalesRef(Salesref salesref) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }
    }

    public List<Salesref> loadSalesRef() {
        try {

            if (entityManager != null) {
                Query q = entityManager.createQuery("SELECT i FROM Salesref i");

                List sarefList = q.getResultList();

                 return sarefList;
            } else {
                return null;
            }
        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public List<Salesref> loadSalesRef() " + ex);

            return null;
        }
    }

    public String updateSalesRef(Salesref salesref) {

        EntityTransaction entityTransaction = null;

        try {

            if (entityManager != null) {

                Salesref salesRefTmp = entityManager.find(Salesref.class, salesref.getSalesrefid());

                if (salesRefTmp != null) {

                    entityTransaction = entityManager.getTransaction();

                    if (!entityTransaction.isActive()) {

                        entityTransaction.begin();

                    }

                    entityManager.merge(salesref);
                    entityManager.flush();
                    entityTransaction.commit();
                
                    return Constants.SALES_REF_UPDATED_SUCCESSFULLY;

                } else {

                    return Constants.NOT_EXIST;

                }

            } else {

                Log4jUtil.logErrorMessage("public String updateSalesRef(Salesref salesref)  entity manager object null");
                return Constants.ERROR;

            }

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public String updateSalesRef(Salesref salesref) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }
    }

    public String deleteSalesRef(Salesref salesref) {

        EntityTransaction entityTransaction = null;

        try {

            if (entityManager != null) {

                Salesref salesRefTmp = entityManager.find(Salesref.class, salesref.getSalesrefid());

                if (salesRefTmp != null) {

                    entityTransaction = entityManager.getTransaction();

                    if (!entityTransaction.isActive()) {

                        entityTransaction.begin();

                    }

                    entityManager.remove(salesref);
                    entityManager.flush();
                    entityTransaction.commit();
                 
                    return Constants.SALES_REF_DELETED_SUCCESSFULLY;

                } else {

                    return Constants.NOT_EXIST;

                }

            } else {

                Log4jUtil.logErrorMessage(" public String deleteSalesRef(Salesref salesref)  entity manager object null");
                return Constants.ERROR;

            }

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public String deleteSalesRef(Salesref salesref) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }
    }

    public String addSalesPoints(InvoiceSales invoiceSales) {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
