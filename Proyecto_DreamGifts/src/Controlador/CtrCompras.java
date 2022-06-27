/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controlador;

import DreamsGifts.Compras;
import Modelo.ConsultaArticulo;
import Modelo.ConsultaDetalleFact;
import Modelo.ConsultaDetalleOrdenCompra;
import Modelo.ConsultaFactura;
import Modelo.ConsultaOrdenCompra;
import Modelo.ConsultaProveedor;
import Modelo.DetalleOrdenCompra;
import Modelo.OrdenCompra;
//import static com.sun.xml.internal.fastinfoset.alphabet.BuiltInRestrictedAlphabets.table;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.awt.event.ItemEvent;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author ludwig
 */
public class CtrCompras implements ActionListener {
    private static Compras compra = new Compras();
    private static ConsultaProveedor conprov = new ConsultaProveedor();
    private static ConsultaArticulo conart = new ConsultaArticulo();
    private static ConsultaDetalleFact condetf = new ConsultaDetalleFact();
    private static ConsultaFactura confact = new ConsultaFactura();
    private static ConsultaDetalleOrdenCompra condetcomp = new ConsultaDetalleOrdenCompra();
    private static ConsultaOrdenCompra conordcomp = new ConsultaOrdenCompra();
    
    

     public void iniciar(){
        if (!compra.isVisible()){
            compra.setVisible(true);
        }
    }
    
    public CtrCompras() throws SQLException {
      compra = new Compras();
      this.iniciarCompras();
      this.actualizarComboBoxProveedor();
//      this.ListarArticulos();
//      this.actualizarTablaPedidosComp();
      
      
   }
     public void iniciarCompras(){
       compra.SavePedid.addActionListener(this);
       compra.CancelPedid.addActionListener(this);
       compra.AddPedid.addActionListener(this);
       compra.RemovPedid.addActionListener(this);
       compra.SearchRegFact.addActionListener(this);
       compra.CancelRegComp.addActionListener(this);
       compra.SaveRegComp.addActionListener(this);
       compra.CancelDetailComp.addActionListener(this);
       compra.SaveDeailtComp.addActionListener(this);
       compra.SearchRevFact.addActionListener(this);
       compra.CancelRevFact.addActionListener(this);
       compra.SaveRevFact.addActionListener(this);
       compra.EditRevFact.addActionListener(this);
//       compra.SaveOC.addActionListener(this);
//       this.actualizarTablaPedidosComp();
//       this.actualizarTablaDetailFact();
//       this.actualizarTablaFactRev();
//       this.actualizarTablaRevFact();
   }
     
    public void borrarTabla(JTable tabla){
       DefaultTableModel rm = (DefaultTableModel) tabla.getModel();
       while (rm.getRowCount() > 0){
           rm.removeRow(0);
       }
   }
   // Inicio CRUD Solicitud Orden de Compra 
    
//    public void iniciarOrdenCompra() throws SQLException{
//       this.iniciarCompras();
//       compra.SavePedid.addActionListener(this);
//       compra.CancelPedid.addActionListener(this);
//       compra.SaveOrde.addActionListener(this);
//       compra.AddPedid.addActionListener(this);
//       compra.RemovPedid.addActionListener(this);
//       
//       this.actualizarTablaPedidosComp();
//       this.ListarArticulos();
//            
//   }
   public void agregarOrdenCompras(){
       OrdenCompra compras = new OrdenCompra();
       DetalleOrdenCompra detordcomp = new DetalleOrdenCompra();
       int NumberOrder;
       DefaultComboBoxModel CbProveedor = (DefaultComboBoxModel) compra.VendorSoliComp.getModel();
       compras.setId_proveedor((int) conprov.buscarIdPorName((String) CbProveedor.getSelectedItem()));
       compras.setFecha_orden(compra.FechaPed.getDate());
       System.out.println(CbProveedor.getSelectedItem());
        NumberOrder = conordcomp.registrar(compras);
        compra.NumberOrder.setText(Integer.toString(NumberOrder));
        conordcomp.registrar(compras);
        

    }
   
   public void AgregarDetalleCompra(){
       OrdenCompra compras = new OrdenCompra();
       DetalleOrdenCompra detordcomp = new DetalleOrdenCompra();
       int NumberOrder;
       int Valor;
       NumberOrder = Integer.parseInt(compra.NumberOrder.getText());
       detordcomp.setIdDetalle_Orden_Compra(NumberOrder);
       DefaultTableModel table = (DefaultTableModel) compra.TablaArticPed.getModel();
       compras.setIdOrden_Compra(NumberOrder);
       
       if (conordcomp.buscar(compras)) {
           condetcomp.borrarDetalle(NumberOrder);
       } 
       
       for (int i = 0; i < table.getRowCount(); i++) {
            detordcomp.setIDArticutlo((int) table.getValueAt(i, 0));
            detordcomp.setOC_cantidad((int) table.getValueAt(i, 2));
            Valor = ((int) table.getValueAt(i, 3));
            detordcomp.setOC_Valor(Valor * detordcomp.getOC_cantidad());
            condetcomp.registrar(detordcomp);
            
        }
   }
   
   public void ListarArticulos() {
        DefaultListModel DetailArtic1 = new DefaultListModel();
        ResultSet rs = conart.llamarActivosXProveedor();
        try {
            while(rs.next()) {
                DetailArtic1.addElement(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        compra.DetailArtic1.setModel(DetailArtic1);
        
   }
   
   public void AgregarArticulo(){
       DefaultTableModel tb = (DefaultTableModel) compra.TablaArticPed.getModel();
       String nombreAr = compra.DetailArtic1.getSelectedValue();
       int cantidad = Integer.parseInt(compra.CantArtic.getText());
       Object[] row = new Object[4];
       int pos =this.existeTabla(compra.TablaArticPed, nombreAr, 1);
       if (nombreAr != null) {
          if (pos == -1) {
                row[0] = conart.buscarIdPorNombre(nombreAr);
                row[1] = nombreAr;
                row[2] = cantidad;
                row[3] = conart.buscarPrecio(nombreAr);
                System.out.println(row[2]);
                tb.addRow(row);
            } else {
                int cantidadAct = (int) tb.getValueAt(pos, 2);
                tb.setValueAt(cantidadAct + cantidad, pos, 2);
            } 
       }
   }
   
   public void actualizarTablaPedidosComp(){
        this.borrarTabla(compra.TablaPedidosComp);
        ResultSet rs = condetcomp.TablaDetalleOrdenCompra();
        Object[] row;
        row = new Object[4];
        DefaultTableModel rm = (DefaultTableModel) compra.TablaPedidosComp.getModel();
        try {
            while (rs.next()){
                
                row[0] = rs.getString("Nro de Pedido");
                row[1] = rs.getString("Fecha de Pedido");
                row[2] = rs.getString("Cantidad de Articulo");
                row[3] = rs.getString("Monto Pedido");

                rm.addRow(row);  
            }
            } catch (SQLException ex) {
                System.out.println(ex);
        }
    }
   
    public void actualizarComboBoxProveedor() throws SQLException{
       DefaultComboBoxModel cbModel = (DefaultComboBoxModel) compra.VendorSoliComp.getModel();
       cbModel.removeAllElements();
       ResultSet rs = conprov.llamarActivos();
       while (rs.next()){
           cbModel.addElement(rs.getString(2));
       }       
   }
   
      
    
   public int existeTabla(JTable tabla,Object valor , int columna){
       DefaultTableModel tb = (DefaultTableModel) tabla.getModel();
       if (valor != null) {
            for(int i=0; i < tb.getRowCount(); i++) {
                 if (valor.equals(tb.getValueAt(i, columna).toString())) {
                     return i;
                }
            }
       }    
       return -1;
   } 
   
   public void QuitarArticulo() {
       DefaultTableModel tb = (DefaultTableModel) compra.TablaArticPed.getModel();
       String nombreAr = compra.DetailArtic1.getSelectedValue();
       int cantidad = Integer.parseInt(compra.CantArtic.getText());
       int pos = this.existeTabla(compra.TablaArticPed, nombreAr, 1);
       if (pos != -1) {
            int cantidadAct = (int) tb.getValueAt(pos, 2);
            if (cantidadAct - cantidad > 0) {
                tb.setValueAt(cantidadAct - cantidad, pos, 2);
           } else {
                tb.removeRow(pos);
            }
       }
   }
   
   //FIN CRUD Solicitud de Compra
   
//      public void actualizarTablaDetailFact(){
//        this.borrarTabla(compra.TablaDetailFact);
//        ResultSet rs = condetf.DetailFact();
//        Object[] row;
//        row = new Object[10];
//        DefaultTableModel rm = (DefaultTableModel) compra.TablaDetailFact.getModel();
//        try {
//            while (rs.next()){
//                
//                row[0] = rs.getString("Orden de Venta");
//                row[1] = rs.getString("Nombre de Cliente");
//                row[2] = rs.getString("Fecha de Entrega");
//                row[3] = rs.getString("Bloque Horario");
//                row[4] = rs.getString("Comuna");
//                row[5] = rs.getString("Direccion de Entrega");
//                row[6] = rs.getString("Nro de Contacto");
//                row[7] = rs.getString("Banco");
//                row[8] = rs.getString("Codigo_TRX");
//                row[9] = rs.getString("Estado");
//
//                rm.addRow(row);  
//            }
//            } catch (SQLException ex) {
//                System.out.println(ex);
//        }
//    }
//      
//       public void actualizarTablaFactRev(){
//        this.borrarTabla(compra.TablaFactRev);
//        ResultSet rs = condetf.listadoFact();
//        Object[] row;
//        row = new Object[10];
//        DefaultTableModel rm = (DefaultTableModel) compra.TablaFactRev.getModel();
//        try {
//            while (rs.next()){
//                
//                row[0] = rs.getString("Orden de Venta");
//                row[1] = rs.getString("Nombre de Cliente");
//                row[2] = rs.getString("Fecha de Entrega");
//                row[3] = rs.getString("Bloque Horario");
//                row[4] = rs.getString("Comuna");
//                row[5] = rs.getString("Direccion de Entrega");
//                row[6] = rs.getString("Nro de Contacto");
//                row[7] = rs.getString("Banco");
//                row[8] = rs.getString("Codigo_TRX");
//                row[9] = rs.getString("Estado");
//
//                rm.addRow(row);  
//            }
//            } catch (SQLException ex) {
//                System.out.println(ex);
//        }
//    }
//       public void actualizarTablaRevFact(){
//        this.borrarTabla(compra.TablaRevFact);
//        ResultSet rs = condetf.listadoFact();
//        Object[] row;
//        row = new Object[10];
//        DefaultTableModel rm = (DefaultTableModel) compra.TablaRevFact.getModel();
//        try {
//            while (rs.next()){
//                
//                row[0] = rs.getString("Orden de Venta");
//                row[1] = rs.getString("Nombre de Cliente");
//                row[2] = rs.getString("Fecha de Entrega");
//                row[3] = rs.getString("Bloque Horario");
//                row[4] = rs.getString("Comuna");
//                row[5] = rs.getString("Direccion de Entrega");
//                row[6] = rs.getString("Nro de Contacto");
//                row[7] = rs.getString("Banco");
//                row[8] = rs.getString("Codigo_TRX");
//                row[9] = rs.getString("Estado");
//
//                rm.addRow(row);  
//            }
//            } catch (SQLException ex) {
//                System.out.println(ex);
//        }
//    }
   
  
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == compra.SavePedid) {
            System.out.println("intentando registrar Solicitud de Compra");
            this.AgregarDetalleCompra();
            this.actualizarTablaPedidosComp();
        }
        if (e.getSource() == compra.SaveOrde) {
            System.out.println("intentando agregar");
            this.agregarOrdenCompras();
            this.actualizarTablaPedidosComp();
        }
//        if(e.getSource() == inven.proveedoresSave){
//            this.agregarProveedor();
//            this.actualizarTablaProveedor();
//        }
        if(e.getSource() == compra.AddPedid) {
            this.AgregarArticulo();
        }
        if (e.getSource() == compra.RemovPedid) {
            this.QuitarArticulo();
        }
//        if (e.getSource() == compra.SearchRegFact) {
//            this.packBuscar();
//        }
    }
    
}
