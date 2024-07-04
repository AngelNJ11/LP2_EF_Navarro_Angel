package com.example.demo.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.CategoriaEntity;
import com.example.demo.entity.ProductoEntity;
import com.example.demo.repository.CategoriaRepository;
import com.example.demo.repository.ProductoRepository;
import com.example.demo.utils.Utilitarios;

import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;



@Controller
public class ProductoController {


	@Autowired
	private ProductoRepository productoRepository;
	
    @Autowired
    private CategoriaRepository categoriaRepository;
	
    @GetMapping("/menu")
	public String listaProducto(Model model, HttpSession session) {
		List<ProductoEntity> listaProducto = productoRepository.findAll();
		model.addAttribute("listaProducto", listaProducto);
		return "listaProducto";
	}
	
	@GetMapping("/registrarProducto")
	public String createProducto(Model model, HttpSession session) {
		List<CategoriaEntity> categoria = categoriaRepository.findAll();
		model.addAttribute("categorias", categoria);
		model.addAttribute("producto", new ProductoEntity());
		return "registrarProducto";
	}
	
	@PostMapping("/registrarProducto")
	public String registrarProducto(@ModelAttribute ProductoEntity producto,
			@RequestParam("imagen") MultipartFile file, Model model, HttpSession session) {
	    producto.setUrlImagen(file.getOriginalFilename());
	    ProductoEntity pro = productoRepository.save(producto);
	    if (pro != null) {
	    	try {
	    		File saveFile = new ClassPathResource("static/producto_foto").getFile();
	            Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
	            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
			} catch (Exception e) {
				// Manejo de excepción
			}
	    } 
	    model.addAttribute("successMessage", "El registro fue guardado exitosamente.");
	    List<CategoriaEntity> categoriaBebida = categoriaRepository.findAll();
	    model.addAttribute("categorias", categoriaBebida);
	    model.addAttribute("producto", new ProductoEntity());
	    return "registrarProducto";
	}

	@GetMapping("/editarProducto/{idProducto}")
	public String editarProducto(Model model, @PathVariable("idProducto") Integer idProducto, HttpSession session) {
		
	    ProductoEntity pro = productoRepository.findById(idProducto).orElse(null);
	    List<CategoriaEntity> categoriaPro = categoriaRepository.findAll();
	    model.addAttribute("producto", pro);
	    model.addAttribute("categorias", categoriaPro);	    
	    return "editarProducto";
	}
	
	@PostMapping("/guardarEdicionProducto")
	@Transactional
	public String guardarEdicionBebida(@ModelAttribute ProductoEntity pro, @RequestParam("imagen") MultipartFile file, HttpSession session) throws IOException {
	    try {
	        if (!file.isEmpty()) {
	            ProductoEntity producexsis = productoRepository.findById(pro.getIdProducto()).orElse(null);
	            if (producexsis != null && producexsis.getUrlImagen() != null) {
	                Utilitarios.eliminarImagenPro("producto_imagem", producexsis.getUrlImagen());
	            }
	            File saveFile = new ClassPathResource("static/bebida_imagen").getFile();
	            Path path = Paths.get(saveFile.getAbsolutePath() + File.separator + file.getOriginalFilename());
	            Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
	            pro.setUrlImagen(file.getOriginalFilename());     
	        } else {
	        	ProductoEntity producexsis = productoRepository.findById(pro.getIdProducto()).orElse(null);
	            if (producexsis != null) {
	                pro.setUrlImagen(producexsis.getUrlImagen());
	            }
	        }
	        productoRepository.save(pro);
	    } catch (IOException e) {
	    	// Manejo de excepción
	    }
	    return "redirect:/listarProducto";
	}
	
	@GetMapping("/detallePedido/{idProducto}")
	public String verProducto(Model model, @PathVariable("idProducto") Integer idBebida, HttpSession session) {
		ProductoEntity proenco = productoRepository.findById(idBebida).get();
		model.addAttribute("producto", proenco);
		return "detalleProducto";
	}
	
	@GetMapping("/eliminarBebida/{idProducto}")
	public String eliminarBebida(Model model, @PathVariable("idProducto") Integer idBebida, HttpSession session) {
		productoRepository.deleteById(idBebida);
		return "redirect:/listarBebida";
	}
	
}
