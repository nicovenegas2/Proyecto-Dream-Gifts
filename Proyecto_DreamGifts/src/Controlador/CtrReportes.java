package Controlador;

import DreamsGifts.Reportes;
import Modelo.ConsultaReportes;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class CtrReportes implements ActionListener{
    Reportes repo;
    ConsultaReportes conRep;
    
//    metodo constructor
    public CtrReportes(){
        repo = new Reportes();
        conRep = new ConsultaReportes();
        this.iniciarInformeVentas();
        
    }
    
    
//    Metodo para hacer visible la pestaña de reportes
    public void iniciar(){
        if (!repo.isVisible()) {
            repo.setVisible(true);
        }
    }
   
   public void borrarTabla(JTable tabla){
       DefaultTableModel rm = (DefaultTableModel) tabla.getModel();
       while (rm.getRowCount() > 0){
           rm.removeRow(0);
       }
   }
    
    
    
/*   Seccion Informe de Ventas  */

    public void iniciarInformeVentas(){
        repo.informeVentasBuscar.addActionListener(this);
    }
    
    public void informeVentasBuscar(){
        Date inicio = repo.informeVentasDesdeDate.getDate();
        Date fin = repo.informeVentasHastaDate.getDate();
        String rut = repo.informeVentasRut.getText() + repo.informeVentasDigitoV.getText();
        this.borrarTabla(repo.informeVentasTable);
        ResultSet rs = conRep.buscarTodos(inicio, fin, rut);
        Object[] row;
        row = new Object[7];
        DefaultTableModel rm = (DefaultTableModel) repo.informeVentasTable.getModel();
        System.out.println();
        try {
            while (rs.next()){
                row[0] = rs.getInt(1);
                row[1] = rs.getString("RUT");
                row[2] = rs.getString("CNombre");
                row[3] = rs.getDate("fecha_compra");
                row[4] = rs.getDate("fecha_entrega");
                row[5] = rs.getString("PNombre");
                row[6] = rs.getInt("precio");
                rm.addRow(row);  
            }
            } catch (SQLException ex) {
        }
    }
    
    
    /*  Seccion Informe de Inventario*/
    
    
    

//  Adicion de eventos a los objetos añadidos
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == repo.informeVentasBuscar) {
            System.out.println("intentando buscar");
            this.informeVentasBuscar();
            
        }
    }
    
    
}
