/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.control;

import com.esd.pojo.ItemType;
import com.esd.pojo.Items;
import com.esd.pojo.Stores;
import java.util.List;
import java.util.Vector;

/**
 *
 * @author Duminda
 */
public interface ItemManager {

    public String addItem(Items items, Stores stores);

    public String editItem(Items items);

    public List<Items> getItems();

    public int getNoOfItems();

    public String addItemType(ItemType itemType);

    public List<ItemType> getItemType();

    public String editItemType(ItemType itemType, String oldItemTypeName);

    public Vector<Items> geKeyTypedItems(String keyTyped);
}
