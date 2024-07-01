/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datos.Comidas;
import datos.MesasDatos;
import datos.PedidosDatos;
import datos.Rol;
import datos.Usuario;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JComboBox;

/**
 *
 * @author CAFRAL
 */
public class FunPedidos {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.getConnection();
    public Integer totalRegistro;
    private String sql = "";
    private String estado;

    ArrayList<Comidas> lista;
    ArrayList<MesasDatos> lista2;
    ArrayList<Rol> lista3;
    ArrayList<Usuario> lista4;
    ArrayList<PedidosDatos> pedidos;

    public FunPedidos() {
        lista = new ArrayList();
        lista2 = new ArrayList();
        lista3 = new ArrayList();
        lista4 = new ArrayList();
        pedidos = new ArrayList<>();
    }

    public void Agregar_Platos(Comidas c) {
        lista.add(c);
    }

    public void AgregarMesas(MesasDatos m) {
        lista2.add(m);
    }

    public void AgregarRoles(Rol r) {
        lista3.add(r);
    }

    public void AgregarIDUsuario(Usuario u) {
        lista4.add(u);
    }

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
        String[] titulos = {"ID PEDIDO", "ID MESA", "FECHA Y HORA", "ESTADO"};
        String[] registro = new String[4];
        totalRegistro = 0;
        modelo = new DefaultTableModel(null, titulos);
        sql = "SELECT p.idpedido AS idpedido,"
                + " pm.idmesa AS idmesa,"
                + " p.fecha_pedido AS fecha_pedido,"
                + " p.estado AS estado"
                + " FROM pedido p"
                + " JOIN pedido_mesa pm ON p.idpedido = pm.idpedido"
                + " WHERE pm.idmesa LIKE '%" + buscar + "%'";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet resultSet = ps.executeQuery();
            while (resultSet.next()) {
                registro[0] = resultSet.getString("idpedido");
                registro[1] = resultSet.getString("idmesa");
                Timestamp fechaPedido = resultSet.getTimestamp("fecha_pedido");
                if (fechaPedido != null) {
                    registro[2] = fechaPedido.toString();
                } else {
                    registro[2] = "N/A";
                }
                registro[3] = resultSet.getString("estado");
                totalRegistro++;
                modelo.addRow(registro);
            }
            return modelo;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error al mostrar la tabla " + e);
            return null;
        }
    }

    public boolean insertarPedido(String cedulaCliente, int cantidad, int idMesa, String nombrePlato, int idUsuario) {
        LocalDateTime fechaActual = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String fechaFormateada = fechaActual.format(formato);

        int idPlato = obtenerIdPlatoPorNombre(nombrePlato);
        if (idPlato == -1) {
            JOptionPane.showMessageDialog(null, "Plato no encontrado");
            return false;
        }

        if (!esMesaLibre(idMesa)) {
            JOptionPane.showMessageDialog(null, "La mesa está ocupada");
            return false;
        }

        try {
            cn.setAutoCommit(false);

            // Insertar el pedido con el idusuario
            sql = "INSERT INTO pedido (fecha_pedido, estado, idusuario) VALUES (?, 1, ?)";
            PreparedStatement psPedido = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psPedido.setString(1, fechaFormateada);
            psPedido.setInt(2, idUsuario);
            int n = psPedido.executeUpdate();

            if (n == 0) {
                cn.rollback();
                JOptionPane.showMessageDialog(null, "Error al insertar el pedido");
                return false;
            }

            ResultSet generatedKeys = psPedido.getGeneratedKeys();
            int idPedido;
            if (generatedKeys.next()) {
                idPedido = generatedKeys.getInt(1);
            } else {
                cn.rollback();
                JOptionPane.showMessageDialog(null, "Error al obtener el ID del pedido");
                return false;
            }

            sql = "INSERT INTO detalle_pedido (idpedido, idplato, cantidad) VALUES (?, ?, ?)";
            PreparedStatement psDetallePedido = cn.prepareStatement(sql);
            psDetallePedido.setInt(1, idPedido);
            psDetallePedido.setInt(2, idPlato);
            psDetallePedido.setInt(3, cantidad);
            psDetallePedido.executeUpdate();

            sql = "INSERT INTO pedido_mesa (idpedido, idmesa) VALUES (?, ?)";
            PreparedStatement psPedidoMesa = cn.prepareStatement(sql);
            psPedidoMesa.setInt(1, idPedido);
            psPedidoMesa.setInt(2, idMesa);
            psPedidoMesa.executeUpdate();

            cn.commit();
            return true;
        } catch (SQLException e) {
            try {
                cn.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
            JOptionPane.showMessageDialog(null, e);
            return false;
        } finally {
            try {
                cn.setAutoCommit(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    public boolean editarPedido(int idPedido, String cedulaCliente, String nombrePlato, int cantidad, int idMesa, String fecha, int estado) {
        int idPlato = obtenerIdPlatoPorNombre(nombrePlato);
        if (idPlato == -1) {
            JOptionPane.showMessageDialog(null, "Plato no encontrado");
            return false;
        }

        try {
            cn.setAutoCommit(false);

            sql = "UPDATE detalle_pedido SET idplato = ?, cantidad = ? WHERE idpedido = ?";
            PreparedStatement psDetallePedido = cn.prepareStatement(sql);
            psDetallePedido.setInt(1, idPlato);
            psDetallePedido.setInt(2, cantidad);
            psDetallePedido.setInt(3, idPedido);
            psDetallePedido.executeUpdate();

            sql = "UPDATE pedido_mesa SET idmesa = ? WHERE idpedido = ?";
            PreparedStatement psPedidoMesa = cn.prepareStatement(sql);
            psPedidoMesa.setInt(1, idMesa);
            psPedidoMesa.setInt(2, idPedido);
            psPedidoMesa.executeUpdate();

            cn.commit();
            return true;
        } catch (SQLException e) {
            try {
                cn.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
            JOptionPane.showMessageDialog(null, e);
            return false;
        } finally {
            try {
                cn.setAutoCommit(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    public boolean eliminarPedido(int idPedido) {
        try {
            cn.setAutoCommit(false);

            sql = "DELETE FROM pedido_mesa WHERE idpedido = ?";
            PreparedStatement psPedidoMesa = cn.prepareStatement(sql);
            psPedidoMesa.setInt(1, idPedido);
            psPedidoMesa.executeUpdate();

            sql = "DELETE FROM detalle_pedido WHERE idpedido = ?";
            PreparedStatement psDetallePedido = cn.prepareStatement(sql);
            psDetallePedido.setInt(1, idPedido);
            psDetallePedido.executeUpdate();

            sql = "DELETE FROM pedido WHERE idpedido = ?";
            PreparedStatement psPedido = cn.prepareStatement(sql);
            psPedido.setInt(1, idPedido);
            int n = psPedido.executeUpdate();

            if (n == 0) {
                cn.rollback();
                JOptionPane.showMessageDialog(null, "Error al eliminar el pedido");
                return false;
            }
            cn.commit();
            return true;
        } catch (SQLException e) {
            try {
                cn.rollback();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
            JOptionPane.showMessageDialog(null, e);
            return false;
        } finally {
            try {
                cn.setAutoCommit(true);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }

    public int obtenerIdUsuarioPorCedula(String cedula) {
        sql = "SELECT idusuario FROM usuario WHERE cedula = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, cedula);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("idusuario");
            } else {
                return -1; // No se encontró el usuario
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return -1;
        }
    }

    public int obtenerIdPlatoPorNombre(String nombrePlato) {
        sql = "SELECT idplato FROM plato WHERE nombre = ?";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setString(1, nombrePlato);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                return rs.getInt("idplato");
            } else {
                return -1; // No se encontró el plato
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return -1;
        }
    }

    public boolean esMesaLibre(int idMesa) {
        sql = "SELECT * FROM mesa WHERE idmesa = ? AND estado = 0";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ps.setInt(1, idMesa);
            ResultSet rs = ps.executeQuery();
            return rs.next(); // Devuelve true si encuentra la mesa libre
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
    }

    public void llenarComboBox(JComboBox combo) {
        sql = "SELECT nombre FROM plato";
        try {
            PreparedStatement ps = cn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                combo.addItem(rs.getString("nombre"));
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    public String getEstadoMesa(int idMesa) {
        if(idMesa == 1) {
            return "V"; // Mesa activa
        }else {
        return "L"; // Mesa inactiva
    }
    }
}
