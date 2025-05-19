/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.dao;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import model.bean.ItemVenda;
import model.bean.Venda;

/**
 *
 * @author jonat
 */
public interface VendaDAO {
    Venda create(Venda venda);
    List<ItemVenda> loadItensForVenda(Venda venda, Connection connection) throws SQLException;
    List<Venda> read();
    Venda findById(Long idVenda);
    boolean update(Venda venda);
    boolean delete(Long idVenda);
    
    
    
}
