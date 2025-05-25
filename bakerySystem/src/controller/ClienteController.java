package controller;

import model.bean.Cliente;
import service.ClienteService;

import java.util.List;

public class ClienteController {
    private final ClienteService clienteService;

    public ClienteController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    public List<Cliente> readCliente() {
        return clienteService.readCliente();
    }

    public Cliente findById(Long id) {
        return clienteService.findById(id);
    }

}
