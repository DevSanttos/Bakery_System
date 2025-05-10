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
    private double preco;
    private StatusResgate resgatavel;
    private String tipo;
    private int quantidade;

    public Produto(String nome, double preço, StatusResgate resgatavel, String tipo, int quantidade) {
        this.nome = nome;
        this.preco = preco;
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

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public StatusResgate getResgatavel() {
        return resgatavel;
    }

    public void setResgatavel(StatusResgate resgatavel) {
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
        return "Produto{" + "nome=" + nome + ", preço=" + preco + ", resgatavel=" + resgatavel + ", tipo=" + tipo + ", quantidade=" + quantidade + '}';
    }
}
