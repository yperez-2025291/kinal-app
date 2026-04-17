package com.yubiniperez.kinalapp.controller;

import com.yubiniperez.kinalapp.entity.Cliente;
import com.yubiniperez.kinalapp.service.ClienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping ("/vistas/clientes")
public class ClienteViewController {

    private final ClienteService clienteService;

    public ClienteViewController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @GetMapping
    public String listar(@RequestParam(name = "busqueda", required = false) String busqueda,
                @RequestParam(name = "activos", required = false, defaultValue = "false") boolean activos,
                Model model){
        List<Cliente> lista;

        if (busqueda != null && !busqueda.trim().isEmpty()){
        lista = new ArrayList<>();
        Optional<Cliente> opcional = clienteService.buscarPorDPI(busqueda);
        if (opcional.isPresent()) {
            lista.add(opcional.get());
        }
        } else if (activos){
        lista = clienteService.listarActivos();
        } else {
        lista = clienteService.listarClientes();
        }
        model.addAttribute("clientes", lista);
        model.addAttribute("soloActivos", activos);
        return "pages/clientes";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        Cliente cliente = new Cliente();
        cliente.setEstado(1);
        model.addAttribute("cliente", cliente);
        model.addAttribute("titulo", "Nuevo Cliente");
        return "pages/form/clientes-form";
    }

    @GetMapping("/editar/{dpi}")
    public String editar(@PathVariable("dpi") String dpi, Model model){
        Optional<Cliente> opcional = clienteService.buscarPorDPI(dpi);
        if (opcional.isPresent()){
        model.addAttribute("cliente", opcional.get());
        }
        model.addAttribute("titulo", "Editar Cliente");
        return "pages/form/clientes-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("cliente") Cliente cliente){
        if (clienteService.existePorDPI(cliente.getDPICliente())) {
        clienteService.actualizar(cliente.getDPICliente(), cliente);
        } else {
        clienteService.guardar(cliente);
        }
        return "redirect:/vistas/clientes";
    }

    @GetMapping("/eliminar/{dpi}")
    public String eliminar(@PathVariable("dpi") String dpi){
        try {
        clienteService.eliminar(dpi);
        } catch (Exception e) {
        return "redirect:/vistas/clientes?error=fk";
        }
        return "redirect:/vistas/clientes";
    }
}