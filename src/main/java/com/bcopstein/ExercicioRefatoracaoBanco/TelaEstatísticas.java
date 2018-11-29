package com.bcopstein.ExercicioRefatoracaoBanco;

import java.io.IOException;
import java.util.GregorianCalendar;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;

public class TelaEstatísticas {
	private Conta conta;
	private Stage mainStage;
	private Scene telaOP;
	private LogicaOperacoes logicaOperacoes;
	
	public TelaEstatísticas(Conta conta, Stage mainStage, Scene telaOP) throws IOException {
		this.conta = conta;
		this.mainStage = mainStage;
		this.telaOP = telaOP;
		this.logicaOperacoes=LogicaOperacoes.getInstance();
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
        
        ChoiceBox<Integer> selecionarMes=new ChoiceBox<Integer>(); 
        ChoiceBox<Integer> selecionarAno=new ChoiceBox<Integer>();
        selecionarMes.getItems().addAll(logicaOperacoes.listMes());
        selecionarAno.getItems().addAll(logicaOperacoes.listAno());
        
        Button btnVoltar = new Button("Voltar");
        Button btnEnter = new Button("Enter");
        HBox hbBtn = new HBox(20);
        hbBtn.setAlignment(Pos.TOP_CENTER);
        Label mensagem01=new Label("Mes:");
        Label mensagem02=new Label("Ano:");
    	hbBtn.getChildren().add(mensagem01);
        hbBtn.getChildren().add(selecionarMes);
    	hbBtn.getChildren().add(mensagem02);
        hbBtn.getChildren().add(selecionarAno);
        hbBtn.getChildren().add(btnEnter);
        hbBtn.getChildren().add(btnVoltar);
        grid.add(hbBtn, 1, 2);
        
        HBox box = new HBox(20);
        box.setAlignment(Pos.BOTTOM_CENTER);

        String saldoM="Saldo Medio:";
    	Label saldoMedio=new Label(saldoM);
    	box.getChildren().add(saldoMedio);
    	String creditosD="Creditos:";
    	Label creditosData=new Label(creditosD);
    	box.getChildren().add(creditosData);
    	String debitosD="Debitos:";
    	Label debitosData=new Label(debitosD);
    	box.getChildren().add(debitosData);
    	grid.add(box, 1, 3);
    	
    	HBox boxbox = new HBox(20);
        box.setAlignment(Pos.BOTTOM_CENTER);
        Label Avisos = new Label();
    	box.getChildren().add(Avisos);
        grid.add(boxbox, 1, 4);
    	
    	GregorianCalendar date = new GregorianCalendar();
    	try {
    		selecionarMes.setValue(date.get(GregorianCalendar.MONTH+1));
    		selecionarAno.setValue(date.get(GregorianCalendar.YEAR));
    		creditosData.setText(creditosD+logicaOperacoes.creditoMes(date.get(GregorianCalendar.MONTH+1),date.get(GregorianCalendar.YEAR),conta));
    		debitosData.setText(debitosD+logicaOperacoes.debitoMes(date.get(GregorianCalendar.MONTH+1),date.get(GregorianCalendar.YEAR),conta));
    		saldoMedio.setText(saldoM + logicaOperacoes.SolicitaSaldoMedio(date.get(GregorianCalendar.MONTH+1),date.get(GregorianCalendar.YEAR),conta));
    	}
        catch(NumberFormatException ex){
        	Avisos.setText("Nenhuma operacao no mes e ano atual!");
        	creditosData.setText(creditosD+0);
    		debitosData.setText(debitosD+0);
    		saldoMedio.setText(saldoM+0);
        }
    	
        btnEnter.setOnAction(e->{
        	saldoMedio.setText(saldoM + logicaOperacoes.SolicitaSaldoMedio(selecionarMes.getValue(),selecionarAno.getValue(),conta));
        	creditosData.setText(creditosD+logicaOperacoes.creditoMes(selecionarMes.getValue(),selecionarAno.getValue(),conta));
    		debitosData.setText(debitosD+logicaOperacoes.debitoMes(selecionarMes.getValue(),selecionarAno.getValue(),conta));
        });
        btnVoltar.setOnAction(e->{
        	mainStage.setScene(telaOP);
        });
        Scene cenaEstatisticas = new Scene(grid);
        return cenaEstatisticas;
	}
}
