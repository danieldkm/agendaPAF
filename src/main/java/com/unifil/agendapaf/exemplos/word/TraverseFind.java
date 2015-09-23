/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unifil.agendapaf.exemplos.word;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;

import org.docx4j.TraversalUtil;
import org.docx4j.XmlUtils;
import org.docx4j.TraversalUtil.CallbackImpl;
import org.docx4j.finders.ClassFinder;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.docx4j.wml.BooleanDefaultTrue;
import org.docx4j.wml.CTFFCheckBox;
import org.docx4j.wml.FldChar;

public class TraverseFind {

    /**
     * Example of how to find an object in document.xml via traversal (as
     * opposed to XPath)
     * http://stackoverflow.com/questions/15763778/docx4j-checking-checkboxes
     * https://github.com/plutext/docx4j/blob/master/src/samples/docx4j/org/docx4j/samples/TraverseFind.java
     */
    public static void main(String[] args) throws Exception {

        String inputfilepath = System.getProperty("user.dir") + "/docx/b.docx";

        WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new java.io.File(inputfilepath));
        MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();
//        System.out.println(XmlUtils.marshaltoString(documentPart.getJaxbElement(), true, true));
//        System.out.println("TESTE ");
//        System.out.println("");

        ClassFinder finder = new ClassFinder(FldChar.class);
        new TraversalUtil(documentPart.getContent(), finder);

//        System.out.println("got " + finder.results.size() + " of type " + finder.typeToFind.getName());
        System.out.println("got " + finder.results.size());

        for (Object o : finder.results) {

            Object o2 = XmlUtils.unwrap(o);
            // this is ok, provided the results of the Callback
            // won't be marshalled          
            FldChar fldChar = (FldChar) o;
            if (fldChar.getFfData() != null) {
//                System.out.println("getFldData " + fldChar.getFfData().getParent());
                for (JAXBElement<?> nameOrEnabledOrCalcOnExit : fldChar.getFfData().getNameOrEnabledOrCalcOnExit()) {
                    if (nameOrEnabledOrCalcOnExit.getValue() instanceof CTFFCheckBox) {
                        CTFFCheckBox ccb = (CTFFCheckBox) nameOrEnabledOrCalcOnExit.getValue();

                        System.out.println("COMBO BOX is checked? " + ccb.getDefault().isVal());
                        System.out.println("");
                        ccb.setDefault(new BooleanDefaultTrue());
                    }
                }
            }
            if (o2 instanceof org.docx4j.wml.Text) {

                org.docx4j.wml.Text txt = (org.docx4j.wml.Text) o2;

                System.out.println("org.docx4j.wml.Text " + txt.getValue());

            } else {
                System.out.println("FLDCHARS " + XmlUtils.marshaltoString(o, true, true));
            }
        }

//        wordMLPackage.save(new File("docx/bAllChecked.docx"));
    }

    public static class Finder extends CallbackImpl {

        protected Class<?> typeToFind;

        protected Finder(Class<?> typeToFind) {
            this.typeToFind = typeToFind;
        }

        public List<Object> results = new ArrayList<Object>();

        @Override
        public List<Object> apply(Object o) {

            // Adapt as required
            if (o.getClass().equals(typeToFind)) {
                results.add(o);
            }
            return null;
        }
    }

}
