package com.yubiniperez.kinalapp.service;

import com.yubiniperez.kinalapp.entity.DetalleVenta;

import java.util.List;
import java.util.Optional;

public interface IDetalleVentaService {
    List<DetalleVenta> listarDetalleVenta();

    Optional<DetalleVenta> buscarPorId(Long id);

    DetalleVenta crear(DetalleVenta detalleVenta);

    DetalleVenta actualizar(Long id, DetalleVenta detalleVenta);

    void eliminar(Long id);

    boolean existePorId(Long id);
}
