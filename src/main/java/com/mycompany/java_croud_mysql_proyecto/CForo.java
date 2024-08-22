/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_croud_mysql_proyecto;

import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CForo {
    private int idForo;
    private String nombre;
    private String tipo;
    private String fecha;

   
    public CForo() {
    }

     public void InsertarForo(JTextField paramNombre, JTextField paramTipo, JTextField paramFecha) {
        setNombre(paramNombre.getText());
        setTipo(paramTipo.getText());
        setFecha(paramFecha.getText());

        CConexion objConexion = new CConexion();

        String consulta = """
                          INSERT INTO Foro (nombre, tipo, fecha) VALUES
                          (?, ?, ?)
                          """;

        try {
            CallableStatement cs = objConexion.estableceConexion().prepareCall(consulta);
            cs.setString(1, getNombre());
            cs.setString(2, getTipo());
            cs.setDate(3, java.sql.Date.valueOf(getFecha())); // Convertir la fecha a java.sql.Date

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se insertó correctamente el foro");
        } catch (HeadlessException | SQLException e1) {
            JOptionPane.showMessageDialog(null, "No se insertó correctamente el foro, error: " + e1.toString());
        }
    }
     
     public void mostrarForos(JTable paramTableTotalForos) {
        CConexion objConexion2 = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
        paramTableTotalForos.setRowSorter(ordenarTabla);

        // Definir las columnas del modelo
        modelo.addColumn("ID Foro");
        modelo.addColumn("Nombre");
        modelo.addColumn("Tipo");
        modelo.addColumn("Fecha");

        // Asignar el modelo a la tabla
        paramTableTotalForos.setModel(modelo);

        // Consulta SQL para obtener los datos
        String sql = "SELECT * FROM Foro";

        String[] datos = new String[4]; // Se ajusta a la cantidad de columnas en la tabla Foro
        Statement st;

        try {
            // Crear el Statement y ejecutar la consulta
            st = objConexion2.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            // Recorrer el ResultSet y llenar el modelo de la tabla
            while (rs.next()) {
                datos[0] = rs.getString("id_foro");
                datos[1] = rs.getString("nombre");
                datos[2] = rs.getString("tipo");
                datos[3] = rs.getString("fecha");

                modelo.addRow(datos);
            }

            paramTableTotalForos.setModel(modelo);

        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "No se pueden mostrar los registros, error: " + e2.toString());
        }
    }
     public void seleccionarForo(JTable paramTableForos, JLabel idForo, JTextField paramNombre, JTextField paramTipo, JTextField paramFecha) {
        try {
            int fila = paramTableForos.getSelectedRow(); // Obtiene la fila seleccionada

            if (fila >= 0) {
                // Rellena los campos de texto con los valores de la fila seleccionada
                idForo.setText(paramTableForos.getValueAt(fila, 0).toString());
                paramNombre.setText(paramTableForos.getValueAt(fila, 1).toString());
                paramTipo.setText(paramTableForos.getValueAt(fila, 2).toString());
                paramFecha.setText(paramTableForos.getValueAt(fila, 3).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }

        } catch (Exception e3) {
            JOptionPane.showMessageDialog(null, "Error de selección: " + e3.toString());
        }
    }
     public void deseleccionarForo(JTable paramTableForos, JLabel idForo, JTextField paramNombre, JTextField paramTipo, JTextField paramFecha) {
        // Desseleccionar cualquier fila en la tabla
        paramTableForos.clearSelection();

        // Limpiar los campos
        idForo.setText("");
        paramNombre.setText("");
        paramTipo.setText("");
        paramFecha.setText("");
    }
     public void modificarForo(JTextField paramNombre, JTextField paramTipo, JTextField paramFecha, JLabel paramIdForo) {
        // Obtener los valores de los campos y convertirlos a los tipos adecuados
        String nombre = paramNombre.getText();
        String tipo = paramTipo.getText();
        String fecha = paramFecha.getText(); // Suponiendo que el formato de fecha es yyyy-MM-dd
        int idForo = Integer.parseInt(paramIdForo.getText());

        // Crear una instancia de conexión
        CConexion objConexion2 = new CConexion();

        // Consulta SQL para actualizar el foro
        String consulta1 = """
            UPDATE Foro 
            SET nombre = ?, tipo = ?, fecha = ? 
            WHERE id_foro = ?;
        """;

        try {
            // Preparar el CallableStatement
            CallableStatement cs = objConexion2.estableceConexion().prepareCall(consulta1);

            // Configurar los parámetros del CallableStatement
            cs.setString(1, nombre);
            cs.setString(2, tipo);
            cs.setDate(3, java.sql.Date.valueOf(fecha)); // Convertir la fecha a java.sql.Date
            cs.setInt(4, idForo); // Convertir el ID del foro a entero

            // Ejecutar la consulta
            cs.execute();

            // Mensaje de éxito
            JOptionPane.showMessageDialog(null, "Modificación exitosa");
        } catch (SQLException e4) {
            // Mensaje de error
            JOptionPane.showMessageDialog(null, "No se modificó, error: " + e4.toString());
        }
    }
     public void eliminarForo(JLabel paramIdForo) {
        // Obtener el ID del foro a eliminar
        int idForo = Integer.parseInt(paramIdForo.getText());

        // Crear una instancia de conexión
        CConexion objConexion3 = new CConexion();

        // Consulta SQL para eliminar el foro
        String consulta3 = "DELETE FROM Foro WHERE id_foro = ?;";

        try {
            // Preparar el CallableStatement
            CallableStatement cs = objConexion3.estableceConexion().prepareCall(consulta3);

            // Configurar el parámetro del CallableStatement
            cs.setInt(1, idForo); // Asignar el ID del foro a eliminar

            // Ejecutar la consulta
            cs.execute();

            // Mensaje de éxito
            JOptionPane.showMessageDialog(null, "Se eliminó correctamente el foro");
        } catch (Exception e5) {
            // Mensaje de error
            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e5.toString());
        }
    }
     
     

    
    
    
    
    
    public int getIdForo() {
        return idForo;
    }

    public void setIdForo(int idForo) {
        this.idForo = idForo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
