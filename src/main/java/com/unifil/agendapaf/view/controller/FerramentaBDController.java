package com.unifil.agendapaf.view.controller;

import com.unifil.agendapaf.SceneManager;
import com.unifil.agendapaf.util.mensagem.Dialogos;
import com.unifil.agendapaf.util.huffman.JSONHuffman;
import com.unifil.agendapaf.util.huffman.Huffman;
import com.unifil.agendapaf.util.mensagem.Mensagem;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 *
 * @author danielmorita
 */
public class FerramentaBDController {

    /**
     * Initializes the controller class.
     */
    @FXML
    public void initialize() {
        mensagem = new Mensagem(stage);
        sceneManager = SceneManager.getInstance();
        lerJSONSistema("sistema.txt");
        if (sceneManager.getBDLOCAL()) {
            arquivoServidor = "conexaoLocal.txt";
            lerJSONConexao(arquivoServidor);
        } else if (sceneManager.getBDSERVIDOR()) {
            arquivoServidor = "conexao.txt";
            lerJSONConexao(arquivoServidor);
        }
    }

    @FXML
    private TextField txtIp;
    @FXML
    private TextField txtNomeBD;
    @FXML
    private TextField txtUser;
    @FXML
    private TextField txtPass;
    @FXML
    private TextField txtEmail;
    @FXML
    private TextField txtSenha;
    @FXML
    private CheckBox cboxSincronizacao;
    @FXML
    private VBox mainFerramentaBD;
    @FXML
    private Tab tabSistema;
    @FXML
    private Tab tabBd;
    private Stage stage;
    private String arquivoServidor;
//    private boolean local;
    private SceneManager sceneManager;
    private Mensagem mensagem;

    @FXML
    private void setOnActionBtnSalvar() {
        Dialogos d = new Dialogos(stage);
        Optional<ButtonType> result = d.confirmacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Deseja salvar?");
        if (result.get() == ButtonType.OK) {
            if (tabSistema.isSelected()) {
                criarJSONSistema("sistema.txt");
            } else {
                criarJSONConexao(arquivoServidor);
            }
            stage.close();
        }
    }

    @FXML
    private void setOnActionBtnLimpar() {
        txtIp.setText("");
        txtNomeBD.setText("");
        txtPass.setText("");
        txtUser.setText("");
        txtEmail.setText("");
        txtSenha.setText("");
    }

    private boolean lerJSONConexao(String nomeArquivo) {
        try {
            BufferedReader buf = new BufferedReader(new FileReader(nomeArquivo));
            String line = buf.readLine();
            JSONHuffman j = new JSONHuffman();
            String tipoConexao = "";
            if (sceneManager.getBDLOCAL()) {
                tipoConexao = "conexaoLocal.json";
            } else {
                tipoConexao = "conexao.json";
            }

            while (line != null) {
                String a[] = line.split("[-]");
                switch (a[0]) {
                    case "ip":
                        txtIp.setText(j.getFraseJSON(a[1].trim(), "ip", tipoConexao));
                        break;
                    case "nomeDataBase":
                        txtNomeBD.setText(j.getFraseJSON(a[1].trim(), "nomeDataBase", tipoConexao));
                        break;
                    case "user":
                        txtUser.setText(j.getFraseJSON(a[1].trim(), "user", tipoConexao));
                        break;
                    case "pass":
                        txtPass.setText(j.getFraseJSON(a[1].trim(), "pass", tipoConexao));
                        break;
                }
                line = buf.readLine();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private void criarJSONConexao(String nomeArquivo) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo));
            PrintWriter writer = new PrintWriter(bw);

            Huffman ip = new Huffman(txtIp.getText());
            Huffman nomeBD = new Huffman(txtNomeBD.getText());
            Huffman user = new Huffman(txtUser.getText());
            Huffman pass = new Huffman(txtPass.getText());

            String tipoConexao = "";

            if (sceneManager.getBDLOCAL()) {
                tipoConexao = "conexaoLocal.json";
            } else {
                tipoConexao = "conexao.json";
            }

            JSONHuffman j = new JSONHuffman();
            j.salvarArquivoJSON("ip", ip.getHuffmanNode(), tipoConexao, false);
            j.salvarArquivoJSON("nomeDataBase", nomeBD.getHuffmanNode(), tipoConexao, true);
            j.salvarArquivoJSON("user", user.getHuffmanNode(), tipoConexao, true);
            j.salvarArquivoJSON("pass", pass.getHuffmanNode(), tipoConexao, true);

            writer.println("ip- " + ip.getBinario());
            writer.println("nomeDataBase- " + nomeBD.getBinario());
            writer.println("user- " + user.getBinario());
            writer.println("pass- " + pass.getBinario());
            writer.flush();
            writer.close();
            bw.close();
            mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Salvo.getMensagem());
        } catch (IOException ex) {
            Logger.getLogger(FerramentaBDController.class.getName()).log(Level.SEVERE, null, ex);
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroSalvar + " o arquivo", ex);
        }

    }

    private boolean lerJSONSistema(String nomeArquivo) {
        try {
//            BufferedReader buf = new BufferedReader(new FileReader("conexao.txt"));
            BufferedReader buf = new BufferedReader(new FileReader(nomeArquivo));
            String line = buf.readLine();
            JSONHuffman j = new JSONHuffman();
            while (line != null) {
                String a[] = line.split("[-]");
                switch (a[0]) {
                    case "sincronizacao":
                        if (j.getFraseJSON(a[1].trim(), "sincronizacao", "sistema.json").equals("true")) {
                            cboxSincronizacao.setSelected(true);
                        } else {
                            cboxSincronizacao.setSelected(false);
                        }
                        break;
                    case "email":
                        txtEmail.setText(j.getFraseJSON(a[1].trim(), "email", "sistema.json"));
                        break;
                    case "senha":
                        txtSenha.setText(j.getFraseJSON(a[1].trim(), "senha", "sistema.json"));
                        break;
                }
                line = buf.readLine();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

    private void criarJSONSistema(String nomeArquivo) {
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(nomeArquivo));
            PrintWriter writer = new PrintWriter(bw);
            JSONHuffman j = new JSONHuffman();
            if (cboxSincronizacao.isSelected()) {
                Huffman sinc = new Huffman("true");
                writer.println("sincronizacao- " + sinc.getBinario());
                j.salvarArquivoJSON("sincronizacao", sinc.getHuffmanNode(), "sistema.json", false);
            } else {
                Huffman sinc = new Huffman("false");
                writer.println("sincronizacao- " + sinc.getBinario());
                j.salvarArquivoJSON("sincronizacao", sinc.getHuffmanNode(), "sistema.json", false);
            }

            Huffman email = new Huffman(txtEmail.getText());
            Huffman senha = new Huffman(txtSenha.getText());

            j.salvarArquivoJSON("email", email.getHuffmanNode(), "sistema.json", true);
            j.salvarArquivoJSON("senha", senha.getHuffmanNode(), "sistema.json", true);

            writer.println("email- " + email.getBinario());
            writer.println("senha- " + senha.getBinario());
            writer.flush();
            writer.close();
            bw.close();
            mensagem.informacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Salvo.getMensagem());
        } catch (IOException ex) {
            Logger.getLogger(FerramentaBDController.class.getName()).log(Level.SEVERE, null, ex);
            mensagem.erro(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroSalvar + " o arquivo", ex);
        }

    }

    public void setMainFerramentaBD(VBox mainFerramentaBD) {
        this.mainFerramentaBD = mainFerramentaBD;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

}
