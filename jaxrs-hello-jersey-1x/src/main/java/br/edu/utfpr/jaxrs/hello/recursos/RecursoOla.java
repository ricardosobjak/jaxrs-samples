package br.edu.utfpr.jaxrs.hello.recursos;

import java.util.List;

import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import br.edu.utfpr.jaxrs.hello.modelo.OlaMundo;
import br.edu.utfpr.jaxrs.hello.servicos.ServicoOlaMundo;

@Path("ola-mundo")
public class RecursoOla {
	private ServicoOlaMundo servicoOlaMundo;

	public RecursoOla() {
		servicoOlaMundo = new ServicoOlaMundo();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<OlaMundo> buscaTodos() {
		return servicoOlaMundo.buscaTodos();
	}

	@Path("/{idioma: [A-Z][A-Z]}")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public OlaMundo buscaPorIdioma(@PathParam("idioma") String idioma) {
		OlaMundo res = servicoOlaMundo.buscaPorIdioma(idioma);

		if (res == null)
			throw new WebApplicationException(Status.NOT_FOUND);
		return res;
	}

	@Path("/{idioma: [A-Z][A-Z]}")
	@GET
	@Produces({ MediaType.TEXT_HTML })
	public String buscaPorIdiomaHTML(
			@PathParam("idioma") String idioma,
			@QueryParam("cor") @DefaultValue("red") String cor,
			@QueryParam("tamanho") @DefaultValue("18") int tamanho) {
		
		OlaMundo res = servicoOlaMundo.buscaPorIdioma(idioma);
		if (res == null)
			throw new WebApplicationException(Status.NOT_FOUND);
		StringBuffer sb = new StringBuffer();
		sb.append("<span style='");
		sb.append("color:" + cor + ";");
		sb.append("font-size:" + tamanho + "px;");
		sb.append("'>");
		sb.append(res.getMensagem());
		sb.append("</strong>");
		return sb.toString();
	}
}