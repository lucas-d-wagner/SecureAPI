package com.secureapi.model.enums;

import com.secureapi.core.entity.IEntityEnum;
import com.secureapi.model.entity.ItemMenu;

public enum EnumItemMenu implements IEntityEnum<ItemMenu> {
	
	CADASTRO_USUARIO(new ItemMenu(1L, "/cadastro-usuario", "Cadastro de Usuário")),
	CADASTRO_PARCEIRO(new ItemMenu(2L, "/cadastro-parceiro", "Cadastro de Parceiro")),
	FATURAMENTO_NOTA_FISCAL(new ItemMenu(3L, "/faturamento-nfe", "Faturamento NF-e")),
	RECEBIMENTO_FINANCEIRO(new ItemMenu(4L, "/recebimento-financeiro", "Recebimento Financeiro")),
	FECHAMENTO_CONTABIL(new ItemMenu(5L, "/fechamento-contabil", "Fechamento Contábil")),
	;

	private final ItemMenu itemMenu;
	
	private EnumItemMenu(ItemMenu itemMenu) {
		this.itemMenu = itemMenu;
	}

	public ItemMenu getItemMenu() {
		return itemMenu;
	}

	@Override
	public ItemMenu getEntity() {
		return getItemMenu();
	}

}
