package logica;

import datos.Rol;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class FunRol {
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.getConnection();
    private String sql = "";

    // Devuelve un arreglo de todos los roles del sistema
    public ArrayList<Rol> mostrar_roles(){
        ArrayList<Rol> lista = new ArrayList<Rol>();
        Rol rol;
        
        sql = "select * from rol order by idrol";
        try{
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()){
                rol = new Rol();
                rol.setId_rol(rs.getInt("idrol"));
                rol.setNombre(rs.getString("nombre"));
                lista.add(rol);
            }
            cn.close();
            st.close();
            rs.close();
            cn = null;
        }catch(Exception e){
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
        return lista;
    };

    
}
