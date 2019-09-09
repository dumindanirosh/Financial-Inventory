/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esd.control;

import com.esd.pojo.Supplier;
import com.esd.util.Constants;
import com.esd.util.Log4jUtil;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;
import java.util.List;
/**
 *
 * @author Duminda
 */
public class SupplierManagerImpl implements SupplierManager {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;



    public SupplierManagerImpl() {

        entityManagerFactory = Persistence.createEntityManagerFactory("Financial_InventoryPU");


        entityManager = entityManagerFactory.createEntityManager();

    }


    public String addSupplier(Supplier supplier) {

            EntityTransaction entityTransaction = null;

        try {
//            if (getNoOfItems() > 20) {
//                 return "TRIAL_VERSION";
//            }
            if (entityManager != null) {

                Supplier supplierTmp = entityManager.find(Supplier.class, supplier.getSuppliercode());

                if (supplierTmp == null) {

                  
                        entityTransaction = entityManager.getTransaction();

                        if (!entityTransaction.isActive()) {

                            entityTransaction.begin();

                        }

                        entityManager.persist(supplier);

                        entityManager.flush();
                        entityTransaction.commit();

                        return Constants.SUPPLIER_ADDED_SUCCESSFULLY;


                } else {
                    return Constants.SUPPLIER_ALREADY_EXIST;
                }

            } else {
                Log4jUtil.logErrorMessage(" public String addSupplier(Supplier supplier) entity manager object null ");
                return Constants.ERROR;
            }



        } catch (Exception ex) {

            Log4jUtil.logErrorMessage(" public String addSupplier(Supplier supplier) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }
    }


    public List<Supplier> getSupplier() {
        try {

            if (entityManager != null) {
                Query q = entityManager.createQuery("SELECT s FROM Supplier s");

                return q.getResultList();
            } else {
                return  null;
            }
        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public List<Items> getItems() " + ex);

            return null;
        }
    }

    public String editSupplier(Supplier supplier) {


        EntityTransaction entityTransaction = null;

        try {

            if (entityManager != null) {
               Supplier supplierTmp = entityManager.find(Supplier.class, supplier.getSuppliercode());
                if (supplierTmp != null) {

//                    Items itemsTmp2 = entityManager.find(Items.class, items.getItemName());
//
//                    if (itemsTmp2 == null) {

                    entityTransaction = entityManager.getTransaction();

                    if (!entityTransaction.isActive()) {

                        entityTransaction.begin();

                    }

                    entityManager.merge(supplier);
                    entityManager.flush();
                    entityTransaction.commit();

                    return Constants.SUPPLIER_UPDATED_SUCCESSFULLY;

//                    } else {
//
//                        return Constants.ITEM_NAME_EXIST;
//                    }

                } else {
                    return Constants.SUPPLIER_NOT_EXIST;
                }

            } else {
                return Constants.ERROR;
            }


        } catch (Exception ex) {

            Log4jUtil.logErrorMessage(" public String editSupplier(Supplier supplier) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }
    }

    public String deleteSupplier(Supplier supplier) {

        EntityTransaction entityTransaction = null;

        try {

            if (entityManager != null) {
               Supplier supplierTmp = entityManager.find(Supplier.class, supplier.getSuppliercode());
                if (supplierTmp != null) {

//                    Items itemsTmp2 = entityManager.find(Items.class, items.getItemName());
//
//                    if (itemsTmp2 == null) {

                    entityTransaction = entityManager.getTransaction();

                    if (!entityTransaction.isActive()) {

                        entityTransaction.begin();

                    }

                    entityManager.remove(supplier);
                    entityManager.flush();
                    entityTransaction.commit();

                    return Constants.SUPPLIER_DELETED_SUCCESSFULLY;

//                    } else {
//
//                        return Constants.ITEM_NAME_EXIST;
//                    }

                } else {
                    return Constants.SUPPLIER_NOT_EXIST;
                }

            } else {
                return Constants.ERROR;
            }


        } catch (Exception ex) {

            Log4jUtil.logErrorMessage(" public String deleteSupplier(Supplier supplier) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }
    }


}
