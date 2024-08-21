/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_croud_mysql_proyecto;

import java.awt.HeadlessException;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

/**
 *
 * @author LUIS
 */
public class CPersona {
    
    String cedula;
    String nombre;
    String apellido;
    String nom_usuario;
    String bio_perfil;
    String email;
    String telefono;
    String direccion;
    String nacimiento;
    String contrasena;
    String foto_perfil;
    
    

    public void InsertarPersona(JTextField paramCedula, JTextField paramNombre, JTextField paramApellido, JTextField paramNom_usuario,JTextField paramBio_perfil,JTextField paramEmail, JTextField paramTelefono, JTextField paramDireccion, JTextField paramNacimiento, JTextField paramContrasena, JTextField paramFoto_perfil){
        setCedula(paramCedula.getText());
        setNombre(paramNombre.getText());
        setApellido(paramApellido.getText());
        setNom_usuario(paramNom_usuario.getText());
        setBio_perfil(paramBio_perfil.getText());
        setEmail(paramEmail.getText());
        setTelefono(paramTelefono.getText());
        setDireccion(paramDireccion.getText());
        setNacimiento(paramNacimiento.getText());
        setContrasena(paramCedula.getText());
        setFoto_perfil(paramCedula.getText());
        
        CConexion objConexion1 = new CConexion();
        
        String consulta ="""
                         INSERT INTO Persona (cedula, nombre, apellido, nom_usuario, bio_perfil, email, telefono, direccion, fecha_nacimiento, contrasena, foto_perfil) VALUES
                         (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)""";
        
        try {
            CallableStatement cs = objConexion1.estableceConexion().prepareCall(consulta);
            cs.setString(1, getCedula());
            cs.setString(2, getNombre());
            cs.setString(3, getApellido());
            cs.setString(4, getNom_usuario());
            cs.setString(5, getBio_perfil());
            cs.setString(6, getEmail());
            cs.setString(7, getTelefono());
            cs.setString(8, getDireccion());
            cs.setString(9, getNacimiento());
            cs.setString(10, getContrasena());
            cs.setString(11, getFoto_perfil());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Se inserto correctamente la persona");
        }catch(HeadlessException | SQLException e1){
            JOptionPane.showMessageDialog(null,"No se inserto correctamente la persona, error: "+ e1.toString());
        
        }
    }
    
    public void mostrarPersonas(JTable paramTableTotalPersonas){
        CConexion objConexion2 = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel> (modelo);
        paramTableTotalPersonas.setRowSorter(ordenarTabla);
        
        String sql = "";
        
        modelo.addColumn("Cedula");
        modelo.addColumn("Nombre");
        modelo.addColumn("Apellido");
        modelo.addColumn("Usuario");
        modelo.addColumn("Bio");
        modelo.addColumn("Email");
        modelo.addColumn("Telefono");
        modelo.addColumn("Direccion");
        modelo.addColumn("Nacimiento");
        modelo.addColumn("Contrasena");
        modelo.addColumn("Foto");
        
        paramTableTotalPersonas.setModel(modelo);
        
        sql = "select * from persona";
        
        String[] datos = new String[11];
        Statement st;
        
        try{
            st = objConexion2.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);
                
                modelo.addRow(datos);
            }
            
            paramTableTotalPersonas.setModel(modelo);
            
        }catch(Exception e2){
            JOptionPane.showMessageDialog(null, "No se puede mostrar los registros, error: "+e2.toString());
        }
    }
    
    public void seleccionarPersona(JTable paramTablePersonas, JTextField paramCedula, JTextField paramNombre, JTextField paramApellido, JTextField paramNom_usuario,JTextField paramBio_perfil,JTextField paramEmail, JTextField paramTelefono, JTextField paramDireccion, JTextField paramNacimiento, JTextField paramContrasena, JTextField paramFoto_perfil){
        try{
            int fila = paramTablePersonas.getSelectedRow();
            
            if(fila >= 0){
                paramCedula.setText((paramTablePersonas.getValueAt(fila, 0)).toString());
                paramNombre.setText((paramTablePersonas.getValueAt(fila, 1)).toString());
                paramApellido.setText((paramTablePersonas.getValueAt(fila, 2)).toString());
                paramNom_usuario.setText((paramTablePersonas.getValueAt(fila, 3)).toString());
                paramBio_perfil.setText((paramTablePersonas.getValueAt(fila, 4)).toString());
                paramEmail.setText((paramTablePersonas.getValueAt(fila, 5)).toString());
                paramTelefono.setText((paramTablePersonas.getValueAt(fila, 6)).toString());
                paramDireccion.setText((paramTablePersonas.getValueAt(fila, 7)).toString());
                paramNacimiento.setText((paramTablePersonas.getValueAt(fila, 8)).toString());
                paramContrasena.setText((paramTablePersonas.getValueAt(fila, 9)).toString());
                paramFoto_perfil.setText((paramTablePersonas.getValueAt(fila, 10)).toString());
            }
            else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
            
        }catch(Exception e3){
            JOptionPane.showMessageDialog(null, "Error de seleccion: "+e3.toString());
            
        }
    }
    
    public void modificarPersonas(JTextField paramCedula, JTextField paramNombre, JTextField paramApellido, JTextField paramNom_usuario,JTextField paramBio_perfil,JTextField paramEmail, JTextField paramTelefono, JTextField paramDireccion, JTextField paramNacimiento, JTextField paramContrasena, JTextField paramFoto_perfil){
        
        setCedula(paramCedula.getText());
        setNombre(paramNombre.getText());
        setApellido(paramApellido.getText());
        setNom_usuario(paramNom_usuario.getText());
        setBio_perfil(paramBio_perfil.getText());
        setEmail(paramEmail.getText());
        setTelefono(paramTelefono.getText());
        setDireccion(paramDireccion.getText());
        setNacimiento(paramNacimiento.getText());
        setContrasena(paramContrasena.getText());
        setFoto_perfil(paramFoto_perfil.getText());
        
        CConexion objConexion2 = new CConexion();
        
        String consulta1 = "UPDATE persona SET nombre = ?, apellido = ?, nom_usuario = ?, bio_perfil = ?, email = ?, telefono = ?, direccion = ?, fecha_nacimiento = ?, contrasena = ?, foto_perfil = ? WHERE cedula = ?;";
        
        try{
            
           CallableStatement cs = objConexion2.estableceConexion().prepareCall(consulta1);
            cs.setString(1, getNombre());
            cs.setString(2, getApellido());
            cs.setString(3, getNom_usuario());
            cs.setString(4, getBio_perfil());
            cs.setString(5, getEmail());
            cs.setString(6, getTelefono());
            cs.setString(7, getDireccion());
            cs.setString(8, getNacimiento());
            cs.setString(9, getContrasena());
            cs.setString(10, getFoto_perfil());
            cs.setString(11, getCedula());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Modificacion exitosa");

        }catch(SQLException e4){
            JOptionPane.showMessageDialog(null, "No se modifico, error: "+e4.toString());
        }
    }

    public void EliminarPersonas(JTextField paramCedula){
        setCedula(paramCedula.getText());
        
        CConexion objConexion3 = new CConexion();
        
        String consulta3 = "DELETE FROM persona WHERE persona.cedula=?;";
        
        try{
        
            CallableStatement cs = objConexion3.estableceConexion().prepareCall(consulta3);
            cs.setString(1, getCedula());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se elimino Correctamente a la persona");
        }catch(Exception e5){
            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: "+e5.toString());
        }
    }
    
    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getNom_usuario() {
        return nom_usuario;
    }

    public void setNom_usuario(String nom_usuario) {
        this.nom_usuario = nom_usuario;
    }

    public String getBio_perfil() {
        return bio_perfil;
    }

    public void setBio_perfil(String bio_perfil) {
        this.bio_perfil = bio_perfil;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getFoto_perfil() {
        return foto_perfil;
    }

    public void setFoto_perfil(String foto_perfil) {
        this.foto_perfil = foto_perfil;
    }
    
}
