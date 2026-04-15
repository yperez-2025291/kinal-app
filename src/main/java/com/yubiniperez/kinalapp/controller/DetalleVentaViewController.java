package com.yubiniperez.kinalapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yubiniperez.kinalapp.service.IDetalleVentaService;

@Controller
@RequestMapping ("/vista/detalles")
public class DetalleVentaViewController {
    
    private final IDetalleVentaService detalleVentaService;

    public DetalleVentaViewController(IDetalleVentaService detalleVentaService) {
        this.detalleVentaService = detalleVentaService;
    }

    @GetMapping
    public String listar (Model model){
        model.addAttribute("detalles", detalleVentaService.listarDetalleVenta());
        return "pages/detalles";
    }
}
