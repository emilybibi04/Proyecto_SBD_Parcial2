/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_croud_mysql_proyecto;

import java.time.LocalDate;

/**
 *
 * @author CltControl
 */
public class CResena {
    private int idResena; // Se puede establecer después de la inserción
    private String comentario;
    private int calificacion;
    private LocalDate fecha; // Cambiado a LocalDate
    private String foto;
    private String cedula;

    // Constructor
    public CResena(String comentario, int calificacion, LocalDate fecha, String foto, String cedula) {
        this.comentario = comentario;
        this.calificacion = calificacion;
        this.fecha = fecha;
        this.foto = foto;
        this.cedula = cedula;
    }

    // Getters y Setters
    public int getIdResena() {
        return idResena;
    }

    public void setIdResena(int idResena) {
        this.idResena = idResena;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public int getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(int calificacion) {
        this.calificacion = calificacion;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }
}

