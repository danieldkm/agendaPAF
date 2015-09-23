package com.unifil.agendapaf.exemplos.word;

import com.itextpdf.text.Document;
import com.itextpdf.text.html.simpleparser.HTMLWorker;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.StringReader;

/**
 *
 * @author danielmorita
 */
public class ConvertPDFToHTML {

    public static void main(String[] args) {

        try {
            String k = "<html><body> This is my Project </body></html>";
            OutputStream file = new FileOutputStream(new File("modelo/teste.pdf"));
            Document document = new Document();
            PdfWriter.getInstance(document, file);
            document.open();
            HTMLWorker htmlWorker = new HTMLWorker(document);
            htmlWorker.parse(new StringReader(k));
            System.out.println("htmlWorker " + htmlWorker);
            document.close();
            file.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
//        try {http://cssbox.sourceforge.net/
//            // load the PDF file using PDFBox
//            PDDocument pdf = PDDocument.load("modelo/teste.pdf");
//            // create the DOM parser
//            PDFDomTree parser = new PDFDomTree();
//            // parse the file
//            parser.processDocument(pdf);
//            // get the DOM Document
//            Document dom = parser.getDocument();
//
//            Writer output = new PrintWriter("teste.html", "utf-8");
//            parser.writeText(pdf, output);
//            output.close();
//
//            if (pdf != null) {
//                pdf.close();
//            }
//
//            System.out.println("1 " + dom.getDoctype().getName());
//            System.out.println("2 " + dom.getBaseURI());
//            System.out.println("3 " + dom.getDocumentURI());
//            System.out.println("4 " + dom.getInputEncoding());
//            System.out.println("5 " + dom.getLocalName());
//            System.out.println("6 " + dom.getNamespaceURI());
//            System.out.println("7 " + dom.getNodeName());
//            System.out.println("8 " + dom.getNodeValue());
//            System.out.println("9 " + dom.getPrefix());
//            System.out.println("10 " + dom.getTextContent());
//            System.out.println("11 " + dom.getXmlEncoding());
//            System.out.println("12 " + dom.getXmlVersion());
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
    }
}
