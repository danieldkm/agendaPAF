package com.unifil.agendapaf.exemplos;

import com.unifil.agendapaf.model.aux.ParametroDocx;
import com.unifil.agendapaf.util.Json;
import java.util.ArrayList;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import jdk.nashorn.internal.runtime.arrays.ArrayLikeIterator;

/**
 *
 * @author danielmorita
 */
public class TesteJson {
    
    public static void main(String[] args) {
        Json json = new Json();
        
//        ArrayList<String> ptros = new ArrayList<>();
//        ptros.add("${teste1}");
//        ptros.add("${teste2}");
//        ptros.add("${teste3}");
//        ptros.add("${teste4}");
//        ParametroDocx pd = new ParametroDocx();
//        pd.setDocumento("DOCUMENTO1");
//        pd.setParametros(ptros);
//        
//        ArrayList<String> ptros2 = new ArrayList<>();
//        ptros2.add("${teste1}");
//        ptros2.add("${teste2}");
//        ptros2.add("${teste3}");
//        ptros2.add("${teste4}");
//        ParametroDocx pd2 = new ParametroDocx();
//        pd2.setDocumento("DOCUMENTO2");
//        pd2.setParametros(ptros2);
//        
//        ArrayList<String> ptros3 = new ArrayList<>();
//        ptros3.add("${teste1}");
//        ptros3.add("${teste2}");
//        ptros3.add("${teste3}");
//        ptros3.add("${teste4}");
//        ParametroDocx pd3 = new ParametroDocx();
//        pd3.setDocumento("DOCUMENTO3");
//        pd3.setParametros(ptros3);
//        
//        ArrayList<ParametroDocx> prs = new ArrayList<>();
//        prs.add(pd);
//        prs.add(pd2);
//        prs.add(pd3);
//        
//        
//        json.salvarParametroDocxJSON(prs, "modelo/Documentos.json", true);
        json.lerArquivoJSON( "modelo/Documentos.json");
    }
    
}
