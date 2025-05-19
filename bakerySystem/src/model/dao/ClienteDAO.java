/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.dao;

import java.util.List;
import model.bean.Cliente;

/**
 *
 * @author jonat
 */
public interface ClienteDAO {
    Cliente create(Cliente cliente);
    List<Cliente> read();
    boolean update(Cliente cliente);
    boolean delete(Long id);
    Cliente findById(Long idCliente);
    Cliente findByName(String nomeCliente);
}
