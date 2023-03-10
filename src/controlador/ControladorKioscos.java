/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import modelo.ModeloKiosco;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JOptionPane;
import modelo.ModeloPrecios;
import vista.ManejoKioscos;

/**
 *
 * @author queza
 */
public class ControladorKioscos implements ActionListener, KeyListener {

    ManejoKioscos manejoKiosc;
    List<ModeloPrecios> listPrecios;
    List<ModeloKiosco> listKiosco;

    public ControladorKioscos(ManejoKioscos manejo, List<ModeloPrecios> listPrecios, List<ModeloKiosco> listKiosc) {
        manejoKiosc = manejo;
        manejoKiosc.jButtonEnviar.addActionListener(this);
        this.listPrecios = listPrecios;
        this.listKiosco = listKiosc;
        manejoKiosc.jTextFieldNombre.addKeyListener(this);
        manejoKiosc.jTextFieldCodigoKiosco.addKeyListener(this);
        PullRegion();
    }

    private void PullRegion() {
        for (int i = 0; i < listPrecios.size(); i++) {
            manejoKiosc.jComboBoxRegion.addItem(listPrecios.get(i).getNombre());
        }
    }

    private void HabilitarBoton() {
        String nombre = manejoKiosc.jTextFieldNombre.getText();
        String codigoKiosc = manejoKiosc.jTextFieldCodigoKiosco.getText();
        if (!(nombre.isEmpty() || codigoKiosc.isEmpty())) {
            manejoKiosc.jButtonEnviar.setEnabled(true);
        } else {
            manejoKiosc.jButtonEnviar.setEnabled(false);
        }
    }

    private void AgregarKiosco() {
        String nombre = manejoKiosc.jTextFieldNombre.getText();
        String codigoKiosc = manejoKiosc.jTextFieldCodigoKiosco.getText();
        String codigoReg = (String) manejoKiosc.jComboBoxRegion.getSelectedItem();

        ModeloKiosco mod = new ModeloKiosco(nombre, codigoReg, codigoKiosc);
        listKiosco.add(mod);

        manejoKiosc.jTextFieldNombre.setText("");
        manejoKiosc.jTextFieldNombre.requestFocus();
        manejoKiosc.jTextFieldCodigoKiosco.setText("");
        manejoKiosc.jComboBoxRegion.setSelectedItem(0);

        JOptionPane.showMessageDialog(null, "Datos guardados exitosamente!", "INFORMACIÓN!", JOptionPane.INFORMATION_MESSAGE);
    }

    private boolean isCodigoKioscoValid(String kiosco) {
        for (int i = 0; i < listKiosco.size(); i++) {
            if (listKiosco.get(i).getCodigoKiosco().equals(kiosco)) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manejoKiosc.jButtonEnviar) {
            if (isCodigoKioscoValid(manejoKiosc.jTextFieldCodigoKiosco.getText())) {
                AgregarKiosco();
            } else {
                JOptionPane.showMessageDialog(null, "Ya existe un kiosco con este código", "DUPLICADOS!", JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (e.getSource() == manejoKiosc.jTextFieldNombre) {
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                e.consume();
            }
            HabilitarBoton();
        } else if (e.getSource() == manejoKiosc.jTextFieldCodigoKiosco) {
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
