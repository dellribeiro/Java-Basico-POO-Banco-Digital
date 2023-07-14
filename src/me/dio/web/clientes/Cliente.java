package me.dio.web.clientes;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Cliente {

	private String nome;
	private TipoCliente tipoCliente;

}
