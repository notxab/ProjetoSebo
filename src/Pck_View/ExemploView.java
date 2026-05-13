package Pck_View;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import Pck_Control.ExemploControl;

public class ExemploView extends JFrame implements ActionListener {

    private static final long serialVersionUID = 1L;

    /* JLabel é usado para exibir um texto fixo na tela. */
    JLabel jl_texto;

    /* JTextField é usado para permitir digitação. */
    JTextField jt_texto;

    /* JButton é usado para criar um botão clicável. */
    JButton jb_inserir;

    /* Objeto da camada Control. A View chama a Control, e a Control organiza o envio dos dados. */
    ExemploControl oExemploControl = new ExemploControl();

    /* Construtor da tela. Ele monta os componentes visuais. */
    public ExemploView() {
        setTitle("Exemplo MVC + DAO + MySQL");
        setBounds(100, 100, 430, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        jl_texto = new JLabel("Texto");
        jl_texto.setBounds(20, 20, 100, 20);

        jt_texto = new JTextField("");
        jt_texto.setBounds(130, 20, 180, 20);

        jb_inserir = new JButton("Inserir");
        jb_inserir.setBounds(20, 80, 100, 25);

        getContentPane().setLayout(null);
        getContentPane().add(jl_texto);
        getContentPane().add(jt_texto);
        getContentPane().add(jb_inserir);

        jb_inserir.addActionListener(this);
    }

    /* Este método é executado quando ocorre uma ação na tela.
       Neste exemplo, a ação principal é clicar no botão Inserir. */
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb_inserir) {
            String textoDigitado = jt_texto.getText();

            if (textoDigitado.trim().equals("")) {
                JOptionPane.showMessageDialog(null, "Digite um texto antes de inserir.");
            } else {
                oExemploControl.inserirExemplo(textoDigitado);
                JOptionPane.showMessageDialog(null, "Registro enviado para o banco.");
                jt_texto.setText("");
            }
        }
    }

    /* Método principal. Ele inicia a aplicação. */
    public static void main(String[] args) {
        ExemploView obj_interface = new ExemploView();
        obj_interface.setVisible(true);
    }
}