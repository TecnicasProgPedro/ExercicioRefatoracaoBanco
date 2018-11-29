package com.bcopstein.ExercicioRefatoracaoBanco;

public class Persistencia {
	private static Persistencia INSTANCE;
	private Contas contas;
	private Operacoes operacoes;

	public static Persistencia getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new Persistencia();
		}
		return INSTANCE;
	}

	private Persistencia() {
		this.operacoes = Operacoes.getInstance();
		this.contas = Contas.getInstance();
	}

	public Contas getContas() {
		return this.contas;
	}

	public Operacoes getOperacoes() {
		return this.operacoes;
	}
}
