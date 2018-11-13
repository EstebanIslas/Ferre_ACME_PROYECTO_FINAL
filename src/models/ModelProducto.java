package models;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
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
public class ModelProducto extends Conexion {

    private Connection conexion;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;

    private int producto_id;
    private String nombre;
    private String tipo;
    private String marca;
    private double precio_venta;
    private String unidad_medida;

    //////////////////////////////////
    private String descicion = "";
    private DefaultTableModel modelo = new DefaultTableModel();
    //////////////////////////////////

    public int getProducto_id() {
        return producto_id;
    }

    public void setProducto_id(int producto_id) {
        this.producto_id = producto_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public double getPrecio_venta() {
        return precio_venta;
    }

    public void setPrecio_venta(double precio_venta) {
        this.precio_venta = precio_venta;
    }

    public String getUnidad_medida() {
        return unidad_medida;
    }

    public void setUnidad_medida(String unidad_medida) {
        this.unidad_medida = unidad_medida;
    }

    public String getDescicion() {
        return descicion;
    }

    public void setDescicion(String descicion) {
        this.descicion = descicion;
    }

    public DefaultTableModel getModelo() {
        return modelo;
    }

    public void setModelo(DefaultTableModel modelo) {
        this.modelo = modelo;
    }

    public void consultaProductos() {
        try {
            conexion = getConexion();
            ps = conexion.prepareStatement("Select * from productos");
            rs = ps.executeQuery();
            rs.next();

            setValues();
            //System.out.println("Consulta realizada!!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Model-Connection 000 " + ex.getMessage());
        }
    }

    public void setValues() {
        try {
            producto_id = rs.getInt("productoid");
            nombre = rs.getString("nombre");
            tipo = rs.getString("tipo");
            marca = rs.getString("marca");
            precio_venta = rs.getDouble("precio_venta");
            unidad_medida = rs.getString("unidad_medida");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Model-setValues 001 " + ex.getMessage());
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al primer registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverPrimerRegistro() {
        //System.out.println("moverPrimerRegistro");
        try {
            if (rs.isFirst() == false) {
                rs.first();
                setValues();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Model 002: " + ex.getMessage());
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al siguiente
     * registro 2.- obtener el valor del nombre de rs y guardarlo en la variable
     * nombre 3.- obtener el valor del email de rs y guardarlo en la variable
     * email
     */
    public void moverSiguienteRegistro() {
        //System.out.println("moverSiguienteRegistro");
        try {
            if (rs.isLast() == false) {
                rs.next();
                setValues();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Model 003" + ex.getMessage());
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al anterior
     * registro 2.- obtener el valor del nombre de rs y guardarlo en la variable
     * nombre 3.- obtener el valor del email de rs y guardarlo en la variable
     * email
     */
    public void moverAnteriorRegistro() {
        //System.out.println("moverAnteriorRegistro");
        try {
            if (rs.isFirst() == false) {
                rs.previous();
                setValues();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Model 004" + ex.getMessage());
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al ultimo registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la ariable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverUltimoRegistro() {
        //System.out.println("moverUltimoRegistro");
        try {
            if (rs.isLast() == false) {
                rs.last();
                setValues();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Model 005" + ex.getMessage());
        }

    }

    /**
     * Método que guarda un nuevo o actualiza registro a la Base de Datos.
     *
     * @param producto_id guarda en la variable el valor que se almacena en una
     * caja de texto
     * @param nombre guarda en la variable el valor que se almacena en una caja
     * de texto
     * @param tipo guarda en la variable el valor que se almacena en una caja de
     * texto
     * @param marca guarda en la variable el valor que se almacena en una caja
     * de texto
     * @param precio_venta guarda en la variable el valor que se almacena en una
     * caja de texto
     * @param unidad_medida guarda en la variable el valor que se almacena en
     * una caja de texto
     */

    public void guardarRegistro(int producto_id, String nombre, String tipo, String marca, double precio_venta, String unidad_medida) {
        if (this.getDescicion() == "nuevo") {
            try {
                //System.out.println("Insertar nuevo registro");
                conexion = null;
                conexion = getConexion();
                ps = conexion.prepareStatement("INSERT INTO productos (nombre, tipo, marca, precio_venta, unidad_medida) values (?,?,?,?,?)");
                ps.setString(1, nombre);
                ps.setString(2, tipo);
                ps.setString(3, marca);
                ps.setDouble(4, precio_venta);
                ps.setString(5, unidad_medida);

                int res = ps.executeUpdate();

                consultaProductos();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Registro guardado Exitosamente!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar registro");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Model-Save_New 006" + ex.getMessage());
            }
        } else if (this.getDescicion() == "editar") {
            try {
                //System.out.println("Actualizar registro");
                conexion = null;
                conexion = getConexion();
                //"UPDATE contactos SET nombre=?,email=? WHERE id_contactos=?");
                ps = conexion.prepareStatement("UPDATE productos SET nombre=?, tipo=?, marca=?, precio_venta=?, unidad_medida=? WHERE productoid=?");
                ps.setString(1, nombre);
                ps.setString(2, tipo);
                ps.setString(3, marca);
                ps.setDouble(4, precio_venta);
                ps.setString(5, unidad_medida);
                ps.setInt(6, producto_id);

                int res = ps.executeUpdate();
                consultaProductos();

                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Registro actualizado Exitosamente!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar registro");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Model-Save_Update 006" + ex.getMessage());
            }
        }
    }

    public void borrarRegistro(int producto_id) {
        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este dato?", "Alerta!", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            try {
                //System.out.println("Elimina un registro");
                conexion = null;
                conexion = getConexion();
                ps = conexion.prepareStatement("DELETE FROM productos WHERE productoid=?");
                ps.setInt(1, producto_id);

                int res = ps.executeUpdate();
                consultaProductos();

                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado Exitosamente!!");

                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar registro");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Model-Eliminate 007" + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Accion Cancelada!!");
        }
    }

    public void columnasTabla() {
        modelo.addColumn("Identificador");
        modelo.addColumn("Nombre");
        modelo.addColumn("Tipo");
        modelo.addColumn("Marca");
        modelo.addColumn("Precio Venta");
        modelo.addColumn("UN-MED");
    }

    public void agregaraTabla() {
        try {
            String[] datos = new String[6];
            rs.first();
            do{
            datos[0] = rs.getString(1);
            datos[1] = rs.getString(2);
            datos[2] = rs.getString(3);
            datos[3] = rs.getString(4);
            datos[4] = rs.getString(5);
            datos[5] = rs.getString(6);
            
            modelo.addRow(datos);
            }while (rs.next());

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en Tabla!! " + ex.getMessage());
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error en Tabla!! " + ex.getMessage());
        }
    }

    public void limpiarTabla() {
        int a = modelo.getRowCount();
        for (int i = 0; i < a; i++) {
            modelo.removeRow(0);
        }
        System.err.println("Limpio la tabla");
    }

    /**
     * Método que realiza una consulta de busqueda para el usuario
     *
     * @param buscar En esta parametro se almacena el texto que el usuario va a
     * buscar en la caja de texto
     */
    public void buscarTabla(String buscar) {
        try {
            conexion = null;
            conexion = getConexion();

            ps = conexion.prepareStatement("SELECT * FROM productos where nombre like '%" + buscar + "%' Or productoid like '%" + buscar + "%';");
            rs = ps.executeQuery();

            //System.out.println("Consulta");
            if (rs.next() == false) {
                JOptionPane.showMessageDialog(null, "No existen resultados en la busqueda!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en Busqueda!! " + ex.getMessage());
        }
    }
}
