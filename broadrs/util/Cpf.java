
package broadrs.util;

import bsh.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.text.MaskFormatter;
import org.zkoss.zk.ui.WrongValueException;
import broadrs.util.Cpf;

public class Cpf {
    private static Object evento;
    private static Object campoSoNumeros;
    public static boolean validaCpf(String cpf)
    {
//------- Rotina para CPF
       if (cpf.length() == 11 )
       {

           if(cpf.equals("11111111111") || cpf.equals("22222222222") || cpf.equals("33333333333") || cpf.equals("44444444444")
                    || cpf.equals("55555555555") || cpf.equals("66666666666") || cpf.equals("77777777777")
                     || cpf.equals("88888888888") || cpf.equals("99999999999") || cpf.equals("00000000000")){

                     return false;
           }


           int     d1, d2;
           int     digito1, digito2, resto;
           int     digitoCPF;
           String  nDigResult;
           d1 = d2 = 0;
           digito1 = digito2 = resto = 0;
           for (int n_Count = 1; n_Count < cpf.length() -1; n_Count++)
           {
              digitoCPF = Integer.valueOf (cpf.substring(n_Count -1, n_Count)).intValue();
//--------- Multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
              d1 = d1 + ( 11 - n_Count ) * digitoCPF;
//--------- Para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
              d2 = d2 + ( 12 - n_Count ) * digitoCPF;
           }
//--------- Primeiro resto da divisão por 11.
           resto = (d1 % 11);
//--------- Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
           if (resto < 2)
              digito1 = 0;
           else
              digito1 = 11 - resto;
           d2 += 2 * digito1;
//--------- Segundo resto da divisão por 11.
           resto = (d2 % 11);
//--------- Se o resultado for 0 ou 1 o digito é 0 caso contrário o digito é 11 menos o resultado anterior.
           if (resto < 2)
              digito2 = 0;
           else
              digito2 = 11 - resto;
//--------- Digito verificador do CPF que está sendo validado.
           String nDigVerific = cpf.substring (cpf.length()-2, cpf.length());
//--------- Concatenando o primeiro resto com o segundo.
           nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
//--------- Comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
           return nDigVerific.equals(nDigResult);
        }
//-------- Rotina para CNPJ
        else if (cpf.length() == 14)
        {
            int soma = 0, aux, dig;
            String cnpj_calc = cpf.substring(0,12);
            char[] chr_cnpj = cpf.toCharArray();
//--------- Primeira parte
            for( int i = 0; i < 4; i++ )
            if ( chr_cnpj[i]-48 >=0 && chr_cnpj[i]-48 <=9 )
              soma += (chr_cnpj[i] - 48) * (6 - (i + 1));
            for( int i = 0; i < 8; i++ )
            if ( chr_cnpj[i+4]-48 >=0 && chr_cnpj[i+4]-48 <=9 )
                soma += (chr_cnpj[i+4] - 48) * (10 - (i + 1));
            dig = 11 - (soma % 11);
            cnpj_calc += ( dig == 10 || dig == 11 ) ?
                "0" : Integer.toString(dig);
//--------- Segunda parte
            soma = 0;
            for ( int i = 0; i < 5; i++ )
            if ( chr_cnpj[i]-48 >=0 && chr_cnpj[i]-48 <=9 )
                soma += (chr_cnpj[i] - 48) * (7 - (i + 1));
            for ( int i = 0; i < 8; i++ )
            if ( chr_cnpj[i+5]-48 >=0 && chr_cnpj[i+5]-48 <=9 )
                soma += (chr_cnpj[i+5] - 48) * (10 - (i + 1));
            dig = 11 - (soma % 11);
            cnpj_calc += ( dig == 10 || dig == 11 ) ?
                        "0" : Integer.toString(dig);
            return cpf.equals(cnpj_calc);
        }
        else
            return false;
    }
}

