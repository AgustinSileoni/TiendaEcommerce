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
import com.agustin.sileoni.TiendaEcommerce.model.Usuario;
import com.agustin.sileoni.TiendaEcommerce.service.IProductoService;
import com.agustin.sileoni.TiendaEcommerce.service.IUsuarioService;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;





@Controller
@RequestMapping("/")
public class HomeController {

    private final Logger log = LoggerFactory.getLogger(HomeController.class);

    List<DetalleOrden> listaDetallesOrden = new ArrayList<DetalleOrden>();
    
    Orden orden = new Orden();

    @Autowired
    private IProductoService productoService;
    @Autowired
    private IUsuarioService usuarioService;

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("productos", productoService.findAll());
        model.addAttribute("cart", listaDetallesOrden);
        model.addAttribute("orden",orden);
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
    
    @PostMapping("cart")
    public String addCart(@RequestParam("id") Integer id, @RequestParam("cantidad") Integer cantidad, Model model) {
        
        Producto producto = productoService.get(id).get();
        Integer idProducto = producto.getIdProducto();
        boolean ingresado = listaDetallesOrden.stream().anyMatch(p -> p.getProducto().getIdProducto() == idProducto);

        if(ingresado){
            for (DetalleOrden detalleOrden: listaDetallesOrden){
                if (detalleOrden.getProducto().getIdProducto()==id){
                    detalleOrden.setCantidad(detalleOrden.getCantidad()+cantidad);
                    detalleOrden.setTotal(detalleOrden.getTotal()+(cantidad)*detalleOrden.getPrecio());
                    break;
                }
            }
        }
        else{
            DetalleOrden detalleOrden = new DetalleOrden(producto,cantidad);
            listaDetallesOrden.add(detalleOrden);
        }

        double sumaTotal = 0;
        sumaTotal = listaDetallesOrden.stream().mapToDouble(dt-> dt.getTotal()).sum();
        orden.setTotal(sumaTotal);

        model.addAttribute("cart", listaDetallesOrden);
        model.addAttribute("orden", orden);

        return "usuario/carrito";
    }

    @GetMapping("delete/{id}")
    public String deleteProductoCart(@PathVariable("id") Integer idProducto, Model model) {
        
        List<DetalleOrden> ordenesNuevas =  new ArrayList<DetalleOrden>();

        for(DetalleOrden detalleOrden:listaDetallesOrden){
            if(detalleOrden.getProducto().getIdProducto()!=idProducto){
                ordenesNuevas.add(detalleOrden);
            }
        }

        listaDetallesOrden = ordenesNuevas;
        double sumaTotal = 0;
        sumaTotal = listaDetallesOrden.stream().mapToDouble(dt->dt.getTotal()).sum();
        orden.setTotal(sumaTotal);
        model.addAttribute("cart", listaDetallesOrden);
        model.addAttribute("orden",orden);

        return "redirect:/getCart";
    }
    
    @GetMapping("/getCart")
    public String getCart(Model model){
        model.addAttribute("cart", listaDetallesOrden);
        model.addAttribute("orden", orden);
        return "/usuario/carrito";
    }

    @GetMapping("/order")
    public String order(Model model) {

        Usuario usuario = usuarioService.get(3).get();

        model.addAttribute("cart", listaDetallesOrden);
        model.addAttribute("orden", orden);
        model.addAttribute("usuario", usuario);
    
        return "usuario/resumenorden";
    }


}
