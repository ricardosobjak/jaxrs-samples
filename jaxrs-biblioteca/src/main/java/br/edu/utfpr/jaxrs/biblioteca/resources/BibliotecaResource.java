package br.edu.utfpr.jaxrs.biblioteca.resources;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import br.edu.utfpr.jaxrs.biblioteca.dao.LivroDAO;
import br.edu.utfpr.jaxrs.biblioteca.model.Livro;

//Identificação do recurso
@Path("/biblioteca")
public class BibliotecaResource {
	
	@Context
	UriInfo uriInfo;
	
	@Context
	Request request;
	
	// Retorna uma lista de livros para o browser
	// http://{Contexto do servidor}/{Aplicação}/rest/biblioteca
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Livro> getLivros() {
		List<Livro> livros = new ArrayList<>();
		livros.addAll(LivroDAO.instance.getBanco().values());
		return livros;
	}
	
	// Retorna uma lista de livros para aplica��es
	// http://{Contexto do servidor}/{Aplica��o}/rest/biblioteca
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Livro> getLivrosApplicacao() {
		return getLivros();
	}
	
	@POST
	//@Produces(MediaType.TEXT_HTML)
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Response novoLivro(
		@FormParam(value = "id") String id,
		@FormParam(value = "titulo") String nome, 
		@FormParam(value = "autor") String autor,
		@FormParam(value = "isbn") String isbn,
		@Context HttpServletResponse response) throws IOException, URISyntaxException {
		
		Livro livro = new Livro(id, nome, autor, isbn);
		LivroDAO.instance.getBanco().put(id, livro);
		
		// Caso queira redirecionar a resposta
		//response.sendRedirect("../criar_livro.html");
		
		// Na resposta à criação do livro nós enviamos o status HTTP 201 (Created),
		// colocando no header Location a URI do novo livro.
		return Response.created(new URI(uriInfo.getAbsolutePath() + "/"+ String.valueOf(livro.getId()))).build();
	}
	
	@GET
	@Path("/count")
	@Produces(MediaType.TEXT_PLAIN)
	public String getCount() {
		return String.valueOf( LivroDAO.instance.getBanco().size() );
	}
	
	@Path("{livro}")
	public LivroResource getLivro(@PathParam("livro") String id) {
		return new LivroResource(uriInfo, request, id);
	}
}