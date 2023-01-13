

package broadrs.util;

import javax.mail.Authenticator;
import javax.mail.PasswordAuthentication;


public class AutenticarEmail extends Authenticator{

   @Override
   public PasswordAuthentication getPasswordAuthentication() {
    String username, password;
    username = "cead";
    password = "";

    return new PasswordAuthentication(username, password);
 }
}