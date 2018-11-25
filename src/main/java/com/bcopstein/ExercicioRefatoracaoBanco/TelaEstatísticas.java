package com.bcopstein.ExercicioRefatoracaoBanco;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class TelaEstatísticas {
	private List<Operacao> operacoes;
	private Conta conta;
	private Stage mainStage;
	private Scene telaOP;
	
	public TelaEstatísticas(List<Operacao> operacoes,Conta conta,Stage mainStage, Scene telaOP) throws IOException{
		this.operacoes=operacoes;
		this.conta=conta;
		this.mainStage=mainStage;
		this.telaOP=telaOP;
	}
	
	public Scene getEstatisticas() throws IOException {
		Pane root=FXMLLoader.load(getClass().getResource("FXMLtelaEstats.fxml"));
		Scene sceneEstats=new Scene(root,300,200);
		
	
		return sceneEstats;
	}
	
	//-------------------------------------
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
