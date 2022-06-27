package Modelo;

import static Modelo.Conexion.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaCategoria {
    
    public boolean registrar(Categoria cat){
    
      PreparedStatement ps = null;
      String sql = "INSERT INTO Categoria_Articulo (Nombre, cod_categoria,estado, descripcion) VALUES(?,?,?,?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, cat.getNombre());
          ps.setString(2, cat.getCodigo());
          ps.setBoolean(3, cat.getEstado());
          ps.setString(4, cat.getDescripcion());
          ps.execute();
          System.out.println("registrado");
          return true;
      } catch (SQLException e){
          System.out.println(e);
          return false;
      }
    }

    public boolean modificar(Categoria cat){
    
      PreparedStatement ps = null;
      String sql = "UPDATE Categoria_Articulo SET Nombre=?, estado=? WHERE cod_categoria=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, cat.getNombre());
          ps.setBoolean(2, cat.getEstado());
          ps.setString(3, cat.getCodigo());
          ps.execute();
          return true;
      } catch (SQLException e){
          return false;
      }
    }
    public boolean buscar(Categoria cat){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Categoria_Articulo WHERE cod_categoria=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, cat.getCodigo());
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
      String sql = "SELECT * FROM Categoria_Articulo";
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
      String sql = "SELECT * FROM Categoria_Articulo WHERE estado=1";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return null;
      }
     }
     public String buscarNamePorId (int id){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String name;
        String sql = "SELECT * FROM Categoria_Articulo where category_id=?  ";
        try {
            ps = conn.prepareStatement (sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("Categoria no encontrado (buscarNamePorId)");
              return "Categoria No Encontrado (buscarNamePorId)"; 
            }else{
                System.out.println("Categoria encontrado (buscarNamePorId)");
                name = rs.getString(2);
                return name;
            }
            
        } catch (SQLException e) {
           System.out.println(e);
            return null;
        }
    
}
     
     public int buscarIdPorNombre(String nombre) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id;
        String sql = "SELECT * FROM Categoria_Articulo WHERE nombre=?";
        try {
            ps =  conn.prepareStatement(sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("ID de artículo no encontrado");
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
     
    
}
