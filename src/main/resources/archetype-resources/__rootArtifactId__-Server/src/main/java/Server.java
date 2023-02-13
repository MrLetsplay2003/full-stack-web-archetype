package ${package};

import java.nio.charset.StandardCharsets;
import java.util.Date;

import me.mrletsplay.fullstackweb.ClientLoader;
import me.mrletsplay.simplehttpserver.http.HttpServer;
import me.mrletsplay.simplehttpserver.http.request.HttpRequestContext;

public class Server {

	public static void main(String[] args) {
		HttpServer server = new HttpServer(HttpServer.newConfigurationBuilder()
			.hostBindAll()
			.port(8080)
			.create());
		ClientLoader.serveDefaultClient(server.getDocumentProvider());
		server.getDocumentProvider().registerDocument("/hello", () -> {
			HttpRequestContext ctx = HttpRequestContext.getCurrentContext();
			String message = "Hello World, the current time is " + new Date().toString() + "!";
			ctx.getServerHeader().setContent("text/plain", message.getBytes(StandardCharsets.UTF_8));
		});
		server.start();
	}

}
