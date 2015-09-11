/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unifil.agendapaf.testes;

import com.unifil.agendapaf.model.laudo.LaudoType;
import com.unifil.agendapaf.model.laudo.MarcaModeloType;
import com.unifil.agendapaf.model.laudo.MarcasModelosCompativeisType;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

/**
 *
 * @author danielmorita
 */
public class TesteUnMarshall {

    public MarcasModelosCompativeisType run() {

        String csvFile = "Marca e modelo.csv";
        BufferedReader br = null;
        String line = "";
        String cvsSplitBy = ";";

        MarcasModelosCompativeisType mmct = new MarcasModelosCompativeisType();
        MarcaModeloType mmt = null;
        try {

            br = new BufferedReader(new FileReader(csvFile));
            while ((line = br.readLine()) != null) {
                String[] m = line.split(cvsSplitBy);
                mmt.setMarca(m[0]);
                mmt.setModelo(m[1]);
                mmct.getMarcaModelo().add(mmt);
//                System.out.println("Marca = " + m[0]
//                        + " , modelo=" + m[1]);;

            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return mmct;
    }

    public static void main(String[] args) {
        TesteUnMarshall t = new TesteUnMarshall();
        LaudoType l = (LaudoType) t.unmarshalFromFile(LaudoType.class, "out.xml");
//        System.out.println("Laudo " + l);
        System.out.println("LAUDO " + l.getVersao());
        System.out.println("Vers�o " + l.getVersao());
        System.out.println("N�mero " + l.getMensagem().getNumero());
        
//        for (MarcaModeloType m
//                : l.getMarcaModelo()) {
//            System.out.println("Marca " + m.getMarca());
//            System.out.println("Modelo " + m.getModelo());
//        }
//        t.run();
    }

    /**
     * Realiza a conversao (unmarshal) de um arquivo XML em um objeto do seu
     * tipo.
     *
     * @param clazz classe referente ao objeto a ser criado a partir do XML.
     * @param fileXml nome do arquivo XML a ser convertido em objeto.
     * @return novo objeto.
     */
    public Object unmarshalFromFile(Class clazz, String fileXml) {
        JAXBContext context = null;
        File file = new File(fileXml);
        System.out.println("FILE " + file.getAbsolutePath());
        try {
            context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return unmarshaller.unmarshal(new FileInputStream(file));
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
