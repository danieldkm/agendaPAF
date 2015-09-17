package com.unifil.agendapaf.dao.conexao;

import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.util.huffman.JSONHuffman;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

public class Conexao {

    public static String message;
    private static UtilDialog utilDialog = new UtilDialog();
    private String ip;
    private String user;
    private String pass;
    private String nomeDataBase;
//    private static String ip = "192.168.8.21";
////    private static String ip = "localhost";
//    private static String user = "SYSDBA";
//    private static String pass = "masterkey";
//    //?useUnicode=true&characterEncoding=utf-8
////    ?defaultResultSetHoldable=True
////    private static String nomeDataBase = "C:/Users/npi/Desktop/IBExpert.2012.02.21.1/temp/AGENDAPAF_ECF.FDB";
//    private static String nomeDataBase = "C:/Users/Administrador/Documents/firebird/AGENDAPAF_ECF.FDB";

    private static class UnicaConexao {

        private static Connection con = new Conexao().getConnection();
    }

    public static Connection getInstance() {
        UnicaConexao.con = new Conexao().getConnection();
        if (UnicaConexao.con == null) {
            utilDialog.criarDialogWarning(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro: não foi possivel conectar com o BD");
        }
        return UnicaConexao.con;
    }

    private Conexao() {
    }

    private Connection getConnection() {
        Connection con = null;
        try {
            if (lerTxtConexao()) {
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://" + ip + ":3306/" + nomeDataBase, user, pass);
            } else {
                System.out.println("ERRO AO LER O ARQUIVO ~CONEXAOLOCAL.TXT~");
            }
        } catch (Exception sq) {
            sq.printStackTrace();
        }
        return con;
    }

    private boolean lerTxtConexao() {
        try {
            BufferedReader buf = new BufferedReader(new FileReader("conexao.txt"));
            String line = buf.readLine();
            JSONHuffman j = new JSONHuffman();
            while (line != null) {
                String a[] = line.split("[-]");
                switch (a[0]) {
                    case "ip":
                        ip = j.getFraseJSON(a[1].trim(), "ip", "conexao.json");
                        break;
                    case "nomeDataBase":
                        nomeDataBase = j.getFraseJSON(a[1].trim(), "nomeDataBase", "conexao.json");
                        break;
                    case "user":
                        user = j.getFraseJSON(a[1].trim(), "user", "conexao.json");
                        break;
                    case "pass":
                        pass = j.getFraseJSON(a[1].trim(), "pass", "conexao.json");
                        break;
                }
                line = buf.readLine();
            }
            System.out.println("ip servidor: " + ip);
            System.out.println("nome servidor: " + nomeDataBase);
            System.out.println("usuario servidor: " + user);
            System.out.println("senha servidor: " + pass);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }

}
