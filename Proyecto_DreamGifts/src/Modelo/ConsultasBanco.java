
package Modelo;

import static Modelo.Conexion.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultasBanco{
    
    public boolean registrar(Banco ban){
    
      PreparedStatement ps = null;
      String sql = "INSERT INTO Bancos (cod_banco, nombre, estado) VALUES(?,?,?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, ban.getCodigo());
          ps.setString(2, ban.getNombre());
          ps.setBoolean(3, ban.getEstado());
          ps.execute();
          System.out.println("registrado");
          return true;
      } catch (SQLException e){
          System.out.println(e);
          return false;
      }
    }

    public boolean modificar(Banco ban){
    
      PreparedStatement ps = null;
      String sql = "UPDATE Bancos SET nombre=?, estado=? WHERE cod_banco=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, ban.getNombre());
          ps.setBoolean(2, ban.getEstado());
          ps.setString(3, ban.getCodigo());
          ps.execute();
          return true;
      } catch (SQLException e){
          return false;
      }
    }
    public boolean buscar(Banco ban){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Bancos WHERE cod_banco=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, ban.getCodigo());
          rs = ps.executeQuery();
          if (!rs.absolute(1)) {
              System.out.println("banco no encontrado");
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
      String sql = "SELECT * FROM Bancos";
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
      String sql = "SELECT * FROM Bancos WHERE estado=1";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return null;
      }
     }
     
    public int BancoIdPorNombre (String namebanco){
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id;
        String sql = "SELECT * FROM Bancos WHERE Nombre=?";
        try {
            ps =  conn.prepareStatement(sql);
            ps.setString(1, namebanco);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("Banco no encontrado");
              return -1; 
            }else{
                id = Integer.parseInt(rs.getString(1));
                System.out.println(id);
                return id;
            }        
        } catch (SQLException e){
            return -1;
        }
    }
    
    public int BancoNombrePorId (int idbanco){
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id;
        String sql = "SELECT * FROM Bancos WHERE Nombre=?";
        try {
            ps =  conn.prepareStatement(sql);
            ps.setInt(1, idbanco);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("Banco no encontrado");
              return -1; 
            }else{
                id = Integer.parseInt(rs.getString(1));
                 System.out.println(idbanco);
                return idbanco;
            }        
        } catch (SQLException e){
            return -1;
        }
    }
}
