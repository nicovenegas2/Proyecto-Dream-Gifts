/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import static Modelo.Conexion.conn;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author ludwig
 */
public class ConsultaFactura {
    public boolean registrar(Facturas fact){
      PreparedStatement ps = null;
      String sql = "INSERT INTO facturas (ID_Proveedor, monto_factura, fecha_factura) VALUES(?,?,?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1, fact.getID_Proveedor());
          ps.setInt(2, fact.getMonto_factura());
          ps.setDate(3, (Date) fact.getFecha_factura());
          ps.execute();
          System.out.println("registrado");
          return true;
      } catch (SQLException e){
          System.out.println(e);
          return false;
      }
    }
    public boolean modificar(Facturas fact){
      PreparedStatement ps = null;
      String sql = "UPDATE facturas SET ID_Proveedor=?, monto_factura=?, fecha_factura=? WHERE id_factura=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1, fact.getID_Proveedor());
          ps.setInt(2, fact.getMonto_factura());
          ps.setDate(3, (Date) fact.getFecha_factura());
          ps.execute();
          return true;
      } catch (SQLException e){
          return false;
      }
    }
    public boolean buscar(Facturas fact){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM facturas WHERE id_factura=?  ";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1, fact.getId_factura());
          rs = ps.executeQuery();
          if (!rs.absolute(1)) {
              System.out.println("facturas no encontrado");
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
      String sql = "SELECT * FROM facturas";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return null;
      }
    }


}
