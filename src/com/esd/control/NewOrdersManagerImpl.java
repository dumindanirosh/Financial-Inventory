/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.control;

import com.esd.pojo.NewOrders;
import com.esd.util.Constants;
import com.esd.util.Log4jUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

/**
 *
 * @author Duminda
 */
public class NewOrdersManagerImpl implements NewOrdersManager {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private UserActivityImpl userActivityImpl;

    public NewOrdersManagerImpl() {

        entityManagerFactory = Persistence.createEntityManagerFactory("Financial_InventoryPU");


        entityManager = entityManagerFactory.createEntityManager();

    }

    public String newOrders(NewOrders newOrders) {

        EntityTransaction entityTransaction = null;

        try {

            if (entityManager != null) {


                entityTransaction = entityManager.getTransaction();

                if (!entityTransaction.isActive()) {

                    entityTransaction.begin();

                }


                entityManager.persist(newOrders);

                entityManager.flush();
                entityTransaction.commit();

                return Constants.OREDER_ADDED_SUCCESSFULLY;

            } else {

                return Constants.ERROR;

            }

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public String newOrders(NewOrders newOrders) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }



    }
}
