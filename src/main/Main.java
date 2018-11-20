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

import models.ModelProducto;
import views.ViewProducto;
import controllers.ControllerProducto;

import models.ModelDescuento;
import views.ViewDescuento;
import controllers.ControllerDescuento;

import models.ModelProveedores;
import views.ViewProveedores;
import controllers.ControllerProveedores;

import models.ModelCompras;
import views.ViewCompras;
import controllers.ControllerCompras;

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
        
        ModelProducto modelProducto = new ModelProducto();
        ViewProducto viewProducto = new ViewProducto();
        ControllerProducto controllerProducto = new ControllerProducto(modelProducto, viewProducto);
        
        ModelProveedores modelProveedores = new ModelProveedores();
        ViewProveedores viewProveedores = new ViewProveedores();
        ControllerProveedores controllerProveedores = new ControllerProveedores(modelProveedores, viewProveedores);
        
        ModelCompras modelCompras = new ModelCompras();
        ViewCompras viewCompras = new ViewCompras();
        ControllerCompras controllerCompras = new ControllerCompras(modelCompras, viewCompras);
        
        ModelDescuento modelDescuento = new ModelDescuento();
        ViewDescuento viewDescuento = new ViewDescuento();
        ControllerDescuento controllerDescuento = new ControllerDescuento(modelDescuento, viewDescuento);
        
        Object[] controllers = new Object[5];
        controllers[0] = controllerSucursal;
        controllers[1] = controllerProducto;
        controllers[2] = controllerProveedores;
        controllers[3] = controllerCompras;
        controllers[4] = controllerDescuento;
        
        
        ModelMain modelMain = new ModelMain();
        ViewMain viewMain = new ViewMain();
        ControllerMain controllerMain = new ControllerMain(modelMain, viewMain, controllers);
    }
    
}
