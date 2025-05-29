/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.bean.Caixa;
import model.bean.Cliente;
import model.bean.Gerente;
import model.dao.CaixaDAO;
import model.dao.ClienteDAO;
import model.dao.GerenteDAO;
import model.dao.impl.ClienteDAOImpl;
import model.dao.impl.GerenteDAOImpl;
import model.bean.Produto;

import java.util.List;
import model.bean.StatusResgate;
import model.dao.ProdutoDAO;
import model.dao.impl.ProdutoDAOImpl;

/**
 *
 * @author jonat
 */
public class CaixaService {
    private final CaixaDAO caixaDAO;
    
    ProdutoDAO produtoDAO = new ProdutoDAOImpl();
    ProdutoService produtoService = new ProdutoService(produtoDAO);
    
    ClienteDAO clienteDAO = new ClienteDAOImpl();
    ClienteService clienteService = new ClienteService(clienteDAO);

    public CaixaService(CaixaDAO caixaDAO) {
        this.caixaDAO = caixaDAO;
    }

    public Caixa createCaixa(Caixa caixa){
        if (caixa == null) {
            throw new IllegalArgumentException("O caixa não pode ser nulo.");
        }

        if (caixa.getNome() == null || caixa.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do caixa é necessário para sua criação.");
        }

        if (caixa.getCPF() == null || caixa.getCPF().length() > 14 || caixa.getCPF().length() < 11 || caixa.getCPF().trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF do caixa é necessário para sua criação.");
        }

        if (caixa.getTelefone() == null || caixa.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do caixa é necessário para sua criação.");
        }

        if (caixa.getCargo() == null || caixa.getCargo().trim().isEmpty()) {
            throw new IllegalArgumentException("O cargo do caixa é necessário para sua criação.");
        }

        if (caixa.getLogin() == null || caixa.getLogin().trim().isEmpty()) {
            throw new IllegalArgumentException("O login do caixa é necessário para sua criação.");
        }

        if (caixa.getSenha() == null || caixa.getSenha().trim().isEmpty()) {
            throw new IllegalArgumentException("A senha do caixa é necessária para sua criação.");
        }

        try {
            return caixaDAO.create(caixa);
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException("Erro ao criar o caixa." + ex.getMessage());
        }
    }

    public List<Caixa> readCaixa() {
        return caixaDAO.read();
    }

    public boolean updateCaixa(Caixa caixa){
        if (caixa == null) {
            throw new IllegalArgumentException("O caixa não pode ser nulo.");
        }

        if (caixa.getNome() == null || caixa.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do caixa é necessário para sua atualização.");
        }

        if (caixa.getCPF() == null || caixa.getCPF().length() > 14 || caixa.getCPF().length() < 11 || caixa.getCPF().trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF do caixa é necessário para sua atualização.");
        }

        if (caixa.getTelefone() == null || caixa.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do caixa é necessário para sua atualização.");
        }

        if (caixa.getCargo() == null || caixa.getCargo().trim().isEmpty()) {
            throw new IllegalArgumentException("O cargo do caixa é necessário para sua atualização.");
        }

        if (caixa.getLogin() == null || caixa.getLogin().trim().isEmpty()) {
            throw new IllegalArgumentException("O login do caixa é necessário para sua atualização.");
        }

        if (caixa.getSenha() == null || caixa.getSenha().trim().isEmpty()) {
            throw new IllegalArgumentException("A senha do caixa é necessária para sua atualização.");
        }

        try {
            return caixaDAO.update(caixa);
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException("Erro ao atualizar o caixa." + ex.getMessage());
        }
    }

    public boolean deleteCaixa(Long id){
        if(id == null || id <= 0) {
            throw new RuntimeException("Não há caixas com ID negativo ou nulo.");
        } try {
            return caixaDAO.delete(id);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Erro ao excluir o caixa!" + ex.getMessage());
        }
    }

    public Caixa findById(Long id){
        if(id == null || id <= 0) {
            throw new RuntimeException("Não há caixas com ID negativo ou nulo.");
        } try {
            return caixaDAO.findById(id);
        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao encontrar o caixa pelo ID!" + ex.getMessage());
        }
    }

    public boolean findByLoginAndPassword(String login, String senha) {
        if (login == null || login.isEmpty()) {
            throw new IllegalArgumentException("Informe um login válido.");
        }

        if (senha == null || senha.isEmpty()) {
            throw new IllegalArgumentException("Informe uma senha válida.");
        }
        try {
            if (caixaDAO.findByLoginAndPassword(login, senha) != null) {
                return true;
            } else
                throw new RuntimeException("O login ou a senha inseridos não coincidem com nenhum registro no banco de dados!");
        } catch (RuntimeException ex) {
            throw new RuntimeException("Erro ao buscar pelo ID do caixa: " + ex.getMessage());
        }
    }

    public Cliente createCliente (String nome, String cpf, String telefone) {
        if(nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("Não é possível criar um cliente com nome nulo!");
        }
        if(cpf == null || cpf.trim().isEmpty()) {
            throw new RuntimeException("Não é possível criar um cliente com cpf nulo!");
        }
        if(telefone == null || telefone.trim().isEmpty()) {
            throw new RuntimeException("Não é possível criar um cliente com telefone nulo!");
        }
        try {
            ClienteDAO clienteDAO = new ClienteDAOImpl();
            ClienteService clienteService = new ClienteService(clienteDAO);
            Cliente cliente = new Cliente(nome, cpf, telefone);
            clienteService.createCliente(cliente);
            return cliente;
        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao criar cliente! " + ex.getMessage());
        }
    }
    
    public boolean realizarResgatePorPontos(Long idProduto, Long idCliente, int quantidade) {
        if (idCliente == null || idCliente <= 0) {
            throw new IllegalArgumentException("ID do cliente não pode ser nulo.");
        }
        Cliente cliente = clienteService.findById(idCliente);
        if (cliente != null && cliente.getId() != null) {
            if (idProduto != null && idProduto > 0) {
                Produto produto = produtoService.findById(idProduto);
                System.out.println(produtoService.readProduto());
                if (produto.isDisponivelParaTroca()) {
                    if (produto.getQuantidade() > 0 && quantidade <= produto.getQuantidade()) {
                            while(quantidade > 0){
                                if (produto.getPontosNecessarios() <= cliente.getTotalPontosAcumulados()) {
                                cliente.setTotalPontosAcumulados(cliente.getTotalPontosAcumulados() - produto.getPontosNecessarios());
                                produto.setStatusResgate(StatusResgate.REALIZADO);
                                produto.setQuantidade(produto.getQuantidade() - 1);
                                produtoService.updateProduto(produto);
                                clienteService.updateCliente(cliente);
                                quantidade--;
                                }
                            return true;
                            }
                    }
                } else {
                    System.out.println("Produto não disponivel para troca!");
                }
            } throw new RuntimeException("O ID do produto é nulo ou menor que 0.");
        } throw new RuntimeException("O ID do cliente é nulo ou menor que 0.");
    }

    public boolean updateCliente (Long id, String nome, String CPF, String telefone) {
        ClienteDAO clienteDAO = new ClienteDAOImpl();
        ClienteService clienteService = new ClienteService(clienteDAO);
        Cliente clienteDoBD = clienteService.findById(id);

        if(nome != null && !nome.trim().isEmpty()){
            clienteDoBD.setNome(nome);
        }
        if(CPF != null && !CPF.trim().isEmpty()){
            clienteDoBD.setCPF(CPF);
        }
        if(telefone != null && !telefone.trim().isEmpty()) {
            clienteDoBD.setTelefone(telefone);
        }

        if(clienteDoBD == null){
            throw new RuntimeException("Não é possível atualizar um cliente nulo.");
        }
        if(clienteDoBD.getNome() == null || clienteDoBD.getNome().trim().isEmpty()) {
            throw new RuntimeException("Não é possível atualizar o nome do cliente para um nome vazio ou nulo.");
        }
        if(clienteDoBD.getCPF() == null || clienteDoBD.getCPF().trim().isEmpty()) {
            throw new RuntimeException("Não é possível atualizar o CPF do cliente para vazio ou nulo.");
        }
        if(clienteDoBD.getTelefone() == null || clienteDoBD.getTelefone().trim().isEmpty()) {
            throw new RuntimeException("Não é possível atualizar o telefone do cliente para um número vazio ou nulo.");
        }

        try{
            return clienteService.updateCliente(clienteDoBD);
        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao atualizar cliente! " + ex.getMessage());
        }
    }
}
