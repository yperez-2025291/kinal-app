package com.yubiniperez.kinalapp.controller;

import com.yubiniperez.kinalapp.entity.Cliente;
import com.yubiniperez.kinalapp.service.IClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/vistas/clientes")
public class ClienteViewController {

    private final IClienteService clienteService;

    public ClienteViewController(IClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("clientes", clienteService.listarClientes());
        return "pages/clientes";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        Cliente cliente = new Cliente();
        cliente.setEstado(1); 
        model.addAttribute("cliente", cliente);
        model.addAttribute("titulo", "Nuevo Cliente");
        return "pages/cliente-form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") String dpi, Model model) {
        clienteService.buscarPorDPI(dpi).ifPresent(c -> model.addAttribute("cliente", c));
        model.addAttribute("titulo", "Editar Cliente");
        return "pages/cliente-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("cliente") Cliente cliente) {
        if (cliente.getEstado() == null) cliente.setEstado(1);
        clienteService.guardar(cliente);
        return "redirect:/vistas/clientes";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") String dpi) {
        try {
            clienteService.eliminar(dpi);
        } catch (Exception e) {
            return "redirect:/vistas/clientes?error=fk";
        }
        return "redirect:/vistas/clientes";
    }
}
