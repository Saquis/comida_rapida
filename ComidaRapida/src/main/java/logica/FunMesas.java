package logica;

import datos.MesasDatos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class FunMesas {
    private Conexion mysql = new Conexion();
    private Connection cn = mysql.getConnection();
    private String sSQL = "";

    public DefaultTableModel mostrar() {
        DefaultTableModel modelo;
        String[] titulos = {"ID MESA", "Capacidad", "Estado"};
        String[] registro = new String[3];

        modelo = new DefaultTableModel(null, titulos);
        sSQL = "SELECT * FROM mesa";

        try (Statement st = cn.createStatement();
             ResultSet rs = st.executeQuery(sSQL)) {

            while (rs.next()) {
                registro[0] = rs.getString("idmesa");
                registro[1] = rs.getString("capacidad");
                registro[2] = rs.getString("estado");
                modelo.addRow(registro);
            }
            return modelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar mesas: " + e);
            return null;
        }
    }

    public boolean insertarMesa(MesasDatos mesa) {
        
    sSQL = "INSERT INTO mesa (capacidad, estado) VALUES (?, ?)";
    try (PreparedStatement pst = cn.prepareStatement(sSQL)) {
        pst.setInt(1, mesa.getCapacidad());
        pst.setString(2, mesa.getEstado());
        int n = pst.executeUpdate();
        return n != 0;
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(null, "Error al insertar mesa: " + e);
        return false;
    }
}


    public boolean actualizarEstadoMesa(int idMesa, String nuevoEstado) {
        sSQL = "UPDATE mesa SET estado = ? WHERE idmesa = ?";
        try (PreparedStatement pst = cn.prepareStatement(sSQL)) {
            pst.setString(1, nuevoEstado);
            pst.setInt(2, idMesa);
            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar el estado de la mesa: " + e);
            return false;
        }
    }

    public boolean eliminarMesa(int idMesa) {
        sSQL = "DELETE FROM mesa WHERE idmesa = ?";
        try (PreparedStatement pst = cn.prepareStatement(sSQL)) {
            pst.setInt(1, idMesa);
            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al eliminar mesa: " + e);
            return false;
        }
    }

    public boolean actualizarCapacidadMesa(int idMesa, int nuevaCapacidad) {
        sSQL = "UPDATE mesa SET capacidad = ? WHERE idmesa = ?";
        try (PreparedStatement pst = cn.prepareStatement(sSQL)) {
            pst.setInt(1, nuevaCapacidad);
            pst.setInt(2, idMesa);
            int n = pst.executeUpdate();
            return n != 0;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al actualizar la capacidad de la mesa: " + e);
            return false;
        }
    }
}







