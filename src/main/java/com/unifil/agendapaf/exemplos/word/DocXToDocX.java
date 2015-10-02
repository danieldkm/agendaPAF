package com.unifil.agendapaf.exemplos.word;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import org.docx4j.Docx4jProperties;
import org.docx4j.model.datastorage.migration.VariablePrepare;
import org.docx4j.model.structure.HeaderFooterPolicy;
import org.docx4j.model.structure.SectionWrapper;
import org.docx4j.openpackaging.exceptions.Docx4JException;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.samples.AbstractSample;
import org.docx4j.wml.ContentAccessor;
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
public class DocXToDocX extends AbstractSample {

    static String dir;

    // Config for non-command line version
    static {

        dir = System.getProperty("user.dir") + "/word/modelo_docxs/";
//		dir = System.getProperty("user.dir") + "/";
        inputfilepath = "LAUDO PAF-ECF-F MODELO 2015.docx";
    }

    public static void vai() throws Docx4JException, JAXBException, Exception {
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new java.io.File(dir + inputfilepath));
        MainDocumentPart docu = wordMLPackage.getMainDocumentPart();
        VariablePrepare.prepare(wordMLPackage);
        HashMap<String, String> mappings = new HashMap<String, String>();
        mappings.put("a", "aaaaaaaaa aaa");
        mappings.put("icecream", "????????");
        mappings.put("txtRazaoSocial", "UniFil - PAF-ECF");
        mappings.put("colour", "green");
        mappings.put("teste", "primeiro teste");
        mappings.put("laudo", "IFL0012015");
        mappings.put("cidade", "Londrina");
        mappings.put("txtUf", "PR");
        mappings.put("txtCep", "8600000-000");
        mappings.put("txtCnpj", "12345678901234");
        mappings.put("txtNomeAplicativo", "Agenda PAF-ECF");
        mappings.put("txtVersao", "2.0");

        docu.variableReplace(mappings);
        wordMLPackage.save(new java.io.File(dir + "b2.docx"));

    }

    private static List<Object> getAllElementFromObject(Object obj, Class<?> toSearch) {
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

    public static void vai2() throws Exception {
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new java.io.File(dir + inputfilepath));
        VariablePrepare.prepare(wordMLPackage);
        MainDocumentPart docu = wordMLPackage.getMainDocumentPart();
        List<SectionWrapper> sectionWrappers = wordMLPackage.getDocumentModel().getSections();

        System.out.println("XML " + docu.getXML());
        HashMap<String, String> mappings = new HashMap<String, String>();
        mappings.put("a", "aaaaaaaaa aaa");
        mappings.put("icecream", "????????");
        mappings.put("txtRazaoSocial", "UniFil - PAF-ECF");
        mappings.put("colour", "green");
        mappings.put("teste", "primeiro teste");
        mappings.put("laudo", "IFL0012015");
        mappings.put("cidade", "Londrina");
        mappings.put("txtUf", "PR");
        mappings.put("txtCep", "8600000-000");
        mappings.put("txtCnpj", "12345678901234");
        mappings.put("txtNomeAplicativo", "Agenda PAF-ECF");
        mappings.put("txtVersao", "2.0");
        mappings.put("txtRelacaoMd5Executaveis", "BEMAFI32.DLL 				F5844A2F35204D273B8686A124DD7A9A\r\n"
                + "BEMAMFD.DLL 				35C532B94CD183788A48F94586CFB199\r\n"
                + "BEMAMFD2.DLL 				9FCAE7D1DE0595724D6CFA7E55748775\r\n"
                + "BEMAMFD3.DLL 				FD1B862390649F4177EF38F5592D871D\r\n"
                + "DARUMAFRAMEWORK.DLL 		332963A7B52FF86C719B5AE42AEF2888\r\n"
                + "INTERFACEEPSON.DLL 			F48559304D6E877CF503F86C1CF5BF0E\r\n"
                + "ELGIN.DLL    				8D3C4FA90336A14FA3E79888BE7CB92B\r\n"
                + "ATO17.DLL 					02B0086ABFD2A8609B26EC24F4E35138\r\n"
                + "DLLG2.DLL 					57B8021F6E91FFC567AA91EFCB56A7D5\r\n"
                + "LEITURA.DLL 				79BF7CEB68D63C601D17DD7B563F8616\r\n"
                + "LEITURAMFDBIN.DLL 			7B1E9D4A19C0B7CD5BCE3F9521BFED88\r\n"
                + "PRIORIZENFE.EXE 			BAAD19E816CBD56298775475D07B9746\r\n"
                + "PRIORIZEPDV.EXE				5213978D5DB517C5992880925F4EB607");

        for (SectionWrapper sw : sectionWrappers) {
            HeaderFooterPolicy hfp = sw.getHeaderFooterPolicy();
            System.out.println("\n\nSECTION  \n");

            System.out.println("Headers:");
            if (hfp.getFirstHeader() != null) {
                System.out.println("-first " + hfp.getFirstHeader().toString());
                System.out.println("-first content " + hfp.getFirstHeader().getContentType());
                System.out.println("-first relationShip " + hfp.getFirstHeader().getRelationshipType());
                System.out.println("-first xml " + hfp.getFirstHeader().getXML());
                hfp.getFirstHeader().variableReplace(mappings);

            }
            if (hfp.getDefaultHeader() != null) {
//                System.out.println("-default " + hfp.getDefaultHeader().toString());
//                System.out.println("-default content " + hfp.getDefaultHeader().getContentType());
//                System.out.println("-default relationShip " + hfp.getDefaultHeader().getRelationshipType());
//                System.out.println("-default xml " + hfp.getDefaultHeader().getXML());
//                String xml = hfp.getDefaultHeader().getXML().replace("<w:t>${laudo</w:t>", "<w:t>${laudo}</w:t>");
//                hfp.getDefaultHeader().
                hfp.getDefaultHeader().variableReplace(mappings);

            }
            if (hfp.getEvenHeader() != null) {
                System.out.println("-even " + hfp.getEvenHeader().toString());
                System.out.println("-even content " + hfp.getEvenHeader().getContentType());
                System.out.println("-even relationShip " + hfp.getEvenHeader().getRelationshipType());
                System.out.println("-even xml " + hfp.getEvenHeader().getXML());
                hfp.getEvenHeader().variableReplace(mappings);

            }
        }

        List<Object> tcs = getAllElementFromObject(docu, Tc.class);
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
//                        System.out.println("TXT " + txt.getValue());
                        if (txt.getValue().equals("${txtRelacaoMd5Executaveis}")) {
                            txt.setValue("DARUMAFRAMEWORK.DLL                    332963A7B52FF86C719B5AE42AEF2888");
                            P novoP = new P();
                            novoP.setPPr(p.getPPr());
                            R novoR = new R();
                            Text txt2 = new Text();
                            txt2.setValue("BEMAFI32.DLL                             F5844A2F35204D273B8686A124DD7A9A");
                            novoR.setRPr(r.getRPr());
                            novoR.getContent().add(txt2);
                            novoP.getContent().add(novoR);
                            tc.getContent().add(novoP);
                        }
                    }
                }
            }
        }
        HashMap<String, String> map = new HashMap<>();
        map.put("txtNaoConformidadeRequisito", "marca1,modelo1,marca2,modelo2,marca3,modelo3,marca4,modelo4,marca5,modelo5,marca6,modelo6,marca7,modelo7");
        addTabela(docu, map);
        docu.variableReplace(mappings);
        wordMLPackage.save(new java.io.File(dir + "b2.docx"));
    }

    public static void addTabela(MainDocumentPart document, HashMap<String, String> map) {
//        String[] listaRelacaoEcf = map.get("${txtRelacaoEcf}").split(",");
        String[] listaRelacaoEcf = map.get("txtNaoConformidadeRequisito").split(",");
        double a = listaRelacaoEcf.length / 2f;
        int linhas = (int) Math.round(a);
        System.out.println("LINHAS " + linhas);
        Tr trTemporario = null;
        Tbl tblTemporario = null;
        int posicaoTr = 0;
        List<Object> tbls = getAllElementFromObject(document, Tbl.class);
        for (Object oTbl : tbls) {
            Tbl tbl = (Tbl) oTbl;
            List<Object> trs = getAllElementFromObject(tbl, Tr.class);
            for (int i = 0; i < trs.size(); i++) {
//            }
//            for (Object oTr : trs) {
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
                                if (txt.getValue().contains("txtNaoConformidadeRequisito")) {
//                                    System.out.println("TXT " + txt.getValue());
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
                tblTemporario.getContent().add(tblTemporario.getContent().size() - 1, novoTr);
            }
        }
    }

    public static void main(String[] args) {
        try {

            Docx4jProperties.setProperty("docx4j.Convert.Out.HTML.OutputMethodXML", true);

            try {
                getInputFilePath(args);
            } catch (IllegalArgumentException e) {
//                e.printStackTrace();
            }

            System.out.println(inputfilepath);
//            vai();
            vai2();
//            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new java.io.File(dir + inputfilepath));
//            MainDocumentPart docu = wordMLPackage.getMainDocumentPart();
//
//            HashMap<String, String> mappings = new HashMap<String, String>();
////            mappings.put("a", "aaaaaaaaa aaa");
////            mappings.put("icecream", "????????");
////            mappings.put("razao", "UniFil - PAF-ECF");
//            mappings.put("colour", "green");
//            mappings.put("teste", "primeiro teste");
////            mappings.put("laudo", "IFL0012015");
////            mappings.put("txtCidade", "Londrina");
////            mappings.put("txtUf", "PR");
////            mappings.put("txtCep", "8600000-000");
////            mappings.put("txtCnpj", "12345678901234");
////            mappings.put("txtNomeAplicativo", "Agenda PAF-ECF");
////            mappings.put("txtVersao", "2.0");
//
//            docu.variableReplace(mappings);
//
////            SaveToZipFile saver = new SaveToZipFile(wordMLPackage);
////            saver.save(dir + "Out_VariableReplace.docx");
//            wordMLPackage.save(new java.io.File(dir + "b2.docx"));

//            //save in pdf
//            boolean save = true;
//            PdfConversion c = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(wordMLPackage);
//            ((org.docx4j.convert.out.pdf.viaXSLFO.Conversion) c).setSaveFO(new java.io.File(dir + "saveFO.fo"));
//
//            if (save) {
//                ((org.docx4j.convert.out.pdf.viaXSLFO.Conversion) c).setSaveFO(
//                        new java.io.File(dir +"saveFO.fo"));
//                OutputStream os = new java.io.FileOutputStream(dir + "convertido.pdf");
//                c.output(os, new PdfSettings());
//                System.out.println("Saved " + dir + "convertido.pdf");
//            }
//            
//            //segunda Opcao salvar em pdf
//            FOSettings foSettings = Docx4J.createFOSettings();
//            foSettings.setWmlPackage(wordMLPackage);
//            OutputStream os2 = new java.io.FileOutputStream(dir + "convertido2.pdf");
//            Docx4J.toFO(foSettings, os2, Docx4J.FLAG_EXPORT_PREFER_XSL);
////            org.docx4j.convert.out.pdf.PdfConversion c  = new Conversion(wordMLPackage);
//            //       = new org.docx4j.convert.out.pdf.viaHTML.Conversion(wordMLPackage);
//            //       = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(wordMLPackage);
////                    = new org.docx4j.convert.out.pdf.viaIText.Conversion(wordMLPackage);
////            c.output(os);
//
////            //converte o documento atual para xml "supostamente irei dps detectar os campos a serem substituidos pelo HashMap
////            //em seguido salvo o xml em docx
//////            String xml = XmlUtils.marshaltoString(wordMLPackage.getMainDocumentPart().getJaxbElement(), true, true);
////            MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
////            org.docx4j.wml.Document wmlDocumentEl = (org.docx4j.wml.Document) documentPart.getJaxbElement();
////
////            String xml = XmlUtils.marshaltoString(wmlDocumentEl, true);
//////            System.out.println("XML ?? ");
//////            System.out.println(xml);
////            WordprocessingMLPackage wordXml = WordprocessingMLPackage.createPackage();
////            MainDocumentPart document = wordXml.getMainDocumentPart();
////            HashMap substitution = new HashMap();
//////	    substitution.put("fontname", fontName);
////            Object o = XmlUtils.unmarshallFromTemplate(xml, substitution);
//////	    document.addObject(o);
////            document.setJaxbElement((org.docx4j.wml.Document) o);
////            // Some code to deal with the footers
////            wordXml.save(new java.io.File("sera.docx"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
