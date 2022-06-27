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
public class ConsultaDetalleOrdenCompra {
    public boolean registrar(DetalleOrdenCompra DetailOrdComp){
      PreparedStatement ps = null;
      String sql = "INSERT INTO Detalle_Orden_Compra (IDArticulo, OC_cantidad, OC_Valor, IdOrdenCompra) VALUES(?,?,?,?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1,DetailOrdComp.getIDArticutlo());
          ps.setInt(2, DetailOrdComp.getOC_cantidad());
          ps.setInt(3, DetailOrdComp.getOC_Valor());
          ps.setInt(4, DetailOrdComp.getIdOrdenCompra());
          ps.execute();
          System.out.println("registrado");
          return true;
      } catch (SQLException e){
          System.out.println(e);
          return false;
      }
    }
    public boolean modificar(DetalleOrdenCompra DetailOrdComp){
      PreparedStatement ps = null;
      String sql = "UPDATE Detalle_Orden_Compra SET IDArticulo=?, OC_cantidad=?, OC_Valor=? WHERE idOrdenCompra=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1, DetailOrdComp.getIDArticutlo());
          ps.setInt(2, DetailOrdComp.getOC_cantidad());
          ps.setInt(3, DetailOrdComp.getOC_Valor());
          ps.execute();
          return true;
      } catch (SQLException e){
          return false;
      }
    }
    public boolean buscar(DetalleOrdenCompra DetailOrdComp){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM Detalle_Orden_Compra WHERE idDetalle_Orden_Compra=?  ";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1,DetailOrdComp.getIdDetalle_Orden_Compra());
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
      String sql = "SELECT * FROM Detalle_Orden_Compra";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return null;
      }
    }
    
    public ResultSet TablaDetalleOrdenCompra(){
       PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT Orden_Compra.idOrden_Compra AS \"Nro Factura\", Articulos.nombre AS \"Articulo\", Detalle_Orden_Compra.OC_cantidad AS \"Cantidad\", Detalle_Orden_Compra.OC_Valor AS \"Valor\" FROM Orden_Compra INNER JOIN Articulos INNER JOIN Detalle_Orden_Compra";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return null;
      } 
    }
    
    public void borrarDetalle(int idOC) {
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "DELETE FROM Orden_Compra WHERE idOrden_Compra = ?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1, idOC);
          ps.execute();
      } catch (SQLException e){
          System.out.println(e);
      }
     }

//    public void registrar(Compras compras) {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
//    }

    }
