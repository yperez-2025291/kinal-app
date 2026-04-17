package com.yubiniperez.kinalapp.controller;

import com.yubiniperez.kinalapp.entity.Producto;
import com.yubiniperez.kinalapp.service.ProductoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping ("/vistas/productos")
public class ProductoViewController {

    private final ProductoService productoService;

    public ProductoViewController(ProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String listar(@RequestParam(name = "busqueda", required = false) Long busqueda,
                @RequestParam(name = "activos", required = false, defaultValue = "false") boolean activos,
                Model model) {
        List<Producto> lista;
        if (busqueda != null) {
        lista = new ArrayList<>();
        Optional<Producto> opcional = productoService.buscarPorId(busqueda);
        if (opcional.isPresent()) {
            lista.add(opcional.get());
        }
        } else if (activos) {
        lista = productoService.listarActivos();
        } else {
        lista = productoService.listarProductos();
        }
        model.addAttribute("productos", lista);
        model.addAttribute("soloActivos", activos);
        return "pages/productos";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        Producto producto = new Producto();
        producto.setEstado(1);
        model.addAttribute("producto", producto);
        model.addAttribute("titulo", "Nuevo Producto");
        return "pages/form/productos-form";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        Optional<Producto> opcional = productoService.buscarPorId(id);
        if (opcional.isPresent()) {
        model.addAttribute("producto", opcional.get());
        }
        model.addAttribute("titulo", "Editar Producto");
        return "pages/form/productos-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("producto") Producto producto) {
        if (producto.getCodigoProducto() != null) {
        productoService.actualizar(producto.getCodigoProducto(), producto);
        } else {
        productoService.crear(producto);
        }
        return "redirect:/vistas/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id){
        try {
        productoService.eliminar(id);
        } catch (Exception e) {
        return "redirect:/vistas/productos?error=fk";
        }
        return "redirect:/vistas/productos";
    }
}