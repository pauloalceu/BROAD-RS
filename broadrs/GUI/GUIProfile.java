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
import java.io.FileOutputStream;


import java.util.List;

import broadrs.util.Cep;
import broadrs.util.Cpf;

import broadrs.util.Email;
import broadrs.util.Telefone;
import com.hp.hpl.jena.ontology.Individual;
import com.hp.hpl.jena.ontology.OntModel;
import com.hp.hpl.jena.ontology.OntModelSpec;
import com.hp.hpl.jena.rdf.model.ModelFactory;
import com.hp.hpl.jena.rdf.model.Resource;
import com.hp.hpl.jena.rdf.model.StmtIterator;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.WrongValueException;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Comboitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class GUIProfile extends Window{

    private Textbox nome;
    private Intbox idade;
    private Combobox Device;
    private Combobox language;
    private Combobox country;
    private Combobox preferences_media;
    private Combobox cognitive_styles;
    private Textbox security_number;
    private Textbox email_contact;
    private Combobox interrest;
    private Combobox teacher;
    private Textbox telephone;


    public void onCreate() {
        nome = (Textbox) getFellow("nome");
        idade = (Intbox) getFellow("idade");
        Device = (Combobox) getFellow("Device");
        language = (Combobox) getFellow("language");
        country = (Combobox) getFellow("country");
        preferences_media = (Combobox) getFellow("preferences_media");
        cognitive_styles = (Combobox) getFellow("cognitive_styles");
        security_number = (Textbox) getFellow("security_number");
        email_contact = (Textbox) getFellow("email_contact");
        interrest = (Combobox) getFellow("interrest");
        teacher = (Combobox) getFellow("teacher");
        telephone = (Textbox) getFellow("telephone");



        String PrefixURI = "http://www.semanticweb.org/ontologies/2013/4/Ontology1367952328156.owl#";

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

        Property learner_name= ontModel.getProperty(PrefixURI +"has_learner_name");
        Property has_learner_age= ontModel.getProperty(PrefixURI +"has_learner_age");
        
        

        
        Individual P5=ontModel.getIndividual(PrefixURI + Sessions.getCurrent().getAttribute("logado_login").toString()); //Pegando o inividuo logado

        nome.setValue(P5.getProperty(learner_name).getLiteral().getString());
        idade.setValue(P5.getProperty(has_learner_age).getLiteral().getInt());






        //Preenchendo combobox
        Property has_device= ontModel.getProperty(PrefixURI +"has_device");
        OntClass device=ontModel.getOntClass(PrefixURI + "device");
        Iterator i = device.listInstances();
        while (i.hasNext())
        {
               String valor = ((Individual)i.next()).getLocalName().toString();
               //System.out.println(P5.getProperty(has_device).getResource().getLocalName().toString());
               Comboitem item = new Comboitem(valor);
               item.setValue(valor);
               Device.appendChild(item);
               if (valor.equals(P5.getProperty(has_device).getResource().getLocalName().toString())){
                    Device.setSelectedItem(item);
               }
        }
        //Preenchendo combobox

        //Preenchendo combobox
        Property has_learner_preferences_language= ontModel.getProperty(PrefixURI +"has_learner_preferences_language");
        OntClass languages=ontModel.getOntClass(PrefixURI + "languages");
        Iterator i2 = languages.listInstances();
        while (i2.hasNext())
        {
               String valor2 = ((Individual)i2.next()).getLocalName().toString();
               Comboitem item = new Comboitem(valor2);
               item.setValue(valor2);
               language.appendChild(item);
               if (valor2.equals(P5.getProperty(has_learner_preferences_language).getResource().getLocalName().toString())){
                    language.setSelectedItem(item);
               }
        }
        //Preenchendo combobox

        //Preenchendo combobox
        Property has_learner_country= ontModel.getProperty(PrefixURI +"has_learner_country");
        OntClass countrys=ontModel.getOntClass(PrefixURI + "country");
        Iterator i3 = countrys.listInstances();
        while (i3.hasNext())
        {
               String valor3 = ((Individual)i3.next()).getLocalName().toString();
               Comboitem item = new Comboitem(valor3);
               item.setValue(valor3);
               country.appendChild(item);
               if (valor3.equals(P5.getProperty(has_learner_country).getResource().getLocalName().toString())){
                    country.setSelectedItem(item);
               }
        }
        //Preenchendo combobox

        //Preenchendo combobox
        Property has_learner_preferences_media= ontModel.getProperty(PrefixURI +"has_learner_preferences_media");
        OntClass preferences_medias=ontModel.getOntClass(PrefixURI + "media");
        Iterator i4 = preferences_medias.listInstances();
        while (i4.hasNext())
        {
               String valor4 = ((Individual)i4.next()).getLocalName().toString();
               Comboitem item = new Comboitem(valor4);
               item.setValue(valor4);
               preferences_media.appendChild(item);
               if (valor4.equals(P5.getProperty(has_learner_preferences_media).getResource().getLocalName().toString())){
                    preferences_media.setSelectedItem(item);
               }
        }
        //Preenchendo combobox

        //Preenchendo combobox
        Property has_learner_cognitive_styles= ontModel.getProperty(PrefixURI +"has_learner_cognitive_styles");
        OntClass cognitive_style=ontModel.getOntClass(PrefixURI + "cognitive_styles");
        Iterator i5 = cognitive_style.listInstances();
        while (i5.hasNext())
        {
               String valor5 = ((Individual)i5.next()).getLocalName().toString();
               Comboitem item = new Comboitem(valor5);
               item.setValue(valor5);
               cognitive_styles.appendChild(item);
               if (valor5.equals(P5.getProperty(has_learner_cognitive_styles).getResource().getLocalName().toString())){
                    cognitive_styles.setSelectedItem(item);
               }
        }
        //Preenchendo combobox

        Property has_learner_social_security_number= ontModel.getProperty(PrefixURI +"has_learner_social_security_number");
        security_number.setValue(P5.getProperty(has_learner_social_security_number).getLiteral().getString());

        Property has_learner_email_contact= ontModel.getProperty(PrefixURI +"has_learner_email_contact");
        email_contact.setValue(P5.getProperty(has_learner_email_contact).getLiteral().getString());

        //Preenchendo combobox
        Property has_interrest= ontModel.getProperty(PrefixURI +"has_interrest");
        OntClass domain=ontModel.getOntClass(PrefixURI + "domain");
        Iterator i6 = domain.listInstances();
        while (i6.hasNext())
        {
               String valor6 = ((Individual)i6.next()).getLocalName().toString();
               Comboitem item = new Comboitem(valor6);
               item.setValue(valor6);
               interrest.appendChild(item);
               if (valor6.equals(P5.getProperty(has_interrest).getResource().getLocalName().toString())){
                    interrest.setSelectedItem(item);
               }
        }
        //Preenchendo combobox

        //Preenchendo combobox
        Property has_teacher= ontModel.getProperty(PrefixURI +"has_teacher");
        OntClass teacher2=ontModel.getOntClass(PrefixURI + "teacher");
        Iterator i7 = teacher2.listInstances();
        while (i7.hasNext())
        {
               String valor7 = ((Individual)i7.next()).getLocalName().toString();
               Comboitem item = new Comboitem(valor7);
               item.setValue(valor7);
               teacher.appendChild(item);
               if (valor7.equals(P5.getProperty(has_teacher).getResource().getLocalName().toString())){
                    teacher.setSelectedItem(item);
               }
        }
        //Preenchendo combobox

        Property has_learner_telephone= ontModel.getProperty(PrefixURI +"has_learner_telephone");
        telephone.setValue(P5.getProperty(has_learner_telephone).getLiteral().getString());

        ontModel.close();


    }

    public void alteraDados() {
        //ABRINDO ONTOLOGIA
        OntModel ontModel;
        ontModel = ModelFactory.createOntologyModel(OntModelSpec.OWL_MEM); //OWL_LITE_MEM_RULES_INF

            String sistema = System.getProperty("os.name");
            System.out.println("Voce esta usando: "+sistema);
            String Caminho;
            String Caminho2;
            if (sistema.equals("Linux"))
            {
               Caminho = "file:///var/lib/tomcat-7-broad/webapps/owl/PERSONNA.owl";
               Caminho2 = "/var/lib/tomcat-7-broad/webapps/owl/PERSONNA.owl";
            }
            else
            {
               Caminho = "file:///C:/BROAD-RS/broad-rs/owl/PERSONNA.owl";
                Caminho2 = "/var/lib/tomcat-7-broad/webapps/owl/PERSONNA.owl";
            }

        ontModel.read(Caminho);

        //ABRINDO ONTOLOGIA
      
        String PrefixURI = "http://www.semanticweb.org/ontologies/2013/4/Ontology1367952328156.owl#";

        Property learner_name= ontModel.getProperty(PrefixURI +"has_learner_name");
        Property has_learner_age= ontModel.getProperty(PrefixURI +"has_learner_age");

        Individual P5=ontModel.getIndividual(PrefixURI + Sessions.getCurrent().getAttribute("logado_login").toString()); //Salvando o individuo logado

        P5.setPropertyValue(learner_name, ontModel.createTypedLiteral(nome.getValue().toString()));
        P5.setPropertyValue(has_learner_age, ontModel.createTypedLiteral(idade.getValue()));





        Property has_device= ontModel.getProperty(PrefixURI +"has_device");
        Individual device2=ontModel.getIndividual(PrefixURI + Device.getValue().toString());
        P5.setPropertyValue(has_device, device2);
        

        Property has_learner_preferences_language= ontModel.getProperty(PrefixURI +"has_learner_preferences_language");
        Individual language2=ontModel.getIndividual(PrefixURI + language.getValue().toString());
        P5.setPropertyValue(has_learner_preferences_language, language2);

        Property has_learner_country= ontModel.getProperty(PrefixURI +"has_learner_country");
        Individual country2=ontModel.getIndividual(PrefixURI + country.getValue().toString());
        P5.setPropertyValue(has_learner_country, country2);

        Property has_learner_preferences_media= ontModel.getProperty(PrefixURI +"has_learner_preferences_media");
        Individual preferences_media2=ontModel.getIndividual(PrefixURI + preferences_media.getValue().toString());
        P5.setPropertyValue(has_learner_preferences_media, preferences_media2);

        Property has_learner_cognitive_styles= ontModel.getProperty(PrefixURI +"has_learner_cognitive_styles");
        Individual cognitive_styles2=ontModel.getIndividual(PrefixURI + cognitive_styles.getValue().toString());
        P5.setPropertyValue(has_learner_cognitive_styles, cognitive_styles2);

        Property has_learner_social_security_number= ontModel.getProperty(PrefixURI +"has_learner_social_security_number");
        P5.setPropertyValue(has_learner_social_security_number, ontModel.createTypedLiteral(security_number.getValue().toString()));

        Property has_learner_email_contact= ontModel.getProperty(PrefixURI +"has_learner_email_contact");
        P5.setPropertyValue(has_learner_email_contact, ontModel.createTypedLiteral(email_contact.getValue().toString()));

        Property has_interrest= ontModel.getProperty(PrefixURI +"has_interrest");
        Individual interrest2=ontModel.getIndividual(PrefixURI + interrest.getValue().toString());
        P5.setPropertyValue(has_interrest, interrest2);

        Property has_teacher= ontModel.getProperty(PrefixURI +"has_teacher");
        Individual teacher3=ontModel.getIndividual(PrefixURI + teacher.getValue().toString());
        P5.setPropertyValue(has_teacher, teacher3);


        Property has_learner_telephone= ontModel.getProperty(PrefixURI +"has_learner_telephone");
        P5.setPropertyValue(has_learner_telephone, ontModel.createTypedLiteral(telephone.getValue().toString()));



        //new GUICaixaMensagem().caixaErro("Caminho:" + Caminho, "OK");
        //SALVANDO ONTOLOGIA
        File file= new File(Caminho2);
        try {
            ontModel.write(new FileOutputStream(file), "RDF/XML");
        } catch (FileNotFoundException ex) {
            new GUICaixaMensagem().caixaErro("Error Write: " + ex.getMessage(), "Error.");
        }
        //FIM SALVANDO ONTOLOGIA
        ontModel.close();
        new GUICaixaMensagem().caixaErro("Success!", "OK");

    }
}
