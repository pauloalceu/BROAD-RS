package broadrs.GUI;

import broadrs.util.Cep;
import broadrs.util.Cpf;
import broadrs.util.Email;
import broadrs.util.Telefone;
import com.hp.hpl.jena.ontology.Individual;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.query.*;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.ontology.OntClass;
import com.hp.hpl.jena.ontology.OntProperty;
import com.hp.hpl.jena.ontology.Ontology;
import com.hp.hpl.jena.rdf.model.Property;
import com.hp.hpl.jena.rdf.model.Resource;
import java.io.File;
import java.io.FileOutputStream;



public class GUILearners extends Window{

    private int idpessoa;

    private Textbox nome;
    private Textbox cpf;
    //private Object Cpf;
    private boolean permissaoDeCadastro;
    private Textbox endereco;
    private Textbox numero;
    private Textbox complemento;
    private Textbox bairro;
    private Textbox cep;
    private Textbox selectEstado;
    private Textbox cidade;
    private Textbox email;
    private Textbox ddd_telresidencial, telefoneresidencial;
    private Textbox ddd_telefonecelular, telefonecelular;
    private Grid listarArquivos;


    public void Visualizar() {
        listarArquivos = (Grid) getFellow("listarArquivos");
        listarArquivos.getRows().detach();
        listarArquivos.appendChild(new Rows());
        listarArquivos.setVisible(true);
        new GUIRecommendation().listarArquivos(listarArquivos);
    }

    public void onCreate() {

        consultar();
 
    }

    public void cadastraPessoa() {


            //idpessoa = pessoa.getIdPessoa();

            nome.setValue("");
            cpf.setValue("");
            endereco.setValue("");
            numero.setValue("");
            complemento.setValue("");
            bairro.setValue("");
            cep.setValue("");
            selectEstado.setValue("");
            cidade.setValue("");
            ddd_telresidencial.setValue("");
            telefoneresidencial.setValue("");
            ddd_telefonecelular.setValue("");
            telefonecelular.setValue("");
            email.setValue("");


            new GUICaixaMensagem().caixaInformacao("Cadastro realizado com Sucesso!", "Sucesso");
           
            //Arquivo arquivo = new Arquivo(pessoa);
            //Executions.getCurrent().sendRedirect("arquivo.zul");
        

    }


    public void validaCpf() {

       // boolean existe = new DAOPessoa().existeCpf(cpf.getValue());
        boolean existe = true;
        @SuppressWarnings("static-access")
        boolean valido = new Cpf().validaCpf(cpf.getValue());
        if (existe) {
            permissaoDeCadastro = false;
            throw new WrongValueException(cpf, "O CPF já foi cadastrado!");
        } else {

            if (valido) {
                permissaoDeCadastro = true;
            } else {
                permissaoDeCadastro = false;
                throw new WrongValueException(cpf, "O CPF não é válido!");
            }
        }
    }

    public void validarEmail() {

        boolean valido = new Email().ValidarEmail(email.getValue());
        if (valido) {
            permissaoDeCadastro = true;
        } else {
            permissaoDeCadastro = false;
            throw new WrongValueException(email, "O Email não é válido!");
        }

    }

    public void verificaCep() {

        boolean valido = new Cep().verificaCep(cep.getValue());
        if (valido) {
            permissaoDeCadastro = true;
        } else {
            permissaoDeCadastro = false;
            throw new WrongValueException(cep, "O CEP não é válido, verifique o número e digitos!");
        }

    }

    public void validarTelefoneCel() {
        boolean validocel = new Telefone().validarTelefone(telefonecelular.getValue());

        if (validocel) {
            permissaoDeCadastro = true;
        } else {
            permissaoDeCadastro = false;
            throw new WrongValueException(telefonecelular, "O Telefone Celular não é válido, verifique o número e digitos!");
        }

    }

    public void validarTelefone() {
        boolean valido = new Telefone().validarTelefone(telefoneresidencial.getValue());
        if (valido) {
            permissaoDeCadastro = true;
        } else {
            permissaoDeCadastro = false;
            throw new WrongValueException(telefoneresidencial, "O Telefone Residenial não é válido, verifique o número e digitos!");
        }
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

        String queryString= "PREFIX bc: <http://www.semanticweb.org/ontologies/2013/4/Ontology1367952328156.owl#> select ?x ?z ?t ?s WHERE {?x bc:has_learner_name ?z. ?x bc:has_learner_age ?t . ?x bc:has_learner_sex ?s} LIMIT 1000";

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

        Label nTitulo5 = new Label();
        nTitulo5.setValue(sol.get("t").asLiteral().getString());
        linhaPrincipal.appendChild(nTitulo5);

        Label nTitulo6 = new Label();
        nTitulo6.setValue(sol.get("s").asLiteral().getString());
        linhaPrincipal.appendChild(nTitulo6);

        listarArquivos.getRows().appendChild(linhaPrincipal);

    }
    }
}
/*
        OntClass c = ontModel.getOntClass("http://www.semanticweb.org/ontologies/2013/4/Ontology1367952328156.owl#" + "learner");
        listarArquivos = (Grid) getFellow("listarArquivos");
        listarArquivos.getRows().detach();
        listarArquivos.appendChild(new Rows());
        listarArquivos.setVisible(true);

        for(Iterator i = c.listInstances();i.hasNext();)
        {
                    Individual next=(Individual) i.next();
                    temp5 = next.toString().split("#");

                    Row linhaPrincipal = null;
                    linhaPrincipal = new Row();

                    Label nTitulo = new Label();
                    nTitulo.setValue(temp5[1].toString());
                    linhaPrincipal.appendChild(nTitulo);
                        for(Iterator t=c.listDeclaredProperties();t.hasNext();)
                        {
                                OntProperty p=(OntProperty)t.next();
                                if (p.getLocalName().equals("has_learner_name")){
                                    Label nTitulo2 = new Label();
                                    nTitulo2.setValue(next.getPropertyValue(p)+ "");
                                    linhaPrincipal.appendChild(nTitulo2);
                                }
                        }
                        for(Iterator t=c.listDeclaredProperties();t.hasNext();)
                        {
                                OntProperty p=(OntProperty)t.next();
                                if (p.getLocalName().equals("has_leaner_years")){
                                    Label nTitulo3 = new Label();
                                    nTitulo3.setValue(next.getPropertyValue(p)+ "");
                                    linhaPrincipal.appendChild(nTitulo3);
                                }
                        }

                    listarArquivos.getRows().appendChild(linhaPrincipal);

        }*/
    


