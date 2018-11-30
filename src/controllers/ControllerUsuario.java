package controllers;

import models.ModelUsuario;
import views.ViewUsuario;
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
public class ControllerUsuario implements KeyListener{
    public ModelUsuario modelUsuario;
    public ViewUsuario viewUsuario;

    public ControllerUsuario(ModelUsuario modelUsuario, ViewUsuario viewUsuario) {
        this.modelUsuario = modelUsuario;
        this.viewUsuario = viewUsuario;
        this.viewUsuario.jtb_tabla_usuario.addMouseListener(mouseListener);
        setActionListener();
        initComponets();
        inicioCrud();
    }
    
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == viewUsuario.jb_primero) {
                jbtn_primero_actionPerformed();
            } else if (e.getSource() == viewUsuario.jb_anterior) {
                jbtn_anterior_actionPerformed();
            } else if (e.getSource() == viewUsuario.jb_siguiente) {
                jbtn_siguiente_actionPerformed();
            } else if (e.getSource() == viewUsuario.jb_ultimo) {
                jbtn_ultimo_actionPerformed();
            } // CRUD //
            else if (e.getSource() == viewUsuario.jb_nuevo) {
                jbtn_nuevo_actionPerformed();
            } else if (e.getSource() == viewUsuario.jb_guardar) {
                jbtn_guardar_actionPerformed();
            } else if (e.getSource() == viewUsuario.jb_eliminar) {
                jbtn_eliminar_actionPerformed();
            } else if (e.getSource() == viewUsuario.jb_editar) {
                jbtn_editar_actionPerformed();
            } else if (e.getSource() == viewUsuario.jb_cancelar) {
                jbtn_cancelar_actionPerformed();
            }
        }
    };
    
    private void setActionListener(){
        viewUsuario.jb_primero.addActionListener(actionListener);
        viewUsuario.jb_anterior.addActionListener(actionListener);
        viewUsuario.jb_siguiente.addActionListener(actionListener);
        viewUsuario.jb_ultimo.addActionListener(actionListener);
        
        // CRUD //
        viewUsuario.jb_nuevo.addActionListener(actionListener);
        viewUsuario.jb_guardar.addActionListener(actionListener);
        viewUsuario.jb_eliminar.addActionListener(actionListener);
        viewUsuario.jb_editar.addActionListener(actionListener);
        viewUsuario.jb_cancelar.addActionListener(actionListener);
    }
    
    private void inicioCrud(){
        viewUsuario.jb_nuevo.setEnabled(true);
        viewUsuario.jb_editar.setEnabled(true);
        viewUsuario.jb_eliminar.setEnabled(true);
        viewUsuario.jb_guardar.setEnabled(false);
        viewUsuario.jb_cancelar.setEnabled(false);
    }
    
    private void initComponets(){
        modelUsuario.consultaUsuario();
        getValues();
        
        //##No debe ser visible para el usuario pero se utiliza para el crud
        viewUsuario.jtf_usuario_id.setVisible(false);
        viewUsuario.jtf_usuario_id.setVisible(false);
        viewUsuario.jtf_usuario_id.setEnabled(false);
        
        habilitarCajas(false);
        habilitarDesplazamiento(true);
        
        llenarTabla();
        
        //Agregar el Key listener
        this.viewUsuario.jtf_buscar.addKeyListener(this);
    }
    
    private void getValues(){
        viewUsuario.jtf_usuario_id.setText(String.valueOf(modelUsuario.getUsuario_id()));
        viewUsuario.jtf_sucursal_id.setText(String.valueOf(modelUsuario.getSucursal_id()));
        viewUsuario.jtf_tipo.setText(modelUsuario.getTipo());
        viewUsuario.jtf_nombre.setText(modelUsuario.getNombre());
        viewUsuario.jtf_apellido_paterno.setText(modelUsuario.getApellido_paterno());
        viewUsuario.jtf_apellido_materno.setText(modelUsuario.getApellido_materno());
        viewUsuario.jtf_telefono.setText(modelUsuario.getTelefono());
        viewUsuario.jtf_rfc.setText(modelUsuario.getRfc());
        viewUsuario.jtf_calle.setText(modelUsuario.getCalle());
        viewUsuario.jtf_colonia.setText(modelUsuario.getColonia());
        viewUsuario.jtf_num_interno.setText(modelUsuario.getNum_interior());
        viewUsuario.jtf_num_externo.setText(modelUsuario.getNum_exterior());
        viewUsuario.jtf_codigo_postal.setText(modelUsuario.getCodigo_postal());
        viewUsuario.jtf_email.setText(modelUsuario.getEmail());
        viewUsuario.jtf_ciudad.setText(modelUsuario.getCiudad());
        viewUsuario.jtf_estado.setText(modelUsuario.getEstado());
        viewUsuario.jtf_numero_seguro.setText(String.valueOf(modelUsuario.getNumero_seguro()));
        viewUsuario.jtf_curp.setText(modelUsuario.getCurp());
        viewUsuario.jtf_password.setText(modelUsuario.getPassword());
    }
    
    private void habilitarCajas(boolean desi){
        viewUsuario.jtf_sucursal_id.setEditable(desi);
        viewUsuario.jtf_tipo.setEditable(desi);
        viewUsuario.jtf_nombre.setEditable(desi);
        viewUsuario.jtf_apellido_paterno.setEditable(desi);
        viewUsuario.jtf_apellido_materno.setEditable(desi);
        viewUsuario.jtf_telefono.setEditable(desi);
        viewUsuario.jtf_rfc.setEditable(desi);
        viewUsuario.jtf_calle.setEditable(desi);
        viewUsuario.jtf_colonia.setEditable(desi);
        viewUsuario.jtf_num_interno.setEditable(desi);
        viewUsuario.jtf_num_externo.setEditable(desi);
        viewUsuario.jtf_codigo_postal.setEditable(desi);
        viewUsuario.jtf_email.setEditable(desi);
        viewUsuario.jtf_ciudad.setEditable(desi);
        viewUsuario.jtf_estado.setEditable(desi);
        viewUsuario.jtf_numero_seguro.setEditable(desi);
        viewUsuario.jtf_curp.setEditable(desi);
        viewUsuario.jtf_password.setEditable(desi);
    }
    
    private void habilitarDesplazamiento(boolean desi){
        viewUsuario.jb_eliminar.setEnabled(desi);
        viewUsuario.jb_nuevo.setEnabled(desi);
        viewUsuario.jb_editar.setEnabled(desi);
        viewUsuario.jb_guardar.setEnabled(desi);
        viewUsuario.jb_cancelar.setEnabled(desi);
    }
    
    /**
     * Método para ver el primer registro de la tabla contactos
     */
    private void jbtn_primero_actionPerformed() {
        //System.out.println("Action del boton jbtn_primero");
        modelUsuario.moverPrimerRegistro();
        getValues();
    }

    /**
     * Método para ver el registro anterior de la tabla contactos.
     */
    private void jbtn_anterior_actionPerformed() {
        //System.out.println("Action del boton jbtn_anterior");
        modelUsuario.moverAnteriorRegistro();
        getValues();
    }

    /**
     * Método para ver el último registro de la tabla contactos.
     */
    private void jbtn_ultimo_actionPerformed() {
        //System.out.println("Action del boton jbtn_ultimo");
        modelUsuario.moverUltimoRegistro();
        getValues();
    }

    /**
     * Método para ver el siguiente registro de la tabla contactos.
     */
    private void jbtn_siguiente_actionPerformed() {
        //System.out.println("Action del boton jbtn_siguiente");
        modelUsuario.moverSiguienteRegistro();
        getValues();
    }
    
     /**
     * Método que habilita la edicion de las cajas de texto Deshabilita los
     * botones de dezplazamiento A la variable Descicion se le asigna el valor
     * nuevo para saber que se va a crear un nuevo registro
     */
    private void jbtn_nuevo_actionPerformed() {
        modelUsuario.setDescicion("nuevo");
        
        habilitarCajas(true);
        habilitarDesplazamiento(false);
        
        viewUsuario.jtf_sucursal_id.setText("");
        viewUsuario.jtf_tipo.setText("");
        viewUsuario.jtf_nombre.setText("");
        viewUsuario.jtf_apellido_paterno.setText("");
        viewUsuario.jtf_apellido_materno.setText("");
        viewUsuario.jtf_telefono.setText("");
        viewUsuario.jtf_rfc.setText("");
        viewUsuario.jtf_calle.setText("");
        viewUsuario.jtf_colonia.setText("");
        viewUsuario.jtf_num_interno.setText("");
        viewUsuario.jtf_num_externo.setText("");
        viewUsuario.jtf_codigo_postal.setText("");
        viewUsuario.jtf_email.setText("");
        viewUsuario.jtf_ciudad.setText("");
        viewUsuario.jtf_estado.setText("");
        viewUsuario.jtf_numero_seguro.setText("");
        viewUsuario.jtf_curp.setText("");
        viewUsuario.jtf_password.setText("");
        
        viewUsuario.jb_eliminar.setEnabled(false);
        viewUsuario.jb_nuevo.setEnabled(false);
        viewUsuario.jb_editar.setEnabled(false);
        viewUsuario.jb_guardar.setEnabled(true);
        viewUsuario.jb_cancelar.setEnabled(true);
    }
    
    /**
     * Método que realiza el guardado de datos sean nuevos o actualizaciones
     * Llama al metodo guardarRegistro del modelo como parametro lo que
     * contienen sus cajas de texto Hibilitar y deshabilitar botones de CRUD
     */
    public void jbtn_guardar_actionPerformed() {

        modelUsuario.guardarRegistro(Integer.parseInt(viewUsuario.jtf_usuario_id.getText()), Integer.parseInt(viewUsuario.jtf_sucursal_id.getText()), viewUsuario.jtf_tipo.getText(), viewUsuario.jtf_nombre.getText(), viewUsuario.jtf_apellido_paterno.getText(), viewUsuario.jtf_apellido_materno.getText(), viewUsuario.jtf_telefono.getText(), viewUsuario.jtf_rfc.getText(), viewUsuario.jtf_calle.getText(), viewUsuario.jtf_colonia.getText(), viewUsuario.jtf_num_interno.getText(), viewUsuario.jtf_num_externo.getText(), viewUsuario.jtf_codigo_postal.getText(), viewUsuario.jtf_email.getText(), viewUsuario.jtf_ciudad.getText(), viewUsuario.jtf_estado.getText(), Integer.parseInt(viewUsuario.jtf_numero_seguro.getText()), viewUsuario.jtf_curp.getText(), viewUsuario.jtf_password.getText());

        habilitarCajas(false);
        habilitarDesplazamiento(true);

        viewUsuario.jb_eliminar.setEnabled(true);
        viewUsuario.jb_nuevo.setEnabled(true);
        viewUsuario.jb_editar.setEnabled(true);
        viewUsuario.jb_guardar.setEnabled(false);
        viewUsuario.jb_cancelar.setEnabled(false);

        modelUsuario.limpiarTabla();
        modelUsuario.agregaraTabla();
    }
    
    /**
     * Método que elimina un dato de la BD Llama al metodo borrarRegistro del
     * modelo como parametro el id del dato que queremos borrar Llama al metodo
     * jbtn_ultimo_actionPerformed para indicar al usuario el cambio y
     * actualizacion de datos
     */
    public void jbtn_eliminar_actionPerformed() {

        modelUsuario.borrarRegistro(Integer.parseInt(viewUsuario.jtf_usuario_id.getText()));
        jbtn_ultimo_actionPerformed();
    }
    
    public void jbtn_editar_actionPerformed() {
        modelUsuario.setDescicion("editar");
        
        habilitarCajas(true);
        habilitarDesplazamiento(false);
        
        viewUsuario.jb_eliminar.setEnabled(false);
        viewUsuario.jb_nuevo.setEnabled(false);
        viewUsuario.jb_editar.setEnabled(false);
        viewUsuario.jb_guardar.setEnabled(true);
        viewUsuario.jb_cancelar.setEnabled(true);
    }
    
    public void jbtn_cancelar_actionPerformed() {
        JOptionPane.showMessageDialog(null, "Registro Cancelado!!");
        JOptionPane.showMessageDialog(null, "Moviendo al Primer Registro");
        jbtn_primero_actionPerformed();

        habilitarCajas(false);
        habilitarDesplazamiento(true);

        viewUsuario.jb_eliminar.setEnabled(true);
        viewUsuario.jb_nuevo.setEnabled(true);
        viewUsuario.jb_editar.setEnabled(true);
        viewUsuario.jb_guardar.setEnabled(false);
        viewUsuario.jb_cancelar.setEnabled(false);
    }
    
    public void llenarTabla(){
        modelUsuario.columnasTabla();
        viewUsuario.jtb_tabla_usuario.setModel(modelUsuario.getModelo());
        modelUsuario.agregaraTabla();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(viewUsuario.jtf_buscar.hasFocus()){
            modelUsuario.columnasTabla();
            modelUsuario.buscarTabla(viewUsuario.jtf_buscar.getText());
            modelUsuario.agregaraTabla();
        }
    }
    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}
    
    MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() == viewUsuario.jtb_tabla_usuario){
                int seleccion = viewUsuario.jtb_tabla_usuario.getSelectedRow();
                
                viewUsuario.jtf_usuario_id.setText(String.valueOf(viewUsuario.jtb_tabla_usuario.getValueAt(seleccion, 0)));
                viewUsuario.jtf_sucursal_id.setText(String.valueOf(viewUsuario.jtb_tabla_usuario.getValueAt(seleccion, 1)));
                viewUsuario.jtf_tipo.setText(String.valueOf(viewUsuario.jtb_tabla_usuario.getValueAt(seleccion, 2)));
                viewUsuario.jtf_nombre.setText(String.valueOf(viewUsuario.jtb_tabla_usuario.getValueAt(seleccion, 3)));
                viewUsuario.jtf_apellido_paterno.setText(String.valueOf(viewUsuario.jtb_tabla_usuario.getValueAt(seleccion, 4)));
                viewUsuario.jtf_apellido_materno.setText(String.valueOf(viewUsuario.jtb_tabla_usuario.getValueAt(seleccion, 5)));
                viewUsuario.jtf_telefono.setText(String.valueOf(viewUsuario.jtb_tabla_usuario.getValueAt(seleccion, 6)));
                viewUsuario.jtf_rfc.setText(String.valueOf(viewUsuario.jtb_tabla_usuario.getValueAt(seleccion, 7)));
                viewUsuario.jtf_calle.setText(String.valueOf(viewUsuario.jtb_tabla_usuario.getValueAt(seleccion, 8)));
                viewUsuario.jtf_colonia.setText(String.valueOf(viewUsuario.jtb_tabla_usuario.getValueAt(seleccion, 9)));
                viewUsuario.jtf_num_interno.setText(String.valueOf(viewUsuario.jtb_tabla_usuario.getValueAt(seleccion, 10)));
                viewUsuario.jtf_num_externo.setText(String.valueOf(viewUsuario.jtb_tabla_usuario.getValueAt(seleccion, 11)));
                viewUsuario.jtf_codigo_postal.setText(String.valueOf(viewUsuario.jtb_tabla_usuario.getValueAt(seleccion, 12)));
                viewUsuario.jtf_email.setText(String.valueOf(viewUsuario.jtb_tabla_usuario.getValueAt(seleccion, 13)));
                viewUsuario.jtf_ciudad.setText(String.valueOf(viewUsuario.jtb_tabla_usuario.getValueAt(seleccion, 14)));
                viewUsuario.jtf_estado.setText(String.valueOf(viewUsuario.jtb_tabla_usuario.getValueAt(seleccion, 15)));
                viewUsuario.jtf_numero_seguro.setText(String.valueOf(viewUsuario.jtb_tabla_usuario.getValueAt(seleccion, 16)));
                viewUsuario.jtf_curp.setText(String.valueOf(viewUsuario.jtb_tabla_usuario.getValueAt(seleccion, 17)));
                viewUsuario.jtf_password.setText(String.valueOf(viewUsuario.jtb_tabla_usuario.getValueAt(seleccion, 18)));
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
