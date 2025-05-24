package controller;

import model.bean.Produto;
import service.CaixaService;
import model.bean.Caixa;
import model.bean.Gerente;
import model.dao.CaixaDAO;
import service.GerenteService;

import java.util.List;

public class GerenteController {
    private GerenteService gerenteService;

    public GerenteController(GerenteService gerenteService) {
        this.gerenteService = gerenteService;
    }

    public Gerente createGerente(Gerente gerente) {
        return gerenteService.createGerente(gerente);
    }

    public List<Gerente> readGerente() {
        return gerenteService.readGerente();
    }

    public boolean updateGerente(Gerente gerente) {
        return gerenteService.updateGerente(gerente);
    }

    public boolean deleteGerente(Long id) {
        return gerenteService.deleteGerente(id);
    }

    public Gerente findById(Long id) {
        return gerenteService.findById(id);
    }

    public boolean findByLoginAndPassword(String login, String senha) {
        return gerenteService.findByLoginAndPassword(login, senha);
    }

    public Caixa createCaixa(String nome, String CPF, String telefone, String cargo, String login, String senha){
        return gerenteService.createCaixa(nome, CPF, telefone, cargo, login, senha);
    }

    public Produto createProduto(String nome, double preco, String tipo, int quantidade) {
        return gerenteService.createProduto(nome, preco, tipo, quantidade);
    }

    public boolean updateCaixa(Long id, String nome, String CPF, String telefone, String cargo, String login, String senha){
        return gerenteService.updateCaixa(id, nome, CPF, telefone, cargo, login, senha);
    }

    public boolean deleteCaixa(Long id){
        return gerenteService.deleteCaixa(id);
    }

}
