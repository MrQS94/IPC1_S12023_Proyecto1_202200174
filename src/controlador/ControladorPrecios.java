/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelo.ModeloPrecios;
import vista.ManejoPrecios;

/**
 *
 * @author queza
 */
public class ControladorPrecios implements ActionListener, MouseListener, KeyListener {

    ManejoPrecios manejo;
    List<ModeloPrecios> list;

    public ControladorPrecios(ManejoPrecios manejo, List<ModeloPrecios> list) {
        this.manejo = manejo;
        this.list = list;
        this.manejo.jButtonEnviar.addActionListener(this);
        this.manejo.jTable.addMouseListener(this);
        this.manejo.jCheckBoxMostrar.addActionListener(this);
        this.manejo.jTextFieldPrecioEstandar.addKeyListener(this);
        this.manejo.jTextFieldPrecioEspecial.addKeyListener(this);
        LlenarTabla();
    }

    private void CambiarPrecios() {
        String codigo = (String) manejo.jComboBoxRegion.getSelectedItem();
        double precioEstandar = Double.parseDouble(manejo.jTextFieldPrecioEstandar.getText());
        double precioEspecial = Double.parseDouble(manejo.jTextFieldPrecioEspecial.getText());

        for (int i = 0; i < list.size(); i++) {
            if (codigo.equals(list.get(i).getNombre())) {
                list.get(i).setPrecioEstandar(precioEstandar);
                list.get(i).setPrecioEspecial(precioEspecial);
            }
        }
        manejo.jComboBoxRegion.setSelectedIndex(0);
        manejo.jTextFieldPrecioEstandar.setText("");
        manejo.jTextFieldPrecioEspecial.setText("");
        manejo.jTextFieldPrecioEstandar.setEnabled(false);
        manejo.jTextFieldPrecioEspecial.setEnabled(false);
        manejo.jCheckBoxMostrar.setSelected(false);
        manejo.jCheckBoxMostrar.setText("MOSTRAR");
        LlenarTabla();
    }

    private void LlenarTabla() {
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Nombre", "Precio Estándar Q.", "Precio Especial Q."}, list.size());
        manejo.jTable.setModel(dtm);
        dtm.getColumnName(0);
        TableModel model = manejo.jTable.getModel();

        for (int i = 0; i < list.size(); i++) {
            model.setValueAt(list.get(i).getNombre(), i, 0);
            model.setValueAt(list.get(i).getPrecioEstandar(), i, 1);
            model.setValueAt(list.get(i).getPrecioEspecial(), i, 2);
        }
    }

    private void ComprobarCampos() {
        String codigo = (String) manejo.jComboBoxRegion.getSelectedItem();
        String precioEstandar = manejo.jTextFieldPrecioEstandar.getText();
        String precioEspecial = manejo.jTextFieldPrecioEspecial.getText();
        if (codigo.isEmpty() || precioEstandar.isEmpty() || precioEspecial.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Seleccione un dato de la tabla para modificar si deasea ser modificado.", "ADVERTENCIA!!", JOptionPane.WARNING_MESSAGE);
            manejo.jCheckBoxMostrar.setSelected(false);
            manejo.jTextFieldPrecioEstandar.setEnabled(false);
            manejo.jTextFieldPrecioEspecial.setEnabled(false);
            manejo.jCheckBoxMostrar.setText("MOSTRAR");
        } else {
            manejo.jTextFieldPrecioEstandar.setEnabled(true);
            manejo.jTextFieldPrecioEspecial.setEnabled(true);
            manejo.jCheckBoxMostrar.setText("CONFIRMAR");
        }
    }

    private void HabilitarBoton() {
        String codigo = manejo.jTextFieldPrecioEspecial.getText();
        String codigoDepart = manejo.jTextFieldPrecioEstandar.getText();
        if (!(codigo.isEmpty() || codigoDepart.isEmpty())) {
            manejo.jButtonEnviar.setEnabled(true);
        } else {
            manejo.jButtonEnviar.setEnabled(false);
        }
    }

    private void SelecionarDatos(int selec) {
        manejo.jComboBoxRegion.setSelectedItem(String.valueOf(manejo.jTable.getValueAt(selec, 0)));
        manejo.jTextFieldPrecioEstandar.setText(String.valueOf(manejo.jTable.getValueAt(selec, 1)));
        manejo.jTextFieldPrecioEspecial.setText(String.valueOf(manejo.jTable.getValueAt(selec, 2)));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manejo.jButtonEnviar) {
            CambiarPrecios();
        } else if (e.getSource() == manejo.jCheckBoxMostrar) {
            if (manejo.jCheckBoxMostrar.isSelected()) {
                ComprobarCampos();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int select = manejo.jTable.rowAtPoint(e.getPoint());
        if (e.getSource() == manejo.jTable) {
            SelecionarDatos(select);
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (e.getSource() == manejo.jTextFieldPrecioEstandar
                || e.getSource() == manejo.jTextFieldPrecioEspecial) {
            if ((c < '0' || c > '9') && c != '.') {
                e.consume();
            }
            HabilitarBoton();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
