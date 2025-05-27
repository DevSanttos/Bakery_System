package controller;

import model.bean.Produto;
import model.bean.StatusResgate;
import service.CaixaService;
import model.bean.Caixa;
import model.bean.Gerente;
import model.dao.CaixaDAO;
import service.GerenteService;

import java.util.List;
import model.bean.Cliente;

public class GerenteController {
    private GerenteService gerenteService;

    public GerenteController(GerenteService gerenteService) {
        this.gerenteService = gerenteService;
    }

    public List<Gerente> readGerente() {
        return gerenteService.readGerente();
    }

    public Gerente findById(Long id) {
        return gerenteService.findById(id);
    }

    public boolean findByLoginAndPassword(String login, String senha) {
        return gerenteService.findByLoginAndPassword(login, senha);
    }

    public Caixa createCaixa(String nome, String CPF, String telefone, String cargo, String login, String senha) {
        return gerenteService.createCaixa(nome, CPF, telefone, cargo, login, senha);
    }

    public boolean updateCaixa(Long idCaixa, String nome, String CPF, String telefone, String cargo, String login, String senha) {
        return gerenteService.updateCaixa(idCaixa, nome, CPF, telefone, cargo, login, senha);
    }

    public boolean deleteCaixa(Long id) {
        return gerenteService.deleteCaixa(id);
    }

    public Produto createProduto(String nome, double preco, String tipo, int quantidade) {
        return gerenteService.createProduto(nome, preco, tipo, quantidade);
    }

    public boolean updateProduto(Long idProduto, String nome, double preco, String tipo, int quantidade, boolean disponivelParaTroca, int pontosNecessarios, StatusResgate statusResgate) {
        return gerenteService.updateProduto(idProduto, nome, preco, tipo, quantidade, disponivelParaTroca, pontosNecessarios, statusResgate);
    }

    public boolean deleteProduto(Long idProduto) {
        return gerenteService.deleteProduto(idProduto);
    }
    
    public Cliente createCliente (String nome, String CPF, String telefone) {
        return gerenteService.createCliente(nome, CPF, telefone);
    }
    
    public boolean updateCliente(Long idCliente, String nome, String CPF, String telefone){
        return gerenteService.updateCliente(idCliente, nome, CPF, telefone);
    }
    
    public boolean deleteCliente(Long idCliente){
        return gerenteService.deleteCliente(idCliente);
    }
}
