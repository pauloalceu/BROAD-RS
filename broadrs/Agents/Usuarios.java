package broadrs.Agents;

import jade.util.leap.Serializable;
// Uma classe que tera seus objetos  serializados deve implementar ainterface Serializable//

public class Usuarios implements Serializable{
    String campo;
    String valor;

    public Usuarios(String campo, String valor){
        this.campo = campo;
        this.valor = valor;
    }

}
