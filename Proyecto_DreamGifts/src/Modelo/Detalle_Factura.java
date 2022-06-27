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
public class Detalle_Factura {
    int id_factura_detalle;
    int id_factura;
    int id_articulo;
    int cantidad;
    int valor;

    public int getId_factura_detalle() {
        return id_factura_detalle;
    }

    public void setId_factura_detalle(int id_factura_detalle) {
        this.id_factura_detalle = id_factura_detalle;
    }

    public int getId_factura() {
        return id_factura;
    }

    public void setId_factura(int id_factura) {
        this.id_factura = id_factura;
    }

    public int getId_articulo() {
        return id_articulo;
    }

    public void setId_articulo(int id_articulo) {
        this.id_articulo = id_articulo;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }
    
    
}
