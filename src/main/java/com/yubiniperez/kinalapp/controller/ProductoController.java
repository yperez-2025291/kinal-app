package com.yubiniperez.kinalapp.controller;

import com.yubiniperez.kinalapp.entity.Producto;
import com.yubiniperez.kinalapp.service.IProductoService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    private final IProductoService productoService;

    public ProductoController(IProductoService productoService) {
        this.productoService = productoService;
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos(){
        List<Producto> productos = productoService.listarProductos();
        return ResponseEntity.ok(productos);
    }

    @GetMapping ("/{id}")
    public ResponseEntity<Producto> buscarPorId(@PathVariable Long id){
        return productoService.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> crearProducto(@RequestBody Producto producto){
        try {
            Producto nuevoProducto = productoService.crear(producto);
            return new ResponseEntity<>(nuevoProducto, HttpStatus.CREATED);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping ("/{id}")
    public ResponseEntity<?> actualizarProducto(@PathVariable Long id, @RequestBody Producto producto){
        try {
            if (!productoService.existePorId(id)){
                return ResponseEntity.notFound().build();
            }
            Producto productoActualizado = productoService.actualizar(id, producto);
            return ResponseEntity.ok(productoActualizado);
        }catch (IllegalArgumentException e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping ("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id){
        try {
            if (!productoService.existePorId(id)){
                return ResponseEntity.notFound().build();
            }
            productoService.eliminar(id);
            return ResponseEntity.noContent().build();
        }catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping ("/activos")
    public ResponseEntity<List<Producto>> productosActivos(){
        List<Producto> productosActivos = productoService.listarActivos();
        return ResponseEntity.ok(productosActivos);
    }

}
