package Pck_Persistencia;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import Pck_DAO.ConexaoMySql;
import Pck_Model.LivroModel;

public class LivroPersistencia {
    public void inserirLivro(LivroModel oLivroModel) {
        Connection conexao = null;
        CallableStatement chamada = null;

        try {
            conexao = ConexaoMySql.conectar();
            // A procedure sp_cadastrar_livro tem 5 parâmetros de entrada
            chamada = conexao.prepareCall("{call sp_cadastrar_livro(?, ?, ?, ?, ?)}");

            chamada.setString(1, oLivroModel.getTitulo());
            chamada.setString(2, oLivroModel.getAutor());
            chamada.setString(3, oLivroModel.getGenero());
            chamada.setDouble(4, oLivroModel.getPreco());
            chamada.setInt(5, oLivroModel.getId_prateleira());

            chamada.execute();
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            try { if (chamada != null) chamada.close(); } catch (SQLException e) { e.printStackTrace(); }
            ConexaoMySql.fechar(conexao);
        }
    }
}