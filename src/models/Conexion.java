package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author EstebanIslas
 */
public class Conexion {
    private final String base = "acme_db";
    private final String user = "root";
    private final String password = "";
    private final String url = "jdbc:mysql://localhost:3306/" + base;
    private Connection conexion;
    
    public Connection getConexion(){
        try {
            
            Class.forName("com.mysql.jdbc.Driver");
            conexion = DriverManager.getConnection(this.url, this.user, this.password);
            
        } catch (ClassNotFoundException ex ) {
            
            JOptionPane.showMessageDialog(null,"error en clase driver conexion" + ex.getMessage());
            
        } catch (SQLException ex) {
            
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
        }
        return conexion;
    }
}
