/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_croud_mysql_proyecto;

/**
 *
 * @author CltControl
 */
public class CBasico extends CUsuario {
    private String id_basico;

    public CBasico(String id_basico, String cedula) {
        super(cedula);
        this.id_basico = id_basico;
    }

    public String getId_basico() {
        return id_basico;
    }

    public void setId_basico(String id_basico) {
        this.id_basico = id_basico;
    }
    
}
