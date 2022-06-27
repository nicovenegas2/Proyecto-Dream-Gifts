package Controlador;
import static Controlador.CtrLogin.lgn;
import DreamsGifts.*;
import Modelo.Articulo;
import Modelo.Categoria;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.table.DefaultTableModel;
import Modelo.ConsultaArticulo;
import Modelo.ConsultaCategoria;

import Modelo.ConsultaPack;
import Modelo.ConsultaProveedor;
import Modelo.DetallePack;
import Modelo.Pack;
import Modelo.Proveedor;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

public class CtrInventario implements ActionListener{
    private static Inventario inven = new Inventario();
    private static ConsultaArticulo invenArt = new ConsultaArticulo();
    private static ConsultaPack invenPack = new ConsultaPack();
    private static ConsultaPack conPack = new ConsultaPack();
    private static ConsultaCategoria conCat = new ConsultaCategoria();
    private static ConsultaProveedor conProv = new ConsultaProveedor();
    private static ConsultaArticulo conAr = new ConsultaArticulo();

    public void iniciar(){
         if (!inven.isVisible()){
             inven.setVisible(true);
         }
     }

     public CtrInventario(){
         this.iniciarPack();
         this.iniciarcategoria();
         this.iniciarProveedores();
         this.iniciarArticulo();
     }
     
     
    public void borrarTabla(JTable tabla){
       DefaultTableModel rm = (DefaultTableModel) tabla.getModel();
       while (rm.getRowCount() > 0){
           rm.removeRow(0);
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
    
//    
      public void iniciarArticulo(){
          inven.ArticuloBtnAdd.addActionListener(this);
          this.actualizarArticulos();
          this.articuloActualizarComboBoxProveedor();
          this.articuloActualizarComboBoxCategoria();

       }
//        /*       inicio CRUD ARTICULOS       */
//
//
   public boolean agregarArticulo(){
       Articulo art = new Articulo();
       art.setNombre(inven.nomTextArticulo.getText());
       art.setProveedor(conProv.buscarIdPorName(inven.proveedorTextArticulo.getModel().getSelectedItem().toString()));
       art.setCategoria(conCat.buscarIdPorNombre(inven.articuloCategoria.getModel().getSelectedItem().toString()));
       art.setCantidad(Integer.parseInt(inven.cantIntArticulo.getText()));
       art.setCosto(Integer.parseInt(inven.costoIntArticulo.getText()));
       art.setEstado(inven.articuloActive.isSelected());
       art.setFecha(inven.txtfecha.getDate());
       
       inven.nomTextArticulo.setText("");       
       inven.cantIntArticulo.setText("");
       inven.costoIntArticulo.setText("");
       
       
       
         if (!invenArt.buscar(art)) {
             System.out.println("intentando agregar");
             invenArt.registrar(art);
            return true;
           } else{
            System.out.println("a modificar");
             return invenArt.modificar(art);
         }
        
   }
   
   public void articuloActualizarComboBoxProveedor() {
       DefaultComboBoxModel cbModel = (DefaultComboBoxModel) inven.proveedorTextArticulo.getModel();
      // DefaultComboBoxModel cbModel2 = (DefaultComboBoxModel) venta.BancoBoxHist.getModel();
       cbModel.removeAllElements();
       //cbModel2.removeAllElements();
        try {
            ResultSet rs = conProv.llamarActivos();
            while (rs.next()){
                cbModel.addElement(rs.getString(2));
               // cbModel2.addElement(rs.getString(2));
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
   }
   public void articuloActualizarComboBoxCategoria() {
       DefaultComboBoxModel cbModel = (DefaultComboBoxModel) inven.articuloCategoria.getModel();
      // DefaultComboBoxModel cbModel2 = (DefaultComboBoxModel) venta.BancoBoxHist.getModel();
       cbModel.removeAllElements();
       //cbModel2.removeAllElements();
        try {
            ResultSet rs = conCat.llamarActivos();
            while (rs.next()){
                cbModel.addElement(rs.getString(2));
               // cbModel2.addElement(rs.getString(2));
            }
        } catch(SQLException e) {
            System.out.println(e);
        }
   }
   
   public void actualizarArticulos(){
        this.borrarTabla(inven.articulosTable);
        ResultSet rs = invenArt.llamarTodos();
        Object[] row;
        row = new Object[7];
        DefaultTableModel rm = (DefaultTableModel) inven.articulosTable.getModel();
        try {
            while (rs.next()){
                row[0] = rs.getString("nombre");
                row[1] = rs.getString("cantidad");
                row[3] = rs.getString("fecha_vencimiento");
                row[2] = rs.getString("precio");
                row[4] = rs.getString("pNombre");
                row[5] = rs.getString("cNombre");
                row[6] = rs.getBoolean("estado");
                rm.addRow(row);  
            }
            } catch (SQLException ex) {
                System.out.println(ex);
        }
    }
   
/*         INICIO CRUD PACK          */
   
   public void iniciarPack(){
    inven.packSave.addActionListener(this);
    inven.packCrearSum.addActionListener(this);
    inven.packCrearRem.addActionListener(this);
    inven.packBuscar.addActionListener(this);
    this.packListarArticulos();
    this.actualizarTablaPack();
    
}
   
   public void packAgregarArticulo(){
       DefaultTableModel tb = (DefaultTableModel) inven.packListado.getModel();
       String nombreAr = inven.packLista.getSelectedValue();
       int cantidad = Integer.parseInt(inven.packCantidad.getText());
       Object[] row = new Object[4];
       int pos =this.existeTabla(inven.packListado, nombreAr, 1);
       if (nombreAr != null) {
          if (pos == -1) {
                row[0] = conAr.buscarIdPorNombre(nombreAr);
                row[1] = nombreAr;
                row[2] = cantidad;
                row[3] = conAr.buscarPrecio(nombreAr);
                System.out.println(row[2]);
                tb.addRow(row);
            } else {
                int cantidadAct = (int) tb.getValueAt(pos, 2);
                tb.setValueAt(cantidadAct + cantidad, pos, 2);
            } 
       }
   }
   
   public void actualizarTablaPack(){
        this.borrarTabla(inven.packTabla);
        ResultSet rs = conPack.llamarTodos();
        Object[] row;
        row = new Object[3];
        DefaultTableModel rm = (DefaultTableModel) inven.packTabla.getModel();
        try {
            while (rs.next()){
                row[0] = rs.getString("nombre");
                row[1] = rs.getInt("precio");
                row[2] = rs.getBoolean("estado");

                rm.addRow(row);  
            }
            } catch (SQLException ex) {
                System.out.println(ex);
        }
   }
   
   
   public void packQuitarArticulo() {
       DefaultTableModel tb = (DefaultTableModel) inven.packListado.getModel();
       String nombreAr = inven.packLista.getSelectedValue();
       int cantidad = Integer.parseInt(inven.packCantidad.getText());
       int pos =this.existeTabla(inven.packListado, nombreAr, 1);
       if (pos != -1) {
            int cantidadAct = (int) tb.getValueAt(pos, 2);
            if (cantidadAct - cantidad > 0) {
                tb.setValueAt(cantidadAct - cantidad, pos, 2);
           } else {
                tb.removeRow(pos);
            }
       }
   }

   
   public void packListarArticulos() {
        DefaultListModel li = new DefaultListModel();
        ResultSet rs = conAr.llamarActivos();
        try {
            while(rs.next()) {
                li.addElement(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            System.out.println(ex);
        }
        inven.packLista.setModel(li);
        
   }

   
   public void agregarPack(){
       Pack pack = new Pack();
       pack.setDescripcion(inven.packDesc.getText());
       pack.setEstado(inven.packEstadoActive.isSelected());
       pack.setNombre(inven.packNombre.getText());
       pack.setPrecio(Integer.parseInt(inven.packValor.getText()));
       DefaultTableModel table = (DefaultTableModel) inven.packListado.getModel();
       DetallePack detPack = new DetallePack();
       if (!conPack.buscar(pack)) {
           if (conPack.registrar(pack)){
                pack.setId(conPack.PackIdPorNombre(pack.getNombre()));
                System.out.println(detPack);
           }
       } else {
           pack.setId(conPack.PackIdPorNombre(pack.getNombre()));
           conPack.modificar(pack);
           conPack.borrarDetalle(pack.getId());
       }
       for (int i = 0; i < table.getRowCount(); i++) {
            detPack.setIdArticulo((int) table.getValueAt(i, 0));
            detPack.setCantidad((int) table.getValueAt(i, 2));
            conPack.agregarArticulo(detPack, pack);
        }
   }
   public void packBuscar(){
       DefaultTableModel rm = (DefaultTableModel) inven.packTabla.getModel();
       int rowIn = inven.packTabla.getSelectedRow();
       ResultSet rs;
       inven.packNombre.setText(rm.getValueAt(rowIn, 0).toString());
       inven.packValor.setText(rm.getValueAt(rowIn, 1).toString());
       if ((Boolean) rm.getValueAt(rowIn,2)) {
           inven.packEstadoActive.doClick();
       } else {
           inven.packEstadoDesactive.doClick();
       }
       try {
           rs = conPack.listadoPack(rm.getValueAt(rowIn, 0).toString());
           DefaultTableModel tablaAr = (DefaultTableModel) inven.packListado.getModel();
           Object[] row = new Object[4];
           this.borrarTabla(inven.packListado);
           while (rs.next()) {
               row[0] = rs.getInt(1);
               row[1] = rs.getString(2);
               row[2] = rs.getInt(3);
               row[3] = rs.getInt(4);
               tablaAr.addRow(row);
               System.out.println(rs.getString(5));
               inven.packDesc.setText(rs.getString(5));
           } 
       } catch(SQLException e) {
           System.out.println("error " + e);
       }
   }
   
   
   
   
   /*         INICIO CRUD CATEGORIA          */
   public void iniciarcategoria(){
       inven.categoriaSave.addActionListener(this);
       this.actualizarTablaProveedor();

   }
   
   public void agregarCategoria(){
       Categoria cat = new Categoria();
       cat.setCodigo(inven.categoriaCodigo.getText());
       cat.setDescripcion(inven.categoriaDesc.getText());
       cat.setNombre(inven.categoriaNombre.getText());
       cat.setEstado(inven.categoriaActive.isSelected());
       if (!conCat.buscar(cat)) {
            if (conCat.registrar(cat)) {
                JOptionPane.showMessageDialog(lgn, "categoria agregada con exito");   
            } else {
                JOptionPane.showMessageDialog(lgn, "hubo un error al agregar la categoria");
            }   
       } else{
           conCat.modificar(cat);
       }
   }
   public void actualizarTablaCategoria(){
        this.borrarTabla(inven.categoriaTable);
        ResultSet rs = conCat.llamarTodos();
        Object[] row;
        row = new Object[3];
        DefaultTableModel rm = (DefaultTableModel) inven.categoriaTable.getModel();
        try {
            while (rs.next()){
                row[0] = rs.getString(3);
                row[1] = rs.getString(2);
                row[2] = rs.getBoolean(4);

                rm.addRow(row);  
            }
            } catch (SQLException ex) {
                System.out.println(ex);
        }
   }
   
   
   
   
   /*         INICIO CRUD Proveedores         */
   
   public void iniciarProveedores(){
       inven.proveedoresSave.addActionListener(this);
       this.actualizarTablaProveedor();
   }
   
   public void actualizarTablaProveedor(){
        this.borrarTabla(inven.proveedoresTable);
        ResultSet rs = conProv.llamarTodos();
        Object[] row;
        row = new Object[7];
        DefaultTableModel rm = (DefaultTableModel) inven.proveedoresTable.getModel();
        try {
            while (rs.next()){
                row[0] = rs.getString("nombre");
                row[1] = rs.getString("RUT");
                row[2] = rs.getString("correo");
                row[3] = rs.getString("fono");
                row[4] = rs.getString("direccion");
                row[5] = rs.getString("ciclo");
                row[6] = rs.getBoolean("estado");


                rm.addRow(row);  
            }
            } catch (SQLException ex) {
                System.out.println(ex);
        }
   }
   public void agregarProveedor() {
    Proveedor prov = new Proveedor();
    prov.setDireccion(inven.proveedoresDireccion.getText());
    prov.setCicloFac(inven.proveedoresCiclo.getText());
    prov.setCorreo(inven.proveedoresCorreo.getText());
    prov.setEstado(inven.proveedoresActive.isSelected());
    prov.setFono(inven.proveedoresTel.getText());
    prov.setNombre(inven.proveedoresNombre.getText());
    prov.setRut(inven.proveedoresRut.getText() + inven.proveedoresRutDv.getText());
       if (!conProv.buscar(prov)) {
           if (conProv.registrar(prov)) {
            JOptionPane.showMessageDialog(lgn, "Proveedor registrado con exito");
            }
            else{
                 JOptionPane.showMessageDialog(lgn, "Ocurrio un error al intentar registrar al proveedor");
        }
       }else {
           conProv.modificar(prov);
       }
        
    }
   
   
    @Override
     public void actionPerformed(ActionEvent e) {
        if (e.getSource() == inven.ArticuloBtnAdd) {
            this.agregarArticulo();
            this.actualizarArticulos();
        }
        if (e.getSource() == inven.packSave) {
            System.out.println("intentando registrar pack");
            this.agregarPack();
            this.actualizarTablaPack();
        }
        if (e.getSource() == inven.categoriaSave) {
            System.out.println("intentando agregar");
            this.agregarCategoria();
            this.actualizarTablaCategoria();
        }
        if(e.getSource() == inven.proveedoresSave){
            this.agregarProveedor();
            this.actualizarTablaProveedor();
        }
        if(e.getSource() == inven.packCrearSum) {
            this.packAgregarArticulo();
        }
        if (e.getSource() == inven.packCrearRem) {
            this.packQuitarArticulo();
        }
        if (e.getSource() == inven.packBuscar) {
            this.packBuscar();
        }
    }
   

}
