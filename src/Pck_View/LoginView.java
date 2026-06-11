package Pck_View;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    JLabel jl_tituloMenu, jl_labelUsuario, jl_labelSenha;
    JTextField jt_usuario;
    JPasswordField jp_senha;
    JButton jb_loginButton;

    public LoginView() {
        setTitle("Sistema de Sebo - Login");
        setSize(400, 320);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(null);

        jl_tituloMenu = new JLabel("Controle de Acervo - Login", SwingConstants.CENTER);
        jl_tituloMenu.setBounds(20, 20, 340, 30);
        jl_tituloMenu.setFont(jl_tituloMenu.getFont().deriveFont(18.0f));

        jl_labelUsuario = new JLabel("Usuário:");
        jl_labelUsuario.setBounds(50, 75, 300, 20);

        jt_usuario = new JTextField();
        jt_usuario.setBounds(50, 100, 280, 30);

        jl_labelSenha = new JLabel("Senha:");
        jl_labelSenha.setBounds(50, 140, 300, 20);

        jp_senha = new JPasswordField();
        jp_senha.setBounds(50, 165, 280, 30);

        jb_loginButton = new JButton("Entrar");
        jb_loginButton.setBounds(130, 220, 120, 35);
        jb_loginButton.addActionListener(this);

        getContentPane().add(jl_tituloMenu);
        getContentPane().add(jl_labelUsuario);
        getContentPane().add(jt_usuario);
        getContentPane().add(jl_labelSenha);
        getContentPane().add(jp_senha);
        getContentPane().add(jb_loginButton);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb_loginButton) {
            String usuarioDigitado = jt_usuario.getText();
            String senhaDigitada = new String(jp_senha.getPassword());

            Pck_Control.LoginControl loginControl = new Pck_Control.LoginControl();

            if (loginControl.efetuarLogin(usuarioDigitado, senhaDigitada)) {
                JOptionPane.showMessageDialog(this, "Login efetuado com sucesso!");
                this.dispose();
                MenuView telaMenu = new MenuView();
                telaMenu.setVisible(true);
            } else {
                JOptionPane.showMessageDialog(this, "Usuário ou senha incorretos.", "Erro de Autenticação", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LoginView().setVisible(true);
        });
    }
}