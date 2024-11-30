package com.example.literatura.repository;

import com.example.literatura.model.Autor;
import com.example.literatura.model.Libro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface LibroRepository extends JpaRepository<Libro,Long> {
    @Query("SELECT a FROM Autor a")
    List<Autor> mostrarAutores();

    @Query("SELECT a FROM Autor a WHERE :fecha BETWEEN a.fechaDeNacimiento AND a.fechaDeMuerte")
    List<Autor> mostrarAutorPorAño(String fecha);

    @Query("SELECT l FROM Libro l JOIN l.idioma i WHERE i = :idioma")
    List<Libro> findByIdioma(@Param("idioma") String idioma);
//    @Query("SELECT l FROM Libro l WHERE l.idioma ILIKE %:idioma%")
//    List<Libro> mostrarLibrosPorIdioma(String idioma);
//List<Libro> findByIdiomaContainsIgnoreCase(String idioma);


}
