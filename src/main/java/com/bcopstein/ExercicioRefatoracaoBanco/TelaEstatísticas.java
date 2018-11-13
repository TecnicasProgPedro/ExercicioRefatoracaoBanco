package com.bcopstein.ExercicioRefatoracaoBanco;

import java.util.List;

public class TelaEstatísticas {
	private List<Operacao> operacoes;
	private Conta conta;

	public double SaldoMedioMes(int mes, int ano) {
		double Soma = 0;
		for (Operacao op : operacoes) {
			if (conta.getNumero() == op.getNumeroConta()) {
				if (op.getAno() == ano) {
					if (op.getMes() > mes) {
						if (op.getTipoOperacao() == 1) {
							Soma-=op.getValorOperacao();
						}
						else {
							Soma+=op.getValorOperacao();
						}
					}
				}
				else if(op.getAno() > ano) {
					if (op.getTipoOperacao() == 1) {
						Soma-=op.getValorOperacao();
					}
					else {
						Soma+=op.getValorOperacao();
					}
				}
			}
		}
		return (conta.getSaldo() - (Soma))/30;
	}

}
