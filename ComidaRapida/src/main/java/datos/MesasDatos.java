package datos;

public class MesasDatos {
    private int IdMesa;
    private int capacidad;
    private String estado;

    // Constructor sin parámetros
    public MesasDatos() {
    }

    public MesasDatos(int IdMesa, int capacidad, String estado) {
        this.IdMesa = IdMesa;
        this.capacidad = capacidad;
        this.estado = estado;
    }

    public int getIdMesa() {
        return IdMesa;
    }

    public void setIdMesa(int idMesa) {
        this.IdMesa = idMesa;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    
    
}