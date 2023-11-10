/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConexionSQL;
import java.sql.*;
/**
 *
 * @author samue
 */
public class ConexionSQL {
    
    private Connection con;
    
    public Connection abrirConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/biblioteca", "root", "");
            System.out.println("Conexion exitosa");
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error en la conexion: "+e.getMessage());
        }
        return con;
    }
       
    public Connection cerrarConexion(){
        con = null;
        System.out.println("Conexion cerrada exitosamente");
        return con;
    }  
    
    
}
