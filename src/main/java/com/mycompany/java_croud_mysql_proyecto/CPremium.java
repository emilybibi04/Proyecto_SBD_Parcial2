/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.java_croud_mysql_proyecto;

/**
 *
 * @author CltControl
 */
public class CPremium extends CUsuario {
    private String id_premium;
    
    public CPremium(String id_premium, String cedula) {
        super(cedula);
        this.id_premium = id_premium;
    }

    public String getId_premium() {
        return id_premium;
    }

    public void setId_premium(String id_premium) {
        this.id_premium = id_premium;
    }
    
    
}
