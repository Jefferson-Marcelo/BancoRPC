package com.gugawag.rpc.banco;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class AppBanco {

    public static void main(String[] args) throws RemoteException, MalformedURLException, AlreadyBoundException {

        // Cria uma instância do serviço com.gugawag.rpc.banco.BancoServiceServer...
        BancoServiceIF bancoService = new BancoServiceServer();

        // instanciando o registro
        Registry registry = LocateRegistry.getRegistry("127.0.0.1",1099);

        // liga (bind) o serviço ao RMI Registry
        registry.rebind("BancoService", bancoService);

        System.out.println("Service de banco registrado por Jefferson Marcelo Oliveira da Silva....");
        menu();
    }
    public static void menu() {
        System.out.println("\n=== BANCO RMI (ou FMI?!) ===");
        System.out.println("1 - Saldo da conta");
        System.out.println("2 - Quantidade de contas");
        System.out.println("9 - Sair");
    }
}
