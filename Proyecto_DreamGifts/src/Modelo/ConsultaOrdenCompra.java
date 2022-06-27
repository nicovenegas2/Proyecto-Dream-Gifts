/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import DreamsGifts.Compras;
import static Modelo.Conexion.conn;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author lespinosa
 */
public class ConsultaOrdenCompra {
      public int registrar(OrdenCompra OrdComp){
      PreparedStatement ps = null;
      java.sql.Date fechaorden;
      fechaorden = new java.sql.Date(OrdComp.getFecha_orden().getTime());
      String sql = "INSERT INTO Orden_Compra (id_proveedor, fecha_orden ) VALUES(?,?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1,OrdComp.getId_proveedor());
          ps.setDate(2,fechaorden);
          ps.execute();
          System.out.println("registrado");
          return this.mostrarOrdenCompra(OrdComp);
      } catch (SQLException e){
          System.out.println(e);
          return -1;
      }
    }
      
    public int mostrarOrdenCompra (OrdenCompra OrdComp) {
        PreparedStatement ps = null;
        ResultSet rs = null;
        java.sql.Date fechaorden;
        fechaorden = new java.sql.Date(OrdComp.getFecha_orden().getTime());
        int id;
        String sql = "SELECT * FROM Orden_Compra where id_proveedor=? and fecha_orden=?";
        try {
            ps =  conn.prepareStatement(sql);
            ps.setInt(1,OrdComp.getId_proveedor());
            ps.setDate(2,fechaorden);
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
    
    public boolean modificar(OrdenCompra OrdComp){
      PreparedStatement ps = null;
      String sql = "UPDATE Orden_Compra SET id_proveedor=?, fecha_orden=?  WHERE idOrden_Compra=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1, OrdComp.getId_proveedor());
          ps.setDate(2, (Date) OrdComp.getFecha_orden());
          ps.execute();
          return true;
      } catch (SQLException e){
          return false;
      }
    }
    public boolean buscar(OrdenCompra OrdComp){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Orden_Compra WHERE idOrden_Compra=?  ";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1,OrdComp.getIdOrden_Compra());
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
      String sql = "SELECT * FROM Orden_Compra";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return null;
      }
    }

//    public boolean buscar(Compras compras) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public void registrar(Compras compras) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }
//
//    public boolean modificar(Compras compras) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

}
