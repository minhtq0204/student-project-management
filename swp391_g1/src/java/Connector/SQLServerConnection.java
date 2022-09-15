/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Connector;

import java.sql.Connection;
import java.sql.DriverManager;
/**
 *
 * @author Thanh
 */
public class SQLServerConnection {

    public Connection getConnection() throws Exception {
        Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
        String url = "jdbc:mysql://" + serverName + ":" + portNumber + "/" + dbName+"?allowPublicKeyRetrieval=true&useSSL=false";
        return DriverManager.getConnection(url, userID, password);
    }



    private final String serverName = "127.0.0.1";
    private final String dbName = "swp319_1";
    private final String portNumber = "33066";
    private final String userID = "root";
    private final String password = "123456";


    public static void main(String[] args) throws Exception {
        
        SQLServerConnection x = new SQLServerConnection();
        System.out.println(x.getConnection());;
    }
}
