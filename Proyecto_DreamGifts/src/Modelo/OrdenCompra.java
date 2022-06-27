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
public class OrdenCompra {
   private int idOrden_Compra;
   private int id_proveedor;
   private Date fecha_orden;
   private int idDetalle_Orden_Compra;

    public int getIdOrden_Compra() {
        return idOrden_Compra;
    }

    public void setIdOrden_Compra(int idOrden_Compra) {
        this.idOrden_Compra = idOrden_Compra;
    }

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
    }

    public Date getFecha_orden() {
        return fecha_orden;
    }

    public void setFecha_orden(Date fecha_orden) {
        this.fecha_orden = fecha_orden;
    }

    public int getIdDetalle_Orden_Compra() {
        return idDetalle_Orden_Compra;
    }

    public void setIdDetalle_Orden_Compra(int idDetalle_Orden_Compra) {
        this.idDetalle_Orden_Compra = idDetalle_Orden_Compra;
    }
   
   
}
