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
import modelo.ModeloDepartamentos;
import vista.ManejoKioscos;

/**
 *
 * @author queza
 */
public class ControladorKioscos implements ActionListener, KeyListener {

    ManejoKioscos manejoKiosc = new ManejoKioscos();
    ControladorRegiones controlReg = new ControladorRegiones();
    List<ModeloDepartamentos> listDepart;
    List<ModeloKiosco> listKiosc;

    public ControladorKioscos(ManejoKioscos manejo, List<ModeloDepartamentos> listDepart, List<ModeloKiosco> listKiosc) {
        manejoKiosc = manejo;
        manejoKiosc.jButtonEnviar.addActionListener(this);
        this.listDepart = listDepart;
        this.listKiosc = listKiosc;
        manejoKiosc.jTextFieldNombre.addKeyListener(this);
        manejoKiosc.jTextFieldCodigoKiosco.addKeyListener(this);
        PullRegion();
    }

    private void PullRegion() {
        for (int i = 0; i < listDepart.size(); i++) {
            manejoKiosc.jComboBoxRegion.addItem(listDepart.get(i).getCodigoDepart());
        }
    }

    private void HabilitarBoton() {
        String nombre = manejoKiosc.jTextFieldNombre.getText();
        String codigoKiosc = manejoKiosc.jTextFieldCodigoKiosco.getText();
        //int codigoReg = manejoKiosc.jComboBoxRegion.getSelectedIndex();

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
        listKiosc.add(mod);

        manejoKiosc.jTextFieldNombre.setText("");
        manejoKiosc.jTextFieldNombre.requestFocus();
        manejoKiosc.jTextFieldCodigoKiosco.setText("");
        manejoKiosc.jComboBoxRegion.setSelectedItem(0);

        JOptionPane.showMessageDialog(null, "Datos guardados exitosamente!", "INFORMACIÓN!", JOptionPane.INFORMATION_MESSAGE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manejoKiosc.jButtonEnviar) {
            AgregarKiosco();
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
