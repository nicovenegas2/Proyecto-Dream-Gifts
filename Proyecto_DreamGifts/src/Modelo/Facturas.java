/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author lespinosa
 */
public class Facturas {
    int id_factura;
    int ID_Proveedor;
    int monto_factura;
    Date fecha_factura;

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getID_Proveedor() {
        return ID_Proveedor;
    }

    public void setID_Proveedor(int ID_Proveedor) {
        this.ID_Proveedor = ID_Proveedor;
    }

    public int getMonto_factura() {
        return monto_factura;
    }

    public void setMonto_factura(int monto_factura) {
        this.monto_factura = monto_factura;
    }

   

    public Date getFecha_factura() {
        return fecha_factura;
    }

    public void setFecha_factura(Date fecha_factura) {
        this.fecha_factura = fecha_factura;
    }
    
    
}
