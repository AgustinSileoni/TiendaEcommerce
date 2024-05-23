package com.agustin.sileoni.TiendaEcommerce.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.agustin.sileoni.TiendaEcommerce.model.DetalleOrden;
import com.agustin.sileoni.TiendaEcommerce.model.Orden;
import com.agustin.sileoni.TiendaEcommerce.model.Producto;
import com.agustin.sileoni.TiendaEcommerce.service.ProductoService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;





@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    List<DetalleOrden> detallesOrden = new ArrayList<DetalleOrden>();
    
    Orden orden = new Orden();

    @Autowired
    private ProductoService productoService;

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "usuario/home";
    }

    @GetMapping("productoHome/{id}")
    public String productoHome(@PathVariable("id") Integer id, Model model) {
        log.info("Id producto enviado como parametro {}", id);
        Optional<Producto> productoOptional = productoService.get(id);
        Producto producto = productoOptional.get();
        model.addAttribute("producto", producto);
        return "usuario/productohome";
    }
    
    @PostMapping("/cart")
    public String addCart(@RequestParam("id") Integer id, @RequestParam("cantidad") Integer cantidad, Model model) {
        DetalleOrden detalleOrden = new DetalleOrden();
        Producto producto = productoService.get(id).get();
        double sumaTotal = 0;

        detalleOrden.setCantidad(cantidad);
        detalleOrden.setNombre(producto.getNombre());
        detalleOrden.setPrecio(producto.getPrecio());
        detalleOrden.setTotal(producto.getPrecio() * cantidad);
        detalleOrden.setProducto(producto);
        
        detallesOrden.add(detalleOrden);

        sumaTotal = detallesOrden.stream().mapToDouble(dt-> dt.getTotal()).sum();
        orden.setTotal(sumaTotal);

        model.addAttribute("cart", detalleOrden);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }
    
    
}
