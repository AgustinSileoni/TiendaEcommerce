package com.agustin.sileoni.TiendaEcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agustin.sileoni.TiendaEcommerce.model.Producto;

@Repository
public interface ProductoRepository extends JpaRepository<Producto,Integer> {


    
}
