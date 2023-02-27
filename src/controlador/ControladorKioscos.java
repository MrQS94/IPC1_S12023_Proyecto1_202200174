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
import modelo.ModeloKiosco;
import vista.ManejoKioscos;

/**
 *
 * @author queza
 */
public class ControladorKioscos implements ActionListener, KeyListener {

    ManejoKioscos manejo;
    List<ModeloKiosco> lista;
    ModeloKiosco kiosco = new ModeloKiosco();

    public ControladorKioscos() {

    }

    public ControladorKioscos(ManejoKioscos manejo, List lista) {
        this.manejo = manejo;
        this.manejo.jTextFieldNombre.addKeyListener(this);
        this.manejo.jTextFieldCodigoKiosco.addKeyListener(this);
        this.manejo.jTextFieldCodigoRegion.addKeyListener(this);
        this.manejo.jButtonEnviar.addActionListener(this);
        this.lista = lista;
    }

    private void AgregarKioscos() {
        String nombre = manejo.jTextFieldNombre.getText();
        String codigoKiosco = manejo.jTextFieldCodigoKiosco.getText();
        String codigoRegion = manejo.jTextFieldCodigoRegion.getText();
        ModeloKiosco modelo = new ModeloKiosco(nombre, codigoKiosco, codigoRegion);
        lista.add(modelo);
        
        for (int i = 0; i < lista.size(); i++) {
            System.out.println(lista.get(i).getNombre() + " - " + i);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manejo.jButtonEnviar) {
            AgregarKioscos();
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (e.getSource() == manejo.jTextFieldNombre) {
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                e.consume();
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
