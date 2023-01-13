package broadrs.GUI;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class GUIConexao {
     //private static final String URL = "jdbc:mysql://localhost/broadrs";
     //private static final String DRIVER = "com.mysql.jdbc.Driver";
     //private static final String USUARIO = "root";
     //private static final String SENHA = "n2e4a6d8";

     private static final String URL = "jdbc:mysql://192.168.20.202/broadrs";
     private static final String DRIVER = "com.mysql.jdbc.Driver";
     private static final String USUARIO = "admin";
     private static final String SENHA = "octavio202#";

     public static Connection conectar() throws SQLException,InstantiationException,IllegalAccessException {
         try {
             Class.forName(DRIVER);
             return DriverManager.getConnection(URL,USUARIO,SENHA);
         } catch (ClassNotFoundException e) {
             throw new SQLException(e.getMessage());
         }
     }

    /** Creates a new instance of Conexao */
    public GUIConexao() {
    }
}
