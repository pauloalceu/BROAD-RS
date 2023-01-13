package broadrs.util;

public class Telefone {
     public boolean validarTelefone(String tel) {

      String formato = "[0-9]{4}?[0-9]{4}?";

      if((tel == null) || (tel.length()!=8) || (!tel.matches(formato)))
         return false;

      else
         return true;
   }
}
