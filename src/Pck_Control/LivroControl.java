package Pck_Control;

import Pck_Model.LivroModel;
import Pck_Persistencia.LivroPersistencia;

public class LivroControl {
    LivroPersistencia oLivroPersistencia = new LivroPersistencia();

    public void inserirLivro(String titulo, String autor, String genero, String preco, String idPrateleira) {
        LivroModel oLivroModel = new LivroModel();

        oLivroModel.setTitulo(titulo);
        oLivroModel.setAutor(autor);
        oLivroModel.setGenero(genero);

        // Conversões necessárias para o banco
        oLivroModel.setPreco(Double.parseDouble(preco.replace(",", ".")));
        oLivroModel.setId_prateleira(Integer.parseInt(idPrateleira));

        oLivroPersistencia.inserirLivro(oLivroModel);
    }
}