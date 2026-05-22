package Pck_Model;

public class LivroModel {
    private int id_livro;
    private String titulo;
    private String autor;
    private String genero;
    private double preco;
    private String status;
    private int id_prateleira;

    // Getters e Setters
    public int getId_livro() { return id_livro; }
    public void setId_livro(int id_livro) { this.id_livro = id_livro; }

    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) { this.titulo = titulo; }

    public String getAutor() { return autor; }
    public void setAutor(String autor) { this.autor = autor; }

    public String getGenero() { return genero; }
    public void setGenero(String genero) { this.genero = genero; }

    public double getPreco() { return preco; }
    public void setPreco(double preco) { this.preco = preco; }

    public String getStatus() { return status; } // status adicionado 22/05/2026
    public void setStatus(String status) { this.status = status; } // status adicionado 22/05/2026

    public int getId_prateleira() { return id_prateleira; }
    public void setId_prateleira(int id_prateleira) { this.id_prateleira = id_prateleira; }
}