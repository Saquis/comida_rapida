/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package datos;

/**
 *
 * @author USER1
 */
public class Comidas {
    private int idplato;
    private String nombre;
    private String descripcion;
    private String observacion;
    private double valor;
    private int stock;
    private String estado;

    //Implementamos el constructor vacio para el objeto Estudiante
    public Comidas(){
    }
    
    public Comidas(int idplato, String nombre, String descripcion, String observacion, double valor, int stock, String estado) {
        this.idplato = idplato; // Esto se asignará automáticamente en la base de datos
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.observacion = observacion;
        this.valor = valor;
        this.stock = stock;
        this.estado = estado;
    }     
    
    // Implementamos un constructor que solo inicialice el idplato
    public Comidas(int idplato){
        this.idplato = idplato;
    }

    // Getters y Setters

    public int getIdplato() {
        return idplato;
    }

    public void setIdplato(int idplato) {
        this.idplato = idplato;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}