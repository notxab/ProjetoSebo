package Pck_View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import Pck_Control.VendaControl;
import Pck_Model.VendaModel;
import Pck_Model.Sessao;

public class VendaView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private int idEmEdicao = 0;

    JLabel jl_valor, jl_formapag;
    JTextField jt_valor;
    JComboBox<String> jc_formapag;

    JButton jb_salvar, jb_deletar;
    JTable tabela;
    DefaultTableModel modelo;
    VendaControl oVendaControl = new VendaControl();
    private static VendaView instanciaUnica;

    private VendaView() {
        setTitle("Sebo - Registro de Vendas");
        setBounds(100, 100, 500, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        jl_valor = new JLabel("Valor:");
        jl_valor.setBounds(20, 20, 100, 20);
        jt_valor = new JTextField();
        jt_valor.setBounds(130, 20, 230, 20);

        jl_formapag = new JLabel("Pagamento:");
        jl_formapag.setBounds(20, 50, 100, 20);
        jc_formapag = new JComboBox<>(new String[]{"Dinheiro", "Cartão", "Pix"});
        jc_formapag.setBounds(130, 50, 230, 20);

        jb_salvar = new JButton("Salvar");
        jb_salvar.setBounds(50, 110, 140, 30);
        jb_salvar.addActionListener(this);

        jb_deletar = new JButton("Deletar");
        jb_deletar.setBounds(200, 110, 140, 30);
        jb_deletar.addActionListener(this);

        modelo = new DefaultTableModel(new Object[]{"ID", "Código", "Valor", "Data", "Pagamento", "ID Usuário"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 170, 440, 360);

        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int linha = tabela.getSelectedRow();
                if (linha != -1) {
                    prepararParaEdicao(
                            (int) modelo.getValueAt(linha, 0),
                            (double) modelo.getValueAt(linha, 2)
                    );
                }
            }
        });

        add(jl_valor); add(jt_valor);
        add(jl_formapag); add(jc_formapag);
        add(jb_salvar); add(jb_deletar); add(scroll);

        atualizarTabela();
    }

    public static VendaView getInstancia() {
        if (instanciaUnica == null) instanciaUnica = new VendaView();
        return instanciaUnica;
    }

    public void atualizarTabela() {
        modelo.setRowCount(0);
        for (VendaModel v : oVendaControl.listarVendas()) {
            modelo.addRow(new Object[]{v.getId_venda(), v.getCodigo_recibo(), v.getValor_total(), v.getData_venda(), v.getForma_pagamento(), v.getId_usuario()});
        }
    }

    public void prepararParaEdicao(int id, double val) {
        this.idEmEdicao = id;
        jb_salvar.setText("Atualizar");
        jt_valor.setText(String.valueOf(val));
    }

    private void limparCampos() {
        jt_valor.setText("");
        idEmEdicao = 0;
        jb_salvar.setText("Salvar");
    }

    @Override
    public void dispose() { super.dispose(); instanciaUnica = null; }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb_salvar) {
            String codigoAuto = "VEN-" + System.currentTimeMillis();
            String dataAuto = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));

            int idUsuarioLogado = Sessao.usuarioLogado.getIdUsuario();

            if (idEmEdicao == 0) {
                oVendaControl.inserirVenda(Double.parseDouble(jt_valor.getText()), codigoAuto, (String)jc_formapag.getSelectedItem(), dataAuto, idUsuarioLogado, 1);
            } else {
                oVendaControl.atualizarVenda(idEmEdicao, Double.parseDouble(jt_valor.getText()), "N/A", (String)jc_formapag.getSelectedItem(), "N/A", idUsuarioLogado);
            }
            limparCampos();
            atualizarTabela();
        } else if (e.getSource() == jb_deletar) {
            if (idEmEdicao != 0) {
                oVendaControl.removerVenda(idEmEdicao);
                limparCampos();
                atualizarTabela();
            }
        }
    }
}