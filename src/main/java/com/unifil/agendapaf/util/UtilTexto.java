package com.unifil.agendapaf.util;

import java.io.IOException;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.util.PDFTextStripper;

/**
 *
 * @author danielmorita
 */
public class UtilTexto {

    /**
     * Le o conteudo (texto) de um arquivo pdf
     *
     * @param caminho caminho do arquivo pdf
     * @return string do conteudo extraido
     */
    public static String extraiTextoDoPDF(String caminho) {
        PDDocument pdfDocument = null;
        try {
            pdfDocument = PDDocument.load(caminho);
            PDFTextStripper stripper = new PDFTextStripper();
            String texto = stripper.getText(pdfDocument);
            return texto;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (pdfDocument != null) {
                try {
                    pdfDocument.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public static String removeAllSimbolsExceptNumber(String textoNumerico) {
        if (textoNumerico != null) {
            textoNumerico = textoNumerico.replaceAll("[^0-9]", "");
        }
//        cpf = cpf.replaceFirst("([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})$", "$1.$2.$3-$4");
        return textoNumerico;
    }

    public static String formatarMascaraCpf(String cpf) {
        cpf = cpf.replaceAll("[^0-9]", "");
        cpf = cpf.replaceFirst("([0-9]{3})([0-9]{3})([0-9]{3})([0-9]{2})$", "$1.$2.$3-$4");
        return cpf;
    }

    public static String formatarMascaraCnpj(String cnpj) {
        cnpj = cnpj.replaceAll("[^0-9]", "");
        cnpj = cnpj.replaceFirst("([0-9]{2})([0-9]{3})([0-9]{3})([0-9]{4})([0-9]{2})$", "$1.$2.$3/$4-$5");
        return cnpj;
    }

    public static String formatarMascaraTelefone(String telefone) {
        telefone = telefone.replaceAll("[^0-9]", "");
        telefone = telefone.replaceFirst("([0-9]{2})([0-9]{4})([0-9]{4})$", "($1) $2 - $3");
        return telefone;
    }

    public static String formatarMascaraCep(String cep) {
        cep = cep.replaceAll("[^0-9]", "");
        cep = cep.replaceFirst("([0-9]{5})([0-9]{3})$", "$1-$2");
        return cep;
    }

    /**
     *
     * Extrai o conteudo do arquivo indicado
     *
     * @param args argumentos
     */
    public static void main(String[] args) {
        String caminho = "modelo/TERMO DE AUTENTICAÇÃO MODELO.pdf";
        String texto = extraiTextoDoPDF(caminho);
        System.out.println(texto);
    }
}
