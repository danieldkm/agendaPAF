package com.unifil.agendapaf;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.unifil.agendapaf.model.aux.ParametroDocx;
import com.unifil.agendapaf.util.Json;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author danielmorita
 */
public class GerarParametrosDocPadrao {

    public static void main(String[] args) {
        Json json = new Json();
        ArrayList<ParametroDocx> prs = new ArrayList<>();

        HashMap<String, String> ptros = new HashMap<>();
        ptros.put("laudo", "");
        ptros.put("txtRazaoSocial", "");
        ptros.put("txtCnpj", "");
        ptros.put("txtNomeAplicativo", "");
        ptros.put("txtVersao", "");
        ptros.put("txtTamanhoBytes", "");
        ptros.put("txtDataGeracao", "");
        ptros.put("txtPrincipalExec", "");
        ptros.put("txtMd5Principal", "");
        ptros.put("txtNome", "");
        ptros.put("txtCpf", "");
        ptros.put("txtData", "");
        ptros.put("txtRg", "");
        ptros.put("ckbGerenciadorBD", "");
        ptros.put("ckbComRegras", "");

        Gson gson = new Gson();
//        String map = gson.toJson(ptros);
//        System.out.println("map = " + map);

        ParametroDocx pd = new ParametroDocx();
        pd.setDocumento("ANEXO BANCO DE DADOS MODELO");
        pd.setParametros(gson.toJson(ptros));
        prs.add(pd);

        ptros = new HashMap<>();
        ptros.put("laudo", "");
        ptros.put("txtRazaoSocial", "");
        ptros.put("txtCnpj", "");
        ptros.put("txtNomeAplicativo", "");
        ptros.put("txtVersao", "");
        ptros.put("txtTamanhoBytes", "");
        ptros.put("txtDataGeracao", "");
        ptros.put("txtPrincipalExec", "");
        ptros.put("txtMd5Principal", "");
        ptros.put("txtNome", "");
        ptros.put("txtCpf", "");
        ptros.put("txtData", "");
        ptros.put("txtRg", "");
        ptros.put("txtEnvelope", "");

        pd = new ParametroDocx();
        pd.setDocumento("TERMO DE DEPÓSITO MODELO");
        pd.setParametros(gson.toJson(ptros));
        prs.add(pd);

        ptros = new HashMap<>();
        ptros.put("laudo", "");
        ptros.put("txtRazaoSocial", "");
        ptros.put("txtCnpj", "");
        ptros.put("txtNomeAplicativo", "");
        ptros.put("txtVersao", "");
        ptros.put("txtTamanhoBytes", "");
        ptros.put("txtDataGeracao", "");
        ptros.put("txtPrincipalExec", "");
        ptros.put("txtMd5Principal", "");
        ptros.put("txtNome", "");
        ptros.put("txtCpf", "");
        ptros.put("txtData", "");
        ptros.put("txtRg", "");

        pd = new ParametroDocx();
        pd.setDocumento("TERMO DE DECLARAÇÃO REQUISITO I");
        pd.setParametros(gson.toJson(ptros));
        prs.add(pd);

        ptros = new HashMap<>();
        ptros.put("laudo", "");
        ptros.put("txtRazaoSocial", "");
        ptros.put("txtNomeFantasia", "");
        ptros.put("txtCnpj", "");
        ptros.put("txtIe", "");
        ptros.put("txtIm", "");
        ptros.put("txtNomeAplicativo", "");
        ptros.put("txtVersao", "");
        ptros.put("txtTamanhoBytes", "");
        ptros.put("txtDataGeracao", "");
        ptros.put("txtPrincipalExec", "");
        ptros.put("txtMd5Principal", "");
        ptros.put("txtMd5Relacao", "");
        ptros.put("txtRipmedRelacao", "");
        ptros.put("txtNomeArquivoEmpresa", "");
        ptros.put("txtMd5Empresa", "");
        ptros.put("txtRipmedEmpresa", "");
        ptros.put("txtNome", "");
        ptros.put("txtCpf", "");
        ptros.put("txtData", "");

        pd = new ParametroDocx();
        pd.setDocumento("TERMO DE AUTENTICAÇÃO MODELO");
        pd.setParametros(gson.toJson(ptros));
        prs.add(pd);

        ptros = new HashMap<>();
        ptros.put("laudo", "");
        ptros.put("txtRazaoSocial", "");
        ptros.put("txtNomeFantasia", "");
        ptros.put("txtEndereco", "");
        ptros.put("txtBairro", "");
        ptros.put("txtCidade", "");
        ptros.put("txtUf", "");
        ptros.put("txtCep", "");
        ptros.put("txtTelefone", "");
        ptros.put("txtFax", "");
        ptros.put("txtCelular", "");
        ptros.put("txtCnpj", "");
        ptros.put("txtIe", "");
        ptros.put("txtIm", "");
        ptros.put("txtContato", "");
        ptros.put("txtCpfContato", "");
        ptros.put("txtEmail", "");
        ptros.put("txtResponsavel", "");
        ptros.put("txtNomeHomologador", "");
        ptros.put("txtDataInicio", "");
        ptros.put("txtDataFinal", "");
        ptros.put("txtNomeComercial", "");
        ptros.put("txtVersao", "");
        ptros.put("txtDataVersao", "");
        ptros.put("txtPrincipalExec", "");
        ptros.put("txtMd5Principal", "");
        ptros.put("txtMd5NomeArquivoEmpresa", "");
        ptros.put("txtRelacaoMd5Executaveis", "");
        ptros.put("txtMd5NomeRelacao", "");
        ptros.put("perfilr", "");
        ptros.put("perfils", "");
        ptros.put("perfilt", "");
        ptros.put("perfilu", "");
        ptros.put("perfilv", "");
        ptros.put("perfilw", "");
        ptros.put("perfily", "");
        ptros.put("perfilz", "");
        ptros.put("txtEnvelope", "");
        ptros.put("txtLinguagemProgramacao", "");
        ptros.put("txtSo", "");
        ptros.put("txtBd", "");
        
        ptros.put("ckbComercializavel", "");
        ptros.put("ckbExclusivoProprio", "");
        ptros.put("ckbExclusivoTerce", "");
        ptros.put("ckbConcomitante", "");
        ptros.put("ckbNaoConcomitanteDV", "");
        ptros.put("ckbNaoConcomitantePr", "");
        ptros.put("ckbNaoConcomitanteCC", "");
        ptros.put("ckbDavSemImp", "");
        ptros.put("ckbDavImpNaoFiscal", "");
        ptros.put("ckbDavEcf", "");
        ptros.put("ckbStandAlone", "");
        ptros.put("ckbEmRede", "");
        ptros.put("ckbParametrizavel", "");
        ptros.put("ckbPeloPaf", "");
        ptros.put("ckbPeloRetaguarda", "");
        ptros.put("ckbPeloSisPed", "");
        ptros.put("ckbNfeSim", "");
        ptros.put("ckbNfeNao", "");
        ptros.put("ckbNfceSim", "");
        ptros.put("ckbNfceNao", "");
        ptros.put("ckbRecuDados", "");
        ptros.put("ckbCancelAutoma", "");
        ptros.put("ckbBloqueFunc", "");
        ptros.put("ckbComRetaguarda", "");
        ptros.put("ckbComSisPed", "");
        ptros.put("ckbComAmbos", "");
        ptros.put("ckbNaoIntegrado", "");
        ptros.put("ckbPostoComBomb", "");
        ptros.put("ckbPostoSemBomb", "");
        ptros.put("ckbOficinaComDavOs", "");
        ptros.put("ckbOficinaComCC", "");
        ptros.put("ckbRestaEcfRestaCom", "");
        ptros.put("ckbRestaEcfRestaSem", "");
        ptros.put("ckbRestaEcfNormalCom", "");
        ptros.put("ckbRestaEcfNormalSem", "");
        ptros.put("ckbFarmacia", "");
        ptros.put("ckbTransporte", "");
        ptros.put("ckbPedagio", "");
        ptros.put("ckbEstacionamento", "");
        ptros.put("ckbCinema", "");
        ptros.put("ckbDemaisAtividades", "");
        ptros.put("ckbSimplesNacional", "");
        
        ptros.put("txtSGRazaoSocialCnpj", "");
        ptros.put("txtSGNomeSistema", "");
        ptros.put("txtSGRequisitoExecutado", "");
        ptros.put("txtSGNomeArquivoMd5", "");
        
        ptros.put("txtSPRazaoSocialCnpj", "");
        ptros.put("txtSPNomeSistema", "");
        ptros.put("txtSPNomeArquivoMd5", "");
        ptros.put("txtSPFuncao", "");
        
        ptros.put("txtSNRazaoSocialCnpj", "");
        ptros.put("txtSNNomeSistema", "");
        ptros.put("txtSNNomeArquivoMd5", "");
        
        ptros.put("txtNaoConformidadeRequisito", "");
        
        ptros.put("txtEcfMarca", "");
        ptros.put("txtEcfModelo", "");
        
        ptros.put("txtRelacaoEcf", "");
        
//        ptros.put("txtRelacaoMarca", "");
//        ptros.put("txtRelacaoModelo", "");
//        ptros.put("txtRelacaoMarca2", "");
//        ptros.put("txtRelacaoModelo2", "");
        
        ptros.put("ckbConstatada", "");
        ptros.put("ckbNaoConstatada", "");
        
        ptros.put("txtObservacaoOTC", "");
        
        ptros.put("txtData", "");
        
        ptros.put("txtCargoTecnico", "");
        ptros.put("txtTecnicoCpf", "");
        ptros.put("txtCargoCoordenador", "");
        ptros.put("txtCoordenadorCpf", "");
        

        pd = new ParametroDocx();
        pd.setDocumento("LAUDO PAF-ECF-F MODELO 2015");
        pd.setParametros(gson.toJson(ptros));
        prs.add(pd);

////        
////        ArrayList<String> ptros2 = new ArrayList<>();
////        ptros2.add("teste1");
////        ptros2.add("teste2");
////        ptros2.add("teste3");
////        ptros2.add("teste4");
////        ParametroDocx pd2 = new ParametroDocx();
////        pd2.setDocumento("DOCUMENTO2");
////        pd2.setParametros(ptros2);
////        
////        ArrayList<String> ptros3 = new ArrayList<>();
////        ptros3.add("teste1");
////        ptros3.add("teste2");
////        ptros3.add("teste3");
////        ptros3.add("teste4");
////        ParametroDocx pd3 = new ParametroDocx();
////        pd3.setDocumento("DOCUMENTO3");
////        pd3.setParametros(ptros3);
////        
////        prs.add(pd2);
////        prs.add(pd3);
////        
////        
        json.salvarParametroDocxJSON(prs, "word/modelo_docxs/Documentos.json", false);
        Type type = new TypeToken<HashMap<String, String>>() {
        }.getType();
        for (ParametroDocx pr : json.lerArquivoJSON("word/modelo_docxs/Documentos.json")) {
            HashMap<String, String> hmap = gson.fromJson(pr.getParametros(), type);
            for (String key : hmap.keySet()) {
                System.out.println("map.get = " + hmap.get(key) + " key " + key);
            }
        }
    }

}
