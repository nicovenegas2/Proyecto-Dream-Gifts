package Modelo;

import java.util.Date;



public class Venta {
    private int id;
    private int idCliente;
    private int rrss;
    private int idPack;
    private int idBanco;
    private String EstadoPago;
    private int monto;
    private int idcomuna;
    private Date FechaCompra;
    private String direccion;
    private String receptor;
    private String contactoReceptor;
    private String codigoTransaccion;
    private String EstadoDeOrden;
    private int idBoleta;
    private Date FechaEntrega;
    private String BloqueHorario;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getRrss() {
        return rrss;
    }

    public void setRrss(int rrss) {
        this.rrss = rrss;
    }

    public int getIdPack() {
        return idPack;
    }

    public void setIdPack(int idPack) {
        this.idPack = idPack;
    }

    public int getIdBanco() {
        return idBanco;
    }

    public void setIdBanco(int idBanco) {
        this.idBanco = idBanco;
    }

    public String getEstadoPago() {
        return EstadoPago;
    }

    public void setEstadoPago(String EstadoPago) {
        this.EstadoPago = EstadoPago;
    }

   
    public int getMonto() {
        return monto;
    }

    public void setMonto(int monto) {
        this.monto = monto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getReceptor() {
        return receptor;
    }

    public void setReceptor(String receptor) {
        this.receptor = receptor;
    }

    public String getContactoReceptor() {
        return contactoReceptor;
    }

    public void setContactoReceptor(String contactoReceptor) {
        this.contactoReceptor = contactoReceptor;
    }

    public String getCodigoTransaccion() {
        return codigoTransaccion;
    }

    public void setCodigoTransaccion(String codigoTransaccion) {
        this.codigoTransaccion = codigoTransaccion;
    }

    public int getIdBoleta() {
        return idBoleta;
    }

    public void setIdBoleta(int idBoleta) {
        this.idBoleta = idBoleta;
    }

//    public void setRut(String text) {
  //      throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    //}

    public int getIdcomuna() {
        return idcomuna;
    }

    public void setIdcomuna(int idcomuna) {
        this.idcomuna = idcomuna;
    }

    public Date getFechaCompra() {
        return FechaCompra;
    }

    public void setFechaCompra(Date FechaCompra) {
        this.FechaCompra = FechaCompra;
    }

    public String getEstadoDeOrden() {
        return EstadoDeOrden;
    }

    public void setEstadoDeOrden(String EstadoDeOrden) {
        this.EstadoDeOrden = EstadoDeOrden;
    }

    public Date getFechaEntrega() {
        return FechaEntrega;
    }

    public void setFechaEntrega(Date FechaEntrega) {
        this.FechaEntrega = FechaEntrega;
    }

    public String getBloqueHorario() {
        return BloqueHorario;
    }

    public void setBloqueHorario(String BloqueHorario) {
        this.BloqueHorario = BloqueHorario;
    }
    
    
}
