/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author queza
 */
public class ModeloCotizacion {

    private int noFactura;
    private int codigoPaquete;
    private String guia;
    private String origen;
    private String destino;
    private String nit;
    private String tipoPago;
    private double packageSize;
    private int noPaquetes;
    private double totPagar;
    private String fechaEnvio;
    private String tipoServicio;
    private String dpi;
    private String region;

    public ModeloCotizacion(int noFactura, int codigoPaquete, String guia, String origen,
            String destino, String nit, String tipoPago, double packageSize, int noPaquetes,
            double totPagar, String fechaEnvio, String tipoServicio, String dpi, String region) {
        this.noFactura = noFactura;
        this.codigoPaquete = codigoPaquete;
        this.guia = guia;
        this.origen = origen;
        this.destino = destino;
        this.nit = nit;
        this.tipoPago = tipoPago;
        this.packageSize = packageSize;
        this.noPaquetes = noPaquetes;
        this.totPagar = totPagar;
        this.fechaEnvio = fechaEnvio;
        this.tipoServicio = tipoServicio;
        this.dpi = dpi;
        this.region = region;
    }
    
    public String getRegion() {
        return region;
    }

    public String getDpi() {
        return dpi;
    }

    public ModeloCotizacion(int codigoPaquete) {
        this.codigoPaquete = codigoPaquete;
    }

    public String getGuia() {
        return guia;
    }

    public int getNoFactura() {
        return noFactura;
    }

    public int getCodigoPaquete() {
        return codigoPaquete;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    public String getNit() {
        return nit;
    }

    public String getTipoPago() {
        return tipoPago;
    }

    public double getPackageSize() {
        return packageSize;
    }

    public int getNoPaquetes() {
        return noPaquetes;
    }

    public double getTotPagar() {
        return totPagar;
    }

    public String getFechaEnvio() {
        return fechaEnvio;
    }

    public String getTipoServicio() {
        return tipoServicio;
    }

}
