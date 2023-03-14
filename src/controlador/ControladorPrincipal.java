/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import modelo.ModeloCotizacion;
import modelo.ModeloDepartamentos;
import modelo.ModeloFacturacion;
import modelo.ModeloKiosco;
import modelo.ModeloMunicipios;
import modelo.ModeloPersona;
import modelo.ModeloPrecios;
import vista.Autenticacion;
import vista.FormPrincipal;
import vista.RegistroUsuario;

/**
 *
 * @author queza
 */
public class ControladorPrincipal implements ActionListener {

    Autenticacion aut = new Autenticacion();
    RegistroUsuario registro = new RegistroUsuario();

    ModeloPersona modPersona0 = new ModeloPersona("Andres", "Quezada", "ipc1_202200174@ipc1delivery.com", "202200174",
            "3903542010101", "24/12/2002", "Hombre", "Guatemala", "MrQS", 42201602, "adnin", "", "No existe foto");
    ModeloPrecios modPrec1 = new ModeloPrecios("(M) Metropolitana", 25, 35);
    ModeloPrecios modPrec2 = new ModeloPrecios("(NT) Norte", 45.55, 68.50);
    ModeloPrecios modPrec3 = new ModeloPrecios("(NO) Nororiente", 35.48, 58.68);
    ModeloPrecios modPrec4 = new ModeloPrecios("(SO) Suroriente", 32.48, 38.68);
    ModeloPrecios modPrec5 = new ModeloPrecios("(SOC) Suroccidente", 29.00, 34.00);
    ModeloPrecios modPrec6 = new ModeloPrecios("(NOC) Noroccidente", 40.00, 44.50);

    List<ModeloPersona> listaPersona = new ArrayList();
    List<ModeloDepartamentos> listaDepart = new ArrayList();
    List<ModeloKiosco> listKiosco = new ArrayList();
    List<ModeloPrecios> listPrecio = new ArrayList();
    List<ModeloFacturacion> listFact = new ArrayList();
    List<ModeloCotizacion> listCot = new ArrayList();
    List<ModeloMunicipios> listMuni = new ArrayList();

    private int intentos = 3;
    private String nombreUser = "";
    private String dpi = "";

    public ControladorPrincipal(Autenticacion aut, List<ModeloKiosco> listKiosco,
            List<ModeloPrecios> listPrecio, List<ModeloDepartamentos> listaDepart,
            List<ModeloMunicipios> listMuni, List<ModeloFacturacion> listFact,
            List<ModeloPersona> listaPersona, List<ModeloCotizacion> listCot) {
        this.aut = aut;
        this.listKiosco = listKiosco;
        this.listPrecio = listPrecio;
        this.listaDepart = listaDepart;
        this.listMuni = listMuni;
        this.listFact = listFact;
        this.listaPersona = listaPersona;
        this.listCot = listCot;
        this.aut.jButtonIngresar.addActionListener(this);
        this.aut.jCheckBoxMostrar.addActionListener(this);
    }

    public ControladorPrincipal(Autenticacion aut) {
        this.aut = aut;
        this.aut.jButtonIngresar.addActionListener(this);
        this.aut.jCheckBoxMostrar.addActionListener(this);
        listaPersona.add(modPersona0);
        listPrecio.add(modPrec1);
    }

    public ControladorPrincipal(RegistroUsuario reg) {
        registro = reg;
    }

    private boolean ComprobarPass() {
        char[] c = aut.jPasswordField.getPassword();
        String pass = ReconocerPass(c);
        return CaracteresPass(pass);
    }

    private String ReconocerPass(char[] c) {
        String pass = "";
        for (int i = 0; i < c.length; i++) {
            pass += c[i];
        }
        return pass;
    }

    private static boolean CaracteresPass(String sd) {
        int upper = 0;
        int digit = 0;
        int lower = 0;
        int charr = 0;
        for (int i = 0; i < sd.length(); i++) {
            if (Character.isUpperCase(sd.charAt(i))) {
                upper++;
            }
            if (Character.isDigit(sd.charAt(i))) {
                digit++;
            }
            if (Character.isLowerCase(sd.charAt(i))) {
                lower++;
            }
            if (sd.charAt(i) >= 33 && sd.charAt(i) <= 47 && sd.charAt(i) != 32) {
                charr++;
            }
        }
        return (upper > 0 && digit > 0 && lower > 0 && charr > 0);
    }

    private void Autentificar() {
        String email = aut.jTextFieldCorreo.getText();
        String pass = aut.jPasswordField.getText();

        if (VerificarEmailPass(email, pass)) {
            FormPrincipal form = new FormPrincipal(listaPersona, listaDepart, listKiosco, listPrecio, listFact, listCot, dpi, listMuni);
            if (VerificarAdmin(email, pass)) {
                form.jMenuAdmin.setVisible(true);
                form.jMenuCliente.setVisible(false);
            } else {
                form.jMenuAdmin.setVisible(false);
            }
            aut.dispose();
            form.setVisible(true);
        } else {
            if (!ComprobarPass()) {
                JOptionPane.showMessageDialog(null, """
                                                        Las contraseñas no son iguales o
                                                        La contraseña debe llevar: 
                                                        Letras mayúsculas
                                                        Letras minúsculas
                                                        Números
                                                        Caracteres especiales (Ej. #,%,&,_,etc.)
                                                        Sin espacios
                                                        """, "WARNING!", JOptionPane.WARNING_MESSAGE);
            }
            JOptionPane.showMessageDialog(null, "Tiene " + intentos-- + " intentos restantes.", "WARNING!", JOptionPane.WARNING_MESSAGE);
            if (intentos < 0) {
                JOptionPane.showMessageDialog(null, "Se va a cerrar el programa", "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        }
    }

    private boolean VerificarAdmin(String email, String pass) {
        for (int i = 0; i < listaPersona.size(); i++) {
            if (email.equals(listaPersona.get(i).getCorreo()) && pass.equals(listaPersona.get(i).getPass())) {
                if (listaPersona.get(i).getRol().equals("adnin")) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean VerificarEmailPass(String email, String pass) {
        for (int i = 0; i < listaPersona.size(); i++) {
            if (email.equals(listaPersona.get(i).getCorreo()) && pass.equals(listaPersona.get(i).getPass())) {
                dpi = listaPersona.get(i).getDpi();
                return true;
            }
        }
        return false;
    }

    protected void MostrarPass(JCheckBox check, JPasswordField password) {
        if (check.isSelected()) {
            password.setEchoChar((char) 0);
        } else {
            password.setEchoChar('*');
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == aut.jCheckBoxMostrar) {
            MostrarPass(aut.jCheckBoxMostrar, aut.jPasswordField);
        } else if (e.getSource() == aut.jButtonIngresar) {
            Autentificar();
        }
    }
}
