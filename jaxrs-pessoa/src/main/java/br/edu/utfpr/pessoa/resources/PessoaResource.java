package br.edu.utfpr.pessoa.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import javax.xml.bind.JAXBElement;

import br.edu.utfpr.pessoa.dao.EMF;
import br.edu.utfpr.pessoa.dao.PessoaDAO;
import br.edu.utfpr.pessoa.model.Pessoa;

public class PessoaResource {
	@Context
	UriInfo uriInfo;
	
	@Context
	Request request;
	
	private int id;
	
	private PessoaDAO pessoaDAO;
	
	public PessoaResource(UriInfo uriInfo, Request request, int id) {
		this.uriInfo = uriInfo;
		this.request = request;
		this.id = id;
		
		this.pessoaDAO = new PessoaDAO(EMF.createEntityManager());
	}
	
	
	
	@GET
	@Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
	public Pessoa getPessoa() {
		Pessoa l = pessoaDAO.getById(id);
		
		if(l == null) 
			throw new RuntimeException("GET: A pessoa com id "+id+
					" não foi encontrada!");
		return l;
	}
	
	
	@GET
	@Path("foto")
	@Produces("image/jpg")
	public byte[] getFotoPessoa() {
		return getPessoa().getFoto();
	}
	
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response putPessoa(JAXBElement<Pessoa> l) {
		Pessoa pessoa = l.getValue();
		Response res;
		
		if(pessoaDAO.contains(pessoa)) {
			pessoaDAO.save(pessoa);
			res = Response.ok().build();
		} else {
			pessoaDAO.update(pessoa);
			res = Response.created(uriInfo.getAbsolutePath()).build();
		}
		
		return res;
	}
	
	@DELETE
	public void deletePessoa() {
		try{
			pessoaDAO.delete(getPessoa());
		} catch(Exception e) {
			throw new RuntimeException("DELETE: Falha ao deletar Pessoa!");
		}
	}
	
	
}
