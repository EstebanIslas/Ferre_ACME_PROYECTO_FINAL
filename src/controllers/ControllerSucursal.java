/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import models.ModelSucursal;
import views.ViewSucursal;

/**
 *
 * @author EstebanIslas
 */
public class ControllerSucursal implements KeyListener {

    public ModelSucursal modelSucursal;
    public ViewSucursal viewSucursal;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewSucursal.jb_primero) {
                jbtn_primero_actionPerformed();
            } else if (e.getSource() == viewSucursal.jb_anterior) {
                jbtn_anterior_actionPerformed();
            } else if (e.getSource() == viewSucursal.jb_siguiente) {
                jbtn_siguiente_actionPerformed();
            } else if (e.getSource() == viewSucursal.jb_ultimo) {
                jbtn_ultimo_actionPerformed();
            } // CRUD //
            else if (e.getSource() == viewSucursal.jb_nuevo) {
                jbtn_nuevo_actionPerformed();
            } else if (e.getSource() == viewSucursal.jb_guardar) {
                jbtn_guardar_actionPerformed();
            } else if (e.getSource() == viewSucursal.jb_eliminar) {
                jbtn_eliminar_actionPerformed();
            } else if (e.getSource() == viewSucursal.jb_editar) {
                jbtn_editar_actionPerformed();
            } else if (e.getSource() == viewSucursal.jb_cancelar) {
                jbtn_cancelar_actionPerformed();
            }
        }
    };

    public ControllerSucursal(ModelSucursal modelSucursal, ViewSucursal viewSucursal) {
        this.modelSucursal = modelSucursal;
        this.viewSucursal = viewSucursal;
        this.viewSucursal.jtb_sucursal.addMouseListener(mouseListener);
        setActionListener();
        initDB();
    }

    private void setActionListener() {
        viewSucursal.jb_primero.addActionListener(actionListener);
        viewSucursal.jb_anterior.addActionListener(actionListener);
        viewSucursal.jb_siguiente.addActionListener(actionListener);
        viewSucursal.jb_ultimo.addActionListener(actionListener);

        //CRUD//
        viewSucursal.jb_nuevo.addActionListener(actionListener);
        viewSucursal.jb_guardar.addActionListener(actionListener);
        viewSucursal.jb_eliminar.addActionListener(actionListener);
        viewSucursal.jb_editar.addActionListener(actionListener);
        viewSucursal.jb_cancelar.addActionListener(actionListener);
    }

    /**
     * Método que llama al método conectarBD del modelo y muestra informacion
     * primer registro en las cajas de texto de ViewAgenda.
     */
    private void initDB() {
        modelSucursal.consultaSucursal();
        setValues();

        //## No es visible, pero se utilizara para el crud ##//
        viewSucursal.jtf_sucursal_id.setVisible(false);
        viewSucursal.jtf_sucursal_id.setEnabled(false);

        viewSucursal.jb_eliminar.setEnabled(true);
        viewSucursal.jb_nuevo.setEnabled(true);
        viewSucursal.jb_editar.setEnabled(true);
        viewSucursal.jb_guardar.setEnabled(false);
        viewSucursal.jb_cancelar.setEnabled(false);

        habilitarCajas(false);
        habilitarDesplazamiento(true);

        llenarTabla();

        //Aplicar a caja de texto el KeyListener 
        this.viewSucursal.jtf_buscar.addKeyListener(this);
    }

    private void habilitarCajas(boolean desi) {
        viewSucursal.jtf_calle.setEditable(desi);
        viewSucursal.jtf_numero.setEditable(desi);
        viewSucursal.jtf_colonia.setEditable(desi);
        viewSucursal.jtf_codigo_postal.setEditable(desi);
        viewSucursal.jtf_email.setEditable(desi);
        viewSucursal.jtf_telefono.setEditable(desi);
        viewSucursal.jtf_ciudad.setEditable(desi);
        viewSucursal.jtf_estado.setEditable(desi);
    }

    private void habilitarDesplazamiento(boolean desi) {
        viewSucursal.jb_primero.setEnabled(desi);
        viewSucursal.jb_siguiente.setEnabled(desi);
        viewSucursal.jb_anterior.setEnabled(desi);
        viewSucursal.jb_ultimo.setEnabled(desi);
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

    private void setValues() {
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

    /**
     * Método que habilita la edicion de las cajas de texto Deshabilita los
     * botones de dezplazamiento A la variable Descicion se le asigna el valor
     * nuevo para saber que se va a crear un nuevo registro
     */
    private void jbtn_nuevo_actionPerformed() {
        System.err.println("Action del boton nuevo");

        habilitarCajas(true);
        habilitarDesplazamiento(false);

        modelSucursal.setDescicion("nuevo");
        viewSucursal.jtf_calle.setText("");
        viewSucursal.jtf_numero.setText("");
        viewSucursal.jtf_colonia.setText("");
        viewSucursal.jtf_codigo_postal.setText("");
        viewSucursal.jtf_email.setText("");
        viewSucursal.jtf_telefono.setText("");
        viewSucursal.jtf_ciudad.setText("");
        viewSucursal.jtf_estado.setText("");

        viewSucursal.jb_eliminar.setEnabled(false);
        viewSucursal.jb_nuevo.setEnabled(false);
        viewSucursal.jb_editar.setEnabled(false);
        viewSucursal.jb_guardar.setEnabled(true);
        viewSucursal.jb_cancelar.setEnabled(true);
    }

    /**
     * Método que realiza el guardado de datos sean nuevos o actualizaciones
     * Llama al metodo guardarRegistro del modelo como parametro lo que
     * contienen sus cajas de texto Hibilitar y deshabilitar botones de CRUD
     */
    public void jbtn_guardar_actionPerformed() {
        System.err.println("Action del boton guardar");
        modelSucursal.guardarRegistro(viewSucursal.jtf_calle.getText(), viewSucursal.jtf_numero.getText(), viewSucursal.jtf_colonia.getText(), viewSucursal.jtf_codigo_postal.getText(), viewSucursal.jtf_email.getText(), viewSucursal.jtf_telefono.getText(), viewSucursal.jtf_ciudad.getText(), viewSucursal.jtf_estado.getText(), Integer.parseInt(viewSucursal.jtf_sucursal_id.getText()));
        habilitarDesplazamiento(true);
        habilitarCajas(false);

        viewSucursal.jb_eliminar.setEnabled(true);
        viewSucursal.jb_nuevo.setEnabled(true);
        viewSucursal.jb_editar.setEnabled(true);
        viewSucursal.jb_guardar.setEnabled(false);
        viewSucursal.jb_cancelar.setEnabled(false);

        modelSucursal.limpiarTabla();
        modelSucursal.insertarDatosTabla();
    }

    /**
     * Método que elimina un dato de la BD Llama al metodo borrarRegistro del
     * modelo como parametro el id del dato que queremos borrar Llama al metodo
     * jbtn_ultimo_actionPerformed para indicar al usuario el cambio y
     * actualizacion de datos
     */
    public void jbtn_eliminar_actionPerformed() {
        System.err.println("Action del boton eliminar");
        modelSucursal.borrarRegistro(Integer.parseInt(viewSucursal.jtf_sucursal_id.getText()));
        jbtn_ultimo_actionPerformed();
    }

    public void jbtn_editar_actionPerformed() {
        System.err.println("Action de boton jb_modificar");
        modelSucursal.setDescicion("editar");
        habilitarCajas(true);
        habilitarDesplazamiento(false);

        viewSucursal.jb_eliminar.setEnabled(false);
        viewSucursal.jb_nuevo.setEnabled(false);
        viewSucursal.jb_editar.setEnabled(false);
        viewSucursal.jb_guardar.setEnabled(true);
        viewSucursal.jb_cancelar.setEnabled(true);
    }

    public void jbtn_cancelar_actionPerformed() {
        System.err.println("Action de boton jb_cancelar");
        JOptionPane.showMessageDialog(null, "Registro Cancelado!!");
        jbtn_primero_actionPerformed();
        habilitarCajas(false);
        habilitarDesplazamiento(true);

        viewSucursal.jb_eliminar.setEnabled(true);
        viewSucursal.jb_nuevo.setEnabled(true);
        viewSucursal.jb_editar.setEnabled(true);
        viewSucursal.jb_guardar.setEnabled(false);
        viewSucursal.jb_cancelar.setEnabled(false);
    }

    public void llenarTabla() {
        modelSucursal.tituloTabla();
        viewSucursal.jtb_sucursal.setModel(modelSucursal.getModelo());
        modelSucursal.insertarDatosTabla();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Realizando la busqueda");
        if (viewSucursal.jtf_buscar.hasFocus()) {
            modelSucursal.limpiarTabla();
            modelSucursal.buscarTabla(viewSucursal.jtf_buscar.getText());
            modelSucursal.insertarDatosTabla();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
    }

    MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if (e.getSource() == viewSucursal.jtb_sucursal) {
                int seleccion = viewSucursal.jtb_sucursal.getSelectedRow();

                viewSucursal.jtf_sucursal_id.setText(String.valueOf(viewSucursal.jtb_sucursal.getValueAt(seleccion, 0)));
                viewSucursal.jtf_calle.setText(String.valueOf(viewSucursal.jtb_sucursal.getValueAt(seleccion, 1)));
                viewSucursal.jtf_numero.setText(String.valueOf(viewSucursal.jtb_sucursal.getValueAt(seleccion, 2)));
                viewSucursal.jtf_colonia.setText(String.valueOf(viewSucursal.jtb_sucursal.getValueAt(seleccion, 3)));
                viewSucursal.jtf_codigo_postal.setText(String.valueOf(viewSucursal.jtb_sucursal.getValueAt(seleccion, 4)));
                viewSucursal.jtf_email.setText(String.valueOf(viewSucursal.jtb_sucursal.getValueAt(seleccion, 5)));
                viewSucursal.jtf_telefono.setText(String.valueOf(viewSucursal.jtb_sucursal.getValueAt(seleccion, 6)));
                viewSucursal.jtf_ciudad.setText(String.valueOf(viewSucursal.jtb_sucursal.getValueAt(seleccion, 7)));
                viewSucursal.jtf_estado.setText(String.valueOf(viewSucursal.jtb_sucursal.getValueAt(seleccion, 8)));
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
    };
}
