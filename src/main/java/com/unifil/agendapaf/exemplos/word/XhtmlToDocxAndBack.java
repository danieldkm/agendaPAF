/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unifil.agendapaf.exemplos.word;

/**
 *
 * @author danielmorita
 */
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.OutputStream;
import org.apache.commons.io.FileUtils;

import org.docx4j.Docx4J;
import org.docx4j.Docx4jProperties;
import org.docx4j.XmlUtils;
import org.docx4j.convert.in.xhtml.XHTMLImporterImpl;
import org.docx4j.convert.out.HTMLSettings;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Round-trip XHTML to docx and back to XHTML.
 */
public class XhtmlToDocxAndBack {

    private static Logger log = LoggerFactory.getLogger(XhtmlToDocxAndBack.class);

    public static void main(String[] args) throws Exception {

//        String xhtml
//                = "<table border=\"1\" cellpadding=\"1\" cellspacing=\"1\" style=\"width:100%;\"><tbody><tr><td>test</td><td>test</td></tr><tr><td>test</td><td>test</td></tr><tr><td>test</td><td>test</td></tr></tbody></table>";
        String xhtml = FileUtils.readFileToString(new File("docx/a.html"), "UTF-8");
        System.out.println("XHTML " + xhtml);
        // To docx, with content controls
        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();

        XHTMLImporterImpl XHTMLImporter = new XHTMLImporterImpl(wordMLPackage);
        //XHTMLImporter.setDivHandler(new DivToSdt());

        wordMLPackage.getMainDocumentPart().getContent().addAll(
                XHTMLImporter.convert(xhtml, null));

        System.out.println(XmlUtils.marshaltoString(wordMLPackage
                .getMainDocumentPart().getJaxbElement(), true, true));

        wordMLPackage.save(new java.io.File("docx/OUT_from_XHTML.docx"));
        // Back to XHTML
        HTMLSettings htmlSettings = Docx4J.createHTMLSettings();
        htmlSettings.setWmlPackage(wordMLPackage);

        // output to an OutputStream.
        OutputStream os = new ByteArrayOutputStream();

        // If you want XHTML output
        Docx4jProperties.setProperty("docx4j.Convert.Out.HTML.OutputMethodXML",
                true);
        Docx4J.toHTML(htmlSettings, os, Docx4J.FLAG_EXPORT_PREFER_XSL);

        System.out.println(((ByteArrayOutputStream) os).toString());

    }

}
