/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package com.esd.control;

import com.esd.pojo.Supplier;
import java.util.List;

/**
 *
 * @author Duminda
 */
public interface SupplierManager {

        public String addSupplier(Supplier supplier);
        public List<Supplier> getSupplier();
        public String editSupplier(Supplier supplier);






        public String deleteSupplier(Supplier supplier);

}


