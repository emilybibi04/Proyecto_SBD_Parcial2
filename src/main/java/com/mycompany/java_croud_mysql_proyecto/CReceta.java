/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_croud_mysql_proyecto;

/**
 *
 * @author CltControl
 */
import java.time.LocalDate;

public class CReceta {
    private int idReceta; // Se establecerá después de la inserción
    private String titulo;
    private String instrucciones;
    private LocalDate fecha; // Cambiado a LocalDate

    // Constructor
    public CReceta(String titulo, String instrucciones, LocalDate fecha) {
        this.titulo = titulo;
        this.instrucciones = instrucciones;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getIdReceta() {
        return idReceta;
    }

    public void setIdReceta(int idReceta) {
        this.idReceta = idReceta;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInstrucciones() {
        return instrucciones;
    }

    public void setInstrucciones(String instrucciones) {
        this.instrucciones = instrucciones;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
}

