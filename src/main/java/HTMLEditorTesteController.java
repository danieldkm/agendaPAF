/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Application;
import javafx.event.Event;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.web.HTMLEditor;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author danielmorita
 */
public class HTMLEditorTesteController extends Application implements Initializable {

    public static void main(String[] args) {
        launch(args);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        editor.setHtmlText("<html>\"\n"
                + "            + \"<head>\"\n"
                + "            + \"<title>A Test</title>\"\n"
                + "            + \"</head>\"\n"
                + "            + \"<body>This is just a Test</body>\"\n"
                + "            + \"</html>");
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        HTMLEditor editor = new HTMLEditor();
//        editor.setHtmlText(
//                "<html>\n"
//                    + "<head>\n"
//                        + "<title>A Test</title>\n"
//                    + "</head>\n"
//                    + "<body>This is just a Test"
//                        + "<table border=\"1\">\n"
//                            + "<tr>\n"
//                                + "<td>linha 1, célula 1</td>\n"
//                                + "<td>linha 1, célula 2</td>\n"
//                            + "</tr>\n"
//                            + "<tr>\n"
//                                + "<td>linha 2, célula 1</td>\n"
//                                + "<td>linha 2, célula 2</td>\n"
//                            + "</tr>\n"
//                        + "</table>"
//                    + "</body>\"\n"
//                + "</html>");
        editor.setHtmlText(
                "<?xml version=\"1.0\" encoding=\"utf-8\"?><!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
"<html xmlns:r=\"http://schemas.openxmlformats.org/officeDocument/2006/relationships\"><head><meta content=\"text/html; charset=utf-8\" http-equiv=\"Content-Type\" /><style><!--/*paged media */ div.header {display: none }div.footer {display: none } /*@media print { */div.header {display: block; position: running(header) }div.footer {display: block; position: running(footer) }@page { size: A4; margin: 10%; @top-center {content: element(header) } @bottom-center {content: element(footer) } }/*element styles*/ .del  {text-decoration:line-through;color:red;} .ins {text-decoration:none;background:#c0ffc0;padding:1px;}\n" +
" /* TABLE STYLES */ \n" +
"\n" +
" /* PARAGRAPH STYLES */ \n" +
".DocDefaults {display:block;margin-top: 0in;margin-bottom: 0in;line-height: 100%;font-size: 10.0pt;}\n" +
".Normal {display:block;}\n" +
".Cabealho {display:block;}\n" +
".Rodap {display:block;}\n" +
".TabelaSubtitulo {display:block;text-align: center;margin-top: 0.02in;margin-bottom: 0.02in;font-size: 9.0pt;}\n" +
".Ttulo2 {display:block;text-align: center;page-break-after: avoid;font-weight: bold;color: #000000;font-size: 14.0pt;}\n" +
".Ttulo3 {display:block;text-align: center;page-break-after: avoid;margin-top: 1mm;font-weight: bold;font-size: 14.0pt;}\n" +
"\n" +
" /* CHARACTER STYLES */ span.Fontepargpadro {display:inline;}\n" +
"span.Nmerodepgina {display:inline;}\n" +
"--></style><script type=\"text/javascript\"><!--function toggleDiv(divid){if(document.getElementById(divid).style.display == 'none'){document.getElementById(divid).style.display = 'block';}else{document.getElementById(divid).style.display = 'none';}}\n" +
"--></script></head><body>\n" +
"  \n" +
"  <!-- userBodyTop goes here -->\n" +
"  \n" +
"  <div class=\"header\">\n" +
"  \n" +
"  <table id=\"docx4j_tbl_0\" style=\"position: relative; margin-left: 0mm;table-layout: fixed;vertical-align: top;border-collapse: collapse;width: 173mm;\"><colgroup><col style=\"width: 23.19%;\" /><col style=\"width: 60.8%;\" /><col style=\"width: 16.01%;\" /></colgroup><tbody><tr style=\"page-break-inside: avoid;\"><td rowspan=\"2\" style=\"border-bottom-style: none;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 0.26mm;border-right-color: #000000;border-top-style: solid;border-top-width: 1px;border-top-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Cabealho Normal DocDefaults \" style=\"text-align: center;position: relative; margin-left: 1mm;margin-top: 1mm;margin-bottom: 1mm;\"><span class=\"Fontepargpadro \" style=\"\"><img height=\"33\" id=\"rId1\" src=\"docx/a.docx_files/98e044a7-f4ed-477c-90f4-135600a7ff8bimage1.png\" width=\"111\" /></span></p></td><td rowspan=\"2\" style=\"border-bottom-style: none;border-left-style: solid;border-left-width: 0.26mm;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: solid;border-top-width: 1px;border-top-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Ttulo3 Normal DocDefaults \" style=\"margin-top: 0.08in;\"><span class=\"\" style=\"font-family: 'Arial';\">DECLARAÇÃO AO ATENDIMENTO DO REQUISITO I DO ANEXO I</span></p></td><td style=\"border-bottom-style: none;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: solid;border-top-width: 1px;border-top-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Ttulo2 Normal DocDefaults \" style=\"text-align: left;position: relative; margin-left: 1mm;margin-top: 0mm;\"><span class=\"Fontepargpadro \" style=\"font-weight: normal;font-size: 8.0pt;;font-family: 'Arial';\">Nº</span><span class=\"Fontepargpadro \" style=\"font-weight: normal;font-size: 8.0pt;;font-family: 'Arial';white-space:pre-wrap;\"> DO LAUDO:</span></p></td></tr><tr style=\"page-break-inside: avoid;\"><td style=\"border-bottom-style: solid;border-bottom-width: 1px;border-bottom-color: #000000;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;padding-left: 0mm;padding-right: 0mm;vertical-align: middle;\">\n" +
"  \n" +
"  <p class=\"Ttulo2 Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;\"><span class=\"Fontepargpadro \" style=\"font-weight: normal;font-size: 8.0pt;;font-family: 'Arial';\">IFL___2015</span></p></td></tr></tbody></table>\n" +
"  \n" +
"  <p class=\"Cabealho Normal DocDefaults \"> </p></div>\n" +
"  \n" +
"  <div class=\"document\">\n" +
"  \n" +
"  <table id=\"docx4j_tbl_1\" style=\"position: relative; margin-left: -0in;table-layout: fixed;vertical-align: top;border-collapse: collapse;width: 173mm;\"><colgroup><col style=\"width: 100%;\" /></colgroup><tbody><tr style=\"page-break-inside: avoid;\"><td style=\"border-bottom-style: solid;border-bottom-width: 1px;border-bottom-color: #000000;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: solid;border-top-width: 1px;border-top-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Rodap Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;\"><span class=\"Fontepargpadro \" style=\"font-weight: bold;font-size: 14.0pt;;font-family: 'Times New Roman';\">1. Identificação da Empresa:</span></p></td></tr><tr style=\"page-break-inside: avoid;\"><td style=\"background-color: #C0C0C0;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Rodap Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"><span class=\"Fontepargpadro \" style=\"font-size: 9.0pt;;font-family: 'Arial';\">1.1 Razão Social:</span></p></td></tr><tr style=\"page-break-inside: avoid;\"><td style=\"border-bottom-style: solid;border-bottom-width: 1px;border-bottom-color: #000000;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Rodap Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"><a name=\"Texto11\" /></p></td></tr><tr style=\"page-break-inside: avoid;\"><td style=\"background-color: #C0C0C0;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Rodap Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"><span class=\"Fontepargpadro \" style=\"font-size: 9.0pt;;font-family: 'Arial';\">1.2</span><span class=\"Fontepargpadro \" style=\"font-size: 9.0pt;;font-family: 'Arial';white-space:pre-wrap;\"> CNPJ:</span></p></td></tr><tr style=\"page-break-inside: avoid;\"><td style=\"border-bottom-style: solid;border-bottom-width: 1px;border-bottom-color: #000000;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Rodap Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"> </p></td></tr></tbody></table>\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \"> </p>\n" +
"  \n" +
"  <table id=\"docx4j_tbl_2\" style=\"border-bottom-style: solid;border-bottom-width: 1px;border-bottom-color: #000000;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: solid;border-top-width: 1px;border-top-color: #000000;position: relative; margin-left: 0mm;table-layout: fixed;vertical-align: top;border-collapse: collapse;width: 6.8in;\"><colgroup><col style=\"width: 50.73%;\" /><col style=\"width: 49.27%;\" /></colgroup><tbody><tr style=\"page-break-inside: avoid;\"><td colspan=\"2\" style=\"border-bottom-style: solid;border-bottom-width: 0.26mm;border-bottom-color: #000000;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: solid;border-top-width: 1px;border-top-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 0.08in;margin-bottom: 0.08in;\"><span class=\"Fontepargpadro \" style=\"font-weight: bold;font-size: 14.0pt;;font-family: 'Arial';\">2.</span><span class=\"Fontepargpadro \" style=\"font-weight: bold;font-size: 14.0pt;\">   <span class=\"\" style=\"font-family: 'Arial';\">Identificação do Programa Aplicativo Fiscal ( PAF-ECF):</span></span></p></td></tr><tr style=\"page-break-inside: avoid;\"><td style=\"background-color: #C0C0C0;border-bottom-style: none;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 0.26mm;border-right-color: #000000;border-top-style: solid;border-top-width: 0.26mm;border-top-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"><span class=\"Fontepargpadro \" style=\"font-size: 9.0pt;;font-family: 'Arial';\">2.1 Nome</span><span class=\"Fontepargpadro \" style=\"font-size: 9.0pt;;font-family: 'Arial';white-space:pre-wrap;\"> do Aplicativo</span><span class=\"Fontepargpadro \" style=\"font-size: 9.0pt;;font-family: 'Arial';\">:</span></p></td><td style=\"background-color: #C0C0C0;border-bottom-style: none;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 0.26mm;border-right-color: #000000;border-top-style: solid;border-top-width: 0.26mm;border-top-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"margin-top: 1mm;\"><span class=\"Fontepargpadro \" style=\"font-size: 9.0pt;;font-family: 'Arial';white-space:pre-wrap;\"> 2</span><span class=\"Fontepargpadro \" style=\"font-size: 9.0pt;;font-family: 'Arial';\">.2 Versão:</span></p></td></tr><tr style=\"page-break-inside: avoid;\"><td style=\"border-bottom-style: none;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 0.26mm;border-right-color: #000000;border-top-style: none;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"> </p></td><td style=\"border-bottom-style: none;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 0.26mm;border-right-color: #000000;border-top-style: none;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"> </p></td></tr><tr style=\"page-break-inside: avoid;\"><td style=\"background-color: #C0C0C0;border-bottom-style: none;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: solid;border-top-width: 1px;border-top-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Rodap Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"><span class=\"Fontepargpadro \" style=\"font-size: 9.0pt;;font-family: 'Arial';\">2.3 Tamanho em Bytes</span></p></td><td style=\"background-color: #C0C0C0;border-bottom-style: none;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: solid;border-top-width: 1px;border-top-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Rodap Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"><span class=\"Fontepargpadro \" style=\"font-size: 9.0pt;;font-family: 'Arial';\">2.4 Data da Geração</span><span class=\"Fontepargpadro \" style=\"font-size: 9.0pt;;font-family: 'Arial';white-space:pre-wrap;\">: </span></p></td></tr><tr style=\"page-break-inside: avoid;\"><td style=\"border-bottom-style: solid;border-bottom-width: 0.26mm;border-bottom-color: #000000;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: none;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Rodap Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"> </p></td><td style=\"border-bottom-style: solid;border-bottom-width: 0.26mm;border-bottom-color: #000000;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: none;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Rodap Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"> </p></td></tr><tr style=\"page-break-inside: avoid;\"><td colspan=\"2\" style=\"background-color: #C0C0C0;border-bottom-style: none;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: solid;border-top-width: 1px;border-top-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Rodap Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"><span class=\"Fontepargpadro \" style=\"font-size: 9.0pt;;font-family: 'Arial';\">2.5</span><span class=\"Fontepargpadro \" style=\"font-size: 9.0pt;;font-family: 'Arial';white-space:pre-wrap;\"> Principal Arquivo Executável:</span></p></td></tr><tr style=\"page-break-inside: avoid;\"><td colspan=\"2\" style=\"border-bottom-style: solid;border-bottom-width: 1px;border-bottom-color: #000000;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: none;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Rodap Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"> </p></td></tr><tr style=\"page-break-inside: avoid;\"><td colspan=\"2\" style=\"background-color: #C0C0C0;border-bottom-style: none;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: solid;border-top-width: 1px;border-top-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Rodap Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"><span class=\"Fontepargpadro \" style=\"font-size: 9.0pt;;font-family: 'Arial';\">2.6</span><span class=\"Fontepargpadro \" style=\"font-size: 9.0pt;;font-family: 'Arial';white-space:pre-wrap;\"> Código de autenticação do principal arquivo executável (MD -5):</span></p></td></tr><tr style=\"page-break-inside: avoid;\"><td colspan=\"2\" style=\"border-bottom-style: solid;border-bottom-width: 1px;border-bottom-color: #000000;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: none;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Rodap Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"> </p></td></tr></tbody></table>\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \"> </p>\n" +
"  \n" +
"  <table id=\"docx4j_tbl_3\" style=\"border-bottom-style: solid;border-bottom-width: 1px;border-bottom-color: #000000;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: solid;border-top-width: 1px;border-top-color: #000000;position: relative; margin-left: 0mm;table-layout: fixed;vertical-align: top;border-collapse: collapse;width: 173mm;\"><colgroup><col style=\"width: 66.2%;\" /><col style=\"width: 33.8%;\" /></colgroup><tbody><tr style=\"page-break-inside: avoid;\"><td colspan=\"2\" style=\"border-bottom-style: solid;border-bottom-width: 0.26mm;border-bottom-color: #000000;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: solid;border-top-width: 1px;border-top-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 0.08in;margin-bottom: 0.08in;\"><br style=\"page-break-before:always\" /><span class=\"Fontepargpadro \" style=\"font-weight: bold;font-size: 14.0pt;;font-family: 'Arial';\">3</span><span class=\"Fontepargpadro \" style=\"font-weight: bold;font-size: 14.0pt;;font-family: 'Arial';\">.</span><span class=\"Fontepargpadro \" style=\"font-weight: bold;font-size: 14.0pt;\">   <span class=\"\" style=\"font-family: 'Arial';\">Declaração:</span></span></p></td></tr><tr style=\"page-break-inside: avoid;\"><td colspan=\"2\" style=\"border-bottom-style: solid;border-bottom-width: 0.26mm;border-bottom-color: #000000;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: solid;border-top-width: 1px;border-top-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"text-align: justify;position: relative; margin-left: 1mm;margin-top: 0.08in;margin-bottom: 0.08in;\"><span class=\"Fontepargpadro \" style=\"font-family: 'Arial';white-space:pre-wrap;\">Nos termos da legislação vigente e para fins de atendimento ao Requisito I do </span><span class=\"Fontepargpadro \" style=\"font-family: 'Arial';\">Anexo I do ATO COTEPE que especifica o PAF-ECF, na condição de responsável legal pelo seu desenvolvimento,</span><span class=\"Fontepargpadro \" style=\"font-family: 'Arial';white-space:pre-wrap;\"> </span><span class=\"Fontepargpadro \" style=\"font-family: 'Arial';\">declaro que o programa acima identificado não possibilita ao usuário possuir informação contábil diversa daquela que é, por lei, fornecida a Fazenda Pública, conforme inciso V do art 2° da Lei 8.137/90.</span></p>\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"text-align: justify;position: relative; margin-left: 1mm;margin-top: 0.08in;margin-bottom: 0.08in;\"> </p>\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"text-align: justify;position: relative; margin-left: 1mm;margin-top: 0.08in;margin-bottom: 0.08in;\"> </p>\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"text-align: justify;position: relative; margin-left: 1mm;margin-top: 0.08in;margin-bottom: 0.08in;\"> </p>\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"text-align: justify;position: relative; margin-left: 1mm;margin-top: 0.08in;margin-bottom: 0.08in;\"> </p>\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"text-align: justify;position: relative; margin-left: 1mm;margin-top: 0.08in;margin-bottom: 0.08in;\"> </p>\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"text-align: justify;position: relative; margin-left: 1mm;margin-top: 0.08in;margin-bottom: 0.08in;\"> </p></td></tr><tr style=\"page-break-inside: avoid;\"><td colspan=\"2\" style=\"border-bottom-style: solid;border-bottom-width: 0.26mm;border-bottom-color: #000000;border-left-style: solid;border-left-width: 0.26mm;border-left-color: #000000;border-right-style: solid;border-right-width: 0.26mm;border-right-color: #000000;border-top-style: solid;border-top-width: 1px;border-top-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"text-align: center;position: relative; margin-left: 1mm;margin-top: 0.08in;margin-bottom: 0.08in;\"><span class=\"Fontepargpadro \" style=\"font-weight: bold;;font-family: 'Times New Roman';\">IDENTIFICAÇÃO DO SÓCIO, RESPONSÁVEL OU REPRESENTANTE LEGAL DA EMPRESA</span></p></td></tr><tr style=\"height: 0.24in;page-break-inside: avoid;\"><td style=\"background-color: #C0C0C0;border-bottom-style: none;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: solid;border-top-width: 1px;border-top-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"><span class=\"Fontepargpadro \" style=\"font-size: 9.0pt;;font-family: 'Arial';\">3.1 Nome:</span><span class=\"Fontepargpadro \" style=\"font-size: 9.0pt;;font-family: 'Arial';white-space:pre-wrap;\"> </span></p></td><td style=\"background-color: #C0C0C0;border-bottom-style: none;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: solid;border-top-width: 1px;border-top-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"><span class=\"Fontepargpadro \" style=\"font-size: 9.0pt;;font-family: 'Arial';\">3.2 CPF:</span></p></td></tr><tr style=\"page-break-inside: avoid;\"><td style=\"border-bottom-style: none;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: none;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"> </p></td><td style=\"border-bottom-style: none;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: none;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"> </p></td></tr><tr style=\"height: 0.24in;page-break-inside: avoid;\"><td style=\"background-color: #C0C0C0;border-bottom-style: none;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 0.26mm;border-right-color: #000000;border-top-style: solid;border-top-width: 1px;border-top-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"><span class=\"Fontepargpadro \" style=\"font-size: 9.0pt;;font-family: 'Arial';\">3.3 Local e Data:</span></p></td><td style=\"background-color: #C0C0C0;border-bottom-style: none;border-left-style: solid;border-left-width: 0.26mm;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: solid;border-top-width: 1px;border-top-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"margin-top: 1mm;\"><span class=\"Fontepargpadro \" style=\"font-size: 9.0pt;;font-family: 'Arial';white-space:pre-wrap;\"> 3.4  RG:</span></p></td></tr><tr style=\"page-break-inside: avoid;\"><td style=\"border-bottom-style: solid;border-bottom-width: 0.26mm;border-bottom-color: #000000;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 0.26mm;border-right-color: #000000;border-top-style: none;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"><span class=\"Fontepargpadro \" style=\"font-weight: bold;;font-family: 'Arial';white-space:pre-wrap;\">Londrina,   de </span><span class=\"Fontepargpadro \" style=\"font-weight: bold;;font-family: 'Arial';\">Setembro</span><span class=\"Fontepargpadro \" style=\"font-weight: bold;;font-family: 'Arial';white-space:pre-wrap;\"> de 2015.</span></p></td><td style=\"border-bottom-style: solid;border-bottom-width: 0.26mm;border-bottom-color: #000000;border-left-style: solid;border-left-width: 0.26mm;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: none;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"position: relative; margin-left: 1mm;margin-top: 1mm;\"> </p></td></tr><tr style=\"height: 1.08in;\"><td colspan=\"2\" style=\"border-bottom-style: solid;border-bottom-width: 1px;border-bottom-color: #000000;border-left-style: solid;border-left-width: 1px;border-left-color: #000000;border-right-style: solid;border-right-width: 1px;border-right-color: #000000;border-top-style: solid;border-top-width: 1px;border-top-color: #000000;padding-left: 0mm;padding-right: 0mm;\">\n" +
"  \n" +
"  <p class=\"TabelaSubtitulo Normal DocDefaults \"> </p>\n" +
"  \n" +
"  <p class=\"TabelaSubtitulo Normal DocDefaults \"> </p>\n" +
"  \n" +
"  <p class=\"TabelaSubtitulo Normal DocDefaults \"> </p>\n" +
"  \n" +
"  <p class=\"TabelaSubtitulo Normal DocDefaults \"> </p>\n" +
"  \n" +
"  <p class=\"TabelaSubtitulo Normal DocDefaults \"> </p>\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \" style=\"text-align: center;\"><span class=\"\" style=\"font-family: 'Times New Roman';\">Assinatura do Sócio, Responsável ou Representante Legal da Empresa</span></p></td></tr></tbody></table>\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \"> </p></div>\n" +
"  \n" +
"  <div class=\"footnotes\">\n" +
"  \n" +
"  <p class=\"Normal DocDefaults \"> </p></div>\n" +
"  \n" +
"  \n" +
"  \n" +
"  <div class=\"footer\">\n" +
"  \n" +
"  <p class=\"Rodap Normal DocDefaults \"><span class=\"\" style=\"\">1</span></p>\n" +
"  \n" +
"  <p class=\"Rodap Normal DocDefaults \"> </p></div>\n" +
"  \n" +
"  <!-- userBodyTail goes here -->\n" +
"  \n" +
"  </body></html>");

        TextArea area = new TextArea();
        area.setText(editor.getHtmlText());
        editor.addEventHandler(EventType.ROOT, (Event event) -> {
            area.setText(editor.getHtmlText());
        });

        VBox root = new VBox();
        VBox.setVgrow(area, Priority.ALWAYS);
        root.getChildren().addAll(editor, area);

        Scene scene = new Scene(root, 300, 250);

        primaryStage.setScene(scene);
        primaryStage.show();
        System.out.println("???s "+editor.getHtmlText());
                
    }

    @FXML
    private HTMLEditor editor;

}
