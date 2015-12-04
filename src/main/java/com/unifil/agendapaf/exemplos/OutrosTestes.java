package com.unifil.agendapaf.exemplos;

import com.unifil.agendapaf.model.Endereco;
import com.unifil.agendapaf.model.email.Emails;
import com.unifil.agendapaf.model.email.FerramentaEmail;
import com.unifil.agendapaf.util.UtilConverter;
import com.unifil.agendapaf.util.UtilFile;
import com.unifil.agendapaf.util.UtilTexto;
import com.unifil.agendapaf.view.util.enums.EnumCaminho;
import java.io.File;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author danielmorita
 */
public class OutrosTestes {

    public static void main(String[] args) {
//        Endereco e1 = new Endereco();
//        Endereco e2;
//        
//        e1.setBairro("aa");
//        e2 = e1.clone();
//        e2.setBairro("bb");
//        System.out.println(e1.getBairro());
//        System.out.println(e2.getBairro());

//        System.out.println(DateFormat.getDateInstance(DateFormat.LONG).format(UtilConverter.converterLocalDateToUtilDate(LocalDate.now())));
//        System.out.println(DateFormat.getDateInstance(DateFormat.MEDIUM).format(UtilConverter.converterLocalDateToUtilDate(LocalDate.now())));
//        System.out.println("" + UtilTexto.formatarMascaraCnpj("10293847564732"));
//        System.out.println("" + UtilTexto.formatarMascaraCpf("04635185940"));
//        System.out.println(LocalDate.now().format(DateTimeFormatter.BASIC_ISO_DATE));
//        System.out.println(LocalDate.now().format(DateTimeFormatter.ISO_DATE));
//        System.out.println(LocalDate.now().format(DateTimeFormatter.ISO_LOCAL_DATE));
//        System.out.println(LocalDate.now().format(DateTimeFormatter.ISO_ORDINAL_DATE));
//        System.out.println(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL)));
//        System.out.println(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG)));
//        System.out.println(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
//        System.out.println(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT)));
//        System.out.println("FLOAT arrenda " + Math.round(1.5));
//        System.out.println("FLOAT arrenda " + Math.floor(1.5));
////        System.out.println(LocalDate.now().format(DateTimeFormatter.ofLocalizedDate(FormatStyle.valueOf("dd/MM/yyyy"))));
//
//        Locale ptBR = new Locale("pt", "BR");
//        DateFormat dateFormat
//                = DateFormat.getDateInstance(DateFormat.FULL, ptBR);
//        System.out.println(dateFormat.format(new Date()));
//
//        DateFormat timeFormat
//                = DateFormat.getTimeInstance(DateFormat.MEDIUM, ptBR);
//        System.out.println(timeFormat.format(new Date()));
//
//        NumberFormat numberFormat
//                = NumberFormat.getNumberInstance(ptBR); //para números
//        System.out.println(numberFormat.format(13.23));
//
//        NumberFormat moedaFormat
//                = NumberFormat.getCurrencyInstance(ptBR);  //para moedas
//        System.out.println(moedaFormat.format(13.23));
//        try {
//            System.out.println(moedaFormat.parse("13,23"));
//        } catch (ParseException ex) {
//            Logger.getLogger(OutrosTestes.class.getName()).log(Level.SEVERE, null, ex);
//        }
        com.unifil.agendapaf.model.email.Email teste = new com.unifil.agendapaf.model.email.Email();
        teste.setAssunto("Assunto");
        teste.setConteudo("conteudo");
        teste.setDestinatario("destinatario");
        System.out.println("" + LocalDate.now());
        teste.setData(LocalDateTime.now());
        UtilFile uf = new UtilFile();
        FerramentaEmail fe = (FerramentaEmail) uf.unmarshalFromFile(FerramentaEmail.class, EnumCaminho.DiretorioEmail.getCaminho() + EnumCaminho.XMLFerramentaEmail.getCaminho());
        teste.setRemente(fe);
        uf.criarDiretorio(EnumCaminho.DiretorioEmailEnviadas.getCaminho() + "/" + fe.getEmail());
        Emails e = new Emails();
        e.getEmails().add(teste);
        System.out.println("" + EnumCaminho.DiretorioEmailEnviadas.getCaminho() + "/" + fe.getEmail());
        uf.salvarArquivo(new File(EnumCaminho.DiretorioEmailEnviadas.getCaminho() + "/" + fe.getEmail() + "/" + EnumCaminho.XMLEmails.getCaminho()), uf.marshal(e));
    }

}
