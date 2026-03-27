package com.yubiniperez.kinalapp.repository;

import com.yubiniperez.kinalapp.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
