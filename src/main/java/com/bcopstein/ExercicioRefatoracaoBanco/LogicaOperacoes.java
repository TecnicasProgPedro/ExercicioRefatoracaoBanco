package com.bcopstein.ExercicioRefatoracaoBanco;

import java.util.ArrayList;

public class LogicaOperacoes {
	private static LogicaOperacoes INSTANCE;
	private Persistencia persistencia;
	
	private LogicaOperacoes() {
		this.persistencia=Persistencia.getInstance();
	}
	
	public static LogicaOperacoes getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new LogicaOperacoes();
		}
		return INSTANCE;
	}
	
	public Conta DefinirContaEmUso(int nroConta){
		return persistencia.getContas().contaEmUso(nroConta);
	}
	public void OperaçaoDeCredito(Conta c,double valor){
		c.deposito(valor);
	}
	public void OperacaoDeDebito(Conta c,double valor){
		c.retirada(valor);
	}
	public double SolicitaSaldoMedio(int mes,int ano,Conta conta){
		return persistencia.getOperacoes().SaldoMedioMes(mes, ano, conta);
	}
	public double creditoMes(int mes, int ano, Conta conta) {
		return persistencia.getOperacoes().creditosMes(mes, ano, conta);
	}
	public double debitoMes(int mes, int ano,Conta conta) {
		return persistencia.getOperacoes().debitosMes(mes, ano, conta);
	}
	public ArrayList<Integer> listMes(){
		return persistencia.getOperacoes().listaMes();
	}
	public ArrayList<Integer> listAno(){
		return persistencia.getOperacoes().listaAno();
	}
}
