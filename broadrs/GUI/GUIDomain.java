package broadrs.GUI;


import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class GUIDomain extends Window{
/*
    private Textbox nome;
    private Textbox objetivo;
    private Datebox dataProjeto;
    private Textbox cursos_associados;
    private Textbox inicio;
    private Textbox fim;
    private Grid listarTodos;
    private Projeto projetoAux;
    private GUIDomain janela;
    private boolean permissaoDeCadastro;
    private Grid listarArquivos;

    public void Visualizar() {
        listarArquivos = (Grid) getFellow("listarArquivos");
        listarArquivos.getRows().detach();
        listarArquivos.appendChild(new Rows());
        listarArquivos.setVisible(true);
        new GUIWelcome().listarArquivos(listarArquivos);
    }
    
    public void onCreate() {
        nome = (Textbox) getFellow("nome");
        objetivo = (Textbox) getFellow("objetivo");
        dataProjeto = (Datebox) getFellow("data");
        cursos_associados = (Textbox) getFellow("cursos_associados");
        inicio = (Textbox) getFellow("inicio");
        fim = (Textbox) getFellow("fim");
        listarTodos = (Grid) getFellow("listarTodos");
    }

    public void cadastraProjeto() {
        Pessoa usu = (Pessoa) Sessions.getCurrent().getAttribute("logado");

        if (nome.getValue().equalsIgnoreCase("")){
            new GUICaixaMensagem().caixaErro("Preencha Todos os campos obrigatórios", "Erro");
        }else if (usu == null){
            new GUICaixaMensagem().caixaErro("Não há usuário logado no sistema. Favor fazer login antes de cadastrar o projeto.", "Erro");
        }else{

            Projeto projeto = new Projeto();
            projeto.setNome(nome.getValue());
            projeto.setObjetivo(objetivo.getValue());
            projeto.setDataProjeto(dataProjeto.getValue());
            projeto.setCursosAssociados(cursos_associados.getValue());
            projeto.setInicio(inicio.getValue());
            projeto.setFim(fim.getValue());

            new DAOProjeto().inserir(projeto);

            nome.setValue("");
            objetivo.setValue("");
            dataProjeto.setValue(null);
            cursos_associados.setValue("");
            inicio.setValue("");
            fim.setValue("");


            new GUICaixaMensagem().caixaInformacao("Cadastro realizado com Sucesso!", "Sucesso");
        }
    }

    public void listarTodos() {
        listarTodos.getRows().detach();
        listarTodos.appendChild(new Rows());

        List<Projeto> lista = new DAOProjeto().buscarTodos();

        for (final Projeto projeto : lista) {

            Row linhaPrincipal = null;
            linhaPrincipal = new Row();
            linhaPrincipal.setStyle("cursor:pointer");

            //nome
            Label nNome = new Label(projeto.getNome());
            linhaPrincipal.appendChild(nNome);
            //data
            //Label nData = new Label(projeto.getData());
            //linhaPrincipal.appendChild(nData);

            Button nEditar = new Button("");
            nEditar.setStyle("background-color:#FFFFFF; border-width:0;");
            nEditar.setImage("img/icones/001_45.png");

            janela = this;

            nEditar.addEventListener("onClick", new EventListener() {

                public void onEvent(Event event) throws Exception {
                    projetoAux = projeto;
                    //new ModalProjetoGUI().abrirJanela(projetoAux, janela);
                }
            });
            nEditar.setStyle("cursor:pointer");
            linhaPrincipal.appendChild(nEditar);

            Button nApagar = new Button("");
            nApagar.setStyle("background-color:#FFFFFF; border-width:0;");
            nApagar.setImage("img/icones/001_05.png");

            nApagar.addEventListener("onClick", new EventListener() {

                public void onEvent(Event event) throws Exception {

                    Messagebox.show("Deseja realmente excluir o projeto " +
                            projeto.getNome() +
                            "?", "Confirmação", Messagebox.YES | Messagebox.NO,
                            Messagebox.QUESTION,
                            new EventListener() {

                                public void onEvent(Event evt) throws Exception {
                                    if (evt.getName().equals("onYes")) {
                                        if ((Pessoa) Sessions.getCurrent().getAttribute("logado") == null)
                                            new GUICaixaMensagem().caixaErro("Não há usuário logado no sistema. Favor fazer login antes de excluir o projeto.", "Erro");
                                        else {
                                            projetoAux = projeto;
                                            List<Objeto> lista = new DAOObjeto().buscarArquivos(projetoAux.getIdprojeto());
                                            if (!lista.isEmpty())
                                                new GUICaixaMensagem().caixaErro("Não é possível excluir este projeto porque existe arquivo associado ao projeto.", "Erro");
                                            else {
                                                new DAOProjeto().apagar(projetoAux);
                                                listarTodos();
                                            }
                                        }
                                    }
                                }
                            });
                }
            });
            nApagar.setStyle("cursor:pointer");
            linhaPrincipal.appendChild(nApagar);

            //adiciona a linha no Grid
            listarTodos.getRows().appendChild(linhaPrincipal);
        }
    }
    
    public void validarNome() {
        List<Projeto> lista = new DAOProjeto().buscarNome(nome.getValue());
        if (!lista.isEmpty()) 
            nome.setErrorMessage("Já existe um projeto com este nome.");
        else
            nome.clearErrorMessage();
    }*/
}