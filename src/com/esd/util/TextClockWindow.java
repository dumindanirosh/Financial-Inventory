/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.esd.util;

import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JFrame;
import javax.swing.JTextField;

/**
 *
 * @author Duminda
 */
public class TextClockWindow extends JFrame {

    //=================================================== instance variables
    private JTextField timeField;  // set by timer listener

    //========================================================== constructor
    public TextClockWindow() {
        // Build the GUI - only one panel
        timeField = new JTextField(6);
        timeField.setFont(new Font("sansserif", Font.PLAIN, 16));

        Container content = this.getContentPane();
        content.setLayout(new FlowLayout());
        content.add(timeField);

        this.setTitle("Text Clock");
        this.pack();

        // Create a 1-second timer and action listener for it.
        // Specify package because there are two Timer classes
        javax.swing.Timer t = new javax.swing.Timer(1000,
                new ActionListener() {

                    public void actionPerformed(ActionEvent e) {
                        Calendar now = Calendar.getInstance();
                        int date = now.get(Calendar.DATE);
                        int month = now.get(Calendar.MONTH);
                        int year = now.get(Calendar.YEAR);
                        int h = now.get(Calendar.HOUR_OF_DAY);
                        int m = now.get(Calendar.MINUTE);
                        int s = now.get(Calendar.SECOND);
                        timeField.setText("" + h + ":" + m + ":" + s + " - " + date + "/" + month + "/" + year);
                    }
                });
        t.start();  // Start the timer
    }//end constructor
}
