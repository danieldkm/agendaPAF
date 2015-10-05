package com.unifil.agendapaf.util;

import com.unifil.agendapaf.model.laudo.MarcaModeloType;
import com.unifil.agendapaf.model.laudo.MarcasModelosCompativeisType;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javax.xml.XMLConstants;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import org.xml.sax.SAXException;

/**
 *
 * @author danielmorita
 */
public class UtilFile {

    private ObservableList<String> empresas = FXCollections.observableArrayList();
    private ObservableList<String> docs = FXCollections.observableArrayList();
    private ObservableList<String> laudos = FXCollections.observableArrayList();
    private String diretorioInicial = "xml/"; //local padr�o para armazenar arquivos XML

    public UtilFile() {
    }

    /**
     * Cria pasta
     *
     * @param novoDiretorio nome da pasta
     * @return nomeDiretorio
     */
    public String criaDiretorio(String novoDiretorio) {
        String nomeDiretorio = diretorioInicial + novoDiretorio;
        try {
            if (!new File(nomeDiretorio).exists()) { // Verifica se o diret�rio existe.
                (new File(nomeDiretorio)).mkdir();   // Cria o diret�rio   
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return nomeDiretorio;
    }

    /**
     * Deletar pasta
     *
     * @param diretorio nome da pasta
     * @return retorna true ou false a existentcia do diretorio
     */
    public boolean deletarDiretorio(String diretorio) {
        String nomeDiretorio = diretorioInicial + diretorio;
        File dir = new File(nomeDiretorio);
        try {
            if (dir.exists()) { // Verifica se o diret�rio existe.
                if (dir.listFiles().length <= 0) { // verifica se n�o tem nenhum arquivo
                    return (new File(nomeDiretorio)).delete();   // Deletar o diret�rio
                }
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
        return false;
    }

    /**
     * Carrega a lista partir do direito passado por parametro, adicionando numa
     * lista todos os direitos existente.
     *
     * @param directory diretorio no qual irá adicionar na lista "empresas"
     */
    public void listarDireitorio(File directory) {
        if (directory.isDirectory()) {
            if (!directory.getPath().equals("xml")) {
//                System.out.println("Diretorio => " + directory.getName());
                if (!directory.getName().equals("modelo_docxs") && !directory.getName().equals("OLD_XSD")) {
                    empresas.add(directory.getName());
                }
            }
            String[] subDirectory = directory.list();
            if (subDirectory != null) {
                for (String dir : subDirectory) {
                    listarDireitorio(new File(directory + File.separator + dir));
                }
            }
        }
    }

    /**
     * Carrega a lista de arquivos partir do direito passado por parametro,
     * adicionando numa lista.
     *
     * @param directory diretorio no qual contém arquivos
     */
    public void listarArquivos(File directory) {
        String extensao = "";
        for (int i = 0; i < directory.listFiles().length; i++) {
            extensao = directory.listFiles()[i].getName();
            if (extensao.toLowerCase().endsWith("xml") && !extensao.toLowerCase().contains("complementar")) {
                laudos.add(directory.listFiles()[i].getName() + "");
            } else if (extensao.toLowerCase().endsWith("docx")) {
                docs.add(directory.listFiles()[i].getName() + "");
            }
        }
    }

    /**
     * Valida arquivos XML com arquivo XSD
     *
     * @param xsdPath diretorio e nome do arquivo XSD
     * @param xmlPath diretorio e nome do arquivo XML
     * @param isXMLString caso o xml estiver em formato string
     * @return true se validar o arquivo
     */
    public boolean validarXMLSchema(String xsdPath, File xmlPath, boolean isXMLString) {
        //System.out.println("employee.xml validates against Employee.xsd? "+validateXMLSchema("laudo.xsd", "tj.xml"));
        File xml = xmlPath;
        try {
            SchemaFactory factory
                    = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            Schema schema = factory.newSchema(new File(xsdPath));
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(xml));
        } catch (IOException | SAXException e) {
            e.getMessage();
            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), "Erro ao validar o arquivo XML", e.getMessage(), "Exception:");
            System.out.println("Deletou o arquivo?!!!! " + xml.delete());
            return false;
        }
        return true;
    }

    /**
     * Salva um arquivo, depende do parametro
     *
     * @param directory diretorio no qual será criado o arquivo
     * @param txt conteudo a ser inserido no arquivo
     */
    public void salvarArquivo(File directory, String txt) {
        try {
//            InputStream io = new FileInputStream(directory);
//            InputStreamReader reader = new InputStreamReader(io);
//            BufferedReader bufferedReader = new BufferedReader(reader);
            OutputStream out = new FileOutputStream(directory);
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(out, "UTF-8");
            BufferedWriter bf = new BufferedWriter(outputStreamWriter);
//            String linha = bufferedReader.readLine();
            bf.write(txt);
            bf.flush();
//            while (linha != null) {
//                bf.append(linha);
//                bf.newLine();
//                System.out.println(linha);
//                linha = bufferedReader.readLine();
//            }
//
//            bufferedReader.close();
            bf.close();
        } catch (Exception e) {
        }
    }

    /**
     * Converte arquivo CSV em objeto marca e modelos compativeis.
     *
     * @return MarcasModelosCompativeisType contendo a lista contendo
     * MarcaModeloType.
     */
    public MarcasModelosCompativeisType getMarcaEModeloECF() {
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
//                System.out.println("Marca = " + m[0]
//                        + " , modelo=" + m[1]);
                mmt = new MarcaModeloType();
                mmt.setMarca(m[0]);
                mmt.setModelo(m[1]);
                mmct.getMarcaModelo().add(mmt);
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

    /**
     * Converte o objeto em uma String com estrutura XML.
     *
     * @param object objeto a ser convertido em XML.
     * @return String contendo a estrutura XML.
     */
    public String marshal(Object object) {
        final StringWriter out = new StringWriter();
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(object.getClass());
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(
                    javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE
            );
            marshaller.marshal(object, new StreamResult(out));
        } catch (PropertyException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return out.toString();
    }

    /**
     * Converte o objeto em uma estrutura XML.
     *
     * @param object objeto a ser convertido em XML.
     * @param fileName nome do arquivo XML a ser gerado.
     * @return uma string com o conteudo do XML gerado.
     */
    public String marshalToFile(Object object, String fileName) {
        final StringWriter out = new StringWriter();
        JAXBContext context = null;
        Marshaller marshaller = null;
        try {
            context = JAXBContext.newInstance(object.getClass());
            marshaller = context.createMarshaller();
            marshaller.setProperty(
                    javax.xml.bind.Marshaller.JAXB_FORMATTED_OUTPUT,
                    Boolean.TRUE
            );
            marshaller.marshal(object, new StreamResult(out));
        } catch (PropertyException e) {
            e.printStackTrace();
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        Writer writer = null;
        try {
            writer = new FileWriter(fileName);
            marshaller.marshal(object, writer);
        } catch (JAXBException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (writer != null) {
                    writer.close();
                }
            } catch (Exception e) {
                e.getMessage();
            }
        }

        return out.toString();
    }

    /**
     * Converte um string com estrutura XML em um objeto.
     *
     * @param clazz classe referente ao tipo do objeto a ser retornado.
     * @param stringXml string com o conteudo XML a ser convertido em objeto.
     * @return retorna um novo objeto de clazz.
     */
    public Object unmarshal(Class clazz, String stringXml) {
        JAXBContext context = null;
        try {
            context = JAXBContext.newInstance(clazz);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return unmarshaller.unmarshal(
                    new StreamSource(new StringReader(stringXml))
            );
        } catch (JAXBException e) {
            e.printStackTrace();
        }
        return null;
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

    /**
     * Verificar se o arquivo ja existe
     *
     * @param numeroLaudo nome do arquivo a ser verificado
     * @param cbEmpresa todas as subpastas a ser verificada
     * @return true caso o laudo for igual ao numeroLaudo
     */
    public boolean laudoExiste(String numeroLaudo, ComboBox cbEmpresa) {
        numeroLaudo += ".xml";
        for (String s : empresas) {
            setLaudos(FXCollections.observableArrayList());
            listarArquivos(new File(getDiretorioInicial() + s));
            for (String s2 : laudos) {
                if (s2.equals(numeroLaudo)) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Salvar arquivo em XML
     *
     * @param documento conteudo do documento em xml
     * @param file local e nome do arquivo a ser salvo
     */
    public static void salvarArquivoXML(String documento, String file) {
        File exeFile = new File("");
//        File path = new File(exeFile.getAbsolutePath() + file);
        File path = new File(exeFile.getAbsolutePath() + file);
        try {
            PrintWriter writer = new PrintWriter(path);
            writer.println(
                    "<?xml version=\"1.0\" encoding=\"windows-1252\"?>"
            );
            writer.println(documento);
            writer.flush();
            writer.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ObservableList<String> getEmpresas() {
        return empresas;
    }

    public void setEmpresas(ObservableList<String> empresas) {
        this.empresas = empresas;
    }

    public ObservableList<String> getLaudos() {
        return laudos;
    }

    public void setLaudos(ObservableList<String> laudos) {
        this.laudos = laudos;
    }

    public String getDiretorioInicial() {
        return diretorioInicial;
    }

    public void setDiretorioInicial(String diretorioInicial) {
        this.diretorioInicial = diretorioInicial;
    }

    public ObservableList<String> getDocs() {
        return docs;
    }

    public void setDocs(ObservableList<String> docs) {
        this.docs = docs;
    }

}
