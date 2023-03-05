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
public class ControladorRegiones implements ActionListener, KeyListener {

    ManejoDepartamentos manejo = new ManejoDepartamentos();
    List<ModeloDepartamentos> lista;

    public ControladorRegiones() {

    }

    public ControladorRegiones(ManejoDepartamentos mane, List<ModeloDepartamentos> list) {
        manejo = mane;
        lista = list;
        manejo.jButtonIngresar.addActionListener(this);
        manejo.jTextFieldCodigo.addKeyListener(this);
        manejo.jTextFieldCodigoMuni.addKeyListener(this);
        manejo.jTextFieldNombreMuni.addKeyListener(this);
    }

    ModeloDepartamentos modDeptAV;
    ModeloDepartamentos modDeptBV;

    private void GuardarDatos() {
        String codigoDepart = manejo.jTextFieldCodigo.getText();
        String region = manejo.jComboBoxRegion.getSelectedItem().toString();
        String nombreDepart = manejo.jComboBoxNombre.getSelectedItem().toString();
        String codigoMuni = manejo.jTextFieldCodigoMuni.getText();
        String nombreMuni = manejo.jTextFieldNombreMuni.getText();

        ModeloMunicipios muni = new ModeloMunicipios(codigoMuni, nombreMuni);
        modDeptAV = new ModeloDepartamentos();
        modDeptBV = new ModeloDepartamentos();
        switch (nombreDepart) {
            case "Alta Verapaz":
                modDeptAV = new ModeloDepartamentos(codigoDepart, region, nombreDepart);
                modDeptAV.AgregarMunicipios(muni);
                lista.add(modDeptAV);
                break;
            case "Baja Verapaz":
                modDeptBV = new ModeloDepartamentos(codigoDepart, region, nombreDepart);
                modDeptBV.AgregarMunicipios(muni);
                lista.add(modDeptBV);
                break;
            case "Chimaltenango":
                break;
            case "Chiquimula":
                break;
            case "El Progreso":
                break;
            case "Escuintla":
                break;
            case "Guatemala":
                break;
            case "Huehuetenango":
                break;
            case "Izabal":
                break;
            case "Jalapa":
                break;
            case "Jutiapa":
                break;
            case "Petén":
                break;
            case "Quetzaltenango":
                break;
            case "Quiché":
                break;
            case "Retalhuleu":
                break;
            case "Sacatepéquez":
                break;
            case "San Marcos":
                break;
            case "Santa Rosa":
                break;
            case "Sololá":
                break;
            case "Suchitepéquez":
                break;
            case "Totonicapán":
                break;
            case "Zacapa":
                break;
        }
        
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).getNombreDepart());
            System.out.println(lista.get(i).getMunicipios());
        }


        /*manejo.jTextFieldCodigo.setText("");
        manejo.jTextFieldCodigo.requestFocus();
        manejo.jComboBoxRegion.setSelectedIndex(0);
        manejo.jComboBoxNombre.setSelectedIndex(0);
        manejo.jTextFieldCodigoMuni.setText("");
        manejo.jTextFieldNombreMuni.setText("");
        manejo.jButtonIngresar.setEnabled(false);

        JOptionPane.showMessageDialog(null, "Datos guardados exitosamente!", "INFORMACIÓN!", JOptionPane.INFORMATION_MESSAGE);*/
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

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manejo.jButtonIngresar) {
            GuardarDatos();
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
