
package ar.com.trazabilidad.servicio;

import ar.com.trazabilidad.dominio.Usuarios;
import java.util.List;
import javax.ejb.Local;

@Local
public interface UsuarioServiceLocal {
     public List<Usuarios> listarUsuarios();
    
    public Usuarios encontrarUsuarioPorId(Usuarios usuario);
    
    public void registrarUsuario(Usuarios usuario);
    
    public void actualizarUsuario(Usuarios usuario);
    
    public void borrarUsuario(Usuarios usuario);
}
