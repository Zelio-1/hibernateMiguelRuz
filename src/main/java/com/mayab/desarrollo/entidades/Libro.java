package com.mayab.desarrollo.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="libros")
public class Libro {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id", unique = true, nullable = false)
	private int id;
	@Column(name = "nombre", nullable = false, length = 100)
	private String nombre;
	@Column(name = "autor", nullable = false, length = 100)
	private String autor;
	@Column(name = "isbn", nullable = false, length = 100) 
	private String isbn;
	@Column(name = "editorial", nullable = false, length = 100) 
	private String editorial;
	
	
	public Libro(int id, String nombre, String autor, String isbn, String editorial) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.autor = autor;
		this.isbn = isbn;
		this.editorial = editorial; 
	}
	
	public Libro() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public String toString() {
		return "Libro [id=" + id + ", nombre=" + nombre + ", autor=" + autor + ", isbn=" + isbn + ", editorial="
				+ editorial + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getEditorial() {
		return editorial;
	}

	public void setEditorial(String editorial) {
		this.editorial = editorial;
	}
	
}
