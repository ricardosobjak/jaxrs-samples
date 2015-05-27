package br.edu.utfpr.jaxrs.biblioteca.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement(name="livro")
@XmlType(propOrder={"id","nome","autor","isbn"})
@XmlAccessorType(XmlAccessType.FIELD)
public class Livro {
	private String id = "";
	private String nome = "";
	private String autor = "";
	private String isbn = "";
	
	public Livro() { }

	public Livro(String id, String nome, String autor, String isbn) {
		super();
		this.id = id;
		this.nome = nome;
		this.autor = autor;
		this.isbn = isbn;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
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
}
