package Pck_Control;

import Pck_Model.Sessao;
import Pck_Model.UsuarioModel;
import Pck_Persistencia.UsuarioPersistencia;

public class LoginControl {
    private UsuarioPersistencia persistencia;

    public LoginControl() {
        this.persistencia = new UsuarioPersistencia();
    }

    public boolean efetuarLogin(String nome, String senha) {
        UsuarioModel usuario = persistencia.autenticarUsuario(nome, senha);

        if (usuario != null) {
            Sessao.usuarioLogado = usuario;
            return true;
        }
        return false;
    }
}