package com.example.demo.service.impl;

import java.util.List;

import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.entity.ProductoEntity;


public interface ProductoServiceImpl {

	
	void crearProducto(ProductoEntity productoEntity,Model  model, MultipartFile foto);
	List<ProductoEntity>buscarTodosProductos();
	ProductoEntity buscarProductoPorId(Integer id);
}
