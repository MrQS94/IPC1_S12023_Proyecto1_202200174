/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author queza
 */
public class ModeloNacionalidad {

    private String codigoNac;
    private String nombreNac;
    private double precioNac;

    public ModeloNacionalidad(String codigoNac, String nombreNac, double precioNac) {
        this.codigoNac = codigoNac;
        this.nombreNac = nombreNac;
        this.precioNac = precioNac;
    }

    public String getCodigoNac() {
        return codigoNac;
    }

    public void setCodigoNac(String codigoNac) {
        this.codigoNac = codigoNac;
    }

    public String getNombreNac() {
        return nombreNac;
    }

    public void setNombreNac(String nombreNac) {
        this.nombreNac = nombreNac;
    }

    public double getPrecioNac() {
        return precioNac;
    }

    public void setPrecioNac(double precioNac) {
        this.precioNac = precioNac;
    }
}
