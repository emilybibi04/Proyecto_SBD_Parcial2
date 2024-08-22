/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_croud_mysql_proyecto;

import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Time;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class CPedido {
    private int idPedido; //PEDIDO TIENE ID
    private double precioTotal;
    private String estado;
    private int cantProductos;
    private String fecha;
    private String hora;
    private String tipoEntrega;
    private String cedula;

    public CPedido() {
        // Constructor vacío
    }

    
    public void InsertarPedido(JTextField paramPrecioTotal,JTextField paramEstado, JTextField paramCantProductos, JTextField paramFecha, JTextField paramHora, JTextField paramTipoEntrega, JTextField paramCedula) {
        
        setPrecioTotal(Double.parseDouble(paramPrecioTotal.getText()));
        setEstado(paramEstado.getText());
        setCantProductos(Integer.parseInt(paramCantProductos.getText()));
        setFecha(paramFecha.getText());
        setHora(paramHora.getText());
        setTipoEntrega(paramTipoEntrega.getText());
        setCedula(paramCedula.getText());

        // Crear una instancia de la conexión
        CConexion objConexion1 = new CConexion();

        // Consulta SQL para insertar un pedido
        String consulta = """
                          INSERT INTO Pedido (precio_total, estado, cant_productos, fecha, hora, tipo_entrega, cedula) VALUES
                          (?, ?, ?, ?, ?, ?, ?)
                          """;

        try {
            // Preparar la consulta con los valores obtenidos
            CallableStatement cs = objConexion1.estableceConexion().prepareCall(consulta);
            cs.setDouble(1, precioTotal);
            cs.setString(2, estado);
            cs.setInt(3, cantProductos);
            cs.setString(4, fecha);
            cs.setString(5, hora);
            cs.setString(6, tipoEntrega);
            cs.setString(7, cedula);

            // Ejecutar la consulta
            cs.execute();

            // Mostrar un mensaje de éxito
            JOptionPane.showMessageDialog(null, "Se inserto correctamente el pedido");
        } catch (HeadlessException | SQLException e1) {
            // Mostrar un mensaje de error si falla la inserción
            JOptionPane.showMessageDialog(null, "No se inserto correctamente el pedido, error: " + e1.toString());
        }
    }
    public void mostrarPedidos(JTable paramTableTotalPedidos) {
        CConexion objConexion2 = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
        paramTableTotalPedidos.setRowSorter(ordenarTabla);

        // Definir las columnas del modelo
        modelo.addColumn("ID Pedido"); 
        modelo.addColumn("Precio Total");
        modelo.addColumn("Estado");
        modelo.addColumn("Cantidad Productos");
        modelo.addColumn("Fecha");
        modelo.addColumn("Hora");
        modelo.addColumn("Tipo Entrega");
        modelo.addColumn("Cédula");

        // Asignar el modelo a la tabla
        paramTableTotalPedidos.setModel(modelo);

        // Consulta SQL para obtener los datos
        String sql = "SELECT * FROM Pedido";

        String[] datos = new String[8]; 
        Statement st;

        try {
            // Crear el Statement y ejecutar la consulta
            st = objConexion2.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            // Recorrer el ResultSet y llenar el modelo de la tabla
            while (rs.next()) {
                datos[0] = rs.getString("id_pedido");
                datos[1] = rs.getString("precio_total");
                datos[2] = rs.getString("estado");
                datos[3] = rs.getString("cant_productos");
                datos[4] = rs.getString("fecha");
                datos[5] = rs.getString("hora");
                datos[6] = rs.getString("tipo_entrega");
                datos[7] = rs.getString("cedula");

                modelo.addRow(datos);
            }

            paramTableTotalPedidos.setModel(modelo);

        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "No se puede mostrar los registros, error: " + e2.toString());
        }
    }
    
    public void seleccionarPedido(JTable paramTablePedidos, JLabel paramIdPedido, JTextField paramPrecioTotal, JTextField paramEstado, JTextField paramCantProductos, JTextField paramFecha, JTextField paramHora, JTextField paramTipoEntrega, JTextField paramCedula) {
        try {
            int fila = paramTablePedidos.getSelectedRow();

            if (fila >= 0) {
                paramIdPedido.setText((paramTablePedidos.getValueAt(fila, 0)).toString());
                paramPrecioTotal.setText((paramTablePedidos.getValueAt(fila, 1)).toString());
                paramEstado.setText((paramTablePedidos.getValueAt(fila, 2)).toString());
                paramCantProductos.setText((paramTablePedidos.getValueAt(fila, 3)).toString());
                paramFecha.setText((paramTablePedidos.getValueAt(fila, 4)).toString());
                paramHora.setText((paramTablePedidos.getValueAt(fila, 5)).toString());
                paramTipoEntrega.setText((paramTablePedidos.getValueAt(fila, 6)).toString());
                paramCedula.setText((paramTablePedidos.getValueAt(fila, 7)).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }

        } catch (Exception e3) {
            JOptionPane.showMessageDialog(null, "Error de selección: " + e3.toString());
        }
    }
    
    public void deseleccionarPedido(JTable paramTablePedidos, JLabel paramIdPedido, JTextField paramPrecioTotal, JTextField paramEstado, JTextField paramCantProductos, JTextField paramFecha, JTextField paramHora, JTextField paramTipoEntrega, JTextField paramCedula) {
        // Desseleccionar cualquier fila en la tabla
        paramTablePedidos.clearSelection();

        // Limpiar los campos
        paramIdPedido.setText("");
        paramPrecioTotal.setText("");
        paramEstado.setText("");
        paramCantProductos.setText("");
        paramFecha.setText("");
        paramHora.setText("");
        paramTipoEntrega.setText("");
        paramCedula.setText("");
    }
    
   
    public void modificarPedido( JTextField paramPrecioTotal, JTextField paramEstado, JTextField paramCantProductos, JTextField paramFecha, JTextField paramHora, JTextField paramTipoEntrega, JTextField paramCedula,JLabel paramId) { //Combo box??
        
        
        setPrecioTotal(Double.parseDouble(paramPrecioTotal.getText()));
        setEstado(paramEstado.getText()); //getSelectedItem().toString()
        setCantProductos(Integer.parseInt(paramCantProductos.getText()));
        setFecha(paramFecha.getText());
        setHora(paramHora.getText());
        setTipoEntrega(paramTipoEntrega.getText());//getSelectedItem().toString()
        setCedula(paramCedula.getText());
        setIdPedido(Integer.parseInt(paramId.getText()));
        
        CConexion objConexion2 = new CConexion();

        String consulta1 = """
            UPDATE Pedido 
            SET precio_total = ?, estado = ?, cant_productos = ?, fecha = ?, hora = ?, tipo_entrega = ?, cedula = ? 
            WHERE id_pedido = ?;
        """;

        try {
            CallableStatement cs = objConexion2.estableceConexion().prepareCall(consulta1);
            cs.setDouble(1, getPrecioTotal()); // Convertir el precio total a double
    cs.setString(2, getEstado());
    cs.setInt(3, getCantProductos()); // Convertir la cantidad de productos a entero
    cs.setDate(4, java.sql.Date.valueOf(getFecha())); // Convertir la fecha a java.sql.Date
    cs.setTime(5, java.sql.Time.valueOf(getHora())); // Convertir la hora a java.sql.Time
    cs.setString(6, getTipoEntrega());
    cs.setString(7, getCedula());
    cs.setInt(8, getIdPedido());
            

            cs.execute();

            JOptionPane.showMessageDialog(null, "Modificación exitosa");
        } catch (SQLException e4) {
            JOptionPane.showMessageDialog(null, "No se modificó, error: " + e4.toString());
        }
    }
    
    
    public void EliminarPedido(JLabel paramIdPedido) {
        setIdPedido(Integer.parseInt(paramIdPedido.getText()));
        CConexion objConexion3 = new CConexion();

        String consulta3 = "DELETE FROM Pedido WHERE id_pedido = ?;";

        try {
            CallableStatement cs = objConexion3.estableceConexion().prepareCall(consulta3);
            cs.setInt(1, getIdPedido()); // Convertir el ID a entero

            cs.execute();

            JOptionPane.showMessageDialog(null, "Se eliminó correctamente el pedido");
        } catch (Exception e5) {
            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e5.toString());
        }
    }
    

    
    

    

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getCantProductos() {
        return cantProductos;
    }

    public void setCantProductos(int cantProductos) {
        this.cantProductos = cantProductos;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getTipoEntrega() {
        return tipoEntrega;
    }

    public void setTipoEntrega(String tipoEntrega) {
        this.tipoEntrega = tipoEntrega;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}
