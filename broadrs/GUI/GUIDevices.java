package broadrs.GUI;

import java.util.List;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Grid;
import org.zkoss.zul.Label;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Row;
import org.zkoss.zul.Rows;
import org.zkoss.zul.Window;
import org.zkoss.zk.ui.event.Event; // Para fazer o Excluir
import org.zkoss.zk.ui.event.EventListener; // Para fazer o Excluir
import org.zkoss.zul.Messagebox; // Para fazer o Excluir
import java.text.SimpleDateFormat; //Para data
import java.text.DateFormat; //Para data
import org.zkoss.zul.Combobox; //Para Combo
import org.zkoss.zul.Comboitem; //Para Combo

public class GUIDevices extends Window {
/*
    private Grid listarTodos;
    private Textbox Nome;
    private Textbox filter1;
    private Tabpanels ABA1;
    private Button Gravar;
    private Integer ID;
    private Grid listarArquivos;

    public void Visualizar() {
        listarArquivos = (Grid) getFellow("listarArquivos");
        listarArquivos.getRows().detach();
        listarArquivos.appendChild(new Rows());
        listarArquivos.setVisible(true);
        new GUIWelcome().listarArquivos(listarArquivos);
    }

    public void onCreate() throws Exception {
        listarTodos = (Grid) getFellow("listarTodos");
        filter1 = (Textbox) getFellow("filter1");
        Nome = (Textbox) getFellow("Nome");
        ABA1 = (Tabpanels) getFellow("ABA1");
        Gravar = (Button) getFellow("Gravar");
        Listar();
    }

    public void Limpar() {
        Gravar.setLabel("Incluir");
        Gravar.setImage("img/001_06.png");
        Nome.setValue("");
        Abilitar(false);
    }

    public void Passadados(Purpose Formato) {
        Nome.setValue(Formato.getValue());
    }

    public void Abilitar(Boolean Valor) {
        Nome.setDisabled(Valor);
    }

    public void Filtrar(Event event) throws Exception {
        ((Textbox) event.getTarget()).setValue(((org.zkoss.zk.ui.event.InputEvent) event).getValue());
        Listar();
    }

    public void Listar() throws Exception {
        listarTodos.getRows().detach();
        listarTodos.appendChild(new Rows());
        List<Purpose> lista = new DAOProposito().buscarTodos();
        for (final Purpose Formato : lista) {
            if (Formato.getValue().toLowerCase().indexOf(filter1.getValue().trim().toLowerCase()) >= 0) {
                Row linhaPrincipal = null;
                linhaPrincipal = new Row();
                linhaPrincipal.setStyle("cursor:pointer");
                Label nNome = new Label(Formato.getValue()); //nome
                linhaPrincipal.appendChild(nNome); //nome
                Button nApagar = new Button("");
                nApagar.setImage("img/001_05.png");
                nApagar.addEventListener("onClick", new EventListener() {
                    public void onEvent(Event evt) throws Exception {
                        Messagebox.show("Deseja realmente excluir: " + Formato.getValue() + "?", "Confirmação", Messagebox.YES | Messagebox.NO, Messagebox.QUESTION,
                                new EventListener() {
                                    public void onEvent(Event evt) throws Exception {
                                        if (evt.getName().equals("onYes")) {
                                            new DAOProposito().acao(Formato, "apagar");
                                            Listar();
                                        }
                                    }
                                });
                    }
                });
                nApagar.setStyle("cursor:pointer");
                linhaPrincipal.appendChild(nApagar);
                Button nAlterar = new Button("");
                nAlterar.setImage("img/001_45.png");
                nAlterar.addEventListener("onClick", new EventListener() {
                    public void onEvent(Event event) throws Exception {
                        ABA1.getTabboxApi().setSelectedIndex(0);
                        Gravar.setLabel("Alterar");
                        Gravar.setImage("img/001_45.png");
                        Passadados(Formato);
                        ID = Formato.getId();
                        Abilitar(false);
                    }
                });
                nAlterar.setStyle("cursor:pointer");
                linhaPrincipal.appendChild(nAlterar);
                listarTodos.getRows().appendChild(linhaPrincipal);
                linhaPrincipal.addEventListener("onClick", new EventListener() {
                    public void onEvent(Event event) throws Exception {
                        ABA1.getTabboxApi().setSelectedIndex(0);
                        Gravar.setLabel("Voltar");
                        Gravar.setImage("img/001_21.png");
                        Passadados(Formato);
                        Abilitar(true);
                    }
                });
            }
        }
    }

    public void Gravar() throws Exception {
        if (Gravar.getLabel().equals("Voltar")) {
            ABA1.getTabboxApi().setSelectedIndex(1);
            return;
        }
        if (Nome.getValue().equalsIgnoreCase("")) {
            Messagebox.show("Preencha Todos os campos obrigatórios", "Atenção", Messagebox.OK, Messagebox.INFORMATION);
        } else {
            Purpose Formato = new Purpose();
            if (Gravar.getLabel().equals("Alterar")) {
                Formato = new DAOProposito().buscar(ID);
            }
            Formato.setValue(Nome.getValue());
            new DAOProposito().acao(Formato, "inserir");
            Limpar();
            Listar();
        }
    }*/
}