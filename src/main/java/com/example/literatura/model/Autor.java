package com.example.literatura.model;

import com.example.literatura.dto.AutorDTO;
import com.fasterxml.jackson.annotation.JsonAlias;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "autor")
public class Autor {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String fechaDeNacimiento;
    private String fechaDeMuerte;
    @OneToOne(mappedBy = "autor",cascade = CascadeType.ALL, orphanRemoval = true)
    private Libro libro;
    public Autor(AutorDTO autorDTO) {
        this.nombre = autorDTO.nombre();
        this.fechaDeNacimiento = autorDTO.fechaDeNacimiento();
        this.fechaDeMuerte = autorDTO.fechaDeMuerte();
    }
    public Autor(){};

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    public String getFechaDeMuerte() {
        return fechaDeMuerte;
    }

    public void setFechaDeMuerte(String fechaDeMuerte) {
        this.fechaDeMuerte = fechaDeMuerte;
    }

    @Override
    public String toString() {
        return
                "\n \n-----------Autor-----------"+
                "\nnombre= '" + nombre + '\'' +
                ",\nfechaDeNacimiento= '" + fechaDeNacimiento + '\'' +
                ",\nfechaDeMuerte= '" + fechaDeMuerte + '\''+
                        "\nlibros= '" + libro.getTitulo() + '\''
                ;
    }
}
