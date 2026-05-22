package Pck_Model;

public class VendaModel {
    private int id_venda;
    private String codigo_recibo;
    private String data_venda;
    private double valor_total;
    private String forma_pagamento;
    private int id_usuario;

    // Getters e Setters
    public int getId_venda() { return id_venda; }
    public void setId_venda(int id_venda) { this.id_venda = id_venda; }

    public String getCodigo_recibo() { return codigo_recibo; }
    public void setCodigo_recibo(String codigo_recibo) { this.codigo_recibo = codigo_recibo; }

    public String getData_venda() { return data_venda; }
    public void setData_venda(String data_venda) { this.data_venda = data_venda; }

    public double getValor_total() { return valor_total; }
    public void setValor_total(double valor_total) { this.valor_total = valor_total; }

    public String getForma_pagamento() { return forma_pagamento; }
    public void setForma_pagamento(String forma_pagamento) { this.forma_pagamento = forma_pagamento; }

    public int getId_usuario() { return id_usuario; }
    public void setId_usuario(int id_usuario) { this.id_usuario = id_usuario; }
}