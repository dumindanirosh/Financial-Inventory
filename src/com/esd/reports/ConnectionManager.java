package com.esd.reports;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author Administrator
 */
public class ConnectionManager {

    private static String className = "com.mysql.jdbc.Driver";
    private static String url = "jdbc:mysql://localhost:3306/fimsdb";
    private static String userName = "root";
    private static String password = "";
    private static Connection conn;

    public static Connection getConnection() {

        // Set up jdbc connection
        try {

            Class.forName(className).newInstance();
            conn = DriverManager.getConnection(url, userName, password);

        } catch (SQLException ex) {
         
        } catch (InstantiationException ex) {
          
        } catch (IllegalAccessException ex) {
          
        } catch (ClassNotFoundException ex) {
          
        }

        return conn;
    }
}
