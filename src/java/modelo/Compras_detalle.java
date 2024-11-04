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
public class Compras_detalle extends Producto {
    private int id_compra_detalle,id_compra,id_producto,cantidad;
    private float precio_costo_unitario;
    private Conexion cn;

    public Compras_detalle() { 
    }

    public Compras_detalle(int id_compra_detalle, int id_compra, int cantidad, float precio_costo_unitario, int id_producto, int id_marca, int existencia, String producto, String descripcion, String imagen, String fecha_ingreso, Float precio_costo, Float precio_venta) {
        super(id_producto, id_marca, existencia, producto, descripcion, imagen, fecha_ingreso, precio_costo, precio_venta);
        this.id_compra_detalle = id_compra_detalle;
        this.id_compra = id_compra;
        this.id_producto = id_producto;
        this.cantidad = cantidad;
        this.precio_costo_unitario = precio_costo_unitario;
    }

    public int getId_compra_detalle() {
        return id_compra_detalle;
    }

    public void setId_compra_detalle(int id_compra_detalle) {
        this.id_compra_detalle = id_compra_detalle;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public float getPrecio_costo_unitario() {
        return precio_costo_unitario;
    }

    public void setPrecio_costo_unitario(float precio_costo_unitario) {
        this.precio_costo_unitario = precio_costo_unitario;
    }
    @Override
   public DefaultTableModel leer() {
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrir_conexion();
     //base al encabezado de la tabla del SQL//
      String query = "SELECT p.id_producto as id,p.producto,p.descripcion,p.imagen,p.precio_costo,p.precio_venta,p.existencia,p.fecha_ingreso,c.cantidad,c.costo_precio_unitario,c.id_compra FROM productos as p inner join compras_detalle as c on p.id_producto = c.id_producto;";
      ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
      String encabezado[] = {"id","producto","descripcion","imagen","precio_costo","precio_venta","existencia","fecha_ingreso","cantidad","costo_precio_unitario","id_compra"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[11];
      while (consulta.next()){
          datos[0] = consulta.getString("id");
          datos[1] = consulta.getString("producto");
          datos[2] = consulta.getString("dexcripcion");
          datos[3] = consulta.getString("imagen");
          datos[4] = consulta.getString("precio_costo");
          datos[5] = consulta.getString("precio_venta");
          datos[6] = consulta.getString("existencia");
          datos[7] = consulta.getString("fecha_ingreso");
          datos[8] = consulta.getString("cantidad");
          datos[9] = consulta.getString("costo_precio_unitario");
          datos[10] = consulta.getString("id_compra");
          tabla.addRow(datos);
         }
        cn.cerrar_conexion();
 }catch(SQLException ex){
     System.out.println(ex.getMessage());
 }
 return tabla;
 }

   public int agregar(){
       int retorno = 0;
   try{
       PreparedStatement parametro;
       cn = new Conexion();
       String query  = "INSERT INTO compras_detalle (producto,descripcion,imagen,precio_costo,precio_venta,existencia,fecha_ingreso,id_compras_detalle,id_compra,id_producto,cantidad,costo_precio_unitario) VALUES(?,?,?,?,?,?,?,?,?,?,?,?);";
       cn.abrir_conexion();
       parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
       parametro.setInt(1, getId_compra_detalle());
       parametro.setInt(2, getId_producto());
       parametro.setString(3, getProducto());
       parametro.setString(4, getDescripcion());
       parametro.setString(5, getImagen());
       parametro.setFloat(6, getPrecio_costo());
       parametro.setFloat(7, getPrecio_venta());
       parametro.setInt(8, getExistencia());
       parametro.setString(9, getFecha_ingreso());
       parametro.setInt(10, getCantidad());
       parametro.setFloat(11, this.getPrecio_costo_unitario());
       parametro.setInt(12, getId_compra());
       retorno = parametro.executeUpdate();
       cn.cerrar_conexion();
       
   }catch(SQLException ex){
   System.out.println(ex.getMessage());
   retorno = 0;
   }
   return retorno;
   }    
   
   public int modificar(){
    int retorno = 0;
   try{
       PreparedStatement parametro;
       cn = new Conexion();
       String query = "UPDATE compras_detalle SET producto=?,descripcion=?,imagen=?,precio_costo=?,precio_venta=?,existencia=?,fecha_ingreso=?,id_compras_detalle=?,id_compra=?,id_producto=?,cantidad=?,costo_precio_unitario=? WHERE id_compras_detalle=?;";
       cn.abrir_conexion();
       parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
       parametro.setInt(1, getId_compra_detalle());
       parametro.setInt(2, getId_producto());
       parametro.setString(3, getProducto());
       parametro.setString(4, getDescripcion());
       parametro.setString(5, getImagen());
       parametro.setFloat(6, getPrecio_costo());
       parametro.setFloat(7, getPrecio_venta());
       parametro.setInt(8, getExistencia());
       parametro.setString(9, getFecha_ingreso());
       parametro.setInt(10, getCantidad());
       parametro.setFloat(11, this.getPrecio_costo_unitario());
       parametro.setInt(12, getId_compra());
       retorno = parametro.executeUpdate();
       cn.cerrar_conexion();
       
   }catch(SQLException ex){
   System.out.println(ex.getMessage());
   retorno = 0;
   }
   return retorno;
   }    
  
   public int eliminar (){
        int retorno = 0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "DELETE FROM compras_detalle WHERE id_compras_detalle=?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
             parametro.setInt(1, getId_compra_detalle());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
             retorno = 0;
        }
    return retorno;
} 
}

    

