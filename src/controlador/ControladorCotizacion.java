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
import java.util.Calendar;
import java.util.List;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import modelo.ModeloCotizacion;
import modelo.ModeloDepartamentos;
import modelo.ModeloFacturacion;
import modelo.ModeloPersona;
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
    List<ModeloFacturacion> listFact;
    List<ModeloCotizacion> listCot;
    List<ModeloPersona> listPers;

    private double totalServicio;
    private String texto;

    public ControladorCotizacion(Cotizacion cot, List<ModeloDepartamentos> listDepart,
            List<ModeloPrecios> listReg, List<ModeloFacturacion> listFact,
            List<ModeloCotizacion> listCot, List<ModeloPersona> listPers) {
        this.cot = cot;
        this.listDepart = listDepart;
        this.listReg = listReg;
        this.listFact = listFact;
        this.listCot = listCot;
        this.listPers = listPers;
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
        this.cot.jRadioButtonCobroCuenta.addActionListener(this);
        this.cot.jRadioButtonCobroEntrega.addActionListener(this);
        this.cot.jComboBoxListaFacturacion.addItemListener(this);
        this.cot.jButtonEnviar.addActionListener(this);
        PullDepartametns();
        PullFacturacion();
        HabilitarCard();
    }

    private void RealizarCompra() {
        int noFactura = 0;
        int codigoPaquete = 0;
        if (!listCot.isEmpty()) {
            for (int i = 0; i < listCot.size(); i++) {
                noFactura = listCot.get(i).getNoFactura();
                codigoPaquete = listCot.get(i).getCodigoPaquete();
            }
        }
        noFactura++;
        codigoPaquete++;

        String origen = cot.jTextFieldDireccionOrigen.getText() + ", "
                + cot.jComboBoxOrigenMuni.getSelectedItem().toString() + ", "
                + cot.jComboBoxOrigenDept.getSelectedItem().toString();
        String destino = cot.jTextFieldDireccionDestino.getText() + ", "
                + cot.jComboBoxDestinoMuni.getSelectedItem().toString() + ", "
                + cot.jComboBoxDestinoDept.getSelectedItem().toString();
        String nit = "";
        for (int i = 0; i < listFact.size(); i++) {
            nit = listFact.get(noFactura).getNit();
        }
        String tipoPago = "";
        if (cot.jRadioButtonCobroEntrega.isSelected()) {
            tipoPago = cot.jRadioButtonCobroEntrega.getText();
            totalServicio += 5;
        } else if (cot.jRadioButtonCobroCuenta.isSelected()) {
            tipoPago = cot.jRadioButtonCobroCuenta.getText();
        }
        double packageSize = 0;
        if (!cot.jTextFieldPequeño.getText().isEmpty()) {
            packageSize = Double.parseDouble(cot.jTextFieldPequeño.getText());
        } else if (!cot.jTextFieldMediano.getText().isEmpty()) {
            packageSize = Double.parseDouble(cot.jTextFieldMediano.getText());
        } else if (!cot.jTextFieldGrande.getText().isEmpty()) {
            packageSize = Double.parseDouble(cot.jTextFieldGrande.getText());
        }
        int noPaquetes = Integer.parseInt(cot.jTextFieldNoPaquetes.getText());

        Calendar c = Calendar.getInstance();
        String fechaEnvio = c.get(Calendar.DATE) + "/"
                + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.YEAR) + ", "
                + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE) + ":"
                + c.get(Calendar.SECOND);

        String tipoServicio = "";
        if (cot.jRadioButtonEstandar.isSelected()) {
            tipoServicio = cot.jRadioButtonEstandar.getText();
        } else if (cot.jRadioButtonEspecial.isSelected()) {
            tipoServicio = cot.jRadioButtonEspecial.getText();
        }
        double totPagar = totalServicio;
        ModeloCotizacion mod = new ModeloCotizacion(noFactura, codigoPaquete,
                origen, destino, nit, tipoPago, packageSize, noPaquetes,
                totPagar, fechaEnvio, tipoServicio);
        listCot.add(mod);
    }

    private void HabilitarCard() {
        if (!listFact.isEmpty()) {
            cot.jRadioButtonCobroCuenta.setEnabled(true);
            cot.jComboBoxListaFacturacion.setEnabled(true);
        }
    }

    private void HabilitarButtonEnviar() {
        if (cot.jRadioButtonCobroEntrega.isSelected()
                && !cot.jRadioButtonCobroCuenta.isEnabled()
                && !(cot.jComboBoxListaFacturacion.getSelectedIndex() > 0)) {
            cot.jButtonEnviar.setEnabled(true);
        } else if ((cot.jRadioButtonCobroEntrega.isSelected() || cot.jRadioButtonCobroCuenta.isSelected())
                && (cot.jComboBoxListaFacturacion.getSelectedIndex() > 0)) {
            cot.jButtonEnviar.setEnabled(true);
        } else {
            cot.jButtonEnviar.setEnabled(false);
        }
    }

    private void PullFacturacion() {
        for (int i = 0; i < listFact.size(); i++) {
            cot.jComboBoxListaFacturacion.addItem(listFact.get(i).getNombreCompleto()
                    + " - XXXX-XXXX-XXXX-" + listFact.get(i).getNumeroTarjeta()
                    + " - " + listFact.get(i).getDireccion());
        }
    }

    private void PullDepartametns() {
        for (int i = 0; i < listDepart.size(); i++) {
            cot.jComboBoxDestinoDept.addItem(listDepart.get(i).getNombreDepart());
            cot.jComboBoxOrigenDept.addItem(listDepart.get(i).getNombreDepart());
        }
    }

    private void ContraEntrega() {
        String texto = cot.jTextArea.getText();
        double tot5 = totalServicio;
        if (cot.jRadioButtonCobroEntrega.isSelected()) {
            texto += "\nCobro extra por contra entrega: Q." + (tot5 + 5);
            cot.jTextArea.setText(texto);
        } else if (cot.jRadioButtonCobroCuenta.isSelected()) {
            cot.jTextArea.setText(this.texto);
        }
    }

    private void ConfirmarCVV() {
        if (cot.jComboBoxListaFacturacion.getSelectedIndex() == 0) {
            JOptionPane.showInputDialog(null, "Ingrese el CVV de su tarjeta:", "Tarjeta Credito/Debito", JOptionPane.QUESTION_MESSAGE);
        }
    }

    private void CalcularServicio() {
        double precioReg = 0;
        double packageSize = 0;
        int noPaquetes = 0;
        String alerta = "";
        String tipoServicio = "";
        String region = PullReg(cot.jComboBoxDestinoDept);

        if (cot.jRadioButtonEstandar.isSelected()) {
            precioReg = Double.parseDouble(cot.jLabelEstandar.getText());
            tipoServicio = cot.jRadioButtonEstandar.getText();
        } else if (cot.jRadioButtonEspecial.isSelected()) {
            precioReg = Double.parseDouble(cot.jLabelEspecial.getText());
            tipoServicio = cot.jRadioButtonEspecial.getText();
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
        } else {
            noPaquetes = Integer.parseInt(cot.jTextFieldNoPaquetes.getText());
            totalServicio = precioReg * packageSize * noPaquetes;

            texto = "";
            texto += "El " + tipoServicio + " por region " + region + " es: Q." + precioReg;
            texto += "\nEl peso del paquete es de: " + packageSize + " lb";
            texto += "\nLa cantidad de paquete(s) es: " + noPaquetes;
            texto += "\nEl total de lo cotizado es: Q." + totalServicio;
            int hola = JOptionPane.showConfirmDialog(null, texto
                    + "\nConfirmar que sean los datos correctos.", "INFORMACIÓN!", JOptionPane.YES_NO_OPTION);
            if (hola == 0) {
                cot.jTextArea.setText(texto);
                cot.jButtonCotizar.setEnabled(false);
            } else if (hola == 1) {
                cot.jButtonCotizar.setEnabled(false);

                cot.jTextFieldNoPaquetes.setEnabled(true);
                cot.jComboBoxDestinoDept.setEnabled(true);
                cot.jComboBoxOrigenDept.setEnabled(true);
                cot.jTextFieldDireccionOrigen.setEnabled(true);
                cot.jTextFieldDireccionDestino.setEnabled(true);
                cot.jTextFieldPequeño.setEnabled(false);
                cot.jTextFieldMediano.setEnabled(false);
                cot.jTextFieldGrande.setEnabled(false);
                cot.jRadioButtonEspecial.setEnabled(true);
                cot.jRadioButtonEstandar.setEnabled(true);
                cot.jRadioButtonPequeño.setEnabled(true);
                cot.jRadioButtonMediano.setEnabled(true);
                cot.jRadioButtonGrande.setEnabled(true);
                cot.jCheckBoxHabilitar.setSelected(false);
            }
        }

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

    private String PullReg(JComboBox destino) {
        String combo = destino.getSelectedItem().toString();
        String region = "";
        String reg = "";

        for (int i = 0; i < listDepart.size(); i++) {
            if (listDepart.get(i).getNombreDepart().equals(combo)) {
                region = listDepart.get(i).getRegion();
            }
        }

        for (int i = 0; i < listReg.size(); i++) {
            if (listReg.get(i).getNombre().equals(region)) {
                reg = listReg.get(i).getNombre();
            }
        }
        return reg;
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

    private void HabilitarButtonCotizacion() {
        if (cot.jTextFieldNoPaquetes.getText().isEmpty()
                || cot.jComboBoxDestinoDept.getSelectedIndex() == 0
                || cot.jComboBoxOrigenDept.getSelectedIndex() == 0
                || cot.jTextFieldDireccionOrigen.getText().isEmpty()
                || cot.jTextFieldDireccionDestino.getText().isEmpty()
                || (cot.jTextFieldPequeño.getText().isEmpty()
                && cot.jTextFieldMediano.getText().isEmpty()
                && cot.jTextFieldGrande.getText().isEmpty())
                || (!cot.jRadioButtonEstandar.isSelected()
                && !cot.jRadioButtonEspecial.isSelected())) {
            cot.jButtonCotizar.setEnabled(false);
            JOptionPane.showMessageDialog(null, "Existen campos vacios o no seleccionados.\nIntente de nuevo.", "WARNING!!", JOptionPane.WARNING_MESSAGE);
            cot.jCheckBoxHabilitar.setSelected(false);
        } else {
            cot.jButtonCotizar.setEnabled(true);
            cot.jTextFieldNoPaquetes.setEnabled(false);
            cot.jComboBoxDestinoDept.setEnabled(false);
            cot.jComboBoxOrigenDept.setEnabled(false);
            cot.jTextFieldDireccionOrigen.setEnabled(false);
            cot.jTextFieldDireccionDestino.setEnabled(false);
            cot.jTextFieldPequeño.setEnabled(false);
            cot.jTextFieldMediano.setEnabled(false);
            cot.jTextFieldGrande.setEnabled(false);
            cot.jRadioButtonEspecial.setEnabled(false);
            cot.jRadioButtonEstandar.setEnabled(false);
            cot.jRadioButtonPequeño.setEnabled(false);
            cot.jRadioButtonMediano.setEnabled(false);
            cot.jRadioButtonGrande.setEnabled(false);
            cot.jComboBoxOrigenMuni.setEnabled(false);
            cot.jComboBoxDestinoMuni.setEnabled(false);
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
                HabilitarButtonCotizacion();
            } else {
                HabilitarButtonCotizacion();
                cot.jButtonCotizar.setEnabled(false);

                cot.jTextFieldNoPaquetes.setEnabled(true);
                cot.jComboBoxDestinoDept.setEnabled(true);
                cot.jComboBoxOrigenDept.setEnabled(true);
                cot.jTextFieldDireccionOrigen.setEnabled(true);
                cot.jTextFieldDireccionDestino.setEnabled(true);
                cot.jTextFieldPequeño.setEnabled(true);
                cot.jTextFieldMediano.setEnabled(true);
                cot.jTextFieldGrande.setEnabled(true);
                cot.jRadioButtonEspecial.setEnabled(true);
                cot.jRadioButtonEstandar.setEnabled(true);
                cot.jRadioButtonPequeño.setEnabled(true);
                cot.jRadioButtonMediano.setEnabled(true);
                cot.jRadioButtonGrande.setEnabled(true);
                cot.jComboBoxOrigenMuni.setEnabled(true);
                cot.jComboBoxDestinoMuni.setEnabled(true);
            }
        } else if (e.getSource() == cot.jRadioButtonCobroEntrega) {
            ContraEntrega();
            HabilitarButtonEnviar();
        } else if (e.getSource() == cot.jRadioButtonCobroCuenta) {
            ContraEntrega();
            HabilitarButtonEnviar();
        } else if (e.getSource() == cot.jButtonEnviar) {
            RealizarCompra();
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
            if (cot.jComboBoxListaFacturacion.getSelectedIndex() > 0) {
                ConfirmarCVV();
                HabilitarButtonEnviar();
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
