package Pck_Persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import Pck_DAO.ConexaoMySql;
import Pck_Model.ExemploModel;

public class ExemploPersistencia {

    /* Método responsável por inserir um registro no banco.
       Ele recebe um objeto ExemploModel.
       O objeto contém os dados que vieram da tela e passaram pela Control. */
    public void inserirExemplo(ExemploModel oExemploModel) {
        /* A conexão começa como nula. Ela será aberta dentro do try. */
        Connection conexao = null;
        /* CallableStatement é usado para chamar procedures no banco. */
        CallableStatement chamada = null;

        try {
            /* Abre conexão com o banco usando a classe ConexaoMySql. */
            conexao = ConexaoMySql.conectar();

            /* Prepara a chamada da procedure.
               A procedure PROC_INSEXEMPLO recebe 1 parâmetro.
               Por isso aparece uma interrogação entre parênteses. */
            chamada = conexao.prepareCall("call proc_insexemplo(?)");

            /* Define o valor do primeiro parâmetro da procedure.
               O valor vem do objeto Model. */
            chamada.setString(1, oExemploModel.getA01_nome());

            /* Executa a procedure no banco de dados. */
            chamada.execute();

        } catch (SQLException erro) {
            /* Mostra detalhes do erro no console.
               Isso ajuda durante a fase de estudo e depuração. */
            erro.printStackTrace();
        } finally {
            /* Fecha o CallableStatement se ele foi criado. */
            try {
                if (chamada != null) {
                    chamada.close();
                }
            } catch (SQLException erro) {
                erro.printStackTrace();
            }
            /* Fecha a conexão usando a classe ConexaoMySql. */
            ConexaoMySql.fechar(conexao);
        }
    }
}