package broadrs.Agents;
import broadrs.GUI.GUIWelcome;
import org.zkoss.zk.ui.Sessions;
import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.UnreadableException;
import java.util.Iterator;
import jade.lang.acl.ACLMessage;
import jade.core.behaviours.CyclicBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AGEPlanner extends Agent {

        protected void setup() {
            

            //Registra os agentes nas páginas amarelas
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName(getAID());
            ServiceDescription sd = new ServiceDescription();
            sd.setType("Planner");
            sd.setName("Planner-service");
            dfd.addServices(sd);
            //Registra os agentes nas páginas amarelas

            addBehaviour(new CyclicBehaviour(this) {
                 Usuarios[] usu = new Usuarios[9];

                int cont = 0;
               
            @Override
                public void action() {
                    String Valor1,Valor2,Valor3,Valor4,Valor5;
                    ACLMessage msg = receive();
                    if (msg!=null){
                        try {
                            usu[cont] = (Usuarios) msg.getContentObject();
                            System.out.println("Resposta - Recebemos " + usu[cont].campo + " " + usu[cont].valor);
                            if (cont == 8)
                            {
                                System.out.println("QUERY: SELECT " + usu[0].campo + " " + usu[0].valor + " | ");
                                System.out.println("QUERY: SELECT " + usu[1].campo + " " + usu[1].valor + " | ");
                                System.out.println("QUERY: SELECT " + usu[2].campo + " " + usu[2].valor + " | ");
                                System.out.println("QUERY: SELECT " + usu[3].campo + " " + usu[3].valor + " | ");
                                System.out.println("QUERY: SELECT " + usu[4].campo + " " + usu[4].valor + " | ");
                                System.out.println("QUERY: SELECT " + usu[5].campo + " " + usu[5].valor + " | ");
                                System.out.println("QUERY: SELECT " + usu[6].campo + " " + usu[6].valor + " | ");
                                System.out.println("QUERY: SELECT " + usu[7].campo + " " + usu[7].valor + " | ");
                                System.out.println("QUERY: SELECT " + usu[8].campo + " " + usu[8].valor + " | ");

                                Valor1 = usu[1].valor.toString(); //Idusuário
                                if (usu[3].valor.equals("false")){ //Visual
                                    Valor2 = "0";
                                }else{
                                    Valor2 = "1";
                                }
                                if (usu[4].valor.equals("false")){ //Auditivo
                                    Valor3 = "0";
                                }else{
                                    Valor3 = "1";
                                }
                                if (usu[5].valor.equals("false")){ //Repetente
                                    Valor4 = "0";
                                }else{
                                    Valor4 = "1";
                                }
                                if (usu[6].valor.equals("false")){ //Inglês
                                    Valor5 = "0";
                                }else{
                                    Valor5 = "1";
                                }

                             /*   List<DidaticView> lista = new DAODidatica_cad().buscarOA2(Valor1,Valor2,Valor3,Valor4,Valor5);

                                for (final DidaticView objeto : lista) {
                                    //Gravando em LOG
                                    Log Log_obj = new Log();
                                    Log_obj.setIdUsuario(Integer.parseInt(usu[1].valor));
                                    Log_obj.setIdObjeto(objeto.getObjeto().getIdobjeto());
                                    Log_obj.setDescricao("teste");
                                    Log_obj.setNota(0);
                                    Log_obj.setData(new Date(System.currentTimeMillis()).toString());
                                    new DAOLog().acao(Log_obj, "inserir");
                                }*/

                            }
                            cont = cont + 1;
                        } catch (UnreadableException ex) {
                        }
                    }else{
                    // inter rompe este comportamento ate que chegue uma nova mensagem
                    block();
                    }
                }
            }) ;
        }
}

/*

package cead.roa.Agents;
import jade.core.Agent;
import jade.core.AID;
import java.util.Iterator;
import jade.lang.acl.ACLMessage;
import jade.core.behaviours.TickerBehaviour;

public class AGEPlanner extends Agent  {

        public void action() {
            System.out.println( "Hello World. Eu sou um agente!" );
            System.out.println( "Todas as minhas informacoes: \n" + getAID().getName() );
        }
        protected void setup() {
        // Printout a welcome message
         System.out.println( "Hello World. Eu sou um agente!" );
         System.out.println( "Todas as minhas informacoes: \n" + getAID().getName() );
         //}
        // Printout a welcome message
         System.out.println( "Hello World. Eu sou um agente!" );
         System.out.println( "Todas as minhas informacoes: \n" + getAID().getName() );
         //System.out.println( "Meu nome local e " + getAID().getLocalName() );
         //System.out.println( "Meu nome global (GUID) e" + getAID().getName() );
         //System.out.println( "Meus enderecos s~ao:");
         //Iterator it = getAID().getAllAddresses();

         //while (it.hasNext() ) {
         //   System.out.println("- "+ it.next());
         //}

	/*protected void setup() {

           // new CaixaMensagemGUI().caixaAlerta("Hello World. Eu sou um agente!","Atenção" ) ;
            new CaixaMensagemGUI().caixaAlerta("Todas as minhas informações: \n" + getAID() ,"Atenção" );
            /*new CaixaMensagemGUI().caixaAlerta("Meu nome local é " + getAID().getLocalName(),"Atenção");
            new CaixaMensagemGUI().caixaAlerta("Meu nome global (GUID) é " + getAID().getName(),"Atenção");
            new CaixaMensagemGUI().caixaAlerta("Meus enderecos são:","Atenção");
            Iterator it = getAID().getAllAddresses();
            while (it.hasNext ()) {
            new CaixaMensagemGUI().caixaAlerta("- " + it.next(),"") ;
            }
            super.setup();*/
           //addBehaviour(new ImprimeFrase(this, 5000));
           // new CaixaMensagemGUI().caixaAlerta("Alo Mundo!", "Atenção");
            //new CaixaMensagemGUI().caixaAlerta("Meu nome: "+ getLocalName(), "Atenção");
	//}*/



               /* addBehaviour(new TickerBehaviour(this, 10000) {
                protected void onTick() {
                System.out.println( "Teste de comportamento!" );
                ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
                msg.setOntology("Aluno-Perfil");
                msg.setLanguage("ACL");
                msg.addReceiver(new AID("agentProfessor",AID.ISLOCALNAME));
                msg.setContent("Aluno João Pedro");
                send(msg);
                }
                });*/



