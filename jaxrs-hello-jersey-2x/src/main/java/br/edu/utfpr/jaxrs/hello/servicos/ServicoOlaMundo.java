package br.edu.utfpr.jaxrs.hello.servicos;

import java.util.ArrayList;
import java.util.List;

import br.edu.utfpr.jaxrs.hello.modelo.OlaMundo;

public class ServicoOlaMundo {

	public List<OlaMundo> buscaTodos() {
		return OlaMundoBD.buscaTodos();
	}

	public OlaMundo buscaPorIdioma(String idioma) {
		return OlaMundoBD.buscaPorIdioma(idioma);
	}

	/**
	 * Essa class estática simula um banco de dados de OlaMundo's
	 */
	public static class OlaMundoBD {
		private static List<OlaMundo> mensagens;

		static {
			mensagens = new ArrayList<OlaMundo>();
			mensagens.add(new OlaMundo("PT", "Olá Mundo!"));
			mensagens.add(new OlaMundo("EN", "Hello World!"));
			mensagens.add(new OlaMundo("ES", "Hola Mundo!"));
			mensagens.add(new OlaMundo("DE", "Hallo Welt!"));
		}

		/**
		 * Todas as mensagens
		 * 
		 * @return
		 */
		public static List<OlaMundo> buscaTodos() {
			return mensagens;
		}

		/**
		 * Tenta buscar por idioma, retorna nulo se não encontrada a mensagem no
		 * idioma que pedimos
		 * 
		 * @param idioma
		 * @return
		 */
		public static OlaMundo buscaPorIdioma(String idioma) {
			for (OlaMundo olaMundo : mensagens) {
				if (olaMundo.getIdioma().equals(idioma))
					return olaMundo;
			}
			return null;
		}
	}
}
