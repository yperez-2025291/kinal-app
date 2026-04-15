package com.yubiniperez.kinalapp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yubiniperez.kinalapp.entity.Producto;
import com.yubiniperez.kinalapp.service.IProductoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;



@Controller
@RequestMapping ("/vistas/productos")
public class ProductoViewController {
    
    private final IProductoService productoService;
    

    public ProductoViewController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos", productoService.listarProductos());
        return "pages/productos";
    }

    @GetMapping ("/nuevo")
    public String nuevo(Model model) {
        Producto producto = new Producto();
        producto.setEstado(1);
        model.addAttribute("producto", producto);
        model.addAttribute("titulo", "Nuevo Producto");
        return "pages/producto-form";
    }
    
    @GetMapping("/editar/{id}")
    public String editar(@PathVariable("id") Long id, Model model) {
        productoService.buscarPorId(id).ifPresent(p -> model.addAttribute("producto", p));
        model.addAttribute("titulo", "Editar Producto");
        return "pages/producto-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("producto") Producto producto) {
        if (producto.getEstado() == null) producto.setEstado(1);
        // Usamos el método crear/actualizar según corresponda
        if (producto.getCodigoProducto() == null) {
            productoService.crear(producto);
        } else {
            productoService.actualizar(producto.getCodigoProducto(), producto);
        }
        return "redirect:/vistas/productos";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable("id") Long id) {
        try {
            productoService.eliminar(id);
        } catch (Exception e) {
            return "redirect:/vistas/productos?error=fk";
        }
        return "redirect:/vistas/productos";
    }
}
