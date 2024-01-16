package com.francisco.connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class H2Connection {
    
    private H2Connection() {}
    public static Connection newInstance(String url, String username, String password) {
        
        Connection newConnection = null;
        
        try {
            newConnection = DriverManager.getConnection(url,username,password);
        } catch (SQLException ex) {
            System.err.println("No se ha podido establecer conexion con el servidor");
            System.err.println(ex.getMessage());
            System.exit(-1);
        }
        
        return newConnection;
    }
}
