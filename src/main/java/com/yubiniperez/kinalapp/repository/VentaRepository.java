package com.yubiniperez.kinalapp.repository;

import com.yubiniperez.kinalapp.entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VentaRepository extends JpaRepository<Venta, Long> {
    List<Venta> FindByEstado(int estado);
}
