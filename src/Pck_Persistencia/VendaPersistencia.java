package Pck_Persistencia;

import Pck_DAO.ConexaoMySql;
import Pck_Model.VendaModel;

import java.sql.*;
import java.util.ArrayList;

public class VendaPersistencia {

    public ArrayList<VendaModel> listarVendas() {
        ArrayList<VendaModel> lista = new ArrayList<>();
        Connection conexao = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conexao = ConexaoMySql.conectar();
            ps = conexao.prepareStatement("SELECT * FROM venda");
            rs = ps.executeQuery();

            while (rs.next()) {
                VendaModel v = new VendaModel();
                v.setId_venda(rs.getInt("id_venda"));
                v.setCodigo_recibo(rs.getString("codigo_recibo"));
                v.setValor_total(rs.getDouble("valor_total"));
                v.setData_venda(rs.getString("data_venda"));
                v.setForma_pagamento(rs.getString("forma_pagamento"));
                v.setId_usuario(rs.getInt("id_usuario"));
                lista.add(v);
            }
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            try { if (rs != null) rs.close(); if (ps != null) ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            ConexaoMySql.fechar(conexao);
        }
        return lista;
    }

    public void inserirVenda(VendaModel oVendaModel) {
        Connection conexao = null;
        CallableStatement chamada = null;

        try {
            conexao = ConexaoMySql.conectar();
            chamada = conexao.prepareCall("{call insere_venda(?, ?, ?, ?, ?)}");

            chamada.setDouble(1, oVendaModel.getValor_total());
            chamada.setString(2, oVendaModel.getCodigo_recibo());
            chamada.setString(3, oVendaModel.getForma_pagamento());
            chamada.setString(4, oVendaModel.getData_venda());
            chamada.setInt(5, oVendaModel.getId_usuario());

            chamada.execute();
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            try { if (chamada != null) chamada.close(); } catch (SQLException e) { e.printStackTrace(); }
            ConexaoMySql.fechar(conexao);
        }
    }

    public void atualizarVenda(VendaModel oVendaModel) {
        Connection conexao = null;
        CallableStatement chamada = null;

        try {
            conexao = ConexaoMySql.conectar();
            chamada = conexao.prepareCall("{call up_venda(?, ?, ?, ?, ?, ?)}");

            chamada.setDouble(1, oVendaModel.getValor_total());
            chamada.setString(2, oVendaModel.getCodigo_recibo());
            chamada.setString(3, oVendaModel.getForma_pagamento());
            chamada.setString(4, oVendaModel.getData_venda());
            chamada.setInt(5, oVendaModel.getId_usuario());
            chamada.setInt(6, oVendaModel.getId_venda());

            chamada.execute();
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            try { if (chamada != null) chamada.close(); } catch (SQLException e) { e.printStackTrace(); }
            ConexaoMySql.fechar(conexao);
        }
    }

    public void deletarVenda(int id_venda) {
        Connection conexao = null;
        CallableStatement chamada = null;

        try {
            conexao = ConexaoMySql.conectar();
            chamada = conexao.prepareCall("{call delete_venda(?)}");
            chamada.setInt(1, id_venda);
            chamada.execute();
        } catch (SQLException erro) {
            erro.printStackTrace();
        } finally {
            try { if (chamada != null) chamada.close(); } catch (SQLException e) { e.printStackTrace(); }
            ConexaoMySql.fechar(conexao);
        }
    }
}