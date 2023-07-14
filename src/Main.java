import me.dio.web.clientes.Cliente;
import me.dio.web.clientes.TipoCliente;
import me.dio.web.contas.Conta;
import me.dio.web.contas.ContaCorrente;
import me.dio.web.contas.ContaPoupanca;

public class Main {

	public static void main(String[] args) {
		Cliente wendell = new Cliente("Wendell", TipoCliente.COMUM);


		Conta cc = new ContaCorrente(wendell);
		Conta poupanca = new ContaPoupanca(wendell);

		cc.depositar(100);
		cc.transferir(30, poupanca);

		cc.imprimirExtrato();
		poupanca.imprimirExtrato();
	}
}

