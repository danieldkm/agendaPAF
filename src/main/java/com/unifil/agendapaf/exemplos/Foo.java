package com.unifil.agendapaf.exemplos;

import java.math.BigInteger;
import static javafx.scene.input.KeyCode.R;
import javafx.scene.text.Text;
import javax.xml.bind.JAXBElement;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.Body;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.CTBookmark;
import org.docx4j.wml.CTBorder;
import org.docx4j.wml.CTColumns;
import org.docx4j.wml.CTDocGrid;
import org.docx4j.wml.CTMarkupRange;
import org.docx4j.wml.Document;
import org.docx4j.wml.FooterReference;
import org.docx4j.wml.Hdr;
import org.docx4j.wml.HeaderReference;
import org.docx4j.wml.P;
import org.docx4j.wml.PPr;
import org.docx4j.wml.PPrBase;
import org.docx4j.wml.R;
import org.docx4j.wml.SectPr;

public class Foo {

    public Document createIt() {

        org.docx4j.wml.ObjectFactory wmlObjectFactory = new org.docx4j.wml.ObjectFactory();

        Document document = wmlObjectFactory.createDocument();
        // Create object for body
        Body body = wmlObjectFactory.createBody();
        document.setBody(body);
        // Create object for sectPr
        SectPr sectpr = wmlObjectFactory.createSectPr();
        body.setSectPr(sectpr);
        // Create object for pgSz
        SectPr.PgSz sectprpgsz = wmlObjectFactory.createSectPrPgSz();
        sectpr.setPgSz(sectprpgsz);
        sectprpgsz.setH(BigInteger.valueOf(16840));
        sectprpgsz.setW(BigInteger.valueOf(11900));
        // Create object for pgMar
        SectPr.PgMar sectprpgmar = wmlObjectFactory.createSectPrPgMar();
        sectpr.setPgMar(sectprpgmar);
        sectprpgmar.setFooter(BigInteger.valueOf(709));
        sectprpgmar.setLeft(BigInteger.valueOf(1701));
        sectprpgmar.setRight(BigInteger.valueOf(1134));
        sectprpgmar.setTop(BigInteger.valueOf(1701));
        sectprpgmar.setBottom(BigInteger.valueOf(1134));
        sectprpgmar.setGutter(BigInteger.valueOf(0));
        sectprpgmar.setHeader(BigInteger.valueOf(709));
        // Create object for headerReference
        HeaderReference headerreference = wmlObjectFactory.createHeaderReference();
        sectpr.getEGHdrFtrReferences().add(headerreference);
        headerreference.setType(org.docx4j.wml.HdrFtrRef.EVEN);
        headerreference.setId("rId7");
        // Create object for headerReference
        HeaderReference headerreference2 = wmlObjectFactory.createHeaderReference();
        sectpr.getEGHdrFtrReferences().add(headerreference2);
        headerreference2.setType(org.docx4j.wml.HdrFtrRef.DEFAULT);
        headerreference2.setId("rId8");
        // Create object for footerReference
        FooterReference footerreference = wmlObjectFactory.createFooterReference();
        sectpr.getEGHdrFtrReferences().add(footerreference);
        footerreference.setType(org.docx4j.wml.HdrFtrRef.EVEN);
        footerreference.setId("rId9");
        // Create object for footerReference
        FooterReference footerreference2 = wmlObjectFactory.createFooterReference();
        sectpr.getEGHdrFtrReferences().add(footerreference2);
        footerreference2.setType(org.docx4j.wml.HdrFtrRef.DEFAULT);
        footerreference2.setId("rId10");
        // Create object for headerReference
        HeaderReference headerreference3 = wmlObjectFactory.createHeaderReference();
        sectpr.getEGHdrFtrReferences().add(headerreference3);
        headerreference3.setType(org.docx4j.wml.HdrFtrRef.FIRST);
        headerreference3.setId("rId11");
        // Create object for footerReference
        FooterReference footerreference3 = wmlObjectFactory.createFooterReference();
        sectpr.getEGHdrFtrReferences().add(footerreference3);
        footerreference3.setType(org.docx4j.wml.HdrFtrRef.FIRST);
        footerreference3.setId("rId12");
        // Create object for cols
        CTColumns columns = wmlObjectFactory.createCTColumns();
        sectpr.setCols(columns);
        columns.setSpace(BigInteger.valueOf(708));
        // Create object for titlePg
        BooleanDefaultTrue booleandefaulttrue = wmlObjectFactory.createBooleanDefaultTrue();
        sectpr.setTitlePg(booleandefaulttrue);
        // Create object for docGrid
        CTDocGrid docgrid = wmlObjectFactory.createCTDocGrid();
        sectpr.setDocGrid(docgrid);
        docgrid.setLinePitch(BigInteger.valueOf(360));
        // Create object for p
        P p = wmlObjectFactory.createP();
        body.getContent().add(p);
        document.setIgnorable("w14 wp14");
        // Create object for body
        P p2 = wmlObjectFactory.createP();
        document.getContent().add(p2);

        return document;
    }

    public Hdr createHeader() {

        org.docx4j.wml.ObjectFactory wmlObjectFactory = new org.docx4j.wml.ObjectFactory();

        Hdr hdr = wmlObjectFactory.createHdr();
        // Create object for p
        P p = wmlObjectFactory.createP();
        hdr.getContent().add(p);
        // Create object for pPr
        PPr ppr = wmlObjectFactory.createPPr();
        p.setPPr(ppr);
        // Create object for pStyle
        PPrBase.PStyle pprbasepstyle = wmlObjectFactory.createPPrBasePStyle();
        ppr.setPStyle(pprbasepstyle);
        pprbasepstyle.setVal("Header");
        // Create object for pBdr
//        PPrBase.PBdr pprbasepbdr = wmlObjectFactory.createPPrBasePBdr();
//        ppr.setPBdr(pprbasepbdr);
        // Create object for left
//        CTBorder border = wmlObjectFactory.createCTBorder();
//        pprbasepbdr.setLeft(border);
//        border.setSz(BigInteger.valueOf(4));
//        border.setVal(org.docx4j.wml.STBorder.SINGLE);
//        border.setColor("auto");
//        border.setSpace(BigInteger.valueOf(4));
//        // Create object for right
//        CTBorder border2 = wmlObjectFactory.createCTBorder();
//        pprbasepbdr.setRight(border2);
//        border2.setSz(BigInteger.valueOf(4));
//        border2.setVal(org.docx4j.wml.STBorder.SINGLE);
//        border2.setColor("auto");
//        border2.setSpace(BigInteger.valueOf(4));
//        // Create object for top
//        CTBorder border3 = wmlObjectFactory.createCTBorder();
//        pprbasepbdr.setTop(border3);
//        border3.setSz(BigInteger.valueOf(4));
//        border3.setVal(org.docx4j.wml.STBorder.SINGLE);
//        border3.setColor("auto");
//        border3.setSpace(BigInteger.valueOf(1));
//        // Create object for bottom
//        CTBorder border4 = wmlObjectFactory.createCTBorder();
//        pprbasepbdr.setBottom(border4);
//        border4.setSz(BigInteger.valueOf(4));
//        border4.setVal(org.docx4j.wml.STBorder.SINGLE);
//        border4.setColor("auto");
//        border4.setSpace(BigInteger.valueOf(1));
//        // Create object for bar
//        CTBorder border5 = wmlObjectFactory.createCTBorder();
//        pprbasepbdr.setBar(border5);
//        border5.setSz(BigInteger.valueOf(4));
//        border5.setVal(org.docx4j.wml.STBorder.SINGLE);
//        border5.setColor("auto");
//        // Create object for between
//        CTBorder border6 = wmlObjectFactory.createCTBorder();
//        pprbasepbdr.setBetween(border6);
//        border6.setSz(BigInteger.valueOf(4));
//        border6.setVal(org.docx4j.wml.STBorder.SINGLE);
//        border6.setColor("auto");
//        border6.setSpace(BigInteger.valueOf(1));
        // Create object for r
        R r = wmlObjectFactory.createR();
        p.getContent().add(r);
        // Create object for t (wrapped in JAXBElement) 
        org.docx4j.wml.Text text = wmlObjectFactory.createText();
        JAXBElement<org.docx4j.wml.Text> textWrapped = wmlObjectFactory.createRT(text);
        r.getContent().add(textWrapped);
        text.setValue("teste");
        // Create object for bookmarkStart (wrapped in JAXBElement) 
//        CTBookmark bookmark = wmlObjectFactory.createCTBookmark();
//        JAXBElement<org.docx4j.wml.CTBookmark> bookmarkWrapped = wmlObjectFactory.createPBookmarkStart(bookmark);
//        p.getContent().add(bookmarkWrapped);
//        bookmark.setName("_GoBack");
//        bookmark.setId(BigInteger.valueOf(0));
//        // Create object for bookmarkEnd (wrapped in JAXBElement) 
//        CTMarkupRange markuprange = wmlObjectFactory.createCTMarkupRange();
//        JAXBElement<org.docx4j.wml.CTMarkupRange> markuprangeWrapped = wmlObjectFactory.createPBookmarkEnd(markuprange);
//        p.getContent().add(markuprangeWrapped);
//        markuprange.setId(BigInteger.valueOf(0));

        return hdr;
    }

    public static void main(String[] args) {
        Foo f = new Foo();
        try {

            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
            // Create the package
            // Create the main document part (word/document.xml)
            MainDocumentPart wordDocumentPart = wordMLPackage.getMainDocumentPart();

//            wordDocumentPart.setJaxbElement(f.createIt());
            wordDocumentPart.addObject(f.createHeader());
            wordMLPackage.save(new java.io.File("HelloWord1.docx"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
