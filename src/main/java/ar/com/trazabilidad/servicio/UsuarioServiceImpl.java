package ar.com.trazabilidad.servicio;

import ar.com.trazabilidad.datos.UsuarioDAO;
import ar.com.trazabilidad.dominio.Usuarios;
import java.util.List;
import javax.inject.Inject;

public class UsuarioServiceImpl implements UsuarioServiceRemote{
    
    @Inject
    private UsuarioDAO usuarioDAO; 
    
    @Override
    public List<Usuarios> listarUsuarios() {
        return usuarioDAO.obtenerUsuarios();
    }

    @Override
    public Usuarios encontrarUsuarioPorId(int id) {
        return usuarioDAO.obtenerUsuarioPorId(id);
    }

    @Override
    public Usuarios registrarUsuario(Usuarios usuario) {
        return usuarioDAO.insertarUsuario(usuario);
    }

    @Override
    public void actualizarUsuario(Usuarios usuario) {
        usuarioDAO.modificarUsuario(usuario);
    }

    @Override
    public void borrarUsuario(Usuarios usuario) {
       usuarioDAO.eliminarUsuario(usuario);
    }

    @Override
    public Usuarios encontrarUsuarioPorDni(int dni) {
        return usuarioDAO.obtenerUsuarioPorDni(dni);
    }

    
}
