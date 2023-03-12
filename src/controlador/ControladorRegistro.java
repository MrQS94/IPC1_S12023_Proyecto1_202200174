/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;
import modelo.ModeloKiosco;
import modelo.ModeloPersona;
import vista.RegistroUsuario;

/**
 *
 * @author queza
 */
public class ControladorRegistro extends ControladorPrincipal implements MouseListener, KeyListener {

    List<ModeloPersona> listPersona;
    List<ModeloKiosco> listKiosco;
    private String imgUrl = "";

    public ControladorRegistro(RegistroUsuario registro) {
        super(registro);
    }

    public ControladorRegistro(RegistroUsuario registro, List<ModeloPersona> list, List<ModeloKiosco> listKiosco) {
        super(registro);
        this.registro.jButtonRegistrar.addActionListener(this);
        this.registro.jTextFieldYYYY.addMouseListener(this);
        this.registro.jTextFieldYYYY.addKeyListener(this);
        this.registro.jTextFieldDPI.addKeyListener(this);
        this.registro.jTextFieldTelefono.addKeyListener(this);
        this.registro.jButtonSubir.addActionListener(this);
        this.registro.jTextFieldNombre.addKeyListener(this);
        this.registro.jTextFieldApellido.addKeyListener(this);
        this.registro.jTextFieldAlias.addKeyListener(this);
        this.registro.jCheckBoxMostrar.addActionListener(this);
        this.registro.jCheckBoxConfirmar.addActionListener(this);
        this.registro.jButtonRegistrar.setVisible(false);
        this.registro.jComboBoxRol.addActionListener(this);
        this.listPersona = list;
        this.listKiosco = listKiosco;
        AgregarKioscos();
    }

    private void AgregarKioscos() {
        if (!listKiosco.isEmpty()) {
            registro.jComboBoxRol.addItem("Kioscos");
        }
    }

    private void GuardarDatos() {
        String nombre = registro.jTextFieldNombre.getText();
        String apellido = registro.jTextFieldApellido.getText();
        String correo = registro.jTextFieldEmail.getText();
        String pass = registro.jPasswordField.getText();
        String dpi = registro.jTextFieldDPI.getText();
        String fechaNac = registro.jComboBoxDD.getSelectedItem() + "/"
                + registro.jComboBoxMM.getSelectedItem() + "/"
                + registro.jTextFieldYYYY.getText();
        String genero = "";
        if (registro.jRadioButtonHombre.isSelected()) {
            genero = "Hombre";
        } else if (registro.jRadioButtonMujer.isSelected()) {
            genero = "mujer";
        } else {
            JOptionPane.showMessageDialog(null, "No está seleccionado uno de los generos.", "ADVERTENCIA!", JOptionPane.WARNING_MESSAGE);
            registro.jRadioButtonHombre.requestFocus();
        }

        String nacionalidad = (String) registro.jComboBoxNacionalidad.getSelectedItem();
        String alias = registro.jTextFieldAlias.getText();
        int telefono = Integer.parseInt(registro.jTextFieldTelefono.getText());
        String rol = (String) registro.jComboBoxRol.getSelectedItem();
        String kiosco = "";

        if ((registro.jComboBoxKiosco.getSelectedItem() != null)) {
            kiosco = registro.jComboBoxKiosco.getSelectedItem().toString();
        }

        String foto = imgUrl;
        if (imgUrl.isEmpty()) {
            foto = "No existe foto";
        }

        ModeloPersona mod;
        if (kiosco.isEmpty()) {
            mod = new ModeloPersona(nombre, apellido, correo, pass, dpi,
                    fechaNac, genero, nacionalidad, alias, telefono, rol, foto);
        } else {
            mod = new ModeloPersona(nombre, apellido, correo, pass, dpi,
                    fechaNac, genero, nacionalidad, alias, telefono, rol, kiosco, foto);
        }

        listPersona.add(mod);
        JOptionPane.showMessageDialog(null, "Usuario registrado!", "INFORMATION!", JOptionPane.INFORMATION_MESSAGE);
    }

    private String ReconocerPass(char[] c) {
        String pass = "";
        for (int i = 0; i < c.length; i++) {
            pass += c[i];
        }
        return pass;
    }

    private static boolean CaracteresPass(String sd) {
        int upper = 0;
        int digit = 0;
        int lower = 0;
        int charr = 0;
        for (int i = 0; i < sd.length(); i++) {
            if (Character.isUpperCase(sd.charAt(i))) {
                upper++;
            }
            if (Character.isDigit(sd.charAt(i))) {
                digit++;
            }
            if (Character.isLowerCase(sd.charAt(i))) {
                lower++;
            }
            if (sd.charAt(i) >= 33 && sd.charAt(i) <= 47 && sd.charAt(i) != 32) {
                charr++;
            }
        }
        return (upper > 0 && digit > 0 && lower > 0 && charr > 0);
    }

    private boolean ComprobarPass() {
        char[] c = registro.jPasswordField.getPassword();
        char[] c2 = registro.jPasswordFieldConfirm.getPassword();
        String pass = ReconocerPass(c);
        String pass2 = ReconocerPass(c2);
        return pass.equals(pass2) && CaracteresPass(pass) && CaracteresPass(pass2);
    }

    private boolean isDpiEmailValid(String dpi, String email) {
        for (int i = 0; i < listPersona.size(); i++) {
            if (listPersona.get(i).getDpi().equals(dpi) || listPersona.get(i).getCorreo().equals(email)) {
                return true;
            }
        }
        return false;
    }

    private boolean ComprobarTextField() {
        return registro.jTextFieldNombre.getText().isBlank()
                || registro.jTextFieldApellido.getText().isBlank()
                || registro.jTextFieldAlias.getText().isBlank()
                || registro.jTextFieldDPI.getText().isBlank()
                || registro.jTextFieldTelefono.getText().isBlank()
                || registro.jPasswordField.getText().isBlank()
                || registro.jPasswordFieldConfirm.getText().isBlank()
                || registro.jTextFieldYYYY.getText().isBlank()
                || registro.jTextFieldYYYY.getText().equals("YYYY")
                || registro.jTextFieldEmail.getText().isBlank()
                || registro.jComboBoxDD.getSelectedItem().equals("DD")
                || registro.jComboBoxMM.getSelectedItem().equals("MM")
                || (!registro.jRadioButtonHombre.isSelected() && !registro.jRadioButtonMujer.isSelected());
    }

    private void CargarImagen() {
        JFileChooser jf = new JFileChooser();
        FileNameExtensionFilter buscador = new FileNameExtensionFilter("JPG, JPEG PNG", "jpg", "png", "jpeg");
        jf.setFileFilter(buscador);
        int respuesta = jf.showOpenDialog(registro);

        if (respuesta == JFileChooser.APPROVE_OPTION) {
            imgUrl = jf.getSelectedFile().getPath();
            Image imagen = new ImageIcon(imgUrl).getImage();
            ImageIcon icono = new ImageIcon(imagen.getScaledInstance(
                    registro.jLabelFotografia.getWidth(), registro.jLabelFotografia.getHeight(), Image.SCALE_SMOOTH));
            registro.jLabelFotografia.setIcon(icono);
        }
    }

    private void PullKioscos() {
        for (int i = 0; i < listKiosco.size(); i++) {
            registro.jComboBoxKiosco.addItem(listKiosco.get(i).getNombre());
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == registro.jButtonRegistrar) {
            GuardarDatos();

            registro.jButtonRegistrar.setEnabled(false);
            registro.jButtonRegistrar.setVisible(false);
            registro.jCheckBoxConfirmar.setVisible(true);
            registro.jCheckBoxConfirmar.setEnabled(true);
            registro.jCheckBoxConfirmar.setSelected(false);
            registro.jTextFieldNombre.requestFocus();

            registro.jTextFieldNombre.setText("");
            registro.jTextFieldApellido.setText("");
            registro.jTextFieldAlias.setText("");
            registro.jTextFieldDPI.setText("");
            registro.jTextFieldTelefono.setText("");
            registro.jPasswordField.setText("");
            registro.jPasswordFieldConfirm.setText("");
            registro.jTextFieldYYYY.setText("YYYY");
            registro.jTextFieldEmail.setText("");
            Icon icon = new ImageIcon();
            registro.jLabelFotografia.setIcon(icon);
            registro.jComboBoxDD.setSelectedIndex(0);
            registro.jComboBoxMM.setSelectedIndex(0);
            registro.jRadioButtonHombre.setSelected(false);
            registro.jRadioButtonMujer.setSelected(false);
            registro.jComboBoxNacionalidad.setSelectedIndex(0);
            registro.jComboBoxRol.setSelectedIndex(0);
            registro.buttonGroupGenero.clearSelection();

            registro.jCheckBoxConfirmar.setEnabled(true);
            registro.jCheckBoxConfirmar.setVisible(true);
            registro.jTextFieldNombre.setEnabled(true);
            registro.jTextFieldApellido.setEnabled(true);
            registro.jTextFieldAlias.setEnabled(true);
            registro.jTextFieldDPI.setEnabled(true);
            registro.jTextFieldTelefono.setEnabled(true);
            registro.jPasswordField.setEnabled(true);
            registro.jPasswordFieldConfirm.setEnabled(true);
            registro.jTextFieldYYYY.setEnabled(true);
            registro.jTextFieldEmail.setEnabled(true);
            registro.jComboBoxDD.setEnabled(true);
            registro.jComboBoxMM.setEnabled(true);
            registro.jComboBoxNacionalidad.setEnabled(true);
            registro.jComboBoxRol.setEnabled(true);
            registro.jButtonSubir.setEnabled(true);
            registro.jCheckBoxMostrar.setEnabled(true);
            registro.jRadioButtonHombre.setEnabled(true);
            registro.jRadioButtonMujer.setEnabled(true);
            registro.jCheckBoxConfirmar.setText("Confirmar Campos");
        } else if (e.getSource() == registro.jButtonSubir) {
            CargarImagen();
        } else if (e.getSource() == registro.jCheckBoxMostrar) {
            MostrarPass(registro.jCheckBoxMostrar, registro.jPasswordFieldConfirm);
            MostrarPass(registro.jCheckBoxMostrar, registro.jPasswordField);
        } else if (e.getSource() == registro.jCheckBoxConfirmar) {
            if (registro.jCheckBoxConfirmar.isSelected()) {
                if (ComprobarTextField() || isDpiEmailValid(registro.jTextFieldDPI.getText(), registro.jTextFieldEmail.getText())) {
                    registro.jCheckBoxConfirmar.setSelected(false);
                    JOptionPane.showMessageDialog(null, "Existen valores vacíos o repetidos.", "WARNING!", JOptionPane.WARNING_MESSAGE);
                } else {
                    if (ComprobarPass()) {
                        registro.jButtonRegistrar.setEnabled(true);
                        registro.jButtonRegistrar.setVisible(true);
                        registro.jCheckBoxConfirmar.setText("Editar campos");

                        registro.jTextFieldNombre.setEnabled(false);
                        registro.jTextFieldApellido.setEnabled(false);
                        registro.jTextFieldAlias.setEnabled(false);
                        registro.jTextFieldDPI.setEnabled(false);
                        registro.jTextFieldTelefono.setEnabled(false);
                        registro.jPasswordField.setEnabled(false);
                        registro.jPasswordFieldConfirm.setEnabled(false);
                        registro.jTextFieldYYYY.setEnabled(false);
                        registro.jTextFieldEmail.setEnabled(false);
                        registro.jComboBoxDD.setEnabled(false);
                        registro.jComboBoxMM.setEnabled(false);
                        registro.jRadioButtonHombre.setEnabled(false);
                        registro.jRadioButtonMujer.setEnabled(false);
                        registro.jComboBoxNacionalidad.setEnabled(false);
                        registro.jComboBoxRol.setEnabled(false);
                        registro.jButtonSubir.setEnabled(false);
                        registro.jCheckBoxMostrar.setEnabled(false);
                        registro.jCheckBoxMostrar.setSelected(false);
                        registro.jPasswordField.setEchoChar('*');
                        registro.jPasswordFieldConfirm.setEchoChar('*');
                    } else {
                        registro.jPasswordField.requestFocus();
                        JOptionPane.showMessageDialog(null, """
                                                        Las contraseñas no son iguales o
                                                        La contraseña debe llevar: 
                                                        Letras mayúsculas
                                                        Letras minúsculas
                                                        Números
                                                        Caracteres especiales (Ej. #,%,&,_,etc.)
                                                        Sin espacios
                                                        """, "WARNING!", JOptionPane.WARNING_MESSAGE);
                    }
                }
            } else if (!registro.jCheckBoxConfirmar.isSelected()) {
                registro.jCheckBoxConfirmar.setText("Comprobar texto");
                registro.jButtonRegistrar.setEnabled(false);
                registro.jButtonRegistrar.setVisible(false);

                registro.jTextFieldNombre.setEnabled(true);
                registro.jTextFieldApellido.setEnabled(true);
                registro.jTextFieldAlias.setEnabled(true);
                registro.jTextFieldDPI.setEnabled(true);
                registro.jTextFieldTelefono.setEnabled(true);
                registro.jPasswordField.setEnabled(true);
                registro.jPasswordFieldConfirm.setEnabled(true);
                registro.jTextFieldYYYY.setEnabled(true);
                registro.jTextFieldEmail.setEnabled(true);
                registro.jComboBoxDD.setEnabled(true);
                registro.jComboBoxMM.setEnabled(true);
                registro.jRadioButtonHombre.setEnabled(true);
                registro.jRadioButtonMujer.setEnabled(true);
                registro.jComboBoxNacionalidad.setEnabled(true);
                registro.jComboBoxRol.setEnabled(true);
                registro.jButtonSubir.setEnabled(true);
                registro.jCheckBoxMostrar.setEnabled(true);
            }
        } else if (e.getSource() == registro.jComboBoxRol) {
            if (registro.jComboBoxRol.getSelectedIndex() == 1) {
                PullKioscos();
            } else if (registro.jComboBoxRol.getSelectedIndex() == 0) {
                registro.jComboBoxKiosco.removeAllItems();
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (registro.jTextFieldYYYY.getText().equals("YYYY") && e.getSource() == registro.jTextFieldYYYY) {
            registro.jTextFieldYYYY.setText("");
        }
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
    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (e.getSource() == registro.jTextFieldNombre
                || e.getSource() == registro.jTextFieldApellido
                || e.getSource() == registro.jTextFieldAlias) {
            if (!((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z'))) {
                e.consume();
            }
        } else if (e.getSource() == registro.jTextFieldTelefono) {
            if (registro.jTextFieldTelefono.getText().length() > 7 || (c >= 58 || c <= 46)) {
                e.consume();
            }
        } else if (e.getSource() == registro.jTextFieldYYYY) {
            if (registro.jTextFieldYYYY.getText().length() > 3 || (c >= 58 || c <= 46)) {
                e.consume();
            }
        } else if (e.getSource() == registro.jTextFieldDPI) {
            if (registro.jTextFieldDPI.getText().length() > 12 || (c >= 58 || c <= 46)) {
                e.consume();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e
    ) {

    }

    @Override
    public void keyReleased(KeyEvent e
    ) {

    }
}
