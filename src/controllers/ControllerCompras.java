package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import models.ModelCompras;
import views.ViewCompras;

/**
 *
 * @author EstebanIslas
 */
public class ControllerCompras implements KeyListener{
    
    public ModelCompras modelCompras;
    public ViewCompras viewCompras;
    
    MouseListener mouseListener = new MouseListener() {
        @Override
        public void mouseClicked(MouseEvent e) {
            if(e.getSource() == viewCompras.jtb_productos){
                int seleccion = viewCompras.jtb_productos.getSelectedRow();
                
                viewCompras.jtf_nombre_producto.setText(String.valueOf(viewCompras.jtb_productos.getValueAt(seleccion, 1)));
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
    
    ActionListener actionListener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            
        }
    };

    @Override
    public void keyTyped(KeyEvent e) {
        if (viewCompras.jtf_buscar.hasFocus()){
            modelCompras.limpiarTabla();
            modelCompras.buscarProducto(viewCompras.jtf_buscar.getText());
            modelCompras.agregaraTablaProducto();
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {}
    @Override
    public void keyReleased(KeyEvent e) {}

    public ControllerCompras(ModelCompras modelCompras, ViewCompras viewCompras) {
        this.modelCompras = modelCompras;
        this.viewCompras = viewCompras;
        this.viewCompras.jtb_productos.addMouseListener(mouseListener);
        initComponents();
        llenarTablaProductos();
        this.viewCompras.jtf_buscar.addKeyListener(this);
    }
    
    private void setActionListener(){
        
    }
    
    private void initComponents(){
        modelCompras.consultaProductosCompras();
    }
    
    public void llenarTablaProductos(){
        modelCompras.columnasTablaProducto();
        viewCompras.jtb_productos.setModel(modelCompras.getModelo());
        modelCompras.agregaraTablaProducto();
    }
    
}
