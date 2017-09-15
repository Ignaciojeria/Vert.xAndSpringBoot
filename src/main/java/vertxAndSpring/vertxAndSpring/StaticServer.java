package vertxAndSpring.vertxAndSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.logging.Logger;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import vertxAndSpring.vertxAndSpring.service.SerieService;

@Component
public class StaticServer extends AbstractVerticle {

	
	 private static final Logger LOGGER = LoggerFactory.getLogger(StaticServer.class);
	
	
	@Autowired
	  AppConfiguration configuration;

	 
	  public void start(Future<Void> fut) throws Exception {
		  
		 // Future<Void> future = Future.future();
		    HttpServer server = vertx.createHttpServer();   // <1>
		    
		    Router router= Router.router(vertx);
		  /*  Forma A
		    router.route().handler(routingContext->{HttpServerResponse response=routingContext.response();
		    										response.putHeader("content-type", "text/plain");
		    										response.end("Hola mundo desde vert.x");});*/
		    //Forma B
		    router.route("/hola").handler(this::route1);
		    router.route("/hola").handler(this::route2);
		    router.route("/hola").handler(this::route3);
		    
		    server.requestHandler(router::accept).listen(configuration.httpPort());
		 

	  }
	  @Autowired
	  SerieService serieService;
	  
	  private void getSeries(RoutingContext routingContext){
		  routingContext.response().putHeader("Content-Type", "application/json")
		  .end(Json.encodePrettily(serieService.findAll()));
		  
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
