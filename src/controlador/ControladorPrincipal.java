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

    ModeloPersona modPersona1 = new ModeloPersona("Andres", "Quezada", "ipc1_202200174@ipc1delivery.com", "202200174", "3903542010101",
            "24/12/2002", "Hombre", "Guatemala", "MrQS", 42201602, "admin", "");
    //ModeloDepartamentos modDepart = new ModeloDepartamentos("GT", "(M) Metropolitana", "Guatemala", "VN", "Villa Nueva");
    //ModeloDepartamentos modDepart = new ModeloDepartamentos("GT", "(M) Metropolitana", "Guatemala");
    ModeloPrecios modPrec1 = new ModeloPrecios("(M) Metropolitana", 25, 35);
    ModeloPrecios modPrec2 = new ModeloPrecios("(NT) Norte", 45.55, 68.50);
    ModeloPrecios modPrec3 = new ModeloPrecios("(NO) Nororiente", 35.48, 58.68);
    ModeloPrecios modPrec4 = new ModeloPrecios("(SO) Suroriente", 32.48, 38.68);
    ModeloPrecios modPrec5 = new ModeloPrecios("(SOC) Suroccidente", 29.00, 34.00);
    ModeloPrecios modPrec6 = new ModeloPrecios("(NOC) Noroccidente", 40.00, 44.50);

    List<ModeloPersona> listaPersona = new ArrayList();
    List<ModeloDepartamentos> listaDepart = new ArrayList();
    List<ModeloKiosco> listKiosc = new ArrayList();
    List<ModeloPrecios> listPrecio = new ArrayList();
    List<ModeloFacturacion> listFact = new ArrayList();
    List<ModeloCotizacion> listCot = new ArrayList();

    private int intentos = 3;

    public ControladorPrincipal(Autenticacion aut) {
        this.aut = aut;
        this.aut.jButtonIngresar.addActionListener(this);
        this.aut.jCheckBoxMostrar.addActionListener(this);
        listaPersona.add(modPersona1);
        //listaDepart.add(modDepart);
        listPrecio.add(modPrec1);
        listPrecio.add(modPrec2);
        listPrecio.add(modPrec3);
        listPrecio.add(modPrec4);
        listPrecio.add(modPrec5);
        listPrecio.add(modPrec6);
    }

    public ControladorPrincipal(RegistroUsuario reg) {
        registro = reg;
    }

    private void Autentificar() {
        String hola = aut.jTextFieldCorreo.getText();
        String pass = aut.jPasswordField.getText();
        for (int i = 0; i < listaPersona.size(); i++) {
            if (hola.equals(listaPersona.get(i).getCorreo()) && pass.equals(listaPersona.get(i).getPass())) {
                FormPrincipal form = new FormPrincipal(listaPersona, listaDepart, listKiosc, listPrecio, listFact, listCot);
                aut.dispose();
                if (listaPersona.get(i).getRol().equals("admin")) {
                    form.jMenuAdmin.setVisible(true);
                } else {
                    form.jMenuAdmin.setVisible(false);
                }
                form.setVisible(true);
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Tiene " + intentos-- + " intentos restantes.", "WARNING!", JOptionPane.WARNING_MESSAGE);
                if (intentos < 0) {
                    JOptionPane.showMessageDialog(null, "Se va a cerrar el programa", "ERROR GRAVE", JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
            }
        }
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
