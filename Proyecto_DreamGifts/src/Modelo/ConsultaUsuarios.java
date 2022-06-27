package Modelo;

import static Modelo.Conexion.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaUsuarios {
    public boolean registrar(Usuario user){
    
      PreparedStatement ps = null;
      String sql = "INSERT INTO Usuarios (RUT, Nombre, Estado, Usuario, Contraseña) VALUES(?,?,?,?,?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, user.getRut());
          ps.setString(2, user.getNombre());
          ps.setBoolean(3, user.getEstado());
          ps.setString(4, user.getUser());
          ps.setString(5, user.getContraseña());
          ps.execute();
          System.out.println("registrado");
          return true;
      } catch (SQLException e){
          System.out.println(e);
          return false;
      }
    }

    public boolean modificar(Usuario user){
    
      PreparedStatement ps = null;
      String sql = "UPDATE Usuarios SET Nombre=?, Estado=?, Contraseña=?, Usuario=? WHERE RUT=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, user.getNombre());
          ps.setBoolean(2, user.getEstado());
          ps.setString(3, user.getContraseña());
          ps.setString(4, user.getUser());
          ps.setString(5, user.getRut());
          ps.execute();
          return true;
      } catch (SQLException e){
          return false;
      }
    }
    public boolean buscar(Usuario user){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Usuarios WHERE RUT=?  ";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, user.getRut());
          rs = ps.executeQuery();
          if (!rs.absolute(1)) {
              System.out.println("Usuario no encontrado");
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
      String sql = "SELECT * FROM Usuarios";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return null;
      }
    }

     public boolean iniciarSesion(Usuario user){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Usuarios WHERE Usuario=? AND Contraseña=? AND Estado=? ";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, user.getUser());
          ps.setString(2, user.getContraseña());
          ps.setBoolean(3, true);
          rs = ps.executeQuery();
          if (!rs.absolute(1)) {
              System.out.println("Usuario no encontrado");
            return false; 
          }
          return true;
      } catch (SQLException e){
          return false;
      }
     }
  
}
