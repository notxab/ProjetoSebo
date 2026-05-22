package Pck_View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import Pck_Control.PrateleiraControl;
import Pck_Model.PrateleiraModel;

public class PrateleiraView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private int idEmEdicao = 0;
    JLabel jl_numero, jl_tipo, jl_lugar;
    JTextField jt_numero, jt_tipo, jt_lugar;
    JButton jb_salvar, jb_deletar;
    JTable tabela;
    DefaultTableModel modelo;
    PrateleiraControl oPrateleiraControl = new PrateleiraControl();
    private static PrateleiraView instanciaUnica;

    private PrateleiraView() {
        setTitle("Sebo - Prateleiras");
        setBounds(100, 100, 450, 500);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        jl_numero = new JLabel("Número:"); jl_numero.setBounds(20, 20, 100, 20);
        jt_numero = new JTextField(); jt_numero.setBounds(130, 20, 200, 20);
        jl_tipo = new JLabel("Tipo:"); jl_tipo.setBounds(20, 50, 100, 20);
        jt_tipo = new JTextField(); jt_tipo.setBounds(130, 50, 200, 20);
        jl_lugar = new JLabel("Lugar:"); jl_lugar.setBounds(20, 80, 100, 20);
        jt_lugar = new JTextField(); jt_lugar.setBounds(130, 80, 200, 20);

        jb_salvar = new JButton("Cadastrar"); jb_salvar.setBounds(50, 150, 140, 30); jb_salvar.addActionListener(this);
        jb_deletar = new JButton("Deletar"); jb_deletar.setBounds(200, 150, 140, 30); jb_deletar.addActionListener(this);

        modelo = new DefaultTableModel(new Object[]{"ID", "Número", "Tipo", "Lugar"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 200, 400, 250);

        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int linha = tabela.getSelectedRow();
                if (linha != -1) {
                    prepararParaEdicao(
                            (int) modelo.getValueAt(linha, 0),
                            (int) modelo.getValueAt(linha, 1),
                            (String) modelo.getValueAt(linha, 2),
                            (String) modelo.getValueAt(linha, 3)
                    );
                }
            }
        });


        add(jl_numero); add(jt_numero); add(jl_tipo); add(jt_tipo); add(jl_lugar); add(jt_lugar);
        add(jb_salvar); add(jb_deletar); add(scroll);

        atualizarTabela();
    }

    public static PrateleiraView getInstancia() {
        if (instanciaUnica == null) instanciaUnica = new PrateleiraView();
        return instanciaUnica;
    }

    public void atualizarTabela() {
        modelo.setRowCount(0);
        for (PrateleiraModel p : oPrateleiraControl.listarPrateleiras()) {
            modelo.addRow(new Object[]{p.getId_prateleira(), p.getNumero(), p.getTipo(), p.getLugar()});
        }
    }

    public void prepararParaEdicao(int id, int num, String tip, String lug) {
        this.idEmEdicao = id;
        jb_salvar.setText("Atualizar");
        jt_numero.setText(String.valueOf(num)); jt_tipo.setText(tip); jt_lugar.setText(lug);
    }

    private void limparCampos() {
        jt_numero.setText(""); jt_tipo.setText(""); jt_lugar.setText("");
        idEmEdicao = 0;
        jb_salvar.setText("Cadastrar");
    }

    @Override
    public void dispose() { super.dispose(); instanciaUnica = null; }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb_salvar) {
            if (idEmEdicao == 0) {
                oPrateleiraControl.inserirPrateleira(Integer.parseInt(jt_numero.getText()), jt_tipo.getText(), jt_lugar.getText());
            } else {
                oPrateleiraControl.atualizarPrateleira(idEmEdicao, Integer.parseInt(jt_numero.getText()), jt_tipo.getText(), jt_lugar.getText());
            }
            limparCampos();
            atualizarTabela();
        } else if (e.getSource() == jb_deletar) {
            if (idEmEdicao != 0) {
                oPrateleiraControl.removerPrateleira(idEmEdicao);
                limparCampos();
                atualizarTabela();
            }
        }
    }
}