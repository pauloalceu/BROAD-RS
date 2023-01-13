package broadrs.Agents;
import jade.core.Agent;
import jade.core.AID;
import java.util.Iterator;

public class AGEAdmin extends Agent  {

        public void action() {
            System.out.println( "Hello World. Eu sou um agente!" );
            System.out.println( "Todas as minhas informacoes: \n" + getAID().getName() );
        }
        protected void setup() {
        // Printout a welcome message
         System.out.println( "Hello World. Eu sou um agente!" );
         System.out.println( "Todas as minhas informacoes: \n" + getAID().getName() );
         //}
        }
}
