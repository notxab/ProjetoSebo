package Pck_Persistencia;

import java.sql.*;
import java.util.ArrayList;
import Pck_DAO.ConexaoMySql;
import Pck_Model.LivroModel;

public class LivroPersistencia {

    public void inserirLivro(LivroModel oLivroModel) {
        Connection conexao = null;
        CallableStatement chamada = null;

        try {
            conexao = ConexaoMySql.conectar();
            chamada = conexao.prepareCall("{call insere_livro(?, ?, ?, ?, ?, ?)}");

            chamada.setString(1, oLivroModel.getTitulo());
            chamada.setString(2, oLivroModel.getAutor());
            chamada.setString(3, oLivroModel.getGenero());
            chamada.setDouble(4, oLivroModel.getPreco());
            chamada.setString(5, oLivroModel.getStatus());
            chamada.setInt(6, oLivroModel.getId_prateleira());

            chamada.execute();
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            try { if (chamada != null) chamada.close(); } catch (SQLException e) { e.printStackTrace(); }
            ConexaoMySql.fechar(conexao);
        }
    }

    public void atualizarLivro(LivroModel oLivroModel) {
        Connection conexao = null;
        CallableStatement chamada = null;

        try {
            conexao = ConexaoMySql.conectar();
            chamada = conexao.prepareCall("{call up_livro(?, ?, ?, ?, ?, ?, ?)}");

            chamada.setInt(1, oLivroModel.getId_livro());
            chamada.setString(2, oLivroModel.getTitulo());
            chamada.setString(3, oLivroModel.getAutor());
            chamada.setString(4, oLivroModel.getGenero());
            chamada.setDouble(5, oLivroModel.getPreco());
            chamada.setString(6, oLivroModel.getStatus());
            chamada.setInt(7, oLivroModel.getId_prateleira());

            chamada.execute();
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            try { if (chamada != null) chamada.close(); } catch (SQLException e) { e.printStackTrace(); }
            ConexaoMySql.fechar(conexao);
        }
    }

    public void removerLivro(int id) {
        Connection conexao = null;
        CallableStatement chamada = null;

        try {
            conexao = ConexaoMySql.conectar();
            chamada = conexao.prepareCall("{call delete_livro(?)}");

            chamada.setInt(1, id);

            chamada.execute();
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            try { if (chamada != null) chamada.close(); } catch (SQLException e) { e.printStackTrace(); }
            ConexaoMySql.fechar(conexao);
        }
    }

    public ArrayList<LivroModel> listarLivros() {
        ArrayList<LivroModel> lista = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = ConexaoMySql.conectar();
            ps = conexao.prepareStatement("SELECT * FROM livro");
            rs = ps.executeQuery();

            while (rs.next()) {
                LivroModel l = new LivroModel();
                l.setId_livro(rs.getInt("id_livro"));
                l.setTitulo(rs.getString("titulo"));
                l.setAutor(rs.getString("autor"));
                l.setPreco(rs.getDouble("preco"));
                l.setGenero(rs.getString("genero"));
                l.setStatus(rs.getString("status1"));
                l.setId_prateleira(rs.getInt("id_prateleira"));
                lista.add(l);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            ConexaoMySql.fechar(conexao);
        }
        return lista;
    }
}