/*
 * UserActivityImpl.java
 *
 * Created on December 4, 2008, 9:12 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */
package com.esd.control;

import com.esd.pojo.UserActivity;
import com.esd.util.Constants;
import com.esd.util.Log4jUtil;
import java.awt.print.PrinterJob;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.SimpleDoc;
import javax.print.attribute.DocAttributeSet;
import javax.print.attribute.HashDocAttributeSet;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;

/**
 *
 * @author Duminda
 */
public class UserActivityImpl {

    private EntityManagerFactory entityManagerFactory;
    private EntityManager entityManager;

    public UserActivityImpl() {

        entityManagerFactory = Persistence.createEntityManagerFactory("Financial_InventoryPU");

        entityManager = entityManagerFactory.createEntityManager();
    }

    public String addActivityDetails(UserActivity userActivity) {

        EntityTransaction entityTransaction = null;
        try {
            entityTransaction = entityManager.getTransaction();

            entityTransaction.begin();

            entityManager.persist(userActivity);
            entityTransaction.commit();
            entityManager.flush();

            return Constants.SUCCESS;

        } catch (Exception ex) {

            Log4jUtil.logErrorMessage("public String addActivityDetails(UserActivity userActivity) " + ex);

            if (entityTransaction != null) {

                entityTransaction.rollback();

            }

            return Constants.ERROR;
        }


    }

//    public static void main(String[] args) {
//
//        UserActivityImpl u = new UserActivityImpl();
//
//        u.openCashDrawer();
//    }
//
//    public void openCashDrawer() {
//        Runnable openCashDrawer = new Runnable() {
//
//            public synchronized void run() {
//                try {
//                    PrintService[] printService1 = PrinterJob.lookupPrintServices();
//
//
//                    PrintService printService = printService1[0];
//                    DocPrintJob job = printService.createPrintJob();
//                    DocAttributeSet das = new HashDocAttributeSet();
//                    PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
//                    SimpleDoc doc = new SimpleDoc(new byte[]{0x1B, 0x70, 0x00, 0x32, -0x06}, DocFlavor.BYTE_ARRAY.AUTOSENSE, das);
//                    job.print(doc, pras);
//                } catch (PrintException ex) {
//                    // logger.error(ex.getMessage(), ex);
//                }
//            }
//        };
//        (new Thread(openCashDrawer)).start();
//    }
}
