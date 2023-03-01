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
import modelo.ModeloDepartamentos;
import vista.ManejoDepartamentos;

/**
 *
 * @author queza
 */
public class ControladorRegiones implements ActionListener, KeyListener {

    ManejoDepartamentos manejo = new ManejoDepartamentos();
    List<ModeloDepartamentos> lista;

    public ControladorRegiones(){
        
    }
    
    public ControladorRegiones(ManejoDepartamentos mane, List<ModeloDepartamentos> list) {
        manejo = mane;
        manejo.jButtonIngresar.addActionListener(this);
        manejo.jTextFieldCodigo.addKeyListener(this);
        manejo.jTextFieldCodigoDepart.addKeyListener(this);
        manejo.jTextFieldNombreDepart.addKeyListener(this);
        lista = list;
    }

    private void GuardarDatos() {
        String codigo = manejo.jTextFieldCodigo.getText();
        String region = (String) manejo.jComboBoxRegion.getSelectedItem();
        String nombre = (String) manejo.jComboBoxNombre.getSelectedItem();
        String codigoDepart = manejo.jTextFieldCodigo.getText();
        String nombreDepart = manejo.jTextFieldNombreDepart.getText();

        ModeloDepartamentos mod = new ModeloDepartamentos(codigo, region, nombre, codigoDepart, nombreDepart);
        lista.add(mod);
    }

    protected List getList() {
        return lista;
    }

    private void HabilitarDepart() {
        String codigo = manejo.jTextFieldCodigo.getText();
        if (!codigo.isEmpty()) {
            manejo.jTextFieldCodigoDepart.setEnabled(true);
            manejo.jTextFieldNombreDepart.setEnabled(true);
        } else {
            manejo.jTextFieldCodigoDepart.setEnabled(false);
            manejo.jTextFieldNombreDepart.setEnabled(false);
        }
    }

    private void HabilitarBoton() {
        String codigo = manejo.jTextFieldCodigo.getText();
        String codigoDepart = manejo.jTextFieldCodigoDepart.getText();
        String nombreDepart = manejo.jTextFieldNombreDepart.getText();
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
        } else if (e.getSource() == manejo.jTextFieldNombreDepart) {
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                e.consume();
            }
            HabilitarBoton();
        } else if (e.getSource() == manejo.jTextFieldCodigoDepart) {
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
