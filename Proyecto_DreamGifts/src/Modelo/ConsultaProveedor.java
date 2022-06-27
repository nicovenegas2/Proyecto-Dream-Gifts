package Modelo;

import static Modelo.Conexion.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ConsultaProveedor {
    public boolean registrar(Proveedor prov){
      PreparedStatement ps = null;
      String sql = "INSERT INTO Proveedores (RUT, nombre, Estado, fono, direccion, correo, ciclo) VALUES(?,?,?,?,?,?,?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, prov.getRut());
          ps.setString(2, prov.getNombre());
          ps.setBoolean(3, prov.getEstado());
          ps.setString(4, prov.getFono());
          ps.setString(5, prov.getDireccion());
          ps.setString(6, prov.getCorreo());
          ps.setString(7, prov.getCicloFac());
          ps.execute();
          System.out.println("registrado");
          return true;
      } catch (SQLException e){
          System.out.println(e);
          return false;
      }
    }

    public boolean modificar(Proveedor prov){
      PreparedStatement ps = null;
      String sql = "UPDATE Proveedores SET nombre=?, Estado=?, fono=?, direccion=?, correo=?, ciclo=? WHERE RUT=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, prov.getNombre());
          ps.setBoolean(2, prov.getEstado());
          ps.setString(3, prov.getFono());
          ps.setString(4, prov.getDireccion());
          ps.setString(5, prov.getCorreo());
          ps.setString(6, prov.getCicloFac());
          ps.setString(7, prov.getRut());
          ps.execute();
          return true;
      } catch (SQLException e){
          return false;
      }
    }
    public boolean buscar(Proveedor prov){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Proveedores WHERE RUT=?  ";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setString(1, prov.getRut());
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
      String sql = "SELECT * FROM Proveedores";
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
      String sql = "SELECT * FROM Proveedores WHERE estado=1";
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
        String sql = "SELECT * FROM Proveedores WHERE RUT=?  ";
        try {
            ps =  conn.prepareStatement(sql);
            ps.setString(1, rut);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("Proveedores no encontrado");
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
        String sql = "SELECT ID_Proveedor, Nombre FROM Proveedores where rut=?  ";
        try {
            ps = conn.prepareStatement (sql);
            ps.setString(1, rut);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("Proveedores no encontrado");
              return "Proveedores No Encontrado"; 
            }else{
                System.out.println("Proveedores encontrado");
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
        String sql = "SELECT ID_Proveedor, Nombre FROM Proveedores where ID_Proveedor=?  ";
        try {
            ps = conn.prepareStatement (sql);
            ps.setInt(1, id);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("Proveedores no encontrado");
              return "Proveedores No Encontrado"; 
            }else{
                System.out.println("Proveedores encontrado");
                name = rs.getString(2);
                return name;
            }
            
        } catch (SQLException e) {
           System.out.println(e);
            return null;
        }
    }
    public int buscarIdPorName (String name){
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id;
        String sql = "SELECT ID_Proveedor FROM Proveedores where nombre=?  ";
        try {
            ps = conn.prepareStatement (sql);
            ps.setString(1, name);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("Proveedores no encontrado");
              return -1; 
            }else{
                System.out.println("Proveedores encontrado");
                id = rs.getInt(1);
                return id;
            }
            
        } catch (SQLException e) {
           System.out.println(e);
            return -1;
        }
    }
}
