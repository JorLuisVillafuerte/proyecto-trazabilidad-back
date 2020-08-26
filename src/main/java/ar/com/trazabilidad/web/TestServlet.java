/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ar.com.trazabilidad.web;

import ar.com.trazabilidad.dominio.Usuarios;
import ar.com.trazabilidad.servicio.UsuarioServiceLocal;
import ar.com.trazabilidad.servicio.UsuarioServiceRemote;
import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author MR ROBOT
 */
@WebServlet("/test")
public class TestServlet extends HttpServlet{
    
    @Inject
    UsuarioServiceRemote usuarioservice;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        Usuarios usuario = new Usuarios("Jorge", "Clarke", "jorge@correo1.com", 1234567, "Administrador", "123456", 1);
        usuarioservice.registrarUsuario(usuario);
        
    }
}
