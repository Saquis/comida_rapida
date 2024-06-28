/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datos.Comidas;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author USER1
 */
public class FunComidas {
    private final Conexion mysql;
    private final Connection cn = (Conexion.getConnection());
    private String sql = "";
    public Integer totalRegistros;

    public FunComidas() {
        this.mysql = new Conexion();
    }
    
    //implementar un metodo de tippo DefaultTableModel
    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
        String[] titulo = {"Id plato", "Nombre", "Descripción", "Observación", "Valor", "Stock", "Estado"};
        String[] registros = new String[7];
        totalRegistros = 0;
        modelo = new DefaultTableModel(null, titulo);

        sql = "SELECT * FROM plato WHERE nombre LIKE '%" + buscar + "%' ORDER BY idplato ASC";

        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);

            while (rs.next()) {
                registros[0] = rs.getString("idplato");
                registros[1] = rs.getString("nombre");
                registros[2] = rs.getString("descripcion");
                registros[3] = rs.getString("observacion");
                registros[4] = rs.getString("valor");
                registros[5] = rs.getString("stock");
                registros[6] = rs.getString("estado");
                totalRegistros = totalRegistros +1;
                modelo.addRow(registros);
            }
            return modelo;
        } 
        catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return null;
        }
    }

    public boolean insertar(Comidas dts) {
        sql = "INSERT INTO plato (nombre, descripcion, observacion, valor, stock, estado) VALUES (?, ?, ?, ?, ?, ?)";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, dts.getObservacion());
            pst.setDouble(4, dts.getValor());
            pst.setInt(5, dts.getStock());
            pst.setString(6, dts.getEstado());

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean editar(Comidas dts) {
        sql = "UPDATE plato SET nombre = ?, descripcion = ?, observacion = ?, valor = ?, stock = ?, estado = ? WHERE idplato = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setString(1, dts.getNombre());
            pst.setString(2, dts.getDescripcion());
            pst.setString(3, dts.getObservacion());
            pst.setDouble(4, dts.getValor());
            pst.setInt(5, dts.getStock());
            pst.setString(6, dts.getEstado());
            pst.setInt(7, dts.getIdplato());

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean eliminar(Comidas dts) {
        sql = "DELETE FROM plato WHERE idplato = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, dts.getIdplato());

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean desactivar(Comidas dts) {
        sql = "UPDATE plato SET estado = 0 WHERE idplato = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, dts.getIdplato());

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }

    public boolean activar(Comidas dts) {
        sql = "UPDATE plato SET estado = 1 WHERE idplato = ?";
        try {
            PreparedStatement pst = cn.prepareStatement(sql);
            pst.setInt(1, dts.getIdplato());

            int n = pst.executeUpdate();
            return n != 0;
        } catch (Exception e) {
            JOptionPane.showConfirmDialog(null, e);
            return false;
        }
    }
}