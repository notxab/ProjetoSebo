package Pck_Persistencia;

import Pck_DAO.ConexaoMySql;
import Pck_Model.PrateleiraModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class PrateleiraPersistencia {
    public void inserirPrateleira(PrateleiraModel oPrateleiraModel) {
        Connection conexao = null;
        CallableStatement chamada = null;

        try {
            conexao = ConexaoMySql.conectar();
            // A procedure *insere_livro tem 5 parâmetros de entrada
            chamada = conexao.prepareCall("{call insere_prateleira(?, ?, ?)}");

            chamada.setInt(1, oPrateleiraModel.getNumero());
            chamada.setString(2, oPrateleiraModel.getTipo());
            chamada.setString(3, oPrateleiraModel.getLugar());

            chamada.execute();
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            try { if (chamada != null) chamada.close(); } catch (SQLException e) { e.printStackTrace(); }
            ConexaoMySql.fechar(conexao);
        }
    }
}