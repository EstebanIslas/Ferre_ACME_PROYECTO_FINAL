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
public class ModelUsuario extends Conexion {

    private Connection conexion;
    private Statement st;
    private ResultSet rs;
    private PreparedStatement ps;

    private int usuario_id;
    private int sucursal_id;
    private String tipo;
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
    private String numero_seguro;
    private String curp;
    private String password;

    private String descicion = "";
    private DefaultTableModel modelo = new DefaultTableModel();

    public int getUsuario_id() {
        return usuario_id;
    }

    public void setUsuario_id(int usuario_id) {
        this.usuario_id = usuario_id;
    }

    public int getSucursal_id() {
        return sucursal_id;
    }

    public void setSucursal_id(int sucursal_id) {
        this.sucursal_id = sucursal_id;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public String getNumero_seguro() {
        return numero_seguro;
    }

    public void setNumero_seguro(String numero_seguro) {
        this.numero_seguro = numero_seguro;
    }

    public String getCurp() {
        return curp;
    }

    public void setCurp(String curp) {
        this.curp = curp;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public void consultaUsuario() {
        try {
            conexion = getConexion();
            ps = conexion.prepareStatement("Select * from usuario");
            rs = ps.executeQuery();
            rs.next();

            setValues();
            //System.out.println("Consulta realizada!!");
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Model-Connection 000 " + ex.getMessage());
        }
    }
    
    public void llenarComboBoxSucursales(javax.swing.JComboBox idsucursal){
        try{
            conexion = getConexion();
            ps = conexion.prepareStatement("Select sucursal_id from sucursal");
            rs = ps.executeQuery();
            rs.next();
            
            idsucursal.removeAllItems();
            
            do{
                idsucursal.addItem("" + rs.getString("sucursal_id"));
            }while(rs.next());
            
            idsucursal.setSelectedIndex(0); //Regresar al primer item que se registro
            
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error Model-Connection 000 " + ex.getMessage());
        }
    }

    public void setValues() {
        try {
            usuario_id = rs.getInt("usuario_id");
            sucursal_id = rs.getInt("sucursal_id");
            tipo = rs.getString("tipo");
            nombre = rs.getString("nombre");
            apellido_paterno = rs.getString("apellido_paterno");
            apellido_materno = rs.getString("apellido_materno");
            telefono = rs.getString("telefono");
            rfc = rs.getString("rfc");
            calle = rs.getString("calle");
            colonia = rs.getString("colonia");
            num_interior = rs.getString("num_interior");
            num_exterior = rs.getString("num_exterior");
            codigo_postal = rs.getString("codigo_postal");
            email = rs.getString("email");
            ciudad = rs.getString("ciudad");
            estado = rs.getString("estado");
            numero_seguro = rs.getString("numero_seguro");
            curp = rs.getString("curp");
            password = rs.getString("password");

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

    public void guardarRegistro(int usuario_id, int sucursal_id, String tipo, String nombre, String ape_pat, String ape_mat, String telefono, String rfc, String calle, String colonia, String num_inte, String num_exte, String codigo_post, String email, String ciudad, String estado, String num_seguro, String curp, String password) {
        if (this.getDescicion() == "nuevo") {
            try {

                conexion = null;
                conexion = getConexion();
                ps = conexion.prepareStatement("Insert into usuario (sucursal_id, tipo, nombre, apellido_paterno, apellido_materno, telefono, rfc, calle, colonia, num_interior, num_exterior, codigo_postal, email, ciudad, estado, numero_seguro, curp, password) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?);");

                ps.setInt(1, sucursal_id);
                ps.setString(2, tipo);
                ps.setString(3, nombre);
                ps.setString(4, ape_pat);
                ps.setString(5, ape_mat);
                ps.setString(6, telefono);
                ps.setString(7, rfc);
                ps.setString(8, calle);
                ps.setString(9, colonia);
                ps.setString(10, num_inte);
                ps.setString(11, num_exte);
                ps.setString(12, codigo_post);
                ps.setString(13, email);
                ps.setString(14, ciudad);
                ps.setString(15, estado);
                ps.setString(16, num_seguro);
                ps.setString(17, curp);
                ps.setString(18, password);

                int res = ps.executeUpdate();

                consultaUsuario();
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
                ps = conexion.prepareStatement("update usuario set sucursal_id=?, tipo=?, nombre=?, apellido_paterno=?, apellido_materno=?, telefono=?, rfc=?, calle=?, colonia=?, num_interior=?, num_exterior=?, codigo_postal=?, email=?, ciudad=?, estado=?, numero_seguro=?, curp=?, password=? where usuario_id=?;");

                ps.setInt(1, sucursal_id);
                ps.setString(2, tipo);
                ps.setString(3, nombre);
                ps.setString(4, ape_pat);
                ps.setString(5, ape_mat);
                ps.setString(6, telefono);
                ps.setString(7, rfc);
                ps.setString(8, calle);
                ps.setString(9, colonia);
                ps.setString(10, num_inte);
                ps.setString(11, num_exte);
                ps.setString(12, codigo_post);
                ps.setString(13, email);
                ps.setString(14, ciudad);
                ps.setString(15, estado);
                ps.setString(16, num_seguro);
                ps.setString(17, curp);
                ps.setString(18, password);

                ps.setInt(19, usuario_id);

                int res = ps.executeUpdate();

                consultaUsuario();
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

    public void borrarRegistro(int usuario_id) {
        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este dato?", "Alerta!", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            try {
                //System.out.println("Elimina un registro");
                conexion = null;
                conexion = getConexion();
                ps = conexion.prepareStatement("DELETE FROM usuario WHERE usuario_id=?");
                ps.setInt(1, usuario_id);

                int res = ps.executeUpdate();
                consultaUsuario();

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
        modelo.addColumn("Id");
        modelo.addColumn("Suc Id");
        modelo.addColumn("Tipo");
        modelo.addColumn("Nombre");
        modelo.addColumn("Paterno");
        modelo.addColumn("Materno");
        modelo.addColumn("Telefono");
        modelo.addColumn("RFC");
        modelo.addColumn("Calle");
        modelo.addColumn("Colonia");
        modelo.addColumn("Num Int");
        modelo.addColumn("Num Ext");
        modelo.addColumn("CodPost");
        modelo.addColumn("Email");
        modelo.addColumn("Ciudad");
        modelo.addColumn("Estado");
        modelo.addColumn("Imss");
        modelo.addColumn("Curp");
        modelo.addColumn("Passwd");
    }

    public void agregaraTabla() {
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
                datos[17] = rs.getString(18);
                datos[18] = rs.getString(19);

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

            ps = conexion.prepareStatement("SELECT * FROM usuario where nombre like '%" + buscar + "%' Or tipo like '%" + buscar + "%';");
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
