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
public class Marcas {
    private int id_marca;
   private String marca;
   private Conexion cn;
   public Marcas(){
    }
    public Marcas(int id_marca, String marca) {
        this.id_marca = id_marca;
        this.marca = marca;
    }

    public int getId_marca() {
        return id_marca;
    }

    public void setId_marca(int id_marca) {
        this.id_marca = id_marca;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }
    
    public DefaultTableModel leer() {
 DefaultTableModel tabla = new DefaultTableModel();
 try{
     cn = new Conexion();
     cn.abrir_conexion();
      String query = "SELECT id_marca as id, marca from marcas;";
      ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
      String encabezado[] = {"id","marca"};
      tabla.setColumnIdentifiers(encabezado);
      String datos[] = new String[2];
      while (consulta.next()){
          datos[0] = consulta.getString("id");
          datos[1] = consulta.getString("marca");
        
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
       String query  = "insert into marcas(marca) values(?);";
       cn.abrir_conexion();
       parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
      
       parametro.setString(1, getMarca());
      
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
       String query = "update marcas set marca=? where id_marca= ?;";
       cn.abrir_conexion();
       parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
       parametro.setString(1, getMarca());
       parametro.setInt(2, getId_marca());
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
            String query = "DELETE FROM marcas WHERE id_marca= ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId_marca());
            retorno = parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
             retorno = 0;
        }
    return retorno;
}
    public HashMap drop_marca(){
        HashMap<String,String> drop = new HashMap();
        try{
            cn = new Conexion();
            String query = "SELECT id_marca as id, marca FROM marcas;";
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("marca"));
            }
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return drop;
    }
}

    

