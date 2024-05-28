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
        return "usuario/registrousuario";
    }
    

    @GetMapping("/registro")
    public String create() {
        return "redirect:/usuario";
    }
    
    @PostMapping("/save")
    public String saveUsuario(Usuario usuario) {

        usuario.setTipo("USER");
        logger.info("Usuario registro: {}", usuario);
        usuarioService.save(usuario);

        return "redirect:/";
    }
    


}
