package com.mayab.desarrollo.daos;

import java.util.List;

import com.mayab.desarrollo.entidades.Libro;

public interface IDAOLibro {
    public Libro createLibro (Libro libro); 
    public Libro obtenerLibro (int id); 
    public boolean modificarLibro (Libro libro, String editorial); 
    public void borrarLibro (Libro libro); 
    public List <Libro> obtenerAllLibros(); 
    public Libro findByISBN(String isbn); 
}
