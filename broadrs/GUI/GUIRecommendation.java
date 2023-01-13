package broadrs.GUI;

import jade.core.Agent;
import jade.Boot;
import jade.core.AID;
import jade.core.Profile;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.AgentContainer;
import jade.wrapper.AgentController;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import jade.wrapper.ContainerController;


import jade.wrapper.ControllerException;
import jade.wrapper.StaleProxyException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;
import org.zkoss.zul.Grid;
import java.util.List;
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
import org.zkoss.io.Files;
import org.zkoss.zhtml.Fileupload;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;

import com.clarkparsia.pellet.owlapiv3.PelletReasoner;
import com.clarkparsia.pellet.owlapiv3.PelletReasonerFactory;
import com.hp.hpl.jena.query.QueryExecution;
import com.hp.hpl.jena.query.QueryExecutionFactory;
import com.hp.hpl.jena.query.QuerySolution;
import com.hp.hpl.jena.query.ResultSet;
import com.hp.hpl.jena.rdf.model.InfModel;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.reasoner.ReasonerException;
import com.hp.hpl.jena.sparql.function.library.e;

import java.io.File;
import java.io.FileInputStream;
import org.zkoss.zk.ui.Executions;
import java.util.List;
import java.io.FileNotFoundException;
import java.util.HashMap;
import javax.swing.JOptionPane;
import org.mindswap.pellet.KnowledgeBase;
import org.mindswap.pellet.jena.PelletInfGraph;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Window;
import org.zkoss.zul.Row;
import org.zkoss.zul.Filedownload;
import org.zkoss.zul.Label;


public class GUIRecommendation extends Window{

    private Grid listarArquivos_high;
    private Grid listarArquivos_low;
    private Grid listarArquivos_mid;
    private AgentController  agent_aluno_controller;
    private AgentController  agent_professor;

    public void onCreate() throws StaleProxyException, ControllerException {


   /*

       //Seta o container Main para os agentes
        Runtime runtime = Runtime.instance();
        Profile profile = new ProfileImpl();
        ContainerController container_controller = runtime.createMainContainer(profile);
        //Fim do Seta o container Main para os agentes


            // Cria o agente dentro do container de agentes
            agent_professor = container_controller.createNewAgent("agentProfessor_", "cead.roa.Agents.AGEProfessor", new Object[]{new Object()});
            //Fim de Cria o agente dentro do container de agentes

            //Cria o agente dentro do container de agentes
            agent_aluno_controller = container_controller.createNewAgent("agentAluno_" , "cead.roa.Agents.AGEAluno", new Object[]{new Object()});
            //Fim de Cria o agente dentro do container de agentes


        // Cria o agente dentro do container de agentes
        AgentController agent_planner = container_controller.createNewAgent("agentPlanner", "cead.roa.Agents.AGEPlanner", new Object[]{new Object()});
        //Fim de Cria o agente dentro do container de agentes

        // Cria o agente dentro do container de agentes
        AgentController agent_admin = container_controller.createNewAgent("agentAdmin", "cead.roa.Agents.AGEAdmin", new Object[]{new Object()});
        //Fim de Cria o agente dentro do container de agentes

        // Cria o agente RMA dentro do container de agentes
        // ESTA PARTE É SÓ PARA DEBUGAÇÂO
        AgentController agent_RMA = container_controller.createNewAgent("myConsole", "jade.tools.rma.rma", new Object[]{new Object()});
        agent_RMA.start();
        agent_RMA.activate();
        //Fim de Cria o agente RMA dentro do container de agentes


        //PARA APRESENTAÇÂO POR BREAKPOINT AKI
        agent_planner.start();
        agent_planner.activate();

            agent_aluno_controller.start();
            agent_aluno_controller.activate();

           // agent_professor.start();W
           // agent_professor.activate();

        agent_admin.start();
        agent_admin.activate();
*/
        consultar();

    }

    public void Visualizar() {
        consultar();
    }

    public void listarArquivos(Grid listar) {

    }

    public void consultar(){

        try{

            String sistema = System.getProperty("os.name");
            System.out.println("Voce esta usando: "+sistema);
            String Caminho;


            OWLOntologyManager manager = OWLManager.createOWLOntologyManager();


            if (sistema.equals("Linux"))
            {
               Caminho = "/var/lib/tomcat-7-broad/webapps/owl/PERSONNA.owl";
            }
            else
            {
               Caminho = "C:/BROAD-RS/broad-rs/owl/PERSONNA.owl";
            }
            File owlFile = new File(Caminho);
            OWLOntology ont = manager.loadOntologyFromOntologyDocument(owlFile);

            PelletReasoner pelletReasoner = PelletReasonerFactory.getInstance().createNonBufferingReasoner(ont);
            KnowledgeBase kb = pelletReasoner.getKB();
            PelletInfGraph graph = new org.mindswap.pellet.jena.PelletReasoner().bind(kb);

            InfModel infModel = ModelFactory.createInfModel( graph );

            String sql = "PREFIX bc: <http://www.semanticweb.org/ontologies/2013/4/Ontology1367952328156.owl#> select ?x ?z ?y WHERE {?x bc:has_recomended_high  bc:" + Sessions.getCurrent().getAttribute("logado_login").toString()  + " . ?x bc:has_learning_objects_URL ?z . ?x bc:has_learning_objects_general_title ?y} LIMIT 1000";

            QueryExecution qe = QueryExecutionFactory.create(sql, infModel);
            ResultSet results =  qe.execSelect();

        listarArquivos_high = (Grid) getFellow("listarArquivos_high");
        listarArquivos_high.getRows().detach();
        listarArquivos_high.appendChild(new Rows());
        listarArquivos_high.setVisible(true);


    java.util.Date dt = new java.util.Date();
    java.text.SimpleDateFormat sdf =  new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String Date = sdf.format(dt);


    while(results.hasNext()) {

        QuerySolution sol = results.nextSolution();
        Row linhaPrincipal = null;
        linhaPrincipal = new Row();

        Label nTitulo3 = new Label();
        nTitulo3.setValue(sol.get("y").asLiteral().getString());
        linhaPrincipal.appendChild(nTitulo3);

        Label nTitulo4 = new Label();
        nTitulo4.setValue(sol.get("z").asLiteral().getString());
        linhaPrincipal.appendChild(nTitulo4);

        boolean cadastrou = new GUIDados().insereObjetosdeAprendizagem(Sessions.getCurrent().getAttribute("logado_login").toString(),sol.get("x").asResource().getLocalName().toString(),"high",sol.get("y").asLiteral().getString(),sol.get("z").asLiteral().getString(),Date);


            //download
            Button nDownload = new Button("");
            //nDownload.setStyle("background-color:#FFFFFF; border-width:0;");
            nDownload.setImage("img/download.png");
            nDownload.addEventListener("onClick", new EventListener() {
                @Override
                public void onEvent(Event event) throws Exception {
                   // baixarArquivo(objetoAux);
                }
            });
            linhaPrincipal.appendChild(nDownload);

            //Botoes Joinha
            final Button nJoinha1 = new Button("");
            final Button nJoinha2 = new Button("");
            //final Log Log_obj = new Log();

            nJoinha1.setImage("img/joinha1.png");
            nJoinha1.addEventListener("onClick", new EventListener() {
                @Override
                public void onEvent(Event event) throws Exception {
                    nJoinha1.setDisabled(true);
                    nJoinha2.setDisabled(false);
                }
            });
            linhaPrincipal.appendChild(nJoinha1);

            nJoinha2.setImage("img/joinha2.png");
            nJoinha2.addEventListener("onClick", new EventListener() {
                @Override
                public void onEvent(Event event) throws Exception {
                    nJoinha2.setDisabled(true);
                    nJoinha1.setDisabled(false);
                }
            });
            linhaPrincipal.appendChild(nJoinha2);

        listarArquivos_high.getRows().appendChild(linhaPrincipal);
      }
           sql = "PREFIX bc: <http://www.semanticweb.org/ontologies/2013/4/Ontology1367952328156.owl#> select ?x ?z ?y WHERE {?x bc:has_recomended_mid  bc:" + Sessions.getCurrent().getAttribute("logado_login").toString()  + " . ?x bc:has_learning_objects_URL ?z . ?x bc:has_learning_objects_general_title ?y} LIMIT 1000";

            qe = QueryExecutionFactory.create(sql, infModel);
            results =  qe.execSelect();

        listarArquivos_mid = (Grid) getFellow("listarArquivos_mid");
        listarArquivos_mid.getRows().detach();
        listarArquivos_mid.appendChild(new Rows());
        listarArquivos_mid.setVisible(true);


    while(results.hasNext()) {

        QuerySolution sol2 = results.nextSolution();
        Row linhaPrincipal2 = null;
        linhaPrincipal2 = new Row();

        Label nTitulo3 = new Label();
        nTitulo3.setValue(sol2.get("y").asLiteral().getString());
        linhaPrincipal2.appendChild(nTitulo3);

        Label nTitulo4 = new Label();
        nTitulo4.setValue(sol2.get("z").asLiteral().getString());
        linhaPrincipal2.appendChild(nTitulo4);

        boolean cadastrou = new GUIDados().insereObjetosdeAprendizagem(Sessions.getCurrent().getAttribute("logado_login").toString(),sol2.get("x").asResource().getLocalName().toString(),"mid",sol2.get("y").asLiteral().getString(),sol2.get("z").asLiteral().getString(),Date);


            //download
            Button nDownload2 = new Button("");
            //nDownload.setStyle("background-color:#FFFFFF; border-width:0;");
            nDownload2.setImage("img/download.png");
            nDownload2.addEventListener("onClick", new EventListener() {
                @Override
                public void onEvent(Event event) throws Exception {
                   // baixarArquivo(objetoAux);
                }
            });
            linhaPrincipal2.appendChild(nDownload2);

            //Botoes Joinha
            final Button nJoinha5 = new Button("");
            final Button nJoinha6 = new Button("");
            //final Log Log_obj = new Log();

            nJoinha5.setImage("img/joinha1.png");
            nJoinha5.addEventListener("onClick", new EventListener() {
                @Override
                public void onEvent(Event event) throws Exception {
                    nJoinha5.setDisabled(true);
                    nJoinha6.setDisabled(false);
                }
            });
            linhaPrincipal2.appendChild(nJoinha5);

            nJoinha6.setImage("img/joinha2.png");
            nJoinha6.addEventListener("onClick", new EventListener() {
                @Override
                public void onEvent(Event event) throws Exception {
                    nJoinha6.setDisabled(true);
                    nJoinha5.setDisabled(false);
                }
            });
            linhaPrincipal2.appendChild(nJoinha6);

        listarArquivos_mid.getRows().appendChild(linhaPrincipal2);

    }

           sql = "PREFIX bc: <http://www.semanticweb.org/ontologies/2013/4/Ontology1367952328156.owl#> select ?x ?z ?y WHERE {?x bc:has_recomended_low  bc:" + Sessions.getCurrent().getAttribute("logado_login").toString()  + " . ?x bc:has_learning_objects_URL ?z . ?x bc:has_learning_objects_general_title ?y} LIMIT 1000";

            qe = QueryExecutionFactory.create(sql, infModel);
            results =  qe.execSelect();

        listarArquivos_low = (Grid) getFellow("listarArquivos_low");
        listarArquivos_low.getRows().detach();
        listarArquivos_low.appendChild(new Rows());
        listarArquivos_low.setVisible(true);


    while(results.hasNext()) {

        QuerySolution sol3 = results.nextSolution();
        Row linhaPrincipal3 = null;
        linhaPrincipal3 = new Row();

        Label nTitulo5 = new Label();
        nTitulo5.setValue(sol3.get("y").asLiteral().getString());
        linhaPrincipal3.appendChild(nTitulo5);

        Label nTitulo6 = new Label();
        nTitulo6.setValue(sol3.get("z").asLiteral().getString());
        linhaPrincipal3.appendChild(nTitulo6);

        boolean cadastrou = new GUIDados().insereObjetosdeAprendizagem(Sessions.getCurrent().getAttribute("logado_login").toString(),sol3.get("x").asResource().getLocalName().toString(),"low",sol3.get("y").asLiteral().getString(),sol3.get("z").asLiteral().getString(),Date);

            //download
            Button nDownload3 = new Button("");
            //nDownload.setStyle("background-color:#FFFFFF; border-width:0;");
            nDownload3.setImage("img/download.png");
            nDownload3.addEventListener("onClick", new EventListener() {
                @Override
                public void onEvent(Event event) throws Exception {
                   // baixarArquivo(objetoAux);
                }
            });
            linhaPrincipal3.appendChild(nDownload3);

            //Botoes Joinha
            final Button nJoinha7 = new Button("");
            final Button nJoinha8 = new Button("");
            //final Log Log_obj = new Log();

            nJoinha7.setImage("img/joinha1.png");
            nJoinha7.addEventListener("onClick", new EventListener() {
                @Override
                public void onEvent(Event event) throws Exception {
                    nJoinha7.setDisabled(true);
                    nJoinha8.setDisabled(false);
                }
            });
            linhaPrincipal3.appendChild(nJoinha7);

            nJoinha8.setImage("img/joinha2.png");
            nJoinha8.addEventListener("onClick", new EventListener() {
                @Override
                public void onEvent(Event event) throws Exception {
                    nJoinha8.setDisabled(true);
                    nJoinha7.setDisabled(false);
                }
            });
            linhaPrincipal3.appendChild(nJoinha8);

        listarArquivos_low.getRows().appendChild(linhaPrincipal3);

    }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Wasn't possible to get all services.\n\n" + e , ".: BROAD-RS :.", JOptionPane.ERROR_MESSAGE);

        }
    }






        /*
        OntModel model=ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);
        OntModel data=ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM);

        model.read("file:///C:/BROAD-RS/broad-rs/owl/PERSONNA.owl");
         data.read("file:///C:/BROAD-RS/broad-rs/owl/PERSONNA.owl");

        Reasoner reasoner = ReasonerRegistry.getOWLReasoner();
        reasoner = reasoner.bindSchema(model);

        InfModel infmodel = (InfModel) ModelFactory.createInfModel(reasoner, data);


        String[] temp5;
        String[] temp6;

        listarArquivos = (Grid) getFellow("listarArquivos");
        listarArquivos.getRows().detach();
        listarArquivos.appendChild(new Rows());
        listarArquivos.setVisible(true);

        String queryString= "PREFIX bc: <http://www.semanticweb.org/ontologies/2013/4/Ontology1367952328156.owl#> select ?x ?z WHERE {?x bc:has_recomended  ?z} LIMIT 1000";

        Query query=QueryFactory.create(queryString);
        QueryExecution qexec = QueryExecutionFactory.create(query, infmodel);
        ResultSet results = qexec.execSelect();

    while(results.hasNext()) {

        QuerySolution sol = results.nextSolution();
        Row linhaPrincipal = null;
        linhaPrincipal = new Row();

        Label nTitulo3 = new Label();
        nTitulo3.setValue(sol.get("x").asResource().getLocalName().toString());
        linhaPrincipal.appendChild(nTitulo3);

        Label nTitulo4 = new Label();
        nTitulo4.setValue(sol.get("z").toString());
        linhaPrincipal.appendChild(nTitulo4);

       Label nTitulo5 = new Label();
        nTitulo5.setValue(sol.get("t").toString());
        linhaPrincipal.appendChild(nTitulo5);

        listarArquivos.getRows().appendChild(linhaPrincipal);

    }
*/





    /*        public void baixarArquivo(Objeto objeto) {
                if (objeto.getLink() == null)
                    new GUICaixaMensagem().caixaInformacao("Não há arquivo associado ao objeto", "Erro");
                else
                    try {
                        //Filedownload.save(new File("C:\\teste\\teste.jpg"), "image/jpg");
                        Filedownload.save(new File(objeto.getLink()), "image/jpg");
                    } catch (FileNotFoundException ex) {
                    }
            }*/

/*
        String[] temp;
        for(Iterator i=ontModel.listClasses();i.hasNext();)
        {
              OntClass cls=(OntClass)i.next();

              System.out.println("Properties class:"+cls.getLocalName());
              for(Iterator j=cls.listDeclaredProperties();j.hasNext();)
              {
                   temp = j.next().toString().split("#");
                   System.out.println("\t"+temp[1].toString());
              }
        }*/

/*        System.out.println("-----------------------------");
        String[] temp5;
	for(Iterator i=ontModel.listClasses();i.hasNext();)
        {
                OntClass c=(OntClass)i.next();
                if(c.isAnon()) continue;
                System.out.println("Classe: "+c.getLocalName());
                for(Iterator j=c.listInstances();j.hasNext();)
                {
                        Individual next=(Individual) j.next();
                        temp5 = next.toString().split("#");
                        System.out.println(" - individuo: "+temp5[1].toString());
                        for(Iterator t=c.listDeclaredProperties();t.hasNext();)
                        {
                                OntProperty p=(OntProperty)t.next();
                                System.out.print("\t Propriedade " + p.getLocalName()+": ");
                                System.out.print(" de Valor: " + next.getPropertyValue(p) + "\n");
                        }
                }
        }*/
    }