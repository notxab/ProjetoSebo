package Pck_Control;

import Pck_Model.LivroModel;
import Pck_Persistencia.LivroPersistencia;
import java.util.ArrayList;

public class LivroControl {

    private LivroPersistencia oLivroPersistencia = new LivroPersistencia();

    public ArrayList<LivroModel> listarLivros() {
        return oLivroPersistencia.listarLivros();
    }

    public void inserirLivro(String titulo, String autor, String genero, String preco, String status, String idPrateleira) {
        LivroModel oLivroModel = new LivroModel();

        oLivroModel.setTitulo(titulo);
        oLivroModel.setAutor(autor);
        oLivroModel.setGenero(genero);
        oLivroModel.setPreco(Double.parseDouble(preco));
        oLivroModel.setStatus(status);
        oLivroModel.setId_prateleira(Integer.parseInt(idPrateleira));

        oLivroPersistencia.inserirLivro(oLivroModel);
    }

    public void atualizarLivro(int id, String titulo, String autor, String genero, double preco, String status, int idPrateleira) {
        LivroModel oLivroModel = new LivroModel();

        oLivroModel.setId_livro(id);
        oLivroModel.setTitulo(titulo);
        oLivroModel.setAutor(autor);
        oLivroModel.setGenero(genero);
        oLivroModel.setPreco(preco);
        oLivroModel.setStatus(status);
        oLivroModel.setId_prateleira(idPrateleira);

        oLivroPersistencia.atualizarLivro(oLivroModel);
    }

    public void removerLivro(int id) {
        oLivroPersistencia.removerLivro(id);
    }
}