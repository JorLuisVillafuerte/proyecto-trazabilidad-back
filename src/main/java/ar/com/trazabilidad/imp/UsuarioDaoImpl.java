package ar.com.trazabilidad.imp;

import ar.com.trazabilidad.datos.UsuarioDAO;
import ar.com.trazabilidad.dominio.Usuarios;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.*;

@Stateless
public class UsuarioDaoImpl implements UsuarioDAO{
    
    @PersistenceContext(unitName="TrazabilidadPU")
    EntityManager em;

    @Override
    public List<Usuarios> obtenerUsuarios() {
        return em.createNamedQuery("Usuarios.findAll").getResultList();
    }

    @Override
    public Usuarios obtenerUsuarioPorId(int id) {
        return em.find(Usuarios.class, id);
    }

    @Override
    public Usuarios insertarUsuario(Usuarios u) {
        em.persist(u);
        em.flush();
        return u;
    }

    @Override
    public void modificarUsuario(Usuarios u) {
        em.merge(u);
    }

    @Override
    public void eliminarUsuario(Usuarios u) {
        em.remove(em.merge(u));
    }

    @Override
    public boolean validarLogin(int dni, String password) {
        Query query = em.createNamedQuery("Usuarios.validarLoginDB");
        query.setParameter("dni", dni);
        query.setParameter("password", password);
        return false;
    }

    @Override
    public Usuarios obtenerUsuarioPorDni(int dni) {
        Query query = em.createNamedQuery("Usuarios.findByDni");
        query.setParameter("dni", dni);
        return (Usuarios) query.getSingleResult();
    }
    
    
    
}
