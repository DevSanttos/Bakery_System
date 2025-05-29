package controller;

import model.bean.Caixa;
import model.bean.Cliente;
import service.CaixaService;

import java.util.List;

public class CaixaController {
    private final CaixaService caixaService;

    public CaixaController(CaixaService caixaService) {
        this.caixaService = caixaService;
    }

    public List<Caixa> readCaixa() {
        return caixaService.readCaixa();
    }

    public Caixa findById(Long id){
        return caixaService.findById(id);
    }

    public boolean findByLoginAndPassword(String login, String senha) {
        return caixaService.findByLoginAndPassword(login, senha);
    }

    public Cliente createCliente (String nome, String cpf, String telefone) {
        return caixaService.createCliente(nome, cpf, telefone);
    }
    
    public boolean realizarResgatePorPontos(Long idProduto, Long idCliente, int quantidade) {
        if (idProduto == null || idProduto <= 0) {
            throw new IllegalArgumentException("O ID do produto não pode ser null ou negativo");
        }

        if (idCliente == null || idCliente <= 0) {
            throw new IllegalArgumentException("O ID do cliente não pode ser null ou negativo");
        }
        return caixaService.realizarResgatePorPontos(idProduto, idCliente, quantidade);
    }

    public boolean updateCliente (Long id, String nome, String CPF, String telefone) {
        return caixaService.updateCliente(id, nome, CPF, telefone);
    }
}
