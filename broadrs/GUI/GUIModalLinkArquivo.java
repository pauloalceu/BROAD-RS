package broadrs.GUI;

import broadrs.util.AreaTransferencia;
import broadrs.util.Arquivos;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zul.Button;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class GUIModalLinkArquivo extends Window {
/*
    private Window win;
    private Textbox tLink;
//    private Button bCopiar;

    public void onCreate() {
    }

    public void exibir(Objeto objeto) {
        String link = new Arquivos().getLinkPath(objeto);
        if (link == null) {
            new GUICaixaMensagem().caixaInformacao("Não há arquivo associado ao objeto", "Erro");
        } else {
            try {
                instanciarJanela();
                win.setTitle(objeto.getTitulo());
                tLink.setValue(link);
                tLink.select();
                win.doModal();
            } catch (InterruptedException ex) {
                System.out.println("Erro edição do projeto: " + ex.getMessage());
            }
        }
    }

    private void instanciarJanela() {
        win = (Window) Executions.createComponents("/modal/modalLinkArquivo.zul", this.getParent(), null);
        win.setClosable(true);
        win.setBorder("normal");
        win.addEventListener("onOK", new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                copiar();
            }
        });
        win.addEventListener("onCancel", new EventListener() {

            @Override
            public void onEvent(Event event) throws Exception {
                cancelar();
            }
        });

        tLink = (Textbox) win.getFellow("tLink");

//        bCopiar = (Button) win.getFellow("bCopiar");
//        bCopiar.setStyle("cursor:pointer");
//        bCopiar.addEventListener("onClick", new EventListener() {
//
//            @Override
//            public void onEvent(Event event) throws Exception {
//                copiar();
//            }
//        });
    }

    private void copiar() {
        AreaTransferencia.copiar(tLink.getValue());
        win.detach();
    }

    private void cancelar() {
        win.detach();
    }*/
}
