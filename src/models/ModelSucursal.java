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

/**
 *
 * @author EstebanIslas
 */
public class ModelSucursal {
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
    
    ///////////////////////////////
    private String descicion = "";
    ///////////////////////////////

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
    
    public Connection conectarDB(){
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
    }
    
    public void setValues(){
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
}
