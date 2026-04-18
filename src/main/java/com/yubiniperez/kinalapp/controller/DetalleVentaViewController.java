package com.yubiniperez.kinalapp.controller;

import com.yubiniperez.kinalapp.entity.DetalleVenta;
import com.yubiniperez.kinalapp.service.DetalleVentaService;
import com.yubiniperez.kinalapp.service.ProductoService;
import com.yubiniperez.kinalapp.service.VentaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping ("/vistas/detalles")
public class DetalleVentaViewController {

    private final DetalleVentaService detalleVentaService;
    private final VentaService ventaService;
    private final ProductoService productoService;

    public DetalleVentaViewController(DetalleVentaService detalleVentaService, VentaService ventaService, ProductoService productoService) {
        this.detalleVentaService = detalleVentaService;
        this.ventaService = ventaService;
        this.productoService = productoService;
    }

    @GetMapping
    public String listar(@RequestParam(name = "busqueda", required = false) String busqueda, Model model){
        List<DetalleVenta> lista;
        
        if (busqueda != null && !busqueda.trim().isEmpty()){
            try {
                Long id = Long.parseLong(busqueda);
                lista = detalleVentaService.buscarPorId(id).map(List::of).orElse(List.of());
            } catch (NumberFormatException e) {
                lista = detalleVentaService.listarDetalleVenta();
            }
        } else {
            lista = detalleVentaService.listarDetalleVenta();
        }
        
        model.addAttribute("detalles", lista);
        model.addAttribute("titulo", "Detalles de Venta");
        return "pages/detalles";
    }

    @GetMapping("/nuevo")
    public String nuevo(Model model){
        model.addAttribute("detalle", new DetalleVenta());
        model.addAttribute("ventas", ventaService.listarVentas());
        model.addAttribute("productos", productoService.listarProductos());
        model.addAttribute("titulo", "Agregar Detalle a Venta");
        return "pages/form/detalle-form";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute("detalle") DetalleVenta detalle){
        detalleVentaService.crear(detalle);
        return "redirect:/vistas/detalles";
    }
}
