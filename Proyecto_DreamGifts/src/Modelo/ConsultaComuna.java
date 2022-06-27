/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import static Modelo.Conexion.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author Usuario
 */
public class ConsultaComuna{
    
    public boolean registrar(Comuna com){
    
      PreparedStatement ps = null;
      String sql = "INSERT INTO comunas (cod_comuna, nombre, estado) VALUES(?,?,?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, com.getCodigo());
          ps.setString(2, com.getNombre());
          ps.setBoolean(3, com.isEstado());
          ps.execute();
          System.out.println("registrado");
          return true;
      } catch (SQLException e){
          System.out.println(e);
          return false;
      }
    }

    public boolean modificar(Comuna com){
    
      PreparedStatement ps = null;
      String sql = "UPDATE comunas SET nombre=?, estado=? WHERE cod_comuna=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, com.getNombre());
          ps.setBoolean(2, com.isEstado());
          ps.setString(3, com.getCodigo());
          ps.execute();
          return true;
      } catch (SQLException e){
          return false;
      }
    }
    public boolean buscar(Comuna com){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM comunas WHERE cod_comuna=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, com.getCodigo());
          rs = ps.executeQuery();
          if (!rs.absolute(1)) {
              System.out.println("comuna no encontrado");
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
      String sql = "SELECT * FROM comunas";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return null;
      }
     }
     
     public ResultSet llamarActivos(){
         PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM comunas WHERE estado=1";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return null;
      }
     }
     
     
      public int ComunaIdPorNombre (String Comuna){
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id;
        String sql = "SELECT * FROM comunas WHERE nombre=?  ";
        try {
            ps =  conn.prepareStatement(sql);
            ps.setString(1, Comuna);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("Comuna no encontrado");
              return -1; 
            }else{
                id = Integer.parseInt(rs.getString(1));
                return id;
            }
                
        } catch (SQLException e){
            return -1;
        }
    }
     
      public String buscarNamePorId (int id){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String name;
        String sql = "SELECT * FROM comunas WHERE id_comunas=?  ";
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
