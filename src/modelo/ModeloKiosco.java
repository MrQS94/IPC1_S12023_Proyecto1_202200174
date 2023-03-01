/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author queza
 */
public class ModeloKiosco {

    private String nombre;
    private String codigoKiosco;
    private String codigoRegion;

    public ModeloKiosco(String nombre, String codigoRegion, String codigoKiosco) {
        this.nombre = nombre;
        this.codigoKiosco = codigoKiosco;
        this.codigoRegion = codigoRegion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigoKiosco() {
        return codigoKiosco;
    }

    public String getCodigoRegion() {
        return codigoRegion;
    }

}
