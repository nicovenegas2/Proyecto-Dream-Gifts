/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;
import static Modelo.Conexion.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Usuario
 */
public class ConsultaCliente {
     public boolean registrar(Cliente client){
    
      PreparedStatement ps = null;
      String sql = "INSERT INTO Cliente (RUT, Nombre, Estado, Celular, Direccion, RRSS) VALUES(?,?,?,?,?,?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, client.getRut());
          ps.setString(2, client.getNombre());
          ps.setBoolean(3, client.isEstado());
//          ps.setDate(4, (java.sql.Date) client.getNacimiento());
          ps.setString(4, client.getCelular());
          ps.setString(5, client.getDireccion());
          ps.setString(6, client.getRedSocial());
          ps.execute();
          System.out.println("registrado");
          return true;
      } catch (SQLException e){
          System.out.println(e);
          return false;
      }
    }

    public boolean modificar(Cliente client){
    
      PreparedStatement ps = null;
      String sql = "UPDATE Cliente SET Nombre=?, Estado=?, Nacimiento=?, Celular=?, Direccion=?, RedSocial=? WHERE RUT=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(2, client.getNombre());
          ps.setBoolean(3, client.isEstado());
          ps.setDate(4, (java.sql.Date) client.getNacimiento());
          ps.setString(5, client.getCelular());
          ps.setString(5, client.getDireccion());
          ps.setString(5, client.getRedSocial());
          ps.execute();
          return true;
      } catch (SQLException e){
          return false;
      }
    }
    public boolean buscar(Cliente client){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Cliente WHERE RUT=?  ";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, client.getRut());
          rs = ps.executeQuery();
          if (!rs.absolute(1)) {
              System.out.println("Cliente no encontrado");
            return false; 
          }
          return true;
      } catch (SQLException e){
          return false;
      }
    }
    
     public ResultSet llamarTodos(){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Cliente";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return null;
      }
    }

    public int buscarIdPorRut(String rut) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id;
        String sql = "SELECT * FROM Cliente WHERE RUT=?  ";
        try {
            ps =  conn.prepareStatement(sql);
            ps.setString(1, rut);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("Cliente no encontrado");
              return -1; 
            }else{
                id = Integer.parseInt(rs.getString(1));
                return id;
            }
                
        } catch (SQLException e){
            return -1;
        }
    }
    
    public String buscarNamePorRut (String rut){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ID_Clientes, Nombre FROM Cliente where rut=?  ";
        try {
            ps = conn.prepareStatement (sql);
            ps.setString(1, rut);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("Cliente no encontrado");
              return "Cliente No Encontrado"; 
            }else{
                System.out.println("cliente encontrado");
                rut = rs.getString(2);
                return rut;
            }
            
        } catch (SQLException e) {
           System.out.println(e);
            return null;
        }
    }

    public String buscarNamePorId (int id){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String name;
        String sql = "SELECT ID_Clientes, Nombre FROM Cliente where ID_Clientes=?  ";
        try {
            ps = conn.prepareStatement (sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("Cliente no encontrado");
              return "Cliente No Encontrado"; 
            }else{
                System.out.println("cliente encontrado");
                name = rs.getString(2);
                return name;
            }
            
        } catch (SQLException e) {
           System.out.println(e);
            return null;
        }
    }
}
