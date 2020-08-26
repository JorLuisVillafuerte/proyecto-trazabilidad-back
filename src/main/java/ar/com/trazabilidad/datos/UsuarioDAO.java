/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.trazabilidad.datos;

import java.util.List;
import ar.com.trazabilidad.dominio.Usuarios;

/**
 *
 * @author MR ROBOT
 */
public interface UsuarioDAO {
    
        public List<Usuarios> obtenerUsuarios();
        public Usuarios obtenerUsuarioPorId(int id);
        public Usuarios obtenerUsuarioPorDni(int dni);
        public Usuarios insertarUsuario(Usuarios u);
        public void modificarUsuario(Usuarios u);
        public void eliminarUsuario(Usuarios u);
        public boolean validarLogin(int dni, String password);
        
}
