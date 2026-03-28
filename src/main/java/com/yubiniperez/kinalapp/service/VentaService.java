package com.yubiniperez.kinalapp.service;

import com.yubiniperez.kinalapp.entity.Venta;
import com.yubiniperez.kinalapp.repository.ClienteRepository;
import com.yubiniperez.kinalapp.repository.UsuarioRepository;
import com.yubiniperez.kinalapp.repository.VentaRepository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VentaService implements IVentaService{

    private final VentaRepository ventaRepository;
    private final ClienteRepository clienteRepository;
    private final UsuarioRepository usuarioRepository;

    public VentaService(VentaRepository ventaRepository, ClienteRepository clienteRepository, UsuarioRepository usuarioRepository) {
        this.ventaRepository = ventaRepository;
        this.clienteRepository = clienteRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public List<Venta> listarVentas() {
        return ventaRepository.findAll();
    }

    @Override
    public Optional<Venta> buscarPorId(Long id) {
        return ventaRepository.findById(id);
    }

    @Override
    public Venta crear(Venta venta) {
        validarVenta(venta);
        if (venta.getEstado() == null) {
            venta.setEstado(1);
        }
        return ventaRepository.save(venta);
    }

    @Override
    public Venta actualizar(Long id, Venta venta) {
        if (!ventaRepository.existsById(id)){
            throw new RuntimeException("Venta de id "+id+" no fue encontrada");
        }
        venta.setCodigoVenta(id);
        validarVenta(venta);
        return ventaRepository.save(venta);
    }

    @Override
    public void eliminar(Long id) {
        if (!ventaRepository.existsById(id)){
            throw new RuntimeException("Venta de id "+id+"no fue encontrada");
        }
        ventaRepository.deleteById(id);
    }

    @Override
    public List<Venta> listarActivos() {
        return ventaRepository.findByEstado(1);
    }

    @Override
    public boolean existePorId(Long id) {
        return ventaRepository.existsById(id);
    }

    private void validarVenta(Venta venta){
        if (venta.getFechaVenta() == null){
            throw new IllegalArgumentException(("La fecha de la venta es un dato obligatorio"));
        }

        if (venta.getTotal() == null){
            throw new IllegalArgumentException(("El total del producto es un dato obligatorio"));
        }

        if (venta.getCliente() == null ||  !clienteRepository.existsById(venta.getCliente().getDPICliente())){
            throw new IllegalArgumentException(("El cliente no existe"));
        }

        if (venta.getUsuario() == null || !usuarioRepository.existsById(venta.getUsuario().getCodigoUsuario())){
            throw new IllegalArgumentException(("El usuario no existe"));
        }
    }
}
