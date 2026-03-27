package com.yubiniperez.kinalapp.service;

import com.yubiniperez.kinalapp.entity.Producto;

import java.util.List;
import java.util.Optional;

public interface IProductoService {

    List<Producto> listarProductos();

    Optional<Producto> buscarPorId(Long id);

    Producto crear(Producto producto);

    Producto actualizar(Long id, Producto producto);

    void eliminar(Long id);

    List<Producto> listarActivos();

    boolean existePorId(Long id);
}
