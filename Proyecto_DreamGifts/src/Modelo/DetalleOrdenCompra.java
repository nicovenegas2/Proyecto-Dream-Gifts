/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author lespinosa
 */
public class DetalleOrdenCompra {
    private int idDetalle_Orden_Compra;
    private int IDArticutlo;
    private int OC_cantidad;
    private int OC_Valor;
    private int idOrdenCompra;

    public int getIdDetalle_Orden_Compra() {
        return idDetalle_Orden_Compra;
    }

    public void setIdDetalle_Orden_Compra(int idDetalle_Orden_Compra) {
        this.idDetalle_Orden_Compra = idDetalle_Orden_Compra;
    }

    public int getIDArticutlo() {
        return IDArticutlo;
    }

    public void setIDArticutlo(int IDArticutlo) {
        this.IDArticutlo = IDArticutlo;
    }

    public int getOC_cantidad() {
        return OC_cantidad;
    }

    public void setOC_cantidad(int OC_cantidad) {
        this.OC_cantidad = OC_cantidad;
    }

    public int getOC_Valor() {
        return OC_Valor;
    }

    public void setOC_Valor(int OC_Valor) {
        this.OC_Valor = OC_Valor;
    }

    public int getIdOrdenCompra() {
        return idOrdenCompra;
    }

    public void setIdOrdenCompra(int idOrdenCompra) {
        this.idOrdenCompra = idOrdenCompra;
    }

    public void borrarDetalle(int NumberOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
}
