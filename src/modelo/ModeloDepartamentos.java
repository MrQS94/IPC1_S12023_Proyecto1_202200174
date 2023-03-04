/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author queza
 */
public class ModeloDepartamentos {

    private String codigoDepart;
    private String region;
    private String nombreDepart;
    private String codigoMuni;
    private String nombreMuni;

    public ModeloDepartamentos(String codigoDepart, String region, String nombreDepart, String codigoMuni, String nombreMuni) {
        this.codigoDepart = codigoDepart;
        this.region = region;
        this.nombreDepart = nombreDepart;
        this.codigoMuni = codigoMuni;
        this.nombreMuni = nombreMuni;
    }

    public String getCodigoDepart() {
        return codigoDepart;
    }

    public String getRegion() {
        return region;
    }

    public String getNombreDepart() {
        return nombreDepart;
    }

    public String getCodigoMuni() {
        return codigoMuni;
    }

    public String getNombreMuni() {
        return nombreMuni;
    }

    public void setCodigoDepart(String codigoDepart) {
        this.codigoDepart = codigoDepart;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setNombreDepart(String nombreDepart) {
        this.nombreDepart = nombreDepart;
    }

    public void setCodigoMuni(String codigoMuni) {
        this.codigoMuni = codigoMuni;
    }

    public void setNombreMuni(String nombreMuni) {
        this.nombreMuni = nombreMuni;
    }

}
