package com.agustin.sileoni.TiendaEcommerce.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agustin.sileoni.TiendaEcommerce.model.Usuario;
import com.agustin.sileoni.TiendaEcommerce.service.IUsuarioService;

import jakarta.servlet.http.HttpSession;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;





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

    @PostMapping("/acceder")
    public String postMethodName(Usuario usuarioIngresado, HttpSession httpSession) {
        
        Optional<Usuario> usuarioDataBase = usuarioService.findByCorreo(usuarioIngresado.getEmail());
        
        logger.info("Accesos : {}" , usuarioDataBase);

        if(usuarioDataBase.isPresent()){
            httpSession.setAttribute("idUsuario", usuarioDataBase.get().getIdUsuario());
            if(usuarioDataBase.get().getTipo().equals("ADMIN")){
                return "redirect:/administrador";
            }
            else{
                return "redirect:/";
            }
        }
        else{
            logger.info("El usuario ingresado no existe");
        }

        return "redirect:/usuario/login";
    }
    

    @GetMapping("/logout")
    public String logout(HttpSession httpSession) {
        httpSession.removeAttribute("idUsuario");

        return "redirect:/usuario/login";
    }
    
    
    

}
