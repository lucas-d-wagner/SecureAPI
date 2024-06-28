package com.secureapi.api.resource;

import java.util.List;

import javax.annotation.ManagedBean;
import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.secureapi.api.dto.PayloadResponse;
import com.secureapi.business.exception.BusinessException;
import com.secureapi.business.repository.ItemMenuRepository;
import com.secureapi.business.repository.PermissaoUsuarioRepository;
import com.secureapi.business.repository.UsuarioRepository;
import com.secureapi.core.rest.AbstractResource;
import com.secureapi.model.entity.ItemMenu;
import com.secureapi.model.entity.PermissaoUsuario;
import com.secureapi.model.entity.Usuario;
import com.secureapi.model.enums.EnumItemMenu;

@ManagedBean
@Transactional
@Path(MenuResource.PATH)
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MenuResource extends AbstractResource {

	public static final String PATH = "/menu";

	@Inject
	private UsuarioRepository usuarioRepository;

	@Inject
	private ItemMenuRepository itemMenuRepository;
	
	@Inject
	private PermissaoUsuarioRepository permissaoUsuarioRepository;
	
	@GET
	public List<ItemMenu> consultar() {
		return itemMenuRepository.findAll();
	}
	
	@GET
	@Path("/permitidos")
	public List<ItemMenu> consultarPermitidos() {
		Usuario usuarioContexto = usuarioRepository.findNotNull(getLoginContext().getIdUsuario());
		return itemMenuRepository.findByUsuarioComPermissao(usuarioContexto);
	}
	
	@GET
	@Path("/cadastro-usuario")
	public PayloadResponse<String> cadastroUsuario() {
		validarAcessoItemMenu(EnumItemMenu.CADASTRO_USUARIO);
		return new PayloadResponse<String>("Sucesso!");
	}

	@GET
	@Path("/cadastro-parceiro")
	public PayloadResponse<String> cadastroParceiro() {
		validarAcessoItemMenu(EnumItemMenu.CADASTRO_PARCEIRO);
		return new PayloadResponse<String>("Sucesso!");
	}

	@GET
	@Path("/faturamento-nfe")
	public PayloadResponse<String> faturamentoNFe() {
		validarAcessoItemMenu(EnumItemMenu.FATURAMENTO_NOTA_FISCAL);
		return new PayloadResponse<String>("Sucesso!");
	}

	@GET
	@Path("/recebimento-financeiro")
	public PayloadResponse<String> recebimentoFinanceiro() {
		validarAcessoItemMenu(EnumItemMenu.RECEBIMENTO_FINANCEIRO);
		return new PayloadResponse<String>("Sucesso!");
	}

	@GET
	@Path("/fechamento-contabil")
	public PayloadResponse<String> fechamentoContabil() {
		validarAcessoItemMenu(EnumItemMenu.FECHAMENTO_CONTABIL);
		return new PayloadResponse<String>("Sucesso!");

	}
	
	private void validarAcessoItemMenu(EnumItemMenu enumItemMenu) {
		ItemMenu itemMenu = enumItemMenu.getEntity();
		Usuario usuario = usuarioRepository.findNotNull(getLoginContext().getIdUsuario());
		
		PermissaoUsuario permissaoUsuario = permissaoUsuarioRepository.findByUsuarioAndItemMenu(usuario, itemMenu);
		
		if(permissaoUsuario == null) {
			throw new BusinessException("Usuário não possui permissão de acesso ao item de menu: " + itemMenu.getDescricao() + "!");
		}
	}
	
}
