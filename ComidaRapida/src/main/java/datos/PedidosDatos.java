/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

public class PedidosDatos {
    private int idPedido;
    private java.sql.Date fecha;
    private int idUsuario;
    private int estado;
    private String nombre;  // Agregar atributo nombre

    public PedidosDatos() {}

    public PedidosDatos(int idPedido, java.sql.Date fecha, int idUsuario, int estado, String nombre) {
        this.idPedido = idPedido;
        this.fecha = fecha;
        this.idUsuario = idUsuario;
        this.estado = estado;
        this.nombre = nombre;  // Inicializar atributo nombre
    }

    public int getIdPedido() {
        return idPedido;
    }

    public void setIdPedido(int idPedido) {
        this.idPedido = idPedido;
    }

    public java.sql.Date getFecha() {
        return fecha;
    }

    public void setFecha(java.sql.Date fecha) {
        this.fecha = fecha;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public String getNombre() {  // Método getter para nombre
        return nombre;
    }

    public void setNombre(String nombre) {  // Método setter para nombre
        this.nombre = nombre;
    }
}




