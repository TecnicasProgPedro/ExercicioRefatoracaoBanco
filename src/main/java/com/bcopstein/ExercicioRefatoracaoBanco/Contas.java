package com.bcopstein.ExercicioRefatoracaoBanco;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collection;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.Scanner;

public class Contas {
	 private final String NomeBDContas = "BDContasBNG.txt";
	 
	 public Contas() {}
	 
	 public Map<Integer,Conta> loadContas(){
	    	Map<Integer,Conta> contas = new HashMap<>();
	    	
	        String currDir = Paths.get("").toAbsolutePath().toString();
	        String nameComplete = currDir+"\\"+NomeBDContas;
	        System.out.println(nameComplete);
	        Path path2 = Paths.get(nameComplete); 
	        try (Scanner sc = new Scanner(Files.newBufferedReader(path2, Charset.defaultCharset()))){ 
	           sc.useDelimiter("[;\n]"); // separadores: ; e nova linha 
	           int numero;
	           String nomeCorr;
	           double saldo;
	           int status;
	           while (sc.hasNext()){ 
	               numero = Integer.parseInt(sc.next()); 
	               nomeCorr = sc.next();
	               saldo = Double.parseDouble(sc.next());
	               status = Integer.parseInt(sc.next());
	               Conta conta = new Conta(numero,nomeCorr,saldo,status);
	               contas.put(numero, conta);
	           }
	        }catch (IOException x){ 
	            System.err.format("Erro de E/S: %s%n", x);
	            return null;
	        } 
	        return contas;
	    }

	    public void saveContas(Collection<Conta> contas) {
	        Path path1 = Paths.get(NomeBDContas); 
	        try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path1, Charset.defaultCharset()))) 
	        { 
	            for(Conta c: contas) 
	                writer.format(Locale.ENGLISH,
	                		      "%d;%s;%f;%d;",
	                		      c.getNumero(),c.getCorrentista(), 
	                              c.getSaldo(),c.getStatus()); 
	        } 
	        catch (IOException x) 
	        { 
	            System.err.format("Erro de E/S: %s%n", x); 
	        } 
	    }
}
