package broadrs.GUI;

import broadrs.util.HashGUI;
import java.sql.SQLException;
import java.lang.*;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

public class GUILogin extends Window{

    private Textbox login, senha;
    private Grid gridLogin;
    private Hbox box;
    private Label nome;

    public void onCreate(){
        login = (Textbox) getFellow("login");
        senha = (Textbox) getFellow("senha");
        gridLogin = (Grid) getFellow("gridLogin");
    }

    public void logar() throws SQLException, Exception{

        String pass = HashGUI.md5(senha.getValue());

        boolean logou = new GUIDados().conferirLogin(login.getValue(), pass);

        if (logou) {
            Executions.getCurrent().sendRedirect("welcome.zul");

        } else {
            new GUICaixaMensagem().caixaErro("Login ou senha não conferem", "Falha");
        }
    }
}