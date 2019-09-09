/*
 * Log4jUtil.java
 *
 * Created on January 12, 2009, 4:58 PM
 *
 * To change this template, choose Tools | Template Manager
 * and open the template in the editor.
 */

package com.esd.util;

import org.apache.log4j.Logger;

/**
 *
 * @author Duminda
 */
public class Log4jUtil {
    
    private static org.apache.log4j.Logger log = Logger.getLogger("errors.log");

  

    public Log4jUtil() {
    }
  
     public static void logErrorMessage(Object message){
      
      log.error(message);
      
      
  }

    
}
