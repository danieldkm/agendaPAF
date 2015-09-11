package com.unifil.agendapaf.view.controller;

import com.fasterxml.jackson.core.io.UTF32Reader;
import com.unifil.agendapaf.statics.StaticBoolean;
import com.unifil.agendapaf.util.huffman.JSONHuffman;
import com.unifil.agendapaf.util.huffman.Huffman;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Tab;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import javassist.bytecode.annotation.EnumMemberValue;

/**
 *
 * @author danielmorita
 */
public class FerramentaBDController extends FXMLController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        if (dialog == null) {
            dialog = new UtilDialog();
        }
        mainFerramentaBD.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent t) {
                if (t.getCode() == KeyCode.ESCAPE) {
                    stage.close();
                }
            }
        });
        lerJSONSistema("sistema.txt");
        System.out.println("LOCAL " + StaticBoolean.isLocal() + " SERVIDOR " + StaticBoolean.isServidor());
        if (StaticBoolean.isLocal()) {
            arquivoServidor = "conexaoLocal.txt";
            lerJSONConexao(arquivoServidor);
        } else if (StaticBoolean.isServidor()) {
            arquivoServidor = "conexao.txt";
            lerJSONConexao(arquivoServidor);
        }
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        try {
            stage = primaryStage;
            mainFerramentaBD = FXMLLoader.load(FXMLController.class.getResource(EnumCaminho.FerramentaBD.getCaminho()));
            Scene scene = new Scene(mainFerramentaBD);
            stage.setScene(scene);
            stage.setTitle("Ferramenta BD");
//            stage.setResizable(false);
//        stage.initOwner(this.myParent);
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();
            stage.toFront();
//            stage.getIcons().add(Controller.icoPAF);
            stage.setOnHidden(new EventHandler<WindowEvent>() {

                @Override
                public void handle(WindowEvent t) {
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            dialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro no start", e, "Exception:");
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
    private static Stage stage;
    private String arquivoServidor;
    private boolean local;

    private UtilDialog dialog;

    @FXML
    private void setOnActionBtnSalvar() {
        Optional<ButtonType> result = dialog.criarDialogConfirmacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Deseja salvar?");
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
            if (StaticBoolean.isSelectedLocal()) {
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

            if (StaticBoolean.isSelectedLocal()) {
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
            dialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Salvo.getMensagem());
        } catch (IOException ex) {
            Logger.getLogger(FerramentaBDController.class.getName()).log(Level.SEVERE, null, ex);
            dialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroSalvar + " o arquivo", ex, "Exception:");
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
            dialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Salvo.getMensagem());
        } catch (IOException ex) {
            Logger.getLogger(FerramentaBDController.class.getName()).log(Level.SEVERE, null, ex);
            dialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroSalvar + " o arquivo", ex, "Exception:");
        }

    }

}
