package com.agustin.sileoni.TiendaEcommerce.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agustin.sileoni.TiendaEcommerce.service.IUsuarioService;
import org.springframework.web.bind.annotation.GetMapping;


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
    public String create() {
        return "usuario/login";
    }
    
    // @PostMapping("/save")
    // public String saveUsuario(Usuario usuario) {

    //     usuario.setTipo("USER");
    //     logger.info("Usuario registro: {}", usuario);
    //     usuarioService.save(usuario);

    //     return "redirect:/";
    // }
    


}
