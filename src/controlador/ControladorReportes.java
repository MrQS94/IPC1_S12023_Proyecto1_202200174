/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.util.List;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import modelo.ModeloPrecios;
import modelo.ModeloCotizacion;
import modelo.ModeloPersona;
import vista.Reporte;

/**
 *
 * @author queza
 */
public class ControladorReportes {

    Reporte repo;
    List<ModeloPrecios> listReg;
    List<ModeloCotizacion> listCot;
    List<ModeloPersona> listPersona;
    //List<Integer> cantidadActual = new ArrayList();

    public ControladorReportes(Reporte repo, List<ModeloPrecios> listRegiones, List<ModeloCotizacion> listCot, List<ModeloPersona> listPersona) {
        this.repo = repo;
        this.listReg = listRegiones;
        this.listCot = listCot;
        this.listPersona = listPersona;
        RegionesEnvios();
        OrdenarJTableDescendente(this.repo.jTableRegiones, 3);
        this.repo.jLabelPaquetes.setText(String.valueOf(PaquetesEnviados(this.repo.jTableRegiones, 3)));
        this.repo.jLabelIngresos.setText(String.valueOf(SumarTotal()));
        UserEnvios();
        OrdenarJTableDescendente(this.repo.jTableUser, 3);
    }

    private void RegionesEnvios() {
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"Código", "Precio Estándar Q.",
            "Precio Especial Q.", "Cantidad de Envios"}, listReg.size());
        repo.jTableRegiones.setModel(dtm);
        TableModel model = repo.jTableRegiones.getModel();

        for (int i = 0; i < listReg.size(); i++) {
            int contador = 0;
            boolean mostrado = false;
            for (int j = 0; j < listCot.size(); j++) {
                if (listCot.get(j).getRegion().equals(listReg.get(i).getNombre())) {
                    contador++;
                }
            }
            for (int k = 0; k < listCot.size(); k++) {
                if (listCot.get(k).getRegion().equals(listReg.get(i).getNombre())) {
                    mostrado = true;
                    break;
                }
            }
            if (mostrado) {
                model.setValueAt(listReg.get(i).getNombre(), i, 0);
                model.setValueAt(listReg.get(i).getPrecioEstandar(), i, 1);
                model.setValueAt(listReg.get(i).getPrecioEspecial(), i, 2);
                model.setValueAt(contador, i, 3);
            }
        }
    }

    private void UserEnvios() {
        DefaultTableModel dtm = new DefaultTableModel(new String[]{"DPI", "Nombre",
            "Apellido", "Cantidad de Compras"}, listPersona.size());
        repo.jTableUser.setModel(dtm);
        TableModel model = repo.jTableUser.getModel();

        for (int i = 0; i < listPersona.size(); i++) {
            int contador = 0;
            boolean mostrado = false;
            for (int j = 0; j < listCot.size(); j++) {
                if (listCot.get(j).getDpi().equals(listPersona.get(i).getDpi())) {
                    contador++;
                }
            }
            for (int k = 0; k < listCot.size(); k++) {
                if (listCot.get(k).getDpi().equals(listPersona.get(i).getDpi())) {
                    mostrado = true;
                    break;
                }
            }
            if (mostrado) {
                model.setValueAt(listPersona.get(i).getDpi(), i, 0);
                model.setValueAt(listPersona.get(i).getNombre(), i, 1);
                model.setValueAt(listPersona.get(i).getApellido(), i, 2);
                model.setValueAt(contador, i, 3);
            }
        }
    }

    private void OrdenarJTableDescendente(JTable table, int columnIndex) {
        int rowCount = table.getRowCount();
        for (int i = 0; i < rowCount - 1; i++) {
            for (int j = i + 1; j < rowCount; j++) {
                Object value1 = table.getValueAt(i, columnIndex);
                Object value2 = table.getValueAt(j, columnIndex);
                if (CompararValores(value1, value2) < 0) {
                    CambiarRow(table, i, j);
                }
            }
        }
    }

    private int CompararValores(Object value1, Object value2) {
        if (value1 == null && value2 == null) {
            return 0;
        }
        if (value1 == null) {
            return -1;
        }
        if (value2 == null) {
            return 1;
        }
        if (value1 instanceof Comparable) {
            return ((Comparable) value1).compareTo(value2);
        }
        return String.valueOf(value1).compareTo(String.valueOf(value2));
    }

    private void CambiarRow(JTable table, int row1, int row2) {
        for (int i = 0; i < table.getColumnCount(); i++) {
            Object temp = table.getValueAt(row1, i);
            table.setValueAt(table.getValueAt(row2, i), row1, i);
            table.setValueAt(temp, row2, i);
        }
    }

    private int PaquetesEnviados(JTable table, int column) {
        int total = 0;
        for (int row = 0; row < table.getRowCount(); row++) {
            Object value = table.getValueAt(row, column);
            if (value instanceof Number) {
                total += ((Number) value).doubleValue();
            }
        }
        return total;
    }

    private double SumarTotal() {
        double total = 0;
        for (int i = 0; i < listCot.size(); i++) {
            total += listCot.get(i).getTotPagar();
        }
        return total;
    }
}
