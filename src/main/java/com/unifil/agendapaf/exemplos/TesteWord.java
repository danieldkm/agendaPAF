package com.unifil.agendapaf.exemplos;

import org.docx4j.openpackaging.packages.WordprocessingMLPackage;

/**
 *
 * @author danielmorita
 */
public class TesteWord {

    public static void main(String[] args) {
        try {
            WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.createPackage();
            wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Title", "Hello Word!");
            wordMLPackage.getMainDocumentPart().addStyledParagraphOfText("Subtitle",
                    "This is a subtitle!");
            wordMLPackage.save(new java.io.File("HelloWord1.docx"));
        } catch (Exception e) {
        }
    }

}
