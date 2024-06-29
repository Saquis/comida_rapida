package logica;

import datos.Usuario;
import datos.UsuarioRol;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FunUsuarioRol {
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.getConnection();
    private String sql = "";
    
    //Método para buscar datos de roles de un usuario en la BDD
    public DefaultTableModel mostrar(Usuario usuario) {
        DefaultTableModel modelo;
        String[] titulos = {"ID", "Rol"};
        String[] registros = new String[titulos.length];
        modelo = new DefaultTableModel(null, titulos);
        sql = "select ur.idrol as idrol, ro.nombre as nombre from usuario_rol as ur, rol as ro where ur.idrol = ro.idrol and ur.idusuario = ?";
        
        try{
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, usuario.getId_usuario());
            ResultSet rs = pst.executeQuery();
            while(rs.next()){
                registros[0] = rs.getString("idrol");
                registros[1] = rs.getString("nombre");
                modelo.addRow(registros);
            }
            return modelo;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    //Método para insertar rol de usuario en la BDD
    public boolean insertar(UsuarioRol dts){
        sql = "insert into usuario_rol(idusuario,idrol)"
                + " values(?,?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, dts.getUsuario().getId_usuario());
            pst.setInt(2, dts.getRol().getId_rol());
            int n = pst.executeUpdate(); //1-OK; 0-Error
            if(n != 0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
    
    //Método para insertar rol de usuario en la BDD
    public boolean eliminar(UsuarioRol dts){
        sql = "delete from usuario_rol where idusuario=? and idrol=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, dts.getUsuario().getId_usuario());
            pst.setInt(2, dts.getRol().getId_rol());
            int n = pst.executeUpdate(); //1-OK; 0-Error
            if(n != 0){
                return true;
            }else{
                return false;
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }
}
