package com.unifil.agendapaf.util.huffman;

import java.util.ArrayList;

public class TesteArvore {

	public ArrayList<Node> ordenar(ArrayList<Node> ns) {
		Node aux;
		// if (ns.get(ns.size() - 1).getData() == null) {
		// ns.get(ns.size() - 1).setValor(ns.get(ns.size()-1).getValor() -
		// 0.00001);
		// aux = ns.get(ns.size() - 1);
		// ns.set(ns.size() - 1, ns.get(ns.size() - 2));
		// ns.set(ns.size() - 2, aux);
		// }
		for (int i = 0; i < ns.size(); i++) {
			for (int j = 0; j < ns.size(); j++) {
				if (ns.get(i).getValor() < ns.get(j).getValor()) {
					aux = ns.get(i);
					ns.set(i, ns.get(j));
					ns.set(j, aux);
				}
			}
		}
		return ns;
	}

	public static void main(String[] args) {
		TesteArvore ta = new TesteArvore();
		String p = "ATGTCA";
		char[] ps = p.toCharArray();
		int x = 1;
		ArrayList<Node> ns = new ArrayList<Node>();
		ArrayList<Node> nodes = new ArrayList<Node>();
		for (int i = 0; i < ps.length; i++) {
			for (int j = i + 1; j < ps.length; j++) {
				if (ps[i] == ps[j] && ps[i] != 0) {
					ps[j] = 0;
					x += 1;
				}
			}
			if (ps[i] != 0) {
				double x2 = Double.parseDouble(x + "") / p.length();
				ns.add(new Node(x, ps[i] + "", x2));
			}
			x = 1;
		}

		ns = ta.ordenar(ns);
		double soma = 0;
		for (Node node : ns) {
			System.out.println(node.getData() + " " + node.getQuantidade()
					+ " " + node.getValor());
			nodes.add(node);
			soma += node.getValor();
		}

		ArvoreBinaria raiz = new ArvoreBinaria();
		while (ns.size() > 1) {
			ns = ta.ordenar(ns);
			if (ns.get(0).getValor() <= ns.get(1).getValor()) {
				Node n = new Node(0, null, ns.get(0).getValor()
						+ ns.get(1).getValor());
				ArvoreBinaria ar = new ArvoreBinaria();
				ar.inserirNaArvore(n);
				if (ns.get(0).getValor() <= ns.get(1).getValor()) {
					ar.inserirNaArvore(ns.get(0));
					ar.inserirNaArvore(ns.get(1));
				} else {
					ar.inserirNaArvore(ns.get(1));
					ar.inserirNaArvore(ns.get(0));
				}
				ns.remove(0);
				ns.remove(0);
				ns.add(0, ar.getRaiz());
				raiz.setRaiz(ar.getRaiz());
				ar.imprimir();
				nodes.add(new Node(1, ar.getPrint(), n.getValor()));
			}

		}

		raiz.configurarBinario(raiz.getRaiz(), "");
		String novaBin = "";
		char[] palavra = p.toCharArray();
		for (char c : palavra) {
			System.out.println(c);
			raiz.setBinarioData(raiz.getRaiz(), c + "");
			novaBin += raiz.getBin();
		}

		System.out.println("aew " + novaBin);
		for (Node node : nodes) {
			System.out.println(node.getData() + " " + node.getQuantidade()
					+ " " + node.getValor());
		}

		// for (int i = 0; i < ns.size(); i++) {
		// for (int j = i+1; j < ns.size(); j++) {
		// if(ns.get(i).getValor() == ns.get(j).getValor()){
		// ArvoreBinaria a = new ArvoreBinaria();
		// Node temp = new Node(0,null,ns.get(i).getValor() +
		// ns.get(j).getValor());
		// a.inserirNaArvore(temp);
		// a.inserirNaArvore(ns.get(i));
		// a.inserirNaArvore(ns.get(j));
		// as.add(a);
		// ns.remove(i);
		// ns.remove(j);
		// ns.add(a.getRaiz());
		// ns = ta.ordenar(ns);
		// break;
		// }
		// }
		// }
		//
		// for (ArvoreBinaria arvoreBinaria : as) {
		// System.out.println(arvoreBinaria.getRaiz().getValor());
		// System.out.println(arvoreBinaria.getRaiz().getEsquerda().getData());
		// System.out.println(arvoreBinaria.getRaiz().getDireita().getData());
		// }

		/*
		 * ArvoreBinaria arvoreBinaria = new ArvoreBinaria();
		 * 
		 * 
		 * // arvoreBinaria.inserirNaArvore(10);
		 * 
		 * arvoreBinaria.imprimir();
		 * 
		 * Node n1 = new Node(2,"A", 1); Node n2 = new Node(2,"T", 2); Node n3 =
		 * new Node(1,"G", 3); Node n4 = new Node(1,"C", 4); // Node n5 = new
		 * Node(1,"A", 2); // Node n6 = new Node(1,"A", 1);
		 * 
		 * arvoreBinaria.inserirNaArvore(n1); arvoreBinaria.inserirNaArvore(n2);
		 * arvoreBinaria.inserirNaArvore(n3); arvoreBinaria.inserirNaArvore(n4);
		 * // arvoreBinaria.inserirNaArvore(n5); //
		 * arvoreBinaria.inserirNaArvore(n6); //
		 * arvoreBinaria.inserirNaArvore(13); //
		 * arvoreBinaria.inserirNaArvore(15); //
		 * arvoreBinaria.inserirNaArvore(11);
		 * 
		 * // arvoreBinaria.inserirNaArvore(9); //
		 * arvoreBinaria.inserirNaArvore(7); //
		 * arvoreBinaria.inserirNaArvore(6); //
		 * arvoreBinaria.inserirNaArvore(8); //
		 * arvoreBinaria.inserirNaArvore(11); //
		 * arvoreBinaria.inserirNaArvore(16); //
		 * arvoreBinaria.inserirNaArvore(14); //
		 * arvoreBinaria.inserirNaArvore(20); //
		 * arvoreBinaria.inserirNaArvore(17); //
		 * arvoreBinaria.inserirNaArvore(13); //
		 * arvoreBinaria.inserirNaArvore(1); //
		 * arvoreBinaria.inserirNaArvore(4); //
		 * arvoreBinaria.inserirNaArvore(12); //
		 * arvoreBinaria.inserirNaArvore(5); //
		 * arvoreBinaria.inserirNaArvore(2); //
		 * arvoreBinaria.inserirNaArvore(15); //
		 * arvoreBinaria.inserirNaArvore(3); //
		 * arvoreBinaria.inserirNaArvore(18); //
		 * arvoreBinaria.inserirNaArvore(19); //
		 * arvoreBinaria.inserirNaArvore(21);
		 * System.out.println("RAIZ: "+arvoreBinaria.getRaiz().getData());
		 * System.out.print("Parenteses aninhados: "); arvoreBinaria.imprimir();
		 * 
		 * // System.out.println("\n\n\nRemover"); //
		 * arvoreBinaria.removerNaArvore(7); // System.out.println(); //
		 * arvoreBinaria.removerNaArvore(13); // System.out.println(); //
		 * arvoreBinaria.removerNaArvore(21); // System.out.println(); //
		 * arvoreBinaria.removerNaArvore(8); // System.out.println(); //
		 * arvoreBinaria.removerNaArvore(9); // System.out.println(); //
		 * arvoreBinaria.removerNaArvore(4); // System.out.println(); //
		 * arvoreBinaria.removerNaArvore(15); // System.out.println(); //
		 * arvoreBinaria.removerNaArvore(19); // System.out.println(); //
		 * arvoreBinaria.removerNaArvore(6); // System.out.println(); //
		 * arvoreBinaria.removerNaArvore(11); // System.out.println(); //
		 * arvoreBinaria.removerNaArvore(5); // System.out.println(); //
		 * arvoreBinaria.removerNaArvore(14); // System.out.println(); //
		 * arvoreBinaria.removerNaArvore(3); // System.out.println(); //
		 * arvoreBinaria.removerNaArvore(16); // System.out.println(); //
		 * arvoreBinaria.removerNaArvore(1); // System.out.println(); //
		 * arvoreBinaria.removerNaArvore(18); // System.out.println(); //
		 * arvoreBinaria.removerNaArvore(2); // System.out.println(); //
		 * arvoreBinaria.removerNaArvore(20); // System.out.println();
		 * arvoreBinaria.imprimir();
		 * 
		 * System.out.println("\n\nIs Balanced? " +
		 * arvoreBinaria.isBalanceada()+"\n"); //
		 * System.out.println("Is perfectly balanced? " +
		 * arvoreBinaria.isBalanceada()+"\n");
		 * arvoreBinaria.listarNodesDaArvore();
		 */
	}

}
