package com.bcopstein.ExercicioRefatoracaoBanco;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class Operacoes {
	private List<Operacao> operacoes;
	private final String NomeBDOperacoes = "BDOperBNG.txt";
	private static Operacoes INSTANCE;
	
	public static Operacoes getInstance() {
		 if (INSTANCE == null) {
				INSTANCE = new Operacoes();
			}
		 return INSTANCE;
	}
	
	private Operacoes() {
		this.operacoes=loadOperacoes();
	}
	public List<Operacao> getOperacoes(){
		return this.operacoes;
	}
	
	public double SaldoMedioMes(int mes, int ano,Conta conta) {
		double Saldo = 0;
		for (Operacao op : operacoes) {
			if(op.getNumeroConta()==conta.getNumero() && op.getAno()<=ano && op.getMes()<mes) {
				if(op.getTipoOperacao()==0) {
					Saldo+=op.getValorOperacao();
				}
				else {
					Saldo-=op.getValorOperacao();
				}
			}
		}
		double somaSaldo=0;
		for (Operacao op : operacoes) {
			if(op.getNumeroConta()==conta.getNumero() && op.getAno()==ano && op.getMes()==mes) {
				if(op.getTipoOperacao()==0) {
					somaSaldo+=(Saldo+op.getValorOperacao());
				}
				else {
					somaSaldo+=(Saldo-op.getValorOperacao());
				}
			}
		}
		return somaSaldo/30;
	}
	
	private List<Operacao> loadOperacoes() {
		List<Operacao> operacoes = new LinkedList<Operacao>();

		String currDir = Paths.get("").toAbsolutePath().toString();
		String nameComplete = currDir + "\\" + NomeBDOperacoes;
		System.out.println(nameComplete);
		Path path2 = Paths.get(nameComplete);
		try (Scanner sc = new Scanner(Files.newBufferedReader(path2, Charset.defaultCharset()))) {
			sc.useDelimiter("[;\n]"); // separadores: ; e nova linha
			int dia, mes, ano;
			int hora, minuto, segundo;
			int numero, status, tipo;
			double valor;

			while (sc.hasNext()) {
				dia = Integer.parseInt(sc.next());
				mes = Integer.parseInt(sc.next());
				ano = Integer.parseInt(sc.next());
				hora = Integer.parseInt(sc.next());
				minuto = Integer.parseInt(sc.next());
				segundo = Integer.parseInt(sc.next());
				numero = Integer.parseInt(sc.next());
				status = Integer.parseInt(sc.next());
				valor = Double.parseDouble(sc.next());
				tipo = Integer.parseInt(sc.next());

				Operacao op = new Operacao(dia, mes, ano, hora, minuto, segundo, numero, status, valor, tipo);

				operacoes.add(op);
			}
		} catch (IOException x) {
			System.err.format("Erro de E/S: %s%n", x);
			return null;
		}
		return operacoes;
	}

	public void saveOperacoes(Collection<Operacao> operacoes) {
		Path path1 = Paths.get(NomeBDOperacoes);
		try (PrintWriter writer = new PrintWriter(Files.newBufferedWriter(path1, Charset.defaultCharset()))) {
			for (Operacao op : operacoes)
				writer.format(Locale.ENGLISH, "%d;%d;%d;%d;%d;%d;%d;%d;%f;%d;", op.getDia(), op.getMes(), op.getAno(),
						op.getHora(), op.getMinuto(), op.getSegundo(), op.getNumeroConta(), op.getStatusConta(),
						op.getValorOperacao(), op.getTipoOperacao());
		} catch (IOException x) {
			System.err.format("Erro de E/S: %s%n", x);
		}
	}
	public double creditosMes(int mes, int ano, Conta conta) {
		double soma = 0;
		for (Operacao op : operacoes) {
			if(op.getNumeroConta()==conta.getNumero() && op.getAno()==ano && op.getMes()==mes) {
				if(op.getTipoOperacao()==0) {
					soma+=op.getValorOperacao();
				}
			}
		}
		return soma;
	}
	public double debitosMes(int mes, int ano, Conta conta) {
		double soma = 0;
		for (Operacao op : operacoes) {
			if(op.getNumeroConta()==conta.getNumero() && op.getAno()==ano && op.getMes()==mes) {
				if(op.getTipoOperacao()==1) {
					soma+=op.getValorOperacao();
				}
			}
		}
		return soma;
	}
	public ArrayList<Integer> listaMes() {
		ArrayList<Integer> listaMes = new ArrayList<Integer>();
		for (Operacao op : operacoes) {
			if (ok(listaMes, op.getMes()) == true) {
				listaMes.add(op.getMes());
			}
		}
		return listaMes;
	}

	public ArrayList<Integer> listaAno() {
		ArrayList<Integer> listaAno = new ArrayList<Integer>();
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
