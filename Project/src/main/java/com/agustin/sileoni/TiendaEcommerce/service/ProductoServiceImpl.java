package com.agustin.sileoni.TiendaEcommerce.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.agustin.sileoni.TiendaEcommerce.model.Producto;
import com.agustin.sileoni.TiendaEcommerce.repository.ProductoRepository;

public class ProductoServiceImpl implements ProductoService{

    @Autowired
    private ProductoRepository productoRepository;

    @Override
    public Producto save(Producto producto) {
        return productoRepository.save(producto);
    }

    @Override
    public Optional<Producto> get(Integer id) {
        return productoRepository.findById(id);
    }

    @Override
    public void update(Producto producto) {
        productoRepository.save(producto);
    }

    @Override
    public void delete(Integer id) {
        productoRepository.deleteById(id);
    }
    
}
