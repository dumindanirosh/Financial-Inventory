/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.control;

import com.esd.pojo.ItemType;
import com.esd.pojo.Items;
import com.esd.pojo.Stores;
import com.esd.util.Constants;
import com.esd.util.Log4jUtil;
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
public class ItemManagerImpl implements ItemManager {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;
    private UserActivityImpl userActivityImpl;

    public ItemManagerImpl() {

        entityManagerFactory = Persistence.createEntityManagerFactory("Financial_InventoryPU");


        entityManager = entityManagerFactory.createEntityManager();
    }

    public String addItem(Items items, Stores stores) {

        EntityTransaction entityTransaction = null;

        try {
//            if (getNoOfItems() > 20) {
//                 return "TRIAL_VERSION";
//            }
            if (entityManager != null) {

                Items itemsTmp1 = entityManager.find(Items.class, items.getItemCode());

                if (itemsTmp1 == null) {

                    Stores storesTmp = entityManager.find(Stores.class, items.getItemCode());

                    if (storesTmp == null) {

                        entityTransaction = entityManager.getTransaction();

                        if (!entityTransaction.isActive()) {

                            entityTransaction.begin();

                        }



                        items.setStores(stores);

                        entityManager.persist(items);

                        entityManager.flush();
                        entityTransaction.commit();

                        return Constants.ITEM_ADDED_SUCCESSFULLY;

                    } else {
                        // Cant be happen
                        Log4jUtil.logErrorMessage("public String addItem(Items items) storesTmp object null ");
                        return Constants.STORES_ITEM_CODE_EXIST;
                    }

                } else {
                    return Constants.ITEM_CODE_EXIST;
                }

            } else {
                Log4jUtil.logErrorMessage("public String addItem(Items items) entity manager object null ");
                return Constants.ERROR;
            }



        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public String addItem(Items items) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }

    }

    public List<Items> getItems() {
        try {

            if (entityManager != null) {
                Query q = entityManager.createQuery("SELECT i FROM Items i");

                return q.getResultList();
            } else {
                return null;
            }
        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public List<Items> getItems() " + ex);

            return null;
        }
    }

    public String editItem(Items items) {


        EntityTransaction entityTransaction = null;

        try {

            if (entityManager != null) {

                Items itemsTmp1 = entityManager.find(Items.class, items.getItemCode());

                if (itemsTmp1 != null) {

//                    Items itemsTmp2 = entityManager.find(Items.class, items.getItemName());
//
//                    if (itemsTmp2 == null) {

                    entityTransaction = entityManager.getTransaction();

                    if (!entityTransaction.isActive()) {

                        entityTransaction.begin();

                    }

                    entityManager.merge(items);
                    entityManager.flush();
                    entityTransaction.commit();

                    return Constants.ITEM_UPDATED_SUCCESSFULLY;

//                    } else {
//
//                        return Constants.ITEM_NAME_EXIST;
//                    }

                } else {
                    return Constants.ITEM_NOT_EXIST;
                }

            } else {
                return Constants.ERROR;
            }


        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public String addItem(Items items) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }

    }

    public int getNoOfItems() {

        try {

            if (entityManager != null) {
                Query q = entityManager.createQuery("SELECT i FROM Items i");

                return q.getResultList().size();
            } else {
                return 15;
            }
        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public List<Items> getItems() " + ex);

            return 15;
        }

    }

    public String addItemType(ItemType itemType) {

          EntityTransaction entityTransaction = null;

        try {
            if (entityManager != null) {

                   ItemType itemsTypeTmp1 = null;
                try{
                       Query q = entityManager.createQuery("SELECT i FROM ItemType i WHERE i.itemtypename = :itemtypename");
                       q.setParameter("itemtypename", itemType.getItemtypename());

                        itemsTypeTmp1  = (ItemType) q.getSingleResult();
                }catch(NoResultException ex){
                        itemsTypeTmp1 = null;
                }
                

                if (itemsTypeTmp1 == null) {

                        entityTransaction = entityManager.getTransaction();

                        if (!entityTransaction.isActive()) {

                            entityTransaction.begin();

                        }
                        entityManager.persist(itemType);

                        entityManager.flush();
                        entityTransaction.commit();

                        return Constants.ITEM_TYPE_ADDED_SUCCESSFULLY;

                } else {
                    return Constants.ITEM_TYPE_EXIST;
                }

            } else {
                Log4jUtil.logErrorMessage("public String addItemType(ItemType itemtype) entity manager object null ");
                return Constants.ITEM_TYPE_ADDED_ERROR;
            }



        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public String addItemType(ItemType itemtype) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ITEM_TYPE_ADDED_ERROR;
        }

    }

    public List<ItemType> getItemType() {
        try {

            if (entityManager != null) {
                Query q = entityManager.createQuery("SELECT i FROM ItemType i");

                return q.getResultList();
            } else {
                return null;
            }
        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public List<ItemType> getItemType() " + ex);

            return null;
        }
    }

    public String editItemType(ItemType itemType, String oldItemTypeName) {

        EntityTransaction entityTransaction = null;

        try {

            if (entityManager != null) {
                ItemType itemTypeTmp1 = null;
                try{
                       Query q = entityManager.createQuery("SELECT i FROM ItemType i WHERE i.itemtypename = :itemtypename");
                       q.setParameter("itemtypename", oldItemTypeName);

                        itemTypeTmp1  = (ItemType) q.getSingleResult();
                }catch(NoResultException ex){
                        itemTypeTmp1 = null;
                }
                if (itemTypeTmp1 != null) {

//                    Items itemsTmp2 = entityManager.find(Items.class, items.getItemName());
//
//                    if (itemsTmp2 == null) {

                    entityTransaction = entityManager.getTransaction();

                    if (!entityTransaction.isActive()) {

                        entityTransaction.begin();

                    }
                    itemTypeTmp1.setItemtypename(itemType.getItemtypename());

                    entityManager.merge(itemTypeTmp1);
                    entityManager.flush();
                    entityTransaction.commit();

                    return Constants.ITEM_TYPE_UPDATED_SUCCESSFULLY;

//                    } else {
//
//                        return Constants.ITEM_NAME_EXIST;
//                    }

                } else {
                    return Constants.ITEM_TYPE_NOT_EXIST;
                }

            } else {
                return Constants.ERROR;
            }


        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public String editItemType(ItemType itemtype) " + ex);
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            return Constants.ERROR;
        }
    }
    
    public Vector<Items> geKeyTypedItems(String keyTyped) {

           try {
           String  pattern = "'" + keyTyped+ "%" + "'";

           if (entityManager != null) {
                Query q = entityManager.createQuery("SELECT i FROM Items i WHERE i.itemName LIKE "+pattern);

                Vector<Items> itemsList = (Vector<Items>) q.getResultList();
                
                return itemsList;
            } else {
                return null;
            }
        } catch (Exception ex) {
               

            Log4jUtil.logErrorMessage("public ArrayList<Items> geKeyTypedItems(String keyTyped) " + ex);

            return null;
        }
    }
//    public static void main(String[] args) {
//
//        ItemManagerImpl itemManagerImpl = new ItemManagerImpl();
//
//        itemManagerImpl.testingData();
//    }
//
//    public String testingData() {
//
//
//        EntityTransaction entityTransaction = null;
//
//        try {
//
//            if (entityManager != null) {
//
//
////                    Items itemsTmp2 = entityManager.find(Items.class, items.getItemName());
////
////                    if (itemsTmp2 == null) {
//
//                entityTransaction = entityManager.getTransaction();
//
//                if (!entityTransaction.isActive()) {
//
//                    entityTransaction.begin();
//
//                }
//
//
//                for (int i = 25000; i < 26000; i++) {
//                    System.out.println("iiiiiii "+ i);
//                    Integer va = new Integer(i);
//
//                    Items items = new Items();
//
//                    items.setItemCode(i);
//                    items.setEnteredBy("D " + va.intValue());
//                    items.setEnteredDate(new Date());
//                    items.setItemCost(va.doubleValue());
//                    items.setItemName(va.toString());
//                    items.setItemType(va.toString());
//                    items.setSalesPrice(va.doubleValue());
//
//                    entityManager.persist(items);
//                    entityManager.flush();
//                }
//
//
//                entityTransaction.commit();
//
//                return Constants.ITEM_UPDATED_SUCCESSFULLY;
//
////                    } else {
////
////                        return Constants.ITEM_NAME_EXIST;
////                    }
//
//            } else {
//                return Constants.ERROR;
//            }
//
//
//        } catch (Exception ex) {
//            System.out.println("dasdsadsads "+ ex);
//            Log4jUtil.logErrorMessage("public String addItem(Items items) " + ex);
//            if (entityTransaction != null) {
//                entityTransaction.rollback();
//            }
//            return Constants.ERROR;
//        }
//
//
//
//    }
}
