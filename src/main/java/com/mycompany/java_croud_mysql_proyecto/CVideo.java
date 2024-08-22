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

/**
 *
 * @author jorge
 */
public class CVideo {
    private int idVideo;
    private String titulo;
    private String fecha;
    private String url;
    private int id_curso;
    
    public CVideo() {
    }
    public void InsertarVideo(JTextField paramTitulo, JTextField paramFecha, JTextField paramUrl, JTextField paramIdCurso) {
        setTitulo(paramTitulo.getText());
        setFecha(paramFecha.getText());
        setUrl(paramUrl.getText());
        setId_Curso(Integer.parseInt(paramIdCurso.getText())); // Convertimos el ID del curso a entero

        CConexion objConexion1 = new CConexion();

        String consulta = """
                          INSERT INTO Video (titulo, fecha, url, id_curso) VALUES
                          (?, ?, ?, ?)
                          """;

        try {
            CallableStatement cs = objConexion1.estableceConexion().prepareCall(consulta);
            cs.setString(1, getTitulo());
            cs.setDate(2, java.sql.Date.valueOf(getFecha())); // Convertir la fecha a java.sql.Date
            cs.setString(3, getUrl());
            cs.setInt(4, getId_Curso()); // Asignar el ID del curso

            cs.execute();

            JOptionPane.showMessageDialog(null,"Se insertó correctamente el video");
        } catch (HeadlessException | SQLException e1) {
            JOptionPane.showMessageDialog(null,"No se insertó correctamente el video, error: " + e1.toString());
        }
    }
    
    public void mostrarVideos(JTable paramTableTotalVideos) {
        CConexion objConexion2 = new CConexion();
        DefaultTableModel modelo = new DefaultTableModel();
        TableRowSorter<TableModel> ordenarTabla = new TableRowSorter<>(modelo);
        paramTableTotalVideos.setRowSorter(ordenarTabla);

        // Definir las columnas del modelo
        modelo.addColumn("ID Video"); 
        modelo.addColumn("Título");
        modelo.addColumn("Fecha");
        modelo.addColumn("URL");
        modelo.addColumn("ID Curso");

        // Asignar el modelo a la tabla
        paramTableTotalVideos.setModel(modelo);

        // Consulta SQL para obtener los datos
        String sql = "SELECT * FROM Video";

        String[] datos = new String[5]; 
        Statement st;

        try {
            // Crear el Statement y ejecutar la consulta
            st = objConexion2.estableceConexion().createStatement();
            ResultSet rs = st.executeQuery(sql);

            // Recorrer el ResultSet y llenar el modelo de la tabla
            while (rs.next()) {
                datos[0] = rs.getString("id_titulo");
                datos[1] = rs.getString("titulo");
                datos[2] = rs.getString("fecha");
                datos[3] = rs.getString("url");
                datos[4] = rs.getString("id_curso");

                modelo.addRow(datos);
            }

            paramTableTotalVideos.setModel(modelo);

        } catch (Exception e2) {
            JOptionPane.showMessageDialog(null, "No se pueden mostrar los registros, error: " + e2.toString());
        }
    }
    
    public void seleccionarVideo(JTable paramTableVideos,JLabel idVideo, JTextField paramTitulo, JTextField paramFecha, JTextField paramUrl, JTextField paramIdCurso) {
        try {
            int fila = paramTableVideos.getSelectedRow(); // Obtiene la fila seleccionada

            if (fila >= 0) {
                // Rellena los campos de texto con los valores de la fila seleccionada
                idVideo.setText((paramTableVideos.getValueAt(fila, 0)).toString());
                paramTitulo.setText((paramTableVideos.getValueAt(fila, 1)).toString());
                paramFecha.setText((paramTableVideos.getValueAt(fila, 2)).toString());
                paramUrl.setText((paramTableVideos.getValueAt(fila, 3)).toString());
                paramIdCurso.setText((paramTableVideos.getValueAt(fila, 4)).toString());
            } else {
                JOptionPane.showMessageDialog(null, "Fila no seleccionada");
            }

        } catch (Exception e3) {
            JOptionPane.showMessageDialog(null, "Error de selección: " + e3.toString());
        }
    }
    public void deseleccionarVideo(JTable paramTableVideos, JLabel idVideo, JTextField paramTitulo, JTextField paramFecha, JTextField paramUrl, JTextField paramIdCurso) {
        // Desseleccionar cualquier fila en la tabla
        paramTableVideos.clearSelection();

        // Limpiar los campos
        idVideo.setText("");
        paramTitulo.setText("");
        paramFecha.setText("");
        paramUrl.setText("");
        paramIdCurso.setText("");
    }
    public void modificarVideo(JTextField paramTitulo, JTextField paramFecha, JTextField paramUrl, JTextField paramIdCurso, JLabel paramIdVideo) {
        // Obtener los valores de los campos y convertirlos a los tipos adecuados
        setTitulo(paramTitulo.getText());
        setFecha(paramFecha.getText()); // Suponiendo que el formato de fecha es yyyy-MM-dd
        setUrl(paramUrl.getText());
        setId_Curso(Integer.parseInt(paramIdCurso.getText()));
        setIdVideo(Integer.parseInt(paramIdVideo.getText()));

        // Crear una instancia de conexión
        CConexion objConexion2 = new CConexion();

        // Consulta SQL para actualizar el video
        String consulta1 = """
            UPDATE Video 
            SET titulo = ?, fecha = ?, url = ?, id_curso = ? 
            WHERE id_video = ?;
        """;

        try {
            // Preparar el CallableStatement
            CallableStatement cs = objConexion2.estableceConexion().prepareCall(consulta1);

            // Configurar los parámetros del PreparedStatement
            cs.setString(1, getTitulo());
            cs.setDate(2, java.sql.Date.valueOf(getFecha())); // Convertir la fecha a java.sql.Date
            cs.setString(3, getUrl());
            cs.setInt(4, getId_Curso()); // Convertir el ID del curso a entero
            cs.setInt(5, getIdVideo()); // Convertir el ID del video a entero

            // Ejecutar la consulta
            cs.execute();

            // Mensaje de éxito
            JOptionPane.showMessageDialog(null, "Modificación exitosa");
        } catch (SQLException e4) {
            // Mensaje de error
            JOptionPane.showMessageDialog(null, "No se modificó, error: " + e4.toString());
        }
    }
    public void EliminarVideo(JLabel paramIdVideo) {
        // Obtener el ID del video a eliminar
        setIdVideo(Integer.parseInt(paramIdVideo.getText()));

        // Crear una instancia de conexión
        CConexion objConexion3 = new CConexion();

        // Consulta SQL para eliminar el video
        String consulta3 = "DELETE FROM Video WHERE id_video = ?;";

        try {
            // Preparar el CallableStatement
            CallableStatement cs = objConexion3.estableceConexion().prepareCall(consulta3);

            // Configurar el parámetro del PreparedStatement
            cs.setInt(1, getIdVideo()); // Convertir el ID a entero

            // Ejecutar la consulta
            cs.execute();

            // Mensaje de éxito
            JOptionPane.showMessageDialog(null, "Se eliminó correctamente el video");
        } catch (Exception e5) {
            // Mensaje de error
            JOptionPane.showMessageDialog(null, "No se pudo eliminar, error: " + e5.toString());
        }
    }
    
    
    
    
    
    
    

    // Getter y Setter para titulo
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    // Getter y Setter para fecha
    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    // Getter y Setter para url
    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    // Getter y Setter para id_curso
    public int getId_Curso() {
        return id_curso;
    }

    public void setId_Curso(int id_curso) {
        this.id_curso = id_curso;
    }

    public int getIdVideo() {
        return idVideo;
    }

    public void setIdVideo(int idVideo) {
        this.idVideo = idVideo;
    }


 
    
}
