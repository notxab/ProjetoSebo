package Pck_Persistencia;

import java.sql.*;
import java.util.ArrayList;
import Pck_DAO.ConexaoMySql;
import Pck_Model.PrateleiraModel;

public class PrateleiraPersistencia {

    public ArrayList<PrateleiraModel> listarPrateleiras() {
        ArrayList<PrateleiraModel> lista = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = ConexaoMySql.conectar();
            ps = conexao.prepareStatement("SELECT * FROM prateleira");
            rs = ps.executeQuery();

            while (rs.next()) {
                PrateleiraModel p = new PrateleiraModel();
                p.setId_prateleira(rs.getInt("id_prateleira"));
                p.setNumero(rs.getInt("numero"));
                p.setTipo(rs.getString("tipo"));
                p.setLugar(rs.getString("lugar"));
                lista.add(p);
            }
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            ConexaoMySql.fechar(conexao);
        }
        return lista;
    }

    public void inserirPrateleira(PrateleiraModel oPrateleiraModel) {
        Connection conexao = null;
        CallableStatement chamada = null;

        try {
            conexao = ConexaoMySql.conectar();
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

    public void atualizarPrateleira(PrateleiraModel oPrateleiraModel) {
        Connection conexao = null;
        CallableStatement chamada = null;

        try {
            conexao = ConexaoMySql.conectar();
            chamada = conexao.prepareCall("{call up_prateleira(?, ?, ?, ?)}");

            chamada.setInt(1, oPrateleiraModel.getId_prateleira());
            chamada.setInt(2, oPrateleiraModel.getNumero());
            chamada.setString(3, oPrateleiraModel.getTipo());
            chamada.setString(4, oPrateleiraModel.getLugar());

            chamada.execute();
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            try { if (chamada != null) chamada.close(); } catch (SQLException e) { e.printStackTrace(); }
            ConexaoMySql.fechar(conexao);
        }
    }

    public void removerPrateleira(int id) {
        Connection conexao = null;
        CallableStatement chamada = null;

        try {
            conexao = ConexaoMySql.conectar();
            chamada = conexao.prepareCall("{call delete_prateleira(?)}");

            chamada.setInt(1, id);

            chamada.execute();
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            try { if (chamada != null) chamada.close(); } catch (SQLException e) { e.printStackTrace(); }
            ConexaoMySql.fechar(conexao);
        }
    }
}