/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package com.mycompany.java_croud_mysql_proyecto;

/**
 *
 * @author CltControl
 */
public enum TipoEntrega {
    
    RETIRO("Retiro"),
    DOMICIOLIO("En Proceso");

    private final String descripcion;

    private TipoEntrega(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

}
