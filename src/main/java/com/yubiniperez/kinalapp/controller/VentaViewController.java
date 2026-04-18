package com.yubiniperez.kinalapp.controller;

import com.yubiniperez.kinalapp.entity.Venta;
import com.yubiniperez.kinalapp.service.ClienteService;
import com.yubiniperez.kinalapp.service.UsuarioService;
import com.yubiniperez.kinalapp.service.VentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/vistas/ventas")
public class VentaViewController {

    private final VentaService ventaService;
    private final ClienteService clienteService;
    private final UsuarioService usuarioService;

    public VentaViewController(VentaService ventaService, ClienteService clienteService, UsuarioService usuarioService) {
        this.ventaService = ventaService;
        this.clienteService = clienteService;
        this.usuarioService = usuarioService;
    }

    @GetMapping
    public String listar(@RequestParam(name = "activos", required = false, defaultValue = "false") boolean activos,
                        @RequestParam(name = "busqueda", required = false) Long busqueda,
                        Model model) {
        List<Venta> lista;
        
        if (busqueda != null) {
            lista = ventaService.buscarPorId(busqueda)
                    .map(List::of)
                    .orElse(List.of());
        } else if (activos) {
            lista = ventaService.listarActivos();
        } else {
            lista = ventaService.listarVentas();
        }
        
        model.addAttribute("ventas", lista);
        model.addAttribute("soloActivos", activos);
        model.addAttribute("titulo", "Gestión de Ventas");
        return "pages/ventas";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Venta venta = new Venta();
        venta.setEstado(1);
        model.addAttribute("venta", venta);
        model.addAttribute("clientes", clienteService.listarClientes());
        model.addAttribute("usuarios", usuarioService.listarUsuarios());
        model.addAttribute("titulo", "Registrar Nueva Venta");
        return "pages/form/venta-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("venta") Venta venta) {
        ventaService.crear(venta);
        return "redirect:/vistas/ventas";
    }
}