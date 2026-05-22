package Pck_Persistencia;

import Pck_DAO.ConexaoMySql;
import Pck_Model.VendaModel;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;

public class VendaPersistencia {
    public void inserirVenda(VendaModel oVendaModel) {
        Connection conexao = null;
        CallableStatement chamada = null;

        try {
            conexao = ConexaoMySql.conectar();

            // A procedure *insere_venda* espera 5 parâmetros na ordem do seu Script 2:
            // (n_valor, n_codigo, n_formapag, n_data, n_id_usuario)
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
}