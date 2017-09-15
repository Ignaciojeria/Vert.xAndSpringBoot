package vertxAndSpring.vertxAndSpring.controller;

import org.springframework.stereotype.Controller;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.Router;
@Controller
public class HolaMundoController {

	public void holaMundo(Router router){
	    router.route("/holaMundo").handler(routingContext->{HttpServerResponse response=routingContext.response();
		response.putHeader("content-type", "text/plain");
		response.end("Hola mundo desde vert.x");});
	}
}
