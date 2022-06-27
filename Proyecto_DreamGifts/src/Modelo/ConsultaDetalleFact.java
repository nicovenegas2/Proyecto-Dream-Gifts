/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import DreamsGifts.Compras;
import static Modelo.Conexion.conn;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *
 * @author ludwig
 */
public class ConsultaDetalleFact {
    public boolean registrar(Detalle_Factura DetFact){
      PreparedStatement ps = null;
      String sql = "INSERT INTO factura_detalle (id_factura, id_articulo, cantidad, valor) VALUES(?,?,?,?)";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1, DetFact.getId_factura());
          ps.setInt(2, DetFact.getId_articulo());
          ps.setInt(3, DetFact.getCantidad());
          ps.setInt(4, DetFact.getValor());
          ps.execute();
          System.out.println("registrado");
          return true;
      } catch (SQLException e){
          System.out.println(e);
          return false;
      }
    }
    public boolean modificar(Detalle_Factura DetFact){
      PreparedStatement ps = null;
      String sql = "UPDATE factura_detalle SET id_articulo=?, cantidad=?, valor=? WHERE id_factura_detalle=?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1, DetFact.getId_articulo());
          ps.setInt(2, DetFact.getCantidad());
          ps.setInt(3, DetFact.getValor());
          ps.execute();
          return true;
      } catch (SQLException e){
          return false;
      }
    }
     public boolean buscar(Detalle_Factura DetFact){
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT * FROM factura_detalle WHERE id_factura_detalle=?  ";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1, DetFact.getId_factura_detalle());
          rs = ps.executeQuery();
          if (!rs.absolute(1)) {
              System.out.println("factura_detalle no encontrado");
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
      String sql = "SELECT * FROM factura_detalle";
      try {
          ps =  conn.prepareStatement(sql);
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return null;
      }
    }
    public String buscarArtDetPorId (String DetFact){
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT id_articulo, cantidad, valor FROM factura_detalle id_factura=?  ";
        try {
            ps = conn.prepareStatement (sql);
            ps.setString(1, DetFact);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("factura_detalle no encontrado (buscarArtDetPorId)");
              return "Pack No Encontrado"; 
            }else{
                System.out.println("factura_detalle encontrado (buscarArtDetPorId)");
                DetFact = rs.getString(2);
                return DetFact;
            }
            
        } catch (SQLException e) {
           System.out.println(e);
            return "";
        }
    }
    public int buscarValorPorId (String DettFact){
        PreparedStatement ps = null;
        ResultSet rs = null;
        int precio;
        String sql = "SELECT * FROM factura_detalle WHERE id_factura=?  ";
        try {
            ps = conn.prepareStatement (sql);
            ps.setString(1, DettFact);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("factura_detalle no encontrado (buscarValorPorId)");
              return -1; 
            }else{
                System.out.println("factura_detalle encontrado (buscarValorPorId)");
                precio = rs.getInt(3);
                return precio;
            }
            
        } catch (SQLException e) {
           System.out.println(e);
            return -1;
        }
    }
     public int FactId (String FactId){
        PreparedStatement ps = null;
        ResultSet rs = null;
        int id;
        String sql = "SELECT * FROM factura_detalle WHERE id_factura=?  ";
        try {
            ps =  conn.prepareStatement(sql);
            ps.setString(1, FactId);
            rs = ps.executeQuery();
            rs.next();
            if (!rs.absolute(1)) {
                System.out.println("id_factura no encontrado (FactId)");
              return -1; 
            }else{
                id = Integer.parseInt(rs.getString(1));
                return id;
            }
                
        } catch (SQLException e){
            return -1;
        }
     }
     
     public ResultSet DetailFact(String FactAll) {
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT factura_detalle.id_factura_detalle AS \"Nro de Pedido\", facturas.fecha_factura AS \"Fecha_Pedido\", factura_detalle.cantidad AS \"Cantidad de Articulos\", factura_detalle.valor AS \"Monto Pedido\" FROM factura_detalle LEFT JOIN facturas ON factura_detalle.id_factura=facturas.id_factura;";
      try {
          ps =  conn.prepareStatement(sql);

          ps.setString(1, FactAll);
          System.out.println(sql);          
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return rs;
      }
     }
     
     public ResultSet PedidosComp(String FactAll) {
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "SELECT factura_detalle.id_factura_detalle AS \"Nro de Pedido\", facturas.fecha_factura AS \"Fecha_Pedido\", factura_detalle.cantidad AS \"Cantidad de Articulos\", factura_detalle.valor AS \"Monto Pedido\" FROM factura_detalle LEFT JOIN facturas ON factura_detalle.id_factura=facturas.id_factura;";
      try {
          ps =  conn.prepareStatement(sql);

          ps.setString(1, FactAll);
          System.out.println(sql);          
          rs = ps.executeQuery();
          return rs;
      } catch (SQLException e){
          return rs;
      }
     }
     
     public void borrarDetalle(int FactId) {
      PreparedStatement ps = null;
      ResultSet rs = null;
      String sql = "DELETE FROM factura_detalle WHERE id_factura = ?";
      try {
          ps =  conn.prepareStatement(sql);
          ps.setInt(1, FactId);
          ps.execute();
      } catch (SQLException e){
          System.out.println(e);
      }
     }
}


   
