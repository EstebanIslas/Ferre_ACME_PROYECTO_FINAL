package controllers;

import models.ModelProveedores;
import views.ViewProveedores;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JOptionPane;

/**
 *
 * @author EstebaIslas
 */
public class ControllerProveedores implements KeyListener{
    public ModelProveedores modelProveedores;
    public ViewProveedores viewProveedores;

    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewProveedores.jb_primero) {
                jbtn_primero_actionPerformed();
            } else if (e.getSource() == viewProveedores.jb_anterior) {
                jbtn_anterior_actionPerformed();
            } else if (e.getSource() == viewProveedores.jb_siguiente) {
                jbtn_siguiente_actionPerformed();
            } else if (e.getSource() == viewProveedores.jb_ultimo) {
                jbtn_ultimo_actionPerformed();
            } // CRUD //
            else if (e.getSource() == viewProveedores.jb_nuevo) {
                jbtn_nuevo_actionPerformed();
            } else if (e.getSource() == viewProveedores.jb_guardar) {
                jbtn_guardar_actionPerformed();
            } else if (e.getSource() == viewProveedores.jb_eliminar) {
                jbtn_eliminar_actionPerformed();
            } else if (e.getSource() == viewProveedores.jb_editar) {
                jbtn_editar_actionPerformed();
            } else if (e.getSource() == viewProveedores.jb_cancelar) {
                jbtn_cancelar_actionPerformed();
            }
        }
    };
    
    public ControllerProveedores(ModelProveedores modelProveedores, ViewProveedores viewProveedores) {
        this.modelProveedores = modelProveedores;
        this.viewProveedores = viewProveedores;
        setActionListener();
        initComponets();
        inicioCrud();
    }
    
    private void setActionListener(){
        viewProveedores.jb_primero.addActionListener(actionListener);
        viewProveedores.jb_anterior.addActionListener(actionListener);
        viewProveedores.jb_siguiente.addActionListener(actionListener);
        viewProveedores.jb_ultimo.addActionListener(actionListener);
        
        // CRUD //
        viewProveedores.jb_nuevo.addActionListener(actionListener);
        viewProveedores.jb_guardar.addActionListener(actionListener);
        viewProveedores.jb_eliminar.addActionListener(actionListener);
        viewProveedores.jb_editar.addActionListener(actionListener);
        viewProveedores.jb_cancelar.addActionListener(actionListener);
     }
    
    private void inicioCrud(){
        viewProveedores.jb_nuevo.setEnabled(true);
        viewProveedores.jb_editar.setEnabled(true);
        viewProveedores.jb_eliminar.setEnabled(true);
        viewProveedores.jb_guardar.setEnabled(false);
        viewProveedores.jb_cancelar.setEnabled(false);
    }
    
    private void initComponets(){
        modelProveedores.consultaProveedores();
        getValues();
        //##No debe ser visible para el usuario pero se utiliza para el crud
        viewProveedores.jl_proveedor_id.setVisible(false);
        viewProveedores.jtf_proveedor_id.setVisible(false);
        viewProveedores.jtf_proveedor_id.setEnabled(false);

        
        
        habilitarCajas(false);
        habilitarDezplazamiento(true);
        
        llenarTabla();
        
        //Aplicar la busqueda con KeyListener
        this.viewProveedores.jtf_buscar.addKeyListener(this);
    }
    
    private void getValues(){
        viewProveedores.jtf_proveedor_id.setText(String.valueOf(modelProveedores.getId_proveedor()));
        viewProveedores.jtf_nombre.setText(modelProveedores.getNombre());
        viewProveedores.jtf_apellido_paterno.setText(modelProveedores.getApellido_paterno());
        viewProveedores.jtf_apellido_materno.setText(modelProveedores.getApellido_materno());
        viewProveedores.jtf_empresa.setText(modelProveedores.getEmpresa());
        viewProveedores.jtf_telefono.setText(modelProveedores.getTelefono());
        viewProveedores.jtf_calle.setText(modelProveedores.getCalle());
        viewProveedores.jtf_numero.setText(String.valueOf(modelProveedores.getNumero()));
        viewProveedores.jtf_colonia.setText(modelProveedores.getColonia());
        viewProveedores.jtf_codigo_postal.setText(modelProveedores.getCodigo_postal());
        viewProveedores.jtf_email.setText(modelProveedores.getEmail());
        viewProveedores.jtf_ciudad.setText(modelProveedores.getCiudad());
        viewProveedores.jtf_estado.setText(modelProveedores.getEstado());
    }
    
    private void habilitarCajas(boolean desi) {
        viewProveedores.jtf_nombre.setEditable(desi);
        viewProveedores.jtf_apellido_paterno.setEditable(desi);
        viewProveedores.jtf_apellido_materno.setEditable(desi);
        viewProveedores.jtf_empresa.setEditable(desi);
        viewProveedores.jtf_telefono.setEditable(desi);
        viewProveedores.jtf_calle.setEditable(desi);
        viewProveedores.jtf_numero.setEditable(desi);
        viewProveedores.jtf_colonia.setEditable(desi);
        viewProveedores.jtf_codigo_postal.setEditable(desi);
        viewProveedores.jtf_email.setEditable(desi);
        viewProveedores.jtf_ciudad.setEditable(desi);
        viewProveedores.jtf_estado.setEditable(desi);
    }

    private void habilitarDezplazamiento(boolean desi) {
        viewProveedores.jb_eliminar.setEnabled(desi);
        viewProveedores.jb_nuevo.setEnabled(desi);
        viewProveedores.jb_editar.setEnabled(desi);
        viewProveedores.jb_guardar.setEnabled(desi);
        viewProveedores.jb_cancelar.setEnabled(desi);
    }
    
    /**
     * Método para ver el primer registro de la tabla contactos
     */
    private void jbtn_primero_actionPerformed() {
        //System.out.println("Action del boton jbtn_primero");
        modelProveedores.moverPrimerRegistro();
        getValues();
    }

    /**
     * Método para ver el registro anterior de la tabla contactos.
     */
    private void jbtn_anterior_actionPerformed() {
        //System.out.println("Action del boton jbtn_anterior");
        modelProveedores.moverAnteriorRegistro();
        getValues();
    }

    /**
     * Método para ver el último registro de la tabla contactos.
     */
    private void jbtn_ultimo_actionPerformed() {
        //System.out.println("Action del boton jbtn_ultimo");
        modelProveedores.moverUltimoRegistro();
        getValues();
    }

    /**
     * Método para ver el siguiente registro de la tabla contactos.
     */
    private void jbtn_siguiente_actionPerformed() {
        //System.out.println("Action del boton jbtn_siguiente");
        modelProveedores.moverSiguienteRegistro();
        getValues();
    }
    
    /**
     * Método que habilita la edicion de las cajas de texto Deshabilita los
     * botones de dezplazamiento A la variable Descicion se le asigna el valor
     * nuevo para saber que se va a crear un nuevo registro
     */
    private void jbtn_nuevo_actionPerformed() {
        
        modelProveedores.setDescicion("nuevo");
        
        habilitarCajas(true);
        habilitarDezplazamiento(false);
        
        viewProveedores.jtf_nombre.setText("");
        viewProveedores.jtf_apellido_paterno.setText("");
        viewProveedores.jtf_apellido_materno.setText("");
        viewProveedores.jtf_empresa.setText("");
        viewProveedores.jtf_telefono.setText("");
        viewProveedores.jtf_calle.setText("");
        viewProveedores.jtf_numero.setText("");
        viewProveedores.jtf_colonia.setText("");
        viewProveedores.jtf_codigo_postal.setText("");
        viewProveedores.jtf_email.setText("");
        viewProveedores.jtf_ciudad.setText("");
        viewProveedores.jtf_estado.setText("");
        
        viewProveedores.jb_eliminar.setEnabled(false);
        viewProveedores.jb_nuevo.setEnabled(false);
        viewProveedores.jb_editar.setEnabled(false);
        viewProveedores.jb_guardar.setEnabled(true);
        viewProveedores.jb_cancelar.setEnabled(true);
    }
    
    /**
     * Método que realiza el guardado de datos sean nuevos o actualizaciones
     * Llama al metodo guardarRegistro del modelo como parametro lo que
     * contienen sus cajas de texto Hibilitar y deshabilitar botones de CRUD
     */
    public void jbtn_guardar_actionPerformed() {
        
        modelProveedores.guardarRegistro(Integer.parseInt(viewProveedores.jtf_proveedor_id.getText()), viewProveedores.jtf_nombre.getText(), viewProveedores.jtf_apellido_paterno.getText(), viewProveedores.jtf_apellido_materno.getText(), viewProveedores.jtf_empresa.getText(), viewProveedores.jtf_telefono.getText(), viewProveedores.jtf_calle.getText(), Integer.parseInt(viewProveedores.jtf_numero.getText()), viewProveedores.jtf_colonia.getText(), viewProveedores.jtf_codigo_postal.getText(), viewProveedores.jtf_email.getText(), viewProveedores.jtf_ciudad.getText(), viewProveedores.jtf_estado.getText());
        
        habilitarCajas(false);
        habilitarDezplazamiento(true);
        
        viewProveedores.jb_eliminar.setEnabled(true);
        viewProveedores.jb_nuevo.setEnabled(true);
        viewProveedores.jb_editar.setEnabled(true);
        viewProveedores.jb_guardar.setEnabled(false);
        viewProveedores.jb_cancelar.setEnabled(false);
    }
    /**
     * Método que elimina un dato de la BD Llama al metodo borrarRegistro del
     * modelo como parametro el id del dato que queremos borrar Llama al metodo
     * jbtn_ultimo_actionPerformed para indicar al usuario el cambio y
     * actualizacion de datos
     */
    public void jbtn_eliminar_actionPerformed() {
        
        modelProveedores.borrarRegistro(Integer.parseInt(viewProveedores.jtf_proveedor_id.getText()));
        jbtn_ultimo_actionPerformed();
    }
    
    public void jbtn_editar_actionPerformed() {
        modelProveedores.setDescicion("editar");
        
        habilitarCajas(true);
        habilitarDezplazamiento(false);
        
        viewProveedores.jb_eliminar.setEnabled(false);
        viewProveedores.jb_nuevo.setEnabled(false);
        viewProveedores.jb_editar.setEnabled(false);
        viewProveedores.jb_guardar.setEnabled(true);
        viewProveedores.jb_cancelar.setEnabled(true);
    }
    
    public void jbtn_cancelar_actionPerformed() {
        JOptionPane.showMessageDialog(null, "Registro Cancelado!!");
        jbtn_primero_actionPerformed();
        
        habilitarCajas(false);
        habilitarDezplazamiento(true);
        
        viewProveedores.jb_eliminar.setEnabled(true);
        viewProveedores.jb_nuevo.setEnabled(true);
        viewProveedores.jb_editar.setEnabled(true);
        viewProveedores.jb_guardar.setEnabled(false);
        viewProveedores.jb_cancelar.setEnabled(false);
    }
    
    public void llenarTabla() {
        modelProveedores.columnasTabla();
        viewProveedores.jtb_tabla_productos.setModel(modelProveedores.getModelo());
        modelProveedores.agregaraTabla();

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if (viewProveedores.jtf_buscar.hasFocus()){
            modelProveedores.limpiarTabla();
            modelProveedores.buscarTabla(viewProveedores.jtf_buscar.getText());
            modelProveedores.agregaraTabla();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        
    }
}
