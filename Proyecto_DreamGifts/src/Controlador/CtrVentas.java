/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DreamsGifts.Ventas;
import Modelo.Venta;
import Modelo.ConsultaVentas;
import Modelo.ConsultaCliente;
import Modelo.ConsultaComuna;
import Modelo.ConsultaPack;
import Modelo.ConsultaRedSocial;
import Modelo.ConsultasBanco;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author lespinosa
 */
public class CtrVentas implements ActionListener {
    private static Ventas venta;
    private static ConsultaCliente conCliente = new ConsultaCliente();
    private static ConsultaVentas conventas = new ConsultaVentas();
    private static ConsultaRedSocial conrrss = new ConsultaRedSocial();
    private static ConsultaComuna concomu = new ConsultaComuna();
    private static ConsultasBanco conban = new ConsultasBanco();
    private static ConsultaPack conpack = new ConsultaPack();
    private static ConsultaComuna concom = new ConsultaComuna();
    
    public void iniciar(){
        if (!venta.isVisible()){
            venta.setVisible(true);
        }
    }
/*       inicio CRUD Ventas        */

   public void iniciarVentas(){
       venta.SaveVenta.addActionListener(this);
       venta.CancelVenta.addActionListener(this);
       venta.SearchClient.addActionListener(this);
       venta.SearchClient1.addActionListener(this);
       venta.PackVenta.addItemListener(this::comboBoxitemStateChanged);
       this.actualizarTablaVentas();
   }
   
    public CtrVentas() throws SQLException {
      venta = new Ventas();
      this.iniciarVentas();
      this.actualizarComboBoxComuna();
      this.actualizarComboBoxBanco();
      this.actualizarComboBoxPack();
      this.actualizarComboBoxRRSS();
      this.actualizarComboBoxComunaHist();
      this.actualizarComboBoxBancoHist();
      
      
   }
    
    public String ObtenerRUT () {
      String rutobtn;
        rutobtn = (venta.rutvent.getText()+venta.dvclient.getText());
      String rutclient = rutobtn;
      System.out.println(rutclient);
      return rutclient;
  }
    
    public String ObtenerRUTHist () {
      String rutobtn2;
         rutobtn2 = (venta.rutventhis.getText()+venta.dvventhist.getText());
      String rutclient2 = rutobtn2;
      System.out.println(rutclient2);
      return rutclient2;
  }
    
    public void ObtenName (){
        String NameClient;
        NameClient = conCliente.buscarNamePorRut(this.ObtenerRUT());
        venta.VNameClient.setText(NameClient);            
    }
    
    public void ObtenName2 (){
        String NameClient2;
        NameClient2 = conCliente.buscarNamePorRut(this.ObtenerRUTHist());
        venta.namehistor.setText(NameClient2);            
    }
    
        
    public void borrarTabla(JTable tabla){
       DefaultTableModel rm = (DefaultTableModel) tabla.getModel();
       while (rm.getRowCount() > 0){
           rm.removeRow(0);
       }
   }
    
   public boolean agregarVentas(){
       Venta vent = new Venta();
       DefaultComboBoxModel CbRrss = (DefaultComboBoxModel) venta.VRedSocialClient.getModel();
       DefaultComboBoxModel CbEstadoDePago = (DefaultComboBoxModel) venta.EstadoDePago.getModel();
       DefaultComboBoxModel CbPack = (DefaultComboBoxModel) venta.PackVenta.getModel();
       DefaultComboBoxModel CbEstadoDeOrden = (DefaultComboBoxModel) venta.EstadoDeOrden.getModel();
       DefaultComboBoxModel CbComuna = (DefaultComboBoxModel) venta.ComunaVent.getModel();
       DefaultComboBoxModel CbBanco = (DefaultComboBoxModel) venta.BancoBox.getModel();
       DefaultComboBoxModel CbBloqHor = (DefaultComboBoxModel) venta.BloqueHorarioVent.getModel();
       vent.setDireccion(venta.VAddrClient.getText());
       vent.setIdCliente(conCliente.buscarIdPorRut(this.ObtenerRUT()));
       vent.setReceptor(venta.VNameRecp.getText());
       vent.setContactoReceptor(venta.VNumberContact.getText());
       vent.setCodigoTransaccion(venta.codigoTransaccion.getText());
       vent.setRrss((int) conrrss.RRSSIdPorNombre((String) CbRrss.getSelectedItem()));
       vent.setIdPack((int) conpack.PackIdPorNombre((String) CbPack.getSelectedItem()));
       vent.setIdBanco((int) conban.BancoIdPorNombre((String) CbBanco.getSelectedItem()));
       vent.setEstadoPago((String) CbEstadoDePago.getSelectedItem());
       vent.setEstadoDeOrden((String) CbEstadoDeOrden.getSelectedItem());
       vent.setIdcomuna((int) concom.ComunaIdPorNombre((String) CbComuna.getSelectedItem()));
       vent.setMonto(Integer.parseInt(venta.ValorVenta.getText()));
       vent.setFechaCompra((Date) venta.FechaCompraVent.getDate());
       vent.setFechaEntrega((Date) venta.FechanEntregaVent.getDate());
       vent.setBloqueHorario((String) CbBloqHor.getSelectedItem().toString());
       venta.rutvent.setText("");
       venta.dvclient.setText("");
       venta.VAddrClient.setText("");
       venta.VNameClient.setText("");
       venta.VNameRecp.setText("");
       venta.VNumberContact.setText("");
       venta.codigoTransaccion.setText("");
       venta.ValorVenta.setText("");
       venta.ValorVenta.setText("");
            System.out.println(vent.getIdBanco());
            System.out.println(CbBanco.getSelectedItem());
         if (!conventas.buscar(vent)) {
             System.out.println("intentando agregar");
             conventas.registrar(vent);
            return true;
        } else{
             System.out.println("a modificar");
             return conventas.modificar(vent);
         }
        
   }
   
   public void actualizarTablaVentas(){
        this.borrarTabla(venta.TablaVentas);
        ResultSet rs = conventas.llamarActualizacionTabla();
        Object[] row;
        row = new Object[10];
        DefaultTableModel rm = (DefaultTableModel) venta.TablaVentas.getModel();
        
        try {
            while (rs.next()){
                
                row[0] = rs.getString("Orden de Venta");
                row[1] = rs.getString("Nombre de Cliente");
                row[2] = rs.getString("Fecha de Entrega");
                row[3] = rs.getString("Bloque Horario");
                row[4] = rs.getString("Comuna");
                row[5] = rs.getString("Direccion de Entrega");
                row[6] = rs.getString("Nro de Contacto");
                row[7] = rs.getString("Banco");
                row[8] = rs.getString("Codigo_TRX");
                row[9] = rs.getString("Estado");

                rm.addRow(row);  
            }
            } catch (SQLException ex) {
                System.out.println(ex);
        }
    }
   
   
   public void actualizarComboBoxComuna() throws SQLException{
       DefaultComboBoxModel cbModel = (DefaultComboBoxModel) venta.ComunaVent.getModel();
      // DefaultComboBoxModel cbModel2 = (DefaultComboBoxModel) venta.ComunaVentHist.getModel();
       cbModel.removeAllElements();
       //cbModel2.removeAllElements();
       ResultSet rs = concom.llamarActivos();
       while (rs.next()){
           cbModel.addElement(rs.getString(2));
           //cbModel2.addElement(rs.getString(2));
       }       
   }
   
   public void actualizarComboBoxComunaHist() throws SQLException{
       DefaultComboBoxModel cbModel = (DefaultComboBoxModel) venta.ComunaVentHist.getModel();
      // DefaultComboBoxModel cbModel2 = (DefaultComboBoxModel) venta.ComunaVentHist.getModel();
       cbModel.removeAllElements();
       //cbModel2.removeAllElements();
       ResultSet rs = concom.llamarActivos();
       while (rs.next()){
           cbModel.addElement(rs.getString(2));
           //cbModel2.addElement(rs.getString(2));
       }       
   }
   
   public void actualizarComboBoxBanco() throws SQLException{
       DefaultComboBoxModel cbModel = (DefaultComboBoxModel) venta.BancoBox.getModel();
      // DefaultComboBoxModel cbModel2 = (DefaultComboBoxModel) venta.BancoBoxHist.getModel();
       cbModel.removeAllElements();
       //cbModel2.removeAllElements();
       ResultSet rs = conban.llamarActivos();
       while (rs.next()){
           cbModel.addElement(rs.getString(2));
          // cbModel2.addElement(rs.getString(2));
       }    
   }
   
    public void actualizarComboBoxBancoHist() throws SQLException{
       DefaultComboBoxModel cbModel = (DefaultComboBoxModel) venta.BancoBoxHist.getModel();
      // DefaultComboBoxModel cbModel2 = (DefaultComboBoxModel) venta.BancoBoxHist.getModel();
       cbModel.removeAllElements();
       //cbModel2.removeAllElements();
       ResultSet rs = conban.llamarActivos();
       while (rs.next()){
           cbModel.addElement(rs.getString(2));
          // cbModel2.addElement(rs.getString(2));
       }    
   }
   
   public void actualizarComboBoxPack() throws SQLException{
       DefaultComboBoxModel cbModel = (DefaultComboBoxModel) venta.PackVenta.getModel();
       cbModel.removeAllElements();
       ResultSet rs = conpack.llamarACtivos();
       while (rs.next()){
           cbModel.addElement(rs.getString(2));
       }  
   }
   
   public void actualizarComboBoxRRSS() throws SQLException{
       DefaultComboBoxModel cbModel = (DefaultComboBoxModel) venta.VRedSocialClient.getModel();
       cbModel.removeAllElements();
       ResultSet rs = conrrss.llamarActivos();
       while (rs.next()){
           cbModel.addElement(rs.getString(2));
       }     
   }
   
   public void actualizarPrecioPack() {
       int monto;
       monto = conpack.buscarPrecioPorNombre(venta.PackVenta.getModel().getSelectedItem().toString());
       venta.ValorVenta.setText(Integer.toString(monto));          
       }
   
   
   /* Fin CRUD Ventas*/   

   public void comboBoxitemStateChanged(ItemEvent e) {
    if (e.getStateChange() == ItemEvent.SELECTED) {
        System.out.println("buscando precio (comboBoxitemStateChanged)");
        this.actualizarPrecioPack();
    }
}
   
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == venta.SaveVenta){
            this.agregarVentas();
            this.actualizarTablaVentas();
        }   
        if(e.getSource() == venta.SaveVentHist){
            this.agregarVentas();
            this.actualizarTablaVentas();
        }   
        if (e.getSource() == venta.SearchClient) {
            System.out.println("buscando rut");
            this.ObtenName();
        }
        if (e.getSource() == venta.SearchClient1) {
            System.out.println("buscando rut");
            this.ObtenName2();
        }

    }
    
}
