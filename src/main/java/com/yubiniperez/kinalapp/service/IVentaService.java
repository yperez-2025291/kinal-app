package com.yubiniperez.kinalapp.service;

import com.yubiniperez.kinalapp.entity.Venta;

import java.util.List;
import java.util.Optional;

public interface IVentaService {
    List<Venta> listarVentas();

    Optional<Venta> buscarPorId(Long id);

    Venta crear(Venta venta);

    Venta actualizar(Long id, Venta venta);

    void eliminar(Long id);

    List<Venta> listarActivos();

    boolean existePorId(Long id);
}
