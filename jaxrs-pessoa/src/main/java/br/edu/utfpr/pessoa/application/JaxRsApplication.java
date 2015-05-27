package br.edu.utfpr.pessoa.application;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import org.glassfish.jersey.filter.LoggingFilter;
import org.glassfish.jersey.media.multipart.MultiPartFeature;

import br.edu.utfpr.pessoa.resources.PessoasResource;


@ApplicationPath("/rest")
public class JaxRsApplication extends Application {
	private final Set<Class<?>> classes;
	
	public JaxRsApplication() {
		super();
		HashSet<Class<?>> c = new HashSet<Class<?>>();
		
		// Recursos adicionados em para funcionamento do FORM MULTIPART
		c.add(MultiPartFeature.class);
        c.add(LoggingFilter.class);
        
        // Classes que são recursos na aplicação
		c.add(PessoasResource.class);
		
		classes = Collections.unmodifiableSet(c);
	}
	
	public Set<Class<?>> getClasses() {
		return classes;
	}
}