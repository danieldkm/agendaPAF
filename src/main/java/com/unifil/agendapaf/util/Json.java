package com.unifil.agendapaf.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unifil.agendapaf.model.aux.ParametroDocx;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author danielmorita
 */
public class Json {

    /**
     * Salvar em formato json novo arquivo
     *
     * @param parametros objeto a ser salvo
     * @param fileName arquivo a ser substituido ou nao
     * @param addArquivo caso true adicionar o objeto no arquivo se nao cria um
     */
    public void salvarParametroDocxJSON(ArrayList<ParametroDocx> parametros, String fileName, boolean addArquivo) {
        Gson gson = new Gson();
        // convert java object to JSON format,  
        // and returned as JSON formatted string
        String json = gson.toJson(parametros);
        BufferedWriter buf;
        try {
            buf = new BufferedWriter(new FileWriter(fileName, addArquivo));
            buf.write(json + "\n");
            buf.flush();
            buf.close();
        } catch (IOException ex) {
            Logger.getLogger(Json.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Ler arquivo json
     *
     * @param nomeWrapper nomeWrapper
     * @param fileName arquivo a ser substituido ou nao
     * @return JSONArray conteudo lido
     */
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

    /**
     * Ler arquivo json
     *
     * @param fileName arquivo a ser substituido ou nao
     * @return retornar lista de ParametroDocx
     */
    public ObservableList<ParametroDocx> lerArquivoJSON(String fileName) {
        Gson gson = new Gson();
        ObservableList<ParametroDocx> retornar = FXCollections.observableArrayList();
        try {
//            System.out.println("Reading JSON from a file");
//            System.out.println("----------------------------");
            BufferedReader br = new BufferedReader(
                    new FileReader(fileName));
            TypeToken<List<ParametroDocx>> token = new TypeToken<List<ParametroDocx>>() {
            };
            List<ParametroDocx> personList = gson.fromJson(br, token.getType());
            for (ParametroDocx pds : personList) {
                retornar.add(pds);
                System.out.println("Name of Document: " + pds.getDocumento());
                System.out.println("Parametros: " + pds.getParametros());
//                System.out.println("States are :");
//                ArrayList<String> listOfStates = pds.getParametros();
//                for (int i = 0; i < listOfStates.size(); i++) {
//                    System.out.println(listOfStates.get(i));
//                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return retornar;
//        ObservableList<ParametroDocx> listaParametro = FXCollections.observableArrayList();
//        try {
//            String nomeWrapper = name;
//            JSONArray a = readJSON(nomeWrapper, fileName);
//            JSONObject o = null;
//            ParametroDocx pd = null;
//            for (int i = 0; i < a.length(); i++) {
//                o = a.getJSONObject(i);
//                pd = new ParametroDocx();
//                pd.setDocumento(o.getString("documento"));
//                pd.setParametros(o.getJSONArray("parametros"));
//                listaParametro.add(pd);
//            }
//        } catch (Exception ex) {
//            ex.printStackTrace();
//        }
//        return listaParametro;
    }

}
