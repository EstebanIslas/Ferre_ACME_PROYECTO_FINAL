/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.ModelMain;
import views.ViewMain;

/**
 *
 * @author EstebanIslas
 */
public class ControllerMain {
    private final ModelMain modelMain;
    private final ViewMain viewMain;
    
    /**
     * Esta variable se usa para almacenar los controladores necesarios para el proyecto
     */
    private Object controllers[];
    
    private ControllerSucursal controllerSucursal;

    public ControllerMain(ModelMain modelMain, ViewMain viewMain, Object[] controllers) {
        this.modelMain = modelMain;
        this.viewMain = viewMain;
        this.controllers = controllers;
        setControllers();
        setActionListener();
        initComponets();
    }
    
    private final ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == viewMain.jmi_sucursales){
                jmi_Sucursales_actionPerformed();
            }
        }
    };
    
    /**
     * Separa cada uno de los controlladores almacendados en controllers, de
     * esta forma se puede acceder a todas las variables y métodos publicos
     * de cada uno.
     */
    private void setControllers() {
        controllerSucursal = (ControllerSucursal) controllers[0];
    }
    
    private void initComponets() {
        viewMain.setTitle("ACME FERRETERIA");
        viewMain.setLocationRelativeTo(null);
        viewMain.setVisible(true);
    }
    
    private void setActionListener(){
        viewMain.jmi_sucursales.addActionListener(actionListener);
    }
    
    private void jmi_Sucursales_actionPerformed(){
        viewMain.setContentPane(controllerSucursal.viewSucursal);
        viewMain.revalidate();
        viewMain.repaint();
    }
}
