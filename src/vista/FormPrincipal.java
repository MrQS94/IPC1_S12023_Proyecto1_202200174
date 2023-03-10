/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controlador.ControladorCotizacion;
import controlador.ControladorEnvios;
import controlador.ControladorFacturacion;
import controlador.ControladorKioscos;
import controlador.ControladorPrecios;
import controlador.ControladorPrincipal;
import controlador.ControladorDepartamentos;
import controlador.ControladorRegistro;
import controlador.ControladorReportes;
import java.awt.Graphics;
import java.awt.Image;
import java.util.List;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import modelo.ModeloCotizacion;
import modelo.ModeloDepartamentos;
import modelo.ModeloFacturacion;
import modelo.ModeloKiosco;
import modelo.ModeloPersona;
import modelo.ModeloPrecios;

/**
 *
 * @author queza
 */
public class FormPrincipal extends javax.swing.JFrame {

    /**
     * Creates new form FramePrincipal
     */
    List<ModeloPersona> listPersona;
    List<ModeloDepartamentos> listDepart;
    List<ModeloKiosco> listKiosco;
    List<ModeloPrecios> listPrecios;
    List<ModeloFacturacion> listFact;
    List<ModeloCotizacion> listCot;

    private String dpi;

    public FormPrincipal() {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
    }

    public FormPrincipal(List<ModeloPersona> list, List<ModeloDepartamentos> listDepart,
            List<ModeloKiosco> listKiosco, List<ModeloPrecios> listPrecios, List<ModeloFacturacion> listFact,
            List<ModeloCotizacion> listCot, String dpi) {
        initComponents();
        this.setExtendedState(JFrame.MAXIMIZED_BOTH);
        this.listPersona = list;
        this.listDepart = listDepart;
        this.listKiosco = listKiosco;
        this.listPrecios = listPrecios;
        this.listFact = listFact;
        this.listCot = listCot;
        this.dpi = dpi;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        ImageIcon icon = new ImageIcon(getClass().getResource("/recursos/fondo.jfif"));
        Image image = icon.getImage();
        jDesktopPane = new javax.swing.JDesktopPane(){
            public void paintComponent(Graphics g){
                g.drawImage(image,0,0,getWidth(),getHeight(),this);
            }
        }
        ;
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        jMenuItemVolver = new javax.swing.JMenuItem();
        jMenuItemExit = new javax.swing.JMenuItem();
        jMenuAdmin = new javax.swing.JMenu();
        jMenuItemKioscos = new javax.swing.JMenuItem();
        jMenuItemRegiones = new javax.swing.JMenuItem();
        jMenuItemDepartamentos = new javax.swing.JMenuItem();
        jMenuItemRegistro = new javax.swing.JMenuItem();
        jMenuItemReportes = new javax.swing.JMenuItem();
        jMenuCliente = new javax.swing.JMenu();
        jMenuItemCotizacion = new javax.swing.JMenuItem();
        jMenuItemFacturacion = new javax.swing.JMenuItem();
        jMenuItemEnvios = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Pantalla Principal");

        jDesktopPane.setName(""); // NOI18N

        javax.swing.GroupLayout jDesktopPaneLayout = new javax.swing.GroupLayout(jDesktopPane);
        jDesktopPane.setLayout(jDesktopPaneLayout);
        jDesktopPaneLayout.setHorizontalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 606, Short.MAX_VALUE)
        );
        jDesktopPaneLayout.setVerticalGroup(
            jDesktopPaneLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 381, Short.MAX_VALUE)
        );

        jMenu1.setText("Archivo");

        jMenuItemVolver.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_0, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItemVolver.setText("Inicio de Sesión");
        jMenuItemVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemVolverActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemVolver);

        jMenuItemExit.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_F4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItemExit.setText("Salir");
        jMenuItemExit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemExitActionPerformed(evt);
            }
        });
        jMenu1.add(jMenuItemExit);

        jMenuBar1.add(jMenu1);

        jMenuAdmin.setText("Admin");

        jMenuItemKioscos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_1, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItemKioscos.setText("Manejo de Kioscos");
        jMenuItemKioscos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemKioscosActionPerformed(evt);
            }
        });
        jMenuAdmin.add(jMenuItemKioscos);

        jMenuItemRegiones.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_2, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItemRegiones.setText("Manejo de Regiones y Precios");
        jMenuItemRegiones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRegionesActionPerformed(evt);
            }
        });
        jMenuAdmin.add(jMenuItemRegiones);

        jMenuItemDepartamentos.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_3, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItemDepartamentos.setText("Manejo de Departamentos y Municipios");
        jMenuItemDepartamentos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemDepartamentosActionPerformed(evt);
            }
        });
        jMenuAdmin.add(jMenuItemDepartamentos);

        jMenuItemRegistro.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_4, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItemRegistro.setText("Registro de usuarios");
        jMenuItemRegistro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemRegistroActionPerformed(evt);
            }
        });
        jMenuAdmin.add(jMenuItemRegistro);

        jMenuItemReportes.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_5, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItemReportes.setText("Reportes");
        jMenuItemReportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemReportesActionPerformed(evt);
            }
        });
        jMenuAdmin.add(jMenuItemReportes);

        jMenuBar1.add(jMenuAdmin);

        jMenuCliente.setText("Cliente");

        jMenuItemCotizacion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_6, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItemCotizacion.setText("Cotización, pago, descarga de factura y guía.");
        jMenuItemCotizacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemCotizacionActionPerformed(evt);
            }
        });
        jMenuCliente.add(jMenuItemCotizacion);

        jMenuItemFacturacion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_7, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItemFacturacion.setText("Registro de Facturacion");
        jMenuItemFacturacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemFacturacionActionPerformed(evt);
            }
        });
        jMenuCliente.add(jMenuItemFacturacion);

        jMenuItemEnvios.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_9, java.awt.event.InputEvent.ALT_DOWN_MASK));
        jMenuItemEnvios.setText("Ver Envíos Solicitados ");
        jMenuItemEnvios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jMenuItemEnviosActionPerformed(evt);
            }
        });
        jMenuCliente.add(jMenuItemEnvios);

        jMenuBar1.add(jMenuCliente);

        setJMenuBar(jMenuBar1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jDesktopPane, javax.swing.GroupLayout.Alignment.TRAILING)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jMenuItemExitActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemExitActionPerformed
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_jMenuItemExitActionPerformed

    private void EvitarAbrir(JInternalFrame frame) {
        boolean show = true;
        for (int i = 0; i < jDesktopPane.getComponentCount(); i++) {
            if (frame.getClass().isInstance(jDesktopPane.getComponent(i))) {
                show = false;
            }
        }
        if (show) {
            jDesktopPane.add(frame);
            frame.setVisible(true);
        }
    }

    private void jMenuItemRegistroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRegistroActionPerformed
        // TODO add your handling code here:
        RegistroUsuario registro = new RegistroUsuario();
        ControladorRegistro controlador = new ControladorRegistro(registro, listPersona, listKiosco);
        EvitarAbrir(registro);
    }//GEN-LAST:event_jMenuItemRegistroActionPerformed

    private void jMenuItemCotizacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemCotizacionActionPerformed
        // TODO add your handling code here:
        Cotizacion cotizacion = new Cotizacion();
        ControladorCotizacion cot = new ControladorCotizacion(cotizacion, listDepart,
                listPrecios, listFact, listCot, listPersona, listKiosco, dpi);
        EvitarAbrir(cotizacion);
    }//GEN-LAST:event_jMenuItemCotizacionActionPerformed

    private void jMenuItemKioscosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemKioscosActionPerformed
        // TODO add your handling code here:
        ManejoKioscos manejo = new ManejoKioscos();
        ControladorKioscos kiosco = new ControladorKioscos(manejo, listPrecios, listKiosco);
        EvitarAbrir(manejo);
    }//GEN-LAST:event_jMenuItemKioscosActionPerformed

    private void jMenuItemDepartamentosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemDepartamentosActionPerformed
        // TODO add your handling code here:
        ManejoDepartamentos manejo = new ManejoDepartamentos();
        ControladorDepartamentos control = new ControladorDepartamentos(manejo, listDepart);
        EvitarAbrir(manejo);
    }//GEN-LAST:event_jMenuItemDepartamentosActionPerformed

    private void jMenuItemRegionesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemRegionesActionPerformed
        // TODO add your handling code here:
        ManejoPrecios manejo = new ManejoPrecios();
        ControladorPrecios control = new ControladorPrecios(manejo, listPrecios);
        EvitarAbrir(manejo);
    }//GEN-LAST:event_jMenuItemRegionesActionPerformed

    private void jMenuItemFacturacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemFacturacionActionPerformed
        // TODO add your handling code here:
        DatosFacturacion datos = new DatosFacturacion();
        ControladorFacturacion control = new ControladorFacturacion(datos, listFact, listPersona, dpi);
        EvitarAbrir(datos);
    }//GEN-LAST:event_jMenuItemFacturacionActionPerformed

    private void jMenuItemEnviosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemEnviosActionPerformed
        // TODO add your handling code here:
        EnviosSolicitados envio = new EnviosSolicitados();
        ControladorEnvios en = new ControladorEnvios(envio, listCot, dpi);
        EvitarAbrir(envio);
    }//GEN-LAST:event_jMenuItemEnviosActionPerformed

    private void jMenuItemVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemVolverActionPerformed
        // TODO add your handling code here:
        Autenticacion aut = new Autenticacion();
        ControladorPrincipal control = new ControladorPrincipal(aut, listKiosco,
                listPrecios, listDepart, listFact, listPersona, listCot);
        aut.show();
        this.hide();
    }//GEN-LAST:event_jMenuItemVolverActionPerformed

    private void jMenuItemReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jMenuItemReportesActionPerformed
        // TODO add your handling code here:
        Reporte reporte = new Reporte();
        ControladorReportes control = new ControladorReportes(reporte, listPrecios, listCot, listPersona);
        EvitarAbrir(reporte);
    }//GEN-LAST:event_jMenuItemReportesActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FormPrincipal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FormPrincipal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JDesktopPane jDesktopPane;
    private javax.swing.JMenu jMenu1;
    public javax.swing.JMenu jMenuAdmin;
    private javax.swing.JMenuBar jMenuBar1;
    public javax.swing.JMenu jMenuCliente;
    private javax.swing.JMenuItem jMenuItemCotizacion;
    private javax.swing.JMenuItem jMenuItemDepartamentos;
    private javax.swing.JMenuItem jMenuItemEnvios;
    private javax.swing.JMenuItem jMenuItemExit;
    private javax.swing.JMenuItem jMenuItemFacturacion;
    private javax.swing.JMenuItem jMenuItemKioscos;
    private javax.swing.JMenuItem jMenuItemRegiones;
    private javax.swing.JMenuItem jMenuItemRegistro;
    private javax.swing.JMenuItem jMenuItemReportes;
    private javax.swing.JMenuItem jMenuItemVolver;
    // End of variables declaration//GEN-END:variables
}
