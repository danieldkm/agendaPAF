package com.unifil.agendapaf.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unifil.agendapaf.model.aux.ParametroDocx;
import com.unifil.agendapaf.model.laudo.LaudoType;
import java.lang.reflect.Type;
import java.text.DateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javax.xml.bind.JAXBException;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;

/**
 *
 * @author danielmorita
 */
public class GerarDocx {

    private ObservableList<String> docs;
    private String dirPadrao = System.getProperty("user.dir");
    private WordprocessingMLPackage wordMLPackage;

    public GerarDocx() {
    }

    public GerarDocx(ObservableList<String> docs) {
        this.docs = docs;
    }

    public void gerarDocx(String dirFinal, ParametroDocx parametro) {
        try {
            Gson gson = new Gson();
            Type type = new TypeToken<HashMap<String, String>>() {
            }.getType();
            //
            // Convert JSON string back to Map.
            //
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new java.io.File(dirPadrao + "/xml/modelo_docxs/" + parametro.getDocumento() + ".docx"));
            MainDocumentPart docu = wordMLPackage.getMainDocumentPart();

//                HashMap<String, String> mappings = new HashMap<String, String>();
//                mappings.put("teste", "????????");
//                mappings.put("colour", "green");
            HashMap<String, String> map = gson.fromJson(parametro.getParametros(), type);
            for (String key : map.keySet()) {
                System.out.println("key " + key + " -> map.get = " + map.get(key));
            }
            docu.variableReplace(map);
//            wordMLPackage.save(new java.io.File());
            SaveToZipFile saver = new SaveToZipFile(wordMLPackage);
            String outputfilepath = dirPadrao + "/" + dirFinal + "/" + parametro.getDocumento() + ".docx";
            saver.save(outputfilepath);
        } catch (Docx4JException ex) {
            Logger.getLogger(GerarDocx.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(GerarDocx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
