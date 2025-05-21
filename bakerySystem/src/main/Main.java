/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package main;

import model.dao.impl.ProdutoDAOImpl;
import model.dao.impl.ClienteDAOImpl;
import model.dao.impl.VendaDAOImpl;
import connection.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import model.bean.*;
import model.dao.GerenteDAO;
import model.dao.impl.GerenteDAOImpl;
import service.GerenteService;

public class Main {

    public static void main(String[] args) {
        Connection conexao = null;
        conexao = (Connection) ConnectionFactory.getConnection();
        
        //testando a implementação da classe GerenteService
        GerenteDAO gerenteDAO = new GerenteDAOImpl();
        
        GerenteService gerenteService = new GerenteService(gerenteDAO);
        
        
        Gerente gerente = new Gerente("Nataniel", "14212122212", "929929929", "GerenteI", "natanGay", "123");
        
        Gerente novoGerente = gerenteService.createProduto(gerente);
        
//
//        VendaDAOImpl vendaDAO = new VendaDAOImpl();
//
//        // Criar clientes e produtos para testes
//        Cliente cliente1 = new Cliente("Dagoberto", "89089089089", "45989890990", 0);
//        new ClienteDAOImpl().create(cliente1); // Persiste o cliente para ter um ID
//
//        Produto produto1 = new Produto();
//        produto1.setNome("Notebook");
//        produto1.setPreco(2500.00);
//        produto1.setQuantidade(10);
//        new ProdutoDAOImpl().create(produto1); // Persiste o produto para ter um ID
//
//        Produto produto2 = new Produto();
//        produto2.setNome("Mouse");
//        produto2.setPreco(25.00);
//        produto2.setQuantidade(50);
//        new ProdutoDAOImpl().create(produto2); // Persiste o produto para ter um ID
//
//        // Teste do método create
//        System.out.println("--- Teste do método create em venda ---");
//        Venda novaVenda = new Venda();
//        novaVenda.setDataVenda(LocalDate.now());
//        novaVenda.setCliente(cliente1);
//        System.out.println("Objeto venda, com data e cliente, criado com sucesso na memória local!");
//
//        List<ItemVenda> itensNovaVenda = new ArrayList<>();
//        System.out.println("Lista de ItemVenda criada com sucesso!");
//
//        ItemVenda item1 = new ItemVenda();
//        item1.setProduto(produto1);
//        item1.setQuantidade(1);
//        System.out.println("Objeto ItemVenda criado com sucesso, com o produto adicionado e quantidade setada!");
//        itensNovaVenda.add(item1);
//        System.out.println("Objeto ItemVenda adicionado à lista de ItemVenda com sucesso!");
//
//        ItemVenda item2 = new ItemVenda();
//        item2.setProduto(produto2);
//        item2.setQuantidade(2);
//        System.out.println("venda associada ao item2: " + item2.getVenda());
//        itensNovaVenda.add(item2);
//        novaVenda.setItens(itensNovaVenda);
//        
//        Venda vendaCriada = vendaDAO.create(novaVenda);
//        
//        if (vendaCriada != null && vendaCriada.getIdVenda() != null) {
//            System.out.println("Venda criada com sucesso! ID: " + vendaCriada.getIdVenda());
//            System.out.println("Quantidade de itens presente na venda: " + vendaCriada.getItens().size());
//
//            for (ItemVenda item : vendaCriada.getItens()) {
//                System.out.println("  Item ID: " + item.getIdItemVenda() + ", Produto: " + item.getProduto().getNome() + ", Quantidade: " + item.getQuantidade());
//            }
//        } else {
//            System.out.println("Falha ao criar a venda.");
//        }
//        System.out.println();
//
//        Long idVendaCriada = (vendaCriada != null) ? vendaCriada.getIdVenda() : null;
//
//        if (idVendaCriada != null) {
//            // Teste do método findById
//            System.out.println("--- Teste do método findById ---");
//            Venda vendaEncontrada = vendaDAO.findById(idVendaCriada);
//            if (vendaEncontrada != null) {
//                System.out.println("Venda encontrada com ID: " + vendaEncontrada.getIdVenda() + ", Data: " + vendaEncontrada.getDataVenda() + ", Cliente: " + (vendaEncontrada.getCliente() != null ? vendaEncontrada.getCliente().getNome() : "Nenhum"));
//                if (vendaEncontrada.getItens() != null) {
//                    for (ItemVenda item : vendaEncontrada.getItens()) {
//                        System.out.println("  Item ID: " + item.getIdItemVenda() + ", Produto: " + item.getProduto().getNome() + ", Quantidade: " + item.getQuantidade() + ", Venda associada: " + (item.getVenda() != null ? item.getVenda() : "Nenhum"));
//                    }
//                }
//            } else {
//                System.out.println("Nenhuma venda encontrada com o ID: " + idVendaCriada);
//            }
//            System.out.println();
//
//            // Teste do método update
//            System.out.println("--- Teste do método update ---");
//            if (vendaEncontrada != null) {
//                vendaEncontrada.setDataVenda(LocalDate.now().plusDays(1));
//                
//                Cliente cliente2 = new Cliente("Maria Souza", "89089089089", "34989898778", 0);
//                new ClienteDAOImpl().create(cliente2); // Persiste outro cliente
//                
//                vendaEncontrada.setCliente(cliente2);
//                
//                List<ItemVenda> itensAtualizados = new ArrayList<>();
//                ItemVenda item3 = new ItemVenda();
//                item3.setProduto(produto1);
//                item3.setQuantidade(3);
//                itensAtualizados.add(item3);
//                vendaEncontrada.setItens(itensAtualizados);
//
//                boolean atualizado = vendaDAO.update(vendaEncontrada);
//                if (atualizado) {
//                    System.out.println("Venda com ID " + vendaEncontrada.getIdVenda() + " atualizada com sucesso!");
//                    Venda vendaAtualizada = vendaDAO.findById(vendaEncontrada.getIdVenda());
//                    if (vendaAtualizada != null) {
//                        System.out.println("Dados da venda atualizada: Data: " + vendaAtualizada.getDataVenda() + ", Cliente: " + (vendaAtualizada.getCliente() != null ? vendaAtualizada.getCliente().getNome() : "Nenhum"));
//                        if (vendaAtualizada.getItens() != null) {
//                            for (ItemVenda item : vendaAtualizada.getItens()) {
//                                System.out.println("Item ID: " + item.getIdItemVenda() + ", Produto: " + item.getProduto().getNome() + ", Quantidade: " + item.getQuantidade());
//                            }
//                        }
//                    }
//                } else {
//                    System.out.println("Falha ao atualizar a venda com ID " + vendaEncontrada.getIdVenda());
//                }
//            }
//            System.out.println();
//
//            // Teste do método read
//            System.out.println("--- Teste do método read ---");
//            List<Venda> todasVendas = vendaDAO.read();
//            
//            if (!todasVendas.isEmpty()) {
//                System.out.println("Lista de todas as vendas:");
//                for (Venda venda : todasVendas) {
//                    System.out.println("  ID: " + venda.getIdVenda() + ", Data: " + venda.getDataVenda() + ", Cliente: " + (venda.getCliente() != null ? venda.getCliente().getNome() : "Nenhum"));
//                    if (venda.getItens() != null) {
//                        for (ItemVenda item : venda.getItens()) {
//                            System.out.println("    Item ID: " + item.getIdItemVenda() + ", Produto: " + item.getProduto().getNome() + ", Quantidade: " + item.getQuantidade());
//                        }
//                    }
//                }
//            } else {
//                System.out.println("Nenhuma venda cadastrada.");
//            }
//            System.out.println();
//
//            // Teste do método delete
//            System.out.println("--- Teste do método delete ---");
//            if (idVendaCriada != null) {
//                boolean deletado = vendaDAO.delete(idVendaCriada);
//                if (deletado) {
//                    System.out.println("Venda com ID " + idVendaCriada + " deletada com sucesso!");
//                    Venda vendaDeletada = vendaDAO.findById(idVendaCriada);
//                    if (vendaDeletada == null) {
//                        System.out.println("Confirmação: Venda com ID " + idVendaCriada + " não encontrada após a exclusão.");
//                    } else {
//                        System.out.println("Erro: Venda com ID " + idVendaCriada + " ainda existe após a tentativa de exclusão.");
//                    }
//                } else {
//                    System.out.println("Falha ao deletar a venda com ID " + idVendaCriada);
//                }
//            }
//            System.out.println();
//        }
//
//        // Limpeza (opcional, dependendo do ambiente de teste)
//        if (cliente1 != null && cliente1.getId() != null) {
//            new ClienteDAOImpl().delete(cliente1.getId());
//            System.out.println("cliente1 deletado com sucesso!");
//        }
//        if (produto1 != null && produto1.getIdProduto() != null) {
//            new ProdutoDAOImpl().delete(produto1.getIdProduto());
//            System.out.println("produto1 deletado com sucesso!");
//        }
//        if (produto2 != null && produto2.getIdProduto() != null) {
//            new ProdutoDAOImpl().delete(produto2.getIdProduto());
//            System.out.println("produto2 deletado com sucesso!");
//        }
//
//        Cliente cliente2 = new Cliente("Maria Souza", "13333333333", "34989898778", 0);
//        ClienteDAOImpl clienteDAO = new ClienteDAOImpl();
//
//        Cliente cliente2Persistido = clienteDAO.findByName("Maria Souza");
//        if (cliente2Persistido != null) {
//            clienteDAO.delete(cliente2Persistido.getId());
//            System.out.println("cliente persistido deletado");
//        } else {
//            System.out.println("Sucesso!");
//        }
//        
    }
}

