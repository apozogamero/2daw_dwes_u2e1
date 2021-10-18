package com.mycompany.u0.e1.nominas.laboral;
import java.sql.*;

public class ConexionBD {
    Connection conn = null;
    public String url = "jdbc:oracle:thin:@localhost:1521/XE";
    public String user = "ALEJANDRO";
    public String pass = "ALEJANDRO1";
    
    public Connection getConnection() {
        try {
            conn = DriverManager.getConnection(url,user,pass);
            System.out.println("Conexión válida: " + conn.isValid(10));
            System.out.println("Estado del autocommit: " + conn.getAutoCommit());
        } catch (SQLException e) {
            System.out.println("Ocurrió una excepción");
        }
        
        return conn;
    }
    
    public void close() {
        try {
            if (conn == null)
                conn.close();
        } catch (SQLException e) {
            System.out.println("Ocurrió una excepción");
        }
    }
}
