package com.example.literatura;

import com.example.literatura.dto.DatosLibro;
import com.example.literatura.dto.LibroDTO;
import com.example.literatura.model.Libro;
import com.example.literatura.repository.LibroRepository;
import com.example.literatura.service.ConvierteDatos;
import com.example.literatura.service.ObtenerApi;

import java.util.List;
import java.util.Optional;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Principal {
    private ObtenerApi obtenerApi = new ObtenerApi();
    private ConvierteDatos convierteDatos = new ConvierteDatos();
    private final String URL= "https://gutendex.com/books/";
    private Scanner escritura = new Scanner(System.in);
    private LibroRepository repository;
    private List<Libro> libros;

    public Principal(LibroRepository repository) {
        this.repository = repository;
    }

    private String menu =
            """
            Elija una opcion a traves de su numero:
            1 - Buscar libro por titulo
            2 - Listar libros registrados
            3 - Listar autores registrados
            4 - Listar autores vivos en un determinado año
            5 - Listar libros por idioma 
            0 -salir
            """;
    private String menuDeIdiomas =
            """
             Ingrese el idioma para buscar los libros
             es- Español
             en- Ingles
             fr- Frances
             pt- Portugues       
            """;

    public void muestraOpciones(){
        var opcion = -1;
        while (opcion!=0){
            System.out.println(menu);
            opcion = escritura.nextInt();
            escritura.nextLine();

            switch (opcion){
                case 1 -> buscarLibro();
                case 2 -> mostrarLibrosRegistrados();
                case 3 -> mostrarAutoresRegistrados();
                case 4 -> mostrarAutoresPorAnio();
                case 5 -> mostrarLibrosPorIdioma();
            }
        }
    }




    private void buscarLibro() {
        System.out.println("Ingrese el nombre del libro que desea buscar.");
        var nombreDelLibro= escritura.nextLine();
        var json= obtenerApi.obtenerJson(URL+ "?search="+nombreDelLibro.replace(" ","+"));
         var convierteBusqueda= convierteDatos.obtenerDatos(json,LibroDTO.class);
        Optional<DatosLibro> libroBuscado = convierteBusqueda.libros().stream()
                .filter(t-> t.titulo().toUpperCase().contains(nombreDelLibro.toUpperCase()))
                .findFirst();
        if (libroBuscado.isPresent()){
            Libro libro = new Libro(libroBuscado.get());
            repository.save(libro);
            System.out.println(libro.toString());

        }else {
            System.out.println("No se encontro el libro");
        }

    }

    private void mostrarLibrosRegistrados() {
         libros = repository.findAll();
        libros.forEach(System.out::println);
    }

    private void mostrarAutoresRegistrados() {
        var autores= repository.mostrarAutores();
                autores.forEach(System.out::println);
    }

    private void mostrarAutoresPorAnio() {
        System.out.println("Ingrese el año que desea buscar");
        var año = escritura.nextLine();
        var dato = repository.mostrarAutorPorAño(año);
        System.out.println(dato);
    }
    private void mostrarLibrosPorIdioma() {
        System.out.println(menuDeIdiomas);
        String idioma = escritura.nextLine().trim();
        if (idioma.isEmpty()) {
            System.out.println("Por favor, ingrese un idioma válido.");
            return;
        }

        try {
             libros = repository.findByIdioma(idioma);
        } catch (Exception e) {
            System.out.println("Ocurrió un error al buscar los libros: " + e.getMessage());
            return;
        }

       if (libros.isEmpty()){
           System.out.println("No se encontro libros con ese idioma");
       }else {
           libros.forEach(System.out::println);

           System.out.println(libros.size());
       }
    }
}
