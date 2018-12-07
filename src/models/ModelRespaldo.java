package models;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JOptionPane;

/**
 *
 * @author USUARIO
 */
public class ModelRespaldo {
    public void respaldosDB() {
        try {
            Process p = Runtime.getRuntime().exec("C:\\xampp\\mysql\\bin\\mysqldump --host=estebanisla.ddns.net "
                    + "-udebugware -pt3relemento -B acme_db");
            
            InputStream is = p.getInputStream();
            FileOutputStream fos = new FileOutputStream("src/bd/backup_acme_db.sql"); 
            byte[] buffer = new byte[1000]; 
            
            int leido = is.read(buffer); //Devuelve el número de bytes leídos o -1 si se alcanzó el final del stream.
            System.out.println("Hace Algo");
            while (leido > 0) {
                fos.write(buffer, 0, leido);//Desplazamiento de partida para empezar a escribir caracteres en el archivo
                leido = is.read(buffer);
            }
            
            fos.close();//Cierra respaldo
            
            JOptionPane.showMessageDialog(null, "Respaldo guardado");
            
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Error al realizar el respaldo");
        }
    }
}
