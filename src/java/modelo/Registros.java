/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author edyeh
 */
public class Registros {
    private String nombre,apellidos,correo,telefono,genero;
    private String clave,verificacion;
    private Conexion cn;
    


    public Registros(){}

    public Registros(String nombre, String apellidos, String correo, String telefono, String genero, String clave, String verificacion) {
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.correo = correo;
        this.telefono = telefono;
        this.genero = genero;
        this.clave = clave;
        this.verificacion = verificacion;
    }



    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getVerificacion() {
        return verificacion;
    }

    public void setVerificacion(String verificacion) {
        this.verificacion = verificacion;
    }
    
    
    
    //@Override
    
    public int agregar(){
        int retorno = 0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "INSERT INTO usuario(nombre,apellidos,telefono,correo,clave,verificacion) VALUES (?,?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombre());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getTelefono());
            parametro.setString(4, getCorreo());
            parametro.setString(5, getGenero());
            do{
                parametro.setString(6, getClave());
                parametro.setString(7, getVerificacion());
            }while((clave)==(verificacion));
            
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            retorno = 0;
        }
        return retorno;
    }
    
}
