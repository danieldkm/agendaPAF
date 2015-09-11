package com.unifil.agendapaf.util.huffman;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author danielmorita
 */
public class JSONHuffman {

    public String getFraseJSON(String binario, String name, String fileName) {
        ArrayList<Node> list = lerArquivoJSON(name, fileName);
        String parcial = "";
        String frase = "";
        while (binario.length() > 0) {
            parcial += binario.substring(0, 1);
            for (Node node : list) {
                if (node.getBin().equals(parcial)) {
                    frase += node.getData();
                    parcial = "";
                }
            }
            binario = binario.substring(1);
        }
        return frase;
    }

    public void salvarArquivoJSON(String name, ArrayList<Node> huffmanNode, String fileName, boolean addArquivo) {
        JSONObject o = new JSONObject();
        o.put(name, huffmanNode);
        BufferedWriter buf;
        try {
            buf = new BufferedWriter(new FileWriter(fileName, addArquivo));
            buf.write(o.toString() + "\n");
            buf.flush();
            buf.close();
        } catch (IOException ex) {
            Logger.getLogger(Huffman.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private JSONArray readJSON(String nomeWrapper, String fileName) throws Exception {
        BufferedReader buf = new BufferedReader(new FileReader(fileName));
        String s = "";
        String linha = "";
        while ((linha = buf.readLine()) != null) {
            if (linha.contains(nomeWrapper)) {
                System.out.println("nomeWrapper " + nomeWrapper + " = " + linha);
                s += linha + "\n";
            }
        }
        JSONArray abc = new JSONArray();
        if (!s.equals("")) {

            JSONObject json = new JSONObject(s);
            abc = (JSONArray) json.get(nomeWrapper);
        }
        return abc;
    }

    public ArrayList<Node> lerArquivoJSON(String name, String fileName) {
        ArrayList<Node> list = new ArrayList<>();
        try {
//            Storage cs = new Storage(Jogador.class);
            String nomeWrapper = name;
            JSONArray a = readJSON(nomeWrapper, fileName);
            JSONObject o = null;
            for (int i = 0; i < a.length(); i++) {
                o = a.getJSONObject(i);
                Node n = new Node(o.getInt("quantidade"), o.getString("data"), o.getDouble("valor"), o.getString("bin"));
//                System.out.println("Qtd " + n.getQuantidade());
//                System.out.println("DT " + n.getData());
//                System.out.println("Bin " + n.getBin());
                list.add(n);
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return list;
    }

}
