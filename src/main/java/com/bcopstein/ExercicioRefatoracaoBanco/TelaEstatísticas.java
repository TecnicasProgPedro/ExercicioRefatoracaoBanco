package com.bcopstein.ExercicioRefatoracaoBanco;


import java.util.List;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TelaEstatísticas {
	private List<Operacao> operacoes;
	private Conta conta;
	
	public TelaEstatísticas(Conta conta, List<Operacao> operacoes) {
		this.conta=conta;
		this.operacoes=operacoes;
	}
	
	public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFX Welcome");
        
        primaryStage.show();
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Scene scene = new Scene(grid, 300, 275);
        primaryStage.setScene(scene);
    }
	
	
	
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
