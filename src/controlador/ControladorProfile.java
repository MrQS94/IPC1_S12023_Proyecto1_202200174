/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.ModeloPersona;
import vista.Profile;

/**
 *
 * @author queza
 */
public class ControladorProfile implements ActionListener {

    List<ModeloPersona> listPersona;
    Profile prof;
    private String dpi;
    private String imgUrl;

    public ControladorProfile(Profile profile, List<ModeloPersona> listPersona, String dpi) {
        this.prof = profile;
        this.listPersona = listPersona;
        this.dpi = dpi;
        this.prof.jButtonSubir.addActionListener(this);
        this.prof.jButtonActualizar.addActionListener(this);
        PullPersona();
        PullKiosco();
        PullFoto();
    }

    private void PullPersona() {
        for (int i = 0; i < listPersona.size(); i++) {
            if (listPersona.get(i).getDpi().equals(dpi)) {
                prof.jTextFieldNombre.setText(listPersona.get(i).getNombre());
                prof.jTextFieldApellido.setText(listPersona.get(i).getApellido());
                prof.jTextFieldNac.setText(listPersona.get(i).getFechaNac());
                prof.jTextFieldDPI.setText(listPersona.get(i).getDpi());
                if (listPersona.get(i).getGenero().equals("Hombre")) {
                    prof.jRadioButtonHombre.setSelected(true);
                } else if (listPersona.get(i).getGenero().equals("Mujer")) {
                    prof.jRadioButtonMujer.setSelected(true);
                }
                prof.jTextFieldEmail.setText(listPersona.get(i).getCorreo());
                prof.jPasswordField.setText(listPersona.get(i).getPass());
                prof.jPasswordFieldConfirm.setText(listPersona.get(i).getPass());
                prof.jTextFieldAlias.setText(listPersona.get(i).getAlias());
                prof.jTextFieldTelefono.setText(String.valueOf(listPersona.get(i).getTelefono()));
                break;
            }
        }
    }

    private void PullKiosco() {
        for (int i = 0; i < listPersona.size(); i++) {
            if (listPersona.get(i).getDpi().equals(dpi)) {
                if (listPersona.get(i).getKiosco() != null) {
                    if (!listPersona.get(i).getKiosco().equals("")) {
                        prof.jComboBoxRol.setSelectedIndex(1);
                        prof.jComboBoxKiosco.addItem(listPersona.get(i).getKiosco());
                        break;
                    }
                }
            }
        }
    }

    private void PullFoto() {
        String foto = "";
        for (int i = 0; i < listPersona.size(); i++) {
            if (listPersona.get(i).getDpi().equals(dpi)) {
                foto = listPersona.get(i).getFotografia();
                break;
            }
        }
        if (!foto.equals("No existe foto")) {
            Image imagen = new ImageIcon(foto).getImage();
            ImageIcon icono = new ImageIcon(imagen.getScaledInstance(125, 115, Image.SCALE_SMOOTH));
            prof.jLabelFotografia.setIcon(icono);
            prof.jButtonSubir.setEnabled(false);
            prof.jButtonActualizar.setEnabled(false);
        } else {
            prof.jButtonSubir.setEnabled(true);
        }
    }

    private void CargarImagen() {
        JFileChooser jf = new JFileChooser();
        FileNameExtensionFilter buscador = new FileNameExtensionFilter("JPG, JPEG PNG", "jpg", "png", "jpeg");
        jf.setFileFilter(buscador);
        int respuesta = jf.showOpenDialog(prof);

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            imgUrl = jf.getSelectedFile().getPath();
            Image imagen = new ImageIcon(imgUrl).getImage();
            ImageIcon icono = new ImageIcon(imagen.getScaledInstance(
                    prof.jLabelFotografia.getWidth(), prof.jLabelFotografia.getHeight(), Image.SCALE_SMOOTH));
            prof.jLabelFotografia.setIcon(icono);
        }
    }

    private void GuardarImagen() {
        for (int i = 0; i < listPersona.size(); i++) {
            if (listPersona.get(i).getDpi().equals(dpi)) {
                listPersona.get(i).setFotografia(imgUrl);
                prof.jButtonSubir.setEnabled(false);
                prof.jButtonActualizar.setEnabled(false);
                JOptionPane.showMessageDialog(null, "La información ha sido actualizada!", "INFORMACION ACTUALIZADA!", JOptionPane.INFORMATION_MESSAGE);
                break;
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == prof.jButtonSubir) {
            CargarImagen();
        } else if (e.getSource() == prof.jButtonActualizar) {
            GuardarImagen();
        }
    }

}
