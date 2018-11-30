package controllers;

import models.ModelClientes;
import views.ViewClientes;
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
public class ControllerClientes implements KeyListener{
    
    public ModelClientes modelClientes;
    public ViewClientes viewClientes;

    public ControllerClientes(ModelClientes modelClientes, ViewClientes viewClientes) {
        this.modelClientes = modelClientes;
        this.viewClientes = viewClientes;
        this.viewClientes.jtb_tabla_clientes.addMouseListener(mouseListener);
        setActionListener();
        initComponents();
        inicioCrud();
    }
    
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewClientes.jb_primero) {
                jbtn_primero_actionPerformed();
            } else if (e.getSource() == viewClientes.jb_anterior) {
                jbtn_anterior_actionPerformed();
            } else if (e.getSource() == viewClientes.jb_siguiente) {
                jbtn_siguiente_actionPerformed();
            } else if (e.getSource() == viewClientes.jb_ultimo) {
                jbtn_ultimo_actionPerformed();
            } // CRUD //
            else if (e.getSource() == viewClientes.jb_nuevo) {
                jbtn_nuevo_actionPerformed();
            } else if (e.getSource() == viewClientes.jb_guardar) {
                jbtn_guardar_actionPerformed();
            } else if (e.getSource() == viewClientes.jb_eliminar) {
                jbtn_eliminar_actionPerformed();
            } else if (e.getSource() == viewClientes.jb_editar) {
                jbtn_editar_actionPerformed();
            } else if (e.getSource() == viewClientes.jb_cancelar) {
                jbtn_cancelar_actionPerformed();
            }
        }
    };
    
    private void setActionListener(){
        viewClientes.jb_primero.addActionListener(actionListener);
        viewClientes.jb_anterior.addActionListener(actionListener);
        viewClientes.jb_siguiente.addActionListener(actionListener);
        viewClientes.jb_ultimo.addActionListener(actionListener);
        
        // CRUD //
        viewClientes.jb_nuevo.addActionListener(actionListener);
        viewClientes.jb_guardar.addActionListener(actionListener);
        viewClientes.jb_eliminar.addActionListener(actionListener);
        viewClientes.jb_editar.addActionListener(actionListener);
        viewClientes.jb_cancelar.addActionListener(actionListener);
    }
    
    private void inicioCrud(){
        viewClientes.jb_nuevo.setEnabled(true);
        viewClientes.jb_editar.setEnabled(true);
        viewClientes.jb_eliminar.setEnabled(true);
        viewClientes.jb_guardar.setEnabled(false);
        viewClientes.jb_cancelar.setEnabled(false);
    }
    
    private void initComponents(){
        modelClientes.consultaCliente();
        getValues();
        
        
        viewClientes.jtf_cliente_id.setVisible(false);
        viewClientes.jtf_cliente_id.setEnabled(false);
        
        habilitarCajas(false);
        habilitarDesplazamiento(true);
        
        llenarTabla();
        
        this.viewClientes.jtf_buscar.addKeyListener(this);
    }
    
    private void getValues(){
        viewClientes.jtf_cliente_id.setText(String.valueOf(modelClientes.getCliente_id()));
        viewClientes.jtf_descuento_id.setText(String.valueOf(modelClientes.getDescuento_id()));
        viewClientes.jtf_nombre.setText(modelClientes.getNombre());
        viewClientes.jtf_apellido_paterno.setText(modelClientes.getApellido_paterno());
        viewClientes.jtf_apellido_materno.setText(modelClientes.getApellido_materno());
        viewClientes.jtf_telefono.setText(modelClientes.getTelefono());
        viewClientes.jtf_rfc.setText(modelClientes.getRfc());
        viewClientes.jtf_calle.setText(modelClientes.getCalle());
        viewClientes.jtf_colonia.setText(modelClientes.getColonia());
        viewClientes.jtf_num_interno.setText(modelClientes.getNum_interior());
        viewClientes.jtf_num_externo.setText(modelClientes.getNum_exterior());
        viewClientes.jtf_codigo_postal.setText(modelClientes.getCodigo_postal());
        viewClientes.jtf_email.setText(modelClientes.getEmail());
        viewClientes.jtf_ciudad.setText(modelClientes.getCiudad());
        viewClientes.jtf_estado.setText(modelClientes.getEstado());
        viewClientes.jtf_fecha_creacion.setText(modelClientes.getFecha_creacion());
        viewClientes.jtf_total_acumulado.setText(String.valueOf(modelClientes.getTotal_acumulado()));
        
    }
    
    private void habilitarCajas(boolean desi){
        viewClientes.jtf_descuento_id.setEditable(desi);
        viewClientes.jtf_nombre.setEditable(desi);
        viewClientes.jtf_apellido_paterno.setEditable(desi);
        viewClientes.jtf_apellido_materno.setEditable(desi);
        viewClientes.jtf_telefono.setEditable(desi);
        viewClientes.jtf_rfc.setEditable(desi);
        viewClientes.jtf_calle.setEditable(desi);
        viewClientes.jtf_colonia.setEditable(desi);
        viewClientes.jtf_num_interno.setEditable(desi);
        viewClientes.jtf_num_externo.setEditable(desi);
        viewClientes.jtf_codigo_postal.setEditable(desi);
        viewClientes.jtf_email.setEditable(desi);
        viewClientes.jtf_ciudad.setEditable(desi);
        viewClientes.jtf_estado.setEditable(desi);
        viewClientes.jtf_fecha_creacion.setEditable(desi);
        viewClientes.jtf_total_acumulado.setEditable(desi);
    }
    
    private void habilitarDesplazamiento(boolean desi){
        viewClientes.jb_eliminar.setEnabled(desi);
        viewClientes.jb_nuevo.setEnabled(desi);
        viewClientes.jb_editar.setEnabled(desi);
        viewClientes.jb_guardar.setEnabled(desi);
        viewClientes.jb_cancelar.setEnabled(desi);
    }
    /**
     * Método para ver el primer registro de la tabla contactos
     */
    private void jbtn_primero_actionPerformed() {
        //System.out.println("Action del boton jbtn_primero");
        modelClientes.moverPrimerRegistro();
        getValues();
    }

    /**
     * Método para ver el registro anterior de la tabla contactos.
     */
    private void jbtn_anterior_actionPerformed() {
        //System.out.println("Action del boton jbtn_anterior");
        modelClientes.moverAnteriorRegistro();
        getValues();
    }

    /**
     * Método para ver el último registro de la tabla contactos.
     */
    private void jbtn_ultimo_actionPerformed() {
        //System.out.println("Action del boton jbtn_ultimo");
        modelClientes.moverUltimoRegistro();
        getValues();
    }

    /**
     * Método para ver el siguiente registro de la tabla contactos.
     */
    private void jbtn_siguiente_actionPerformed() {
        //System.out.println("Action del boton jbtn_siguiente");
        modelClientes.moverSiguienteRegistro();
        getValues();
    }
    
    /**
     * Método que habilita la edicion de las cajas de texto Deshabilita los
     * botones de dezplazamiento A la variable Descicion se le asigna el valor
     * nuevo para saber que se va a crear un nuevo registro
     */
    private void jbtn_nuevo_actionPerformed() {
        modelClientes.setDescicion("nuevo");
        
        habilitarCajas(true);
        habilitarDesplazamiento(false);
        
        viewClientes.jtf_descuento_id.setText("");
        viewClientes.jtf_nombre.setText("");
        viewClientes.jtf_apellido_paterno.setText("");
        viewClientes.jtf_apellido_materno.setText("");
        viewClientes.jtf_telefono.setText("");
        viewClientes.jtf_rfc.setText("");
        viewClientes.jtf_calle.setText("");
        viewClientes.jtf_colonia.setText("");
        viewClientes.jtf_num_interno.setText("");
        viewClientes.jtf_num_externo.setText("");
        viewClientes.jtf_codigo_postal.setText("");
        viewClientes.jtf_email.setText("");
        viewClientes.jtf_ciudad.setText("");
        viewClientes.jtf_estado.setText("");
        viewClientes.jtf_fecha_creacion.setText("");
        viewClientes.jtf_total_acumulado.setText("");
        
        viewClientes.jb_eliminar.setEnabled(false);
        viewClientes.jb_nuevo.setEnabled(false);
        viewClientes.jb_editar.setEnabled(false);
        viewClientes.jb_guardar.setEnabled(true);
        viewClientes.jb_cancelar.setEnabled(true);
    }
    
    /**
     * Método que realiza el guardado de datos sean nuevos o actualizaciones
     * Llama al metodo guardarRegistro del modelo como parametro lo que
     * contienen sus cajas de texto Hibilitar y deshabilitar botones de CRUD
     */
    public void jbtn_guardar_actionPerformed() {
        modelClientes.guardarRegistro(Integer.parseInt(viewClientes.jtf_cliente_id.getText()), Integer.parseInt(viewClientes.jtf_descuento_id.getText()) , viewClientes.jtf_nombre.getText(), viewClientes.jtf_apellido_paterno.getText(), viewClientes.jtf_apellido_materno.getText(), viewClientes.jtf_telefono.getText(), viewClientes.jtf_rfc.getText(), viewClientes.jtf_calle.getText(), viewClientes.jtf_colonia.getText(), viewClientes.jtf_num_interno.getText(), viewClientes.jtf_num_externo.getText(), viewClientes.jtf_codigo_postal.getText(), viewClientes.jtf_email.getText(), viewClientes.jtf_ciudad.getText(), viewClientes.jtf_estado.getText(), viewClientes.jtf_fecha_creacion.getText(), Double.parseDouble(viewClientes.jtf_total_acumulado.getText()));
        
        habilitarCajas(false);
        habilitarDesplazamiento(true);
        
        viewClientes.jb_eliminar.setEnabled(true);
        viewClientes.jb_nuevo.setEnabled(true);
        viewClientes.jb_editar.setEnabled(true);
        viewClientes.jb_guardar.setEnabled(false);
        viewClientes.jb_cancelar.setEnabled(false);
        
        modelClientes.limpiarTabla();
        modelClientes.agregaraTabla();
    }
    
    /**
     * Método que elimina un dato de la BD Llama al metodo borrarRegistro del
     * modelo como parametro el id del dato que queremos borrar Llama al metodo
     * jbtn_ultimo_actionPerformed para indicar al usuario el cambio y
     * actualizacion de datos
     */
    public void jbtn_eliminar_actionPerformed() {
        modelClientes.borrarRegistro(Integer.parseInt(viewClientes.jtf_cliente_id.getText()));
        JOptionPane.showMessageDialog(null, "Moviendo al primer registro");
        jbtn_primero_actionPerformed();
        
        modelClientes.limpiarTabla();
        modelClientes.agregaraTabla();
    }
    
    public void jbtn_editar_actionPerformed() {
        modelClientes.setDescicion("editar");
        
        habilitarCajas(true);
        habilitarDesplazamiento(false);
        
        viewClientes.jb_eliminar.setEnabled(false);
        viewClientes.jb_nuevo.setEnabled(false);
        viewClientes.jb_editar.setEnabled(false);
        viewClientes.jb_guardar.setEnabled(true);
        viewClientes.jb_cancelar.setEnabled(true);
    }
    
    public void jbtn_cancelar_actionPerformed() {
        JOptionPane.showMessageDialog(null, "Registro Cancelado!!");
        JOptionPane.showMessageDialog(null, "Moviendo al Primer Registro");
        jbtn_primero_actionPerformed();

        habilitarCajas(false);
        habilitarDesplazamiento(true);

        viewClientes.jb_eliminar.setEnabled(true);
        viewClientes.jb_nuevo.setEnabled(true);
        viewClientes.jb_editar.setEnabled(true);
        viewClientes.jb_guardar.setEnabled(false);
        viewClientes.jb_cancelar.setEnabled(false);
    }
    
    public void llenarTabla(){
        modelClientes.columnasTabla();
        viewClientes.jtb_tabla_clientes.setModel(modelClientes.getModelo());
        modelClientes.agregaraTabla();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(viewClientes.jtf_buscar.hasFocus()){
            modelClientes.limpiarTabla();
            modelClientes.buscarTabla(viewClientes.jtf_buscar.getText());
            modelClientes.agregaraTabla();
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    
    MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() == viewClientes.jtb_tabla_clientes){
                int seleccion = viewClientes.jtb_tabla_clientes.getSelectedRow();
                
                viewClientes.jtf_cliente_id.setText(String.valueOf(viewClientes.jtb_tabla_clientes.getValueAt(seleccion, 0)));
                viewClientes.jtf_descuento_id.setText(String.valueOf(viewClientes.jtb_tabla_clientes.getValueAt(seleccion, 1)));
                viewClientes.jtf_nombre.setText(String.valueOf(viewClientes.jtb_tabla_clientes.getValueAt(seleccion, 2)));
                viewClientes.jtf_apellido_paterno.setText(String.valueOf(viewClientes.jtb_tabla_clientes.getValueAt(seleccion, 3)));
                viewClientes.jtf_apellido_materno.setText(String.valueOf(viewClientes.jtb_tabla_clientes.getValueAt(seleccion, 4)));
                viewClientes.jtf_telefono.setText(String.valueOf(viewClientes.jtb_tabla_clientes.getValueAt(seleccion, 5)));
                viewClientes.jtf_rfc.setText(String.valueOf(viewClientes.jtb_tabla_clientes.getValueAt(seleccion, 6)));
                viewClientes.jtf_calle.setText(String.valueOf(viewClientes.jtb_tabla_clientes.getValueAt(seleccion, 7)));
                viewClientes.jtf_colonia.setText(String.valueOf(viewClientes.jtb_tabla_clientes.getValueAt(seleccion, 8)));
                viewClientes.jtf_num_interno.setText(String.valueOf(viewClientes.jtb_tabla_clientes.getValueAt(seleccion, 9)));
                viewClientes.jtf_num_externo.setText(String.valueOf(viewClientes.jtb_tabla_clientes.getValueAt(seleccion, 10)));
                viewClientes.jtf_codigo_postal.setText(String.valueOf(viewClientes.jtb_tabla_clientes.getValueAt(seleccion, 11)));
                viewClientes.jtf_email.setText(String.valueOf(viewClientes.jtb_tabla_clientes.getValueAt(seleccion, 12)));
                viewClientes.jtf_ciudad.setText(String.valueOf(viewClientes.jtb_tabla_clientes.getValueAt(seleccion, 13)));
                viewClientes.jtf_estado.setText(String.valueOf(viewClientes.jtb_tabla_clientes.getValueAt(seleccion, 14)));
                viewClientes.jtf_fecha_creacion.setText(String.valueOf(viewClientes.jtb_tabla_clientes.getValueAt(seleccion, 15)));
                viewClientes.jtf_total_acumulado.setText(String.valueOf(viewClientes.jtb_tabla_clientes.getValueAt(seleccion, 16)));
            }
        }
        @Override
        public void mousePressed(MouseEvent e) {}
        @Override
        public void mouseReleased(MouseEvent e) {}
        @Override
        public void mouseEntered(MouseEvent e) {}
        @Override
        public void mouseExited(MouseEvent e) {}
    };
}
