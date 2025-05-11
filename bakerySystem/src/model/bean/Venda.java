/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model.bean;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author joseo
 */
public class Venda {
    
    private LocalDate dataVenda;
    private Cliente cliente;
    private List<Produto> listaProdutos; 

    public Venda() {
    }

    public LocalDate getDataVenda() {
        return this.dataVenda;
    }

    public boolean setDataVenda(LocalDate dataVenda) {
        if (dataVenda != null) {
            this.dataVenda = dataVenda;
            return true;
        } else
            return false;
    }

    public Cliente getCliente() {
        return this.cliente;
    }

    public boolean setCliente(Cliente cliente) {
        if (cliente != null) {
            this.cliente = cliente;
            return true;
        } else
            return false;
    }

    public List<Produto> getListaProdutos() {
        return this.listaProdutos;
    }
    
    public void addProduto(Produto umProduto){
        this.listaProdutos.add(umProduto);
    }
    
    public void removeProduto(Produto umProduto){
        this.listaProdutos.remove(umProduto);
    }

    @Override
    public String toString() {
        return "Venda{" + "dataVenda=" + dataVenda + ", cliente=" + cliente + ", listaProdutos=" + listaProdutos + '}';
    }
}
