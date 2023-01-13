package broadrs.Agents;
import jade.core.Agent;
import jade.core.AID;
import java.util.Iterator;
import jade.lang.acl.ACLMessage;
import jade.core.behaviours.CyclicBehaviour;

public class AGEProfessor extends Agent {

        protected void setup() {
            addBehaviour(new  CyclicBehaviour(this) {
                public void action() {
                    ACLMessage msg = receive();
                    if (msg!=null)
                    System.out.println("Resposta - Recebemos " + msg.getContent());
                    // inter rompe este comportamento ate que chegue uma nova mensagem
                    block();
                }
            }) ;
        }
}
