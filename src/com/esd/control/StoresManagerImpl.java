/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.control;

import com.esd.pojo.StoresMgt;
import com.esd.pojo.Stores;
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
public class StoresManagerImpl implements StoresManager {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private UserActivityImpl userActivityImpl;

    public StoresManagerImpl() {

        entityManagerFactory = Persistence.createEntityManagerFactory("Financial_InventoryPU");


        entityManager = entityManagerFactory.createEntityManager();
    }

    public String addItemsToStores(Stores stores) {

        EntityTransaction entityTransaction = null;

        try {

            if (entityManager != null) {

                Stores storesTmp = entityManager.find(Stores.class, stores.getItems().getItemCode());

                if (storesTmp != null) {

                    entityTransaction = entityManager.getTransaction();

                    if (!entityTransaction.isActive()) {

                        entityTransaction.begin();
                    }

                    storesTmp.setQuantity(stores.getQuantity());
                    storesTmp.setReOrder(stores.getReOrder());

                    entityManager.merge(storesTmp);
                    entityManager.flush();
                    entityTransaction.commit();

                    return Constants.ITEM_UPDATED_SUCCESSFULLY;

                } else {
                    Log4jUtil.logErrorMessage("public String addItemsToStores(Stores stores) Stores object null");
                    return Constants.ERROR;
                }



            } else {
                Log4jUtil.logErrorMessage("public String addItemsToStores(Stores stores) entity manager object null");
                return Constants.ERROR;
            }



        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public String addItemsToStores(Stores stores) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }
    }

    public List<Stores> getStores() {


        try {

            if (entityManager != null) {
                Query q = entityManager.createQuery("SELECT s FROM Stores s");

                return q.getResultList();
            } else {
                Log4jUtil.logErrorMessage("public List<Stores> getStores() entity manager object null ");
                return null;
            }
        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public List<Stores> getStores() " + ex);

            return null;
        }
    }

    public String addStoresMgt(StoresMgt storesMgt, String avilableQuantity) {


        EntityTransaction entityTransaction = null;

        try {

            if (entityManager != null) {

                StoresMgt storesMgtTmp = entityManager.find(StoresMgt.class, storesMgt.getStoresMgtPK());

                if (storesMgtTmp == null) {
                    entityTransaction = entityManager.getTransaction();

                    if (!entityTransaction.isActive()) {

                        entityTransaction.begin();
                    }


                    Stores stores = entityManager.find(Stores.class, new Integer(storesMgt.getStoresMgtPK().getItemCode()));

                    stores.setQuantity(new Integer(avilableQuantity));

                    entityManager.merge(stores);
                    entityManager.flush();

                    entityManager.persist(storesMgt);
                    entityManager.flush();
                    entityTransaction.commit();
                    return Constants.STORES_MGT_ADDED;


                } else {

                    entityTransaction = entityManager.getTransaction();

                    if (!entityTransaction.isActive()) {

                        entityTransaction.begin();
                    }

                    storesMgtTmp.setAccptedQuantity(storesMgt.getAccptedQuantity());
                    storesMgtTmp.setPurchaseQuantity(storesMgt.getPurchaseQuantity());
                    storesMgtTmp.setRejectedQuantity(storesMgt.getRejectedQuantity());
                    storesMgtTmp.setSellQuantity(storesMgt.getSellQuantity());
                    storesMgtTmp.setAvilableQuantity(storesMgt.getAvilableQuantity());

                    Stores stores = entityManager.find(Stores.class, new Integer(storesMgt.getStoresMgtPK().getItemCode()));

                    stores.setQuantity(new Integer(avilableQuantity));

                    entityManager.merge(stores);
                    entityManager.flush();


                    entityManager.merge(storesMgtTmp);
                    entityManager.flush();
                    entityTransaction.commit();

                    return Constants.STORES_MGT_UPDATED;
                }

            } else {
                Log4jUtil.logErrorMessage("public String addStoresMgt(StoresMgt storesMgt) entity manager object null");
                return Constants.ERROR;
            }



        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public String addStoresMgt(StoresMgt storesMgt) " + ex);

            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }
    }

    public StoresMgt getStroesDetailsForDate(String itemCode, String purchaseDate) {

        try {

            if (entityManager != null) {


                Query q = entityManager.createQuery("SELECT s FROM StoresMgt s WHERE s.storesMgtPK.itemCode = :itemCode AND " +
                        "s.storesMgtPK.purchaseDate = :purchaseDate");
                q.setParameter("itemCode", itemCode);
                q.setParameter("purchaseDate", purchaseDate);

                StoresMgt storesMgt = (StoresMgt) q.getSingleResult();

                Query q2 = entityManager.createQuery("SELECT s FROM Stores s WHERE s.itemCode = :itemCode");
                q2.setParameter("itemCode", new Integer(itemCode));

                Stores stores = (Stores) q2.getSingleResult();

                storesMgt.setAvilableQuantity(stores.getQuantity().toString());

                return storesMgt;

            } else {
                return null;
            }
        } catch (Exception ex) {

            System.out.println("dasddkjadashdaskldadkhl " + ex);

            Log4jUtil.logErrorMessage("public StoresMgt getStroesDetailsForDate(String itemCode, String purchaseDate)" + ex);

            return null;
        }

    }

    public Stores getStroesDetailsForItemCode(String itemCode) {

        try {

            if (entityManager != null) {


                entityManager = entityManagerFactory.createEntityManager();

                Query q = entityManager.createQuery("SELECT s FROM Stores s WHERE s.itemCode = :itemCode");

                q.setParameter("itemCode", new Integer(itemCode));

                Stores stores = (Stores) q.getSingleResult();


                return stores;

            } else {
                return null;
            }
        } catch (Exception ex) {


            Log4jUtil.logErrorMessage("public StoresMgt getStroesDetailsForItemCode(String itemCode)" + ex);

            return null;
        }

    }

    public String addReturnItemsToStores(String itemCode, int noOfItems) {
        EntityTransaction entityTransaction = null;

        try {

            if (entityManager != null) {


                entityTransaction = entityManager.getTransaction();

                if (!entityTransaction.isActive()) {

                    entityTransaction.begin();

               }

                    Stores storesTmp = entityManager.find(Stores.class, new Integer(itemCode));

                    if(storesTmp != null){

                        int avilableQty = storesTmp.getQuantity();
                        int newQty = avilableQty + noOfItems;
                        storesTmp.setQuantity(newQty);
                        entityManager.merge(storesTmp);
                     entityManager.flush();
                    entityTransaction.commit();
                    }
                return Constants.STORES_UPDATED_SUCCESSFULLY;

            } else {
                Log4jUtil.logErrorMessage("public String addReturnItemsToStores(String itemCode, int noOfItems) entity manager object null ");
                return Constants.ERROR;
            }

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public String addReturnItemsToStores(String itemCode, int noOfItems) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }
    }
}
