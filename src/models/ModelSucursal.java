/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class ModelSucursal extends Conexion {

    private Connection conexion;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;

    private int sucursal_id;
    private String calle;
    private String numero;
    private String colonia;
    private String codigo_postal;
    private String email;
    private String telefono;
    private String ciudad;
    private String estado;

    //////////////////////////////////
    private String descicion = "";
    private DefaultTableModel modelo = new DefaultTableModel();
    //////////////////////////////////

    public int getSucursal_id() {
        return sucursal_id;
    }

    public void setSucursal_id(int sucursal_id) {
        this.sucursal_id = sucursal_id;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
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

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
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

    /*public Connection conectarDB() {
        try {
            conexion = DriverManager.getConnection("jdbc:mysql://localhost:3306/acme_db", "root", "");
            st = conexion.createStatement();
            rs = st.executeQuery("SELECT * FROM sucursal");
            rs.next();

            setValues();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Model_Sucursal 001: " + ex.getMessage());
        }
        return conexion;
    }*/
    public void setValues() {
        try {
            sucursal_id = rs.getInt("sucursal_id");
            calle = rs.getString("calle");
            numero = rs.getString("numero");
            colonia = rs.getString("colonia");
            codigo_postal = rs.getString("codigo_postal");
            email = rs.getString("email");
            telefono = rs.getString("telefono");
            ciudad = rs.getString("ciudad");
            estado = rs.getString("estado");

        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Model_Sucursal 002: " + ex.getMessage());
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al primer registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la variable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverPrimerRegistro() {
        System.out.println("moverPrimerRegistro");
        try {
            if (rs.isFirst() == false) {
                rs.first();
                setValues();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Model 003: " + ex.getMessage());
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al siguiente
     * registro 2.- obtener el valor del nombre de rs y guardarlo en la variable
     * nombre 3.- obtener el valor del email de rs y guardarlo en la variable
     * email
     */
    public void moverSiguienteRegistro() {
        System.out.println("moverSiguienteRegistro");
        try {
            if (rs.isLast() == false) {
                rs.next();
                setValues();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Model 004" + ex.getMessage());
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al anterior
     * registro 2.- obtener el valor del nombre de rs y guardarlo en la variable
     * nombre 3.- obtener el valor del email de rs y guardarlo en la variable
     * email
     */
    public void moverAnteriorRegistro() {
        System.out.println("moverAnteriorRegistro");
        try {
            if (rs.isFirst() == false) {
                rs.previous();
                setValues();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Model 005" + ex.getMessage());
        }
    }

    /**
     * Método que realiza las siguiente acciones: 1.- Moverse al ultimo registro
     * 2.- obtener el valor del nombre de rs y guardarlo en la ariable nombre
     * 3.- obtener el valor del email de rs y guardarlo en la variable email
     */
    public void moverUltimoRegistro() {
        System.out.println("moverUltimoRegistro");
        try {
            if (rs.isLast() == false) {
                rs.last();
                setValues();
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Model 006" + ex.getMessage());
        }

    }

    public void consultaSucursal() {
        try {
            conexion = getConexion();
            ps = conexion.prepareStatement("Select * from sucursal");
            rs = ps.executeQuery();
            rs.next();

            setValues();
            System.out.println("Consulta realizada!!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Model Sucursal Consulta 007 " + ex.getMessage());
        }
    }

    /**
     * Método que guarda un nuevo o actualiza registro a la Base de Datos.
     *
     * @param calle guarda en la variable el valor que se encuentre en el
     * jtf_calle
     * @param numero guarda en la variable el valor que se encuentre en el
     * jtf_numero
     * @param colonia guarda en la variable el valor que se encuentre en el
     * jtf_colonia
     * @param codigo_postal guarda en la variable el valor que se encuentre en
     * el jtf_codigo
     * @param email guarda en la variable el valor que se encuentre en el
     * jtf_email
     * @param telefono guarda en la variable el valor que se encuentre en el
     * jtf_telefono
     * @param ciudad guarda en la variable el valor que se encuentre en el
     * jtf_ciudad
     * @param estado guarda en la variable el valor que se encuentre en el
     * jtf_estado
     * @param sucursal_id Esta variable se utiliza para la actualizacion de
     * datos
     */
    public void guardarRegistro(String calle, String numero, String colonia, String codigo_postal, String email, String telefono, String ciudad, String estado, int sucursal_id) {
        if (this.getDescicion() == "nuevo") {
            try {
                System.out.println("Insertar nuevo registro");
                conexion = null;
                conexion = getConexion();
                ps = conexion.prepareStatement("insert into sucursal (calle, numero, colonia, codigo_postal, email, telefono, ciudad, estado) values (?,?,?,?,?,?,?,?)");
                ps.setString(1, calle);
                ps.setString(2, numero);
                ps.setString(3, colonia);
                ps.setString(4, codigo_postal);
                ps.setString(5, email);
                ps.setString(6, telefono);
                ps.setString(7, ciudad);
                ps.setString(8, estado);
                int res = ps.executeUpdate();
                consultaSucursal();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Registro guardado Exitosamente!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar registro");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Model 006" + ex.getMessage());
            }
        } else if (this.getDescicion() == "editar") {
            try {
                System.out.println("Actualizar registro");
                conexion = null;
                conexion = getConexion();
                //"UPDATE contactos SET nombre=?,email=? WHERE id_contactos=?");
                ps = conexion.prepareStatement("Update sucursal set calle=?, numero=?, colonia=?, codigo_postal=?, email=?, telefono=?, ciudad=?, estado=? Where sucursal_id=?");
                ps.setString(1, calle);
                ps.setString(2, numero);
                ps.setString(3, colonia);
                ps.setString(4, codigo_postal);
                ps.setString(5, email);
                ps.setString(6, telefono);
                ps.setString(7, ciudad);
                ps.setString(8, estado);
                ps.setInt(9, sucursal_id);
                int res = ps.executeUpdate();
                consultaSucursal();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Registro actualizado Exitosamente!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar registro");
                }

            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Model 006" + ex.getMessage());
            }
        }
    }

    /**
     * Método que borra un registro de la base de datos Contiene un cuadro de
     * confirmacion para realizar la ejecucion.
     *
     * @param sucursal_id Conocer cual es el dato que se desea eliminar
     */
    public void borrarRegistro(int sucursal_id) {
        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este dato?", "Alerta!", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            try {
                System.out.println("Elimina un registro");
                conexion = null;
                conexion = getConexion();
                ps = conexion.prepareStatement("Delete from sucursal where sucursal_id=?");
                ps.setInt(1, sucursal_id);
                int res = ps.executeUpdate();
                consultaSucursal();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Registro eliminado Exitosamente!!");

                } else {
                    JOptionPane.showMessageDialog(null, "Error al eliminar registro");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Model 007" + ex.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(null, "Accion Cancelada!!");
        }
    }

    public void tituloTabla() {
        modelo.addColumn("Identificador");
        modelo.addColumn("Calle");
        modelo.addColumn("Numero");
        modelo.addColumn("Colonia");
        modelo.addColumn("Codigo Postal");
        modelo.addColumn("Email");
        modelo.addColumn("Telefono");
        modelo.addColumn("Ciudad");
        modelo.addColumn("Estado");
    }

    public void insertarDatosTabla() {
        try {
            String[] datos = new String[9];
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

    public void buscarTabla(String buscar) {
        try {
            conexion = null;
            conexion = getConexion();

            ps = conexion.prepareStatement("Select * from sucursal where calle like '%" + buscar + "%' Or colonia like '%" + buscar + "%';");
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
