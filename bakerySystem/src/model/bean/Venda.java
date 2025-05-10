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
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        this.dataVenda = dataVenda;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public List<Produto> getListaProdutos() {
        return listaProdutos;
    }

    public void setListaProdutos(List<Produto> listaProdutos) {
        this.listaProdutos = listaProdutos;
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
