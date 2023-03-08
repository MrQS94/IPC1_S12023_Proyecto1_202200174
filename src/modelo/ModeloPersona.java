/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

/**
 *
 * @author queza
 */
public class ModeloPersona {

    private String nombre;
    private String correo;
    private String apellido;
    private String pass;
    private String dpi;
    private String fechaNac;
    private String genero;
    private String nacionalidad;
    private String alias;
    private int telefono;
    private String rol;
    private String kiosco;
    private String fotografia;

    public ModeloPersona() {

    }

    public ModeloPersona(String nombre, String apellido, String correo, String pass,
            String dpi, String fechaNac, String genero, String nacionalidad,
            String alias, int telefono, String rol, String kiosco, String foto) {
        this.nombre = nombre;
        this.correo = correo;
        this.apellido = apellido;
        this.pass = pass;
        this.dpi = dpi;
        this.fechaNac = fechaNac;
        this.genero = genero;
        this.nacionalidad = nacionalidad;
        this.alias = alias;
        this.telefono = telefono;
        this.rol = rol;
        this.kiosco = kiosco;
        this.fotografia = foto;
    }
    
    public ModeloPersona(String nombre, String apellido, String correo, String pass,
            String dpi, String fechaNac, String genero, String nacionalidad,
            String alias, int telefono, String rol, String foto) {
        this.nombre = nombre;
        this.correo = correo;
        this.apellido = apellido;
        this.pass = pass;
        this.dpi = dpi;
        this.fechaNac = fechaNac;
        this.genero = genero;
        this.nacionalidad = nacionalidad;
        this.alias = alias;
        this.telefono = telefono;
        this.rol = rol;
        this.fotografia = foto;
    }

    public String getKiosco() {
        return kiosco;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(String fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getNacionalidad() {
        return nacionalidad;
    }

    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public int getTelefono() {
        return telefono;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

}
