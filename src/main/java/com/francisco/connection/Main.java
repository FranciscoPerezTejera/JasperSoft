package com.francisco.connection;

import interfaces.PantallaInicio;
import java.nio.file.Path;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) {
        
        String urlH2 = "jdbc:h2:"+Path.of("juegos").toAbsolutePath().toString();
        String username = "root";
        String password = "1234";
        
        System.out.println("Iniciando conexion con el servidor");
        Connection newConnection = H2Connection.newInstance(urlH2, username, password);
        
        PantallaInicio nuevaPantallaInicio = new PantallaInicio(newConnection);
        
    }
}
