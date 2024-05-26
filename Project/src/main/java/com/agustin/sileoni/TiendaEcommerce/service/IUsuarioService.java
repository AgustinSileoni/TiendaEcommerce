package com.agustin.sileoni.TiendaEcommerce.service;

import java.util.List;
import java.util.Optional;

import com.agustin.sileoni.TiendaEcommerce.model.Usuario;

public interface IUsuarioService {
    public Usuario save(Usuario usuario);
    public Optional<Usuario> get(Integer id);
    public List<Usuario> findAll();
    public void update(Usuario usuario);
    public void delete(Integer id);

}
