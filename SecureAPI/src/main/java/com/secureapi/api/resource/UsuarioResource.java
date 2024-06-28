package com.secureapi.api.resource;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.secureapi.api.dto.RespostaId;
import com.secureapi.business.repository.UsuarioRepository;
import com.secureapi.core.rest.AbstractResource;
import com.secureapi.model.entity.Usuario;

@ManagedBean
@Transactional
@Path(UsuarioResource.PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UsuarioResource extends AbstractResource {

	public static final String PATH = "/usuario";

	@Inject
	private UsuarioRepository usuarioRepository;

	@GET
	public List<Usuario> findAll() {
		return usuarioRepository.findAll();
	}
	
	@GET
	@Path("{id}")
	public Usuario find(@PathParam("id") Long id) {
		return usuarioRepository.findNotNull(id);
	}
	
	@POST
	public RespostaId insert(Usuario usuario) {
		usuario = usuarioRepository.persist(usuario);
		return new RespostaId(usuario);
	}
	
	@PUT
	@Path("{id}")
	public void update(@PathParam("id") Long id, Usuario usuario) {
		usuarioRepository.findNotNull(id);
		usuarioRepository.merge(usuario);
	}
	
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") Long id) {
		Usuario usuario = usuarioRepository.findNotNull(id);
		usuarioRepository.remove(usuario);
	}
	
}
