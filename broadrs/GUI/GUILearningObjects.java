package broadrs.GUI;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.zkoss.io.Files;
import org.zkoss.zhtml.Fileupload;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabbox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class GUILearningObjects extends Window {
    private Grid listarArquivos;

    public void onCreate() {

        consultar();

    }

    public void consultar() {
        //ABRINDO ONTOLOGIA
        OntModel ontModel;
        ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM); //OWL_LITE_MEM_RULES_INF

            String sistema = System.getProperty("os.name");
            System.out.println("Voce esta usando: "+sistema);
            String Caminho;
            if (sistema.equals("Linux"))
            {
               Caminho = "file:///var/lib/tomcat-7-broad/webapps/owl/PERSONNA.owl";
            }
            else
            {
               Caminho = "file:///C:/BROAD-RS/broad-rs/owl/PERSONNA.owl";
            }

        ontModel.read(Caminho);
        //ABRINDO ONTOLOGIA

        String[] temp5;
        String[] temp6;
        //OntClass c = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2013/4/Ontology1367952328156.owl#" + "learning_objects");


        //INSERINDO INDIVIDUO
       /* Resource person=ontModel.getResource("http://www.semanticweb.org/ontologies/2013/4/Ontology1367952328156.owl" +"#learner");

        Property learner_name= ontModel.getProperty("http://www.semanticweb.org/ontologies/2013/4/Ontology1367952328156.owl" +"#has_learner_name");
        Property learner_age= ontModel.getProperty("http://www.semanticweb.org/ontologies/2013/4/Ontology1367952328156.owl" +"#has_learner_age");

        Individual P5=ontModel.createIndividual("http://www.semanticweb.org/ontologies/2013/4/Ontology1367952328156.owl" +"#PL_0011",person);

        P5.addLiteral(learner_name, "John Sminowflowers");
        P5.addLiteral(learner_age, 15);*/
        //FIM INSERINDO INDIVIDUO

        //SALVANDO ONTOLOGIA
        File file= new File("C:\\BROAD-RS\\broad-rs\\owl\\PERSONNA2.owl");
        try {
            ontModel.write(new FileOutputStream(file), "RDF/XML");
        } catch (FileNotFoundException ex) {
        }
        //FIM SALVANDO ONTOLOGIA

        listarArquivos = (Grid) getFellow("listarArquivos");
        listarArquivos.getRows().detach();
        listarArquivos.appendChild(new Rows());
        listarArquivos.setVisible(true);

        String queryString= "PREFIX bc: <http://www.semanticweb.org/ontologies/2013/4/Ontology1367952328156.owl#> select ?x ?z WHERE {?x bc:has_learning_objects_URL ?z} LIMIT 1000";

        Query query=QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, ontModel);
        ResultSet results = qexec.execSelect();

    while(results.hasNext()) {

        QuerySolution sol = results.nextSolution();
        Row linhaPrincipal = null;
        linhaPrincipal = new Row();

        Label nTitulo3 = new Label();
        nTitulo3.setValue(sol.get("x").asResource().getLocalName().toString());
        linhaPrincipal.appendChild(nTitulo3);

        Label nTitulo4 = new Label();
        nTitulo4.setValue(sol.get("z").asLiteral().getString());
        linhaPrincipal.appendChild(nTitulo4);

        listarArquivos.getRows().appendChild(linhaPrincipal);

    }
    }
}

/*
    private Textbox titulo;
    private Textbox autor;
    private Textbox assunto;
    private Datebox dataObjeto;
    private Textbox tipo;
    private Textbox tag;
    private Textbox descricao;
    private Textbox caminho;
    private Combobox aberto;
    private Combobox status;
    private Combobox projeto;
    public static Media arquivoMedia;
    private Textbox buscaTitulo;
    private Textbox buscaAutor;
    private Textbox buscaTag;
    private Textbox buscaLink;
    private int idObjeto; // armazena id do objeto em edição atualmente
    private Grid listarArquivos;
    private Tabbox abas;
    private Tab abaCadastro;
    private Tab abaConsulta;
    private Button cancelar;
    private final String tituloCadastro = "Cadastro de Arquivo";
    private final String tituloEdicao = "Edição de Arquivo";
    private Objeto objetoEdicao; // armazena objeto em edição atualmente
    private String caminhoObjetoEdicao; // armazena caminho do arquivo referente ao objeto em edição atualmente
    private Row linhaSelecionada; // linha da grid de resultados referente ao objeto em edição atualmente
    private int ultimoProjetoSelecionado; // armazena o índice do último projeto selecionado no combobox projeto
    private Grid listarArquivos2;

    public void Visualizar() {
        listarArquivos2 = (Grid) getFellow("listarArquivos2");
        listarArquivos2.getRows().detach();
        listarArquivos2.appendChild(new Rows());
        listarArquivos2.setVisible(true);
        new GUIWelcome().listarArquivos(listarArquivos2);
    }
    
    public void onCreate() {
        titulo = (Textbox) getFellow("tituloModal");
        autor = (Textbox) getFellow("autorModal");
        assunto = (Textbox) getFellow("assuntoModal");
        dataObjeto = (Datebox) getFellow("dataModal");
        tipo = (Textbox) getFellow("tipoModal");
        tag = (Textbox) getFellow("tagModal");
        descricao = (Textbox) getFellow("descricaoModal");
        caminho = (Textbox) getFellow("caminho");
        aberto = (Combobox) getFellow("abertoModal");
        status = (Combobox) getFellow("statusModal");
        projeto = (Combobox) getFellow("projetoModal");
        buscaTitulo = (Textbox) getFellow("buscaTitulo");
        buscaAutor = (Textbox) getFellow("buscaAutor");
        buscaTag = (Textbox) getFellow("buscaTag");
        buscaLink = (Textbox) getFellow("buscaLink");
        listarArquivos = (Grid) getFellow("listarArquivos");
        abas = (Tabbox) getFellow("abas");
        abaCadastro = (Tab) getFellow("abaCadastro");
        abaConsulta = (Tab) getFellow("abaConsulta");
        cancelar = (Button) getFellow("cancelar");
        listarCombos();
        GUILearningObjects.arquivoMedia = null; // indica que ainda não há arquivo associado ao objeto atual
        objetoEdicao = null; // indica que não há objeto em edição
        caminhoObjetoEdicao = null;
        ultimoProjetoSelecionado = -1; // indica que não há projeto selecionado
    }

    public void uploadArquivo2() throws IOException, InterruptedException {
        File dest = new File(new Arquivos().getSavePath());
        Media media = Fileupload.get();
        if (media != null) {
            Files.copy(dest, media.getStreamData());
        } else {
            new GUICaixaMensagem().caixaAlerta("erro!", "");
        }
    }

    public void uploadArquivo(Media[] media) {
        if (media != null) {
            for (int i = 0; i < media.length; i++) {
                if (new Arquivos().testaExtensao(media[i].getName())) {
                    // extensão permitida, testar conflito de arquivo:
                    if (!conflitoArquivo(media[i].getName())) {
                        // sem conflito de arquivo, preparar arquivo para upload:
                        GUILearningObjects.arquivoMedia = media[i];
                        caminho.setValue(media[i].getName());
                        break;
                    }
                } else {
                    // extensão não permitida:
                    new GUICaixaMensagem().caixaAlerta("O arquivo não atende ao formato especificado", "");
                    break;
                }
            }
        }
    }
    
    // determina se o arquivo já existe, dado o nome do arquivo:
    public boolean arquivoExiste(String nomeArquivo) {
        return new File( caminhoArquivo(nomeArquivo) ).exists();
    }
    
    // verifica se o arquivo a ser enviado tem o mesmo nome de algum arquivo associado a um objeto do mesmo projeto,
    // ou se já existe arquivo associado ao objeto atual e o arquivo a ser enviado tem um nome diferente:
    public boolean conflitoArquivo(String nomeArquivo) {
        if (caminhoObjetoEdicao == null) { // cadastro de novo objeto ou edição de objeto que não tem arquivo anterior
            if (arquivoExiste(nomeArquivo)) {
                new GUICaixaMensagem().caixaErro("Já existe um arquivo de mesmo nome associado a um objeto do mesmo projeto","");
                return true;
            }
            return false;
        }
        if (!nomeArquivo.equals(caminhoObjetoEdicao)) { // edição de objeto que tem arquivo anterior
            new GUICaixaMensagem().caixaErro("O nome do novo arquivo difere do nome do arquivo anterior","");
            return true;
        }
        return false;
    }

    // retorna a pasta na qual o arquivo será salvo, de acordo com o seu projeto:
    public String pastaArquivo() {
        if (projeto.getSelectedIndex() == -1)
            return "0/";
        else
            return ((Integer) projeto.getSelectedItem().getValue()).toString() + "/";
    }
    
    // retorna o caminho completo no qual o arquivo será salvo, dado o nome do arquivo:
    public String caminhoArquivo(String nomeArquivo) {
        return new Arquivos().getSavePath() + pastaArquivo() + nomeArquivo;
    }
    
    public void cadastrarArquivo() throws IOException {
        cadastraArquivo(GUILearningObjects.arquivoMedia);
        GUILearningObjects.arquivoMedia = null; // indica que ainda não há arquivo associado ao objeto atual
    }

    public void cadastraArquivo(Media media) throws IOException {
        
        Pessoa usu = (Pessoa) Sessions.getCurrent().getAttribute("logado");
        
        if (titulo.getValue().equalsIgnoreCase("") || aberto.getValue().equalsIgnoreCase(""))
            new GUICaixaMensagem().caixaErro("Preencha Todos os campos obrigatórios", "Erro");
        else if (usu == null)
            new GUICaixaMensagem().caixaErro("Não há usuário logado no sistema. Favor fazer login antes de cadastrar o arquivo.", "Erro");
        else {
            
            // upload do arquivo:
            if (media != null) {
                File diretorio = new File( new Arquivos().getSavePath() + pastaArquivo() );
                if (!diretorio.exists())
                    diretorio.mkdir();
                if (new Arquivos().getExtensao(media.getName()).equalsIgnoreCase("txt")) {//salvar arquivo txt

                    OutputStream outputStream = new FileOutputStream(new File( caminhoArquivo(media.getName()) ), true);

                    outputStream.write(media.getStringData().getBytes());
                    outputStream.flush();
                    outputStream.close();

                } else {  //salvar outros tipos de arquivo

                    OutputStream outputStream = new FileOutputStream(new File( caminhoArquivo(media.getName()) ), true);

                    InputStream inputStream = media.getStreamData();
                    byte[] buffer = new byte[1024];
                    for (int count; (count = inputStream.read(buffer)) != -1;) {
                        outputStream.write(buffer, 0, count);
                    }
                    outputStream.flush();
                    outputStream.close();
                    inputStream.close();
                }
            }

            // aponta para, ou instancia, o objeto que será cadastrado:
            Objeto objeto;
            if (objetoEdicao != null)          
                objeto = objetoEdicao;
            else
                objeto = new Objeto();

            //instancia projeto e seta o id dele com o que foi selecionado no select
            Projeto proj = new Projeto();

            if (projeto.getSelectedIndex() != -1) {//se projeto informado
                proj.setIdprojeto((Integer) projeto.getSelectedItem().getValue());
                objeto.setProjeto(proj);
            }

            objeto.setTitulo(titulo.getValue());
            objeto.setAutor(autor.getValue());
            objeto.setAssunto(assunto.getValue());
            objeto.setDataObjeto(dataObjeto.getValue());
            objeto.setTipo(tipo.getValue());
            objeto.setTag(tag.getValue());
            objeto.setDescricao(descricao.getValue());
            
            // se media != null, há arquivo associado:
            if (media != null)
                objeto.setLink( caminhoArquivo(media.getName()) );
            
            if (aberto.getValue().equals("Sim")) {
                objeto.setAberto(1);
            } else {
                objeto.setAberto(0);
            }
            if (status.getSelectedIndex() != -1) {// se status informado
                objeto.setStatus(status.getValue());              
            }
                     
            objeto.setPessoa(usu);
            
            // determina se é cadastro ou edição:
            boolean cadastro = (objetoEdicao == null);
            
            // cadastrar ou editar:
            new DAOObjeto().inserir(objeto);
            limparCampos();
            if (cadastro)
                limparResultadosDaBusca(); // porque não iremos verificar se o novo arquivo é um resultado da busca
            else {
                encerrarEdicao();
                verificarLinhaSelecionada(objeto);
            }
            
            new GUICaixaMensagem().caixaInformacao("Cadastro realizado com Sucesso!", "Sucesso");
        }
    }

    public void listarCombos() {
        List<Projeto> projetoList = new DAOProjeto().buscarTodos();
        projeto.getItems().clear();

        for (Projeto proj : projetoList) {
            Comboitem item = new Comboitem(proj.getNome());
            item.setValue(proj.getIdprojeto());
            projeto.appendChild(item);
        }
    }
    
    // inicia o modo de edição, para um objeto:
    public void iniciarEdicao(Objeto objeto) {        
        limparCampos();
        
        abas.setSelectedTab(abaCadastro);
        abaCadastro.setLabel(tituloEdicao);
        idObjeto = objeto.getIdobjeto();   
        cancelar.setVisible(true);
        
        titulo.setValue(objeto.getTitulo());
        autor.setValue(objeto.getAutor());
        assunto.setValue(objeto.getAssunto());
        dataObjeto.setValue(objeto.getDataObjeto());
        tipo.setValue(objeto.getTipo());
        tag.setValue(objeto.getTag());
        descricao.setValue(objeto.getDescricao());
        if (objeto.getAberto() != null) {
            if (objeto.getAberto() == 1)
                aberto.setValue("Sim");
            else
                aberto.setValue("Não");
        }
        if (objeto.getStatus() != null)
            status.setValue(objeto.getStatus());
        Projeto proj = objeto.getProjeto();
        if (proj != null) {
            int idProjeto = objeto.getProjeto().getIdprojeto();
            String nomeProjeto = new DAOProjeto().buscar(idProjeto).getNome();
            projeto.setValue(nomeProjeto);
        }
        ultimoProjetoSelecionado = projeto.getSelectedIndex();
        if (objeto.getLink() != null) {
            caminhoObjetoEdicao = new File( objeto.getLink() ).getName(); // extrai o nome do arquivo de objeto.getLink()
            caminho.setValue(caminhoObjetoEdicao);
        } else {
            caminhoObjetoEdicao = null;
            caminho.setValue(null);
        }
        objetoEdicao = objeto;
    }
    
    // encerra o modo de edição:
    public void encerrarEdicao() {
        abas.setSelectedTab(abaConsulta);
        abaCadastro.setLabel(tituloCadastro);
        cancelar.setVisible(false);
        objetoEdicao = null;
        caminhoObjetoEdicao = null;
    }
    
    // atualiza a linha de resultado, caso algum campo visível nessa linha tenha sido alterado:
    public void verificarLinhaSelecionada(Objeto objeto) {
        // se o objeto atualizado está na listagem, pode ser preciso atualizar os campos:
        if (linhaSelecionada != null) {
            // se o título foi modificado, atualizar esse campo na listagem:
            //   antes, remover qualquer estilo, pois se o título era nulo, um estilo foi adicionado:
            Label lTitulo = (Label)linhaSelecionada.getChildren().get(0);
            if (!objeto.getTitulo().equals( lTitulo.getValue() )){
                lTitulo.setStyle(null);
                lTitulo.setValue(objeto.getTitulo());
            }
            // se a visibilidade foi modificada, atualizar esse campo na listagem:
            //   antes, converter integer para string:
            String arquivoAberto;
            if (objeto.getAberto() == 1)
                arquivoAberto = "Sim";
            else
                arquivoAberto = "Não";
            if (!arquivoAberto.equals( ((Label)linhaSelecionada.getChildren().get(1)).getValue() ))
                ((Label)linhaSelecionada.getChildren().get(1)).setValue(arquivoAberto);
        }
    }
    
    // esvazia a lista de resultados da busca por arquivos:
    public void limparResultadosDaBusca() {
        listarArquivos.getRows().detach();
        listarArquivos.appendChild(new Rows());
    }
    
    // efetua a busca no banco de dados e lista os resultados na grid:
    public void listarArquivos() {
        limparResultadosDaBusca();

        List<Objeto> lista = new DAOObjeto().buscarArquivosEdicao(
            buscaTitulo.getValue(),
            buscaAutor.getValue(),
            buscaTag.getValue(),
            buscaLink.getValue()
        );
        
        for (final Objeto objeto : lista) {

            Row linhaPrincipal = null;
            linhaPrincipal = new Row();
            linhaPrincipal.setStyle("cursor:pointer");

            //
            //numero
            String numero;
            if (objeto.getIdobjeto() == null)
                numero = "";
             else
                numero = Integer.toString(objeto.getIdobjeto());
            Label nNumero = new Label(numero);
            linhaPrincipal.appendChild(nNumero);

            //titulo
            // se título for nulo e houver link, exibir link no lugar do título, com observação
            Label nTitulo = new Label(objeto.getTitulo());
            if ((objeto.getTitulo() == null) && (objeto.getLink() != null)) {
                nTitulo.setStyle("color:red");
                nTitulo.setMultiline(true);
                nTitulo.setValue("(Sem título)\n" + new File( objeto.getLink() ).getName());
            } else
                nTitulo.setValue(objeto.getTitulo());
            linhaPrincipal.appendChild(nTitulo);

            //aberto
            String arquivoAberto;
            if (objeto.getAberto() == null)
                arquivoAberto = "";
            else if (objeto.getAberto() == 1)
                arquivoAberto = "Sim";
            else
                arquivoAberto = "Não";
            Label nAberto = new Label(arquivoAberto);
            linhaPrincipal.appendChild(nAberto);
            
            //editar
            Button nEditar = new Button("");
            //nEditar.setStyle("background-color:#FFFFFF; border-width:0; cursor:pointer");
            nEditar.setStyle("cursor:pointer"); // remover esta linha se for utilizar a linha anterior
            nEditar.setImage("img/icones/001_45.png");
            final Row ponteiroLinhaPrincipal = linhaPrincipal;
            nEditar.addEventListener("onClick", new EventListener() {
                @Override
                public void onEvent(Event event) throws Exception {
                    iniciarEdicao(objeto);
                    linhaSelecionada = ponteiroLinhaPrincipal;
                }
            });
            linhaPrincipal.appendChild(nEditar);            
            
            //link
            Button nLink = new Button("");
            //nLink.setStyle("background-color:#FFFFFF; border-width:0; cursor:pointer");
            nLink.setStyle("cursor:pointer"); // remover esta linha se for utilizar a linha anterior
            nLink.setImage("img/download.png");
            nLink.addEventListener("onClick", new EventListener() {
                @Override
                public void onEvent(Event event) throws Exception {
                    new GUIModalLinkArquivo().exibir(objeto);
                }
            });
            linhaPrincipal.appendChild(nLink);

            //adiciona a linha no Grid
            listarArquivos.getRows().appendChild(linhaPrincipal);
        }      
        
        buscaTitulo.setValue(null);
        buscaAutor.setValue(null);
        buscaTag.setValue(null);        
        buscaLink.setValue(null);
        
        // nova busca realizada,
        // portanto nenhum item da lista foi obtido para edição,
        // por isso não há linha selecionada:
        linhaSelecionada = null;          
    }
    
    // esvazia os campos da aba de cadastro/edição:
    public void limparCampos() {
        buscaTitulo.setValue(null);
        buscaAutor.setValue(null);
        buscaTag.setValue(null);
        buscaLink.setValue(null);
        
        titulo.setValue(null);
        autor.setValue(null);
        assunto.setValue(null);
        dataObjeto.setValue(null);
        tipo.setValue(null);
        tag.setValue(null);
        descricao.setValue(null);
        aberto.setValue(null);
        aberto.setSelectedIndex(-1);
        status.setValue(null);
        status.setSelectedIndex(-1);
        projeto.setValue(null);
        projeto.setSelectedIndex(-1);
        caminho.setValue(null);
    }
    
    // usado pelo botão "Cancelar" do modo de edição:
    public void cancelar() {
        encerrarEdicao();
        limparCampos();
    }
    
    // usado pelo combobox "Projeto":
    public void atualizarProjeto() {
        // se estiver no modo de edição, e houver um arquivo associado ao objeto, impedir a alteração do projeto:
        if (caminhoObjetoEdicao != null) {
            new GUICaixaMensagem().caixaInformacao("O projeto não pode ser alterado, para evitar a alteração do link do arquivo", "Erro");
            projeto.setSelectedIndex(ultimoProjetoSelecionado);
        // senão, se a alteração do projeto gerar um conflito com outro arquivo, impedir essa alteração:
        } else if ( (arquivoMedia != null) && arquivoExiste(arquivoMedia.getName()) ) {
            new GUICaixaMensagem().caixaInformacao("Não foi possível selecionar este projeto, porque já existe um arquivo de mesmo nome associado a este projeto", "Erro");
            projeto.setSelectedIndex(ultimoProjetoSelecionado);            
        // senão, armazenar o índice selecionado:
        } else
            ultimoProjetoSelecionado = projeto.getSelectedIndex();
    }
    */
