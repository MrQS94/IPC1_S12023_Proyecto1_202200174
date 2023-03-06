/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import modelo.ModeloDepartamentos;
import modelo.ModeloPrecios;
import vista.Cotizacion;

/**
 *
 * @author queza
 */
public class ControladorCotizacion implements ItemListener, KeyListener, ActionListener {
    
    Cotizacion cot;
    List<ModeloDepartamentos> listDepart;
    List<ModeloPrecios> listReg;
    
    public ControladorCotizacion(Cotizacion cot, List<ModeloDepartamentos> listDepart, List<ModeloPrecios> listReg) {
        this.cot = cot;
        this.listDepart = listDepart;
        this.listReg = listReg;
        this.cot.jComboBoxDestinoDept.addItemListener(this);
        this.cot.jComboBoxOrigenDept.addItemListener(this);
        this.cot.jTextFieldGrande.addKeyListener(this);
        this.cot.jTextFieldMediano.addKeyListener(this);
        this.cot.jTextFieldPequeño.addKeyListener(this);
        this.cot.jRadioButtonPequeño.addActionListener(this);
        this.cot.jRadioButtonMediano.addActionListener(this);
        this.cot.jRadioButtonGrande.addActionListener(this);
        this.cot.jTextFieldNoPaquetes.addKeyListener(this);
        this.cot.jButtonCotizar.addActionListener(this);
        this.cot.jCheckBoxHabilitar.addActionListener(this);
        PullDepartametns();
    }
    
    private void PullDepartametns() {
        for (int i = 0; i < listDepart.size(); i++) {
            cot.jComboBoxDestinoDept.addItem(listDepart.get(i).getNombreDepart());
            cot.jComboBoxOrigenDept.addItem(listDepart.get(i).getNombreDepart());
        }
    }
    
    private void HabilitarButton() {
        if (cot.jTextFieldNoPaquetes.getText().isEmpty()
                || cot.jComboBoxOrigenDept.getSelectedIndex() > 0
                || cot.jComboBoxDestinoDept.getSelectedIndex() > 0
                || cot.jTextFieldDireccionOrg.getText().isEmpty()
                || cot.jTextFieldDireccionDestino.getText().isEmpty()
                || !cot.jRadioButtonEstandar.isSelected()
                || !cot.jRadioButtonEspecial.isSelected()) {
            if (cot.jTextFieldPequeño.getText().isEmpty()
                    && cot.jTextFieldMediano.getText().isEmpty()
                    && cot.jTextFieldGrande.getText().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Existen campos vacios o no seleccionados.\nVuelva a intentar.", "ERROR!!", JOptionPane.ERROR_MESSAGE);
                /*cot.jTextFieldNoPaquetes.setText("");
            cot.jComboBoxOrigenDept.setSelectedIndex(0);
            cot.jComboBoxDestinoDept.setSelectedIndex(0);
            cot.jTextFieldDireccionOrg.setText("");
            cot.jTextFieldDireccionDestino.setText("");
            cot.buttonGroupSize.clearSelection();
            cot.jTextFieldPequeño.setText("");
            cot.jTextFieldMediano.setText("");
            cot.jTextFieldGrande.setText("");*/
                cot.jCheckBoxHabilitar.setSelected(false);
                cot.jButtonCotizar.setEnabled(false);
            } else {
                cot.jButtonCotizar.setEnabled(true);
            }
        }
    }
    
    private void CalcularServicio() {
        double precioReg = 0;
        double packageSize = 0;
        int noPaquetes = 0;
        String alerta = "";
        
        if (cot.jRadioButtonEstandar.isSelected()) {
            precioReg = Double.parseDouble(cot.jLabelEstandar.getText());
        } else if (cot.jRadioButtonEspecial.isSelected()) {
            precioReg = Double.parseDouble(cot.jLabelEspecial.getText());
        }
        
        if (cot.jRadioButtonPequeño.isSelected()) {
            packageSize = Double.parseDouble(cot.jTextFieldPequeño.getText());
            if (!(packageSize > 0 && packageSize <= 10)) {
                alerta = "Número invalido, debe ser entre 1 y 10 lb.";
            }
        } else if (cot.jRadioButtonMediano.isSelected()) {
            packageSize = Double.parseDouble(cot.jTextFieldMediano.getText());
            if (!(packageSize > 10 && packageSize <= 50)) {
                alerta = "Número invalido, debe ser entre 11 y 50 lb.";
            }
        } else if (cot.jRadioButtonGrande.isSelected()) {
            packageSize = Double.parseDouble(cot.jTextFieldGrande.getText());
            if (!(packageSize > 50)) {
                alerta = "Número invalido, debe ser de 51 en adelante.";
            }
        }
        if (!alerta.isEmpty()) {
            JOptionPane.showMessageDialog(null, alerta, "WARNING!", JOptionPane.WARNING_MESSAGE);
            cot.buttonGroupSize.clearSelection();
            
            cot.jTextFieldPequeño.setText("");
            cot.jTextFieldMediano.setText("");
            cot.jTextFieldGrande.setText("");
            
            cot.jTextFieldPequeño.setEnabled(false);
            cot.jTextFieldMediano.setEnabled(false);
            cot.jTextFieldGrande.setEnabled(false);
            packageSize = 0;
        }
        noPaquetes = Integer.parseInt(cot.jTextFieldNoPaquetes.getText());
        double total = precioReg * packageSize * noPaquetes;
        
    }
    
    private void HabilitarPesoJTF() {
        if (cot.jRadioButtonPequeño.isSelected()) {
            cot.jTextFieldGrande.setEnabled(false);
            cot.jTextFieldMediano.setEnabled(false);
            cot.jTextFieldPequeño.setEnabled(true);
            
            cot.jTextFieldGrande.setText("");
            cot.jTextFieldMediano.setText("");
        } else if (cot.jRadioButtonMediano.isSelected()) {
            cot.jTextFieldGrande.setEnabled(false);
            cot.jTextFieldMediano.setEnabled(true);
            cot.jTextFieldPequeño.setEnabled(false);
            
            cot.jTextFieldGrande.setText("");
            cot.jTextFieldPequeño.setText("");
        } else if (cot.jRadioButtonGrande.isSelected()) {
            cot.jTextFieldGrande.setEnabled(true);
            cot.jTextFieldMediano.setEnabled(false);
            cot.jTextFieldPequeño.setEnabled(false);
            
            cot.jTextFieldMediano.setText("");
            cot.jTextFieldPequeño.setText("");
        }
    }
    
    private void PullRegiones(JComboBox destino) {
        String combo = destino.getSelectedItem().toString();
        String region = "";
        
        for (int i = 0; i < listDepart.size(); i++) {
            if (listDepart.get(i).getNombreDepart().equals(combo)) {
                region = listDepart.get(i).getRegion();
            }
        }
        
        for (int i = 0; i < listReg.size(); i++) {
            if (listReg.get(i).getNombre().equals(region)) {
                cot.jLabelEstandar.setText(String.valueOf(listReg.get(i).getPrecioEstandar()));
                cot.jLabelEspecial.setText(String.valueOf(listReg.get(i).getPrecioEspecial()));
            }
        }
    }
    
    private void PullMunicipiosDestino(JComboBox combo, JComboBox destino) {
        combo.removeAllItems();
        String s = destino.getSelectedItem().toString();
        switch (s) {
            case "Alta Verapaz" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "Baja Verapaz" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "Chimaltenango" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "Chiquimula" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "El Progreso" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "Escuintla" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "Guatemala" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "Huehuetenango" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "Izabal" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "Jalapa" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "Jutiapa" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "Petén" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "Quetzaltenango" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "Quiché" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "Retalhuleu" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "Sacatepéquez" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "San Marcos" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "Santa Rosa" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "Sololá" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "Suchitepéquez" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "Totonicapán" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            case "Zacapa" -> {
                for (int i = 0; i < listDepart.size(); i++) {
                    if (s.equals(listDepart.get(i).getNombreDepart())) {
                        combo.addItem(listDepart.get(i).getMunicipios());
                    }
                }
            }
            
        }
    }
    
    @Override
    public void itemStateChanged(ItemEvent e) {
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (cot.jComboBoxDestinoDept.getSelectedIndex() == 0) {
                cot.jComboBoxDestinoMuni.removeAllItems();
            } else if (cot.jComboBoxDestinoDept.getSelectedIndex() > 0) {
                PullMunicipiosDestino(cot.jComboBoxDestinoMuni, cot.jComboBoxDestinoDept);
                PullRegiones(cot.jComboBoxDestinoDept);
            }
            if (cot.jComboBoxOrigenDept.getSelectedIndex() == 0) {
                cot.jComboBoxOrigenMuni.removeAllItems();
            } else if (cot.jComboBoxOrigenDept.getSelectedIndex() > 0) {
                PullMunicipiosDestino(cot.jComboBoxOrigenMuni, cot.jComboBoxOrigenDept);
            }
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == cot.jRadioButtonPequeño
                || e.getSource() == cot.jRadioButtonMediano
                || e.getSource() == cot.jRadioButtonGrande) {
            HabilitarPesoJTF();
        } else if (e.getSource() == cot.jButtonCotizar) {
            CalcularServicio();
        } else if (e.getSource() == cot.jCheckBoxHabilitar) {
            if (cot.jCheckBoxHabilitar.isSelected()) {
                HabilitarButton();
            }
        }
    }
    
    @Override
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (e.getSource() == cot.jTextFieldGrande
                || e.getSource() == cot.jTextFieldMediano
                || e.getSource() == cot.jTextFieldPequeño
                || e.getSource() == cot.jTextFieldNoPaquetes) {
            if ((c < '0' || c > '9') && c != '.') {
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
