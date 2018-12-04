package controllers;

import models.ModelProducto;
import views.ViewProducto;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;

/**
 *
 * @author EstebanIslas
 */
public class ControllerProducto implements KeyListener {

    public ModelProducto modelProducto;
    public ViewProducto viewProducto;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewProducto.jb_primero) {
                jbtn_primero_actionPerformed();
            } else if (e.getSource() == viewProducto.jb_anterior) {
                jbtn_anterior_actionPerformed();
            } else if (e.getSource() == viewProducto.jb_siguiente) {
                jbtn_siguiente_actionPerformed();
            } else if (e.getSource() == viewProducto.jb_ultimo) {
                jbtn_ultimo_actionPerformed();
            } // CRUD //
            else if (e.getSource() == viewProducto.jb_nuevo) {
                jbtn_nuevo_actionPerformed();
            } else if (e.getSource() == viewProducto.jb_guardar) {
                jbtn_guardar_actionPerformed();
            } else if (e.getSource() == viewProducto.jb_eliminar) {
                jbtn_eliminar_actionPerformed();
            } else if (e.getSource() == viewProducto.jb_editar) {
                jbtn_editar_actionPerformed();
            } else if (e.getSource() == viewProducto.jb_cancelar) {
                jbtn_cancelar_actionPerformed();
            }
            /*else if (e.getSource() == viewProducto.jb_buscar) {
                modelProducto.limpiarTabla();
                modelProducto.buscarTabla(viewProducto.jtf_buscar.getText());
                //viewProducto.jtb_tabla_productos.setModel(modelProducto.getModelo());
                modelProducto.agregaraTabla();

            }*/
        }
    };

    public ControllerProducto(ModelProducto modelProducto, ViewProducto viewProducto) {
        this.modelProducto = modelProducto;
        this.viewProducto = viewProducto;
        this.viewProducto.jtb_tabla_productos.addMouseListener(mouseListener);
        setActionListener();
        initDB();
        iniciarCrud();
    }

    private void setActionListener() {
        viewProducto.jb_primero.addActionListener(actionListener);
        viewProducto.jb_anterior.addActionListener(actionListener);
        viewProducto.jb_siguiente.addActionListener(actionListener);
        viewProducto.jb_ultimo.addActionListener(actionListener);

        //CRUD//
        viewProducto.jb_nuevo.addActionListener(actionListener);
        viewProducto.jb_guardar.addActionListener(actionListener);
        viewProducto.jb_eliminar.addActionListener(actionListener);
        viewProducto.jb_editar.addActionListener(actionListener);
        viewProducto.jb_cancelar.addActionListener(actionListener);
    }

    private void iniciarCrud() {
        viewProducto.jb_nuevo.setEnabled(true);
        viewProducto.jb_editar.setEnabled(true);
        viewProducto.jb_eliminar.setEnabled(true);
        viewProducto.jb_guardar.setEnabled(false);
        viewProducto.jb_cancelar.setEnabled(false);
    }

    private void initDB() {
        modelProducto.consultaProductos();
        getValues();

        //##No debe ser visible para el usuario pero se utiliza para el crud
        viewProducto.jl_producto_id.setVisible(false);
        viewProducto.jtf_producto_id.setVisible(false);
        viewProducto.jtf_producto_id.setEnabled(false);

        habilitarCajas(false);
        habilitarDezplazamiento(true);

        llenarTabla();

        //Aplicar a caja de texto el keyListener
        this.viewProducto.jtf_buscar.addKeyListener(this);

    }

    private void getValues() {
        viewProducto.jtf_producto_id.setText(String.valueOf(modelProducto.getProducto_id()));
        viewProducto.jtf_nombre.setText(modelProducto.getNombre());
        viewProducto.jtf_tipos.setText(modelProducto.getTipo());
        viewProducto.jtf_marca.setText(modelProducto.getMarca());
        viewProducto.jtf_precio_venta.setText(String.valueOf(modelProducto.getPrecio_venta()));
        //viewProducto.jtf_unidad_medida.setText(modelProducto.getUnidad_medida());
        viewProducto.jcb_unidad_medida.setSelectedItem(modelProducto.getUnidad_medida());
    }

    private void habilitarCajas(boolean desi) {
        viewProducto.jtf_nombre.setEditable(desi);
        viewProducto.jtf_tipos.setEditable(desi);
        viewProducto.jtf_marca.setEditable(desi);
        viewProducto.jtf_precio_venta.setEditable(desi);
        //viewProducto.jtf_unidad_medida.setEditable(desi);
        viewProducto.jcb_unidad_medida.setEnabled(desi);
    }

    private void habilitarDezplazamiento(boolean desi) {
        viewProducto.jb_eliminar.setEnabled(desi);
        viewProducto.jb_nuevo.setEnabled(desi);
        viewProducto.jb_editar.setEnabled(desi);
        viewProducto.jb_guardar.setEnabled(desi);
        viewProducto.jb_cancelar.setEnabled(desi);
    }

    /**
     * Método para ver el primer registro de la tabla contactos
     */
    private void jbtn_primero_actionPerformed() {
        //System.out.println("Action del boton jbtn_primero");
        modelProducto.moverPrimerRegistro();
        getValues();
    }

    /**
     * Método para ver el registro anterior de la tabla contactos.
     */
    private void jbtn_anterior_actionPerformed() {
        //System.out.println("Action del boton jbtn_anterior");
        modelProducto.moverAnteriorRegistro();
        getValues();
    }

    /**
     * Método para ver el último registro de la tabla contactos.
     */
    private void jbtn_ultimo_actionPerformed() {
        //System.out.println("Action del boton jbtn_ultimo");
        modelProducto.moverUltimoRegistro();
        getValues();
    }

    /**
     * Método para ver el siguiente registro de la tabla contactos.
     */
    private void jbtn_siguiente_actionPerformed() {
        //System.out.println("Action del boton jbtn_siguiente");
        modelProducto.moverSiguienteRegistro();
        getValues();
    }

    /**
     * Método que habilita la edicion de las cajas de texto Deshabilita los
     * botones de dezplazamiento A la variable Descicion se le asigna el valor
     * nuevo para saber que se va a crear un nuevo registro
     */
    private void jbtn_nuevo_actionPerformed() {
        //System.err.println("Action del boton nuevo");

        modelProducto.setDescicion("nuevo");

        habilitarCajas(true);
        habilitarDezplazamiento(false);

        viewProducto.jtf_nombre.setText("");
        viewProducto.jtf_tipos.setText("");
        viewProducto.jtf_marca.setText("");
        viewProducto.jtf_precio_venta.setText("");
        //viewProducto.jtf_unidad_medida.setText("");
        viewProducto.jcb_unidad_medida.setSelectedIndex(0);

        viewProducto.jb_eliminar.setEnabled(false);
        viewProducto.jb_nuevo.setEnabled(false);
        viewProducto.jb_editar.setEnabled(false);
        viewProducto.jb_guardar.setEnabled(true);
        viewProducto.jb_cancelar.setEnabled(true);
    }

    /**
     * Método que realiza el guardado de datos sean nuevos o actualizaciones
     * Llama al metodo guardarRegistro del modelo como parametro lo que
     * contienen sus cajas de texto Hibilitar y deshabilitar botones de CRUD
     */
    public void jbtn_guardar_actionPerformed() {
        //System.err.println("Action del boton guardar");

        modelProducto.guardarRegistro(Integer.parseInt(viewProducto.jtf_producto_id.getText()), viewProducto.jtf_nombre.getText(), viewProducto.jtf_tipos.getText(), viewProducto.jtf_marca.getText(), Double.parseDouble(viewProducto.jtf_precio_venta.getText()), String.valueOf(viewProducto.jcb_unidad_medida.getSelectedItem()));

        habilitarCajas(false);
        habilitarDezplazamiento(true);

        viewProducto.jb_eliminar.setEnabled(true);
        viewProducto.jb_nuevo.setEnabled(true);
        viewProducto.jb_editar.setEnabled(true);
        viewProducto.jb_guardar.setEnabled(false);
        viewProducto.jb_cancelar.setEnabled(false);

        modelProducto.limpiarTabla();
        modelProducto.agregaraTabla();

    }

    /**
     * Método que elimina un dato de la BD Llama al metodo borrarRegistro del
     * modelo como parametro el id del dato que queremos borrar Llama al metodo
     * jbtn_ultimo_actionPerformed para indicar al usuario el cambio y
     * actualizacion de datos
     */
    public void jbtn_eliminar_actionPerformed() {
        //System.err.println("Action del boton eliminar");

        modelProducto.borrarRegistro(Integer.parseInt(viewProducto.jtf_producto_id.getText()));
        jbtn_ultimo_actionPerformed();
    }

    public void jbtn_editar_actionPerformed() {
        //System.err.println("Action de boton jb_modificar");
        modelProducto.setDescicion("editar");

        habilitarCajas(true);
        habilitarDezplazamiento(false);

        viewProducto.jb_eliminar.setEnabled(false);
        viewProducto.jb_nuevo.setEnabled(false);
        viewProducto.jb_editar.setEnabled(false);
        viewProducto.jb_guardar.setEnabled(true);
        viewProducto.jb_cancelar.setEnabled(true);
    }

    public void jbtn_cancelar_actionPerformed() {
        System.err.println("Action de boton jb_cancelar");
        JOptionPane.showMessageDialog(null, "Registro Cancelado!!");
        jbtn_primero_actionPerformed();

        habilitarCajas(false);
        habilitarDezplazamiento(true);

        viewProducto.jb_eliminar.setEnabled(true);
        viewProducto.jb_nuevo.setEnabled(true);
        viewProducto.jb_editar.setEnabled(true);
        viewProducto.jb_guardar.setEnabled(false);
        viewProducto.jb_cancelar.setEnabled(false);
    }

    public void llenarTabla() {
        modelProducto.columnasTabla();
        viewProducto.jtb_tabla_productos.setModel(modelProducto.getModelo());
        modelProducto.agregaraTabla();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        System.out.println("Entdada");
        if (viewProducto.jtf_buscar.hasFocus()) {
            modelProducto.limpiarTabla();
            modelProducto.buscarTabla(viewProducto.jtf_buscar.getText());
            modelProducto.agregaraTabla();
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
            if (e.getSource() == viewProducto.jtb_tabla_productos) {
                int seleccion = viewProducto.jtb_tabla_productos.getSelectedRow();

                //viewCompras.jtf_nombre_producto.setText(String.valueOf(viewCompras.jtb_productos.getValueAt(seleccion, 1)));
                
                viewProducto.jtf_producto_id.setText(String.valueOf(viewProducto.jtb_tabla_productos.getValueAt(seleccion, 0)));
                viewProducto.jtf_nombre.setText(String.valueOf(viewProducto.jtb_tabla_productos.getValueAt(seleccion, 1)));
                viewProducto.jtf_tipos.setText(String.valueOf(viewProducto.jtb_tabla_productos.getValueAt(seleccion, 2)));
                viewProducto.jtf_marca.setText(String.valueOf(viewProducto.jtb_tabla_productos.getValueAt(seleccion, 3)));
                viewProducto.jtf_precio_venta.setText(String.valueOf(viewProducto.jtb_tabla_productos.getValueAt(seleccion, 4)));
                //viewProducto.jtf_unidad_medida.setText(String.valueOf(viewProducto.jtb_tabla_productos.getValueAt(seleccion, 5)));
                viewProducto.jcb_unidad_medida.setSelectedItem(String.valueOf(viewProducto.jtb_tabla_productos.getValueAt(seleccion, 5)));
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
