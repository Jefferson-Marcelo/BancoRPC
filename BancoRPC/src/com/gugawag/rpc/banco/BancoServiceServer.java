package com.gugawag.rpc.banco;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BancoServiceServer extends UnicastRemoteObject implements BancoServiceIF {

    private Map<String, Double> saldoContas;
    private List<Conta> listaContas;

    public BancoServiceServer() throws RemoteException {
        saldoContas = new HashMap<>();
        this.listaContas = new ArrayList<>();

        // Inicialize o mapa de saldo com base na lista de contas
        for (Conta conta : listaContas) {
            saldoContas.put(conta.getNumero(), conta.getSaldo());
        }
    }

    @Override
    public double saldo(String conta) throws RemoteException {
        return saldoContas.get(conta);
    }

    @Override
    public int quantidadeContas() throws RemoteException {
        return saldoContas.size();
    }

    public void cadastrarConta(String conta, double saldoInicial) throws RemoteException {
        saldoContas.put(conta, saldoInicial);
    }

    public void removerConta(String conta) throws RemoteException {
        saldoContas.remove(conta);    }

    @Override
    public void adicionarConta(Conta conta) throws RemoteException {
        // Adicione a conta à lista de contas e ao mapa de saldos
        listaContas.add(conta);
        saldoContas.put(conta.getNumero(), conta.getSaldo());
    }

    @Override
    public void criarConta() throws RemoteException {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'criarConta'");
    }
}
