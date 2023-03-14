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
import vista.ManejoRegiones;

/**
 *
 * @author queza
 */
public class ControladorRegiones implements ActionListener, MouseListener, KeyListener {

    ManejoRegiones manejo;
    List<ModeloPrecios> list;

    public ControladorRegiones(ManejoRegiones manejo, List<ModeloPrecios> list) {
        this.manejo = manejo;
        this.list = list;
        this.manejo.jButtonCambiar.addActionListener(this);
        this.manejo.jButtonAgregar.addActionListener(this);
        this.manejo.jButtonEliminar.addActionListener(this);
        this.manejo.jTable.addMouseListener(this);
        this.manejo.jTextFieldPrecioEstandar.addKeyListener(this);
        this.manejo.jTextFieldPrecioEspecial.addKeyListener(this);
        LlenarTabla();
    }

    private void AgregarPrecios() {
        String region = manejo.jComboBoxRegion.getSelectedItem().toString();
        double precioEstandar = Double.parseDouble(manejo.jTextFieldPrecioEstandar.getText());
        double precioEspecial = Double.parseDouble(manejo.jTextFieldPrecioEspecial.getText());

        for (int i = 0; i < list.size(); i++) {
            if (!list.get(i).getNombre().equals(region)) {
                ModeloPrecios mod = new ModeloPrecios(region, precioEstandar, precioEspecial);
                list.add(mod);
                JOptionPane.showMessageDialog(null, "Está region ha sido agregada.", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
                break;
            } else {
                JOptionPane.showMessageDialog(null, "Ya existe esta region.", "WARNING!", JOptionPane.WARNING_MESSAGE);
                break;
            }
        }
        manejo.jComboBoxRegion.setSelectedIndex(0);
        manejo.jTextFieldPrecioEstandar.setText("");
        manejo.jTextFieldPrecioEspecial.setText("");
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
        LlenarTabla();

        JOptionPane.showMessageDialog(null, "Cambio realizado satisfactoriamente.", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
    }

    private void EliminarPrecios() {
        String region = manejo.jComboBoxRegion.getSelectedItem().toString();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getNombre().equals(region)) {
                list.remove(i);
                JOptionPane.showMessageDialog(null, "Región eliminada satisfactoriamente.", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }

        manejo.jComboBoxRegion.setSelectedIndex(0);
        manejo.jTextFieldPrecioEstandar.setText("");
        manejo.jTextFieldPrecioEspecial.setText("");
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

    private void HabilitarBoton() {
        String codigo = manejo.jTextFieldPrecioEspecial.getText();
        String codigoDepart = manejo.jTextFieldPrecioEstandar.getText();
        if (!(codigo.isEmpty() || codigoDepart.isEmpty())) {
            manejo.jButtonAgregar.setEnabled(true);
            manejo.jButtonCambiar.setEnabled(true);
            manejo.jButtonEliminar.setEnabled(true);
        } else {
            manejo.jButtonAgregar.setEnabled(false);
            manejo.jButtonCambiar.setEnabled(false);
            manejo.jButtonEliminar.setEnabled(false);
        }
    }

    private void SelecionarDatos(int selec) {
        manejo.jComboBoxRegion.setSelectedItem(String.valueOf(manejo.jTable.getValueAt(selec, 0)));
        manejo.jTextFieldPrecioEstandar.setText(String.valueOf(manejo.jTable.getValueAt(selec, 1)));
        manejo.jTextFieldPrecioEspecial.setText(String.valueOf(manejo.jTable.getValueAt(selec, 2)));
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manejo.jButtonCambiar) {
            CambiarPrecios();
        } else if (e.getSource() == manejo.jButtonAgregar) {
            AgregarPrecios();
        } else if (e.getSource() == manejo.jButtonEliminar) {
            EliminarPrecios();
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
