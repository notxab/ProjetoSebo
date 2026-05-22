package Pck_View;

import Pck_Control.VendaControl;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

public class VendaView extends JFrame implements ActionListener {
    private static final long serialVersionUID = 1L;

    JLabel jl_valor, jl_codigo, jl_formapag, jl_data, jl_id_usuario;
    JTextField jt_valor, jt_codigo, jt_data, jt_id_usuario;

    JComboBox<String> jc_formapag;

    JButton jb_inserir;
    VendaControl oVendaControl = new VendaControl();

    private static VendaView instanciaUnica;

    private VendaView() {
        setTitle("Sebo Ruído Branco - Registro de Vendas");
        setBounds(100, 100, 420, 360);
        setResizable(false);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        jl_codigo = new JLabel("Cód. Recibo:");
        jl_codigo.setBounds(20, 20, 100, 20);
        jt_codigo = new JTextField(gerarCodigoRecibo());
        jt_codigo.setBounds(130, 20, 230, 20);
        jt_codigo.setEnabled(false);

        jl_data = new JLabel("Data/Hora:");
        jl_data.setBounds(20, 60, 100, 20);
        jt_data = new JTextField(getComDataAtual());
        jt_data.setBounds(130, 60, 230, 20);
        jt_data.setEnabled(false);

        jl_id_usuario = new JLabel("ID Operador:");
        jl_id_usuario.setBounds(20, 100, 100, 20);
        jt_id_usuario = new JTextField("1");
        jt_id_usuario.setBounds(130, 100, 230, 20);
        jt_id_usuario.setEnabled(false);

        jl_valor = new JLabel("Valor Total (R$):");
        jl_valor.setBounds(20, 140, 100, 20);
        jt_valor = new JTextField("50.00");
        jt_valor.setBounds(130, 140, 230, 20);

        jl_formapag = new JLabel("Forma Pagamento:");
        jl_formapag.setBounds(20, 180, 120, 20);
        String[] opcoesPagamento = {"Dinheiro", "Cartão de Crédito", "Cartão de Débito", "Pix"};
        jc_formapag = new JComboBox<>(opcoesPagamento);
        jc_formapag.setBounds(130, 180, 230, 20);

        jb_inserir = new JButton("Finalizar Venda");
        jb_inserir.setBounds(130, 240, 150, 35);
        jb_inserir.addActionListener(this);

        getContentPane().add(jl_codigo);
        getContentPane().add(jt_codigo);
        getContentPane().add(jl_data);
        getContentPane().add(jt_data);
        getContentPane().add(jl_id_usuario);
        getContentPane().add(jt_id_usuario);
        getContentPane().add(jl_valor);
        getContentPane().add(jt_valor);
        getContentPane().add(jl_formapag);
        getContentPane().add(jc_formapag);
        getContentPane().add(jb_inserir);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == jb_inserir) {
            try {
                String formaSelecionada = (String) jc_formapag.getSelectedItem();

                oVendaControl.inserirVenda(
                        Double.parseDouble(jt_valor.getText()),
                        jt_codigo.getText(),
                        formaSelecionada,
                        jt_data.getText(),
                        Integer.parseInt(jt_id_usuario.getText())
                );

                JOptionPane.showMessageDialog(null, "Venda registrada com sucesso no banco de dados!");

                jt_codigo.setText(gerarCodigoRecibo());
                jt_data.setText(getComDataAtual());

            } catch (Exception erro) {
                JOptionPane.showMessageDialog(null, "Erro ao processar venda: " + erro.getMessage());
            }
        }
    }

    public static VendaView getInstancia() {
        if (instanciaUnica == null) {
            instanciaUnica = new VendaView();
        }
        return instanciaUnica;
    }

    @Override
    public void dispose() {
        super.dispose();
        instanciaUnica = null;
    }

    private String gerarCodigoRecibo() {
        return "REC-" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }

    private String getComDataAtual() {
        SimpleDateFormat formatador = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatador.format(new Date());
    }

    public static void main(String[] args) {
        VendaView.getInstancia().setVisible(true);
    }
}