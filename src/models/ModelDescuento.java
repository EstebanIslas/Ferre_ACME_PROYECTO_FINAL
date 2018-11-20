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
public class ModelDescuento extends Conexion {

    private Connection conexion;
    private ResultSet rs;
    private PreparedStatement ps;

    private int descuentoid;
    private String nombre;
    private double porcentaje;
    private String tipo;

    private String descicion = "";
    private DefaultTableModel modelo = new DefaultTableModel();

    public int getDescuentoid() {
        return descuentoid;
    }

    public void setDescuentoid(int descuentoid) {
        this.descuentoid = descuentoid;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPorcentaje() {
        return porcentaje;
    }

    public void setPorcentaje(double porcentaje) {
        this.porcentaje = porcentaje;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    public void consultaDescuentos() {
        try {
            conexion = getConexion();
            ps = conexion.prepareStatement("Select * from descuentos");
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
            descuentoid = rs.getInt("descuentoid");
            nombre = rs.getString("nombre");
            porcentaje = rs.getDouble("porcentaje");
            tipo = rs.getString("tipo");

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
    public void guardarRegristro(int descuentoid, String nombre, double porcentaje, String tipo) {
        if (this.getDescicion() == "nuevo") {
            try {
                conexion = null;
                conexion = getConexion();
                ps = conexion.prepareStatement("Insert into descuentos (nombre, porcentaje, tipo) values (?,?,?)");                
                ps.setString(1, nombre);
                ps.setDouble(2, porcentaje);
                ps.setString(3, tipo);
                
                int res = ps.executeUpdate();
                
                consultaDescuentos();
                
                if(res > 0){
                    JOptionPane.showMessageDialog(null, "Registro guardado Exitosamente!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al guardar registro");
                }
                
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Model-Save_New 006" + ex.getMessage());
            }
        }
        else if (this.getDescicion() == "editar") {
            try {
                conexion = null;
                conexion = getConexion();
                ps = conexion.prepareStatement("UPDATE descuentos SET nombre = ?, porcentaje=?, tipo=? WHERE descuentoid=?;");
                ps.setString(1, nombre);
                ps.setDouble(2, porcentaje);
                ps.setString(3, tipo);
                ps.setInt(4, descuentoid);
                
                int res = ps.executeUpdate();
                
                consultaDescuentos();
                
                if (res > 0) {
                    JOptionPane.showMessageDialog(null, "Registro actualizado Exitosamente!!");
                } else {
                    JOptionPane.showMessageDialog(null, "Error al editar registro");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, "Error Model-Update_Doc 006" + ex.getMessage());
            }
        }
    }
    public void borrarRegistro(int descuentoid){
        int resp = JOptionPane.showConfirmDialog(null, "¿Esta seguro de eliminar este dato?", "Alerta!", JOptionPane.YES_NO_OPTION);
        if (resp == JOptionPane.YES_OPTION) {
            try {
                //System.out.println("Elimina un registro");
                conexion = null;
                conexion = getConexion();
                ps = conexion.prepareStatement("DELETE FROM descuentos WHERE descuentoid=?");
                ps.setInt(1, descuentoid);

                int res = ps.executeUpdate();
                consultaDescuentos();

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
        modelo.addColumn("Nombre");
        modelo.addColumn("Porcentaje");
        modelo.addColumn("Tipo");
    }
    
    public void agregaraTabla() {
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

            ps = conexion.prepareStatement("SELECT * FROM descuentos where nombre like '%" + buscar + "%' Or tipo like '%" + buscar + "%';");
            rs = ps.executeQuery();

            //System.out.println("Consulta");
            if (rs.next() == false) {
                System.out.println("No existen resultados en la busqueda!!");
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error en Busqueda!! " + ex.getMessage());
        }
    }

    
}
