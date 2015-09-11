/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unifil.agendapaf.dao.conexao;

import com.unifil.agendapaf.statics.StaticBoolean;
import com.unifil.agendapaf.util.huffman.JSONHuffman;
import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author danielmorita
 */
public class ConexaoLocal {

    private String ip;
    private String user;
    private String pass;
    private String nomeDataBase;

    private static class UnicaConexao {

        private static Connection con = new ConexaoLocal().getConnection();
    }

    public static Connection getInstance() {
        return UnicaConexao.con;
    }

    private ConexaoLocal() {
    }

    private Connection getConnection() {
        Connection con = null;
        try {
            if (lerTxtConexao()) {
                System.out.println("jdbc:mysql://localhost:3306/" + nomeDataBase + "\nusuario: " + user + " senha: " + pass);
                Class.forName("com.mysql.jdbc.Driver");
                con = DriverManager.getConnection("jdbc:mysql://" + ip + ":3306/" + nomeDataBase, user, pass);
                if (StaticBoolean.isDbServidor()) {
                    StaticBoolean.setDbServidor(false);
                }
                if (!StaticBoolean.isDbLocal()) {
                    StaticBoolean.setDbLocal(true);
                }
                erro = false;
            } else {
                System.out.println("ERRO AO LER O ARQUIVO ~CONEXAOLOCAL.TXT~");
            }
        } catch (Exception sq) {
            sq.printStackTrace();
            erro = true;
            StaticBoolean.setDbLocal(false);
        }
        return con;
    }

    private static boolean erro = false;

    public static boolean getError() {
        return erro;
    }

    private boolean lerTxtConexao() {
        try {
            BufferedReader buf = new BufferedReader(new FileReader("conexaoLocal.txt"));
            String line = buf.readLine();
            JSONHuffman j = new JSONHuffman();
            while (line != null) {
                String a[] = line.split("[-]");
                switch (a[0]) {
                    case "ip":
                        ip = j.getFraseJSON(a[1].trim(), "ip", "conexaoLocal.json");
                        break;
                    case "nomeDataBase":
                        nomeDataBase = j.getFraseJSON(a[1].trim(), "nomeDataBase", "conexaoLocal.json");
                        break;
                    case "user":
                        user = j.getFraseJSON(a[1].trim(), "user", "conexaoLocal.json");
                        break;
                    case "pass":
                        pass = j.getFraseJSON(a[1].trim(), "pass", "conexaoLocal.json");
                        break;
                }
                line = buf.readLine();
            }
            System.out.println("ip local: " + ip);
            System.out.println("nome local: " + nomeDataBase);
            System.out.println("usuario local: " + user);
            System.out.println("senha local: " + pass);
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return true;
    }
}
