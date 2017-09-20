package vertxAndSpring.vertxAndSpring.controller;

import org.springframework.stereotype.Controller;
import io.vertx.ext.web.Router;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;

@Controller
public class HolaMundoController {

	public void holaMundo(Router router){
	    router.get("/holaMundo").handler(routingContext->{HttpServerResponse response=routingContext.response();
		response.putHeader("content-type", "text/plain");
		response.end("Hola mundo desde vert.x");});
	}
	
	public void holaMundoWhitParams(Router router) {
		router.get("/holaMundo/:param1/:param2")
		.handler(routingContext -> {
			  String param1 = routingContext.request().getParam("param1");
			  String param2=routingContext.request().getParam("param2");
			  routingContext.response().putHeader("Content-Type", "application/json")
			  .end(Json.encodePrettily("parametro1:"+param1+" parametro2:"+param2));
			  // Do something with them...
			});
	}
	
}
