/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.dao;

import java.util.List;
import model.bean.Caixa;



/**
 *
 * @author jonat
 */
public interface CaixaDAO {
    
    Caixa create(Caixa caixa);
    List<Caixa> read();
    boolean update(Caixa caixa);
    boolean delete(Long id);
    Caixa findById(Long id);
}
