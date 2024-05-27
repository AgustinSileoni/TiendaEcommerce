package com.agustin.sileoni.TiendaEcommerce.service;

import java.util.Optional;
import java.util.List;

import com.agustin.sileoni.TiendaEcommerce.model.Orden;


public interface IOrdenService {
    public Orden save(Orden orden);
    public Optional<Orden> get(Integer id);
    public List<Orden> findAll();
    public void update(Orden orden);
    public void delete(Integer id);   
}
