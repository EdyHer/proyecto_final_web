/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author edyeh
 */
public class Producto {
    private int id_producto, id_marca, existencia;
    private String producto, descripcion, imagen, fecha_ingreso;
    private Float precio_costo, precio_venta;
    private Conexion cn;
    public Producto(){}
    public Producto(int id_producto, int id_marca, int existencia, String producto, String descripcion, String imagen, String fecha_ingreso, Float precio_costo, Float precio_venta) {
        this.id_producto = id_producto;
        this.id_marca = id_marca;
        this.existencia = existencia;
        this.producto = producto;
        this.descripcion = descripcion;
        this.imagen = imagen;
        this.fecha_ingreso = fecha_ingreso;
        this.precio_costo = precio_costo;
        this.precio_venta = precio_venta;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public int getExistencia() {
        return existencia;
    }

    public void setExistencia(int existencia) {
        this.existencia = existencia;
    }

    public String getProducto() {
        return producto;
    }

    public void setProducto(String producto) {
        this.producto = producto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }

    public Float getPrecio_costo() {
        return precio_costo;
    }

    public void setPrecio_costo(Float precio_costo) {
        this.precio_costo = precio_costo;
    }

    public Float getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(Float precio_venta) {
        this.precio_venta = precio_venta;
    }
    public HashMap drop_producto(){
        HashMap<String,String> drop = new HashMap();
        try{
            cn = new Conexion();
            String query = "SELECT id_producto as id, producto FROM productos;";
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("producto"));
            }
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return drop;
    }
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn = new Conexion();
            cn.abrir_conexion();
            String query="SELECT p.id_producto as id, p.producto, m.id_marca, m.marca, p.descripcion, p.imagen, p.precio_costo, p.precio_venta, p.existencia, p.fecha_ingreso FROM productos as p inner join marcas as m on p.id_marca = m.id_marca;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[]={"id","producto","id_marca","marca","descripcion","imagen","precio_costo","precio_venta","existencia","fecha_ingreso"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[10];
            while(consulta.next()){
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("producto");
                datos[2] = consulta.getString("marca");
                datos[3] = consulta.getString("id_marca");
                datos[4] = consulta.getString("descripcion");
                datos[5] = consulta.getString("imagen");
                datos[6] = consulta.getString("precio_costo");
                datos[7] = consulta.getString("precio_venta");                
                datos[8] = consulta.getString("existencia");
                datos[9] = consulta.getString("fecha_ingreso");
                tabla.addRow(datos);
            }
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return tabla;
    }
    
    public int agregar(){
        int retorno=0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query="INSERT INTO productos(producto,id_marca,descripcion,imagen,precio_costo,precio_venta,existencia,fecha_ingreso) VALUES (?,?,?,?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getProducto());
            parametro.setInt(2, getId_marca());
            parametro.setString(3, getDescripcion());            
            parametro.setString(4, getImagen());
            parametro.setFloat(5, getPrecio_costo());
            parametro.setFloat(6, getPrecio_venta());            
            parametro.setInt(7, getExistencia());
            parametro.setString(8, getFecha_ingreso());
            retorno=parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            retorno=0;
        }
        return retorno;
    }
    
    public int modificar(){
        int retorno = 0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "UPDATE productos SET producto = ?, id_marca = ?, descripcion = ?, imagen = ?, precio_costo = ?, precio_venta = ?, existencia = ?, fecha_ingreso = ? WHERE id_producto = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getProducto());
            parametro.setInt(2, getId_marca());
            parametro.setString(3, getDescripcion());            
            parametro.setString(4, getImagen());
            parametro.setFloat(5, getPrecio_costo());
            parametro.setFloat(6, getPrecio_venta());            
            parametro.setInt(7, getExistencia());
            parametro.setString(8, getFecha_ingreso());
            parametro.setInt(9, getId_producto());
            retorno=parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            retorno=0;
        }
        return retorno;
    }
    
    public int eliminar(){
        int retorno = 0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query="DELETE FROM productos WHERE id_producto = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId_producto());
            retorno=parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            retorno=0;
        }
        return retorno;
    }
}


