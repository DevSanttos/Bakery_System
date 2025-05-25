package model.bean;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Venda {
    private Long idVenda;
    private LocalDate dataVenda;
    private Cliente cliente;
    private List<ItemVenda> itens;
    private double valorTotal;

    public Venda(LocalDate dataVenda, Cliente cliente) {
        this.dataVenda = dataVenda != null ? dataVenda : LocalDate.now();
        this.cliente = cliente;
        this.itens = new ArrayList<>();
    }

    public Venda() {
        this.dataVenda = LocalDate.now();
        this.itens = new ArrayList<>();
    }

    public double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Long getIdVenda() {
        return idVenda;
    }

    public void setIdVenda(Long idVenda) {
        this.idVenda = idVenda;
    }

    public LocalDate getDataVenda() {
        return dataVenda;
    }

    public void setDataVenda(LocalDate dataVenda) {
        if (dataVenda != null) {
            this.dataVenda = dataVenda;
        }
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public void addItem(ItemVenda item) {
        this.itens.add(item);
    }

    public void removeItem(ItemVenda item) {
        this.itens.remove(item);
    }

    public List<ItemVenda> getItens() {
        return itens;
    }

    public Date getDataVendaSQL() {
        return Date.valueOf(this.dataVenda);
    }

    public double calcularTotal() {
        return itens.stream()
                .mapToDouble(item -> item.getPrecoUnitario() * item.getQuantidade())
                .sum();
    }

    @Override
    public String toString() {
        return "Venda{" +
                "idVenda=" + idVenda +
                ", dataVenda=" + dataVenda +
                ", cliente=" + cliente +
                ", itens=" + itens +
                '}';
    }

    public void setItens(List<ItemVenda> itens) {
        this.itens = itens;
    }

}
