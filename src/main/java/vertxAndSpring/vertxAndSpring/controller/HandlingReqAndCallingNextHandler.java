package vertxAndSpring.vertxAndSpring.controller;

import org.springframework.stereotype.Controller;

import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;

@Controller
public class HandlingReqAndCallingNextHandler {
//Handling requests and calling the next handler
	public void cadena(Router router){
	    router.route("/cadena").handler(this::route1);
	    router.route("/cadena").handler(this::route2);
	    router.route("/cadena").handler(this::route3);
	}
	
	
	  private void route1(RoutingContext routingContext){
		 // routingContext.response().putHeader("content-type", "text/plain")
		  routingContext.response().setChunked(true)
		  .write("/route1\n");
		 // .end("Hola mundo desde Vertx web");
		  routingContext.vertx().setTimer(2000,tid->routingContext.next());

	  }
	  
	  private void route2(RoutingContext routingContext){
		 // routingContext.response().putHeader("content-type", "text/plain")
		  routingContext.response()
		  .write("/route2\n");
		 // .end("Hola mundo desde Vertx web");
		  routingContext.vertx().setTimer(2000,tid->routingContext.next());
	  }
	  
	  private void route3(RoutingContext routingContext){
		 // routingContext.response().putHeader("content-type", "text/plain")
		  routingContext.response()
		  .write("route3");
		 // .end("Hola mundo desde Vertx web");
		  routingContext.response().end();
	  }

}
