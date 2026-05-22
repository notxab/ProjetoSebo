package Pck_Model;

public class PrateleiraModel {
    private int id_prateleira;
    private int numero;
    private String tipo;
    private String lugar;


    // Getters e Setters
    public int getId_prateleira() { return id_prateleira; }
    public void setId_prateleira(int id_prateleira) { this.id_prateleira = id_prateleira; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public String getLugar() { return lugar; }
    public void setLugar(String lugar) { this.lugar = lugar; }
}