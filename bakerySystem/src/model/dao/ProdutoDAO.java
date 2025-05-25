/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.dao;

import java.util.List;
import model.bean.Produto;

/**
 *
 * @author jonat
 */
public interface ProdutoDAO {
    Produto create(Produto produto);
    List<Produto> read();
    boolean update(Produto produto);
    boolean delete(Long id);
    Produto findById(Long idProduto);   

    @Override
    public String toString();
    
}
