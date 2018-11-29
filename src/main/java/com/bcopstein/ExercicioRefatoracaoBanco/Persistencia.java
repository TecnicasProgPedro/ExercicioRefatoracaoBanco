package com.bcopstein.ExercicioRefatoracaoBanco;
import java.util.List;
import java.util.Map;


public class Persistencia {
	private static Persistencia INSTANCE;
	private Map<Integer,Conta> contas;
	private List<Operacao> operacoes;
	
	public static Persistencia getInstance() {
		if(INSTANCE==null) {
			INSTANCE=new Persistencia();
		}
		return INSTANCE;
	}
	
	private Persistencia() {
		Contas c=new Contas();
		Operacoes o=new Operacoes();
		this.contas=c.loadContas();
		this.operacoes=o.loadOperacoes();
	}
	
	public Map<Integer,Conta> getContas(){
		return this.contas;
	}
	public List<Operacao> getOperacoes(){
		return this.operacoes;
	}
}
