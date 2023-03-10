/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;


import controlador.ControladorPrincipal;
import vista.Autenticacion;

/**
 *
 * @author queza
 */
public class Main {

    public static void main(String[] args) {
        Autenticacion aut = new Autenticacion();
        ControladorPrincipal control = new ControladorPrincipal(aut);
        aut.setVisible(true);   
    }
}

