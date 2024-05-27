package com.agustin.sileoni.TiendaEcommerce.service;

import java.util.List;
import java.util.Optional;


import com.agustin.sileoni.TiendaEcommerce.model.DetalleOrden;

public interface IDetalleOrdenService{
    public DetalleOrden save(DetalleOrden detalleOrden);
    public Optional<DetalleOrden> get(Integer id);
    public List<DetalleOrden> findAll();
    public void update(DetalleOrden orden);
    public void delete(Integer id);   
}
