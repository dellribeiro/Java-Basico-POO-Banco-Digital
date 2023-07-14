package me.dio.web.contas;

import lombok.Getter;
import me.dio.web.clientes.Cliente;
import me.dio.web.interfaces.IConta;

public abstract class Conta implements IConta {
	
	private static final int AGENCIA_PADRAO = 1;
	private static int SEQUENCIAL = 1;

	@Getter
	protected int agencia;

	@Getter
	protected int numero;

	@Getter
	protected double saldo;

	protected Cliente cliente;

	public Conta(Cliente cliente) {
		this.agencia = Conta.AGENCIA_PADRAO;
		this.numero = SEQUENCIAL++;
		this.cliente = cliente;
	}

	@Override
	public void sacar(double valor) {
		validaValorPositivo(valor);
		double saldoDisponivel = calculaSaldoDisponivel(valor);
		this.saldo = saldoDisponivel;
	}

	@Override
	public void depositar(double valor) {
		validaValorPositivo(valor);
		this.saldo += valor;
	}

	@Override
	public void transferir(double valor, IConta contaDestino) {
		validaValorPositivo(valor);
		this.sacar(valor);
		contaDestino.depositar(valor);
	}

	protected void imprimirInfosComuns() {
		System.out.println(String.format("Titular: %s", this.cliente.getNome()));
		System.out.println(String.format("Agencia: %d", this.agencia));
		System.out.println(String.format("Numero: %d", this.numero));
		System.out.println(String.format("Saldo: %.2f", this.saldo));
	}

	private void validaValorPositivo(double valor){
		if(valor <= 0)
			throw new IllegalArgumentException("Valor Invalido");
	}

	private double calculaSaldoDisponivel(double valor){
		double saldoDisponivel = this.saldo;

		switch (cliente.getTipoCliente()){
			case COMUM:
				if(valor > saldoDisponivel) {
					throw new RuntimeException("Saldo insuficiente");
				}
				saldoDisponivel -= valor;
				break;
			case ESPECIAL:
				double limiteEspecial = 3000.0;
				if(valor > saldoDisponivel + limiteEspecial) {
					throw new RuntimeException("Saldo e limite especial insuficiente");
				}
				saldoDisponivel += limiteEspecial;
				saldoDisponivel -= valor;
				break;
			default:
				throw new RuntimeException("Tipo de cliente invalido");

		}
		return saldoDisponivel;
	}
}
