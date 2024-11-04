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
public class Ventas extends Cliente {
    private int id_venta,no_factura,id_cliente,id_empleado;   
    private String serie,fecha_factura,fecha_ingreso;
    private Conexion cn; 

    public Ventas() {
        
    }

    public Ventas(int id_venta, int no_factura, int id_cliente, int id_empleado, String serie, String fecha_factura,String nit, String correo, int id, String nombres, String apellidos, String telefono, String genero, String fecha_ingreso) {
        super(nit, correo, id, nombres, apellidos, telefono, genero, fecha_ingreso);
        this.id_venta = id_venta;
        this.no_factura = no_factura;
        this.id_cliente = id_cliente;
        this.id_empleado = id_empleado;
        this.serie = serie;
        this.fecha_factura = fecha_factura;
        this.fecha_ingreso = fecha_ingreso;
    }
    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getNo_factura() {
        return no_factura;
    }

    public void setNo_factura(int no_factura) {
        this.no_factura = no_factura;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public int getId_empleado() {
        return id_empleado;
    }

    public void setId_empleado(int id_empleado) {
        this.id_empleado = id_empleado;
    }

    public String getSerie() {
        return serie;
    }

    public void setSerie(String serie) {
        this.serie = serie;
    }

    public String getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(String fecha_factura) {
        this.fecha_factura = fecha_factura;
    }

     public HashMap drop_venta(){
        HashMap<String,String> drop = new HashMap();
        try{
            cn = new Conexion();
            String query = "SELECT id_venta as id, no_factura FROM ventas;";
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("no_factura"));
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
             String query = "SELECT c.id_cliente as id,c.nombres,c.apellidos,c.nit,c.genero,c.telefono,c.correo_electronico,v.no_factura,v.serie,v.fecha_factura,v.fecha_ingreso FROM clientes as c inner join ventas as v on c.id_cliente = v.id_cliente;";
             ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
             String encabezado[] = {"id","nombres","apellidos","nit","genero","telefono","correo_electronico","no_factura","fecha_factura","id_cliente","id_empleado","fecha_ingreso"};
             tabla.setColumnIdentifiers(encabezado);
             String datos[] = new String[12];
             while (consulta.next()){
                 datos[0] = consulta.getString("id");
                 datos[1] = consulta.getString("nombres");
                 datos[2] = consulta.getString("apellidos");
                 datos[3] = consulta.getString("nit");
                 datos[4] = consulta.getString("genero");
                 datos[5] = consulta.getString("telefono");
                 datos[6] = consulta.getString("correo_electronico");
                 datos[7] = consulta.getString("no_factura");
                 datos[8] = consulta.getString("fecha_factura");
                 datos[9] = consulta.getString("id_cliente");
                 datos[10] = consulta.getString("id_empleado");
                 datos[11] = consulta.getString("fecha_ingreso");
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
       String query  = "INSERT INTO ventas (nombres,apellidos,nit,genero,telefono,correo_electronico,id_venta,no_factura,serie,fecha_factura,id_cliente,id_empleado,fecha_ingreso) VALUES(?,?,?,?,?.?,?.?,?,?,?,?);";
       cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId_venta());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getNit());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getCorreo());
            parametro.setInt(7, getNo_factura());
            parametro.setString(8, getSerie());
            parametro.setString(9, getFecha_factura());
            parametro.setInt(10, getId_cliente());
            parametro.setInt(11, getId_empleado());
            parametro.setString(12, getFecha_ingreso());
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
       String query="UPDATE ventas SET nombres=?,apellidos=?,nit=?,genero=?,telefono=?,correo_electronico=?,id_venta=?,no_factura=?,serie=?,fecha_factura=?,id_cliente =?,id_empleado=?,fecha_ingreso=? WHERE id_venta = ?;";
       cn.abrir_conexion();
       parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
             parametro.setInt(1, getId_venta());
            parametro.setString(2, getNombres());
            parametro.setString(3, getApellidos());
            parametro.setString(4, getNit());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getCorreo());
            parametro.setInt(7, getNo_factura());
            parametro.setString(8, getSerie());
            parametro.setString(9, getFecha_factura());
            parametro.setInt(10, getId_cliente());
            parametro.setInt(11, getId_empleado());
            parametro.setString(12, getFecha_ingreso());
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
            String query = "DELETE FROM ventas WHERE id_venta=?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId_venta());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
    return retorno;
}

}


    

