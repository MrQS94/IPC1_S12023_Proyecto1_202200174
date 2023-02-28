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
import javax.swing.JPasswordField;
import modelo.ModeloKiosco;
import modelo.ModeloPersona;
import modelo.ModeloNacionalidad;
import vista.Autenticacion;
import vista.FormPrincipal;
import vista.RegistroUsuario;

/**
 *
 * @author queza
 */
public class ControladorPrincipal implements ActionListener{

    Autenticacion aut = new Autenticacion();
    RegistroUsuario registro = new RegistroUsuario();
    ModeloPersona modPersona = new ModeloPersona("Andres", "Quezada", "ipc1_202200174@ipc1delivery.com", "202200174", "3903542010101",
            "24/12/2002", "Hombre", "Guatemala", "MrQS", 42201602, "Admin", "");

    List<ModeloPersona> listaPersona = new ArrayList();
    List<ModeloKiosco> listaKiosco = new ArrayList();
    List<ModeloNacionalidad> listNacio = new ArrayList();

    public ControladorPrincipal(Autenticacion aut) {
        this.aut = aut;
        this.aut.jButtonIngresar.addActionListener(this);
        this.aut.jCheckBoxMostrar.addActionListener(this);
        listaPersona.add(modPersona);
    }

    public ControladorPrincipal(RegistroUsuario reg) {
        registro = reg;
    }

    private void Autentificar() {
        for (int i = 0; i < listaPersona.size(); i++) {
            if (aut.jTextFieldCorreo.getText().equals(listaPersona.get(i).getCorreo())
                    && aut.jPasswordField.getText().equals(listaPersona.get(i).getPass())) {
                FormPrincipal form = new FormPrincipal(listaPersona, listaKiosco);
                this.aut.dispose();
                form.setVisible(true);
                break;
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
