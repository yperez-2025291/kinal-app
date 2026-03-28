package com.yubiniperez.kinalapp.service;

import com.yubiniperez.kinalapp.entity.DetalleVenta;
import com.yubiniperez.kinalapp.repository.DetalleVentaRepository;
import com.yubiniperez.kinalapp.repository.ProductoRepository;
import com.yubiniperez.kinalapp.repository.VentaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class DetalleVentaService implements IDetalleVentaService{

    private final DetalleVentaRepository detalleVentaRepository;
    private final ProductoRepository productoRepository;
    private final VentaRepository ventaRepository;

    public DetalleVentaService(DetalleVentaRepository detalleVentaRepository, ProductoRepository productoRepository, VentaRepository ventaRepository) {
        this.detalleVentaRepository = detalleVentaRepository;
        this.productoRepository = productoRepository;
        this.ventaRepository = ventaRepository;
    }

    @Override
    public List<DetalleVenta> listarDetalleVenta() {
        return detalleVentaRepository.findAll();
    }

    @Override
    @Transactional (readOnly = true)
    public Optional<DetalleVenta> buscarPorId(Long id) {
        return detalleVentaRepository.findById(id);
    }

    @Override
    public DetalleVenta crear(DetalleVenta detalleVenta) {
        validarDetalleVenta(detalleVenta);
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public DetalleVenta actualizar(Long id, DetalleVenta detalleVenta) {
        if (!detalleVentaRepository.existsById(id)){
            throw new RuntimeException("Detalle de venta con id "+id+" no fue encontrado");
        }
        detalleVenta.setCodigoDetalleVenta(id);
        validarDetalleVenta(detalleVenta);
        return detalleVentaRepository.save(detalleVenta);
    }

    @Override
    public void eliminar(Long id) {
        if (!detalleVentaRepository.existsById(id)){
            throw new RuntimeException("Detalle de venta no encontrado con id: "+id);
        }
        detalleVentaRepository.deleteById(id);
    }

    @Override
    public boolean existePorId(Long id) {
        return detalleVentaRepository.existsById(id);
    }

    private void validarDetalleVenta(DetalleVenta detalleVenta){
        if (detalleVenta.getCantidad() == null || detalleVenta.getCantidad() <= 0){
            throw new IllegalArgumentException("La cantidad debe ser mayor a 0");
        }

        if (detalleVenta.getPrecioUnitario() == null){
            throw new IllegalArgumentException("El precio unitario es obligatorio");
        }

        if (detalleVenta.getSubtotal() == null){
            throw new IllegalArgumentException("El subtotal es obligatorio");
        }

        if (detalleVenta.getProducto() == null || !productoRepository.existsById(detalleVenta.getProducto().getCodigoProducto())){
            throw new IllegalArgumentException("El producto no existe");
        }

        if (detalleVenta.getVenta() == null || !ventaRepository.existsById(detalleVenta.getVenta().getCodigoVenta())){
            throw new IllegalArgumentException("La venta no existe");
        }
    }
}
