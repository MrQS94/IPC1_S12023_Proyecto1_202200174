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
public class ModeloKiosco {

    private String nombre;
    private String codigoKiosco;
    private String codigoRegion;
    private List<ModeloKiosco> list = new ArrayList();

    public ModeloKiosco() {

    }

    public ModeloKiosco(String nombre, String codigoKiosco, String codigoRegion) {
        this.nombre = nombre;
        this.codigoKiosco = codigoKiosco;
        this.codigoRegion = codigoRegion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCodigoKiosco() {
        return codigoKiosco;
    }

    public void setCodigoKiosco(String codigoKiosco) {
        this.codigoKiosco = codigoKiosco;
    }

    public String getCodigoRegion() {
        return codigoRegion;
    }

    public void setCodigoRegion(String codigoRegion) {
        this.codigoRegion = codigoRegion;
    }

    public List<ModeloKiosco> getList() {
        return list;
    }

    public void setList(List<ModeloKiosco> list) {
        this.list = list;
    }
}
