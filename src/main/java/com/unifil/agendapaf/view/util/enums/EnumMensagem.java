package com.unifil.agendapaf.view.util.enums;

/**
 *
 * @author danielmorita
 */
public enum EnumMensagem {

    Padrao(),
    Atualizado("Atualizado com Sucesso!"),
    Salvo("Salvo com Sucesso!"),
    Deletado("Deletado com Sucesso!"),
    Gerado("Gerado com Sucesso!"),
    Pergunta("Pergunta do Sistema!", "Deseja continuar?"),
    ErroAtualizar("Erro ao tentar atualizar!"),
    ErroSalvar("Erro ao tentar salvar!"),
    ErroDeletar("Erro ao tentar deletar!"),
    ErroGerar("Erro ao tentar gerar!"),
    ErroConversao("Erro de conversão!"),
    Requer("Requer está informação"),
    RequerComboBox("Requer comboBox selecionado"),
    RequerDtInicial("Requer data inicial"),
    RequerDtFinal("Requer data final"),
    RequerDtFinalMenorDtInicial("Requer data final menor que data inicial"),
    CertezaDeletar("Tem certeza que deseja deletar?"),
    CertezaSalvar("Tem certeza que deseja salvar?\nSe o arquivo existir será substituido."),
    InformeComboBox("Escolha umas das opções válidas!!"),
    InformeEmpresa("Informe a empresa!!"),
    InformeHoraAdicional("Informe a quantidade de horas adicionais"),
    Aviso("Aviso", "Aviso do Sistema!", "Não é possível agendar uma Avaliação/Pré-Avaliação: "),
    AgendaFinanceiroExisteHoraAdicional("Houve horas adicionais?"),
    AgendaFinanceiroDesejaCadastrar("Deseja cadastrar no financeiro de forma automática?"),
    AgendaErroSalvarHistoricoAgenda("Erro ao tentar salvar Histórico da agenda"),
    AgendaRequerResponsavel("Atualize no cadastro de empresa o responsável"),
    AgendaRequerEmpresa("Encontre uma empresa para ser agendada"),
    EmpresaErroNaoExiste("Não existe nenhuma empresa para ser deletado"),
    EmpresaErroTentarDeletar("Erro ao tentar deletar a empresa: "),
    EmpresaErroEstadoCidade("Selecione um estado e uma cidade"),
    EmpresaErroNomeContato("Informe o nome de contato"),
    EmpresaErroEmailContato("E-mail inválido ou vazio"),
    EmpresaErroIdEmpresaNull("Empresa não existe!"),
    EmpresaErroEnderecoSelecionado("Não é possível deletar o atual endereço"),
    EmpresaErroContatoSelecionado("Não é possível deletar o atual contato"),
    EmpresaErroTelefoneSelecionado("Não é possível deletar o atual telefone"),
    FeriadoErroNaoExiste("Não existe nenhum feriado para ser deletado"),
    FeriadoErroTentarDeletar("Erro ao tentar deletar este feriado: "),
    FinanceiroTxtValorInvalido("Informe o valor!"),
    FinanceiroHoraAdicionalInvalido("Hora adicional inválido!"),
    FinanceiroDataInicialInvalido("Data inicial"),
    FinanceiroDataFinalInvalido("Data final"),
    FinanceiroDataInvalida("Data inválida"),
    FinanceiroErroNaoSelecionado("Não foi possível deletar, é necesário selecionar um financeiro antes, na sua tabela"),
    LaudoInformeMarcaModelo("Não foram selecionado(s) Marca e/ou Modelo"),
    LaudoInformeTabelaVazia("Tabela está vazia!"),
    LaudoInformeLinhaNaoSelecionada("Selecione uma linha da tabela"),
    LaudoWarningArquivoInvalido("Arquivo inválido, atente-se ao anexo III do ATO COTEPE/ICMS 9"),
    LaudoIdentificarEmpresa("Identifique a empresa\nClicando no botão BUSCAR na parte superior"),
    LoginBDSelecionado("Selecione umas das opções de conexão com o BD"),
    LoginIncorreto("Login ou senha estão incorretos"),
    MotivoReagendamentoPreencherMotivo("Preenher o motivo!"),
    MotivoReagendamentoParaCancelar("Para cancelar a agenda clique no botão salvar!"),
    ParametroConfirmarGerarParametroPadrao("Tem certeza que deseja substituir o arquivo atual?"),
    ParametroConfirmandoGerarParametroPadrao("Ao gerar parâmetro padrão, irá perder todas as configurações já cadastradas"),
    ParametroInformeNome("Informe o nome!"),
    ParametroInformeValor("Informe o valor!"),
    ParametroInformePorcentagem("Informe a porcentagem!"),
    ParametroIncorretoValor("Valor informado incorreto!"),
    ParametroIncorretoPorcentagem("Porcentagem informado incorreto!"),
    ParametroIncorretoPorcentagemAcima("Porcentagem informado não pode ser acima de 100%!"),
    ParametroErroServicoNaoSelecionado("Não foi possivel deletar, é necesário selecionar um serviço antes de deletar"),
    ParametroErroCategoriaNaoSelecionado("Não foi possivel deletar, é necesário selecionar um categoria antes de deletar"),
    ParametroRemoverponto("Favor remover o '.'"),
    PrincipalConfirmarSincronizarBD("Deseja Sincronizar o BD local com o BD servidor?"),
    PrincipalErroConexaoBD("Não foi possível estabelecer conexão com o servidor"),
    PrincipalErroNaoExisteAgendamento("Não existe agendamentos nesta data, \\npara ser atualizado"),
    RelatorioErroValorIncorreto("Valor informado não é válido\nExemplo: 2014"),
    TabelaAgendaErroStatusAgenda("Não pode ser Reagendado, verifique os status da agenda"),
    TabelaAgendaErroReagendado("Esta empresa fez o reagendamento!"),
    TabelaAgendaErroCancelado("Este agendamento já foi cancelado!"),
    TabelaEmpresasHomologadasPergunta("Pergunta do Sistema!", "Deseja finalizar o alerta?", "Confirme clicando em OK\nou CANCELAR para voltar a ver o alerta"),
    UsuarioInformarLogin("Preencher login"),
    UsuarioInformarNome("Preencher o nome"),
    UsuarioInformarSenha("Preencher a senha"),
    UsuarioInformarComboBoxTipo("Selecionar o tipo"),
    UsuarioInformarEmail("Preencher E-mail"),
    UsuarioErroNaoExiste("Não existe nenhum usuário à ser deletado"),
    UsuarioPerguntaDeletar("Pergunta", "Pergunta do Sistema", "Tem certeza que deseja deletar?"),
    FerramentaEmailAvisoEmailNulo("Preencher e-mail"),
    FerramentaEmailAvisoSenhaNulo("Preencher senha"),
    FerramentaEmailAvisoRepetirSenhaNulo("Preencher repetir senha"),
    FerramentaEmailAvisoSMTPNulo("Preencher SMTP"),
    FerramentaEmailAvisoHostNulo("Preencher host name"),
    FerramentaEmailAvisoSenhaDiferente("Repetir senha"),;

    private String titulo;
    private String subTitulo;
    private String mensagem;

    private EnumMensagem(String titulo, String subTitulo, String mensagem) {
        this.titulo = titulo;
        this.subTitulo = subTitulo;
        this.mensagem = mensagem;
    }

    private EnumMensagem(String titulo, String subTitulo) {
        this.titulo = titulo;
        this.subTitulo = subTitulo;
    }

    private EnumMensagem(String mensagem) {
        this();
        this.mensagem = mensagem;
    }

    private EnumMensagem() {
        this.titulo = "Informação";
        this.subTitulo = "Informação para/do Sistema";
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getSubTitulo() {
        return subTitulo;
    }

    public void setSubTitulo(String subTitulo) {
        this.subTitulo = subTitulo;
    }

    public String getMensagem() {
        return mensagem;
    }

    public void setMensagem(String mensagem) {
        this.mensagem = mensagem;
    }

}
