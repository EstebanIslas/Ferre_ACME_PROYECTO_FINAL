/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import models.ModelSucursal;
import views.ViewSucursal;

/**
 *
 * @author EstebanIslas
 */
public class ControllerSucursal {
    public ModelSucursal modelSucursal;
    public ViewSucursal viewSucursal;

    
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if(e.getSource() == viewSucursal.jb_primero){
                jbtn_primero_actionPerformed();
            }else if(e.getSource() == viewSucursal.jb_anterior){
                jbtn_anterior_actionPerformed();
            }else if(e.getSource() == viewSucursal.jb_siguiente){
                jbtn_siguiente_actionPerformed();
            }else if(e.getSource() == viewSucursal.jb_ultimo){
                jbtn_ultimo_actionPerformed();
            }
        }
    };
    
    public ControllerSucursal(ModelSucursal modelSucursal, ViewSucursal viewSucursal) {
        this.modelSucursal = modelSucursal;
        this.viewSucursal = viewSucursal;
        setActionListener();
        initDB();
    }
    
    private void setActionListener(){
        viewSucursal.jb_primero.addActionListener(actionListener);
        viewSucursal.jb_anterior.addActionListener(actionListener);
        viewSucursal.jb_siguiente.addActionListener(actionListener);
        viewSucursal.jb_ultimo.addActionListener(actionListener);
    }
    
    /**
     * Método que llama al método conectarBD del modelo y muestra informacion
     * primer registro en las cajas de texto de ViewAgenda.
     */
    private void initDB() {
        modelSucursal.conectarDB();
        setValues();
    }
    
    /**
     * Método para ver el primer registro de la tabla contactos
     */
    private void jbtn_primero_actionPerformed() {
        System.out.println("Action del boton jbtn_primero");
        modelSucursal.moverPrimerRegistro();
        setValues();
    }

    /**
     * Método para ver el registro anterior de la tabla contactos.
     */
    private void jbtn_anterior_actionPerformed() {
        System.out.println("Action del boton jbtn_anterior");
        modelSucursal.moverAnteriorRegistro();
        setValues();
    }

    /**
     * Método para ver el último registro de la tabla contactos.
     */
    private void jbtn_ultimo_actionPerformed() {
        System.out.println("Action del boton jbtn_ultimo");
        modelSucursal.moverUltimoRegistro();
        setValues();
    }

    /**
     * Método para ver el siguiente registro de la tabla contactos.
     */
    private void jbtn_siguiente_actionPerformed() {
        System.out.println("Action del boton jbtn_siguiente");
        modelSucursal.moverSiguienteRegistro();
        setValues();
    }
    
    private void setValues(){
        viewSucursal.jtf_sucursal_id.setText(String.valueOf(modelSucursal.getSucursal_id()));
        viewSucursal.jtf_calle.setText(modelSucursal.getCalle());
        viewSucursal.jtf_numero.setText(modelSucursal.getNumero());
        viewSucursal.jtf_colonia.setText(modelSucursal.getColonia());
        viewSucursal.jtf_codigo_postal.setText(modelSucursal.getCodigo_postal());
        viewSucursal.jtf_email.setText(modelSucursal.getEmail());
        viewSucursal.jtf_telefono.setText(modelSucursal.getTelefono());
        viewSucursal.jtf_ciudad.setText(modelSucursal.getCiudad());
        viewSucursal.jtf_estado.setText(modelSucursal.getEstado());
    }
}
