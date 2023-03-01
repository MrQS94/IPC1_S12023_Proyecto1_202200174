/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelo.ModeloPrecios;
import vista.ManejoPrecios;

/**
 *
 * @author queza
 */
public class ControladorPrecios implements ActionListener, MouseListener {

    ManejoPrecios manejo;
    List<ModeloPrecios> list;

    public ControladorPrecios(ManejoPrecios manejo, List<ModeloPrecios> list) {
        this.manejo = manejo;
        this.list = list;
        this.manejo.jButtonEnviar.addActionListener(this);
        this.manejo.jButtonPrecios.addActionListener(this);
        this.manejo.jTable.addMouseListener(this);
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

    private void SelecionarDatos(int selec) {
        manejo.jComboBoxRegion.setSelectedItem(String.valueOf(manejo.jTable.getValueAt(selec, 0)));
        manejo.jTextFieldPrecioEstandar.setText(String.valueOf(manejo.jTable.getValueAt(selec, 1)));
        manejo.jTextFieldPrecioEspecial.setText(String.valueOf(manejo.jTable.getValueAt(selec, 2)));
    }
    

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == manejo.jButtonEnviar) {
            CambiarPrecios();
        } else if (e.getSource() == manejo.jButtonPrecios) {
            // MostrarDatos();
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

}
