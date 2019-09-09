/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esd.control;

import com.esd.pojo.Stores;
import java.util.List;
import com.esd.pojo.StoresMgt;

/**
 *
 * @author Duminda
 */
public interface StoresManager {

    public String addItemsToStores(Stores stores);
    public List<Stores> getStores();
    public String addStoresMgt(StoresMgt storesMgt, String avilableQuantity);
    public StoresMgt getStroesDetailsForDate(String itemCode, String purchaseDate);
    public Stores getStroesDetailsForItemCode(String itemCode);

    public String addReturnItemsToStores(String itemCode, int noOfItems);
}
