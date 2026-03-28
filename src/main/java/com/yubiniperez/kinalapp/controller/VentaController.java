package com.yubiniperez.kinalapp.controller;

import com.yubiniperez.kinalapp.entity.Venta;
import com.yubiniperez.kinalapp.service.IVentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping ("/ventas")
public class VentaController {

    private final IVentaService ventaService;

    public VentaController(IVentaService ventaService) {
        this.ventaService = ventaService;
    }

    @GetMapping
    public ResponseEntity<List<Venta>> listarVentas(){
        List<Venta> ventas = ventaService.listarVentas();
        return ResponseEntity.ok(ventas);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Venta> buscarPorId(@PathVariable Long id){
        return ventaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crearVenta(@RequestBody Venta venta){
        try {
            Venta nuevaVenta = ventaService.crear(venta);
            return new ResponseEntity<>(nuevaVenta, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> actualizarVenta(@PathVariable Long id, @RequestBody Venta venta){
        try {
            if (!ventaService.existePorId(id)){
                return ResponseEntity.notFound().build();
            }
            Venta ventaActualizada = ventaService.actualizar(id, venta);
            return ResponseEntity.ok(ventaActualizada);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> eliminarVenta(@PathVariable Long id){
        try {
            if (!ventaService.existePorId(id)){
                return ResponseEntity.notFound().build();
            }
            ventaService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping ("/activos")
    public ResponseEntity <List<Venta>> ventasActivas(){
        List<Venta> ventasActivas = ventaService.listarActivos();
        return ResponseEntity.ok(ventasActivas);
    }
}
