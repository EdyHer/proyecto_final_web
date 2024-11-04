/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author edyeh
 */
public class Compras extends Proveedor {
    private int id_compra,no_orden_compra,id_proveedor;
    private String fecha_orden,fecha_ingreso;
    private Conexion cn;

    public Compras(){}
    public Compras(int id_compra, int no_orden_compra, int id_proveedor, String fecha_orden, String fecha_ingreso, String proveedor, String nit, String direccion, String telefono) {
        super(id_proveedor, proveedor, nit, direccion, telefono);
        this.id_compra = id_compra;
        this.no_orden_compra = no_orden_compra;
        this.id_proveedor = id_proveedor;
        this.fecha_orden = fecha_orden;
        this.fecha_ingreso = fecha_ingreso;
    }

    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public int getNo_orden_compra() {
        return no_orden_compra;
    }

    public void setNo_orden_compra(int no_orden_compra) {
        this.no_orden_compra = no_orden_compra;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public String getFecha_orden() {
        return fecha_orden;
    }

    public void setFecha_orden(String fecha_orden) {
        this.fecha_orden = fecha_orden;
    }

    public String getFecha_ingreso() {
        return fecha_ingreso;
    }

    public void setFecha_ingreso(String fecha_ingreso) {
        this.fecha_ingreso = fecha_ingreso;
    }
    public HashMap drop_compra(){
        HashMap<String,String> drop = new HashMap();
        try{
            cn = new Conexion();
            String query = "SELECT id_compra as id, no_orden_compra FROM compras;";
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("no_orden_compra"));
            }
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return drop;
    }
    @Override
    public DefaultTableModel leer() {
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrir_conexion();
     //base al encabezado de la tabla del SQL//
      String query = "SELECT p.id_proveedor as id, p.proveedor,p.nit,p.direccion,p.telefono,c.no_orden_compra,c.fecha_orden,c.fecha_ingreso FROM proveedores as p inner join compras as c on p.id_proveedor = c.id_proveedor;";
      ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
      String encabezado[] = {"id","proveedor","nit","direccion","telefono","no_orden_compra","fecha_orden","fecha_ingreso"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[8];
      while (consulta.next()){
          datos[0] = consulta.getString("id");
          datos[1] = consulta.getString("proveedor");
          datos[2] = consulta.getString("nit");
          datos[3] = consulta.getString("direccion");
          datos[4] = consulta.getString("telefono");
          datos[5] = consulta.getString("no_orden_compra");
          datos[6] = consulta.getString("fecha_orden");
          datos[7] = consulta.getString("fecha_ingreso");
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
       String query  = "INSERT INTO compras (proveedor,nit,direccion,telefono,no_orden_compra,fecha_orden,fecha_ingreso) VALUES (?,?,?,?,?,?,?,?,?);";
       cn.abrir_conexion();
       parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
       parametro.setInt(1, this.getId_compra());
       parametro.setString(2, getProveedor());
       parametro.setString(3, getNit());
       parametro.setString(4, getDireccion());
       parametro.setString(5, getTelefono());
       parametro.setInt(6, getNo_orden_compra());
       parametro.setString(7, getFecha_orden());
       parametro.setString(8, getFecha_ingreso());
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
       String query = "UPDATE compras SET  id_proveedor=?,proveedor=?,nit=?,direccion=?,telefono=?,id_compra = ?,no_orden_compra=?,fecha_orden=?,fecha_ingreso=? WHERE id_compra=?;";
       cn.abrir_conexion();
       parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
       parametro.setInt(1, this.getId_compra());
       parametro.setString(2, getProveedor());
       parametro.setString(3, getNit());
       parametro.setString(4, getDireccion());
       parametro.setString(5, getTelefono());
       parametro.setInt(6, getNo_orden_compra());
       parametro.setString(7, getFecha_orden());
       parametro.setString(8, getFecha_ingreso());
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
            String query = "DELETE FROM compras WHERE id_compra=?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId_compra());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
             retorno = 0;
        }
    return retorno;
}
}


    

