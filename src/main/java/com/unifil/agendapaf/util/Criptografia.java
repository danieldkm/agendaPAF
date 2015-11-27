package com.unifil.agendapaf.util;

import java.util.HashMap;

/**
 *
 * @author danielmorita
 */
public class Criptografia {

    String palavraCodificada;
    HashMap<Character, Integer> letraNumero = new HashMap<Character, Integer>();
    HashMap<Integer, Character> numeroLetra = new HashMap<Integer, Character>();

    public Criptografia() {
        letraNumero.put('a', 0);
        letraNumero.put('b', 1);
        letraNumero.put('c', 2);
        letraNumero.put('d', 3);
        letraNumero.put('e', 4);
        letraNumero.put('f', 5);
        letraNumero.put('g', 6);
        letraNumero.put('h', 7);
        letraNumero.put('i', 8);
        letraNumero.put('j', 9);
        letraNumero.put('k', 10);
        letraNumero.put('l', 11);
        letraNumero.put('m', 12);
        letraNumero.put('n', 13);
        letraNumero.put('o', 14);
        letraNumero.put('p', 15);
        letraNumero.put('q', 16);
        letraNumero.put('r', 17);
        letraNumero.put('s', 18);
        letraNumero.put('t', 19);
        letraNumero.put('u', 20);
        letraNumero.put('v', 21);
        letraNumero.put('w', 22);
        letraNumero.put('x', 23);
        letraNumero.put('y', 24);
        letraNumero.put('z', 25);

        numeroLetra.put(0, 'a');
        numeroLetra.put(1, 'b');
        numeroLetra.put(2, 'c');
        numeroLetra.put(3, 'd');
        numeroLetra.put(4, 'e');
        numeroLetra.put(5, 'f');
        numeroLetra.put(6, 'g');
        numeroLetra.put(7, 'h');
        numeroLetra.put(8, 'i');
        numeroLetra.put(9, 'j');
        numeroLetra.put(10, 'k');
        numeroLetra.put(11, 'l');
        numeroLetra.put(12, 'm');
        numeroLetra.put(13, 'n');
        numeroLetra.put(14, 'o');
        numeroLetra.put(15, 'p');
        numeroLetra.put(16, 'q');
        numeroLetra.put(17, 'r');
        numeroLetra.put(18, 's');
        numeroLetra.put(19, 't');
        numeroLetra.put(20, 'u');
        numeroLetra.put(21, 'v');
        numeroLetra.put(22, 'w');
        numeroLetra.put(23, 'x');
        numeroLetra.put(24, 'y');
        numeroLetra.put(25, 'z');

    }

    // Cifra de Cesar
    public String criptografar(String palavra, int chave) {
        int k = 0;
        String texto = "";
        char[] letras = palavra.toLowerCase().toCharArray();
        for (int i = 0; i < letras.length; i++) {
            if (letraNumero.get(letras[i]) != null) {
                k = letraNumero.get(letras[i]) + chave;
                while (k > 25) {
                    k = k - 26;
                }
                texto += numeroLetra.get(k);
            } else {
                texto += letras[i];
            }
        }
        return texto;
    }

    // Cifra de Cesar
    public String descriptografar(String palavra, int chave) {
        int k = 0;
        String texto = "";
        char[] letras = palavra.toLowerCase().toCharArray();
        for (int i = 0; i < letras.length; i++) {
            if (letraNumero.get(letras[i]) != null) {
                k = letraNumero.get(letras[i]) - chave;
                while (k < 0) {
                    k = k + 26;
                }
                texto += numeroLetra.get(k);
            } else {
                texto += letras[i];
            }
        }
        return texto;
    }

    public String vigenereCifrar(String palavra, String chave) {
        String texto = "";
        char[] chaveAux = chave.toLowerCase().toCharArray();

        for (int i = 0; i < chave.length() - 1; i++) {
            chave = chave + chaveAux[i];
            if (chave.length() == (palavra.length())) {
                break;
            }
        }
        System.out.println("Chave: " + chave);
        System.out.println("Palavra: " + palavra);
        int p;
        int k;
        int c;
        char[] listaPalavra = palavra.toLowerCase().toCharArray();
        char[] listaChave = chave.toLowerCase().toCharArray();
        for (int i = 0; i < (palavra.length()); i++) {
            System.out.println("listaPalavra[i] " + listaPalavra[i]);
            p = (letraNumero.get(listaPalavra[i]) == null ? 0 : letraNumero.get(listaPalavra[i]));
            k = letraNumero.get(listaChave[i]);
            c = p + k;
            if (c > 25) {
                c = c - 26;

            } else if (c < 0) {
                c = c + 26;

            }
            texto = texto + numeroLetra.get(c);
        }
        return texto;
    }

    public String cifrarVigenere(String palavra, String chave) {
        String texto = "";
        char[] chaveAux = chave.toLowerCase().toCharArray();
        int tamanhoChave = chave.length();
        String novaPalavra = "";
        char[] listaPalavra = palavra.toLowerCase().toCharArray();
        for (int i = 0; i < listaPalavra.length; i++) {
            if (letraNumero.get(listaPalavra[i]) != null) {
                novaPalavra += listaPalavra[i];
            }
        }
        while (chave.length() < (novaPalavra.length() - 1)) {
            for (int i = 0; i < tamanhoChave; i++) {
                chave = chave + chaveAux[i];
                if (chave.length() == (novaPalavra.length())) {
                    break;
                }
            }
        }
        int c;
        int k;
        int p;
        listaPalavra = palavra.toLowerCase().toCharArray();
        char[] listaNovaPalavra = novaPalavra.toLowerCase().toCharArray();
        char[] listaChave = chave.toLowerCase().toCharArray();
        int auxI = 0;
        for (int i = 0; i < listaPalavra.length; i++) {
            if (letraNumero.get(listaPalavra[i]) == null) {
                texto = texto + listaPalavra[i];
            } else {
                if (letraNumero.get(listaNovaPalavra[auxI]) != null) {
                    p = letraNumero.get(listaNovaPalavra[auxI]);
                    k = letraNumero.get(listaChave[auxI]);
                    c = p + k;
                    if (c > 25) {
                        c = c - 26;
                    } else if (c < 0) {
                        c = c + 26;
                    }
                    texto = texto + numeroLetra.get(c);
                    auxI++;
                } else {
                    System.out.println("nao devia entrar aki");
                }
            }
        }
        return texto;
    }

    public String decifrarVigenere(String palavra, String chave) {
        String texto = "";
        char[] chaveAux = chave.toLowerCase().toCharArray();
        int tamanhoChave = chave.length();
        String novaPalavra = "";
        char[] listaPalavra = palavra.toLowerCase().toCharArray();
        for (int i = 0; i < listaPalavra.length; i++) {
            if (letraNumero.get(listaPalavra[i]) != null) {
                novaPalavra += listaPalavra[i];
            }
        }
        while (chave.length() < (novaPalavra.length() - 1)) {
            for (int i = 0; i < tamanhoChave; i++) {
                chave = chave + chaveAux[i];
                if (chave.length() == (novaPalavra.length())) {
                    break;
                }
            }
        }
        int c;
        int k;
        int p;
        listaPalavra = palavra.toLowerCase().toCharArray();
        char[] listaNovaPalavra = novaPalavra.toLowerCase().toCharArray();
        char[] listaChave = chave.toLowerCase().toCharArray();
        int auxI = 0;
        for (int i = 0; i < listaPalavra.length; i++) {
            if (letraNumero.get(listaPalavra[i]) == null) {
                texto = texto + listaPalavra[i];
            } else {
                if (letraNumero.get(listaNovaPalavra[auxI]) != null) {
                    c = letraNumero.get(listaNovaPalavra[auxI]);
                    k = letraNumero.get(listaChave[auxI]);
                    p = c - k + 26;
                    if (p > 25) {
                        p = p - 26;
                    } else if (p < 0) {
                        p = p + 26;
                    }
                    texto = texto + numeroLetra.get(p);
                    auxI++;
                } else {
                    System.out.println("nao devia entrar aki");
                }
            }
        }
        return texto;
    }
}
