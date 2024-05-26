package com.agustin.sileoni.TiendaEcommerce.service;

import java.util.List;
import java.util.Optional;

import com.agustin.sileoni.TiendaEcommerce.model.Producto;

public interface IProductoService {
    public Producto save(Producto producto);
    public Optional<Producto> get(Integer id);
    public List<Producto> findAll();
    public void update(Producto producto);
    public void delete(Integer id);    
}
