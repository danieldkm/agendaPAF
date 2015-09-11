package com.unifil.agendapaf.util.huffman;

import com.unifil.agendapaf.util.huffman.ArvoreBinaria;
import com.unifil.agendapaf.util.huffman.Node;
import java.util.ArrayList;

/**
 *
 * @author danielmorita
 */
public class Huffman {

//    public static void main(String[] args) {
//        Huffman h = new Huffman("minha custela ta doendo");
//    }

    private String frase;
    private String binario;
    private char[] letras;
    private ArrayList<Node> inicialNode;
    private ArrayList<Node> huffmanNode;
    private ArvoreBinaria raiz;

    public Huffman(String frase) {
        this.frase = frase;
        inicialNode = new ArrayList<Node>();
        huffmanNode = new ArrayList<Node>();
        raiz = new ArvoreBinaria();
        letras = frase.toCharArray();
        separarFrase(frase);
        iniciarCodigoHuffman();
        raiz.configurarBinario(raiz.getRaiz(), "");
        criarBinario();
        preencherHuffmanNode(raiz.getRaiz());
//        json.salvarArquivoJSON(huffmanNode);
    }

    public ArrayList<Node> getHuffmanNode() {
        return huffmanNode;
    }

    public void setHuffmanNode(ArrayList<Node> huffmanNode) {
        this.huffmanNode = huffmanNode;
    }
    
    public String getBinario() {
        return binario;
    }

    //fu��o retorna a frase que estava em binario
    public String getPalavraRecovertido() {
        raiz.setPalavraWithBinario(raiz.getRaiz(), binario);
        return raiz.getPalavra();
    }

    private void preencherHuffmanNode(Node node) {
        if (node != null) {
            preencherHuffmanNode(node.getEsquerda());
            if (node.getData() != null) {
                huffmanNode.add(node);
            }
            preencherHuffmanNode(node.getDireita());
        }
    }

    private void criarBinario() {
        //criar binario do huffman da frase completo
//        System.out.println("FRASE " + frase);
        char[] letra = frase.toCharArray();
        // preencher a palavra em binario
        binario = "";
        for (char c : letra) {
            raiz.setBinarioData(raiz.getRaiz(), c + "");
//            System.out.println("BIN " + raiz.getBin());
            binario += raiz.getBin();
        }
//        System.out.println("BINDARIO " + binario);
    }

    private void separarFrase(String frase) {
        int x = 1;
        for (int i = 0; i < letras.length; i++) {
            for (int j = i + 1; j < letras.length; j++) {
                if (letras[i] == letras[j] && letras[i] != 0) {
                    letras[j] = 0;
                    x += 1;
                }
            }
            if (letras[i] != 0) {
                double x2 = Double.parseDouble(x + "") / frase.length();
                inicialNode.add(new Node(x, letras[i] + "", x2));
            }
            x = 1;
        }
    }

    private void iniciarCodigoHuffman() {
        ordenar(inicialNode);
        while (inicialNode.size() > 1) {
            inicialNode = ordenar(inicialNode);
            if (inicialNode.get(0).getValor() <= inicialNode.get(1).getValor()) {
                Node n = new Node(inicialNode.get(0).getQuantidade() + inicialNode.get(1).getQuantidade(), null, inicialNode.get(0).getValor()
                        + inicialNode.get(1).getValor());
                ArvoreBinaria ar = new ArvoreBinaria();
                ar.inserirNaArvore(n);
                if (inicialNode.get(0).getValor() <= inicialNode.get(1).getValor()) {
                    ar.inserirNaArvore(inicialNode.get(0));
                    ar.inserirNaArvore(inicialNode.get(1));
                } else {
                    ar.inserirNaArvore(inicialNode.get(1));
                    ar.inserirNaArvore(inicialNode.get(0));
                }
                inicialNode.remove(0);
                inicialNode.remove(0);
                inicialNode.add(0, ar.getRaiz());
                raiz.setRaiz(ar.getRaiz());
                ar.imprimir();
//                huffmanNode.add(new Node(n.getQuantidade(), ar.getPrint(), n.getValor()));
            }
        }
    }

    private ArrayList<Node> ordenar(ArrayList<Node> ns) {
        Node aux;
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

}
