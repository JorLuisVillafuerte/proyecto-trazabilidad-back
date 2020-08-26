package ar.com.trazabilidad.servicio;

import java.util.List;
import javax.ejb.Remote;
import ar.com.trazabilidad.dominio.Usuarios;

@Remote
public interface UsuarioServiceRemote {
    
    public List<Usuarios> listarUsuarios();
    
    public Usuarios encontrarUsuarioPorId(int id);
    
    public Usuarios encontrarUsuarioPorDni(int dni);

    public Usuarios registrarUsuario(Usuarios usuario);
    
    public void actualizarUsuario(Usuarios usuario);
    
    public void borrarUsuario(Usuarios usuario);
    
}
