package com.agustin.sileoni.TiendaEcommerce.controller;

import java.io.IOException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.agustin.sileoni.TiendaEcommerce.model.Producto;
import com.agustin.sileoni.TiendaEcommerce.model.Usuario;
import com.agustin.sileoni.TiendaEcommerce.service.ProductoServiceImpl;
import com.agustin.sileoni.TiendaEcommerce.service.UploadFileService;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;







@Controller
@RequestMapping("/productos")
public class ProductoController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(ProductoController.class);

    @Autowired
    private ProductoServiceImpl productoService;

    @Autowired
    private UploadFileService uploadFileService;

    //El objeto Model lleva informacion desde el backend hacia la vista
    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("productos", productoService.findAll());
        return "productos/show";
    }

    @GetMapping("/create")
    public String create() {
        return "productos/create";
    }
    
    //@RequesParam trae el valor de la etiqueta llamada img
    @PostMapping("/save")
    public String save(Producto producto, @RequestParam("img") MultipartFile file) throws IOException {
        //LOGGER.info("Este es el objeto producto {}", producto);
        Usuario user = new Usuario(2,"","","","","","","");
        
        producto.setUsuario(user);
        saveImageInProduct(producto, file);
        productoService.save(producto);

        return "redirect:/productos";
    }

    //El ("id") es para que al compilarse se mantenga el nombre del argumento
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, Model model) {
        Producto producto = new Producto();
        Optional<Producto> optionalProducto = productoService.get(id);
        producto = optionalProducto.get();
        LOGGER.info("Producto buscado : {}", producto);
        model.addAttribute("producto", producto);

        return "productos/edit";
    }
    
    @PostMapping("/update")
    public String update(Producto producto,@RequestParam("img") MultipartFile file) throws IOException {
        Producto productOld = productoService.get(producto.getIdProducto()).get();
        producto.setUsuario(productOld.getUsuario());
        String imageToUpdateFromProduct = productOld.getImagen();
        if(file.isEmpty()){
            producto.setImagen(imageToUpdateFromProduct);
        }
        else{
            uploadFileService.delete(imageToUpdateFromProduct);
            saveImageInProduct(producto, file);
        }

        
        productoService.update(producto);
        return "redirect:/productos";        
    }

    @GetMapping("delete/{id}")
    public String delete(@PathVariable("id") Integer idProducto) {
        String imageFromProductToDelete = productoService.get(idProducto).get().getImagen();
         if (!isDefaultImage(imageFromProductToDelete)) {
             uploadFileService.delete(imageFromProductToDelete);
         }
        productoService.delete(idProducto);
        return"redirect:/productos";
    }

    
    private void saveImageInProduct(Producto producto , MultipartFile imageToSave) throws IOException{
        String nombreImagen = uploadFileService.saveImage(imageToSave);        
        producto.setImagen(nombreImagen);
    }

    private boolean isDefaultImage(String nameImage){
        return nameImage.equals("default.jpg");
    }

}
