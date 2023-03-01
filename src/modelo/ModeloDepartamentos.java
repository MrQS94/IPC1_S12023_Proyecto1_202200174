/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author queza
 */
public class ModeloDepartamentos {

    private String codigo;
    private String region;
    private String nombre;
    private String codigoDepart;
    private String nombreDepart;

    public ModeloDepartamentos(String codigo, String region, String nombre, String codigoDepart, String nombreDepart) {
        this.codigo = codigo;
        this.region = region;
        this.nombre = nombre;
        this.codigoDepart = codigoDepart;
        this.nombreDepart = nombreDepart;
    }

    public String getCodigo() {
        return codigo;
    }

    public String getRegion() {
        return region;
    }

    public String getNombre() {
        return nombre;
    }

    public String getCodigoDepart() {
        return codigoDepart;
    }

    public String getNombreDepart() {
        return nombreDepart;
    }

}
