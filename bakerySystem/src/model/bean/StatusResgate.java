/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package model.bean;

/**
 *
 * @author joseo
 */
public enum StatusResgate {
    BOLO_DE_PAO(50),
    CUCA_DE_MORANGO(150),
    BOLO_BRIGADEIRO(125);

    private final int quantPontosNecessarios;

    StatusResgate(int quantPontosNecessarios) {
        this.quantPontosNecessarios = quantPontosNecessarios;
    }

    public int getQuantPontosNecessarios() {
        return quantPontosNecessarios;
    } 
}
