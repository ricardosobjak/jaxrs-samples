package br.edu.utfpr.jaxrs.biblioteca.dao;

import java.util.HashMap;
import java.util.Map;

import br.edu.utfpr.jaxrs.biblioteca.model.Livro;

public enum LivroDAO {
	instance;
	
	private Map<String, Livro> banco = new HashMap<String, Livro>();
	
	private LivroDAO() {
		Livro l1 = new Livro("1", "RESTful Web Services", "Leonard R.", "1234");
		Livro l2 = new Livro("2", "JavaEE", "Juca Silva", "1222");
		Livro l3 = new Livro("3", "Receitas da Ofélia", "LTD", "4444");
		
		banco.put(l1.getId(), l1);
		banco.put(l2.getId(), l2);
		banco.put(l3.getId(), l3);
	}
	
	public Map<String, Livro> getBanco() {
		return banco;
	}
}