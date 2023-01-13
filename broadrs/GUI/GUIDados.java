package broadrs.GUI;

import java.awt.HeadlessException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;
import org.zkoss.zk.ui.Sessions;

public class GUIDados {

     public boolean atualizasenha(String pass) throws SQLException, Exception{
        //Criar query SQL.
        String sql;
        sql = "update pessoa set senha=? where idpessoa=?";

        //Conectar ao banco.
        Connection con = GUIConexao.conectar();
        con.setAutoCommit(false);
        try {
            try {

                //Preparar chamada.
                CallableStatement cs = con.prepareCall(sql);
                cs.setString(1, pass);
                cs.setString(2, Sessions.getCurrent().getAttribute("logado_id").toString() );

                //Submeter mudança.
                cs.execute();

            } catch (SQLException se) {
                con.rollback();
                con.close();
                return false;
            }
            con.commit();
            con.close();
            System.out.println("ativ pess atualizado...");
            return true;
        } catch (Exception e) {
            con.rollback();
            con.close();
            return false;
        }
    }

    public boolean conferirLogin(String login, String pass) throws SQLException, Exception{
        //Criar query SQL.
        String sql = "SELECT * FROM pessoa WHERE login = ? and senha = ?";

        //Conectar ao banco.
        PreparedStatement s = null;
        try {
            //Preparar chamada.
            s = GUIConexao.conectar().prepareStatement(sql);
            s.setString(1, login);
            s.setString(2, pass);

            //Executar query.
            ResultSet r = s.executeQuery();

            //Iniciar array list.
            if(r.next() != false)
            {
                Sessions.getCurrent().setAttribute("logado_nome", r.getString("nome"));
                Sessions.getCurrent().setAttribute("logado_login", r.getString("login"));
                Sessions.getCurrent().setAttribute("logado_tipo",  r.getString("tipo"));
                Sessions.getCurrent().setAttribute("logado_id",  r.getString("idpessoa"));

                s.close();
                return true;
            }
        } catch (SQLException se) {
            throw se;
        } catch (Exception e) {
            throw e;
        } finally {
            s.close();
        }
        return false;
    }




    public boolean insereObjetosdeAprendizagem(String learner, String LO, String Type, String general_title, String LO_URL, String Data) throws SQLException, Exception
    {
        //Criar query SQL.
        String sql;
        sql = "insert into recommendation (learner, LO, Type,LO_general_title, LO_URL, date) values(?,?,?,?,?,?)";

        //Conectar ao banco.
        Connection con = GUIConexao.conectar();
        con.setAutoCommit(false);
        try {
            try {

                //Preparar chamada.
                CallableStatement cs = con.prepareCall(sql);
                cs.setString(1, learner);
                cs.setString(2, LO);
                cs.setString(3, Type);
                cs.setString(4, general_title);
                cs.setString(5, LO_URL);
                cs.setString(6, Data);

                //Submeter mudança.
                cs.execute();

            } catch (SQLException se) {
                con.rollback();
                con.close();
                return false;
            }
            con.commit();
            con.close();
            return true;
        } catch (Exception e) {
            con.rollback();
            con.close();
            return false;
        }
    }


    //Para estatisticas
    //SELECT learner, count(id) as quant, Type, count(r.like) as likes, count(r.unlike) as unlikes  FROM recommendation r group by learner,Type;

/*

    //Atualizar campo existente.
    public boolean atualizar(EntAtivacaoPessoa ativacaoPessoa) throws SQLException, InstantiationException, HeadlessException, IllegalAccessException, ValidarException
    {
        //Criar query SQL.
        String sql;
        sql = "update ativacao_pessoa set funcao=?, curso=?, polo=? where id_pessoa=?";

        //Conectar ao banco.
        Connection con = GUIConexao.conectar();
        con.setAutoCommit(false);
        try {
            try {

                //Preparar chamada.
                CallableStatement cs = con.prepareCall(sql);
                cs.setInt(1, ativacaoPessoa.getFuncao());
                cs.setInt(2, ativacaoPessoa.getCurso());
                cs.setInt(3, ativacaoPessoa.getPolo());
                cs.setInt(4, ativacaoPessoa.getIdPessoa());

                //Submeter mudança.
                cs.execute();

            } catch (SQLException se) {
                con.rollback();
                con.close();
                return false;
            }
            con.commit();
            con.close();
            System.out.println("ativ pess atualizado...");
            return true;
        } catch (Exception e) {
            con.rollback();
            con.close();
            return false;
        }
    }

    //Atualizar campo aguardando_aceitacao existente.
    public boolean atualizarAguardandoAceitacao(EntAtivacaoPessoa ativacaoPessoa) throws SQLException, InstantiationException, HeadlessException, IllegalAccessException, ValidarException
    {
        //Criar query SQL.
        String sql;
        sql = "update ativacao_pessoa set aguardando_aceitacao=? where id_pessoa=?";

        //Conectar ao banco.
        Connection con = GUIConexao.conectar();
        con.setAutoCommit(false);
        try {
            try {

                //Preparar chamada.
                CallableStatement cs = con.prepareCall(sql);
                cs.setBoolean(1, ativacaoPessoa.getAguardandoAceitacao());
                cs.setInt(2, ativacaoPessoa.getIdPessoa());

                //Submeter mudança.
                cs.execute();

            } catch (SQLException se) {
                con.rollback();
                con.close();
                return false;
            }
            con.commit();
            con.close();
            System.out.println("ativ pess atualizado...");
            return true;
        } catch (Exception e) {
            con.rollback();
            con.close();
            return false;
        }
    }

    //Buscar.
    public ArrayList <EntAtivacaoPessoa> buscar(int idPessoa) throws SQLException, InstantiationException, HeadlessException, IllegalAccessException, ValidarException, Exception
    {
        //Variaveis.
        int numeroLinhas;                                                                       //Numero de linhas retornadas.
        ArrayList <EntAtivacaoPessoa> ativacaoPessoaAL = new ArrayList <EntAtivacaoPessoa>();   //Array list para retornar.

        //Criar query SQL.
        String sql = "SELECT * FROM ativacao_pessoa WHERE id_pessoa = ?";

        //Conectar ao banco.
        PreparedStatement s = null;
        try {

            //Preparar chamada.
            s = GUIConexao.conectar().prepareStatement(sql);
            s.setInt(1, idPessoa);

            //Executar query.
            ResultSet r = s.executeQuery();

            //Iniciar array list.
            numeroLinhas = 0;
            if(r.next() != false)
            {
                //Passar valores para referencia.
                ativacaoPessoaAL.add(new EntAtivacaoPessoa());
                ativacaoPessoaAL.get(numeroLinhas).setIdPessoa(r.getInt(1));
                ativacaoPessoaAL.get(numeroLinhas).setFuncao(r.getInt(2));
                ativacaoPessoaAL.get(numeroLinhas).setCurso(r.getInt(3));
                ativacaoPessoaAL.get(numeroLinhas).setPolo(r.getInt(4));
                ativacaoPessoaAL.get(numeroLinhas).setAguardandoAceitacao(r.getBoolean(5));

                //Numero linhas.
                numeroLinhas += 1;

                while(r.next() != false)
                {
                    //Passar valores para referencia.
                    ativacaoPessoaAL.add(new EntAtivacaoPessoa());
                    ativacaoPessoaAL.get(numeroLinhas).setIdPessoa(r.getInt(1));
                    ativacaoPessoaAL.get(numeroLinhas).setFuncao(r.getInt(2));
                    ativacaoPessoaAL.get(numeroLinhas).setCurso(r.getInt(3));
                    ativacaoPessoaAL.get(numeroLinhas).setPolo(r.getInt(4));
                    ativacaoPessoaAL.get(numeroLinhas).setAguardandoAceitacao(r.getBoolean(5));

                    //Numero linhas.
                    numeroLinhas += 1;
                }
            }

            //Caso nao haja linhas adicionadas.
            if(numeroLinhas == 0)
            {
                return null;
            }
            else
            {
                return ativacaoPessoaAL;
            }
        } catch (SQLException se) {
            throw se;
        } catch (Exception e) {
            throw e;
        } finally {
            s.close();
        }
    }*/
}
