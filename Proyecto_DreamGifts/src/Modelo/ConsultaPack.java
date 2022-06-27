package Modelo;

import static Modelo.Conexion.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaPack {
    
    public boolean registrar(Pack pack){
    
      PreparedStatement ps = null;
      String sql = "INSERT INTO Pack (nombre, precio, estado, descripcion) VALUES(?,?,?,?)";
      try {
          ps =  conn.prepareStatement(sql);;
          ps.setString(1, pack.getNombre());
          ps.setInt(2, pack.getPrecio());
          ps.setBoolean(3, pack.isEstado());
          ps.setString(4, pack.getDescripcion());
          ps.execute();
          System.out.println("registrado");
          return true;
      } catch (SQLException e){
          System.out.println(e);
          return false;
      }
    }

    public boolean modificar(Pack pack){
    
      PreparedStatement ps = null;
      String sql = "UPDATE Pack SET nombre=?, estado=?, precio=?, descripcion=? WHERE ID_Pack=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, pack.getNombre());
          ps.setBoolean(2, pack.isEstado());
          ps.setInt(3, pack.getPrecio());
          ps.setString(4, pack.getDescripcion());
          ps.setInt(5, pack.getId());
          System.out.println(ps);
          ps.execute();
          return true;
      } catch (SQLException e){
          return false;
      }
    }
    
    
    public boolean buscar(Pack pack){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Pack WHERE nombre=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, pack.getNombre());
          rs = ps.executeQuery();
          if (!rs.absolute(1)) {
              System.out.println("Pack no encontrado (buscar)");
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
      String sql = "SELECT * FROM Pack";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return rs;
      }
     }
     
     public ResultSet llamarACtivos(){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Pack WHERE estado=1";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return rs;
      }
     }

    public boolean agregarArticulo(DetallePack dp, Pack pack){
    
      PreparedStatement ps = null;
      String sql = "INSERT INTO detalle_pack (ID_Pack, ID_Articulo, cantidad) VALUES(?,?,?)";
      dp.setIdPack(pack.getId());
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1, dp.getIdPack());
          ps.setInt(2, dp.getIdArticulo());
          ps.setInt(3, dp.getCantidad());
          ps.execute();
          System.out.println("registrado");
          return true;
      } catch (SQLException e){
          System.out.println(e);
          return false;
      }
    }
     
    public String buscarNamePorId (String pack){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT ID_Pack, nombre, precio FROM Pack ID_Pack=?  ";
        try {
            ps = conn.prepareStatement (sql);
            ps.setString(1, pack);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("Pack no encontrado (buscarNamePorId)");
              return "Pack No Encontrado"; 
            }else{
                System.out.println("Pack encontrado (buscarNamePorId)");
                pack = rs.getString(2);
                return pack;
            }
            
        } catch (SQLException e) {
           System.out.println(e);
            return "";
        }
    }
    public int buscarPrecioPorNombre (String nombre){
        PreparedStatement ps = null;
        ResultSet rs = null;
        int precio;
        String sql = "SELECT * FROM Pack WHERE nombre=?  ";
        try {
            ps = conn.prepareStatement (sql);
            ps.setString(1, nombre);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("Pack no encontrado (buscarPrecioPorNombre)");
              return -1; 
            }else{
                System.out.println("Pack encontrado (buscarPrecioPorNombre)");
                precio = rs.getInt(3);
                return precio;
            }
            
        } catch (SQLException e) {
           System.out.println(e);
            return -1;
        }
    }
     public int PackIdPorNombre (String Pack){
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id;
        String sql = "SELECT * FROM Pack WHERE nombre=?  ";
        try {
            ps =  conn.prepareStatement(sql);
            ps.setString(1, Pack);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("Pack no encontrado (PackIdPorNombre)");
              return -1; 
            }else{
                id = Integer.parseInt(rs.getString(1));
                return id;
            }
                
        } catch (SQLException e){
            return -1;
        }
     }
     
     public ResultSet listadoPack(String nombre) {
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT ar.ID_Articulo, ar.nombre, dp.cantidad, ar.precio, p.descripcion FROM detalle_pack as dp "
              + "INNER JOIN Articulos as ar "
              + "ON dp.ID_Articulo = ar.ID_Articulo "
              + "INNER JOIN Pack as p "
              + "ON dp.ID_Pack = p.ID_Pack "
              + "WHERE p.nombre=?";
      try {
          ps =  conn.prepareStatement(sql);

          ps.setString(1, nombre);
          System.out.println(sql);          
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return rs;
      }
     }
     
     public void borrarDetalle(int idPack) {
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "DELETE FROM detalle_pack WHERE ID_Pack = ?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1, idPack);
          ps.execute();
      } catch (SQLException e){
          System.out.println(e);
      }
     }
}
