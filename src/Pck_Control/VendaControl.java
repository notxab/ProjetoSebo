package Pck_Control;

import Pck_Model.VendaModel;
import Pck_Persistencia.VendaPersistencia;
import java.util.ArrayList;

public class VendaControl {

    private VendaPersistencia oVendaPersistencia = new VendaPersistencia();

    public ArrayList<VendaModel> listarVendas() {
        return oVendaPersistencia.listarVendas();
    }

    public void inserirVenda(double valorTotal, String codigoRecibo, String formaPagamento, String dataVenda, int idUsuario) {
        VendaModel oVendaModel = new VendaModel();

        oVendaModel.setValor_total(valorTotal);
        oVendaModel.setCodigo_recibo(codigoRecibo);
        oVendaModel.setForma_pagamento(formaPagamento);
        oVendaModel.setData_venda(dataVenda);
        oVendaModel.setId_usuario(idUsuario);

        oVendaPersistencia.inserirVenda(oVendaModel);
    }

    public void atualizarVenda(int idVenda, double valorTotal, String codigoRecibo, String formaPagamento, String dataVenda, int idUsuario) {
        VendaModel oVendaModel = new VendaModel();

        oVendaModel.setId_venda(idVenda);
        oVendaModel.setValor_total(valorTotal);
        oVendaModel.setCodigo_recibo(codigoRecibo);
        oVendaModel.setForma_pagamento(formaPagamento);
        oVendaModel.setData_venda(dataVenda);
        oVendaModel.setId_usuario(idUsuario);

        oVendaPersistencia.atualizarVenda(oVendaModel);
    }

    public void removerVenda(int idVenda) {
        oVendaPersistencia.deletarVenda(idVenda);
    }
}