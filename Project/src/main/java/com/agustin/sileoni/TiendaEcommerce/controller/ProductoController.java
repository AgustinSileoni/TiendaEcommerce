package com.agustin.sileoni.TiendaEcommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agustin.sileoni.TiendaEcommerce.model.Producto;
import com.agustin.sileoni.TiendaEcommerce.model.Usuario;
import com.agustin.sileoni.TiendaEcommerce.service.ProductoServiceImpl;






@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoServiceImpl productoService;

    @GetMapping("")
    public String show() {
        return "productos/show";
    }

    @GetMapping("/create")
    public String create() {
        return "productos/create";
    }
    
    @PostMapping("/save")
    public String save(Producto producto) {
        LOGGER.info("Este es el objeto producto {}", producto);
        Usuario user = new Usuario(1,"","","","","","","");
        producto.setUsuario(user);
        productoService.save(producto);
        return "redirect:/productos";
    }
    



}
