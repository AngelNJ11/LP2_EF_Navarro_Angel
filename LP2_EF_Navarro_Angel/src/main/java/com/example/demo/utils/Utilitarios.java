package com.example.demo.utils;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.multipart.MultipartFile;


public class Utilitarios {

	public static String guardarImagen(MultipartFile foto) {
		
		try {
			
			Path pathDire = Paths.get("src/main/resources/static/usuario_foto/");
			if(!Files.exists(pathDire)) {
				Files.createDirectories(pathDire);
			}
			
			byte[] bytes = foto.getBytes();
			Path path = Paths.get("src/main/resources/static/usuario_foto/"
			+ foto.getOriginalFilename());
			
			Files.write(path, bytes);
			return foto.getOriginalFilename();
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
	
	public static String extraerHash(String passwordInput) {
		return BCrypt.hashpw(passwordInput, BCrypt.gensalt());		
	}
	
	public static boolean checkPassword(String passwordInput, String hashPassword) {
		return BCrypt.checkpw(passwordInput, hashPassword);
	}
	
	public static String guardarImagenPro(MultipartFile foto, String subDirectorio) {
        try {
            Path pathDire = Paths.get("src/main/resources/static/" + subDirectorio + "/");
            if (!Files.exists(pathDire)) {
                Files.createDirectories(pathDire);
            }
            byte[] bytes = foto.getBytes();
            Path path = Paths.get(pathDire.toString(), foto.getOriginalFilename());	            
            Files.write(path, bytes);
            return foto.getOriginalFilename();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;	
        }
    }
  public static void eliminarImagenPro(String subDirectorio, String nombreImagen) {
        try {
            Path path = Paths.get("src/main/resources/static/" + subDirectorio + "/" + nombreImagen);
            Files.deleteIfExists(path);
        } catch (Exception e) {
            System.out.println("Error al eliminar la imagen: " + e.getMessage());
        }
    }
	
}
