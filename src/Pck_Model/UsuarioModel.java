package Pck_Model;

public class UsuarioModel {
    private int idUsuario;
    private String nome;
    private String senha;

    // Construtor vazio
    public UsuarioModel() {}

    // Construtor cheio
    public UsuarioModel(int idUsuario, String nome, String senha) {
        this.idUsuario = idUsuario;
        this.nome = nome;
        this.senha = senha;
    }

    // Getters e Setters
    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }

    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
}