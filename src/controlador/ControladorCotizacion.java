/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;
import modelo.ModeloDepartamentos;
import vista.Cotizacion;

/**
 *
 * @author queza
 */
public class ControladorCotizacion implements ItemListener {

    Cotizacion cot;
    List<ModeloDepartamentos> listDepart;

    public ControladorCotizacion(Cotizacion cot, List<ModeloDepartamentos> listDepart) {
        this.cot = cot;
        this.listDepart = listDepart;
        this.cot.jComboBoxDestinoMuni.addItemListener(this);
        this.cot.jComboBoxDestinoDept.addItemListener(this);
        System.out.println(listDepart.size());
    }

    private void PullMuni() {
        String destinoDept = (String) cot.jComboBoxDestinoDept.getSelectedItem();
        System.out.println(listDepart.size());
        for (int i = 0; i < listDepart.size(); i++) {
            if (listDepart.get(i).getNombreDepart().equals(destinoDept)) {
                System.out.println("hola - " + i);
                break;
            }
            
            // Agregar la funcion de devolucion de los combobox, no tienen que
            //repetirse y compararlos con los municipios para que tengan sentido
        }
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        
        if (e.getStateChange() == ItemEvent.SELECTED) {
            if (cot.jComboBoxDestinoDept.getSelectedIndex() > 0) {
                System.out.println("hola");
            }
        }
    }

}
