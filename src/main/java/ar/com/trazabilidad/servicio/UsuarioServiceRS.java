package ar.com.trazabilidad.servicio;

import java.util.List;
import javax.inject.Inject;
import ar.com.trazabilidad.dominio.Usuarios;
import com.google.gson.Gson;
import io.jsonwebtoken.Jwts;
import java.util.Date;
import javax.ejb.Stateless;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonObjectBuilder;
import javax.json.JsonValue;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static ar.com.trazabilidad.util.Constants.TOKEN_EXPIRATION_TIME;
import static ar.com.trazabilidad.util.Constants.SECRET_KEY;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("/usuarios")
@Stateless
public class UsuarioServiceRS {
    
    @Inject
    private UsuarioServiceRemote usuarioService;
 
    
    /*@GET
    @Produces(MediaType.APPLICATION_JSON)
    public JsonObject listarUsuarios() {
        List<Usuarios> asd = usuarioService.listarUsuarios();
        JsonObjectBuilder response = Json.createObjectBuilder();
        response.add("estado","todo ok");
        response.add("usuarios", asd.toString());
        JsonObject asd1 = response.build();
        return asd1;
    }*/
    @POST
    @Path("/autenticarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response usuarioAutenticado(JsonObject body){
        JsonObjectBuilder msg = Json.createObjectBuilder();
        Gson gson = new Gson(); 
        try {
            String token = body.getString("token");
            System.out.println(token);
            int idUsuario = Jwts.parser()
              .setSigningKey(SECRET_KEY)
              .parseClaimsJws(token)
              .getBody()
              .get("id",Integer.class);
            System.out.println("FLAG ID USUARIO RECUPERADO: "+ idUsuario);
            Usuarios  usuario = usuarioService.encontrarUsuarioPorId(idUsuario);   
            String json = gson.toJson(usuario);
            //return Response.status(Response.Status.OK).entity(token).build();
            return Response.ok().entity(json).build();

        } catch (Exception e) {
            msg.add("msg", "Usuario no esta autenticado o la sesion expiro");
            return Response.status(Response.Status.BAD_REQUEST).entity(msg.build()).build();
        }
      
    }
    
    @POST
    @Path("/validarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response validarLogin(JsonObject body){
        
        JsonObjectBuilder msg = Json.createObjectBuilder();
        
        try {
            System.out.println("FLAG - BODY: "+body); 
            String dni = body.getString("dni");
            String pass = body.getString("password");
            
            Usuarios usuario = usuarioService.encontrarUsuarioPorDni(Integer.parseInt(dni));
            System.out.println("FLAG - USUARIO: "+usuario.toString()); 
            if(!usuario.getPassword().equals(pass)){
                msg.add("msg", "Usuario o password incorrecto");
                return Response.status(Response.Status.BAD_REQUEST).entity(msg.build()).build();
            }
            String token = generarToken(usuario);
            System.out.println("FLAG - TOKEN GENERADO: "+ token);
            msg.add("token", token);
            //return Response.status(Response.Status.OK).entity(token).build();
            return Response.ok().entity(msg.build()).build();
     
        } catch (Exception e) {
            System.out.println("FLAG ERROR - USUARIO NO EXISTE");
            msg.add("msg", "Usuario o password incorrecto");
            return Response.status(Response.Status.BAD_REQUEST).entity(msg.build()).build();
        }        
    }
    @POST
    @Path("/registrarUsuario")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarUsuario(Usuarios usuario){
        JsonObjectBuilder msg = Json.createObjectBuilder();
        try {
            Usuarios user = usuarioService.registrarUsuario(usuario);
            System.out.print("FLAG - USUARIO REGISTRADO:" + user.toString());
            String token = generarToken(user);
            msg.add("token", token);
            return Response.ok().entity(msg.build()).build();
        } catch (Exception e) {
            e.printStackTrace(System.out);
            msg.add("msg", "Ocurrio un error con la solicitud");
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(msg.build()).build();
        }
    }
    
    public String generarToken(Usuarios usuario){
        return Jwts.builder()
                .setIssuedAt(new Date())
                .setSubject(usuario.getNombre()+" "+usuario.getApellido())
                .setExpiration(new Date(System.currentTimeMillis() + 900000))
                .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
                .claim("id", usuario.getIdusuario())
                .compact();
    }
}
