package com.yubiniperez.kinalapp.controller;

import com.yubiniperez.kinalapp.entity.DetalleVenta;
import com.yubiniperez.kinalapp.service.IDetalleVentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vistas/detalles")
public class DetalleVentaViewController {

    private final IDetalleVentaService detalleService;

    public DetalleVentaViewController(IDetalleVentaService detalleService) {
        this.detalleService = detalleService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("detalles", detalleService.listarDetalleVenta());
        return "pages/detalles";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("detalleVenta", new DetalleVenta());
        model.addAttribute("titulo", "Nuevo Detalle de Venta");
        return "pages/detalle-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("detalleVenta") DetalleVenta detalleVenta) {
        detalleService.crear(detalleVenta);
        return "redirect:/vistas/detalles";
    }
}