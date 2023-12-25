package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.Scanner;

public class AppClienteBanco {

    public static void main(String[] args) throws RemoteException, NotBoundException, MalformedURLException {
        // procura o serviço no RMI Registry local. Perceba que o cliente não connhece a implementação do servidor,
        // apenas a interface
        Registry registry = LocateRegistry.getRegistry();
        BancoServiceIF banco = (BancoServiceIF) registry.lookup("BancoService");

        menu();
        Scanner entrada = new Scanner(System.in);
        int opcao = entrada.nextInt();

        while (opcao != 9) {
            switch (opcao) {
                case 1: {
                    System.out.println("Digite o número da conta:");
                    String conta = entrada.next();
                    // Chamada ao método remoto para obter o saldo da conta
                    System.out.println(banco.saldo(conta));
                    break;
                }
                case 2: {
                    // Chamada ao método remoto para obter a quantidade de contas
                    System.out.println(banco.quantidadeContas());
                    break;
                }
                case 3: {
                    System.out.println("Digite o número da nova conta:");
                    String novaConta = entrada.next();
                    System.out.println("Digite o saldo inicial da nova conta:");
                    double saldoInicial = entrada.nextDouble();
                    // Chamada ao método remoto para cadastrar a nova conta
                    banco.cadastrarConta(novaConta, saldoInicial);
                    System.out.println("Conta cadastrada com sucesso!");
                    break;
                }
                case 4: {
                    System.out.println("Digite o número da conta a ser removida:");
                    String contaRemover = entrada.next();
                    // Chamada ao método remoto para remover a conta
                    banco.removerConta(contaRemover);
                    System.out.println("Conta removida com sucesso!");
                    break;
                }
                case 5: {
                    System.out.println("Digite o número da nova conta:");
                    String novaContaNumero = entrada.next();
                    System.out.println("Digite o saldo inicial da nova conta:");
                    double saldoInicial = entrada.nextDouble();

                    // Crie uma instância de Conta
                    Conta novaConta = new Conta(novaContaNumero, saldoInicial);

                    // Chame o método remoto para adicionar a conta no servidor
                    banco.adicionarConta(novaConta);

                    System.out.println("Conta adicionada com sucesso!");
                    break;
                }
            }
            menu();
            opcao = entrada.nextInt();
        }

    }

    public static void menu() {
        System.out.println("\n=== BANCO RMI (ou FMI?!) ===");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("3 - Cadastrar nova conta");
        System.out.println("4 - Remover conta");
        System.out.println("9 - Sair");
    }


}
