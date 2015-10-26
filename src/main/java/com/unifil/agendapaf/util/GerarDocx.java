package com.unifil.agendapaf.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unifil.agendapaf.model.aux.ParametroDocx;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import org.docx4j.TraversalUtil;
import org.docx4j.XmlUtils;
import org.docx4j.finders.ClassFinder;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.model.structure.HeaderFooterPolicy;
import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.CTFFCheckBox;
import org.docx4j.wml.CTFFName;
import org.docx4j.wml.ContentAccessor;
import org.docx4j.wml.FldChar;
import org.docx4j.wml.P;
import org.docx4j.wml.R;
import org.docx4j.wml.Tbl;
import org.docx4j.wml.Tc;
import org.docx4j.wml.Text;
import org.docx4j.wml.Tr;

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

    /**
     * Gerar e Substituir os campos marcados no doc com ${} pelo valor
     * armazenado em parametro
     *
     * @param dirFinal caminho completo a ser salvo o doc a ser gerado
     * @param parametro para converter parametros do parametro para hashmap
     * @param hasCheckBox verificar se o doc tem checkBox
     * @param empresa nome da empresa para concatenar no nome do arquivo
     */
    public void gerarDocx(String dirFinal, ParametroDocx parametro, boolean hasCheckBox, String empresa) {
        try {
            //
            // Convert JSON string back to Map.
            //
            Gson gson = new Gson();
            Type type = new TypeToken<HashMap<String, String>>() {
            }.getType();
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new java.io.File(dirPadrao + "/word/modelo_docxs/" + parametro.getDocumento() + ".docx"));
            MainDocumentPart docu = wordMLPackage.getMainDocumentPart();
            VariablePrepare.prepare(wordMLPackage);
//            System.out.println("" + docu.getXML());
            List<SectionWrapper> sectionWrappers = wordMLPackage.getDocumentModel().getSections();
//                HashMap<String, String> mappings = new HashMap<String, String>();
//                mappings.put("teste", "????????");
//                mappings.put("colour", "green");
            HashMap<String, String> map = gson.fromJson(parametro.getParametros(), type);

            for (SectionWrapper sw : sectionWrappers) {
                HeaderFooterPolicy hfp = sw.getHeaderFooterPolicy();
                if (hfp.getFirstHeader() != null) {
                    hfp.getFirstHeader().variableReplace(map);
                }
                if (hfp.getDefaultHeader() != null) {
                    hfp.getDefaultHeader().variableReplace(map);
                }
                if (hfp.getEvenHeader() != null) {
                    hfp.getEvenHeader().variableReplace(map);
                }
            }
            if (hasCheckBox) {
                setCheckBox(docu, map);
            }
            if (map.get("txtRelacaoEcf") != null) {
                if (!map.get("txtRelacaoEcf").equals("")) {
                    addTabelaTxtRelacaoEcf(docu, map, "txtRelacaoEcf", 4.0f);
                }
            }
            if (map.get("txtNaoConformidadeRequisito") != null) {
                if (!map.get("txtNaoConformidadeRequisito").equals("")) {
                    addTabelaTxtRelacaoEcf(docu, map, "txtNaoConformidadeRequisito", 2.0f);
                }
            }
            if (map.get("txtRelacaoMd5Executaveis") != null) {
                addTabelaTxtRelacaoMd5Executaveis(docu, map);
            }
//            for (String key : map.keySet()) {
//                System.out.println("key " + key + " -> map.get = " + map.get(key));
//            }
            docu.variableReplace(map);
//            wordMLPackage.save(new java.io.File());
            SaveToZipFile saver = new SaveToZipFile(wordMLPackage);
            parametro.setDocumento(parametro.getDocumento().replace("MODELO", empresa));
            String outputfilepath = dirPadrao + "/" + dirFinal + "/" + parametro.getDocumento() + ".docx";
            saver.save(outputfilepath);
        } catch (Docx4JException ex) {
            Logger.getLogger(GerarDocx.class.getName()).log(Level.SEVERE, null, ex);
        } catch (JAXBException ex) {
            Logger.getLogger(GerarDocx.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(GerarDocx.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Verifica se no documento contem checkBox se sim e se estiver true na
     * variavel map checa o componente checkBox
     *
     * @param documentPart objeto contem o doc
     * @param map contem todas as informacoes dos parametros e das checkBoxs
     */
    private void setCheckBox(MainDocumentPart documentPart, HashMap<String, String> map) {
        try {
            ClassFinder finder = new ClassFinder(FldChar.class);
            new TraversalUtil(documentPart.getContent(), finder);

            for (Object o : finder.results) {

                Object o2 = XmlUtils.unwrap(o);
                // this is ok, provided the results of the Callback
                // won't be marshalled          
                FldChar fldChar = (FldChar) o;
                if (fldChar.getFfData() != null) {
                    boolean check = false;
                    for (JAXBElement<?> nameOrEnabledOrCalcOnExit : fldChar.getFfData().getNameOrEnabledOrCalcOnExit()) {
                        if (nameOrEnabledOrCalcOnExit.getValue() instanceof CTFFName) {
                            CTFFName name = (CTFFName) nameOrEnabledOrCalcOnExit.getValue();
                            if (map.get(name.getVal()).equals("true")) {
                                check = true;
                            }
                        } else if (nameOrEnabledOrCalcOnExit.getValue() instanceof CTFFCheckBox) {
                            CTFFCheckBox ccb = (CTFFCheckBox) nameOrEnabledOrCalcOnExit.getValue();
                            if (check) {
                                ccb.setDefault(new BooleanDefaultTrue());
                                check = false;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addTabelaTxtRelacaoMd5Executaveis(MainDocumentPart document, HashMap<String, String> map) {
        String[] listaRelacao = map.get("txtRelacaoMd5Executaveis").split(",");
        List<Object> tcs = getAllElementFromObject(document, Tc.class);
        for (Object o : tcs) {
            Tc tc = (Tc) o;
            List<Object> ps = getAllElementFromObject(tc, P.class);
            for (Object o2 : ps) {
                P p = (P) o2;
                List<Object> tables = getAllElementFromObject(tc, R.class);
                for (Object table : tables) {
                    R r = (R) table;
                    // Get the Text in the Run
                    List<Object> allText = getAllElementFromObject(r, Text.class);
                    for (Object text : allText) {
                        Text txt = (Text) text;
                        if (txt.getValue().equals("${txtRelacaoMd5Executaveis}")) {
                            txt.setValue(listaRelacao[0]);

                            for (int i = 1; i < listaRelacao.length; i++) {
                                P novoP = new P();
                                novoP.setPPr(p.getPPr());
                                R novoR = new R();
                                Text txt2 = new Text();
                                txt2.setValue(listaRelacao[i]);
                                novoR.setRPr(r.getRPr());
                                novoR.getContent().add(txt2);
                                novoP.getContent().add(novoR);
                                tc.getContent().add(novoP);
                            }
                        }
                    }
                }
            }
        }
    }

    public void addTabelaTxtRelacaoEcf(MainDocumentPart document, HashMap<String, String> map, String tipo, float divisao) {
        String[] listaRelacaoEcf = map.get(tipo).split(",");
        double a = listaRelacaoEcf.length / divisao;
        int linhas = (int) Math.round(a);
        Tr trTemporario = null;
        Tbl tblTemporario = null;
        int posicaoTr = 0;
        List<Object> tbls = getAllElementFromObject(document, Tbl.class);
        for (Object oTbl : tbls) {
            Tbl tbl = (Tbl) oTbl;
            List<Object> trs = getAllElementFromObject(tbl, Tr.class);
            for (int i = 0; i < trs.size(); i++) {
                Tr tr = (Tr) trs.get(i);
                List<Object> tcs = getAllElementFromObject(tr, Tc.class);
                for (Object o : tcs) {
                    Tc tc = (Tc) o;
                    List<Object> ps = getAllElementFromObject(tc, P.class);
                    for (Object o2 : ps) {
                        P p = (P) o2;
                        List<Object> tables = getAllElementFromObject(tc, R.class);
                        for (Object table : tables) {
                            R r = (R) table;
                            // Get the Text in the Run
                            List<Object> allText = getAllElementFromObject(r, Text.class);
                            for (Object text : allText) {
                                Text txt = (Text) text;
                                if (txt.getValue().contains(tipo)) {
                                    trTemporario = tr;
                                    tblTemporario = tbl;
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }

        List<Object> allText = getAllElementFromObject(trTemporario, Text.class);
        int i = 0;
        for (Object text : allText) {
            Text txt = (Text) text;
            txt.setValue(listaRelacaoEcf[i]);
            i++;
        }
        if (linhas > 1) {
            Tr novoTr;
            for (int j = 0; j < (linhas - 1); j++) {
                novoTr = new Tr();
                novoTr.setTblPrEx(trTemporario.getTblPrEx());
                novoTr.setTrPr(trTemporario.getTrPr());
                List<Object> tcs = getAllElementFromObject(trTemporario, Tc.class);
                for (Object o : tcs) {
                    Tc tc = (Tc) o;
                    Tc tempTc = new Tc();
                    tempTc.setTcPr(tc.getTcPr());
                    List<Object> ps = getAllElementFromObject(tc, P.class);
                    for (Object oP : ps) {
                        P p = (P) oP;
                        P novoP = new P();
                        novoP.setPPr(p.getPPr());
                        List<Object> rs = getAllElementFromObject(p, R.class);
                        for (Object oR : rs) {
                            R r = (R) oR;
                            R novoR = new R();
                            novoR.setRPr(r.getRPr());
                            Text novoTxt = new Text();
                            if (i < listaRelacaoEcf.length) {
                                novoTxt.setValue(listaRelacaoEcf[i]);
                                novoR.getContent().add(novoTxt);
                                i++;
                            }
                            novoP.getContent().add(novoR);
                        }
                        tempTc.getContent().add(novoP);
                    }
                    novoTr.getContent().add(tempTc);
                }
                if (tipo.equals("txtNaoConformidadeRequisito")) {
                    tblTemporario.getContent().add(tblTemporario.getContent().size() - 1, novoTr);
                } else {
                    tblTemporario.getContent().add(novoTr);
                }
            }
        }
    }

    private List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
        List<Object> result = new ArrayList<Object>();
        if (obj instanceof JAXBElement) {
            obj = ((JAXBElement<?>) obj).getValue();
        }
        if (obj.getClass().equals(toSearch)) {
            result.add(obj);
        } else if (obj instanceof ContentAccessor) {
            List<?> children = ((ContentAccessor) obj).getContent();
            for (Object child : children) {
                result.addAll(getAllElementFromObject(child, toSearch));
            }
        }
        return result;
    }

}
