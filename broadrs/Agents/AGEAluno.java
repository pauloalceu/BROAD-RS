package broadrs.Agents;
import jade.core.Agent;
import jade.core.AID;
import jade.lang.acl.ACLMessage;
import java.io.IOException;
import org.zkoss.zk.ui.Sessions;

import jade.core.behaviours.SimpleBehaviour;
import jade.domain.FIPAAgentManagement.DFAgentDescription;
import jade.domain.FIPAAgentManagement.ServiceDescription;

public class AGEAluno extends Agent  {

    Usuarios[] usu = new Usuarios[9];
    //Pessoa logado = (Pessoa) Sessions.getCurrent().getAttribute("logado"); //Pega o usuário logadi


        protected void setup() {

            //Registra os agentes nas páginas amarelas
            DFAgentDescription dfd = new DFAgentDescription();
            dfd.setName(getAID());
            ServiceDescription sd = new ServiceDescription();
            sd.setType("Aluno");
            sd.setName("Aluno-service");
            dfd.addServices(sd);
            //Registra os agentes nas páginas amarelas

            //Pega dados que quem tá logando e cria um OBJ pra mandar por mensagem
            usu[0] = new Usuarios("Nome","oi");
            usu[1] = new Usuarios("Idpessoa","oi");
            usu[2] = new Usuarios("Idade","oi");
            usu[3] = new Usuarios("DeficienteVisual","oi");
            usu[4] = new Usuarios("DeficienteAuditivo","oi");
            usu[5] = new Usuarios("repetente","oi");
            usu[6] = new Usuarios("ingles","oi");
            usu[7] = new Usuarios("HorarioEstudo","oi");
            usu[8] = new Usuarios("OQueFaz","oi");


            //Pega dados que quem tá logando e cria um OBJ pra mandar por mensagem
           /* usu[0] = new Usuarios("Nome",logado.getNome().toString());
            usu[1] = new Usuarios("Idpessoa",logado.getIdpessoa().toString());
            usu[2] = new Usuarios("Idade",logado.getIdade().toString());
            usu[3] = new Usuarios("DeficienteVisual",logado.getDeficienteVisual().toString());
            usu[4] = new Usuarios("DeficienteAuditivo",logado.getDeficienteAuditivo().toString());
            usu[5] = new Usuarios("repetente",logado.getRepetente().toString());
            usu[6] = new Usuarios("ingles",logado.getIngles().toString());
            usu[7] = new Usuarios("HorarioEstudo",logado.getHorarioEstudo().toString());
            usu[8] = new Usuarios("OQueFaz",logado.getOQueFaz().toString());  */

        addBehaviour(new SimpleBehaviour(this) {// incio do comportamento
            int cont = 0; //contador

        @Override
        public void action () {
        try {
            ACLMessage msg = new ACLMessage(ACLMessage.QUERY_IF);
            msg.setOntology("Aluno-Perfil");
            msg.setLanguage("ACL");
            msg.addReceiver(new AID("agentPlanner",AID.ISLOCALNAME));
            msg.setContentObject(usu[cont]);
            send(msg);
            cont = cont + 1;
        } catch (IOException ex)
        {
            System.out.println( "Erro ao enviar mensagem" );
        }
        }

        @Override
        public boolean done() {
        if (cont == 9){
           // myAgent.clean(true);
           // myAgent.doDelete() ; // finalizao agente até enviar todas as informações
            return true ;
        } else {
            return false;
        }
        }

        } ); // fim do comportamento
}
    @Override
    protected void takeDown() {
        System.out.println("Todas as informações foram enviadas" ) ;
    }
}
