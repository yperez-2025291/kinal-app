package com.yubiniperez.kinalapp.service;

import com.yubiniperez.kinalapp.entity.Producto;
import com.yubiniperez.kinalapp.repository.ProductoRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductoService implements IProductoService{

    private final ProductoRepository productoRepository;

    public ProductoService(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public List<Producto> listarProductos() {
        return productoRepository.findAll();
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<Producto> buscarPorId(Long id) {
        return  productoRepository.findById(id);
    }

    @Override
    public Producto crear(Producto producto) {
        validarProducto(producto);
        if (producto.getEstado() == 0){
            producto.setEstado(1);
        }
        return productoRepository.save(producto);
    }

    @Override
    public Producto actualizar(Long id, Producto producto) {
        if (!productoRepository.existsById(id)){
            throw new RuntimeException("El producto con el id: "+id+" no se encontro");
        }
        producto.setCodigoProducto(id);
        validarProducto(producto);
        return productoRepository.save(producto);
    }

    @Override
    public void eliminar(Long id) {
        if (!productoRepository.existsById(id)){
            throw new RuntimeException("El producto de id "+ id +"no se encontro");
        }
        productoRepository.deleteById(id);
    }

    @Override
    public List<Producto> listarActivos() {
        return productoRepository.findByEstado(1);
    }

    @Override
    public boolean existePorId(Long id) {
        return productoRepository.existsById(id);
    }

    private void validarProducto (Producto producto){
        if (producto.getNombreProducto() == null || producto.getNombreProducto().trim().isEmpty()){
            throw new IllegalArgumentException(("El nombre del producto es un dato obligatorio"));
        }

        if (producto.getPrecio() == null || producto.getPrecio().compareTo(BigDecimal.ZERO) <= 0){
            throw new IllegalArgumentException(("El precio del producto debe ser mayor a 0"));
        }

        if (producto.getStock() < 0){
            throw new IllegalArgumentException(("El stock del producto no puede ser un numero negativo"));
        }

    }
}
