package com.agustin.sileoni.TiendaEcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agustin.sileoni.TiendaEcommerce.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Integer> {
    
}
