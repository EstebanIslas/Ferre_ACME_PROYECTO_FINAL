/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import models.ModelMain;
import views.ViewMain;
import controllers.ControllerMain;

import models.ModelSucursal;
import views.ViewSucursal;
import controllers.ControllerSucursal;

/**
 *
 * @author EstebanIslas
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        ModelSucursal modelSucursal = new ModelSucursal();
        ViewSucursal viewSucursal = new ViewSucursal();
        ControllerSucursal controllerSucursal = new ControllerSucursal(modelSucursal, viewSucursal);
        
        Object[] controllers = new Object[2];
        controllers[0] = controllerSucursal;
        
        ModelMain modelMain = new ModelMain();
        ViewMain viewMain = new ViewMain();
        ControllerMain controllerMain = new ControllerMain(modelMain, viewMain, controllers);
    }
    
}
