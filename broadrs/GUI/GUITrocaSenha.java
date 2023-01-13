
package broadrs.GUI;

import broadrs.util.HashGUI;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Hbox;
import org.zkoss.zul.Label;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;


public class GUITrocaSenha extends Window {
/*
    private Textbox login, senhaAtual, senhaNova, senhaNovaDeNovo;
    private Grid gridSenha;
    private Hbox box;
    private Label nome;

    public void onCreate() {

        login = (Textbox) getFellow("login");
        senhaAtual = (Textbox) getFellow("senhaAtual");
        senhaNova = (Textbox) getFellow("senhaNova");
        senhaNovaDeNovo = (Textbox) getFellow("senhaNovaDeNovo");
        gridSenha = (Grid) getFellow("gridSenha");

    }

    public void trocarSenha() {

        String passOld = HashGUI.md5(senhaAtual.getValue());

        boolean ok = new DAOPessoa().conferirLogin(login.getValue(), passOld);

        Pessoa usu = (Pessoa) Sessions.getCurrent().getAttribute("logado");
        
        if (login.getValue().equalsIgnoreCase("")
                || senhaAtual.getValue().equalsIgnoreCase("")
                || senhaNova.getValue().equalsIgnoreCase("")
                || senhaNovaDeNovo.getValue().equalsIgnoreCase("")) {

            new GUICaixaMensagem().caixaErro("Preencha Todos os campos", "Erro");
        } else if (usu == null) {
            new GUICaixaMensagem().caixaErro("Não há usuário logado no sistema. Favor fazer login antes de alterar a senha.", "Erro");
        } else {
            if (ok) {//login e senha atual conferem
                if (senhaNova.getValue().equals(senhaNovaDeNovo.getValue())) {//novas senhas conferem

                    String passNew = HashGUI.md5(senhaNova.getValue());
                    //cadastra usuario
                    Pessoa usuario = new DAOPessoa().buscarPorLogin(login.getValue());
                    usuario.setIdpessoa(usuario.getIdpessoa());
                    usuario.setLogin(login.getValue());
                    usuario.setSenha(passNew);
                    new DAOPessoa().inserir(usuario);
                    new GUICaixaMensagem().caixaInformacao("Senha alterada com sucesso", "Sucesso");

                    Executions.getCurrent().sendRedirect("trocaSenha.zul");

                } else {
                    new GUICaixaMensagem().caixaErro("Novas senhas não conferem", "Falha");
                }

            } else {
                new GUICaixaMensagem().caixaErro("Login ou senha atual não conferem", "Falha");
            }
        }
    }*/
}
