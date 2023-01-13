package broadrs.GUI;


import broadrs.util.HashGUI;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.Rows;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class GUIUpdateMyPassword extends Window {

    private Textbox senhaAtual, senhaNova, senhaNovaDeNovo;
    private Grid gridSenha;
    private Hbox box;
    private Label nome;
    private Grid listarArquivos;

    public void Visualizar() {
        listarArquivos = (Grid) getFellow("listarArquivos");
        listarArquivos.getRows().detach();
        listarArquivos.appendChild(new Rows());
        listarArquivos.setVisible(true);
        new GUIRecommendation().listarArquivos(listarArquivos);
    }
    
    public void onCreate() {


        if (Sessions.getCurrent().getAttribute("logado_login").toString() == ""){
        new GUICaixaMensagem().caixaErro("Por favor, insira seus dados de login e senha", "Erro");
        Executions.getCurrent().sendRedirect("index.zul");
        }

        senhaAtual = (Textbox) getFellow("senhaAtual");
        senhaNova = (Textbox) getFellow("senhaNova");
        senhaNovaDeNovo = (Textbox) getFellow("senhaNovaDeNovo");
        gridSenha = (Grid) getFellow("gridSenha");

    }

    public void trocarSenha() throws SQLException, Exception {

        String passOld = HashGUI.md5(senhaAtual.getValue());

       
         boolean ok = new GUIDados().conferirLogin(Sessions.getCurrent().getAttribute("logado_login").toString(), passOld);


        if (senhaAtual.getValue().equalsIgnoreCase("")
                || senhaNova.getValue().equalsIgnoreCase("")
                || senhaNovaDeNovo.getValue().equalsIgnoreCase("")) {

            new GUICaixaMensagem().caixaErro("Preencha Todos os campos", "Erro");
        } else if (Sessions.getCurrent().getAttribute("logado_login") == "") {
            new GUICaixaMensagem().caixaErro("Não há usuário logado no sistema. Favor fazer login antes de alterar a senha.", "Erro");
        } else {
            if (ok) {//login e senha atual conferem
                if (senhaNova.getValue().equals(senhaNovaDeNovo.getValue())) {//novas senhas conferem

                    String passNew = HashGUI.md5(senhaNova.getValue());
                    //cadastra usuario

                    boolean mudou = new GUIDados().atualizasenha(passNew);
                    new GUICaixaMensagem().caixaInformacao("Senha alterada com sucesso", "Sucesso");


                   // Executions.getCurrent().sendRedirect("UpdateMyPassword.zul");

                } else {
                    new GUICaixaMensagem().caixaErro("Novas senhas não conferem", "Falha");
                }

            } else {
                new GUICaixaMensagem().caixaErro("Login ou senha atual não conferem", "Falha");
            }
        }
    }
}
