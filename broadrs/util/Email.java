

package broadrs.util;

import broadrs.GUI.GUICaixaMensagem;
import java.util.Date;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;


import org.zkoss.zk.ui.Sessions;


public class Email {

    public static void enviar(String mensagem, String assunto, String emailEnvio, String emailChegada) {


        Properties p = new Properties();
        p.put("mail.smtp.host", "mail.cead.ufjf.br");
        p.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(p, new AutenticarEmail());
        MimeMessage msg = new MimeMessage(session);

        try {
            msg.setFrom(new InternetAddress(emailEnvio));

            //msg.setRecipient(Message.RecipientType.TO, new InternetAddress("rafael.almeida@uab.ufjf.br"));
            msg.setRecipient(Message.RecipientType.TO, new InternetAddress(emailChegada));

            msg.setSentDate(new Date());

            msg.setSubject(assunto);

            msg.setContent(mensagem, "text/html");

            Transport.send(msg);

        } catch (MessagingException ex) {
           new GUICaixaMensagem().caixaErro("Email não enviado!", "Erro");
        }
    }


    public boolean ValidarEmail(String email)
    {
         String EMAIL_REGEX = "^[\\w-]+(\\.[\\w-]+)*@([\\w-]+\\.)+[a-zA-Z]{2,7}$";
         //String EMAIL_REGEX2 = ".+@.+\\.[a-z]+";

        //Definir a seqüência de padrão de e-mail
        Pattern p = Pattern.compile(EMAIL_REGEX);

        // Match Joga seqüência de dados com o padrão
        Matcher m = p.matcher(email);

        // Verifica se o Match jogado foi encontrado
        boolean matchEncon = m.matches();

        if (matchEncon){
            return true;
        }else{
            return false;
        }
    }
}

