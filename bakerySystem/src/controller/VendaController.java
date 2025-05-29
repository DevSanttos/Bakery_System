package controller;

import model.bean.ItemVenda;
import model.bean.Venda;
import service.VendaService;

import java.util.List;
import model.bean.Produto;

public class VendaController {

    private VendaService vendaService;

    public VendaController(VendaService vendaService) {
        if (vendaService == null) {
            throw new IllegalArgumentException("Venda Service não pode ser nula");
        }
        this.vendaService = vendaService;
    }

    public Venda createVenda(Venda venda) {
        if (venda == null) {
            throw new IllegalArgumentException("Venda não pode ser nula");
        }
        return vendaService.createVenda(venda);
    }

    public Venda findById(Long id) {
        if (id == null || id <= 0) {
            throw new IllegalArgumentException("O ID não pode ser nulo ou menor que 0");
        }
        return vendaService.findById(id);
    }

    public List<ItemVenda> loadItensForVenda(Venda venda) {
        if (venda == null) {
            throw new IllegalArgumentException("A venda não pode ser nula");
        }
        return vendaService.loadItensForVenda(venda);
    }

    public List<Venda> readVenda() {
        return vendaService.readVenda();
    }

    public boolean updateVenda(Venda venda) {
        if (venda == null) {
            throw new IllegalArgumentException("A venda não pode se null");
        }
        return vendaService.updateVenda(venda);
    }

    public Produto addProdutoAoCarrinho(Long idInformado) {
        if (idInformado != null || idInformado > 0) {
            
            return vendaService.addProdutoAoCarrinho(idInformado);
        } else {
            throw new RuntimeException("Não é possível adicionar itens com ID nulo ou igual a zero!");
        }
    }
    
    public void addQuantidadeParaVenda(int quantidade){
        if(quantidade <= 0 ){
            throw new RuntimeException("Quantidade para a venda menor ou igual a 0 não é permitida!");
        }
        vendaService.quantList.add(quantidade);
        return;
    }

    public double realizarVenda(Long idCliente) {
        if (idCliente == null || idCliente <= 0) {
            throw new IllegalArgumentException("O ID do cliente não pode ser null ou negativo");
        }
        return vendaService.realizarVenda(idCliente);
    }

    public double calcSubtotal(List<ItemVenda> itensVenda) {
        if (itensVenda == null || itensVenda.isEmpty()) {
            throw new IllegalArgumentException("A lista de itens venda não pode ser nula");
        }
        return vendaService.calcSubtotal(itensVenda);
    }

    
    
    public List<Produto> getProdutoList() {
        return vendaService.getProdutoList();
    }
    
    public List <Integer> getQuantList(){
        return vendaService.getQuantList();
    }
}
