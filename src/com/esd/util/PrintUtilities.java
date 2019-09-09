/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.util;

import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import java.awt.print.*;
import javax.print.PrintService;

public class PrintUtilities implements Printable {

    private Component componentToBePrinted;

    public static void printComponent(Component c) {
        new PrintUtilities(c).print();
    }

    public PrintUtilities(Component componentToBePrinted) {
        this.componentToBePrinted = componentToBePrinted;
    }

    public void print() {
        PrinterJob printJob = PrinterJob.getPrinterJob();

          Paper paper = new Paper();

            paper.setSize(0.0422, 0.083);

            PageFormat pf =  new PageFormat();

            pf.setPaper(paper);

            System.out.println("fffffff  "+ pf.getHeight() + "   "+ pf.getWidth() + pf.getPaper().getHeight() + " "+ pf.getPaper().getWidth());
            printJob.setPrintable(this, pf);


        PrintService printServices[] = printJob.lookupPrintServices();


        for (int i = 0; i < printServices.length; i++) {

            PrintService serviceName = printServices[i];


            String name = serviceName.getName();

            if (name.equals("BIXOLON SAMSUNG SRP-275")) {
                try {
                    printJob.setPrintService(printServices[i]);
                } catch (PrinterException ex) {
                    Logger.getLogger(PrintUtilities.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    printJob.setPrintable(this,pf);
    
    if (printJob.printDialog())
      try {
     

        printJob.print();
      } catch(PrinterException pe) {
        System.out.println("Error printing: " + pe);
      }
    }

    public int print(Graphics g, PageFormat pageFormat, int pageIndex) {
        if (pageIndex > 0) {
            return (NO_SUCH_PAGE);
        } else {
            Graphics2D g2d = (Graphics2D) g;
            g2d.translate(pageFormat.getImageableX(), pageFormat.getImageableY());
            disableDoubleBuffering(componentToBePrinted);
            componentToBePrinted.paint(g2d);
            enableDoubleBuffering(componentToBePrinted);
            return (PAGE_EXISTS);
        }
    }

    public static void disableDoubleBuffering(Component c) {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(false);
        
    }

    public static void enableDoubleBuffering(Component c) {
        RepaintManager currentManager = RepaintManager.currentManager(c);
        currentManager.setDoubleBufferingEnabled(true);
    }
}
