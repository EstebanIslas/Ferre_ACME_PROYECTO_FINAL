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
    private ControllerProducto controllerProducto;
    private ControllerProveedores controllerProveedores;
    private ControllerCompras controllerCompras;
    private ControllerDescuento controllerDescuento;
    private ControllerUsuario controllerUsuario;

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
            else if(e.getSource() == viewMain.jmi_productos){
                jmi_Productos_actionPerformed();
            }
            else if(e.getSource() == viewMain.jmi_proveedores){
                jmi_Proveedores_actionPerformed();
            }
            else if(e.getSource() == viewMain.jmi_compra){
                jmi_Compras_actionPerformed();
            }
            else if(e.getSource() == viewMain.jmi_descuentos){
                jmi_Descuentos_actionPerformed();
            }
            else if(e.getSource() == viewMain.jmi_usuarios){
                jmi_Usuarios_actionPerformed();
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
        controllerProducto = (ControllerProducto) controllers[1];
        controllerProveedores = (ControllerProveedores) controllers[2];
        controllerCompras = (ControllerCompras) controllers[3];
        controllerDescuento = (ControllerDescuento) controllers[4];
        controllerUsuario = (ControllerUsuario) controllers[5];
    }
    
    private void initComponets() {
        viewMain.setTitle("ACME FERRETERIA");
        viewMain.setLocationRelativeTo(null);
        viewMain.setVisible(true);
    }
    
    private void setActionListener(){
        viewMain.jmi_sucursales.addActionListener(actionListener);
        viewMain.jmi_productos.addActionListener(actionListener);
        viewMain.jmi_proveedores.addActionListener(actionListener);
        viewMain.jmi_compra.addActionListener(actionListener);
        viewMain.jmi_descuentos.addActionListener(actionListener);
        viewMain.jmi_usuarios.addActionListener(actionListener);
    }
    
    private void jmi_Sucursales_actionPerformed(){
        viewMain.setContentPane(controllerSucursal.viewSucursal);
        viewMain.revalidate();
        viewMain.repaint();
    }
    
    private void jmi_Productos_actionPerformed(){
        viewMain.setContentPane(controllerProducto.viewProducto);
        viewMain.revalidate();
        viewMain.repaint();
    }
    
    private void jmi_Proveedores_actionPerformed(){
        viewMain.setContentPane(controllerProveedores.viewProveedores);
        viewMain.revalidate();
        viewMain.repaint();
    }
    private void jmi_Compras_actionPerformed(){
        viewMain.setContentPane(controllerCompras.viewCompras);
        viewMain.revalidate();
        viewMain.repaint();
    }
    
    private void jmi_Descuentos_actionPerformed(){
        viewMain.setContentPane(controllerDescuento.viewDescuento);
        viewMain.revalidate();
        viewMain.repaint();
    }
    
    private void jmi_Usuarios_actionPerformed(){
        viewMain.setContentPane(controllerUsuario.viewUsuario);
        viewMain.revalidate();
        viewMain.repaint();
    }
}
