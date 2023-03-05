/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author queza
 */
public class ModeloMunicipios {

    private String codigoMuni;
    private String nombreMuni;

    public ModeloMunicipios(String codigoMuni, String nombreMuni) {
        this.codigoMuni = codigoMuni;
        this.nombreMuni = nombreMuni;
    }

    public String getCodigoMuni() {
        return codigoMuni;
    }

    public String getNombreMuni() {
        return nombreMuni;
    }

}
