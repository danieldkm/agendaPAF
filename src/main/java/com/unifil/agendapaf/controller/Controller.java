package com.unifil.agendapaf.controller;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import com.unifil.agendapaf.dao.JPA;
import com.unifil.agendapaf.model.Agenda;
import com.unifil.agendapaf.model.Cidade;
import com.unifil.agendapaf.model.Contato;
import com.unifil.agendapaf.model.Empresa;
import com.unifil.agendapaf.model.EmpresasHomologadas;
import com.unifil.agendapaf.model.Endereco;
import com.unifil.agendapaf.model.Estado;
import com.unifil.agendapaf.model.Feriado;
import com.unifil.agendapaf.model.Financeiro;
import com.unifil.agendapaf.model.Historico;
import com.unifil.agendapaf.model.Telefone;
import com.unifil.agendapaf.model.Usuario;
import com.unifil.agendapaf.model.aux.Categoria;
import com.unifil.agendapaf.model.aux.Servico;
import com.unifil.agendapaf.service.AgendaService;
import com.unifil.agendapaf.service.CidadeService;
import com.unifil.agendapaf.service.ContatoService;
import com.unifil.agendapaf.service.EmpresaService;
import com.unifil.agendapaf.service.EmpresasHomologadasService;
import com.unifil.agendapaf.service.EnderecoService;
import com.unifil.agendapaf.service.EstadoService;
import com.unifil.agendapaf.service.FeriadoService;
import com.unifil.agendapaf.service.FinanceiroService;
import com.unifil.agendapaf.service.HistoricoService;
import com.unifil.agendapaf.service.TelefoneService;
import com.unifil.agendapaf.service.UsuarioService;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Controller {

    public static ObservableList<Agenda> getAgendas() {
        try {
            AgendaService dao = new AgendaService();
            ObservableList<Agenda> r = dao.findAll();
            JPA.em(false).close();
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Cidade> getCidades() {
        try {
            CidadeService dao = new CidadeService();
            ObservableList<Cidade> r = dao.findAll();
            JPA.em(false).close();
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Empresa> getEmpresas() {
        try {
            EmpresaService dao = new EmpresaService();
            ObservableList<Empresa> r = dao.findAll();
            JPA.em(false).close();
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<EmpresasHomologadas> getEmpresasHomologadas() {
        try {
            EmpresasHomologadasService dao = new EmpresasHomologadasService();
            ObservableList<EmpresasHomologadas> r = dao.findAll();
            JPA.em(false).close();
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Estado> getEstados() {
        try {
            EstadoService dao = new EstadoService();
            ObservableList<Estado> r = dao.findAll();
            JPA.em(false).close();
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Feriado> getFeriados() {
        try {
            FeriadoService dao = new FeriadoService();
            ObservableList<Feriado> r = dao.findAll();
            JPA.em(false).close();
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Financeiro> getFinanceiros() {
        try {
            FinanceiroService dao = new FinanceiroService();
            ObservableList<Financeiro> r = dao.findAll();
            JPA.em(false).close();
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Historico> getHistoricos() {
        try {
            HistoricoService dao = new HistoricoService();
            ObservableList<Historico> r = dao.findAll();
            JPA.em(false).close();
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Usuario> getUsuarios() {
        try {
            UsuarioService dao = new UsuarioService();
            ObservableList<Usuario> r = dao.findAll();
            JPA.em(false).close();
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Contato> getContatos() {
        try {
            ContatoService dao = new ContatoService();
            ObservableList<Contato> r = dao.findAll();
            JPA.em(false).close();
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Endereco> getEnderecos() {
        try {
            EnderecoService dao = new EnderecoService();
            ObservableList<Endereco> r = dao.findAll();
            JPA.em(false).close();
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Telefone> getTelefones() {
        try {
            TelefoneService dao = new TelefoneService();
            ObservableList<Telefone> r = dao.findAll();
            JPA.em(false).close();
            return r;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static ObservableList<Servico> getServicos() {
        File exeFile = new File("");
        FileReader reader = null;
        ObservableList<Servico> servicos = FXCollections.observableArrayList();
        try {
            String caminho = exeFile.getAbsolutePath() + "/servico.xml";
            if (new File(caminho).exists()) {
                reader = new FileReader(caminho);
                XStream xStream = new XStream(new DomDriver());
                xStream.alias("servico", Servico.class);
                servicos = (ObservableList) xStream.fromXML(reader);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return servicos;
    }

    public static ObservableList<Categoria> getCategorias() {
        File exeFile = new File("");
        FileReader reader = null;
        ObservableList<Categoria> categorias = FXCollections.observableArrayList();
        try {
            String caminho = exeFile.getAbsolutePath() + "/categoria.xml";
            if (new File(caminho).exists()) {
                reader = new FileReader(caminho);
                XStream xStream = new XStream(new DomDriver());
                xStream.alias("categoria", Categoria.class);
                categorias = (ObservableList) xStream.fromXML(reader);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return categorias;
    }

//
////    private static Date dataSelecionada = new Date();
//    private static LocalDate dataSelecionada;
//    public static boolean semestral = false; //controle, muda o DateChoose caso seja true
//    public static boolean dbLocal = false;
//    public static boolean dbServidor = false;
//    public static final String scriptBDLocal = "scriptLocal.txt";
//    public static final String scriptBDServidor = "scriptServidor.txt";
//    public static String titulo = "Agenda PAF-ECF - ";
////    public static String ip = "192.168.8.21";
//////    public static String ip = "localhost";
////    public static String user = "SYSDBA";
////    public static String pass = "masterkey";
//////    public static String nomeDataBase = "C:/Users/npi/Desktop/IBExpert.2012.02.21.1/temp/AGENDAPAF_ECF.FDB"; //local windows
////    public static String nomeDataBase = "C:/Users/Administrador/Documents/firebird/AGENDAPAF_ECF.FDB"; //Servidor
////    public static String nomeDataBase = "/Users/danielmorita/Documents/FIREBIRD/AGENDAPAF_ECF.FDB"; //local mac
//
//    //mysql connection
//    public static String ip;
////    public static String ip = "localhost";
//    public static String user;
//    public static String pass;
//    public static String nomeDataBase;
//
//    private static ObservableList<Agenda> listaGlobalAgenda;
//    private static ObservableList<Historico> listaGlobalHistorico;
//    private static ObservableList<Feriado> listaGlobalFeriado;
//
//    public static Image icoPAF;
//
//    private static boolean isVerificarScript;
//    private static String email;
//    private static String senhaEmail;
//
////    public static String lerTxtConexao() {
////        try {
////            BufferedReader buf = new BufferedReader(new FileReader("conexao.txt"));
////            String line = buf.readLine();
////            while (line != null) {
////                String a[] = line.split("[-]");
////                switch (a[0]) {
////                    case "ip":
////                        Controller.ip = a[1].trim();
////                        break;
////                    case "nomeDataBase":
////                        if (dbLocal) {
////                            Controller.nomeDataBase = a[1].trim();
////                        } else if (dbServidor) {
////                            return a[1].trim();
////                        }
////                        break;
////                }
////                line = buf.readLine();
////            }
////            System.out.println("Conex�o Local");
////            System.out.println("ip lido: " + Controller.ip);
////            System.out.println("nome lido: " + Controller.nomeDataBase);
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////        return null;
////    }
//    
//
//    
//
//
//    @Deprecated
//    public static void ifLocal(String sql) {
//        if (dbLocal) {
////            new FileWriter(new File("scriptDbLocal.txt")
//            BufferedWriter bw;
//            try {
//                bw = new BufferedWriter(new FileWriter(new File(scriptBDLocal), true));
//                bw.write(sql + "\r\n");
//                bw.flush();
//                bw.close();
//            } catch (IOException ex) {
//                Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        } else if (dbServidor) {
//            BufferedWriter bw;
//            try {
//                bw = new BufferedWriter(new FileWriter(new File(scriptBDServidor), true));
//                bw.write(sql + "\r\n");
//                bw.flush();
//                bw.close();
//            } catch (IOException ex) {
//                Logger.getLogger(Dao.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    // em andamento - problemas - testar quando no MACOS
//    // 1 - pensar se vale a pena converter todos os dados para UTF-8, ser� custoso para o sistema?
////    private void isUtf8() {
////        String OS = System.getProperty("os.name");
////        if (!OS.contains("Window")) {
////
////        }
////        String intputEncoding = "windows-1252";
////        Charset charsetInput = Charset.forName(intputEncoding);
////        CharsetDecoder decoder = charsetInput.newDecoder();
////
////        String outputEncoding = "UTF-8";
////        Charset charsetOutput = Charset.forName(outputEncoding);
////        CharsetEncoder encoder = charsetOutput.newEncoder();
////
////        byte[] bufferToConvert = null;
////        CharBuffer cbuf;
////
////        FileOutputStream out = null;
////        try {
////            out = new FileOutputStream("teste2.txt");
////        } catch (FileNotFoundException ex) {
////        }
////        String t = "";
////        try {
////            bufferToConvert = a.get(i).getBytes("UTF-8");
////            cbuf = decoder.decode(ByteBuffer.wrap(bufferToConvert));
////            ByteBuffer bbuf = encoder.encode(CharBuffer.wrap(cbuf));
////            System.out.println(new String(bbuf.array(), 0, bbuf.limit(), charsetOutput));
////            String n = new String(bbuf.array(), 0, bbuf.limit(), charsetOutput);
////            t += n + "\n";
////
////        } catch (CharacterCodingException ex) {
////        } catch (IOException ex) {
////        }
////        try {
////            out.write(t.getBytes("UTF-8"));
////            out.close();
////        } catch (IOException ex) {
////        }
////    }
//    /**
//     * Retorna uma lista de Financeiro
//     *
//     * @return financeiros
//     */
//    public static ObservableList<Financeiro> getFinanceiros() {
//        ObservableList<Financeiro> lista = null;
//        if (testarConexao()) {
//            Dao<Financeiro> dao = new Dao(Financeiro.class);
//            try {
//                lista = dao.select();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        return lista;
//    }
//
//    /**
//     * Retorna uma lista de Script
//     *
//     * @return scripts
//     */
//    public static ObservableList<Script> getScript() {
//        ObservableList<Script> script = null;
//        if (testarConexao()) {
//            Dao<Script> dao = new Dao(Script.class);
//            try {
//                script = dao.select();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        return script;
//    }
//
//    /**
//     * Retorna uma lista de Feriados
//     *
//     * @return feriados
//     */
//    public static ObservableList<Feriado> getFeriados() {
//        ObservableList<Feriado> feriados = null;
//        if (testarConexao()) {
//            Dao<Feriado> daoFeriado = new Dao(Feriado.class);
//            try {
//                feriados = daoFeriado.select();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        return feriados;
//    }
//
//    /**
//     * Retorna uma lista de Estados
//     *
//     * @return estados
//     */
//    public static ObservableList<Estado> getEstados() {
//        ObservableList<Estado> estados = null;
//        if (testarConexao()) {
//            Dao<Estado> daoEstado = new Dao(Estado.class);
//            try {
//                estados = daoEstado.select();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        return estados;
//    }
//
//    /**
//     * Retorna uma lista de cidades
//     *
//     * @return cidades
//     */
//    public static ObservableList<Cidade> getCidades() {
//        ObservableList<Cidade> cidades = null;
//        if (testarConexao()) {
//            Dao<Cidade> daoCidade = new Dao(Cidade.class);
//            try {
//                cidades = daoCidade.select();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        return cidades;
//    }
//
//    /**
//     * Retorna uma lista de Empresas
//     *
//     * @return empresas join agenda where status agenda = conclu�do
//     */
//    public static ObservableList<Empresa> getEmpresaJoinAgenda() {
//        ObservableList<Empresa> empresas = null;
//        if (testarConexao()) {
//            Dao<Empresa> daoEmpresa = new Dao(Empresa.class);
//            try {
//                empresas = daoEmpresa.selectEmpresaJoinAgenda();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        return empresas;
//    }
//
//    /**
//     * Retorna uma lista de Empresas
//     *
//     * @return empresas
//     */
//    public static ObservableList<Empresa> getEmpresas() {
//        ObservableList<Empresa> empresas = null;
//        if (testarConexao()) {
//            Dao<Empresa> daoEmpresa = new Dao(Empresa.class);
//            try {
//                empresas = daoEmpresa.select();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        return empresas;
//    }
//
//    /**
//     * Retorna uma lista de Usuarios
//     *
//     * @return usuarios
//     */
//    public static ObservableList<Usuario> getUsuarios() {
//        ObservableList<Usuario> usuarios = null;
//        if (testarConexao()) {
//            Dao<Usuario> daoUsuario = new Dao(Usuario.class);
//            try {
//                usuarios = daoUsuario.select();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        return usuarios;
//    }
//
//    public static ObservableList<Agenda> getAgendasOrderBy(String order) {
//        ObservableList<Agenda> agendas = null;
//        if (testarConexao()) {
//            Dao<Agenda> dao = new Dao(Agenda.class);
//            try {
//                agendas = dao.selectOrderBy(order);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return agendas;
//    }
//
//    /**
//     * Retorna uma lista de Historico
//     *
//     * @return historicos
//     */
//    public static ObservableList<Historico> getHistoricos() {
//        ObservableList<Historico> historicos = null;
//        if (testarConexao()) {
//            Dao<Historico> daoHistorico = new Dao(Historico.class);
//            try {
//                historicos = daoHistorico.selectHistoricoOrderByEmpresaCodHistorico();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        return historicos;
//    }
//
////    /**
////     * Retorna uma lista de HistoricoAgenda
////     *
////     * @return historicoAgendas
////     */
////    public static ObservableList<HistoricoAgenda> getHistoricoAgendas() {
////        Dao<HistoricoAgenda> daoHistoricoAgenda = new Dao(HistoricoAgenda.class);
////        ObservableList<HistoricoAgenda> historicoAgendas = null;
////        try {
////            historicoAgendas = daoHistoricoAgenda.selectHistoricoAgenda();
////        } catch (Exception ex) {
////            ex.printStackTrace();
////        }
////        return historicoAgendas;
////    }
//    /**
//     * Retorna uma lista de Empresas Homologadas
//     *
//     * @return empresasHomologadas
//     */
//    public static ObservableList<EmpresasHomologadas> getEmpresasHomologadas() {
//        ObservableList<EmpresasHomologadas> empresasHomologadas = null;
//        if (testarConexao()) {
//            Dao<EmpresasHomologadas> daoEmpresasHomologadas = new Dao(EmpresasHomologadas.class);
//            try {
//                empresasHomologadas = daoEmpresasHomologadas.select();
//            } catch (Exception ex) {
//                ex.printStackTrace();
//            }
//        }
//        return empresasHomologadas;
//    }
//
////    Dao<Agenda> agendasByDate = new Dao(Agenda.class);
//    public static ObservableList<Agenda> getAgendaFindByDate(String data, String campo) {
//        ObservableList<Agenda> agendas = null;
//        if (testarConexao()) {
//            Dao<Agenda> dao = new Dao(Agenda.class);
//            try {
//                agendas = dao.findByDate(data, campo);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return agendas;
//    }
//
//    public static Agenda getSelectLastInsertAgenda() {
//        Agenda a = null;
//        if (testarConexao()) {
//            Dao<Agenda> dao = new Dao(Agenda.class);
//            try {
//                a = dao.selectLastInsert();
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
//        }
//        return a;
//    }
//
//    // Fun��o para salvar um comando sql na tabela de script
//    public static void salvarScriptBD(String sql) {
//        if (testarConexao()) {
//            System.out.println("SQL script " + sql);
//            try {
//                Dao<Script> daoScript = new Dao(Script.class);
//                Script s = new Script(null, sql);
//                daoScript.insert(s);
//            } catch (Exception ex) {
//                Logger.getLogger(Controller.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    private static boolean testarConexao() {
//        System.out.println("dbServidor "+ dbServidor);
//        System.out.println("dbLocal "+ dbLocal);
//        if (dbServidor) {
//            if (Conexao.getInstance() == null) {
//                return false;
//            } else {
//                return true;
//            }
//        } else if (dbLocal) {
//            if (ConexaoLocal.getInstance() == null) {
//                return false;
//            } else {
//                return true;
//            }
//        }
//        return false;
//    }
//
////    /**
////     * Método para limitar a quantidade de caracteres no textField
////     *
////     * @param String oldValue -> valor antigo
////     * @param String newValue -> valor novo
////     * @param TextField texto-> texto a ser redefinido
////     */
////    public static void verificaQtdDigit(String oldValue, String newValue, TextField texto) {
////        try {
////            // forçar valor numérico, redefinindo a valor antigo se a exceção é lançada
//////                    Integer.parseInt(newValue);
////            // forçar comprimento correto, redefinindo para o valor antigo, se for mais do que maxLength
////            if (newValue.length() > maxLength) {
////                texto.setText(oldValue);
////            }
////        } catch (Exception e) {
////            texto.setText(oldValue);
////        }
////    }
//    @Override
//    public void start(Stage stage) throws Exception {
//    }
//
//    public static LocalDate getDataSelecionada() {
//        return dataSelecionada;
//    }
//
//    public static void setDataSelecionada(LocalDate dataSelecionada) {
//        Controller.dataSelecionada = dataSelecionada;
//    }
//
//    public static ObservableList<Agenda> getListaGlobalAgenda() {
////        int year = calendar.get(Calendar.YEAR);
////        Calendar listCalendar = Calendar.getInstance();
////        listCalendar.setTime(listaGlobalAgenda.get(0).getDataInicial2());
////        int listYear = listCalendar.get(Calendar.YEAR);
////        if(year != listYear){
////            for (Agenda agenda : Controller.getAgendasOrderBy()) {
////                if(agenda.getDataInicial2()){
////                    
////                }
////            }
////        }
////        if(listaGlobalAgenda.get(0).getDataInicial2()){
////            
////        }
//        return listaGlobalAgenda;
//    }
//
//    public static void setListaGlobalAgenda(ObservableList<Agenda> listaGlobalAgenda) {
//        Controller.listaGlobalAgenda = listaGlobalAgenda;
//    }
//
//    public static ObservableList<Historico> getListaGlobalHistorico() {
//        return listaGlobalHistorico;
//    }
//
//    public static void setListaGlobalHistorico(ObservableList<Historico> listaGlobalHistorico) {
//        Controller.listaGlobalHistorico = listaGlobalHistorico;
//    }
//
//    public static ObservableList<Feriado> getListaGlobalFeriado() {
//        return listaGlobalFeriado;
//    }
//
//    public static void setListaGlobalFeriado(ObservableList<Feriado> listaGlobalFeriado) {
//        Controller.listaGlobalFeriado = listaGlobalFeriado;
//    }
//
//    public static boolean isVerificarScript() {
//        return isVerificarScript;
//    }
//
//    public static void setIsVerificarScript(boolean isVerificarScript) {
//        Controller.isVerificarScript = isVerificarScript;
//    }
//
//    public static String getEmail() {
//        return email;
//    }
//
//    public static void setEmail(String email) {
//        Controller.email = email;
//    }
//
//    public static String getSenhaEmail() {
//        return senhaEmail;
//    }
//
//    public static void setSenhaEmail(String senhaEmail) {
//        Controller.senhaEmail = senhaEmail;
//    }
}
