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
import javax.swing.JOptionPane;

/**
 *
 * @author edyeh
 */
public class Cliente extends Persona {
    private String nit, correo;
    private Conexion cn;
    public Cliente() {}
    /*public Cliente(String nit, String correo) {
        this.nit = nit;
        this.correo = correo;
    }*/

    public Cliente(String nit, String correo, int id, String nombres, String apellidos, String telefono, String genero, String fecha_ingreso) {
        super(id, nombres, apellidos, telefono, genero, fecha_ingreso);
        this.nit = nit;
        this.correo = correo;
    }
    public static void asci(String[] args) {
		String data = JOptionPane.showInputDialog("Input chars");
		
		System.out.println(data);   
		 
        for (int i = 0; i < data.length(); i++){
            int as = data.charAt(i);
            System.out.println(as);  
        }
	}

    public String getNit() {
        return nit;
    }

    public void setNit(String nit) {
        this.nit = nit;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }
    
    public DefaultTableModel leer(){
        DefaultTableModel tabla = new DefaultTableModel();
        try{
            cn = new Conexion();
            cn.abrir_conexion();
            String query = "SELECT id_cliente as id, nombres, apellidos, nit, genero, telefono, correo_electronico, fecha_ingreso FROM clientes;";
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            String encabezado[]={"id","nombres","apellidos","nit","genero","telefono","correo_electronico","fecha_ingreso"};
            tabla.setColumnIdentifiers(encabezado);
            String datos[] = new String[8];
            while(consulta.next()){
                datos[0] = consulta.getString("id");
                datos[1] = consulta.getString("nombres");
                datos[2] = consulta.getString("apellidos");
                datos[3] = consulta.getString("nit");
                datos[4] = consulta.getString("genero");
                datos[5] = consulta.getString("telefono");
                datos[6] = consulta.getString("correo_electronico");
                datos[7] = consulta.getString("fecha_ingreso");
                tabla.addRow(datos);
            }
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
            cn = new Conexion();
            String query = "INSERT INTO clientes(nombres,apellidos,nit,genero,telefono,correo_electronico,fecha_ingreso) VALUES (?,?,?,?,?,?,?);";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombres());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getNit());
            parametro.setString(4, getGenero());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getCorreo());
            parametro.setString(7, getFecha_ingreso());
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
            cn = new Conexion();
            String query = "UPDATE clientes SET nombres=?,apellidos=?,nit=?,genero=?,telefono=?,correo_electronico=?,fecha_ingreso=? WHERE id_cliente = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setString(1, getNombres());
            parametro.setString(2, getApellidos());
            parametro.setString(3, getNit());
            parametro.setString(4, getGenero());
            parametro.setString(5, getTelefono());
            parametro.setString(6, getCorreo());
            parametro.setString(7, getFecha_ingreso());
            parametro.setInt(8, getId());
            retorno=parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            retorno=0;
        }
        return retorno;
    }
    
    @Override
    public int eliminar(){
        int retorno = 0;
        try{
            PreparedStatement parametro;
            cn = new Conexion();
            String query = "DELETE FROM clientes WHERE id_cliente = ?;";
            cn.abrir_conexion();
            parametro = (PreparedStatement)cn.conexionBD.prepareStatement(query);
            parametro.setInt(1, getId());
            retorno=parametro.executeUpdate();
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
            retorno=0;
        }
        return retorno;
    }
    public HashMap drop_cliente(){
        HashMap<String,String> drop = new HashMap();
        try{
            cn = new Conexion();
            String query = "SELECT id_empleado as id, nombres FROM empleados;";
            cn.abrir_conexion();
            ResultSet consulta = cn.conexionBD.createStatement().executeQuery(query);
            while (consulta.next()){
                drop.put(consulta.getString("id"), consulta.getString("nombres"));
            }
            cn.cerrar_conexion();
        }catch(SQLException ex){
            System.out.println(ex.getMessage());
        }
        return drop;
    }
    
    
}
