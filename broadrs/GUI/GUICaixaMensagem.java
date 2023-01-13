package broadrs.GUI;

import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class GUICaixaMensagem extends Window{

    protected boolean bt;

    public void caixaInformacao(String msg, String titulo){
         try {
                Messagebox.show(msg, titulo, Messagebox.OK, Messagebox.INFORMATION);
            } catch (InterruptedException ex) {
                System.out.println("Erro na caixa de mensagem: "+ex.getMessage());
            }
    }

    public void caixaErro(String msg, String titulo){

         try {
                Messagebox.show(msg, titulo, Messagebox.OK, Messagebox.ERROR);
            } catch (InterruptedException ex) {
                System.out.println("Erro na caixa de mensagem: "+ex.getMessage());
            }
    }

    public void caixaAlerta(String msg, String titulo){

         try {
                Messagebox.show(msg, titulo, Messagebox.OK, Messagebox.EXCLAMATION);
            } catch (InterruptedException ex) {
                System.out.println("Erro na caixa de mensagem: "+ex.getMessage());
            }
    }
}