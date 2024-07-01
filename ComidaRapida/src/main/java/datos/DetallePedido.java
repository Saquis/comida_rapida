/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

/**
 *
 * @author CAFRAL
 */
public class DetallePedido {
    private int iddetalle_pedido;
    private int idpedido;
    private int idplato;
    private int cantidad;

    public DetallePedido() {
    
}
    
    public DetallePedido(int iddetalle_pedido, int idpedido, int idplato, int cantidad) {
        this.iddetalle_pedido = iddetalle_pedido;
        this.idpedido = idpedido;
        this.idplato = idplato;
        this.cantidad = cantidad;
    }

    public int getIddetalle_pedido() {
        return iddetalle_pedido;
    }

    public void setIddetalle_pedido(int iddetalle_pedido) {
        this.iddetalle_pedido = iddetalle_pedido;
    }

    public int getIdpedido() {
        return idpedido;
    }

    public void setIdpedido(int idpedido) {
        this.idpedido = idpedido;
    }

    public int getIdplato() {
        return idplato;
    }

    public void setIdplato(int idplato) {
        this.idplato = idplato;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    
}
