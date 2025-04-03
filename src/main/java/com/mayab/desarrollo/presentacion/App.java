package com.mayab.desarrollo.presentacion;

//import java.util.ArrayList;
import java.util.List;

import com.mayab.desarrollo.daos.IDAOLibro;
import com.mayab.desarrollo.daos.LibroDAO;
import com.mayab.desarrollo.entidades.Libro;
import com.mayab.desarrollo.logica.LoginServicio;

public class App {

	public static void obtenerTablaTotal (){
		IDAOLibro dao_Libro = new LibroDAO(); 
		LoginServicio servicio = new LoginServicio(dao_Libro);
		List <Libro> all_libros = servicio.findAllLibros(); 
		int len_id=String.valueOf(all_libros.get(0).getId()).length(), len_nombre=all_libros.get(0).getNombre().length(), len_autor=all_libros.get(1).getAutor().length(), len_isbn=all_libros.get(1).getIsbn().length(), len_editorial=all_libros.get(0).getEditorial().length();  
        System.out.println("\nTodos los libros: ");
        for (int i=0; i<all_libros.size();i++) {
            if(String.valueOf(all_libros.get(i).getId()).length()>len_id){
                len_id = String.valueOf(all_libros.get(0).getId()).length();  
            }
            if(all_libros.get(i).getNombre().length()>len_nombre){
                len_nombre = all_libros.get(i).getNombre().length(); 
            }
            if(all_libros.get(i).getAutor().length()>len_autor){
                len_autor = all_libros.get(i).getAutor().length(); 
            }
            if(all_libros.get(i).getIsbn().length()>len_isbn){
                len_isbn = all_libros.get(i).getIsbn().length(); 
            }
            if(all_libros.get(i).getEditorial().length()>len_editorial){
                len_editorial = all_libros.get(i).getEditorial().length(); 
            }
        }
        int total_len= len_nombre+len_autor+len_isbn+len_editorial+17; 
        String guion="-"; 
        for (int i = 0; i < total_len; i++) {
            guion=guion +"-"; 
        }
        System.out.println(guion);
        System.out.printf("| %-"+(len_id+1)+"s | %-"+len_nombre+"s | %-"+len_autor+"s | %-"+len_isbn+"s | %-"+len_editorial+"s |%n", "id", "nombre", "autor", "isbn", "editorial");
        System.out.println(guion);
        for(int i=0; i<all_libros.size();i++){
            System.out.printf("| %-"+(len_id+1)+"s | %-"+len_nombre+"s | %-"+len_autor+"s | %-"+len_isbn+"s | %-"+len_editorial+"s |%n",all_libros.get(i).getId(), all_libros.get(i).getNombre(), all_libros.get(i).getAutor(), all_libros.get(i).getIsbn(), all_libros.get(i).getEditorial());
        }
        System.out.println(guion);
	}
	public static void main(String[] args) {
		//dao, servicio 
		IDAOLibro dao_Libro = new LibroDAO(); 
		LoginServicio servicio = new LoginServicio(dao_Libro);
		
		//Creando dos libros:  
		Libro libro = servicio.agregarLibro("ISBN1", "The Lord of the Rings: The Fellowship of the Ring", "J. R. R. Tolkien", "George Allen & Unwin");
        Libro libro_2 = servicio.agregarLibro("ISBN2", "Los 100 dias del plebeyo", "Jaime Lopera", "Planeta");
		Libro libro_3 = servicio.agregarLibro("ISBN3", "The Great Gatsby", "F. Scott Fitzgerald", "Charles Scribner's Sons");
		System.out.println("\n-----------------Creando libros-----------------");
		if (libro != null) {
			System.out.println("[Libro]: El id del libro es: "+ libro.getId());
		} else {
			System.out.println("[Libro]: Ya existe un libro con ese ISBN");
		}
		if (libro_2 != null) {
			System.out.println("[Libro_2]: El id del libro es: "+ libro_2.getId());
		} else {
			System.out.println("[Libro_2]: Ya existe un libro con ese ISBN");
		}
		if (libro_3 != null) {
			System.out.println("[Libro_3]: El id del libro es: "+ libro_3.getId());
		} else {
			System.out.println("[Libro_3]: Ya existe un libro con ese ISBN");
		}
		System.out.println("--------------------------------------------------");
		
		//Buscando un libro en la tabla "libros" por medio de su ISBN: 
		String isbn_busqueda="ISBN1"; 
		Libro libro_found = servicio.findByIsbn(isbn_busqueda);
		System.out.println("\n-----------------Buscando un libro por medio de su ISBN-----------------");
		if(libro_found!=null){
			//Libro libro_found = servicio.findByIsbn(isbn_busqueda);
			int pixeles = String.valueOf(libro_found.getId()).length()+ libro_found.getNombre().length()+ libro_found.getAutor().length()+ libro_found.getIsbn().length()+ libro_found.getEditorial().length()+16; 
			 
			String guion_n="-"; 
			for (int i = 0; i < pixeles; i++) {
				guion_n=guion_n +"-"; 
			}
			
			System.out.println("\nLibro encontrado: ");
			System.out.println(guion_n);
			System.out.printf("| %-"+(String.valueOf(libro_found.getId()).length()+1)+"s | %-"+libro_found.getNombre().length()+"s | %-"+libro_found.getAutor().length()+"s | %-"+libro_found.getIsbn().length()+"s | %-"+libro_found.getEditorial().length()+"s |%n", "id", "nombre", "autor", "isbn", "editorial");
			System.out.println(guion_n);
			System.out.printf("| %-"+(String.valueOf(libro_found.getId()).length()+1)+"s | %-"+libro_found.getNombre().length()+"s | %-"+libro_found.getAutor().length()+"s | %-"+libro_found.getIsbn().length()+"s | %-"+libro_found.getEditorial().length()+"s |%n", libro_found.getId(), libro_found.getNombre(), libro_found.getAutor(), libro_found.getIsbn(), libro_found.getEditorial());
			System.out.println(guion_n);
		}
		else{
			System.out.println("\nEl libro con el ISBN: "+isbn_busqueda+", no existe");
		}
		
		//Obteniendo todos los libros:
		System.out.println("\n----------------Obteniendo todos los libros-----------------");
		obtenerTablaTotal(); // <-- Imprime todos los registros de la tabla "libros". 

		//Eliminando el libro_2: 
        isbn_busqueda="ISBN2"; 
        if(servicio.deleteLibro(isbn_busqueda)==true){
			System.out.println("\n----------------Eliminando el libro_2-----------------");
			System.out.println("El libro con el ISBN: "+isbn_busqueda+" ha sido eliminado");
			System.out.println("\nComprobando que el registro se elimino en la tabla: ");
			obtenerTablaTotal();
		}else{
			System.out.println("\n----------------Eliminando el libro_2-----------------");
			System.out.println("Error en la eliminacion");
			System.out.println("----------------------------------------------------------");
		}
		
		//Actualizando un libro con otros datos:
		String editorial = "Chump Change"; 
		if(servicio.updateLibro(libro_3, editorial)==true){
			System.out.println("\n----------------Modificando el libro_3-----------------");
			System.out.println("El libro ha sido modificado en su campo de editorial");
		}else{
			System.out.println("\n----------------Modificando el libro_3-----------------");
			System.out.println("Error al modificar el libro");
		} 
		System.out.println("-----------------------------------------------------------");

		//Obteniendo un libro por medio de su ID: 
		int _id = libro_3.getId(); 
		libro_found = servicio.findById(_id); //<-- Obteniendo el libro_3 por medio de su ID.
		System.out.println("\n----------------Obteniendo un libro por medio de su ID-----------------");
		if(libro_found!=null){
			//Libro libro_found = servicio.findByIsbn(isbn_busqueda);
			int pixel = String.valueOf(libro_found.getId()).length()+ libro_found.getNombre().length()+ libro_found.getAutor().length()+ libro_found.getIsbn().length()+ libro_found.getEditorial().length()+16; 
			 
			String guion_n="-"; 
			for (int i = 0; i < pixel; i++) {
				guion_n=guion_n +"-"; 
			}
			
			System.out.println("\nLibro encontrado: ");
			System.out.println(guion_n);
			System.out.printf("| %-"+(String.valueOf(libro_found.getId()).length()+1)+"s | %-"+libro_found.getNombre().length()+"s | %-"+libro_found.getAutor().length()+"s | %-"+libro_found.getIsbn().length()+"s | %-"+libro_found.getEditorial().length()+"s |%n", "id", "nombre", "autor", "isbn", "editorial");
			System.out.println(guion_n);
			System.out.printf("| %-"+(String.valueOf(libro_found.getId()).length()+1)+"s | %-"+libro_found.getNombre().length()+"s | %-"+libro_found.getAutor().length()+"s | %-"+libro_found.getIsbn().length()+"s | %-"+libro_found.getEditorial().length()+"s |%n", libro_found.getId(), libro_found.getNombre(), libro_found.getAutor(), libro_found.getIsbn(), libro_found.getEditorial());
			System.out.println(guion_n);
		}
		else{
			System.out.println("\nEl libro con el ID: "+_id+", no existe");
			System.out.println("-----------------------------------------------------------");
		}


		//System.out.println(isOk); <- Para saber si la conexion a la BD fue existosa. 
		
	}
}
/*
 
//Imprimiendo todos los usuarios en la tabla "Usuarios": 
		servicio.updatePass(usuario_1, "otro_pass");
		List<Libro> lista = servicio.findAll(); 
		for(Libro usuario: lista){
			System.out.println(usuario.toString());
		} 
		
		//Eliminando a un usuario con el id: 13: 
		servicio.deleteUser(13);
		lista = servicio.findAll(); 
		for(Libro usuario: lista){
			System.out.println(usuario.toString());
		}*/
