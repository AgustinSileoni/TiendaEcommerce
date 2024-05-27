package com.agustin.sileoni.TiendaEcommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.agustin.sileoni.TiendaEcommerce.model.Orden;

@Repository
public interface IOrdenRepository extends JpaRepository<Orden,Integer>{
    
}
