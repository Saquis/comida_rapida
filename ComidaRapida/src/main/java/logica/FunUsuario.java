package logica;

import datos.Rol;
import datos.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FunUsuario {
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.getConnection();
    private String sql = "";
    public Integer totalRegistros;
    
    //Método para buscar datos de usuario en la BDD
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
        String[] titulos = {"ID", "Nombres", "Apellidos", "Cédula", "Teléfono", "Email", "Login", "Estado"};
        String[] registros = new String[titulos.length];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulos);
        sql = "select idusuario, nombres, apellidos, cedula,"
                + " telefono, email, login, estado"
                + " from usuario where nombres like '%" + buscar + "%' order by nombres";
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                registros[0] = rs.getString("idusuario");
                registros[1] = rs.getString("nombres");
                registros[2] = rs.getString("apellidos");
                registros[3] = rs.getString("cedula");
                registros[4] = rs.getString("telefono");
                registros[5] = rs.getString("email");
                registros[6] = rs.getString("login");
                registros[7] = rs.getString("estado");
                totalRegistros = totalRegistros + 1;
                modelo.addRow(registros);
            }
            return modelo;
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }
    
    //Método para insertar usuario en la BDD
    public boolean insertar(Usuario dts){
        sql = "insert into usuario(nombres, apellidos, cedula, telefono, email, login, password, estado)"
                + " values(?,?,?,?,?,?,?,'V')";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, dts.getNombres());
            pst.setString(2, dts.getApellidos());
            pst.setString(3, dts.getCedula());
            pst.setString(4, dts.getTelefono());
            pst.setString(5, dts.getEmail());
            pst.setString(6, dts.getLogin());
            pst.setString(7, dts.getPassword());
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

    //Método para actualizar usuario en la BDD
    public boolean actualizar(Usuario dts){
        sql = "update usuario set nombres=?,apellidos=?,cedula=?,telefono=?,email=?,login=?,password=?"
                + " where idusuario=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, dts.getNombres());
            pst.setString(2, dts.getApellidos());
            pst.setString(3, dts.getCedula());
            pst.setString(4, dts.getTelefono());
            pst.setString(5, dts.getEmail());
            pst.setString(6, dts.getLogin());
            pst.setString(7, dts.getPassword());
            pst.setInt(8, dts.getId_usuario());
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

    // Activar o desactivar usuario del sistema (V-Vigente, C-Cancelado)
    public boolean actualizar_estado(Usuario dts, String estado){
        if(!estado.equals("V") && !estado.equals("C")){
            JOptionPane.showMessageDialog(null, "Estado no permitido.");
            return false;
        }
        sql = "update usuario set estado='" + estado + "' where idusuario=?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, dts.getId_usuario());
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
