/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.unifil.agendapaf.exemplos;

import com.unifil.agendapaf.controller.Controller;
import com.unifil.agendapaf.model.aux.Categoria;
import com.unifil.agendapaf.model.aux.Categorias;
import com.unifil.agendapaf.model.aux.Servico;
import com.unifil.agendapaf.model.aux.Servicos;
import com.unifil.agendapaf.util.UtilDialog;
import com.unifil.agendapaf.util.UtilFile;
import com.unifil.agendapaf.view.util.enums.EnumMensagem;
import com.unifil.agendapaf.view.util.enums.EnumServico;
import java.util.Optional;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ButtonType;

/**
 *
 * @author danielmorita
 */
public class TesteGerarParametros {

    public static void main(String[] args) {

//        Optional<ButtonType> result = UtilDialog.criarDialogConfirmacao(EnumMensagem.Padrao.getTitulo(), EnumMensagem.ParametroConfirmarGerarParametroPadrao.getMensagem(), EnumMensagem.ParametroConfirmandoGerarParametroPadrao.getMensagem());
//        if (result.get() == ButtonType.OK) {
        try {
            UtilFile util = new UtilFile();

            ObservableList<Servico> tipos = FXCollections.observableArrayList();
            Servico servico = new Servico();

            servico.setId(
                    1);
            servico.setNome(
                    EnumServico.Avaliacao.getServico()
            );

            servico.setValor(
                    2106);

            Servico servico2 = new Servico();

            servico2.setId(
                    2);
            servico2.setNome(
                    EnumServico.AvaliacaoIntinerante.getServico()
            );
            servico2.setValor(
                    2538);

            Servico servico3 = new Servico();

            servico3.setId(
                    3);
            servico3.setNome(
                    EnumServico.PreAvaliacao.getServico());
            servico3.setValor(
                    1058);

            Servico servico4 = new Servico();

            servico4.setId(
                    4);
            servico4.setNome(
                    EnumServico.PreAvaliacaoRemoto.getServico()
            );
            servico4.setValor(
                    1058);

            Servico servico6 = new Servico();

            servico6.setId(
                    5);
            servico6.setNome(
                    EnumServico.HoraAdicional.getServico()
            );
            servico6.setValor(
                    151);

            Servicos ss = new Servicos();
            ss.getServicos().add(servico);
            ss.getServicos().add(servico2);
            ss.getServicos().add(servico3);
            ss.getServicos().add(servico4);
            ss.getServicos().add(servico6);
            tipos.add(servico);

            tipos.add(servico2);

            tipos.add(servico3);

            tipos.add(servico4);

            tipos.add(servico6);

//                String xml = xStream.toXML(tipos);
            UtilFile.salvarArquivoXML(util.marshal(ss),
                    "/servico.xml");

//                xStream.alias(
//                        "categoria", Categoria.class
//                );
            ObservableList<Categoria> categorias = FXCollections.observableArrayList();
            Categoria categoria = new Categoria();
            categoria.setId(0);
            categoria.setNome(
                    "Normal");
            categoria.setPorcento(
                    0);

            Categoria categoria2 = new Categoria();
            categoria2.setId(1);
            categoria2.setNome(
                    "Empresas APL");
            categoria2.setPorcento(
                    10);

            Categoria categoria3 = new Categoria();
            categoria3.setId(2);
            categoria3.setNome(
                    "Cintec");
            categoria3.setPorcento(
                    20);

            Categoria categoria4 = new Categoria();
            categoria4.setId(3);
            categoria4.setNome(
                    "Governança da APL");
            categoria4.setPorcento(
                    30);

            Categoria categoria5 = new Categoria();
            categoria5.setId(3);
            categoria5.setNome(
                    "Reavaliação");
            categoria5.setPorcento(
                    10);

            Categorias cs = new Categorias();
            cs.getCategorias().add(categoria);
            cs.getCategorias().add(categoria2);
            cs.getCategorias().add(categoria3);
            cs.getCategorias().add(categoria4);
            cs.getCategorias().add(categoria5);
            categorias.add(categoria);
            categorias.add(categoria2);
            categorias.add(categoria3);
            categorias.add(categoria4);
            categorias.add(categoria5);

//                String xml2 = xStream.toXML(categorias);
            UtilFile.salvarArquivoXML(util.marshal(cs),
                    "/categoria.xml");

//                servicos = Controller.getServicos();
//                categorias = Controller.getCategorias();
//                tvServico.getItems()
//                        .setAll(servicos);
//                tvCategoria.getItems()
//                        .setAll(categorias);
//            UtilDialog.criarDialogInfomation(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.Gerado.getMensagem());
        } catch (Exception e) {
//            UtilDialog.criarDialogException(EnumMensagem.Padrao.getTitulo(), EnumMensagem.Padrao.getSubTitulo(), EnumMensagem.ErroGerar.getMensagem(), e, "Exception");
            e.printStackTrace();
        }
//        }
    }

}
