package controllers;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
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
                
                viewCompras.jtf_id_producto.setText(String.valueOf(viewCompras.jtb_productos.getValueAt(seleccion, 0)));
                viewCompras.jtf_nombre_producto.setText(String.valueOf(viewCompras.jtb_productos.getValueAt(seleccion, 1)));
                
            }
            else if(e.getSource() == viewCompras.jtb_detalle_compra){
                int seleccion = viewCompras.jtb_detalle_compra.getSelectedRow();
                viewCompras.jtf_subtotal.setText(String.valueOf(viewCompras.jtb_detalle_compra.getValueAt(seleccion, 2)));
                String prec =(String.valueOf(viewCompras.jtb_detalle_compra.getValueAt(seleccion, 2)));
                
                double precio = Double.parseDouble(prec);
                double iva = (precio * 0.16);
                double total = iva + precio;
                
                viewCompras.jtf_iva.setText(String.valueOf(iva));
                viewCompras.jtf_total.setText(String.valueOf(total));
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
            if(e.getSource() == viewCompras.jb_agregar){
                System.out.println("Agregar productos");
                agregarProductos();
            }else if(e.getSource() == viewCompras.jb_eliminar){
                //System.out.println("Agregar productos");
                jbtn_eliminarProductoActionListener();
            }else if(e.getSource() == viewCompras.jb_finalizar){
                //System.out.println("Agregar productos");
                jbtn_finalizarcompraActionListener();
                limpiar();
            }
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
        this.viewCompras.jtb_detalle_compra.addMouseListener(mouseListener);
        initComponents();
        setActionListener();
        llenarTablaProductos();
        this.viewCompras.jtf_buscar.addKeyListener(this);
    }
    
    private void setActionListener(){
        viewCompras.jb_agregar.addActionListener(actionListener);
        viewCompras.jb_eliminar.addActionListener(actionListener);
        viewCompras.jb_finalizar.addActionListener(actionListener);
        
    }
    
    private void initComponents(){
        modelCompras.consultaProductosCompras();
        modelCompras.columnasTablaDetalleCompra();
        viewCompras.jl_compra_id.setVisible(false);
        viewCompras.jtf_compra_id.setVisible(false);
        viewCompras.jtf_id_producto.setVisible(false);
        viewCompras.jsp_cantidad.setValue(1);
    }
    
    private void jbtn_eliminarProductoActionListener(){
        if(viewCompras.jtb_detalle_compra.getSelectedRow() != -1){
            DefaultTableModel model = (DefaultTableModel) viewCompras.jtb_detalle_compra.getModel();
            
            model.removeRow(viewCompras.jtb_detalle_compra.getSelectedRow());
            
            JOptionPane.showMessageDialog(null, "Producto Eliminado");
        }
    }
    
    public void llenarTablaProductos(){
        modelCompras.columnasTablaProducto();
        viewCompras.jtb_productos.setModel(modelCompras.getModelo());
        modelCompras.agregaraTablaProducto();
    }
    
    private void agregarProductos(){
        viewCompras.jtb_detalle_compra.setModel(modelCompras.getTb_detalle_compra());
        modelCompras.agregarProductosaDetalle(Integer.parseInt(viewCompras.jtf_id_producto.getText()), viewCompras.jtf_nombre_producto.getText(), viewCompras.jtf_precio_compra.getText(), String.valueOf(viewCompras.jsp_cantidad.getValue()));
        
    }
    
    private void jbtn_finalizarcompraActionListener(){
        int seleccion = viewCompras.jtb_detalle_compra.getSelectedRow();
        String producto =(String.valueOf(viewCompras.jtb_detalle_compra.getValueAt(seleccion, 0)));
        String cant =(String.valueOf(viewCompras.jtb_detalle_compra.getValueAt(seleccion, 3)));
        String prec =(String.valueOf(viewCompras.jtb_detalle_compra.getValueAt(seleccion, 2)));
        
        int id_producto = Integer.parseInt(producto);    
        int cantidad = Integer.parseInt(cant);
        double precio = Double.parseDouble(prec);
        
        modelCompras.insertarDetalle(Integer.parseInt(viewCompras.jtf_sucursal_id.getText()), Integer.parseInt(viewCompras.jtf_usuarioid.getText()), id_producto, cantidad, precio, Double.parseDouble(viewCompras.jtf_iva.getText()), Double.parseDouble(viewCompras.jtf_total.getText()));
    }
    
    private void limpiar(){
        viewCompras.jtf_usuarioid.setText("");
        viewCompras.jtf_sucursal_id.setText("");
        viewCompras.jtf_nombre_producto.setText("");
        viewCompras.jsp_cantidad.setValue(1);
        viewCompras.jtf_precio_compra.setText("");
        viewCompras.jtf_subtotal.setText("");
        viewCompras.jtf_iva.setText("");
        viewCompras.jtf_total.setText("");
        modelCompras.limpiarTabla();
    }
    
}
