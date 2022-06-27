package Modelo;

import static Modelo.Conexion.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaRedSocial {
    public boolean registrar(RedSocial red){
    
      PreparedStatement ps = null;
      String sql = "INSERT INTO RRSS (cod_rrss, nombre, estado) VALUES(?,?,?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, red.getCodigo());
          ps.setString(2, red.getNombre());
          ps.setBoolean(3, red.getEstado());
          ps.execute();
          System.out.println("registrado");
          return true;
      } catch (SQLException e){
          System.out.println(e);
          return false;
      }
    }

    public boolean modificar(RedSocial red){
    
      PreparedStatement ps = null;
      System.out.println("agregando red social");
      String sql = "UPDATE RRSS SET nombre=?, estado=? WHERE cod_rrss=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, red.getNombre());
          ps.setBoolean(2, red.getEstado());
          ps.setString(3, red.getCodigo());
          ps.execute();
          return true;
      } catch (SQLException e){
          System.out.println(e);
          return false;
      }
    }
    public boolean buscar(RedSocial red){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM RRSS WHERE cod_rrss=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, red.getCodigo());
          rs = ps.executeQuery();
          if (!rs.absolute(1)) {
              System.out.println("Red Social no encontrada");
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
      String sql = "SELECT * FROM RRSS";
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
      String sql = "SELECT * FROM RRSS WHERE estado=1";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return null;
      }
     }
      
    public int RRSSIdPorNombre (String namerrss){
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id;
        String sql = "SELECT * FROM RRSS WHERE Nombre=?  ";
        try {
            ps =  conn.prepareStatement(sql);
            ps.setString(1, namerrss);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("RRSS no encontrado");
              return -1; 
            }else{
                id = Integer.parseInt(rs.getString(1));
                return id;
            }         
        } catch (SQLException e){
            return -1;
        }
    }
    }

