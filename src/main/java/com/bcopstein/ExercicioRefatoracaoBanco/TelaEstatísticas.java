package com.bcopstein.ExercicioRefatoracaoBanco;

import java.util.ArrayList;
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
	public ArrayList<Integer> listaMes() {
		ArrayList<Integer> listaMes=new ArrayList();
		for(Operacao op: operacoes) {
			if(ok(listaMes,op.getMes())==true) {
				listaMes.add(op.getMes());
			}
		}
		return listaMes;
	}
	public ArrayList<Integer> listaAno() {
		ArrayList<Integer> listaAno=new ArrayList();
		for(Operacao op: operacoes) {
			if(ok(listaAno,op.getAno())==true) {
				listaAno.add(op.getAno());
			}
		}
		return listaAno;
	}
	private boolean ok(ArrayList<Integer> lista,int num) {
		for(int i:lista) {
			if(i==num) {
				return false;
			}
		}
		return true;
	}
}
