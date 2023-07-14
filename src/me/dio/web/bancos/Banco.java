package me.dio.web.bancos;

import lombok.Getter;
import lombok.Setter;
import me.dio.web.contas.Conta;

import java.util.List;

@Getter
@Setter
public class Banco {

	private String nome;
	private List<Conta> contas;

}
