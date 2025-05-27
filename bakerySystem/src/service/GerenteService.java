/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service;

import model.bean.Caixa;
import model.bean.Gerente;
import model.bean.Produto;
import model.bean.StatusResgate;
import model.dao.CaixaDAO;
import model.dao.ClienteDAO;
import model.dao.GerenteDAO;
import model.dao.ProdutoDAO;
import model.dao.impl.CaixaDAOImpl;
import model.dao.impl.ClienteDAOImpl;
import model.dao.impl.GerenteDAOImpl;
import model.dao.impl.ProdutoDAOImpl;

import java.util.ArrayList;
import java.util.List;
import model.bean.Cliente;

/**
 * @author jonat
 */
public class GerenteService {
    private final GerenteDAO gerenteDAO;

    ProdutoDAO produtoDAO = new ProdutoDAOImpl();
    ProdutoService produtoService = new ProdutoService(produtoDAO);

    CaixaDAO caixaDAO = new CaixaDAOImpl();
    CaixaService caixaService = new CaixaService(caixaDAO);

    ClienteDAO clienteDAO = new ClienteDAOImpl();
    ClienteService clienteService = new ClienteService(clienteDAO);

    public GerenteService(GerenteDAO gerenteDAO) {
        this.gerenteDAO = gerenteDAO;
    }


    public Gerente createGerente(Gerente gerente) {
        if (gerente == null) {
            throw new IllegalArgumentException("O gerente não pode ser nulo.");
        }

        if (gerente.getNome() == null || gerente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do gerente é necessário para sua criação.");
        }

        if (gerente.getCPF() == null || gerente.getCPF().length() > 14 || gerente.getCPF().length() < 11 || gerente.getCPF().trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF do gerente é necessário para sua criação.");
        }

        if (gerente.getTelefone() == null || gerente.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do gerente é necessário para sua criação.");
        }

        if (gerente.getCargo() == null || gerente.getCargo().trim().isEmpty()) {
            throw new IllegalArgumentException("O cargo do gerente é necessário para sua criação.");
        }

        if (gerente.getLogin() == null || gerente.getLogin().trim().isEmpty()) {
            throw new IllegalArgumentException("O login do gerente é necessário para sua criação.");
        }

        if (gerente.getSenha() == null || gerente.getSenha().trim().isEmpty()) {
            throw new IllegalArgumentException("A senha do gerente é necessária para sua criação.");
        }

        try {
            return gerenteDAO.create(gerente);
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException("Erro ao criar o gerente." + ex.getMessage());
        }
    }

    public List<Gerente> readGerente() {
        return gerenteDAO.read();
    }

    public boolean updateGerente(Gerente gerente) {
        if (gerente == null) {
            throw new IllegalArgumentException("O gerente não pode ser nulo.");
        }

        if (gerente.getNome() == null || gerente.getNome().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do gerente é necessário para sua atualização.");
        }

        if (gerente.getCPF() == null || gerente.getCPF().length() > 14 || gerente.getCPF().length() < 11 || gerente.getCPF().trim().isEmpty()) {
            throw new IllegalArgumentException("O CPF do gerente é necessário para sua atualização.");
        }

        if (gerente.getTelefone() == null || gerente.getTelefone().trim().isEmpty()) {
            throw new IllegalArgumentException("O nome do gerente é necessário para sua atualização.");
        }

        if (gerente.getCargo() == null || gerente.getCargo().trim().isEmpty()) {
            throw new IllegalArgumentException("O cargo do gerente é necessário para sua atualização.");
        }

        if (gerente.getLogin() == null || gerente.getLogin().trim().isEmpty()) {
            throw new IllegalArgumentException("O login do gerente é necessário para sua atualização.");
        }

        if (gerente.getSenha() == null || gerente.getSenha().trim().isEmpty()) {
            throw new IllegalArgumentException("A senha do gerente é necessária para sua atualização.");
        }

        try {
            return gerenteDAO.update(gerente);
        } catch (RuntimeException ex) {
            throw new IllegalArgumentException("Erro ao atualizar o gerente." + ex.getMessage());
        }
    }

    public boolean deleteGerente(Long id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("Não há gerentes com ID negativo ou nulo.");
        }
        try {
            return gerenteDAO.delete(id);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Erro ao excluir o gerente!" + ex.getMessage());
        }
    }

    public Gerente findById(Long id) {
        if (id == null || id <= 0) {
            throw new RuntimeException("Não há gerentes com ID negativo ou nulo.");
        }
        try {
            return gerenteDAO.findById(id);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Erro ao encontrar o gerente pelo ID!" + ex.getMessage());
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
            if (gerenteDAO.findByLoginAndPassword(login, senha) != null) {
                return true;
            } 
        } catch (RuntimeException ex) {
            throw new RuntimeException("Erro ao buscar pelo ID do gerente: " + ex.getMessage());
        }
        return false;
    }

    public Caixa createCaixa (String nome, String CPF, String telefone, String cargo, String login, String senha) {
        if(nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("Não é possível criar um caixa com nome vazio ou nulo!");
        }
        if(CPF == null || CPF.trim().isEmpty()) {
            throw new RuntimeException("Não é possível criar um caixa com CPF vazio ou nulo!");
        }
        if(telefone == null || telefone.trim().isEmpty()) {
            throw new RuntimeException("Não é possível criar um caixa com telefone vazio ou nulo!");
        }
        if(cargo == null || cargo.trim().isEmpty()){
            throw new RuntimeException("Não é possível criar um caixa com cargo vazio ou nulo!");
        }
        if(login == null || login.trim().isEmpty()) {
            throw new RuntimeException("Não é possível criar um caixa com login vazio ou nulo!");
        }
        if(senha == null || senha.trim().isEmpty()) {
            throw new RuntimeException("Não é possível criar um caixa com senha vazia ou nula!");
        }

        try {
            Caixa caixa = new Caixa(nome, CPF, telefone, cargo, login, senha);
            return caixaService.createCaixa(caixa);

        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao criar caixa! " + ex.getMessage());
        }
    }

    public boolean updateCaixa(Long idCaixa, String nome, String CPF, String telefone, String cargo, String login, String senha){
        Caixa caixaDoBD = caixaService.findById(idCaixa);

        if(nome != null && !nome.trim().isEmpty()){
            caixaDoBD.setNome(nome);
        }
        if(CPF != null && !CPF.trim().isEmpty()){
            caixaDoBD.setCPF(CPF);
        }
        if(telefone != null && !telefone.trim().isEmpty()){
            caixaDoBD.setTelefone(telefone);
        }
        if(cargo != null && !cargo.trim().isEmpty()){
            caixaDoBD.setCargo(cargo);
        }
        if(login != null && !login.trim().isEmpty()){
            caixaDoBD.setLogin(login);
        }
        if(senha != null && !senha.trim().isEmpty()){
            caixaDoBD.setSenha(senha);
        }

        if(caixaDoBD == null){
            throw new IllegalArgumentException("Não é possível atualizar um caixa nulo.");
        }
        if(caixaDoBD.getNome() == null || caixaDoBD.getNome().trim().isEmpty()){
            throw new IllegalArgumentException("Não é possível atualizar o nome do caixa para um nome vazio ou nulo.");
        }
        if(caixaDoBD.getCPF() == null || caixaDoBD.getCPF().trim().isEmpty()){
            throw new IllegalArgumentException("Não é possível atualizar o CPF do caixa para vazio ou nulo.");
        }
        if(caixaDoBD.getTelefone() == null || caixaDoBD.getTelefone().trim().isEmpty()){
            throw new IllegalArgumentException("Não é possível atualizar o telefone do caixa para um número vazio ou nulo.");
        }
        if(caixaDoBD.getCargo() == null || caixaDoBD.getCargo().trim().isEmpty()){
            throw new IllegalArgumentException("Não é possível atualizar o cargo do caixa para vazio ou nulo.");
        }
        if(caixaDoBD.getLogin() == null || caixaDoBD.getLogin().trim().isEmpty()){
            throw new IllegalArgumentException("Não é possível atualizar o login do caixa para vazio ou nulo.");
        }
        if(caixaDoBD.getSenha() == null || caixaDoBD.getSenha().trim().isEmpty()){
            throw new IllegalArgumentException("Não é possível atualizar a senha do caixa para vazio ou nulo.");
        }

        try{
            return caixaService.updateCaixa(caixaDoBD);
        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao atualizar caixa! " + ex.getMessage());
        }
    }

    public boolean deleteCaixa(Long id){
        if(id == null || id == 0){
            throw new RuntimeException("Não é possível deletar um caixa com ID nulo ou igual a zero");
        }

        try{
            return caixaService.deleteCaixa(id);
        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao deletar caixa! " + ex.getMessage());
        }
    }

    public Produto createProduto(String nome, double preco, String tipo, int quantidade) {
        if (nome == null || nome.isEmpty()) {
            throw new IllegalArgumentException("Informe um nome válido.");
        }
        if (preco <= 0) {
            throw new IllegalArgumentException("Informe um preco válido.");
        }
        if (tipo == null || tipo.isEmpty()) {
            throw new IllegalArgumentException("Informe uma senha válida.");
        }
        if (quantidade < 0) {
            throw new IllegalArgumentException("Informe uma quantidade válida.");
        }

        try {
            Produto produto = new Produto(nome, preco, tipo, quantidade);
            ProdutoDAO produtoDAO = new ProdutoDAOImpl();
            ProdutoService produtoService = new ProdutoService(produtoDAO);

            return produtoService.createProduto(produto);
        } catch (RuntimeException ex) {
            throw new RuntimeException("Erro ao criar produto! " + ex.getMessage());
        }
    }

    public boolean updateProduto(Long idProduto, String nome, double preco, String tipo, int quantidade, boolean disponivelParaTroca, int pontosNecessarios, StatusResgate statusResgate) {
        if (idProduto == null || idProduto <= 0) {
            throw new IllegalArgumentException("Informe um ID válido.");
        }

        try {
            Produto produto = produtoService.findById(idProduto);
            if (produto.getIdProduto() == null) {
                throw new IllegalArgumentException("Erro ao realizar a busca pelo produto com ID: " + idProduto);
            } else {

                if (nome == null || nome.trim().isEmpty()) {
                    throw new IllegalArgumentException("O nome não pode ser nulo ou estar vazio");
                }

                if (preco < 0) {
                    throw new IllegalArgumentException("O preço não pode ser negativo.");
                }

                if (tipo == null || tipo.trim().isEmpty()) {
                    throw new IllegalArgumentException("O tipo não pode ser nulo ou estar vazio");
                }

                if (quantidade < 0) {
                    throw new IllegalArgumentException("A quantidade não pode ser negativa");
                }

                if (pontosNecessarios < 0) {
                    throw new IllegalArgumentException("A quantidade de pontos necessários não pode ser negativa");
                }

                produto.setNome(nome);
                produto.setPreco(preco);
                produto.setTipo(tipo);
                produto.setQuantidade(quantidade);
                produto.setDisponivelParaTroca(disponivelParaTroca);
                produto.setPontosNecessarios(pontosNecessarios);
                produto.setStatusResgate(statusResgate);

                produtoService.updateProduto(produto);
                return true;
            }
        } catch (RuntimeException ex) {
            throw new RuntimeException("Erro ao atualizar produto! " + ex.getMessage());
        }
    }

    public boolean deleteProduto(Long idProduto){
        if(idProduto == null || idProduto == 0){
            throw new IllegalArgumentException("Não é possível deletar um produto com ID nulo ou igual a zero");
        }

        try{
            return produtoService.deleteProduto(idProduto);
        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao deletar caixa! " + ex.getMessage());
        }
    }

    
    public Cliente createCliente (String nome, String CPF, String telefone) {
        if(nome == null || nome.trim().isEmpty()) {
            throw new RuntimeException("Não é possível criar um cliente com nome vazio ou nulo!");
        }
        if(CPF == null || CPF.trim().isEmpty()) {
            throw new RuntimeException("Não é possível criar um cliente com CPF vazio ou nulo!");
        }
        if(telefone == null || telefone.trim().isEmpty()) {
            throw new RuntimeException("Não é possível criar um cliente com telefone vazio ou nulo!");
        }

        try {
            Cliente cliente = new Cliente(nome, CPF, telefone);
            return clienteService.createCliente(cliente);

        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao criar cliente! " + ex.getMessage());
        }
    }
    
    public boolean updateCliente(Long idCliente, String nome, String CPF, String telefone){
        Cliente clienteDAO = clienteService.findById(idCliente);

        if(nome != null && !nome.trim().isEmpty()){
            clienteDAO.setNome(nome);
        }
        if(CPF != null && !CPF.trim().isEmpty()){
            clienteDAO.setCPF(CPF);
        }
        if(telefone != null && !telefone.trim().isEmpty()){
            clienteDAO.setTelefone(telefone);
        }

        if(clienteDAO == null){
            throw new IllegalArgumentException("Não é possível atualizar um cliente nulo.");
        }
        if(clienteDAO.getNome() == null || clienteDAO.getNome().trim().isEmpty()){
            throw new IllegalArgumentException("Não é possível atualizar o nome do cliente para um nome vazio ou nulo.");
        }
        if(clienteDAO.getCPF() == null || clienteDAO.getCPF().trim().isEmpty()){
            throw new IllegalArgumentException("Não é possível atualizar o CPF do cliente para vazio ou nulo.");
        }
        if(clienteDAO.getTelefone() == null || clienteDAO.getTelefone().trim().isEmpty()){
            throw new IllegalArgumentException("Não é possível atualizar o telefone do cliente para um número vazio ou nulo.");
        }

        try{
            return clienteService.updateCliente(clienteDAO);
        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao atualizar cliente! " + ex.getMessage());
        }
    }
    
    public boolean deleteCliente(Long idCliente){
        if(idCliente == null || idCliente == 0){
            throw new RuntimeException("Não é possível deletar um cliente com ID nulo ou igual a zero");
        }
        try{
            return clienteService.deleteCliente(idCliente);
        } catch (RuntimeException ex){
            throw new RuntimeException("Erro ao deletar cliente! " + ex.getMessage());
        }
    }
}
