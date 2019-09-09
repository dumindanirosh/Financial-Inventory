/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.control;

import com.esd.pojo.CustomerInvoice;
import com.esd.util.Constants;
import com.esd.util.Log4jUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import com.esd.pojo.ReturnExchange;
import com.esd.pojo.Stores;

/**
 *
 * @author Duminda
 */
public class ReturnExchangeImpl implements ReturnExchangeManager {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public ReturnExchangeImpl() {
        entityManagerFactory = Persistence.createEntityManagerFactory("Financial_InventoryPU");

        entityManager = entityManagerFactory.createEntityManager();

    }

    public String addReturnExchange(ReturnExchange returnExchange) {

        EntityTransaction entityTransaction = null;

        try {

            if (entityManager != null) {

                CustomerInvoice customerInvoiceTmp = entityManager.find(CustomerInvoice.class, returnExchange.getReturnExchangePK().getInvoiceId());


                if (customerInvoiceTmp != null) {

                    ReturnExchange returnExchangeTmp = entityManager.find(ReturnExchange.class, returnExchange.getReturnExchangePK());

                    if (returnExchangeTmp == null) {


                        entityTransaction = entityManager.getTransaction();

                        if (!entityTransaction.isActive()) {

                            entityTransaction.begin();
                        }

                        Stores stores = entityManager.find(Stores.class, returnExchange.getReturnExchangePK().getItemCode());

                        int oldItemQuantity = stores.getQuantity();
                        int newItemQuantity = oldItemQuantity + returnExchange.getNumberOfItems();

                        if (newItemQuantity > 0) {

                            stores.setQuantity(newItemQuantity);

                        } else {

                            stores.setQuantity(0);

                        }

                        entityManager.merge(stores);


                        Stores newStores = entityManager.find(Stores.class, returnExchange.getReturnItemCode());

                        int returnOldItemQuantity = newStores.getQuantity();
                        int returnNewItemQuantity = returnOldItemQuantity - returnExchange.getNumberOfItems();

                        if (returnNewItemQuantity > 0) {

                            newStores.setQuantity(returnNewItemQuantity);

                        } else {

                            newStores.setQuantity(0);
                        }


                        entityManager.merge(newStores);



                        entityManager.persist(returnExchange);
                        entityManager.flush();
                        entityTransaction.commit();

                        return Constants.RETURN_EXCHANGE_ADDED_SUCCESSFULLY;

                    } else {
                        Log4jUtil.logErrorMessage("public String addReturnExchange(ReturnExchange returnExchange) return exchange object null");
                        return Constants.EXIST;
                    }

                } else {
                    Log4jUtil.logErrorMessage("public String addReturnExchange(ReturnExchange returnExchange) return wrong customer");
                    return Constants.INVALID_INVOICE;
                }

            } else {
                Log4jUtil.logErrorMessage("public String addReturnExchange(ReturnExchange returnExchange) entity manager object null");
                return Constants.ERROR;
            }

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public String addReturnExchange(ReturnExchange returnExchange) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }
    }
}
