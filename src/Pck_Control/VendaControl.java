package Pck_Control;

import Pck_Model.VendaModel;
import Pck_Persistencia.VendaPersistencia;

public class VendaControl {
    VendaPersistencia oVendaPersistencia = new VendaPersistencia();

    public void inserirVenda(double valorTotal, String codigoRecibo, String formaPagamento, String dataVenda, int idUsuario) {
        VendaModel oVendaModel = new VendaModel();

        oVendaModel.setValor_total(valorTotal);
        oVendaModel.setCodigo_recibo(codigoRecibo);
        oVendaModel.setForma_pagamento(formaPagamento);
        oVendaModel.setData_venda(dataVenda);
        oVendaModel.setId_usuario(idUsuario);

        oVendaPersistencia.inserirVenda(oVendaModel);
    }
}