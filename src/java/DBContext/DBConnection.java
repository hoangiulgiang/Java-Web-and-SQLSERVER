/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package DBContext;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author duong minh hoang
 */
public class DBConnection {
private static final String urlConnect = "jdbc:sqlserver://LAPTOP-53TELDLT\\SQLEXPRESS:1433;databasename=MealPRJ;user=hoang;password=123;characterEncoding=UTF-8;encrypt=true;trustServerCertificate=true;";
    public static Connection connect() {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            // tao doi tuong connection 
            Connection conn = DriverManager.getConnection(urlConnect);
            return conn;
        } catch(ClassNotFoundException | SQLException ex) {
            System.out.println(ex);
        } 
        return null;
    }
    
    public static void main(String args[]) {
        System.out.println(connect());
    }
}
