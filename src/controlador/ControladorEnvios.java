/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.Desktop;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelo.ModeloCotizacion;
import vista.EnviosSolicitados;

/**
 *
 * @author queza
 */
public class ControladorEnvios implements MouseListener {

    List<ModeloCotizacion> list;
    EnviosSolicitados envio;
    private String dpi;

    public ControladorEnvios(EnviosSolicitados envio, List<ModeloCotizacion> list, String dpi) {
        this.envio = envio;
        this.list = list;
        this.dpi = dpi;
        this.envio.jTable.addMouseListener(this);
        LlenarTabla();
    }
 
    private void LlenarTabla() {      
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Código de Paquete", "Tipo de Servicio",
            "Destinatario", "Total Envio", "Total de Pago"}, list.size());
        envio.jTable.setModel(dtm);
        TableModel model = envio.jTable.getModel();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getDpi().equals(dpi)) {
                model.setValueAt("RA00" + list.get(i).getCodigoPaquete() + "CN", i, 0);
                model.setValueAt(list.get(i).getTipoServicio(), i, 1);
                model.setValueAt(list.get(i).getDestino(), i, 2);
                model.setValueAt(list.get(i).getTotPagar(), i, 3);
                model.setValueAt(list.get(i).getTipoPago(), i, 4);
            }
        }
    }
    
    private void DescargarFactura(int select) {
        String estructuraHTML = "";
        String tablaHTML = "";

        tablaHTML
                += "<tr>"
                + "<td> #0000" + list.get(select).getNoFactura() + "</td>"
                + "<td> RA00" + list.get(select).getCodigoPaquete() + "CN</td>"
                + "<td>" + list.get(select).getOrigen() + "</td>"
                + "<td>" + list.get(select).getDestino() + "</td>"
                + "<td>" + list.get(select).getNit() + "</td>"
                + "<td>" + list.get(select).getTipoPago() + "</td>"
                + "<td>" + list.get(select).getPackageSize() + "</td>"
                + "<td>" + list.get(select).getNoPaquetes() + "</td>"
                + "<td>" + list.get(select).getTotPagar() + "</td>"
                + "</tr>";

        estructuraHTML += """
                          <!DOCTYPE html>
                          <!-- Acá importar archivos necesarios --->
                          <html>
                              <head>
                                  <title>Factura</title>
                                  <meta charset="UTF-8">
                                  <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
                              </head>
                              <body>
                                  <!-- Acá compenzar a conectar con el programa  -->
                                  <div class="container">
                                      <h1>Factura sobre la compra</h1>
                                      <hr>
                                      <table class="table table-bordered">
                                          <tr>
                                              <th class="text-center">Número de factura</th>
                                              <th class="text-center">Código de paquete</th>
                                              <th class="text-center">Origen</th>
                                              <th class="text-center">Destino</th>
                                              <th class="text-center">NIT</th>
                                              <th class="text-center">Tipo de pago</th>
                                              <th class="text-center">Peso del paquete (lb)</th>
                                              <th class="text-center">Número de paquetes</th>
                                              <th class="text-center">Total a pagar</th>
                                          </tr>
                                          
                                          <!-- Acá pedir el ArrayList que recorra hasta detenerse -->
                                         """
                + tablaHTML + """
                                     </table>
                                  </div>
                              </body>
                          </html>
                        """;
        File file = new File("factura.html");
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter("factura.html");
            fw.write(estructuraHTML);
            fw.close();
            Desktop desk = Desktop.getDesktop();
            desk.open(file);
        } catch (IOException ex) {
            Logger.getLogger(ControladorCotizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void DescargarGuia(int select) {
        String estructuraHTML = "";
        String tablaHTML = "";

        tablaHTML
                += "<tr>"
                + "<td>" + list.get(select).getGuia() + "</td>"
                + "<td> RA00" + list.get(select).getCodigoPaquete() + "CN</td>"
                + "<td>" + list.get(select).getOrigen() + "</td>"
                + "<td>" + list.get(select).getDestino() + "</td>"
                + "<td>" + list.get(select).getTipoPago() + "</td>"
                + "<td>" + list.get(select).getPackageSize() + "</td>"
                + "<td>" + list.get(select).getNoPaquetes() + "</td>"
                + "<td>" + list.get(select).getFechaEnvio() + "</td>"
                + "<td>" + list.get(select).getTotPagar() + "</td>"
                + "</tr>";

        estructuraHTML += """
                          <!DOCTYPE html>
                                          <!-- Acá importar archivos necesarios --->
                                          <html>
                                              <head>
                                                  <title>Guía</title>
                                                  <meta charset="UTF-8">
                                                  <meta name="viewport" content="width=device-width, initial-scale=1.0">
                                                  <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-GLhlTQ8iRABdZLl6O3oVMWSktQOp6b7In1Zl3/Jr59b6EGGoI1aFkw7cmDA6j6gD" crossorigin="anonymous">
                                              </head>
                                              <body>
                                                  <!-- Acá compenzar a conectar con el programa  -->
                                                  <div class="container">
                                                      <h1>Guia sobre la entrega</h1>
                                                      <hr>
                                                      <table class="table table-bordered">
                                                          <tr>
                                                              <th class="text-center">Código de guía</th>
                                                              <th class="text-center">Código de paquete</th>
                                                              <th class="text-center">Origen</th>
                                                              <th class="text-center">Destino</th>
                                                              <th class="text-center">Tipo de pago</th>
                                                              <th class="text-center">Peso del paquete</th>       
                                                              <th class="text-center">Número de paquetes</th>
                                                              <th class="text-center">Fecha y hora de Envío</th>
                                                              <th class="text-center">Total a pagar</th>
                                                          </tr>
                                         """
                + tablaHTML + """
                                     </table>
                                  </div>
                              </body>
                          </html>
                        """;
        File file = new File("guia.html");
        try {
            file.createNewFile();
            FileWriter fw = new FileWriter("guia.html");
            fw.write(estructuraHTML);
            fw.close();
            Desktop desk = Desktop.getDesktop();
            desk.open(file);
        } catch (IOException ex) {
            Logger.getLogger(ControladorCotizacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        int select = envio.jTable.rowAtPoint(e.getPoint());
        if (e.getSource() == envio.jTable) {
            int hola = Integer.parseInt(JOptionPane.showInputDialog(null, "0) Factura\n" + "1) Guia" + "\nIngrese entre 0 y 1 para abrir un archivo.", "FACTURA O GUÍA", JOptionPane.QUESTION_MESSAGE));
            if (hola == 0) {
                DescargarFactura(select);
            } else if (hola == 1) {
                DescargarGuia(select);
            }
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
