package br.edu.utfpr.pessoa.resources;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
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

import org.glassfish.jersey.media.multipart.FormDataContentDisposition;
import org.glassfish.jersey.media.multipart.FormDataParam;

import br.edu.utfpr.pessoa.dao.EMF;
import br.edu.utfpr.pessoa.dao.PessoaDAO;
import br.edu.utfpr.pessoa.helper.DateUtil;
import br.edu.utfpr.pessoa.model.Pessoa;

//Identificação do recurso
@Path("/pessoas")
public class PessoasResource {
	
	@Context
	UriInfo uriInfo;
	
	@Context
	Request request;
	
	private PessoaDAO pessoaDAO;
	
	@PostConstruct
	private void init() {
		pessoaDAO = new PessoaDAO(EMF.createEntityManager());
	}
	
	// Retorna uma lista de pessoas para o browser
	// http://{Contexto do servidor}/{Aplicação}/rest/pessoas
	@GET
	@Produces(MediaType.TEXT_XML)
	public List<Pessoa> getPessoas() {
		return pessoaDAO.findAll();
	}
	
	// Retorna uma lista de pessoas para aplicações
	// http://{Contexto do servidor}/{Aplicação}/rest/pessoas
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public List<Pessoa> getPessoasApplicacao() {
		return getPessoas();
	}
	
	
	@POST
	//@Produces(MediaType.TEXT_HTML)
	@Consumes("multipart/form-data")
	public Response add(
		@FormDataParam("nome") String nome, 
		@FormDataParam("nascimento") String nascimento,
		@FormDataParam("telefone") String telefone,
		@FormDataParam("foto") InputStream inputStream,
		@FormDataParam("foto") FormDataContentDisposition fileDisposition,
		@Context HttpServletResponse response) throws IOException, URISyntaxException {
		
		/* Lendo os bytes da foto */
		
		// Objeto para armazenar os bytes recebidos
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		
		int read = 0; // Controlar a quantidade de bytes recebidos em cada leitura
		byte[] bytes = new byte[1024]; // Array para a leitura parcial da foto
		
		while((read=inputStream.read(bytes)) != -1) {
			baos.write(bytes);
		}
		
		Pessoa pessoa = new Pessoa();
		pessoa.setNome(nome);
		pessoa.setTelefone(telefone);
		
		pessoa.setFoto(baos.toByteArray());
		
		try {
			pessoa.setDataNascimento(DateUtil.parse(nascimento));
		} catch (ParseException | NullPointerException e) {
			System.out.println(getClass().getSimpleName() + " add: Falha ao convertar data de nascimento da Pessoa");
			pessoa.setDataNascimento(null);
		}

		pessoaDAO.save(pessoa);
		
		//response.sendRedirect("../criar_pessoa.html");
		//return Response.ok("Pessoa cadastrada com sucesso!").build();
		
		// Na resposta à criação do usuário nós enviamos o status HTTP 201 (Created),
		// colocando no header Location a URI do novo usuário.
		return Response.created(new URI(uriInfo.getAbsolutePath() + "/"+ String.valueOf(pessoa.getId()))).build();
	}
	
	
	@GET
	@Path("/count")
	@Produces(MediaType.TEXT_PLAIN)
	public long getCount() {
		return pessoaDAO.count();
	}
	
	
	
	@Path("{pessoa}")
	public PessoaResource getPessoa(@PathParam("pessoa") int id) {
		return new PessoaResource(uriInfo, request, id);
	}
}