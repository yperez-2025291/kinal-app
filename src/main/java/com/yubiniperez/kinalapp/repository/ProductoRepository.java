package com.yubiniperez.kinalapp.repository;

import com.yubiniperez.kinalapp.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
    List<Producto> findByEstado(int estado);
}
