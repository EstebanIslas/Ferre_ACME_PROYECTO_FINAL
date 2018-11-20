package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import models.ModelDescuento;
import views.ViewDescuento;

/**
 *
 * @author EstebanIslas
 */
public class ControllerDescuento implements KeyListener {

    public ModelDescuento modelDescuento;
    public ViewDescuento viewDescuento;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewDescuento.jb_primero) {
                jbtn_primero_actionPerformed();
            } else if (e.getSource() == viewDescuento.jb_anterior) {
                jbtn_anterior_actionPerformed();
            } else if (e.getSource() == viewDescuento.jb_siguiente) {
                jbtn_siguiente_actionPerformed();
            } else if (e.getSource() == viewDescuento.jb_ultimo) {
                jbtn_ultimo_actionPerformed();
            } // CRUD //
            else if (e.getSource() == viewDescuento.jb_nuevo) {
                jbtn_nuevo_actionPerformed();
            } else if (e.getSource() == viewDescuento.jb_guardar) {
                jbtn_guardar_actionPerformed();
            } else if (e.getSource() == viewDescuento.jb_eliminar) {
                jbtn_eliminar_actionPerformed();
            } else if (e.getSource() == viewDescuento.jb_editar) {
                jbtn_editar_actionPerformed();
            } else if (e.getSource() == viewDescuento.jb_cancelar) {
                jbtn_cancelar_actionPerformed();
            }
        }
    };

    public ControllerDescuento(ModelDescuento modelDescuento, ViewDescuento viewDescuento) {
        this.modelDescuento = modelDescuento;
        this.viewDescuento = viewDescuento;
        this.viewDescuento.jtb_tabla_descuentos.addMouseListener(mouseListener);
        setActionListener();
        initComponents();
        iniciarCrud();

    }

    private void setActionListener() {
        viewDescuento.jb_primero.addActionListener(actionListener);
        viewDescuento.jb_anterior.addActionListener(actionListener);
        viewDescuento.jb_siguiente.addActionListener(actionListener);
        viewDescuento.jb_ultimo.addActionListener(actionListener);

        //CRUD//
        viewDescuento.jb_nuevo.addActionListener(actionListener);
        viewDescuento.jb_guardar.addActionListener(actionListener);
        viewDescuento.jb_eliminar.addActionListener(actionListener);
        viewDescuento.jb_editar.addActionListener(actionListener);
        viewDescuento.jb_cancelar.addActionListener(actionListener);
    }

    private void iniciarCrud() {
        viewDescuento.jb_nuevo.setEnabled(true);
        viewDescuento.jb_editar.setEnabled(true);
        viewDescuento.jb_eliminar.setEnabled(true);
        viewDescuento.jb_guardar.setEnabled(false);
        viewDescuento.jb_cancelar.setEnabled(false);
    }

    private void initComponents() {
        modelDescuento.consultaDescuentos();
        getValues();

        viewDescuento.jtf_descuento_id.setVisible(false);
        viewDescuento.jtf_descuento_id.setEnabled(false);
        viewDescuento.jl_descuento_id.setVisible(false);

        habilitarCajas(false);
        habilitarDezplazamiento(true);

        llenarTabla();

        this.viewDescuento.addKeyListener(this);
    }

    private void getValues() {
        viewDescuento.jtf_descuento_id.setText(String.valueOf(modelDescuento.getDescuentoid()));
        viewDescuento.jtf_nombre.setText(modelDescuento.getNombre());
        viewDescuento.jtf_porcentaje.setText(String.valueOf(modelDescuento.getPorcentaje()));
        viewDescuento.jtf_tipo.setText(modelDescuento.getTipo());
    }

    private void habilitarCajas(boolean desi) {
        viewDescuento.jtf_nombre.setEditable(desi);
        viewDescuento.jtf_tipo.setEditable(desi);
        viewDescuento.jtf_descuento_id.setEditable(desi);
        viewDescuento.jtf_porcentaje.setEditable(desi);
    }

    private void habilitarDezplazamiento(boolean desi) {
        viewDescuento.jb_eliminar.setEnabled(desi);
        viewDescuento.jb_nuevo.setEnabled(desi);
        viewDescuento.jb_editar.setEnabled(desi);
        viewDescuento.jb_guardar.setEnabled(desi);
        viewDescuento.jb_cancelar.setEnabled(desi);
    }

    /**
     * Método para ver el primer registro de la tabla contactos
     */
    private void jbtn_primero_actionPerformed() {
        //System.out.println("Action del boton jbtn_primero");
        modelDescuento.moverPrimerRegistro();
        getValues();
    }

    /**
     * Método para ver el registro anterior de la tabla contactos.
     */
    private void jbtn_anterior_actionPerformed() {
        //System.out.println("Action del boton jbtn_anterior");
        modelDescuento.moverAnteriorRegistro();
        getValues();
    }

    /**
     * Método para ver el último registro de la tabla contactos.
     */
    private void jbtn_ultimo_actionPerformed() {
        //System.out.println("Action del boton jbtn_ultimo");
        modelDescuento.moverUltimoRegistro();
        getValues();
    }

    /**
     * Método para ver el siguiente registro de la tabla contactos.
     */
    private void jbtn_siguiente_actionPerformed() {
        //System.out.println("Action del boton jbtn_siguiente");
        modelDescuento.moverSiguienteRegistro();
        getValues();
    }

    private void jbtn_nuevo_actionPerformed() {
        //System.err.println("Action del boton nuevo");

        modelDescuento.setDescicion("nuevo");

        habilitarCajas(true);
        habilitarDezplazamiento(false);

        viewDescuento.jtf_nombre.setText("");
        viewDescuento.jtf_tipo.setText("");
        viewDescuento.jtf_descuento_id.setText("");
        viewDescuento.jtf_porcentaje.setText("");

        viewDescuento.jb_eliminar.setEnabled(false);
        viewDescuento.jb_nuevo.setEnabled(false);
        viewDescuento.jb_editar.setEnabled(false);
        viewDescuento.jb_guardar.setEnabled(true);
        viewDescuento.jb_cancelar.setEnabled(true);
    }

    /**
     * Método que realiza el guardado de datos sean nuevos o actualizaciones
     * Llama al metodo guardarRegistro del modelo como parametro lo que
     * contienen sus cajas de texto Hibilitar y deshabilitar botones de CRUD
     */
    public void jbtn_guardar_actionPerformed() {
        //System.err.println("Action del boton guardar");
        try {
            
            modelDescuento.guardarRegristro(Integer.parseInt(viewDescuento.jtf_descuento_id.getText()), viewDescuento.jtf_nombre.getText(), Double.parseDouble(viewDescuento.jtf_porcentaje.getText()), viewDescuento.jtf_tipo.getText());
            
            habilitarCajas(false);
            habilitarDezplazamiento(true);

            viewDescuento.jb_eliminar.setEnabled(true);
            viewDescuento.jb_nuevo.setEnabled(true);
            viewDescuento.jb_editar.setEnabled(true);
            viewDescuento.jb_guardar.setEnabled(false);
            viewDescuento.jb_cancelar.setEnabled(false);
            
            modelDescuento.limpiarTabla();
            modelDescuento.agregaraTabla();
            
            
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error!! " + ex.getMessage());
        }
    }

    /**
     * Método que elimina un dato de la BD Llama al metodo borrarRegistro del
     * modelo como parametro el id del dato que queremos borrar Llama al metodo
     * jbtn_ultimo_actionPerformed para indicar al usuario el cambio y
     * actualizacion de datos
     */
    public void jbtn_eliminar_actionPerformed() {
        //System.err.println("Action del boton eliminar");

        modelDescuento.borrarRegistro(Integer.parseInt(viewDescuento.jtf_descuento_id.getText()));
        jbtn_ultimo_actionPerformed();
    }

    public void jbtn_editar_actionPerformed() {
        //System.err.println("Action de boton jb_modificar");
        modelDescuento.setDescicion("editar");

        habilitarCajas(true);
        habilitarDezplazamiento(false);

        viewDescuento.jb_eliminar.setEnabled(false);
        viewDescuento.jb_nuevo.setEnabled(false);
        viewDescuento.jb_editar.setEnabled(false);
        viewDescuento.jb_guardar.setEnabled(true);
        viewDescuento.jb_cancelar.setEnabled(true);
    }

    public void jbtn_cancelar_actionPerformed() {
        System.err.println("Action de boton jb_cancelar");
        JOptionPane.showMessageDialog(null, "Registro Cancelado!!");
        jbtn_primero_actionPerformed();

        habilitarCajas(false);
        habilitarDezplazamiento(true);

        viewDescuento.jb_eliminar.setEnabled(true);
        viewDescuento.jb_nuevo.setEnabled(true);
        viewDescuento.jb_editar.setEnabled(true);
        viewDescuento.jb_guardar.setEnabled(false);
        viewDescuento.jb_cancelar.setEnabled(false);
    }

    public void llenarTabla() {
        modelDescuento.columnasTabla();
        viewDescuento.jtb_tabla_descuentos.setModel(modelDescuento.getModelo());
        modelDescuento.agregaraTabla();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        //System.out.println("Entdada");
        if (viewDescuento.jtf_buscar.hasFocus()) {
            modelDescuento.limpiarTabla();
            modelDescuento.buscarTabla(viewDescuento.jtf_buscar.getText());
            modelDescuento.agregaraTabla();
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
            if (e.getSource() == viewDescuento.jtb_tabla_descuentos) {
                int seleccion = viewDescuento.jtb_tabla_descuentos.getSelectedRow();
                //viewCompras.jtf_nombre_producto.setText(String.valueOf(viewCompras.jtb_productos.getValueAt(seleccion, 1)));

                
                viewDescuento.jtf_descuento_id.setText(String.valueOf(viewDescuento.jtb_tabla_descuentos.getValueAt(seleccion, 0)));
                viewDescuento.jtf_nombre.setText(String.valueOf(viewDescuento.jtb_tabla_descuentos.getValueAt(seleccion, 1)));
                viewDescuento.jtf_porcentaje.setText(String.valueOf(viewDescuento.jtb_tabla_descuentos.getValueAt(seleccion, 2)));
                viewDescuento.jtf_tipo.setText(String.valueOf(viewDescuento.jtb_tabla_descuentos.getValueAt(seleccion, 3)));
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
