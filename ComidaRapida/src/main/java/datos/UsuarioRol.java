package datos;

public class UsuarioRol {
    private int id_usuariorol;
    private Usuario usuario;
    private Rol rol;

    public UsuarioRol() {
    }

    public UsuarioRol(int id_usuariorol, Usuario usuario, Rol rol) {
        this.id_usuariorol = id_usuariorol;
        this.usuario = usuario;
        this.rol = rol;
    }

    public int getId_usuariorol() {
        return id_usuariorol;
    }

    public void setId_usuariorol(int id_usuariorol) {
        this.id_usuariorol = id_usuariorol;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
   
}
