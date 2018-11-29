package com.bcopstein.ExercicioRefatoracaoBanco;

import java.util.GregorianCalendar;

public class VALIDACOES {
	private static VALIDACOES INSTANCE;
	private Persistencia persistencia;

	private VALIDACOES() {
		this.persistencia = Persistencia.getInstance();
	}

	public static VALIDACOES getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new VALIDACOES();
		}
		return INSTANCE;
	}

	public boolean valida(double valor, Conta conta) {
		GregorianCalendar date = new GregorianCalendar();
		double ValorTotalDia = valor;
		for (Operacao op : persistencia.getOperacoes().getOperacoes()) {
			if (op.getNumeroConta() == conta.getNumero()) {
				if (op.getAno() == date.get(GregorianCalendar.YEAR)) {
					if (op.getMes() == date.get(GregorianCalendar.MONTH + 1)) {
						if (op.getDia() == date.get(GregorianCalendar.DAY_OF_MONTH)) {
							if (op.getTipoOperacao() == 1) {
								ValorTotalDia += op.getValorOperacao();
							}
						}
					}
				}
			}
		}

		if (ValorTotalDia > conta.getLimRetiradaDiaria()) {
			return false;
		}
		return true;
	}
}
