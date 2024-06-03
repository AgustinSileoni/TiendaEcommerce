package com.agustin.sileoni.TiendaEcommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agustin.sileoni.TiendaEcommerce.model.Usuario;
import com.agustin.sileoni.TiendaEcommerce.service.IUsuarioService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;



@Controller
@RequestMapping("/usuario")
public class UsuarioController {

    private final Logger logger = LoggerFactory.getLogger(UsuarioController.class);

    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("")
    public String getMethodName() {
        return "usuario/registro";
    }
    
    @GetMapping("/login")
    public String login() {
        return "usuario/login";
    }
    
     @GetMapping("/registro")
     public String create() {
        return "usuario/registro";
    }
    
    @PostMapping("/save")
    public String save(Usuario usuario) {
        usuario.setTipo("USER");
        usuarioService.save(usuario);
        logger.info("Usuario registrado {}",usuario);
        return "redirect:/";
    }
    


}
