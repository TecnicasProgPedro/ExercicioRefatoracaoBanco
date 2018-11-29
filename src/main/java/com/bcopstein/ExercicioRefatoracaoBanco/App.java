package com.bcopstein.ExercicioRefatoracaoBanco;

import javafx.application.Application;
import javafx.stage.Stage;


public class App extends Application {
	private Persistencia persistencia;
	private TelaEntrada telaEntrada;
	
    @Override
    public void start(Stage primaryStage) {
    	persistencia = Persistencia.getInstance();
    	
    	primaryStage.setTitle("$$ Banco NOSSA GRANA $$");

    	telaEntrada = new TelaEntrada(primaryStage, persistencia.getOperacoes().getOperacoes()); // << Substituir por singleton
        primaryStage.setScene(telaEntrada.getTelaEntrada());
        primaryStage.show();
    }
    
    @Override
    public void stop() {
    	 persistencia.getContas().saveContas(persistencia.getContas().getContas().values());
         persistencia.getOperacoes().saveOperacoes(persistencia.getOperacoes().getOperacoes());
    }
    
    public static void main(String[] args) {
        launch(args);
    }
}

