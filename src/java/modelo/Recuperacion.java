/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author edyeh
 */
public class Recuperacion {
    private String correo,comprobacion;
    
    //La comprobacion va con la base de datos y el correo es la variable que pedis
    //private Conexion cn; Aqui va la conexion 
    
    
    public Recuperacion(){}

    public Recuperacion(String correo, String comprobacion) {
        this.correo = correo;
        this.comprobacion = comprobacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getComprobacion() {
        return comprobacion;
    }

    public void setComprobacion(String comprobacion) {
        this.comprobacion = comprobacion;
    }
    
    
       
}


