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
public class ModelProveedores extends Conexion {

    private Connection conexion;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;

    private int id_proveedor;
    private String nombre;
    private String apellido_paterno;
    private String apellido_materno;
    private String empresa;
    private String telefono;
    private String calle;
    private int numero;
    private String colonia;
    private String codigo_postal;
    private String email;
    private String ciudad;
    private String estado;

    //////////////////////////////////////
    private String descicion = "";
    private DefaultTableModel modelo = new DefaultTableModel();
    //////////////////////////////////////

    public int getId_proveedor() {
        return id_proveedor;
    }

    public void setId_proveedor(int id_proveedor) {
        this.id_proveedor = id_proveedor;
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

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
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

    public void consultaProveedores() {
        try {
            conexion = getConexion();
            ps = conexion.prepareStatement("Select * from proveedores");
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
            id_proveedor = rs.getInt("id_proveedor");
            nombre = rs.getString("nombre");
            apellido_paterno = rs.getString("apellido_paterno");
            apellido_materno = rs.getString("apellido_materno");
            empresa = rs.getString("empresa");
            telefono = rs.getString("telefono");
            calle = rs.getString("calle");
            numero = rs.getInt("numero");
            colonia = rs.getString("colonia");
            codigo_postal = rs.getString("codigo_postal");
            email = rs.getString("email");
            ciudad = rs.getString("ciudad");
            estado = rs.getString("estado");
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

    public void guardarRegistro(int id_proveedor, String nombre, String apellido_paterno, String apellido_materno, String empresa, String telefono, String calle, int numero, String colonia, String codigo_postal, String email, String ciudad, String estado) {
        if (this.getDescicion() == "nuevo") {
            try {

                conexion = null;
                conexion = getConexion();
                ps = conexion.prepareStatement("Insert into proveedores (nombre,apellido_paterno,apellido_materno,empresa,telefono,calle,numero,colonia,codigo_postal,email,ciudad,estado) values (?,?,?,?,?,?,?,?,?,?,?,?)");

                ps.setString(1, nombre);
                ps.setString(2, apellido_paterno);
                ps.setString(3, apellido_materno);
                ps.setString(4, empresa);
                ps.setString(5, telefono);
                ps.setString(6, calle);
                ps.setInt(7, numero);
                ps.setString(8, colonia);
                ps.setString(9, codigo_postal);
                ps.setString(10, email);
                ps.setString(11, ciudad);
                ps.setString(12, estado);

                int res = ps.executeUpdate();

                consultaProveedores();
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Registro guardado Exitosamente!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar registro");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Model-Save_New 006" + ex.getMessage());
            }
        } else if (getDescicion() == "editar") {
            try {
                conexion = null;
                conexion = getConexion();
                
                ps = conexion.prepareStatement("UPDATE proveedores SET nombre=?,apellido_paterno=?,apellido_materno=?,empresa=?,telefono=?,calle=?, numero=?,colonia=?,codigo_postal=?,email=?,ciudad=?,estado=? WHERE id_proveedor=? ");
                ps.setString(1, nombre);
                ps.setString(2, apellido_paterno);
                ps.setString(3, apellido_materno);
                ps.setString(4, empresa);
                ps.setString(5, telefono);
                ps.setString(6, calle);
                ps.setInt(7, numero);
                ps.setString(8, colonia);
                ps.setString(9, codigo_postal);
                ps.setString(10, email);
                ps.setString(11, ciudad);
                ps.setString(12, estado);
                ps.setInt(13, id_proveedor);

                int res = ps.executeUpdate();
                consultaProveedores();

                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Registro modificado Exitosamente");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al modificarregistro");
                }

            } catch (SQLException err) {
                JOptionPane.showMessageDialog(null, "Error GuardarRegis 001: " + err.getMessage());
            }
        }
    }
    /**
     * Metodo que realiza la consulta para eliminar un registro 
     * @param id_proveedor atrae el texto que contiene el jtf_id_proveedor para eliminar el registro seleccionado
     */
    public void borrarRegistro(int id_proveedor) {
        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este dato?", "Alerta!", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            try {
                //System.out.println("Elimina un registro");
                conexion = null;
                conexion = getConexion();
                ps = conexion.prepareStatement("DELETE FROM proveedores WHERE id_proveedor=?");
                ps.setInt(1, id_proveedor);

                int res = ps.executeUpdate();
                consultaProveedores();

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
        modelo.addColumn("ID");
        modelo.addColumn("Nombre");
        modelo.addColumn("Ape Pat");
        modelo.addColumn("Ape Mat");
        modelo.addColumn("Empresa");
        modelo.addColumn("Telefono");
        modelo.addColumn("Calle");
        modelo.addColumn("No.");
        modelo.addColumn("Colonia");
        modelo.addColumn("Cod Post");
        modelo.addColumn("Email");
        modelo.addColumn("Ciudad");
        modelo.addColumn("Estado");
    }
    
    public void agregaraTabla() {
        try {
            String[] datos = new String[13];
            rs.first();
            do{
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

            ps = conexion.prepareStatement("SELECT * FROM proveedores where nombre like '%" + buscar + "%' Or empresa like '%" + buscar + "%';");
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
