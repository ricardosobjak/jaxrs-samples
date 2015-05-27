package br.edu.utfpr.jaxrs.biblioteca;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import br.edu.utfpr.jaxrs.biblioteca.model.Livro;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class JAXRSClientTest extends TestCase {
	private static final String PROTOCOL = "http";
	private static final String DOMAIN = "localhost:8080";
	private static final String APPNAME = "jaxrs-samples-biblioteca";
	private static final String ROOT = "/rest";

	// Cliente web para acesso aos resursos
	private Client client = ClientBuilder.newClient();

	// Alvo principal de acesso, no qual estão todos os recursos que iremos
	// acessar
	private WebTarget target = client.target(PROTOCOL + "://" + DOMAIN + "/"
			+ APPNAME + ROOT);

	public JAXRSClientTest(String testName) {
		super(testName);

	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(JAXRSClientTest.class);
	}

	/**
	 * Teste de Criação de um Livro
	 */
	public void testCriarLivro() {
		Response res; // Objeto para receber as respostas

		System.out.println("TEST: Criar um livro");

		// Montado o formul�rio
		Form form = new Form();
		form.param("id", "5");
		form.param("titulo", "Java RMI");
		form.param("autor", "Jacob Nischel");
		form.param("isbn", "123456");

		// Fazendo a requisição
		res = target.path("biblioteca") // indica o recurso
				.request(MediaType.TEXT_HTML)
				// Tipo de retorno
				.post(Entity.entity(form,
						MediaType.APPLICATION_FORM_URLENCODED_TYPE));

		assertNotNull(res); // A resposta não é null
		assertTrue(res.getStatus() == 201); // O código da resposta é 201
											// (Created)
		assertNull(res.getHeaderString("Content-Type")); // O tipo de conteúdo é
															// vazio
		assertTrue(res.getHeaderString("Location").equals(
				target.getUri().toString() + "/biblioteca/" + 5));

		printResposta(res);
	}

	public void testObterLivrosXML() {
		System.out.println("TEST: Obter Livros em Formato XML");
		Response res = target.path("biblioteca").request(MediaType.TEXT_XML)
				.get();

		assertNotNull(res);
		assertTrue(res.getStatus() == 200);
		assertTrue(res.getHeaderString("Content-Type").equals(
				MediaType.TEXT_XML));

		printResposta(res);
	}

	public void testObterLivrosJSON() {
		System.out.println("TEST: Obter Livros em Formato JSON");
		Response res = target.path("biblioteca")
				.request(MediaType.APPLICATION_JSON).get();

		assertNotNull(res);
		assertTrue(res.getStatus() == 200);
		assertTrue(res.getHeaderString("Content-Type").equals(
				MediaType.APPLICATION_JSON));

		printResposta(res);
	}

	public void testObterLivro1XML() {
		System.out.println("TEST: Buscar o Livro com ID 1");

		Response res = target.path("biblioteca").path("1")
				.request(MediaType.APPLICATION_XML).get();

		assertNotNull(res);
		assertTrue(res.getStatus() == 200);
		assertTrue(res.getHeaderString("Content-Type").equals(
				MediaType.APPLICATION_XML));

		printResposta(res);
	}

	public void testObterLivro1Object() {
		System.out.println("TEST: Buscar o Livro com ID 1");

		Livro livro = target.path("biblioteca").path("1")
				.request(MediaType.APPLICATION_XML).get(Livro.class);

		assertNotNull(livro);

		System.out.println(livro);
	}

	public void testExcluirLivro3() {
		System.out.println("TES: Excluir o livro com ID 3");

		Response res = target.path("biblioteca/3").request().delete();
		
		printResposta(res);
	}

	public static void printResposta(Response r) {
		System.out.println("--> Status: " + r.getStatus());
		System.out.println("--> Status Info: " + r.getStatusInfo());
		System.out.println("--> Cabeçalho: " + r.getStringHeaders());
		System.out.println("--> RESPOSTA: " + r.readEntity(String.class));
	}
}
