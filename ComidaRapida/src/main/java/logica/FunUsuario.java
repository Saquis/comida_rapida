package logica;

import datos.Rol;
import datos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;

public class FunUsuario {
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.getConnection();
    private String sql = "";
    public Integer totalRegistros;
    
    // Insertar nuevo usuario del sistema
    public boolean insertar(Usuario usuario){
        return true;
    }
    
    // Actualizar datos del usuario del sistema
    public boolean actualizar(Usuario usuario){
        return true;
    }
    
    // Activar o desactivar usuario del sistema (V-Vigente, C-Cancelado)
    public boolean actualizar_estado(Usuario usuario){
        return true;
    }
    
    // Valida login y password en la BDD
    public boolean validar_login(Usuario usuario, Rol rol){
        boolean bandera;
        
        sql = "select * from usuario as us, usuario_rol as ur"
                + " where us.idusuario = ur.idusuario"
                + " and us.estado = 'V'"
                + " and us.login = ?"
                + " and us.password = ?"
                + " and ur.idrol = ?";
        try{
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, usuario.getLogin());
            pst.setString(2, usuario.getPassword());
            pst.setInt(3, rol.getId_rol());
            ResultSet rs = pst.executeQuery();
            
            if(rs.next()){
                bandera = true;
            }else{
                bandera = false;
            }
            cn.close();
            pst.close();
            rs.close();
            cn = null;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
        return bandera;
    }
    
}
