package com.secureapi.api.resource;

import javax.annotation.ManagedBean;
import javax.annotation.security.PermitAll;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.secureapi.api.context.LoginContext;
import com.secureapi.api.dto.LoginDTO;
import com.secureapi.api.dto.TokenDTO;
import com.secureapi.api.jwt.JWT;
import com.secureapi.business.exception.BusinessException;
import com.secureapi.business.repository.UsuarioRepository;
import com.secureapi.core.rest.AbstractResource;
import com.secureapi.model.entity.Usuario;

@ManagedBean
@Transactional
@Path(AuthResource.PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthResource extends AbstractResource {

	public static final String PATH = "/auth";

	@Inject
	private UsuarioRepository usuarioRepository;
	
	@POST
	@Path("/login")
	@PermitAll
	public TokenDTO login(LoginDTO dto) {
		String login = dto.getLogin();
		String password = dto.getPassword();
		
		LoginContext loginContext = doLogin(login, password);
		
		return JWT.buildTokenDTO(loginContext);
	}

	private LoginContext doLogin(String login, String password) {
		Usuario usuario = usuarioRepository.findByLogin(login);

		if(usuario == null) {
			throw new BusinessException("Usuario inválido, verifique os campos e tente novamente.");
		}
		
		if(usuario.getSenha().equals(password) == false) {
			throw new BusinessException("Senha inválida, verifique os campos e tente novamente.");
		}
		
		return new LoginContext(usuario.getIdUsuario(), usuario.getLogin(), usuario.isAdministrador());
	}
	
}
