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

    private List<ModeloMunicipios> list;

    public ModeloDepartamentos(String codigoDepart, String region, String nombreDepart) {
        this.codigoDepart = codigoDepart;
        this.region = region;
        this.nombreDepart = nombreDepart;
        list = new ArrayList();
    }

    public String getNombreMunicipios() {
        String s = "";
        for (int i = 0; i < list.size(); i++) {
            s += list.get(i).getNombreMuni();
        }
        return s;
    }

    public void AgregarMunicipios(ModeloMunicipios muni) {
        list.add(muni);
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

    public void setCodigoDepart(String codigoDepart) {
        this.codigoDepart = codigoDepart;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setNombreDepart(String nombreDepart) {
        this.nombreDepart = nombreDepart;
    }

}
