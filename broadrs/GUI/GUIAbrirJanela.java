package broadrs.GUI;


import org.zkoss.zul.Window;

public abstract class GUIAbrirJanela <K> extends Window{

    public abstract void abrirJanela(K obj);

    public abstract K abrirJanela();
}
