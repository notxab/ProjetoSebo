package Pck_View;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import Pck_Control.LivroControl;
import Pck_Model.LivroModel;

public class LivroView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;
    private int idEmEdicao = 0;

    JLabel jl_titulo, jl_autor, jl_genero, jl_preco, jl_status, jl_prateleira;
    JTextField jt_titulo, jt_autor, jt_genero, jt_preco, jt_status, jt_prateleira;
    JButton jb_salvar, jb_deletar;
    JTable tabela;
    DefaultTableModel modelo;
    LivroControl oLivroControl = new LivroControl();
    private static LivroView instanciaUnica;

    private LivroView() {
        setTitle("Sistema de Sebo - Livros");
        setBounds(100, 100, 700, 600);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(null);

        jl_titulo = new JLabel("Título:"); jl_titulo.setBounds(20, 20, 100, 20);
        jt_titulo = new JTextField(); jt_titulo.setBounds(130, 20, 200, 20);
        jl_autor = new JLabel("Autor:"); jl_autor.setBounds(20, 50, 100, 20);
        jt_autor = new JTextField(); jt_autor.setBounds(130, 50, 200, 20);
        jl_genero = new JLabel("Gênero:"); jl_genero.setBounds(20, 80, 100, 20);
        jt_genero = new JTextField(); jt_genero.setBounds(130, 80, 200, 20);
        jl_preco = new JLabel("Preço:"); jl_preco.setBounds(20, 110, 100, 20);
        jt_preco = new JTextField(); jt_preco.setBounds(130, 110, 200, 20);
        jl_status = new JLabel("Status:"); jl_status.setBounds(20, 140, 100, 20);
        jt_status = new JTextField(); jt_status.setBounds(130, 140, 200, 20);
        jl_prateleira = new JLabel("ID Prateleira:"); jl_prateleira.setBounds(20, 170, 100, 20);
        jt_prateleira = new JTextField(); jt_prateleira.setBounds(130, 170, 200, 20);

        jb_salvar = new JButton("Cadastrar"); jb_salvar.setBounds(50, 230, 140, 30); jb_salvar.addActionListener(this);
        jb_deletar = new JButton("Deletar"); jb_deletar.setBounds(200, 230, 140, 30); jb_deletar.addActionListener(this);

        modelo = new DefaultTableModel(new Object[]{"ID", "Título", "Autor", "Preço"}, 0);
        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 280, 640, 250);


        tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                int linhaSelecionada = tabela.getSelectedRow();
                if (linhaSelecionada != -1) {
                    int id = (int) tabela.getValueAt(linhaSelecionada, 0);
                    String titulo = (String) tabela.getValueAt(linhaSelecionada, 1);
                    String autor = (String) tabela.getValueAt(linhaSelecionada, 2);
                    double preco = (double) tabela.getValueAt(linhaSelecionada, 3);

                    prepararParaEdicao(id, titulo, autor, "", preco, "", 0);
                }
            }
        });

        add(jl_titulo); add(jt_titulo); add(jl_autor); add(jt_autor); add(jl_genero); add(jt_genero);
        add(jl_preco); add(jt_preco); add(jl_status); add(jt_status); add(jl_prateleira); add(jt_prateleira);
        add(jb_salvar); add(jb_deletar); add(scroll);

        atualizarTabela();
    }

    public static LivroView getInstancia() {
        if (instanciaUnica == null) instanciaUnica = new LivroView();
        return instanciaUnica;
    }

    public void atualizarTabela() {
        modelo.setRowCount(0);
        for (LivroModel l : oLivroControl.listarLivros()) {
            modelo.addRow(new Object[]{l.getId_livro(), l.getTitulo(), l.getAutor(), l.getPreco()});
        }
    }

    public void prepararParaEdicao(int id, String tit, String aut, String gen, double pre, String sta, int idPrat) {
        this.idEmEdicao = id;
        jb_salvar.setText("Atualizar");
        jt_titulo.setText(tit); jt_autor.setText(aut); jt_genero.setText(gen);
        jt_preco.setText(String.valueOf(pre)); jt_status.setText(sta); jt_prateleira.setText(String.valueOf(idPrat));
    }

    private void limparCampos() {
        jt_titulo.setText(""); jt_autor.setText(""); jt_genero.setText("");
        jt_preco.setText(""); jt_status.setText(""); jt_prateleira.setText("");
        idEmEdicao = 0;
        jb_salvar.setText("Cadastrar");
    }

    @Override
    public void dispose() { super.dispose(); instanciaUnica = null; }





    @Override

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb_salvar) {
            if (idEmEdicao == 0) {
                oLivroControl.inserirLivro(jt_titulo.getText(), jt_autor.getText(), jt_genero.getText(), jt_preco.getText(), jt_status.getText(), jt_prateleira.getText());
            } else {
                oLivroControl.atualizarLivro(idEmEdicao, jt_titulo.getText(), jt_autor.getText(), jt_genero.getText(), Double.parseDouble(jt_preco.getText()), jt_status.getText(), Integer.parseInt(jt_prateleira.getText()));
            }
            limparCampos();
            atualizarTabela();
        } else if (e.getSource() == jb_deletar) {
            if (idEmEdicao != 0) {
                oLivroControl.removerLivro(idEmEdicao);
                limparCampos();
                atualizarTabela();
            }
        }
    }
}