package com.unifil.agendapaf.util.huffman;

public class ArvoreBinaria {

    Node raiz;

    public ArvoreBinaria() {
        raiz = null;
    }

    public void inserirNaArvore(Node valor) {
        if (raiz == null) {
            // raiz = new Node(valor);
            raiz = valor;
        } else {
            inserirNovo(raiz, valor);
        }
    }

    private void inserirNovo(Node node, Node valor) {
        if (node.getEsquerda() == null) {
            node.setEsquerda(valor);
            node.getEsquerda().setBit(0);
        } else {
            node.setDireita(valor);
            node.getDireita().setBit(1);
        }
    }

    private String print;
    private String bin;

    public String getPrint() {
        return print;
    }

    public String getBin() {
        return bin;
    }

    // Função que analisa a arvore e adiciona o binario para cada folha
    public void configurarBinario(Node node, String bin) {
        if (node != null) {
            bin += node.getBit() + "";
            configurarBinario(node.getEsquerda(), bin);
            if (node.getData() != null) {
//                System.out.println("bn " + bin.substring(1));
//                System.out.println("kc " + node.getData());
                node.setBin(bin.substring(1));
                bin = "";
            }
            configurarBinario(node.getDireita(), bin);
        }
    }

    // Função para encontrar o binario do valor passado @data
    public void setBinarioData(Node node, String data) {
        if (node != null) {
            setBinarioData(node.getEsquerda(), data);
            if (node.getData() != null) {
                if (data.equals(node.getData())) {
                    bin = node.getBin();
                }
            }
            setBinarioData(node.getDireita(), data);
        }
    }

    private int bit;
    private String palavra = "";

    //Funcao busca atravez da variavel binario para percorrer a arvore ate encontrar o valor concatenando na variavel palavra
    public void setPalavraWithBinario(Node node, String binario) {
        if (node != null) {
//			System.out.println("BINARIO " + binario);
            if (binario.length() > 0) {
                bit = Integer.parseInt(binario.substring(0, 1));
//				System.out.println("BIT " + bit);
//				System.out.println("Palavra " + palavra);
                if (node.getEsquerda() == null) {
                    setPalavraWithBinario(raiz, bit + binario.substring(1));
                } else {
//					System.out.println("E " + node.getEsquerda().getBit()
//							+ " b " + bit);
//					System.out.println("D " + node.getDireita().getBit()
//							+ " b " + bit);
                    if (node.getEsquerda().getBit() == bit) {
                        if (node.getEsquerda().getData() != null) {
                            palavra += node.getEsquerda().getData();
                        }
                        setPalavraWithBinario(node.getEsquerda(),
                                binario.substring(1));
                    } else {
                        if (node.getDireita().getData() != null) {
                            palavra += node.getDireita().getData();
                        }
                        setPalavraWithBinario(node.getDireita(),
                                binario.substring(1));
                    }
                }
            }
        }
    }

    public String getPalavra() {
        return palavra;
    }

    private void imprimir(Node node) {
        if (node != null) {
            if (node.getEsquerda() != null) {
                System.out.print("(");
                print += "(";
            }
            imprimir(node.getEsquerda());
            if (node.getData() != null) {
                System.out.print(node.getData());
//				System.out.print(" qtd " + node.getQuantidade());
                print += node.getData();
            }
            imprimir(node.getDireita());
            if (node.getDireita() != null) {
                System.out.print(")");
                print += ")";
            } else {
				// print += ",";
                // System.out.print(",");
            }
        }
    }

    private void inserir(Node node, Node valor) {
		// Verifica se o valor a ser inserido é menor que o nodo corrente da
        // árovre, se sim vai para subarvore esquerda
        if (valor.getValor() < node.getValor()) {
            // Se tiver elemento no nodo esquerdo continua a busca
            if (node.getEsquerda() != null) {
                inserir(node.getEsquerda(), valor);
                System.out.println("node esquerda tem valor "
                        + node.getEsquerda().getValor());
            } else {
                // Se nodo esquerdo vazio insere o novo nodo aqui
                System.out.println("  Inserindo " + valor + " a esquerda de "
                        + node.getValor());
                node.setEsquerda(valor);
            }
			// Verifica se o valor a ser inserido é maior que o nodo corrente
            // da árvore, se sim vai para subarvore direita
        } else if (valor.getValor() > node.getValor()) {
            // Se tiver elemento no nodo direito continua a busca
            if (node.getDireita() != null) {
                inserir(node.getDireita(), valor);
                System.out.println("node direita tem valor "
                        + node.getDireita().getValor());
            } else {
                // Se nodo direito vazio insere o novo nodo aqui
                System.out.println("  Inserindo " + valor + " a direita de "
                        + node.getValor());
                node.setDireita(valor);
            }
        } else {
            System.out.println(valor + " já existe na arvore");
        }
    }

    public void imprimir() {
        print = "";
        imprimir(raiz);
        System.out.println();
    }

	// private void imprimir2(Node node) {
    // if (node != null) {
    // System.out.print("(");
    // imprimir(node.getEsquerda());
    // System.out.print(node.getValor());
    // imprimir(node.getDireita());
    // System.out.print(")");
    // }
    // }
    public void removerNaArvore(int valor) {
        if (raiz == null) {
            raiz = new Node(valor);
        } else {
            remover(raiz, valor);
        }
    }

    private void remover(Node node, int valor) {
        if (valor < node.getValor()) {
            if (node.getEsquerda().getValor() == valor) {
                /*
                 * se o node pela esquerda.esquerda dele for diferente de nullo
                 * e o node pela esquerda.direita dele for diferente de nullo
                 * ent�o: criar dois nodes esquerda e direita, recebendo seus
                 * nodes correspondentes e o node pela esquerda recebe valor
                 * nullo chama o metodo inserir2 para realocar os nodes esquerda
                 * e direita novamente
                 */
                if (node.getEsquerda().getEsquerda() != null
                        && node.getEsquerda().getDireita() != null) {
                    Node esquerda = node.getEsquerda().getEsquerda();
                    Node direita = node.getEsquerda().getDireita();
                    node.setEsquerda(null);
                    inserir2(raiz, esquerda);
                    inserir2(raiz, direita);
                } else if (node.getEsquerda().getEsquerda() != null) {
                    /*
                     * se apenas o node pela esquerda.esquerda for nullo ent�o
                     * fazer os mesmos passos do anteriror porem apenas para o
                     * node da esquerda.esquerda!
                     */
                    Node esquerda = node.getEsquerda().getEsquerda();
                    node.setEsquerda(null);
                    inserir2(raiz, esquerda);
                } else if (node.getEsquerda().getDireita() != null) {
                    /*
                     * se apenas o node pela esquerda.direita for nullo ent�o
                     * fazer os mesmos passos do primeiro if porem apenas para o
                     * node da esquerda.direita!
                     */
                    Node direita = node.getEsquerda().getDireita();
                    node.setEsquerda(null);
                    inserir2(raiz, direita);
                } else { // caso for nullo para os ambos os lados, simplesmente
                    // setar nullo
                    node.setEsquerda(null);
                }
            } else {
                System.out.println("A esquerda da raiz, Valor "
                        + node.getEsquerda().getValor() + " � diferente de "
                        + valor);
                remover(node.getEsquerda(), valor);
            }
        } else if (valor > node.getValor()) {
            if (node.getDireita().getValor() == valor) {
                if (node.getDireita().getEsquerda() != null
                        && node.getDireita().getDireita() != null) {
                    Node esquerda = node.getDireita().getEsquerda();
                    Node direita = node.getDireita().getDireita();
                    node.setDireita(null);
                    inserir2(raiz, esquerda);
                    inserir2(raiz, direita);
                } else if (node.getDireita().getEsquerda() != null) {
                    Node esquerda = node.getDireita().getEsquerda();
                    node.setDireita(null);
                    inserir2(raiz, esquerda);
                } else if (node.getDireita().getDireita() != null) {
                    Node direita = node.getDireita().getDireita();
                    node.setDireita(null);
                    inserir2(raiz, direita);
                } else {
                    node.setDireita(null);
                }
            } else {
                System.out.println("A direita da raiz, Valor "
                        + node.getDireita().getValor() + " � diferente de "
                        + valor);
                remover(node.getDireita(), valor);
            }
        }
    }

    private void inserir2(Node node, Node valor) {
        if (valor.getValor() < node.getValor()) {
            if (node.getEsquerda() != null) {
                inserir2(node.getEsquerda(), valor);
                System.out.println("node esquerda tem valor "
                        + node.getEsquerda().getValor());
            } else {
                System.out.println("  Inserindo node " + valor.getValor()
                        + " a esquerda de " + node.getValor());
                node.setEsquerda(valor);
            }
        } else if (valor.getValor() > node.getValor()) {
            if (node.getDireita() != null) {
                inserir2(node.getDireita(), valor);
                System.out.println("node direita tem valor "
                        + node.getDireita().getValor());
            } else {
                System.out.println("  Inserindo node " + valor.getValor()
                        + " a direita de " + node.getValor());
                node.setDireita(valor);
            }
        } else {
            System.out.println(valor + " j� existe na arvore");
        }
    }

    public void prefixado(Node no) {
        if (no != null) {
            System.out.print(no.getValor() + " ");
            prefixado(no.getEsquerda());
            prefixado(no.getDireita());
        }
    }

    public void posfixado(Node no) {
        if (no != null) {
            posfixado(no.getEsquerda());
            posfixado(no.getDireita());
            System.out.print(no.getValor() + " ");
        }
    }

    public void emordem(Node no) {
        if (no != null) {
            emordem(no.getEsquerda());
            System.out.print(no.getValor() + " ");
            emordem(no.getDireita());
        }
    }

    public boolean isPerfeitamenteBalanceada() {
        Node aux = raiz;
        percorreRaiz(aux);
        return perfeitamenteBalanceada;
    }

    public boolean isBalanceada() {
        setPercorrer(raiz.getEsquerda());
        setPercorrer(raiz.getDireita());
        return balanceada;
    }

    int esquerda = 0;
    int direita = 0;
    int pEsquerda = 0;
    int pDireita = 0;

    private void setPercorrer(Node no) {
        if (no != null) {
            System.out.println(no.getValor() + " valor do no");
            if (no.getEsquerda() != null) {
                esquerda = height(no.getEsquerda());
                setPercorrer(no.getEsquerda());
            }
            if (no.getDireita() != null) {
                direita = height(no.getDireita());
                setPercorrer(no.getDireita());
            }
            System.out.println("Es " + esquerda + " dir" + direita + " soma "
                    + (esquerda - direita));
            if ((esquerda - direita) > 1 || (esquerda - direita) < -1) {
                if (balanceada) {
                    balanceada = false;
                }
            }
        }
    }

    private int height(Node n) {
        int u, v;
        if (n == null) {
            return -1;
        }
        u = height(n.getEsquerda());
        v = height(n.getDireita());
        if (u > v) {
            return u + 1;
        } else {
            return v + 1;
        }
    }

    public void percorreRaiz(Node no) {
        // System.out.println("valor do " + no.getValor());
        if (no != null) {
            if (no.getEsquerda() != null) {
                verificaRaiz(no.getEsquerda());
                percorreRaiz(no.getEsquerda());
                pEsquerda++;
            }
            if (no.getDireita() != null) {
                verificaRaiz(no.getDireita());
                percorreRaiz(no.getDireita());
                pDireita++;
            }
        }

        if ((pEsquerda - pDireita) > 1 || (pEsquerda - pDireita) < -1) {
            perfeitamenteBalanceada = false;
        }
    }

    boolean balanceada = true;
    boolean perfeitamenteBalanceada = true;

    private void verificaRaiz(Node no) {
        Node a = no;
        esquerda = 0;
        direita = 0;
        while (a != null) {
            if (a.getEsquerda() != null) {
                esquerda++;
                a = a.getEsquerda();
            } else {
                if (a.getDireita() != null) {
                    a = a.getDireita();
                } else {
                    a = a.getEsquerda();
                }
            }
        }

        a = no;
        while (a != null) {
            if (a.getDireita() != null) {
                direita++;
                a = a.getDireita();
            } else {
                if (a.getEsquerda() != null) {
                    a = a.getEsquerda();
                } else {
                    a = a.getDireita();
                }
            }
        }

        if ((esquerda - direita) > 1 || (esquerda - direita) < -1) {
            balanceada = false;
        }

    }

    public void listarNodesDaArvore() {
        System.out.println("N�  |  Grau  |  N�vel ");
        if (raiz.getDireita() != null) {
            grau++;
        }
        if (raiz.getEsquerda() != null) {
            grau++;
        }
        System.out.println(raiz.getValor() + "  |  " + grau + "     |   "
                + nivel);
        grau = 0;

        percorreNodesDaAvore(raiz);
    }

    int grau = 0;
    int nivel = 0;

    private void getNivelDaArvore(Node no, double valor) {
        if (no.getValor() != valor) {
            if (valor < no.getValor()) {
                nivel++;
                getNivelDaArvore(no.getEsquerda(), valor);
            } else if (valor > no.getValor()) {
                nivel++;
                getNivelDaArvore(no.getDireita(), valor);
            }
        }
    }

    private void percorreNodesDaAvore(Node no) {
        if (no != null) {
            if (no.getEsquerda() != null) {
                if (no.getEsquerda().getEsquerda() != null) {
                    grau++;
                }
                if (no.getEsquerda().getDireita() != null) {
                    grau++;
                }
                getNivelDaArvore(raiz, no.getEsquerda().getValor());
                System.out.println(no.getEsquerda().getValor() + "  |  " + grau
                        + "     |   " + nivel);
                nivel = 0;
                grau = 0;
                percorreNodesDaAvore(no.getEsquerda());
            }

            if (no.getDireita() != null) {
                if (no.getDireita().getDireita() != null) {
                    grau++;
                }
                if (no.getDireita().getEsquerda() != null) {
                    grau++;
                }
                getNivelDaArvore(raiz, no.getDireita().getValor());
                System.out.println(no.getDireita().getValor() + "  |  " + grau
                        + "     |   " + nivel);
                nivel = 0;
                grau = 0;
                percorreNodesDaAvore(no.getDireita());
            }
        }
    }

    public Node getRaiz() {
        return raiz;
    }

    public void setRaiz(Node raiz) {
        this.raiz = raiz;
    }

}
