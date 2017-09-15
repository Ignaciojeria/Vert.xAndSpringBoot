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
		    
		    server.requestHandler(request -> {

		    	  // This handler gets called for each request that arrives on the server
		    	  HttpServerResponse response = request.response();
		    	  response.putHeader("content-type", "text/plain");

		    	  // Write to the response and end it
		    	  response.end("Hello World!");
		    	});
		    server.listen(configuration.httpPort());

		    /*
		    Router router = Router.router(vertx);   // <2>		    
		    router.get("/").handler(this::getSeries);
		    
		    server
		      .requestHandler(router::accept)   // <5>
		      .listen(configuration.httpPort(), ar -> {   // <6>
		        if (ar.succeeded()) {
		      LOGGER.info("HTTP server running on port " + configuration.httpPort());
		          future.complete();
		        } else {
		        	 LOGGER.error("Could not start a HTTP server", ar.cause());
		          future.fail(ar.cause());
		        }
		      });
	  */
	  }
	  @Autowired
	  SerieService serieService;
	  
	  private void getSeries(RoutingContext routingContext){
		  routingContext.response().putHeader("Content-Type", "application/json")
		  .end(Json.encodePrettily(serieService.findAll()));
		  
	  }
	  
	  
	  
	 
}
