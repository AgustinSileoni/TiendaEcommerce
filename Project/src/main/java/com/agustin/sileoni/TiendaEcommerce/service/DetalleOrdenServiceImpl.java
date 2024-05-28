package com.agustin.sileoni.TiendaEcommerce.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agustin.sileoni.TiendaEcommerce.model.DetalleOrden;
import com.agustin.sileoni.TiendaEcommerce.repository.IDetalleOrdenRepository;

@Service
public class DetalleOrdenServiceImpl implements IDetalleOrdenService{

    @Autowired
    private IDetalleOrdenRepository detalleOrdenRepository;

    @Override
    public DetalleOrden save(DetalleOrden detalleOrden) {
        return detalleOrdenRepository.save(detalleOrden);
    }

    @Override
    public Optional<DetalleOrden> get(Integer id) {
        return detalleOrdenRepository.findById(id);
    }

    @Override
    public List<DetalleOrden> findAll() {
        return detalleOrdenRepository.findAll();
    }

    @Override
    public void update(DetalleOrden orden) {
        detalleOrdenRepository.save(orden);
    }

    @Override
    public void delete(Integer id) {
        detalleOrdenRepository.deleteById(id);
    }
    
}
