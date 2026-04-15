package com.yubiniperez.kinalapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.yubiniperez.kinalapp.service.ClienteService;
import com.yubiniperez.kinalapp.service.ProductoService;
import com.yubiniperez.kinalapp.service.UsuarioService;
import com.yubiniperez.kinalapp.service.VentaService;
import com.yubiniperez.kinalapp.service.DetalleVentaService;

@Controller
public class HomeViewController {

    @Autowired private ClienteService clienteService;
    @Autowired private ProductoService productoService;
    @Autowired private UsuarioService usuarioService;
    @Autowired private VentaService ventaService;
    @Autowired private DetalleVentaService detalleVentaService;

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("titulo", "Bienvenido a Kinal App");
        model.addAttribute("mensaje", "Sistema de Control de Ventas e Inventario");
        
        // Contadores basados en tus 5 servicios cargados
        model.addAttribute("totalClientes", clienteService.listarClientes().size());
        model.addAttribute("totalProductos", productoService.listarProductos().size());
        model.addAttribute("totalUsuarios", usuarioService.listarUsuarios().size());
        model.addAttribute("totalVentas", ventaService.listarVentas().size());
        model.addAttribute("totalDetalles", detalleVentaService.listarDetalleVenta().size());
        
        return "index";
    }
}
