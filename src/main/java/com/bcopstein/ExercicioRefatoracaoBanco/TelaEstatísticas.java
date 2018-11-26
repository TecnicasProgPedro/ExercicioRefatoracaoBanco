package com.bcopstein.ExercicioRefatoracaoBanco;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class TelaEstatísticas {
	private List<Operacao> operacoes;
	private Conta conta;
	private Stage mainStage;
	private Scene telaOP;
	private Stage cenaEstats;

	public TelaEstatísticas(List<Operacao> operacoes, Conta conta, Stage mainStage, Scene telaOP) throws IOException {
		this.operacoes = operacoes;
		this.conta = conta;
		this.mainStage = mainStage;
		this.telaOP = telaOP;
	}

	public Scene getEstatisticas() throws IOException {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        String dadosCorr = conta.getNumero()+" : "+conta.getCorrentista();
        Text scenetitle = new Text(dadosCorr);
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(scenetitle, 0, 0, 2, 1);
        
        ChoiceBox<Integer> selecionarMes=new ChoiceBox(); 
        ChoiceBox<Integer> selecionarAno=new ChoiceBox();
        
        Button btnVoltar = new Button("Voltar");
        Button btnEnter = new Button("Enter");
        HBox hbBtn = new HBox(20);
        hbBtn.setAlignment(Pos.TOP_CENTER);
        hbBtn.getChildren().add(btnEnter);
        hbBtn.getChildren().add(btnVoltar);
        grid.add(hbBtn, 1, 2);
        
        btnVoltar.setOnAction(e->{
        	mainStage.setScene(telaOP);
        });
        Scene cenaEstatisticas = new Scene(grid);
        return cenaEstatisticas;
	}

	// -------------------------------------
	public Scene getTelaEstatísticas(Stage primaryStage) {
		primaryStage.setTitle("JavaFX Welcome");

		primaryStage.show();
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));

		Scene scene = new Scene(grid, 300, 275);
		primaryStage.setScene(scene);

		return scene;
	}

	public double SaldoMedioMes(int mes, int ano) {
		double Soma = 0;
		for (Operacao op : operacoes) {
			if (conta.getNumero() == op.getNumeroConta()) {
				if (op.getAno() == ano) {
					if (op.getMes() > mes) {
						if (op.getTipoOperacao() == 1) {
							Soma -= op.getValorOperacao();
						} else {
							Soma += op.getValorOperacao();
						}
					}
				} else if (op.getAno() > ano) {
					if (op.getTipoOperacao() == 1) {
						Soma -= op.getValorOperacao();
					} else {
						Soma += op.getValorOperacao();
					}
				}
			}
		}
		return (conta.getSaldo() - (Soma)) / 30;
	}

	public ArrayList<Integer> listaMes() {
		ArrayList<Integer> listaMes = new ArrayList();
		for (Operacao op : operacoes) {
			if (ok(listaMes, op.getMes()) == true) {
				listaMes.add(op.getMes());
			}
		}
		return listaMes;
	}

	public ArrayList<Integer> listaAno() {
		ArrayList<Integer> listaAno = new ArrayList();
		for (Operacao op : operacoes) {
			if (ok(listaAno, op.getAno()) == true) {
				listaAno.add(op.getAno());
			}
		}
		return listaAno;
	}

	private boolean ok(ArrayList<Integer> lista, int num) {
		for (int i : lista) {
			if (i == num) {
				return false;
			}
		}
		return true;
	}
}
