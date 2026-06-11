package Pck_Persistencia;

import Pck_DAO.ConexaoMySql;
import Pck_Model.UsuarioModel;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UsuarioPersistencia {

    public UsuarioModel autenticarUsuario(String nome, String senha) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        UsuarioModel usuario = null;

        String sql = "SELECT * FROM Usuario WHERE nome = ? AND senha = ?";

        try {
            conn = ConexaoMySql.conectar();
            stmt = conn.prepareStatement(sql);
            stmt.setString(1, nome);
            stmt.setString(2, senha);
            rs = stmt.executeQuery();

            if (rs.next()) {
                usuario = new UsuarioModel();
                usuario.setIdUsuario(rs.getInt("id_usuario"));
                usuario.setNome(rs.getString("nome"));
                usuario.setSenha(rs.getString("senha"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao autenticar usuário: " + e.getMessage());
        }

        return usuario;
    }
}