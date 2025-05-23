package controller;

import service.CaixaService;

public class CaixaController {
    private final CaixaService caixaService;

    public CaixaController(CaixaService service, CaixaService caixaService) {
        this.caixaService = caixaService;
    }


}
