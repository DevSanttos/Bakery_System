/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package model.dao;

import java.util.List;
import model.bean.Gerente;

/**
 *
 * @author jonat
 */
public interface GerenteDAO {
    Gerente create(Gerente gerente);
    List<Gerente> read();
    boolean update(Gerente gerente);
    boolean delete(Long id);
    Gerente findById(Long id);
    
}
