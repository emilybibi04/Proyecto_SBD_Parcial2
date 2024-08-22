/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_croud_mysql_proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author LUIS
 */
public class CConexion {
    
    Connection conectar = null;
    String usuario = "root";
    String contrasena = "root"; //1234
    String bd = "ProyectoBases";
    String servidor = "localhost";
    String puerto = "3306";
    String cadena = "jdbc:mysql://"+servidor+":"+puerto+"/"+bd;
    
    public Connection estableceConexion(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contrasena);
            JOptionPane.showMessageDialog(null,"La conexion se ha realizado con exito");
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al conectarse a la base de datos, "+e.toString());
        }
        return conectar;
    }
    
} 
