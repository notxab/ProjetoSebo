package Pck_DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoMySql {

    /* URL de conexão com o banco de dados.
       jdbc:mysql indica que será usado o driver MySQL.
       localhost indica que o banco está na própria máquina.
       3306 é a porta padrão do MySQL.
       bd_mvc_simplificado é o nome do banco utilizado no exemplo. */
    private static final String URL = "jdbc:mysql://localhost:3306/bd_mvc_simplificado";

    /* Usuário utilizado para acessar o MySQL. */
    private static final String USUARIO = "root";

    /* Senha utilizada para acessar o MySQL. */
    private static final String SENHA = "P@$$word";

    /* Método responsável por abrir a conexão. Ele retorna um objeto Connection.
       Esse objeto representa a conexão ativa com o banco de dados. */
    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }

    /* Método responsável por fechar a conexão.
       Antes de fechar, verificamos se a conexão não está nula.
       Isso evita tentativa de fechamento de uma conexão inexistente. */
    public static void fechar(Connection conexao) {
        try {
            if (conexao != null) {
                conexao.close();
            }
        } catch (SQLException erro) {
            erro.printStackTrace();
        }
    }
}