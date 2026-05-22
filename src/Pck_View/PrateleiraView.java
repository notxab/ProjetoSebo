package Pck_View;

import Pck_Control.PrateleiraControl;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PrateleiraView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    // 3 Labels para identificar os campos
    JLabel jl_prateleira, jl_numero, jl_tipo, jl_lugar;

    // 3 TextFields para o usuário digitar
    JTextField jt_prateleira, jt_numero, jt_tipo, jt_lugar;

    JButton jb_inserir;
    PrateleiraControl oPrateleiraControl = new PrateleiraControl();

    private static PrateleiraView instanciaUnica;

    public PrateleiraView() {
        setTitle("Sistema de Sebo - Cadastro de Prateleiras");
        setBounds(100, 100, 900, 480);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        // 1. Numero
        jl_numero = new JLabel("Numero:");
        jl_numero.setBounds(20, 20, 100, 20);
        jt_numero = new JTextField();
        jt_numero.setBounds(130, 20, 200, 20);

        // 2. Tipo
        jl_tipo = new JLabel("Tipo:");
        jl_tipo.setBounds(20, 50, 100, 20);
        jt_tipo = new JTextField();
        jt_tipo.setBounds(130, 50, 200, 20);

        // 3. Lugar
        jl_lugar = new JLabel("Lugar:");
        jl_lugar.setBounds(20, 80, 100, 20);
        jt_lugar = new JTextField();
        jt_lugar.setBounds(130, 80, 200, 20);

        // Botão de Inserir
        jb_inserir = new JButton("Cadastrar Prateleira");
        jb_inserir.setBounds(130, 200, 150, 30);
        jb_inserir.addActionListener(this);

        // Adicionando tudo ao painel
        getContentPane().add(jl_numero);     getContentPane().add(jt_numero);
        getContentPane().add(jl_tipo);      getContentPane().add(jt_tipo);
        getContentPane().add(jl_lugar);     getContentPane().add(jt_lugar);
        getContentPane().add(jb_inserir);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb_inserir) {

            // Verificação básica: se os campos principais estão vazios
            if (jt_numero.getText().trim().isEmpty() || jt_tipo.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(null, "Por favor, preencha o Numero e o Tipo.");
                return;
            }

            try {
                /* CHAMADA DA CONTROL COM OS 3 ARGUMENTOS:
                   1. Numero
                   2. Tipo
                   3. Lugar
                */
                oPrateleiraControl.inserirPrateleira(
                        Integer.parseInt(jt_numero.getText()),
                        jt_tipo.getText(),
                        jt_lugar.getText()
                );

                JOptionPane.showMessageDialog(null, "Prateleira cadastrada com sucesso!");
                limparCampos();

            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "Erro ao processar dados: " + erro.getMessage());
            }
        }
    }


    public static PrateleiraView getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new PrateleiraView();
        }
        return instanciaUnica;
    }

    public void dispose() {
        super.dispose();
        instanciaUnica = null;
    }




    private void limparCampos() {
        jt_numero.setText("");
        jt_tipo.setText("");
        jt_lugar.setText("");
    }

    public static void main(String[] args) {
        new PrateleiraView().setVisible(true);
    }
}