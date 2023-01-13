package broadrs.util;

public class Cep {
    public boolean verificaCep(String str_cep){
        if ( str_cep.substring(0,1).equals("") || str_cep.length() != 8){
            return false;
        }
    return true;
    }
}
