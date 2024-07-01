/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package logica;

import datos.MesasDatos;
import datos.PedidosDatos;
import datos.Rol;
import java.sql.*;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

/**
 *
 * @author CAFRAL
 */
public class FunPedidos {

    private Conexion mysql = new Conexion();
    private Connection cn = mysql.getConnection();
    public Integer totalRegistro;
    private String sql = "";

    ArrayList<PedidosDatos> lista;
    ArrayList<MesasDatos> lista2;
    ArrayList<Rol> lista3;

    public FunPedidos() {
        lista = new ArrayList<>();
        lista2 = new ArrayList<>();
        lista3 = new ArrayList<>();
    }

    public void Agregar_Platos(PedidosDatos p) {
        lista.add(p);
    }

    public void AgregarMesas(MesasDatos m) {
        lista2.add(m);
    }

    public void AgregarRoles(Rol r) {
        lista3.add(r);
    }

    public DefaultTableModel mostrar(String buscar) {
        DefaultTableModel modelo;
    String[] titulos = {"ID PEDIDO", "ID USUARIO", "NOMBRE COMPLETO", "NOMBRE PLATO", "CANTIDAD PLATOS", "ID MESA", "FECHA DEL PEDIDO", "ESTADO"};
    String[] registro = new String[8];
    totalRegistro = 0;
    modelo = new DefaultTableModel(null, titulos);
    sql = "SELECT p.idpedido AS \"idpedido\",\n"
            + "    u.idusuario AS \"idusuario\",\n"
            + "    CONCAT(u.nombres, ' ', u.apellidos) AS \"nombreCompleto\",\n"
            + "    pl.nombre AS \"nombreplato\",\n"
            + "    dp.cantidad AS \"cantidad\",\n"
            + "    pm.idmesa AS \"idmesa\",\n"
            + "    p.fecha_pedido AS \"fecha_pedido\",\n"
            + "    p.estado AS \"estado\"\n"
            + "FROM \n"
            + "    pedido p\n"
            + "JOIN \n"
            + "    usuario u ON p.idusuario = u.idusuario\n"
            + "JOIN \n"
            + "    detalle_pedido dp ON p.idpedido = dp.idpedido\n"
            + "JOIN \n"
            + "    plato pl ON dp.idplato = pl.idplato\n"
            + "JOIN \n"
            + "    pedido_mesa pm ON p.idpedido = pm.idpedido;";
    try {
        Statement st = cn.createStatement();
        ResultSet resultSet = st.executeQuery(sql);
        while (resultSet.next()) {
            registro[0] = resultSet.getString("idpedido");
            registro[1] = resultSet.getString("idusuario");
            registro[2] = resultSet.getString("nombreCompleto");
            registro[3] = resultSet.getString("nombreplato");
            registro[4] = resultSet.getString("cantidad");
            registro[5] = resultSet.getString("idmesa");
            Date fechaPedido = resultSet.getDate("fecha_pedido");
            if (fechaPedido != null) {
                registro[6] = fechaPedido.toString(); // Convierte la fecha a string
            } else {
                registro[6] = "N/A"; // Maneja el caso de fechas nulas
            }
            registro[7] = resultSet.getString("estado");
            modelo.addRow(registro);

            // Agregar impresión para depuración
            System.out.println("Pedido: " + resultSet.getString("idpedido") + ", Usuario: " + resultSet.getString("idusuario") + ", Plato: " + resultSet.getString("nombreplato"));
        }
        return modelo;
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "Error al mostrar la tabla " + e);
        e.printStackTrace(); // Añadir traza de la excepción para depuración
        return null;
    }
}

    public boolean insertarPedido(String cedulaCliente, String nombrePlato, int cantidad, int idMesa) {
        int idUsuario = obtenerIdUsuarioPorCedula(cedulaCliente);
        LocalDateTime fechaActual = LocalDateTime.now();
        // Definir el formato deseado
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        // Formatear la fecha y hora actuales
        String fechaFormateada = fechaActual.format(formato);

        if (idUsuario == -1) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado");
            return false;
        }

        int idPlato = obtenerIdPlatoPorNombre(nombrePlato);
        if (idPlato == -1) {
            JOptionPane.showMessageDialog(null, "Plato no encontrado");
            return false;
        }

        try {
            // Iniciar una transacción
            cn.setAutoCommit(false);

            // Insertar en la tabla pedido
            sql = "INSERT INTO pedido (fecha_pedido, idusuario, estado) VALUES ('" + fechaFormateada + "', ?, 1)";
            PreparedStatement psPedido = cn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            psPedido.setInt(1, idUsuario);
            int n = psPedido.executeUpdate();

            if (n == 0) {
                cn.rollback();
                JOptionPane.showMessageDialog(null, "Error al insertar el pedido");
                return false;
            }

            ResultSet generatedKeys = psPedido.getGeneratedKeys();
            int idPedido = 0;
            if (generatedKeys.next()) {
                idPedido = generatedKeys.getInt(1);
            } else {
                cn.rollback();
                JOptionPane.showMessageDialog(null, "Error al obtener el ID del pedido");
                return false;
            }

            // Insertar en la tabla detalle_pedido
            sql = "INSERT INTO detalle_pedido (idpedido, idplato, cantidad) VALUES (?, ?, ?)";
            PreparedStatement psDetallePedido = cn.prepareStatement(sql);
            psDetallePedido.setInt(1, idPedido);
            psDetallePedido.setInt(2, idPlato);
            psDetallePedido.setInt(3, cantidad);
            psDetallePedido.executeUpdate();

            // Insertar en la tabla pedido_mesa
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

    public boolean editarPedido(int idPedido, String cedulaCliente, String nombrePlato, int cantidad, int idMesa) {
        int idUsuario = obtenerIdUsuarioPorCedula(cedulaCliente);
        if (idUsuario == -1) {
            JOptionPane.showMessageDialog(null, "Cliente no encontrado");
            return false;
        }

        int idPlato = obtenerIdPlatoPorNombre(nombrePlato);
        if (idPlato == -1) {
            JOptionPane.showMessageDialog(null, "Plato no encontrado");
            return false;
        }

        try {
            // Iniciar una transacción
            cn.setAutoCommit(false);

            // Buscar el idPedido del usuario
            sql = "SELECT idpedido FROM pedido WHERE idusuario = ?";
            PreparedStatement psBuscarPedido = cn.prepareStatement(sql);
            psBuscarPedido.setInt(1, idUsuario);
            ResultSet rs = psBuscarPedido.executeQuery();

            if (!rs.next()) {
                cn.rollback();
                JOptionPane.showMessageDialog(null, "Pedido no encontrado para el cliente especificado");
                return false;
            }

            // Actualizar la tabla pedido
            sql = "UPDATE pedido SET fecha_pedido = CURRENT_TIMESTAMP, idusuario = ? WHERE idpedido = ?";
            PreparedStatement psPedido = cn.prepareStatement(sql);
            psPedido.setInt(1, idUsuario);
            psPedido.setInt(2, idPedido);
            int n = psPedido.executeUpdate();
            if (n == 0) {
                cn.rollback();
                JOptionPane.showMessageDialog(null, "Error al actualizar el pedido");
                return false;
            }

            // Actualizar la tabla detalle_pedido
            sql = "UPDATE detalle_pedido SET idplato = ?, cantidad = ? WHERE idpedido = ?";
            PreparedStatement psDetallePedido = cn.prepareStatement(sql);
            psDetallePedido.setInt(1, idPlato);
            psDetallePedido.setInt(2, cantidad);
            psDetallePedido.setInt(3, idPedido);
            psDetallePedido.executeUpdate();

            // Actualizar la tabla pedido_mesa
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

            // Eliminar de la tabla pedido_mesa
            sql = "DELETE FROM pedido_mesa WHERE idpedido = ?";
            PreparedStatement psPedidoMesa = cn.prepareStatement(sql);
            psPedidoMesa.setInt(1, idPedido);
            psPedidoMesa.executeUpdate();

            // Eliminar de la tabla detalle_pedido
            sql = "DELETE FROM detalle_pedido WHERE idpedido = ?";
            PreparedStatement psDetallePedido = cn.prepareStatement(sql);
            psDetallePedido.setInt(1, idPedido);
            psDetallePedido.executeUpdate();

            // Eliminar de la tabla pedido
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
}


