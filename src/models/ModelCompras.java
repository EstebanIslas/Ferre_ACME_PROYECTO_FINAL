package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EstebanIslas
 */
public class ModelCompras extends Conexion {

    private Connection conexion;
    private ResultSet rs;
    private PreparedStatement ps;

    // ####### TABLA COMPRAS ####### //
    ///////////////////////////////////
    private int id_compra;
    private int prosucid;
    private int id_usuario;
    private String fecha_compra;
    private double subtotal_compra;
    private double iva;
    private double total;

    ///////////////////////////////////
    public int getId_compra() {
        return id_compra;
    }

    public void setId_compra(int id_compra) {
        this.id_compra = id_compra;
    }

    public int getProsucid() {
        return prosucid;
    }

    public void setProsucid(int prosucid) {
        this.prosucid = prosucid;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(String fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public double getSubtotal_compra() {
        return subtotal_compra;
    }

    public void setSubtotal_compra(double subtotal_compra) {
        this.subtotal_compra = subtotal_compra;
    }

    public double getIva() {
        return iva;
    }

    public void setIva(double iva) {
        this.iva = iva;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    ///////////////////////////////////
    // ####### TABLA DETALLE_COMPRAS ####### //
    ///////////////////////////////////////////
    private int detcompid;
    private int compras_id;
    private int producto_id;
    private int cantidad_producto;
    private double precio_compra;
    private double total_detalle;

    private DefaultTableModel modelo = new DefaultTableModel();

    public int getDetcompid() {
        return detcompid;
    }

    public void setDetcompid(int detcompid) {
        this.detcompid = detcompid;
    }

    public int getCompras_id() {
        return compras_id;
    }

    public void setCompras_id(int compras_id) {
        this.compras_id = compras_id;
    }

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public int getCantidad_producto() {
        return cantidad_producto;
    }

    public void setCantidad_producto(int cantidad_producto) {
        this.cantidad_producto = cantidad_producto;
    }

    public double getPrecio_compra() {
        return precio_compra;
    }

    public void setPrecio_compra(double precio_compra) {
        this.precio_compra = precio_compra;
    }

    public double getTotal_detalle() {
        return total_detalle;
    }

    public void setTotal_detalle(double total_detalle) {
        this.total_detalle = total_detalle;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public void consultaProductosCompras() {
        try {
            conexion = null;
            conexion = getConexion();
            ps = conexion.prepareStatement("SELECT productoid, nombre, tipo, marca FROM productos");
            rs = ps.executeQuery();
            rs.next();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en Consulta de productos 001" + ex.getMessage());
        }
    }

    public void columnasTablaProducto() {
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Tipo");
        modelo.addColumn("Marca");
    }

    public void agregaraTablaProducto() {
        try {
            String[] datos = new String[4];
            rs.first();
            do {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);

                modelo.addRow(datos);

            } while (rs.next());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en Tabla!! " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en Tabla!! " + ex.getMessage());
        }
    }

    public void buscarProducto(String buscar) {
        try {
            conexion = null;
            conexion = getConexion();
            ps = conexion.prepareStatement("SELECT productoid, nombre, tipo, marca from productos WHERE nombre LIKE '%" + buscar + "%';");
            rs = ps.executeQuery();

            if (rs.next() == false) {
                System.out.println("No existen resultados en la busqueda!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en Busqueda!! " + ex.getMessage());
        }
    }

    public void limpiarTabla() {
        int a = modelo.getRowCount();

        System.out.println(modelo.getColumnCount());
        for (int i = 0; i < a; i++) {
            modelo.removeRow(0);
        }
        System.err.println("Limpio la tabla");
    }

    private DefaultTableModel tb_detalle_compra = new DefaultTableModel();

    public DefaultTableModel getTb_detalle_compra() {
        return tb_detalle_compra;
    }

    public void setTb_detalle_compra(DefaultTableModel tb_detalle_compra) {
        this.tb_detalle_compra = tb_detalle_compra;
    }

    public void columnasTablaDetalleCompra() {
        tb_detalle_compra.addColumn("ID");
        tb_detalle_compra.addColumn("Nombre");
        tb_detalle_compra.addColumn("Precio");
        tb_detalle_compra.addColumn("Cantidad");
    }

    
    
    public void agregarProductosaDetalle(int id_produc, String nombre, String precio, String cantidad) {
        String[] dato = new String[4];

        try {
            
            dato[0] = String.valueOf(id_produc);
            dato[1] = nombre;
            double precios = Double.parseDouble(precio);
            int canti = Integer.parseInt(cantidad);
            Double subtotal = precios * canti;
            dato[2] = String.valueOf(subtotal);
            dato[3] = cantidad;

            tb_detalle_compra.addRow(dato);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al agregar productos");
        }
    }
    
    public void limpiarTablaDetalle() {
        int a = tb_detalle_compra.getRowCount();

        System.out.println(tb_detalle_compra.getColumnCount());
        for (int i = 0; i < a; i++) {
            tb_detalle_compra.removeRow(0);
        }
        System.err.println("Limpio la tabla");
    }
    
    public void consultaCompra() {
        try {
            conexion = getConexion();
            ps = conexion.prepareStatement("Select * from productos");
            rs = ps.executeQuery();
            rs.next();
        }catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en consulta " + ex.getMessage());
        }
        
    }

    public void insertarDetalle(int id_sucursal, int id_usuario, int producto_id, int cantidad, double precio, double iva, double total) {
        try {
            conexion = null;
            conexion = getConexion();

            ps = conexion.prepareStatement("insert into compras (id_sucursal, usuarioid, producto_id, cantidad, precio, iva, total) values (?,?,?,?,?,?,?)");
            ps.setInt(1, id_sucursal);
            ps.setInt(2, id_usuario);
            ps.setInt(3, producto_id);
            ps.setInt(4, cantidad);
            ps.setDouble(5, precio);
            ps.setDouble(6, iva);
            ps.setDouble(7, total);
            
            int res = ps.executeUpdate();
            
            consultaCompra();
            
            if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Compra realizada Exitosamente!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al realizar compra");
                }
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en consulta " + ex.getMessage());
        }
    }
}
