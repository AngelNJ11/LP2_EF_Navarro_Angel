package com.example.demo.entity;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tb_usuario")
public class UsuarioEntity {

	@Id
	@Column(name = "correo")
	private String correo;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "nom_usuario")
	private String nomUsu;
	
	@Column(name = "ape_usuario")
	private String apeUsu;
	
	
	@Column(name = "fecha_nac")
	@Temporal(TemporalType.DATE)
	private LocalDate fecha;
	
	@Column(name = "foto_perfil")
	private String urlImagen;
	
}
