package com.yubiniperez.kinalapp.controller;

import com.yubiniperez.kinalapp.entity.Venta;
import com.yubiniperez.kinalapp.service.IVentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
@RequestMapping("/vistas/ventas")
public class VentaViewController {

    private final IVentaService ventaService;

    public VentaViewController(IVentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("ventas", ventaService.listarVentas());
        return "pages/ventas";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Venta venta = new Venta();
        venta.setFechaVenta(LocalDate.now());
        venta.setEstado(1);
        model.addAttribute("venta", venta);
        model.addAttribute("titulo", "Registrar Nueva Venta");
        return "pages/venta-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("venta") Venta venta) {
        ventaService.crear(venta);
        return "redirect:/vistas/ventas";
    }
}