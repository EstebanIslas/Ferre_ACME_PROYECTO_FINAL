package models;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author EstebanIslas
 */
public class ModelClientes extends Conexion {

    private Connection conexion;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;

    private int cliente_id;
    private int descuento_id;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String telefono;
    private String rfc;
    private String calle;
    private String colonia;
    private String num_interior;
    private String num_exterior;
    private String codigo_postal;
    private String email;
    private String ciudad;
    private String estado;
    private String fecha_creacion;
    private double total_acumulado;

    private String descicion = "";
    private DefaultTableModel modelo = new DefaultTableModel();

    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getDescuento_id() {
        return descuento_id;
    }

    public void setDescuento_id(int descuento_id) {
        this.descuento_id = descuento_id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido_paterno() {
        return apellido_paterno;
    }

    public void setApellido_paterno(String apellido_paterno) {
        this.apellido_paterno = apellido_paterno;
    }

    public String getApellido_materno() {
        return apellido_materno;
    }

    public void setApellido_materno(String apellido_materno) {
        this.apellido_materno = apellido_materno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getNum_interior() {
        return num_interior;
    }

    public void setNum_interior(String num_interior) {
        this.num_interior = num_interior;
    }

    public String getNum_exterior() {
        return num_exterior;
    }

    public void setNum_exterior(String num_exterior) {
        this.num_exterior = num_exterior;
    }

    public String getCodigo_postal() {
        return codigo_postal;
    }

    public void setCodigo_postal(String codigo_postal) {
        this.codigo_postal = codigo_postal;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha_creacion() {
        return fecha_creacion;
    }

    public void setFecha_creacion(String fecha_creacion) {
        this.fecha_creacion = fecha_creacion;
    }

    public double getTotal_acumulado() {
        return total_acumulado;
    }

    public void setTotal_acumulado(double total_acumulado) {
        this.total_acumulado = total_acumulado;
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

    public void consultaCliente() {
        try {
            conexion = getConexion();
            ps = conexion.prepareStatement("Select * from clientes");
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
            cliente_id = rs.getInt("clienteid");
            descuento_id = rs.getInt("descuentoid");
            nombre = rs.getString("nombre");
            apellido_paterno = rs.getString("apellido_paterno");
            apellido_materno = rs.getString("apellido_materno");
            telefono = rs.getString("telefono");
            rfc = rs.getString("rfc");
            calle = rs.getString("calle");
            colonia = rs.getString("colonia");
            num_interior = rs.getString("numero_interior");
            num_exterior = rs.getString("numero_exterior");
            codigo_postal = rs.getString("codigo_postal");
            email = rs.getString("email");
            ciudad = rs.getString("ciudad");
            estado = rs.getString("estado");
            fecha_creacion = rs.getString("fecha_creacion");
            total_acumulado = rs.getDouble("total_acumulado");

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

    public void guardarRegistro(int clienteid, int descuentoid, String nombre, String ape_pat, String ape_mat, String telefono, String rfc, String calle, String colonia, String num_inte, String num_exte, String codigo_post, String email, String ciudad, String estado, String fecha, Double total_acumu) {
        if (this.getDescicion() == "nuevo") {
            try {

                conexion = null;
                conexion = getConexion();
                ps = conexion.prepareStatement("Insert into clientes (descuentoid, nombre, apellido_paterno, apellido_materno, telefono, rfc, calle, colonia, numero_interior, numero_exterior, codigo_postal, email, ciudad, estado, fecha_creacion, total_acumulado) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

                ps.setInt(1, descuentoid);
                ps.setString(2, nombre);
                ps.setString(3, ape_pat);
                ps.setString(4, ape_mat);
                ps.setString(5, telefono);
                ps.setString(6, rfc);
                ps.setString(7, calle);
                ps.setString(8, colonia);
                ps.setString(9, num_inte);
                ps.setString(10, num_exte);
                ps.setString(11, codigo_post);
                ps.setString(12, email);
                ps.setString(13, ciudad);
                ps.setString(14, estado);
                ps.setString(15, fecha);
                ps.setDouble(16, total_acumu);

                int res = ps.executeUpdate();

                consultaCliente();
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

                conexion = null;
                conexion = getConexion();
                ps = conexion.prepareStatement("Update clientes set descuentoid=?, nombre=?, apellido_paterno=?, apellido_materno=?, telefono=?, rfc=?, calle=?, colonia=?, numero_interior=?, numero_exterior=?, codigo_postal=?, email=?, ciudad=?, estado=?, fecha_creacion=?, total_acumulado=? Where clienteid=?");

                ps.setInt(1, descuentoid);
                ps.setString(2, nombre);
                ps.setString(3, ape_pat);
                ps.setString(4, ape_mat);
                ps.setString(5, telefono);
                ps.setString(6, rfc);
                ps.setString(7, calle);
                ps.setString(8, colonia);
                ps.setString(9, num_inte);
                ps.setString(10, num_exte);
                ps.setString(11, codigo_post);
                ps.setString(12, email);
                ps.setString(13, ciudad);
                ps.setString(14, estado);
                ps.setString(15, fecha);
                ps.setDouble(16, total_acumu);
                ps.setInt(17, clienteid);

                int res = ps.executeUpdate();

                consultaCliente();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Registro actualizado Exitosamente!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al actualizar registro");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Model-Save_New 006" + ex.getMessage());
            }
        }
    }
    
    public void borrarRegistro(int clienteid) {
        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este dato?", "Alerta!", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            try {
                //System.out.println("Elimina un registro");
                conexion = null;
                conexion = getConexion();
                ps = conexion.prepareStatement("DELETE FROM cliente WHERE clienteid=?");
                ps.setInt(1, clienteid);

                int res = ps.executeUpdate();
                consultaCliente();

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
    
    public void columnasTabla(){
        modelo.addColumn("ID");
        modelo.addColumn("DescuentoID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Paterno");
        modelo.addColumn("Materno");
        modelo.addColumn("Telefono");
        modelo.addColumn("RFC    ");
        modelo.addColumn("Calle");
        modelo.addColumn("Colonia");
        modelo.addColumn("Num Int");
        modelo.addColumn("Num Ext");
        modelo.addColumn("CodPost");
        modelo.addColumn("Email");
        modelo.addColumn("Ciudad");
        modelo.addColumn("Estado");
        modelo.addColumn("Fecha");
        modelo.addColumn("Total Acum");
    }
    
    public void agregaraTabla(){
        try {
            String[] datos = new String[19];
            rs.first();

            do {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getString(6);
                datos[6] = rs.getString(7);
                datos[7] = rs.getString(8);
                datos[8] = rs.getString(9);
                datos[9] = rs.getString(10);
                datos[10] = rs.getString(11);
                datos[11] = rs.getString(12);
                datos[12] = rs.getString(13);
                datos[13] = rs.getString(14);
                datos[14] = rs.getString(15);
                datos[15] = rs.getString(16);
                datos[16] = rs.getString(17);

                modelo.addRow(datos);
            } while (rs.next());

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

            ps = conexion.prepareStatement("SELECT * FROM cliente where nombre like '%" + buscar + "%' Or rfc like '%" + buscar + "%';");
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
