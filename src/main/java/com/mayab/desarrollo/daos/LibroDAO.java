package com.mayab.desarrollo.daos;

//import java.math.BigDecimal;
import java.util.List;

//import javax.management.Query;

import org.hibernate.Session;
import org.hibernate.query.Query;

import com.mayab.desarrollo.entidades.Libro;
import com.mayab.desarrollo.presentacion.HibernateUtil;

public class LibroDAO implements IDAOLibro{

    @Override
    public Libro createLibro (Libro libro) {
        Session session = HibernateUtil.getSessionFactory().openSession(); 
        session.beginTransaction(); 
        
        //int id = (int) 
        session.save(libro); 
        session.getTransaction().commit();
        session.close();
        return libro;  
    }

    @Override
    public Libro obtenerLibro(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession(); 
        //session.beginTransaction(); 
        Libro libro = session.get(Libro.class, id); 
        session.close();
        return libro; 
    }

    @Override
    public boolean modificarLibro(Libro libro, String editorial) {
        Session session = HibernateUtil.getSessionFactory().openSession(); 
        session.beginTransaction(); 

        libro.setEditorial(editorial);
        session.update(libro);
        session.getTransaction().commit();
        session.close();
        return true;  

    }

    @Override
    public void borrarLibro(Libro libro_delete) {
        Session session = HibernateUtil.getSessionFactory().openSession(); 
        session.beginTransaction(); 
        Libro libro = session.get(Libro.class, libro_delete.getId()); 
        session.delete(libro);
        session.getTransaction().commit();
        session.close();
        //System.out.println("El libro con el ISBN: "+libro_delete.getIsbn()+" ha sido borrado");
    }

    @Override
    public List<Libro> obtenerAllLibros() {
        Session session = HibernateUtil.getSessionFactory().openSession(); 
        Query query = session.createQuery("from Libro"); 

        List<Libro> lista_libros = query.getResultList(); 
        
        session.close();
        return lista_libros;
 
    }

    @Override
    public Libro findByISBN(String isbn) {
        Session session = HibernateUtil.getSessionFactory().openSession(); 

        Query query = session.createQuery("from Libro where isbn = :isbn"); 
        query.setParameter("isbn", isbn); 
        Libro libro = (Libro) query.uniqueResult(); 
        
        session.close();
        return libro;

    }

}
