package Pck_View;

import javax.swing.*;
import java.awt.event.*;
import Pck_Control.LivroControl;

public class LivroView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    // 5 Labels para identificar os campos
    JLabel jl_titulo, jl_autor, jl_genero, jl_preco, jl_status, jl_prateleira;

    // 5 TextFields para o usuário digitar
    JTextField jt_titulo, jt_autor, jt_genero, jt_preco, jt_status, jt_prateleira;

    JButton jb_inserir;
    LivroControl oLivroControl = new LivroControl();

    private static LivroView instanciaUnica;

    public LivroView() {
        setTitle("Sistema de Sebo - Cadastro de Livros");
        setBounds(100, 100, 900, 480);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // 1. Título
        jl_titulo = new JLabel("Título:");
        jl_titulo.setBounds(20, 20, 100, 20);
        jt_titulo = new JTextField();
        jt_titulo.setBounds(130, 20, 200, 20);

        // 2. Autor
        jl_autor = new JLabel("Autor:");
        jl_autor.setBounds(20, 50, 100, 20);
        jt_autor = new JTextField();
        jt_autor.setBounds(130, 50, 200, 20);

        // 3. Gênero
        jl_genero = new JLabel("Gênero:");
        jl_genero.setBounds(20, 80, 100, 20);
        jt_genero = new JTextField();
        jt_genero.setBounds(130, 80, 200, 20);

        // 4. Preço
        jl_preco = new JLabel("Preço (0.00):");
        jl_preco.setBounds(20, 110, 100, 20);
        jt_preco = new JTextField();
        jt_preco.setBounds(130, 110, 200, 20);

        // 5. status
        jl_status = new JLabel("Status (disponivel, vendido, etc):");
        jl_status.setBounds(20, 140, 100, 20);
        jt_status = new JTextField();
        jt_status.setBounds(130, 140, 200, 20);

        // 6. ID Prateleira
        jl_prateleira = new JLabel("ID Prateleira:");
        jl_prateleira.setBounds(20, 170, 100, 20);
        jt_prateleira = new JTextField();
        jt_prateleira.setBounds(130, 170, 200, 20);

        // Botão de Inserir
        jb_inserir = new JButton("Cadastrar Livro");
        jb_inserir.setBounds(130, 200, 150, 30);
        jb_inserir.addActionListener(this);

        // Adicionando tudo ao painel
        getContentPane().add(jl_titulo);     getContentPane().add(jt_titulo);
        getContentPane().add(jl_autor);      getContentPane().add(jt_autor);
        getContentPane().add(jl_genero);     getContentPane().add(jt_genero);
        getContentPane().add(jl_preco);      getContentPane().add(jt_preco);
        getContentPane().add(jl_status);      getContentPane().add(jt_status);
        getContentPane().add(jl_prateleira); getContentPane().add(jt_prateleira);
        getContentPane().add(jb_inserir);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb_inserir) {

            // Verificação básica: se os campos principais estão vazios
            if (jt_titulo.getText().trim().isEmpty() || jt_preco.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha o Título e o Preço.");
                return;
            }

            try {
                /* CHAMADA DA CONTROL COM OS 6* ARGUMENTOS:
                   1. Título
                   2. Autor
                   3. Gênero
                   4. Preço (String)
                   5. Status (String) >> adicionado por ultimo, hotfix
                   6. ID Prateleira (String)
                */
                oLivroControl.inserirLivro(
                        jt_titulo.getText(),
                        jt_autor.getText(),
                        jt_genero.getText(),
                        jt_preco.getText(),
                        jt_status.getText(),
                        jt_prateleira.getText()
                );

                JOptionPane.showMessageDialog(null, "Livro cadastrado com sucesso!");
                limparCampos();

            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "Erro ao processar dados: " + erro.getMessage());
            }
        }
    }

    public static LivroView getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new LivroView();
        }
        return instanciaUnica;
    }

    public void dispose() {
        super.dispose();
        instanciaUnica = null;
    }



    private void limparCampos() {
        jt_titulo.setText("");
        jt_autor.setText("");
        jt_genero.setText("");
        jt_preco.setText("");
        jt_status.setText("");
        jt_prateleira.setText("");
    }

    public static void main(String[] args) {
        new LivroView().setVisible(true);
    }
}