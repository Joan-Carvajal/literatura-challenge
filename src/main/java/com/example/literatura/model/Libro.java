package com.example.literatura.model;

import com.example.literatura.dto.AutorDTO;
import com.example.literatura.dto.DatosLibro;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;
@Entity
@Table(name="libros")
public class Libro {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String titulo;
    @ElementCollection
    private List<String> idioma;

    private Integer cantidaDeDescargas;


    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JoinColumn(name = "autor_id")
    private Autor autor;

    public Libro(DatosLibro datosLibro) {
    this.titulo= datosLibro.titulo();
    this.idioma = datosLibro.idioma();
    this.autor= new Autor(datosLibro.autor().get(0));
    this.cantidaDeDescargas = datosLibro.cantidaDeDescargas();
    }
    public  Libro(){};

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }





    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public Integer getCantidaDeDescargas() {
        return cantidaDeDescargas;
    }

    public void setCantidaDeDescargas(Integer cantidaDeDescargas) {
        this.cantidaDeDescargas = cantidaDeDescargas;
    }

    @Override
    public String toString() {
        return
                "------------ Libro ------------"+
                "\ntitulo='" + titulo + '\'' +
                ",\nautor= " + autor.getNombre() +'\''+
                ",\nidioma= " + idioma +'\''+
                ",\ncantida De Descargas= " + cantidaDeDescargas +'\''+
                        "\n \n-------------------------"
                ;
    }
}
