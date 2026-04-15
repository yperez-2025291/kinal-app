package com.yubiniperez.kinalapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.yubiniperez.kinalapp.service.IVentaService;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
@RequestMapping ("/vistas/ventas")
public class VentaViewController {

    private final IVentaService ventaService;

    public VentaViewController(IVentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ventas", ventaService.listarVentas());
        model.addAttribute("titulo", "historial de ventas");
        return "pages/ventas";
    }
    
}
