/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author queza
 */
public class ModeloFacturacion {

    private String nombreCompleto;
    private String direccion;
    private String nit;
    private String nombreTarjeta;
    private String numeroTarjeta;
    private String fechaVen;
    private String dpi;

    public ModeloFacturacion(String nombreCompleto, String direccion, String nit, String nombreTarjeta, String numeroTarjeta, String fechaVen, String dpi) {
        this.nombreCompleto = nombreCompleto;
        this.direccion = direccion;
        this.nit = nit;
        this.nombreTarjeta = nombreTarjeta;
        this.numeroTarjeta = numeroTarjeta;
        this.fechaVen = fechaVen;
        this.dpi = dpi;
    }

    public String getDpi() {
        return dpi;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getNit() {
        return nit;
    }

    public String getNombreTarjeta() {
        return nombreTarjeta;
    }

    public String getNumeroTarjeta() {
        return numeroTarjeta;
    }

    public String getFechaVen() {
        return fechaVen;
    }

}
