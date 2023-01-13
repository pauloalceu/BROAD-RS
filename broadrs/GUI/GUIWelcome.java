package broadrs.GUI;

import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Window;
import org.zkoss.zul.Label;


public class GUIWelcome extends Window{

    private Label nome;
    private Label tipodeAcesso;

    public void onCreate() {
        nome = (Label) getFellow("nomePessoa");
        nome.setValue("Welcome: " + Sessions.getCurrent().getAttribute("logado_nome"));
        tipodeAcesso = (Label) getFellow("tipodeAcesso");
        tipodeAcesso.setValue("Type of User: " + Sessions.getCurrent().getAttribute("logado_tipo"));
    }
    } 