package vertxAndSpring.vertxAndSpring.controller;

import org.springframework.stereotype.Controller;
import io.vertx.ext.web.Router;
import io.vertx.core.http.HttpServerResponse;

@Controller
public class HolaMundoController {

	public void holaMundo(Router router){
	    router.get("/holaMundo").handler(routingContext->{HttpServerResponse response=routingContext.response();
		response.putHeader("content-type", "text/plain");
		response.end("Hola mundo desde vert.x");});
	}
}
