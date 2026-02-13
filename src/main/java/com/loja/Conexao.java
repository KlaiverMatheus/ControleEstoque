package com.loja;

import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {
    public static Connection conectar() {
        try {

            String url = "jdbc:mysql://localhost:3306/lojinha";
            String user = "root";
            String pass = "root"; 
            
            return DriverManager.getConnection(url, user, pass);
            
        } catch (Exception e) {
            System.out.println("Deu ruim na conexao: " + e.getMessage());
            return null;
        }
    }
}