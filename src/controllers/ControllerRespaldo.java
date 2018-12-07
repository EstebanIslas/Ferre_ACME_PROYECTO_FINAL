package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JOptionPane;
import models.ModelRespaldo;
import views.ViewRespaldo;

/**
 *
 * @author EstebanIslas
 */
public class ControllerRespaldo {

    ModelRespaldo modelRespaldo;
    ViewRespaldo viewRespaldo;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewRespaldo.jb_respaldo) {
                System.out.println("Respaldo Guardado");
                modelRespaldo.respaldosDB();
            }
        }
    };

    public ControllerRespaldo(ModelRespaldo modelRespaldo, ViewRespaldo viewRespaldo) {
        this.modelRespaldo = modelRespaldo;
        this.viewRespaldo = viewRespaldo;
        setActionListener();
    }

    public void setActionListener() {
        viewRespaldo.jb_respaldo.addActionListener(actionListener);
    }
}
