
package broadrs.util;

import java.io.File;
import org.zkoss.zk.ui.util.GenericForwardComposer;


public class Arquivos extends GenericForwardComposer {

//    private static final String SAVE_PATH = "/var/www/roa/";
    private static final String SAVE_PATH = "c:/teste/";
    private static final String LINK_PATH = "www.cead.ufjf.br/roa/";


    public String getSavePath() {
//        String SAVE_PATH = "";
//        java.net.InetAddress i;
//        try {
//            i = java.net.InetAddress.getLocalHost();
//            SAVE_PATH = i.getHostAddress();
//        } catch (UnknownHostException ex) {
//            Logger.getLogger(Arquivos.class.getName()).log(Level.SEVERE, null, ex);
//        }
        return SAVE_PATH;
    }
    
    
    public String getLinkPath() {
        return LINK_PATH;
    }
    
  /*  public String getLinkPath(Objeto objeto) {
        // se objeto não tem arquivo associado:
        if (objeto.getLink() == null)
            return null;
        // se objeto tem arquivo associado:
        String projeto;
        if (objeto.getProjeto() != null)
            projeto = objeto.getProjeto().getIdprojeto().toString() + "/";
        else
            projeto = "0/";
        return new Arquivos().getLinkPath() + projeto + new File( objeto.getLink() ).getName();        
    }*/


    public boolean testaExtensao(String nome){

        if ((nome.substring(nome.length() - 3, nome.length()).equals("jpg"))
        || (nome.substring(nome.length() - 3, nome.length()).equals("psd"))
        || (nome.substring(nome.length() - 3, nome.length()).equals("cdr"))
        || (nome.substring(nome.length() - 2, nome.length()).equals("ai"))
        || (nome.substring(nome.length() - 3, nome.length()).equals("pdf"))
        || (nome.substring(nome.length() - 3, nome.length()).equals("ind"))
        || (nome.substring(nome.length() - 3, nome.length()).equals("avi"))
        || (nome.substring(nome.length() - 3, nome.length()).equals("mpg"))
        || (nome.substring(nome.length() - 3, nome.length()).equals("mov"))
        || (nome.substring(nome.length() - 3, nome.length()).equals("wmv"))
        || (nome.substring(nome.length() - 3, nome.length()).equals("flv"))
        || (nome.substring(nome.length() - 3, nome.length()).equals("swf"))
        || (nome.substring(nome.length() - 3, nome.length()).equals("zip"))
        || (nome.substring(nome.length() - 3, nome.length()).equals("rar"))

                ){
            return true;
        }else{
            return false;
        }
    }

    public String getExtensao(String nome){

        String extensao = nome.substring(nome.length() - 3, nome.length());

            return extensao;

    }



       
}




