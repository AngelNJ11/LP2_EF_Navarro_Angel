package com.example.demo.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "tb_producto")
public class ProductoEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer idProducto;
	
	@Column(name = "Nombre")
	private String nombre;
	
	@Column(name = "Precio")
	private Double precio;
	
	@Column(name = "Stock")
	private Integer stock;
	
	@Column(name = "Foto")
	private String urlImagen;
	
	@ManyToOne
	@JoinColumn(name = "Id_Categoria" , nullable = false)
	private CategoriaEntity idCategoria;

	
}
