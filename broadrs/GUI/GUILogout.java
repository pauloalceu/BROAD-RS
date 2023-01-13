package broadrs.GUI;
import jade.core.NotFoundException;
import jade.wrapper.ContainerController;
import jade.core.Profile;
import jade.mtp.MTPException;
import jade.core.ProfileImpl;
import jade.core.Runtime;
import jade.wrapper.StaleProxyException;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Window;

public class GUILogout extends Window{

    public void onCreate() throws StaleProxyException, MTPException, NotFoundException{
        Sessions.getCurrent().removeAttribute("logado");

       //Seta o container Main para os agentes
        Runtime runtime = Runtime.instance();
        Profile profile = new ProfileImpl();
        ContainerController container_controller = runtime.createMainContainer(profile);
        //Fim do Seta o container Main para os agentes
        //container_controller.uninstallMTP(_zclass)
        container_controller.kill();//Mata o conteiner com todos os agentes
        runtime.shutDown();
    }
}
