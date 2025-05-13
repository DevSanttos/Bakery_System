/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

/**
 *
 * @author jonathan
 */
public class Produto {
    private Long idProduto;
    
    private String nome;
    private double preco;
    private String tipo;
    
    private boolean disponivelParaTroca;
    private int pontosNecessarios;
    private StatusResgate statusResgate;

    public Produto(String nome, double preco, String tipo) {
        this.setNome(nome);
        this.setPreco(preco);
        this.setTipo(tipo);
        this.setDisponivelParaTroca(false);
        this.setPontosNecessarios(0);
        this.setStatusResgate(statusResgate.NAO_REALIZADO);
    }
    
    public Produto() {
        
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

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isDisponivelParaTroca() {
        return disponivelParaTroca;
    }

    public void setDisponivelParaTroca(boolean disponivelParaTroca) {
        this.disponivelParaTroca = disponivelParaTroca;
    }

    public int getPontosNecessarios() {
        return pontosNecessarios;
    }

    public void setPontosNecessarios(int pontosNecessarios) {
        this.pontosNecessarios = pontosNecessarios;
    }

    public StatusResgate getStatusResgate() {
        return statusResgate;
    }

    public void setStatusResgate(StatusResgate statusResgate) {
        this.statusResgate = statusResgate;
    }

    public Long getIdProduto() {
        return idProduto;
    }

    @Override
    public String toString() {
        return "Produto{" + "idProduto=" + idProduto + ", nome=" + nome + ", preco=" + preco + ", tipo=" + tipo + ", disponivelParaTroca=" + disponivelParaTroca + ", pontosNecessarios=" + pontosNecessarios + ", statusResgate=" + statusResgate + '}';
    }
}
