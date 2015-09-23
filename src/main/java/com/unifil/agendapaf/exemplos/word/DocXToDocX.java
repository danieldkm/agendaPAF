package com.unifil.agendapaf.exemplos.word;

import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import org.docx4j.Docx4J;
import org.docx4j.Docx4jProperties;
import org.docx4j.XmlUtils;
import org.docx4j.convert.out.FOSettings;
import org.docx4j.convert.out.pdf.PdfConversion;
import org.docx4j.convert.out.pdf.viaXSLFO.Conversion;
import org.docx4j.convert.out.pdf.viaXSLFO.PdfSettings;
import org.docx4j.openpackaging.io.SaveToZipFile;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.samples.AbstractSample;
import org.docx4j.wml.Document;

/**
 *
 * @author danielmorita
 */
public class DocXToDocX extends AbstractSample {

    static String dir;

    // Config for non-command line version
    static {

        dir = System.getProperty("user.dir") + "/docx/";
//		dir = System.getProperty("user.dir") + "/";
        inputfilepath = "b.docx";
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
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new java.io.File(dir + inputfilepath));
            MainDocumentPart docu = wordMLPackage.getMainDocumentPart();

            HashMap<String, String> mappings = new HashMap<String, String>();
            mappings.put("teste", "????????");
            mappings.put("colour", "green");

            docu.variableReplace(mappings);

//            SaveToZipFile saver = new SaveToZipFile(wordMLPackage);
//            saver.save(dir + "Out_VariableReplace.docx");
            wordMLPackage.save(new java.io.File(dir+"b2.docx"));

            //save in pdf
            boolean save = true;
            PdfConversion c = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(wordMLPackage);
            ((org.docx4j.convert.out.pdf.viaXSLFO.Conversion) c).setSaveFO(new java.io.File(dir + "saveFO.fo"));

            if (save) {
                ((org.docx4j.convert.out.pdf.viaXSLFO.Conversion) c).setSaveFO(
                        new java.io.File(dir +"saveFO.fo"));
                OutputStream os = new java.io.FileOutputStream(dir + "convertido.pdf");
                c.output(os, new PdfSettings());
                System.out.println("Saved " + dir + "convertido.pdf");
            }
            
            //segunda Opcao salvar em pdf
            FOSettings foSettings = Docx4J.createFOSettings();
            foSettings.setWmlPackage(wordMLPackage);
            OutputStream os2 = new java.io.FileOutputStream(dir + "convertido2.pdf");
            Docx4J.toFO(foSettings, os2, Docx4J.FLAG_EXPORT_PREFER_XSL);
//            org.docx4j.convert.out.pdf.PdfConversion c  = new Conversion(wordMLPackage);
            //       = new org.docx4j.convert.out.pdf.viaHTML.Conversion(wordMLPackage);
            //       = new org.docx4j.convert.out.pdf.viaXSLFO.Conversion(wordMLPackage);
//                    = new org.docx4j.convert.out.pdf.viaIText.Conversion(wordMLPackage);
//            c.output(os);

//            //converte o documento atual para xml "supostamente irei dps detectar os campos a serem substituidos pelo HashMap
//            //em seguido salvo o xml em docx
////            String xml = XmlUtils.marshaltoString(wordMLPackage.getMainDocumentPart().getJaxbElement(), true, true);
//            MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
//            org.docx4j.wml.Document wmlDocumentEl = (org.docx4j.wml.Document) documentPart.getJaxbElement();
//
//            String xml = XmlUtils.marshaltoString(wmlDocumentEl, true);
////            System.out.println("XML ?? ");
////            System.out.println(xml);
//            WordprocessingMLPackage wordXml = WordprocessingMLPackage.createPackage();
//            MainDocumentPart document = wordXml.getMainDocumentPart();
//            HashMap substitution = new HashMap();
////	    substitution.put("fontname", fontName);
//            Object o = XmlUtils.unmarshallFromTemplate(xml, substitution);
////	    document.addObject(o);
//            document.setJaxbElement((org.docx4j.wml.Document) o);
//            // Some code to deal with the footers
//            wordXml.save(new java.io.File("sera.docx"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
