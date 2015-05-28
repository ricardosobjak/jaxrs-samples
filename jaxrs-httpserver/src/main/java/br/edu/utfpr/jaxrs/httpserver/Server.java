package br.edu.utfpr.jaxrs.httpserver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.ext.RuntimeDelegate;

import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class Server {

    /**
     * Inicia o servidor HTTP leve, servindo a aplicação JAX-RS.
     *
     * @return nova instância do servidor HTTP leve
     * @throws IOException
     */
    static HttpServer startServer() throws IOException {
        // cria um novo servidor escutanto da porta 8888
        HttpServer server = HttpServer.create(new InetSocketAddress(getBaseURI().getPort()), 0);

        // cria um manipulador envolvendo a aplicação JAX-RS
        HttpHandler handler = RuntimeDelegate.getInstance().createEndpoint(new JaxRsApplication(), HttpHandler.class);

        // mapeia o manipulador JAX-RS para a raíz do servidor
        server.createContext(getBaseURI().getPath(), handler);

        // inicia o servidor
        server.start();

        return server;
    }

    @SuppressWarnings("ResultOfMethodCallIgnored")
    public static void main(String[] args) throws IOException {
        System.out.println("Servidor HTTP Lightweight: Hospedando \""+JaxRsApplication.class.getName()+"\" Jersey Application");

        HttpServer server = startServer();

        System.out.println("Application started.\n" +
                "Try accessing " + getBaseURI() + "data in the browser.\n" +
                "Hit enter to stop the application...");
        System.in.read();
        server.stop(0);
    }

    private static int getPort(int defaultPort) {
        final String port = System.getProperty("jersey.config.test.container.port");
        if (null != port) {
            try {
                return Integer.parseInt(port);
            } catch (NumberFormatException e) {
                System.out.println("Value of jersey.config.test.container.port property" +
                        " is not a valid positive integer [" + port + "]." +
                        " Reverting to default [" + defaultPort + "].");
            }
        }
        return defaultPort;
    }

    /**
     * Gets base {@link URI}.
     *
     * @return base {@link URI}.
     */
    public static URI getBaseURI() {
        return UriBuilder.fromUri("http://localhost/").port(getPort(8888)).build();
    }
}
