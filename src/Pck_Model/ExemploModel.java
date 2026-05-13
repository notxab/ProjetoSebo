package Pck_Model;

public class ExemploModel {

    /* Atributo que representa o campo a01_codigo da tabela exemplo_01.
       Esse campo será a chave primária da tabela. */
    private int a01_codigo;

    /* Atributo que representa o campo a01_nome da tabela exemplo_01.
       Esse campo receberá o texto digitado na tela. */
    private String a01_nome;

    /* Método get do código. Ele devolve o valor armazenado no atributo a01_codigo. */
    public int getA01_codigo() {
        return a01_codigo;
    }

    /* Método set do código. Ele recebe um valor e armazena no atributo a01_codigo. */
    public void setA01_codigo(int a01_codigo) {
        this.a01_codigo = a01_codigo;
    }

    /* Método get do nome. Ele devolve o valor armazenado no atributo a01_nome. */
    public String getA01_nome() {
        return a01_nome;
    }

    /* Método set do nome. Ele recebe um texto e armazena no atributo a01_nome. */
    public void setA01_nome(String a01_nome) {
        this.a01_nome = a01_nome;
    }
}