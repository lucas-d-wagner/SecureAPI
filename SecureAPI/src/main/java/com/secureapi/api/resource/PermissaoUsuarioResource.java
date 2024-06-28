package com.secureapi.api.resource;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.secureapi.api.dto.RespostaId;
import com.secureapi.business.exception.BusinessException;
import com.secureapi.business.repository.ItemMenuRepository;
import com.secureapi.business.repository.PermissaoUsuarioRepository;
import com.secureapi.business.repository.UsuarioRepository;
import com.secureapi.core.rest.AbstractResource;
import com.secureapi.model.entity.ItemMenu;
import com.secureapi.model.entity.PermissaoUsuario;
import com.secureapi.model.entity.Usuario;

@ManagedBean
@Transactional
@Path(PermissaoUsuarioResource.PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PermissaoUsuarioResource extends AbstractResource {

	public static final String PATH = "/permissaousuario";

	@Inject
	private UsuarioRepository usuarioRepository;

	@Inject
	private ItemMenuRepository itemMenuRepository;
	
	@Inject
	private PermissaoUsuarioRepository permissaoUsuarioRepository;
	
	@GET
	@Path("/{idUsuario}")
	public List<PermissaoUsuario> permissoesUsuario(@PathParam("idUsuario") Long idUsuario) {
		Usuario usuario = usuarioRepository.findNotNull(idUsuario);
		return permissaoUsuarioRepository.findByUsuario(usuario);
	}
	
	@POST
	@Path("/adicionar/{idUsuario}/{idItemMenu}")
	public RespostaId adicionarPermissaoUsuario(@PathParam("idUsuario") Long idUsuario, @PathParam("idItemMenu") Long idItemMenu) {
		Usuario usuario = usuarioRepository.findNotNull(idUsuario);
		ItemMenu itemMenu = itemMenuRepository.findNotNull(idItemMenu);
		
		PermissaoUsuario permissaoUsuario = new PermissaoUsuario();
		permissaoUsuario.setUsuario(usuario);
		permissaoUsuario.setItemMenu(itemMenu);
		
		permissaoUsuario = permissaoUsuarioRepository.persist(permissaoUsuario);
		return new RespostaId(permissaoUsuario);
	}
	
	@DELETE
	@Path("/remover/{idUsuario}/{idItemMenu}")
	public void removerPermissaoUsuario(@PathParam("idUsuario") Long idUsuario, @PathParam("idItemMenu") Long idItemMenu) {
		Usuario usuario = usuarioRepository.findNotNull(idUsuario);
		ItemMenu itemMenu = itemMenuRepository.findNotNull(idItemMenu);

		PermissaoUsuario permissaoUsuario = permissaoUsuarioRepository.findByUsuarioAndItemMenu(usuario, itemMenu);
		
		if(permissaoUsuario == null) {
			throw new BusinessException("Usuário não possui a permissão.");
		}
		
		permissaoUsuarioRepository.remove(permissaoUsuario);
	}
	
}
