package com.agustin.sileoni.TiendaEcommerce.service;

import java.util.Optional;

import com.agustin.sileoni.TiendaEcommerce.model.Producto;

public interface ProductoService {
    public Producto save(Producto producto);
    public Optional<Producto> get(Integer id);
    public void update(Producto producto);
    public void delete(Integer id);
    
}
