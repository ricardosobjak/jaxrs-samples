package br.edu.utfpr.jaxrs.biblioteca.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import br.edu.utfpr.jaxrs.biblioteca.dao.LivroDAO;
import br.edu.utfpr.jaxrs.biblioteca.model.Livro;

public class LivroResource {
	@Context
	UriInfo uriInfo;
	
	@Context
	Request request;
	
	String id;
	
	public LivroResource(UriInfo uriInfo, Request request, String id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
	}
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Livro getLivro() {
		Livro l = LivroDAO.instance.getBanco().get(id);
		
		if(l == null) 
			throw new RuntimeException("GET: O livro com id "+id+
					" não foi encontrado!");
		return l;
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putLivro(JAXBElement<Livro> l) {
		Livro newLivro = l.getValue();
		
		return putResponse(newLivro);
	}
	
	@DELETE
	public Response deleteLivro() {
		Livro l = LivroDAO.instance.getBanco().remove(id);
		if(l == null)
			return Response.status(Status.BAD_REQUEST).build();
			//throw new RuntimeException("DELETE: O livro com id "+id+
				//	" não foi encontrado!");
		return Response.ok().build();
	}
	
	private Response putResponse(Livro l) {
		Response res;
		
		if(LivroDAO.instance.getBanco().containsKey(l.getId()))
			res = Response.ok().build();
		else
			res = Response.created(uriInfo.getAbsolutePath()).build();
		
		LivroDAO.instance.getBanco().put(id, l);
		return res;
	}
	
}
