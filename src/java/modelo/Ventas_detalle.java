/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author edyeh
 */
public class Ventas_detalle extends Producto {
    private int id_venta_detalle,id_venta,id_producto;
    private String cantidad;
    private float precio_unitario;
    private Conexion cn;

    public Ventas_detalle() {
        
    }

    public Ventas_detalle(int id_venta_detalle, int id_venta, String cantidad, float precio_unitario, int id_producto, int id_marca, int existencia, String producto, String descripcion, String imagen, String fecha_ingreso, Float precio_costo, Float precio_venta) {
        super(id_producto, id_marca, existencia, producto, descripcion, imagen, fecha_ingreso, precio_costo, precio_venta);
        this.id_venta_detalle = id_venta_detalle;
        this.id_venta = id_venta;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_unitario = precio_unitario;
    }

    public int getId_venta_detalle() {
        return id_venta_detalle;
    }

    public void setId_venta_detalle(int id_venta_detalle) {
        this.id_venta_detalle = id_venta_detalle;
    }

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getCantidad() {
        return cantidad;
    }

    public void setCantidad(String cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio_unitario() {
        return precio_unitario;
    }

    public void setPrecio_unitario(float precio_unitario) {
        this.precio_unitario = precio_unitario;
    }
      @Override
 public DefaultTableModel leer() {
    DefaultTableModel tabla = new DefaultTableModel();
        try{
             String query = "SELECT p.id_producto as id,p.producto,p.id_marca,p.descripcion,p.imagen,p.precio_costo,p.precio_venta,p.existencia,v.cantidad,p.fecha_ingreso,v.precio_unitario FROM productos as p inner join ventas_detalle as v on p.id_producto = v.id_producto;";
             ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
             String encabezado[] = {"id","producto","id_marca","descripcion","imagen","precio_costo","precio_venta","existencia","cantidad","fecha_ingreso","prcio_unitario"};
             tabla.setColumnIdentifiers(encabezado);
             String datos[] = new String[11];
             while (consulta.next()){
                 datos[0] = consulta.getString("id");
                 datos[1] = consulta.getString("producto");
                 datos[2] = consulta.getString("id_marca");
                 datos[3] = consulta.getString("descripcion");
                 datos[4] = consulta.getString("imagen");
                 datos[5] = consulta.getString("precio_costo");
                 datos[6] = consulta.getString("precio_venta");
                 datos[7] = consulta.getString("existencia");
                 datos[8] = consulta.getString("cantidad");
                 datos[9] = consulta.getString("fecha_ingreso");
                 datos[10] = consulta.getString("precio_unitario");
                 tabla.addRow(datos);
                }
               cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return tabla;
 }
    @Override
   public int agregar(){
       int retorno = 0;
   try{
       PreparedStatement parametro;
       String query  = "INSERT INTO ventas_detalle (id_producto,id_marca,existencia,producto,descripcion,imagen,fecha_ingreso,precio_costo,precio_venta,id_venta_detalle,id_venta,cantidad,precio_unitario) VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?);";
       cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId_producto());
            parametro.setInt(2, getId_marca());
            parametro.setInt(3, getExistencia());
            parametro.setString(4, getProducto());
            parametro.setString(5, getImagen());
            parametro.setString(6, getFecha_ingreso());
            parametro.setFloat(7, getPrecio_costo());
            parametro.setFloat(8, getPrecio_venta());
            parametro.setInt(9, getId_venta());
            parametro.setString(10, getCantidad());
            parametro.setInt(11, getId_venta_detalle());
            parametro.setFloat(12, getPrecio_unitario());
            
            
       retorno = parametro.executeUpdate();
       cn.cerrar_conexion();
       
   }catch(SQLException ex){
   System.out.println(ex.getMessage());
   retorno = 0;
   }
   return retorno;
   }    
    @Override
   public int modificar(){
       int retorno = 0;
   try{
       PreparedStatement parametro;
       String query="UPDATE ventas_detalle SET id_producto=?,id_marca=?,existencia=?,producto=?,descripcion=?,imagen=?,fecha_ingreso=?,precio_costo=?, precio_venta=?,id_venta_detalle = ?,id_venta=?,cantidad=?,precio_unitario = ? WHERE id_venta_detalle = ?;";
       cn.abrir_conexion();
       parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId_producto());
            parametro.setInt(2, getId_marca());
            parametro.setInt(3, getExistencia());
            parametro.setString(4, getProducto());
            parametro.setString(5, getImagen());
            parametro.setString(6, getFecha_ingreso());
            parametro.setFloat(7, getPrecio_costo());
            parametro.setFloat(8, getPrecio_venta());
            parametro.setInt(9, getId_venta());
            parametro.setString(10, getCantidad());
            parametro.setInt(11, getId_venta_detalle());
            parametro.setFloat(12, getPrecio_unitario());
       retorno = parametro.executeUpdate();
       cn.cerrar_conexion();
       
   }catch(SQLException ex){
   System.out.println(ex.getMessage());
   retorno = 0;
   }
   return retorno;
   }    
  
    @Override
   public int eliminar (){
        int retorno =0;
        try{
            PreparedStatement parametro;
            String query = "DELETE FROM ventas_detalle WHERE id_venta_detalle=?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId_venta_detalle());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
}
    
    
}
