/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ModeloDepartamentos;
import modelo.ModeloMunicipios;
import vista.ManejoDepartamentos;

/**
 *
 * @author queza
 */
public class ControladorDepartamentos implements ActionListener, KeyListener {

    ManejoDepartamentos manejo;
    List<ModeloDepartamentos> list;

    public ControladorDepartamentos(ManejoDepartamentos mane, List<ModeloDepartamentos> list) {
        manejo = mane;
        this.list = list;
        manejo.jButtonIngresar.addActionListener(this);
        manejo.jTextFieldCodigo.addKeyListener(this);
        manejo.jTextFieldCodigoMuni.addKeyListener(this);
        manejo.jTextFieldNombreMuni.addKeyListener(this);
    }

    private void GuardarDatos() {
        String codigoDepart = manejo.jTextFieldCodigo.getText();
        String region = manejo.jComboBoxRegion.getSelectedItem().toString();
        String nombreDepart = manejo.jComboBoxNombre.getSelectedItem().toString();
        String codigoMuni = manejo.jTextFieldCodigoMuni.getText();
        String nombreMuni = manejo.jTextFieldNombreMuni.getText();

        ModeloDepartamentos modDept = new ModeloDepartamentos(codigoDepart, region, nombreDepart);
        ModeloMunicipios modMuni = new ModeloMunicipios(codigoMuni, nombreMuni);

        modDept.AgregarMunicipios(modMuni);
        list.add(modDept);

        manejo.jTextFieldCodigo.setText("");
        manejo.jTextFieldCodigo.requestFocus();
        manejo.jComboBoxRegion.setSelectedIndex(0);
        manejo.jComboBoxNombre.setSelectedIndex(0);
        manejo.jTextFieldCodigoMuni.setText("");
        manejo.jTextFieldNombreMuni.setText("");
        manejo.jButtonIngresar.setEnabled(false);

        JOptionPane.showMessageDialog(null, "Datos guardados exitosamente!", "INFORMACIÓN!", JOptionPane.INFORMATION_MESSAGE);
    }

    private void HabilitarDepart() {
        String codigo = manejo.jTextFieldCodigo.getText();
        if (!codigo.isEmpty()) {
            manejo.jTextFieldCodigoMuni.setEnabled(true);
            manejo.jTextFieldNombreMuni.setEnabled(true);
        } else {
            manejo.jTextFieldCodigoMuni.setEnabled(false);
            manejo.jTextFieldNombreMuni.setEnabled(false);
        }
    }

    private void HabilitarBoton() {
        String codigo = manejo.jTextFieldCodigo.getText();
        String codigoDepart = manejo.jTextFieldCodigoMuni.getText();
        String nombreDepart = manejo.jTextFieldNombreMuni.getText();
        if (!(codigo.isEmpty() || codigoDepart.isEmpty() || nombreDepart.isEmpty())) {
            manejo.jButtonIngresar.setEnabled(true);
        } else {
            manejo.jButtonIngresar.setEnabled(false);
        }
    }

    private boolean isDepartamentoValid(String departamento) {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getCodigoDepart().equals(departamento)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manejo.jButtonIngresar) {
            if (isDepartamentoValid(manejo.jTextFieldCodigo.getText())) {
                GuardarDatos();
            } else {
                JOptionPane.showMessageDialog(null, "Ya existe un kiosco con este código", "DUPLICADOS!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (e.getSource() == manejo.jTextFieldCodigo) {
            HabilitarDepart();
            HabilitarBoton();
        } else if (e.getSource() == manejo.jTextFieldNombreMuni) {
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                e.consume();
            }
            HabilitarBoton();
        } else if (e.getSource() == manejo.jTextFieldCodigoMuni) {
            HabilitarBoton();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
