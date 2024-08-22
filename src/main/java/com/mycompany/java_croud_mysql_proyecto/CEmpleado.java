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
public class CEmpleado {
    
    String cedula;
    String cargo;
    String jefe;

    public void InsertarEmpleado(JTextField paramCedula, JTextField paramCargo, JTextField paramJefe){
        setCedula(paramCedula.getText());
        setCargo(paramCargo.getText());
        setJefe(paramJefe.getText());
        
        CConexion objConexion1 = new CConexion();
        
        String consulta ="""
                         INSERT INTO Empleado (cedula, cargo, jefe) VALUES
                         (?, ?, ?)""";
        
        try {
            CallableStatement cs = objConexion1.estableceConexion().prepareCall(consulta);
            cs.setString(1, getCedula());
            cs.setString(2, getCargo());
            cs.setString(3, getJefe());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null,"Se inserto correctamente al empleado");
        }catch(HeadlessException | SQLException e1){
            JOptionPane.showMessageDialog(null,"No se inserto correctamente al empleado, error: "+ e1.toString());
        
        }
    }
    
    public void mostrarEmpleado(JTable paramTableTotalEmpleados){
        CConexion objConexion2 = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<TableModel> (modelo);
        paramTableTotalEmpleados.setRowSorter(ordenarTabla);
        
        String sql = "";
        
        modelo.addColumn("Cedula");
        modelo.addColumn("Cargo");
        modelo.addColumn("Jefe");
        
        paramTableTotalEmpleados.setModel(modelo);
        
        sql = "select * from empleado";
        
        String[] datos = new String[3];
        Statement st;
        
        try{
            st = objConexion2.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);
            
            while(rs.next()){
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);

                modelo.addRow(datos);
            }
            
            paramTableTotalEmpleados.setModel(modelo);
            
        }catch(Exception e2){
            JOptionPane.showMessageDialog(null, "No se puede mostrar los registros, error: "+e2.toString());
        }
    }
    
    public void seleccionarEmpleado(JTable paramTableEmpleados, JTextField paramCedula, JTextField paramCargo, JTextField paramJefe){
        try{
            int fila = paramTableEmpleados.getSelectedRow();
            
            if(fila >= 0){
                paramCedula.setText((paramTableEmpleados.getValueAt(fila, 0)).toString());
                paramCargo.setText((paramTableEmpleados.getValueAt(fila, 1)).toString());
                paramJefe.setText((paramTableEmpleados.getValueAt(fila, 2)).toString());
                
            }
            else{
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }
            
        }catch(Exception e3){
            JOptionPane.showMessageDialog(null, "Error de seleccion: "+e3.toString());
            
        }
    }
    
    public void modificarEmpleado(JTextField paramCedula, JTextField paramCargo, JTextField paramJefe){
        
        setCedula(paramCedula.getText());
        setCargo(paramCargo.getText());
        setJefe(paramJefe.getText());
        
        CConexion objConexion2 = new CConexion();
        
        
        String consulta1 = "UPDATE empleado SET cargo = ?, jefe = ?WHERE cedula = ?;";
        try{

           CallableStatement cs = objConexion2.estableceConexion().prepareCall(consulta1);
            
            cs.setString(1, getCargo());
            cs.setString(2, getJefe());
            cs.setString(3, getCedula());
           
            cs.execute();

            JOptionPane.showMessageDialog(null, "Modificacion exitosa");

        }catch(SQLException e4){
            JOptionPane.showMessageDialog(null, "No se modifico, error: "+e4.toString());
        }
        
    }
    
    public void EliminarEmpleado(JTextField paramCedula){
        setCedula(paramCedula.getText());
        
        CConexion objConexion3 = new CConexion();
        
        String consulta3 = "DELETE FROM empleado WHERE empleado.cedula=?;";
        
        try{
        
            CallableStatement cs = objConexion3.estableceConexion().prepareCall(consulta3);
            cs.setString(1, getCedula());
            
            cs.execute();
            
            JOptionPane.showMessageDialog(null, "Se elimino Correctamente al empleado. ");
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

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getJefe() {
        return jefe;
    }

    public void setJefe(String jefe) {
        this.jefe = jefe;
    }
}
