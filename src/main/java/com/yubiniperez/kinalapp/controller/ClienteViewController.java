package com.yubiniperez.kinalapp.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.yubiniperez.kinalapp.entity.Cliente;
import com.yubiniperez.kinalapp.service.ClienteService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@Controller
@RequestMapping ("/clientes")
public class ClienteViewController {
    
    @Autowired
    private ClienteService clienteService;

    @GetMapping
    public String listarClientes (Model model){
        model.addAttribute("titulo", "Listado de Clientes");
        model.addAttribute("clientes", clienteService.listarClientes());
        return "pages/clientes-lista";
    }

    @GetMapping("/nuevo")
    public String nuevoCliente(Model model) {
        model.addAttribute("titulo", "Nuevo cliente");
        model.addAttribute("cliente", new Cliente());
        model.addAttribute("esNuevo", true);
        return "pages/clientes-form";
    }

    @GetMapping("/editar/{dpi}")
    public String formularioEditar(@PathVariable("dpi") String dpi, Model model) {
        Optional<Cliente> cliente = clienteService.buscarPorDPI(dpi);
        if (cliente.isPresent()) {
            model.addAttribute("titulo", "Editar Cliente: " + dpi);
            model.addAttribute("cliente", cliente.get());
            model.addAttribute("esNuevo", false);
            return "pages/clientes-form";
        }
        return "redirect:/clientes";
    }
    
    @PostMapping("/guardar")
    public String guardarCliente(@ModelAttribute("cliente") Cliente cliente, @RequestParam("esNuevo") boolean esNuevo) {
        if (esNuevo) {
            clienteService.guardar(cliente);
        } else {
            clienteService.actualizar(cliente.getDPICliente(), cliente);
        }
        return "redirect:/clientes";
    }
    
    @GetMapping("/eliminar/{dpi}")
    public String eliminar(@PathVariable("dpi") String dpi) {
        clienteService.eliminar(dpi);
        return "redirect:/clientes";
    }
}
