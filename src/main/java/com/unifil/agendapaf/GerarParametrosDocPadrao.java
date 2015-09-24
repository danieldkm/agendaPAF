package com.unifil.agendapaf;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unifil.agendapaf.model.aux.ParametroDocx;
import com.unifil.agendapaf.util.Json;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author danielmorita
 */
public class GerarParametrosDocPadrao {

    public static void main(String[] args) {
        Json json = new Json();

        HashMap<String, String> ptros = new HashMap<>();
        ptros.put("txtNlaudo", "");
        ptros.put("txtRazaoSocial", "");
        ptros.put("txtCnpj", "");
        ptros.put("txtNomeAplicativo", "");
        ptros.put("txtVersao", "");
        ptros.put("txtTamanhoBytes", "");
        ptros.put("txtDataGeracao", "");
        ptros.put("txtPrincipalExec", "");
        ptros.put("txtMd5Principal", "");
        ptros.put("txtNome", "");
        ptros.put("txtCpf", "");
        ptros.put("txtData", "");
        ptros.put("txtRg", "");
        ptros.put("ckbGerenciadorBD", "");
        ptros.put("ckbComRegras", "");

        Gson gson = new Gson();
        String map = gson.toJson(ptros);
//        System.out.println("map = " + map);

        ParametroDocx pd = new ParametroDocx();
        pd.setDocumento("ANEXO BANCO DE DADOS MODELO");
        pd.setParametros(map);
////        
////        ArrayList<String> ptros2 = new ArrayList<>();
////        ptros2.add("teste1");
////        ptros2.add("teste2");
////        ptros2.add("teste3");
////        ptros2.add("teste4");
////        ParametroDocx pd2 = new ParametroDocx();
////        pd2.setDocumento("DOCUMENTO2");
////        pd2.setParametros(ptros2);
////        
////        ArrayList<String> ptros3 = new ArrayList<>();
////        ptros3.add("teste1");
////        ptros3.add("teste2");
////        ptros3.add("teste3");
////        ptros3.add("teste4");
////        ParametroDocx pd3 = new ParametroDocx();
////        pd3.setDocumento("DOCUMENTO3");
////        pd3.setParametros(ptros3);
////        
        ArrayList<ParametroDocx> prs = new ArrayList<>();
        prs.add(pd);
////        prs.add(pd2);
////        prs.add(pd3);
////        
////        
        json.salvarParametroDocxJSON(prs, "xml/modelo_docxs/Documentos.json", false);
        Type type = new TypeToken<HashMap<String, String>>() {
        }.getType();
        for (ParametroDocx pr : json.lerArquivoJSON("xml/modelo_docxs/Documentos.json")) {
            HashMap<String, String> hmap = gson.fromJson(pr.getParametros(), type);
            for (String key : hmap.keySet()) {
                System.out.println("map.get = " + hmap.get(key) + " key " + key);
            }
        }
    }

}
