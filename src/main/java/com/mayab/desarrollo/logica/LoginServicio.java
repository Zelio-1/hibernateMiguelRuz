package com.mayab.desarrollo.logica;

import java.util.List;

//import org.hibernate.Session;
//import org.hibernate.query.Query;

import com.mayab.desarrollo.daos.IDAOLibro;
//import com.mayab.desarrollo.daos.LibroDAO;
import com.mayab.desarrollo.entidades.Libro;
//import com.mayab.desarrollo.presentacion.HibernateUtil;

public class LoginServicio {
    private IDAOLibro dao_libro; 
    public LoginServicio (IDAOLibro dao_libro){
        this.dao_libro = dao_libro; 
    }
    public Libro agregarLibro (String isbn, String nombre, String autor, String editorial){
        
        Libro libro = dao_libro.findByISBN(isbn);  
        if (libro==null) {
            libro = new Libro(); 
            libro.setIsbn(isbn);
            libro.setNombre(nombre);
            libro.setAutor(autor);
            libro.setEditorial(editorial); 
            dao_libro.createLibro(libro); 
            
            System.out.println("Libro creado con el ISBN: "+libro.getIsbn());
            
            return libro;
        } else {
            return null; 
        }

    }
    public Libro findByIsbn (String isbn){
        return this.dao_libro.findByISBN(isbn); 
    }
    public List<Libro> findAllLibros(){
        return this.dao_libro.obtenerAllLibros(); 
    }
    public boolean deleteLibro (String isbn){
        this.dao_libro.borrarLibro(dao_libro.findByISBN(isbn));
        return true; 
    }
    public boolean updateLibro(Libro libro, String editorial){
        return this.dao_libro.modificarLibro(libro, editorial); 
    }
    public Libro findById(int id){
        return dao_libro.obtenerLibro(id); 
    }

}
