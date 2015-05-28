package br.edu.utfpr.jaxrs.hello.modelo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OlaMundo {
	private String idioma;
	private String mensagem;
	
	public OlaMundo() {}
	
	public OlaMundo(String idioma, String mensagem) {
		super();
		this.idioma = idioma;
		this.mensagem = mensagem;
	}
	
	public String getIdioma() {
		return idioma;
	}
	public void setIdioma(String idioma) {
		this.idioma = idioma;
	}
	public String getMensagem() {
		return mensagem;
	}
	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}	
}
