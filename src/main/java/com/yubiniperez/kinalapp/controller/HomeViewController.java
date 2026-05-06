package com.yubiniperez.kinalapp.controller;

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

    private ClienteService clienteService;
    private ProductoService productoService;
    private UsuarioService usuarioService;
    private VentaService ventaService;
    private DetalleVentaService detalleVentaService;

    public HomeViewController(ClienteService clienteService, ProductoService productoService, UsuarioService usuarioService, VentaService ventaService, DetalleVentaService detalleVentaService) {
        this.clienteService = clienteService;
        this.productoService = productoService;
        this.usuarioService = usuarioService;
        this.ventaService = ventaService;
        this.detalleVentaService = detalleVentaService;
    }


    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("titulo", "Bienvenido a la base de datos de Kinal App");
        model.addAttribute("mensaje", "Administracion del sistema de Control de Ventas e Inventario");
        
        model.addAttribute("totalClientes", clienteService.listarClientes().size());
        model.addAttribute("totalProductos", productoService.listarProductos().size());
        model.addAttribute("totalUsuarios", usuarioService.listarUsuarios().size());
        model.addAttribute("totalVentas", ventaService.listarVentas().size());
        model.addAttribute("totalDetalles", detalleVentaService.listarDetalleVenta().size());
        
        return "index";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/index")
    public String home(Model model) {
        return index(model);
    }
}
