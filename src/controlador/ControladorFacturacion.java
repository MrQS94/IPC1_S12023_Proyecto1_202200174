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
import modelo.ModeloFacturacion;
import vista.DatosFacturacion;

/**
 *
 * @author queza
 */
public class ControladorFacturacion implements ActionListener, KeyListener {

    DatosFacturacion manejo;
    List<ModeloFacturacion> list;

    public ControladorFacturacion(DatosFacturacion datos, List<ModeloFacturacion> lista) {
        manejo = datos;
        list = lista;
        manejo.jButtonGuardar.addActionListener(this);
        manejo.jTextFieldNit.addKeyListener(this);
        manejo.jTextFieldNumeroCardX.addKeyListener(this);
        manejo.jTextFieldNumeroCardLastDigits.addKeyListener(this);
        manejo.jTextFieldCVV.addKeyListener(this);
        manejo.jTextFieldNumeroCardX.setEchoChar('X');
        manejo.jTextFieldNombreCard.addKeyListener(this);
        manejo.jTextFieldNombreCompleto.addKeyListener(this);
    }

    private void GuardarFacturacion() {
        String nombreCompleto = manejo.jTextFieldNombreCompleto.getText();
        String direccion = manejo.jTextFieldDireccion.getText();
        String nit = manejo.jTextFieldNit.getText();
        String nombreCard = manejo.jTextFieldNombreCard.getText();
        String numeroCard = manejo.jTextFieldNumeroCardX.getText() + manejo.jTextFieldNumeroCardLastDigits.getText();
        String fechaVen = manejo.jComboBoxMM.getSelectedItem() + "/" + manejo.jComboBoYYYY.getSelectedItem();

        ModeloFacturacion mod = new ModeloFacturacion(nombreCompleto, direccion, nit, nombreCard, numeroCard, fechaVen);
        list.add(mod);

        manejo.jTextFieldNombreCompleto.setText("");
        manejo.jTextFieldNombreCompleto.requestFocus();
        manejo.jTextFieldDireccion.setText("");
        manejo.jTextFieldNit.setText("");
        manejo.jTextFieldNombreCard.setText("");
        manejo.jTextFieldNumeroCardX.setText("");
        manejo.jTextFieldNumeroCardLastDigits.setText("");
        manejo.jComboBoxMM.setSelectedIndex(0);
        manejo.jComboBoYYYY.setSelectedIndex(0);
        manejo.jTextFieldCVV.setText("");
        manejo.jButtonGuardar.setEnabled(false);

        JOptionPane.showMessageDialog(null, "Se ha agreado existosamente los datos de facturación!", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
    }

    private void HabilitarBoton() {
        String nombreCompleto = manejo.jTextFieldNombreCompleto.getText();
        String direccion = manejo.jTextFieldDireccion.getText();
        String nit = manejo.jTextFieldNit.getText();
        String nombreCard = manejo.jTextFieldNombreCard.getText();
        String numeroCard = manejo.jTextFieldNumeroCardX.getText() + manejo.jTextFieldNumeroCardLastDigits.getText();
        String cvv = manejo.jTextFieldCVV.getText();
        int fechaMM = manejo.jComboBoxMM.getSelectedIndex();
        int fechaYYYY = manejo.jComboBoYYYY.getSelectedIndex();

        if (!(nombreCompleto.isEmpty() || direccion.isEmpty()
                || nit.isEmpty() || nombreCard.isEmpty()
                || numeroCard.isEmpty() || cvv.isEmpty()
                || fechaMM == 0 || fechaYYYY == 0)) {
            manejo.jButtonGuardar.setEnabled(true);
        } else {
            manejo.jButtonGuardar.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manejo.jButtonGuardar) {
            GuardarFacturacion();
            for (int i = 0; i < list.size(); i++) {
                System.out.println(list.get(i).getNombreCompleto());
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (e.getSource() == manejo.jTextFieldNit) {
            if (manejo.jTextFieldNit.getText().length() > 7) {
                e.consume();
                HabilitarBoton();
            }
        } else if (e.getSource() == manejo.jTextFieldNumeroCardX) {
            if (manejo.jTextFieldNumeroCardX.getText().length() > 11 || (c < '0' || c > '9')) {
                e.consume();
                HabilitarBoton();
            }
        } else if (e.getSource() == manejo.jTextFieldNumeroCardLastDigits) {
            if (manejo.jTextFieldNumeroCardLastDigits.getText().length() > 3 || (c < '0' || c > '9')) {
                e.consume();
                HabilitarBoton();
            }
        } else if (e.getSource() == manejo.jTextFieldCVV) {
            if (manejo.jTextFieldCVV.getText().length() > 2 || (c < '0' || c > '9')) {
                e.consume();
                HabilitarBoton();
            }
        } else if (e.getSource() == manejo.jTextFieldNombreCompleto
                || e.getSource() == manejo.jTextFieldNombreCard) {
            if ((c < 'a' || c > 'z') && (c < 'A' || c > 'Z')) {
                e.consume();
                HabilitarBoton();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
