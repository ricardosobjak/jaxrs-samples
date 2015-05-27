package br.edu.utfpr.jaxrs.biblioteca.application;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import br.edu.utfpr.jaxrs.biblioteca.resources.BibliotecaResource;


@ApplicationPath("/rest")
public class JaxRsApplication extends Application {
	private final Set<Class<?>> classes;
	
	public JaxRsApplication() {
		HashSet<Class<?>> c = new HashSet<Class<?>>();
		
		// Adicione aqui todas as classe que são recursos na aplicação
		c.add(BibliotecaResource.class);
		
		classes = Collections.unmodifiableSet(c);
	}
	
	public Set<Class<?>> getClasses() {
		return classes;
	}
}