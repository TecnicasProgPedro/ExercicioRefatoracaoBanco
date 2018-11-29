package com.bcopstein.ExercicioRefatoracaoBanco;

public class LogicaOperacoes {
	private static LogicaOperacoes INSTANCE;
	
	private LogicaOperacoes() {}
	
	public static LogicaOperacoes getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new LogicaOperacoes();
		}
		return INSTANCE;
	}
	
	public void DefinirContaEmUso(){}
	public void OperaçaoDeCredito(){}
	public void OperacaoDeDebito(){}
	public void SolicitaExtrato(){}
	public void SolicitaSaldo(){}
	public void SolicitaSaldoMedio(){}
	public void TotalCreditos(){}
}
