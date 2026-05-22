package Pck_View;

import Pck_Control.PrateleiraControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class MenuView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    JLabel jl_tituloMenu;
    JButton jb_menuLivro, jb_menuPrateleira, jb_menuVenda;

    public MenuView() {

        setTitle("Sistema de Sebo - Menu");
        setBounds(100, 100, 480, 480);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);


        jl_tituloMenu = new JLabel("Controle de Acervo", SwingConstants.CENTER);
        jl_tituloMenu.setBounds(20, 30, 340, 30);
        jl_tituloMenu.setFont(jl_tituloMenu.getFont().deriveFont(16.0f));

        jb_menuLivro = new JButton("Gerenciar Livros");
        jb_menuLivro.setBounds(100, 100, 180, 35);
        jb_menuLivro.addActionListener(this);

        jb_menuPrateleira = new JButton("Gerenciar Prateleiras");
        jb_menuPrateleira.setBounds(100, 150, 180, 35);
        jb_menuPrateleira.addActionListener(this);

        jb_menuVenda = new JButton("Gerenciar Vendas");
        jb_menuVenda.setBounds(100, 200, 180, 35);
        jb_menuVenda.addActionListener(this);

        getContentPane().add(jl_tituloMenu);
        getContentPane().add(jb_menuLivro);
        getContentPane().add(jb_menuPrateleira);
        getContentPane().add(jb_menuVenda);


    }

    @Override
    public void actionPerformed(ActionEvent e) {

        if (e.getSource() == jb_menuLivro) {
            LivroView telaLivro = LivroView.getInstancia();
            telaLivro.setVisible(true);
            telaLivro.toFront();
        }

        if (e.getSource() == jb_menuPrateleira) {
            PrateleiraView telaPrateleira = PrateleiraView.getInstancia();
            telaPrateleira.setVisible(true);
            telaPrateleira.toFront();
        }

        if (e.getSource() == jb_menuVenda) {
            VendaView telaVenda = VendaView.getInstancia();
            telaVenda.setVisible(true);
            telaVenda.toFront();
        }


    }


    public static void main(String[] args) {
        new MenuView().setVisible(true);
    }
}