/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.bean.*;
import model.dao.ClienteDAO;
import model.dao.ProdutoDAO;
import model.dao.VendaDAO;
import model.dao.impl.ClienteDAOImpl;
import model.dao.impl.ProdutoDAOImpl;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;



public class VendaService {

    private final VendaDAO vendaDAO;

    ClienteDAO clienteDAO = new ClienteDAOImpl();
    ClienteService clienteService = new ClienteService(clienteDAO);

    ProdutoDAO produtoDAO = new ProdutoDAOImpl();
    ProdutoService produtoService = new ProdutoService(produtoDAO);

    List<Produto> produtoList = new ArrayList();
    public ArrayList<Integer> quantList = new ArrayList();
    
   
         
    double subtotal = 0;

    public VendaService(VendaDAO vendaDAO) {
        this.vendaDAO = vendaDAO;
    }

    public Venda createVenda(Venda venda) {
        if (venda == null) {
            throw new IllegalArgumentException("O caixa não pode ser nulo.");
        }

        if (venda.getItens() == null || venda.getItens().isEmpty()) {
            throw new IllegalArgumentException("É precisa ter um item.");
        }

        if (venda.getDataVenda() == null) {
            throw new IllegalArgumentException("É preciso ter uma data válida");
        }

        if (venda.getDataVendaSQL() == null) {
            throw new IllegalArgumentException("É preciso ter uma data válida");
        }

        try {
            return vendaDAO.create(venda);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Erro ao realizar a venda");
        }
    }

    public List<ItemVenda> loadItensForVenda(Venda venda) {
        if (venda == null) {
            throw new RuntimeException("A venda não pode ser nula.");
        }

        try {
            return vendaDAO.loadItensForVenda(venda);
        } catch (SQLException ex) {
            throw new RuntimeException("Erro ao retornar os itens da venda.",ex);
        }
    }

    public List<Venda> readVenda(){
        try{
            return vendaDAO.read();
        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao retornar as vendas.",ex);
        }
    }

    public Venda findById(Long id) {
        if(id == null || id <= 0){
            throw new RuntimeException("Não é possível encontrar vendas com ID nulo ou menor do que zero.");
        }

        try{
            return vendaDAO.findById(id);
        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao retornar os vendas.",ex);
        }
    }

    public boolean updateVenda(Venda venda) {
        if (venda == null) {
            throw new RuntimeException("Não é possível atualizar uma venda nula");
        }

        try{
            return vendaDAO.update(venda);
        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao atualizar a venda.",ex);
        }
    }

    public boolean deleteVenda(Long id){
        if(id == null || id <= 0){
            throw new RuntimeException("Não é possível deletar uma venda com ID nulo.");
        }

        try{
            return vendaDAO.delete(id);
        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao deletar a venda.",ex);
        }
    }

    public Produto addProdutoAoCarrinho(Long idInformado) {
        if (idInformado <= 0) {
            throw new IllegalArgumentException("O ID precisa ser válido.");
        }
        
        Produto produto = produtoService.findById(idInformado);
        
        if (produto.getIdProduto() == null) {
            throw new RuntimeException("Erro ao encontrar o respectivo produto!");
        }
        produtoList.add(produto);
        
        return produto;
    }
    
    public void addQuantidadeParaVenda(int quantidade){
        if(quantidade <= 0 ){
            throw new RuntimeException("Quantidade para a venda menor ou igual a 0 não é permitida!");
        }
        quantList.add(quantidade);
    }


    public double realizarVenda(Long idCliente) {
        
        if (idCliente <= 0) {
            throw new IllegalArgumentException("ID inválido.");
        }

        Cliente cliente = clienteDAO.findById(idCliente);

        Venda venda = new Venda(LocalDate.now(), cliente);

        List<ItemVenda> itensVenda = new ArrayList<>();
        

        for (Produto produto : produtoList) {
            for(int quantidade: quantList){
                if(produtoList.indexOf(produto) == quantList.indexOf(quantidade)){
                    if(produto.getQuantidade() >= quantidade){
                        ItemVenda itemVenda = new ItemVenda();
                        itemVenda.setProduto(produto);
                        itemVenda.setPrecoUnitario(produto.getPreco());
                        itemVenda.setQuantidade(quantidade);
                        itensVenda.add(itemVenda);
                    } else{
                        throw new RuntimeException("Quantidade insuficiente");
                    }    
                }
            }
        }

        atualizaEstoqueProdutos(itensVenda);
        venda.setItens(itensVenda);
        venda.setValorTotal(calcSubtotal(itensVenda));

        if(cliente.getId() != null) {
            cliente.setTotalPontosAcumulados(((int) calcSubtotal(itensVenda)) / 2);
        }
           
        //Apagar todos os produtos do carrinho
        createVenda(venda);
        produtoList.clear();
        quantList.clear();
        subtotal = 0;
        return calcSubtotal(itensVenda);
    }

    public double calcSubtotal(List<ItemVenda> itensVenda) {
        for (ItemVenda itemVenda : itensVenda) {
            subtotal += itemVenda.getPrecoUnitario() * itemVenda.getQuantidade();
        }
        return subtotal;
    }

    public void atualizaEstoqueProdutos(List<ItemVenda> itensVenda) {
        for (Produto produto : produtoList) {
            for (ItemVenda itemVenda : itensVenda) {
                if (itemVenda.getProduto().equals(produto)) {
                    Produto novoProduto = produtoService.findById(produto.getIdProduto());
                    novoProduto.setQuantidade(novoProduto.getQuantidade() - itemVenda.getQuantidade());
                    produtoService.updateProduto(novoProduto);
                }
            }
        }
    }
    
    

    public List<Produto> getProdutoList() {
        return produtoList;
    }
    
    public List <Integer> getQuantList(){
        return quantList;
    }
    
    
}
