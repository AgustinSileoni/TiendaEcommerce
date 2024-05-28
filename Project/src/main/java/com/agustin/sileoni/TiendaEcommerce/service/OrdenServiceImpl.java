package com.agustin.sileoni.TiendaEcommerce.service;

import java.util.List;
import java.util.Optional;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.agustin.sileoni.TiendaEcommerce.model.Orden;
import com.agustin.sileoni.TiendaEcommerce.repository.IOrdenRepository;

@Service
public class OrdenServiceImpl implements IOrdenService{

    @Autowired
    private IOrdenRepository ordenRepository;

    @Override
    public Orden save(Orden orden) {
        return ordenRepository.save(orden);
    }

    @Override
    public Optional<Orden> get(Integer id) {
        return ordenRepository.findById(id);
    }

    @Override
    public List<Orden> findAll() {
        return ordenRepository.findAll();
    }

    @Override
    public void update(Orden orden) {
        ordenRepository.save(orden);
    }

    @Override
    public void delete(Integer id) {
        ordenRepository.deleteById(id);
    }


    public String generarNumeroOrden(){
        int numero = 0;
        
        List<Orden> ordenes = findAll();
        List<Integer> numeros = new ArrayList<Integer>();

        ordenes.stream().forEach(o -> numeros.add( Integer.parseInt(o.getNumero())));

        if(ordenes.isEmpty()){
            numero=1;
        }
        else{
            numero= numeros.stream().max(Integer::compare).get();
            numero++;
        }

        String numeroString = String.valueOf(numero);
        while (numeroString.length() < 10) {
            numeroString = "0"+numeroString;
        }

        return numeroString;

    }
    
}
