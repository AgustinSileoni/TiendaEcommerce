package com.agustin.sileoni.TiendaEcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.agustin.sileoni.TiendaEcommerce.model.DetalleOrden;


public interface IDetalleOrdenRepository extends JpaRepository<DetalleOrden,Integer>{
    
}
