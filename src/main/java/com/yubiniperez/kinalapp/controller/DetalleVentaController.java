package com.yubiniperez.kinalapp.controller;

import com.yubiniperez.kinalapp.entity.DetalleVenta;
import com.yubiniperez.kinalapp.repository.DetalleVentaRepository;
import com.yubiniperez.kinalapp.service.IDetalleVentaService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping ("/detalle-venta")
public class DetalleVentaController {

    private final IDetalleVentaService detalleVentaService;
    private final DetalleVentaRepository detalleVentaRepository;

    public DetalleVentaController(IDetalleVentaService detalleVentaService, DetalleVentaRepository detalleVentaRepository) {
        this.detalleVentaService = detalleVentaService;
        this.detalleVentaRepository = detalleVentaRepository;
    }

    @GetMapping
    public ResponseEntity<List<DetalleVenta>> listar(){
        List<DetalleVenta> detalleDeVenta = detalleVentaService.listarDetalleVenta();
        return ResponseEntity.ok(detalleDeVenta);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<DetalleVenta> buscarPorId(@PathVariable Long id){
        return detalleVentaService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crear(@RequestBody DetalleVenta detalleVenta){
        try {
            DetalleVenta nuevo = detalleVentaService.crear(detalleVenta);
            return new ResponseEntity<>(nuevo, HttpStatus.CREATED);
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> actualizar(@PathVariable Long id, @RequestBody DetalleVenta detalleVenta){
        try {
            if (!detalleVentaRepository.existsById(id)){
                return ResponseEntity.notFound().build();
            }
            DetalleVenta actualizado = detalleVentaService.actualizar(id, detalleVenta);
            return ResponseEntity.notFound().build();
        } catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id){
        try {
            if (!detalleVentaService.existePorId(id)){
                return ResponseEntity.notFound().build();
            }
            detalleVentaService.eliminar(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }
}
