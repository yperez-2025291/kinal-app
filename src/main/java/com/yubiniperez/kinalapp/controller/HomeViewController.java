package com.yubiniperez.kinalapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeViewController {

    @GetMapping ("/")
    public String index(Model model) {
        model.addAttribute("titulo", "Bienvenido a Kinal App");
        model.addAttribute("mensaje", "Sistema de Gestión Integral");

        model.addAttribute("totalClientes", 120);
        model.addAttribute("totalProductos", 450);
        model.addAttribute("totalProveedores", 15);
        model.addAttribute("totalVentas", 890);
        model.addAttribute("totalEmpleados", 25);
        return "index";
    }
}
