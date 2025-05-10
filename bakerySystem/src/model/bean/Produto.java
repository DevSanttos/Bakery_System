/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author joseo
 */
public class Produto {

    private String nome;
    private double preço;
    private TrocaPontos resgatavel;
    private String tipo;
    private int quantidade;

    public Produto(String nome, double preço, TrocaPontos resgatavel, String tipo, int quantidade) {
        this.nome = nome;
        this.preço = preço;
        this.resgatavel = resgatavel;
        this.tipo = tipo;
        this.quantidade = quantidade;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getPreço() {
        return preço;
    }

    public void setPreço(double preço) {
        this.preço = preço;
    }

    public TrocaPontos getResgatavel() {
        return resgatavel;
    }

    public void setResgatavel(TrocaPontos resgatavel) {
        this.resgatavel = resgatavel;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Produto{" + "nome=" + nome + ", pre\u00e7o=" + preço + ", resgatavel=" + resgatavel + ", tipo=" + tipo + ", quantidade=" + quantidade + '}';
    }
}
