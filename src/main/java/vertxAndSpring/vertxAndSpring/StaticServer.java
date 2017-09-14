package vertxAndSpring.vertxAndSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.core.logging.LoggerFactory;
import io.vertx.ext.sql.SQLClient;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import io.vertx.ext.web.handler.StaticHandler;
import io.vertx.rxjava.ext.jdbc.JDBCClient;
import io.vertx.rxjava.ext.sql.SQLConnection;
import vertxAndSpring.vertxAndSpring.service.SerieService;

@Component
public class StaticServer extends AbstractVerticle {

	
	
	
	
	@Autowired
	  AppConfiguration configuration;

	 
	  public void start(Future<Void> fut) throws Exception {
		  
		  Future<Void> future = Future.future();
		    HttpServer server = vertx.createHttpServer();   // <1>

		    Router router = Router.router(vertx);   // <2>
		    router.get("/").handler(this::getSeries);
		    
		    server
		      .requestHandler(router::accept)   // <5>
		      .listen(8281, ar -> {   // <6>
		        if (ar.succeeded()) {

		          future.complete();
		        } else {

		          future.fail(ar.cause());
		        }
		      });
	  
	  }
	  @Autowired
	  SerieService serieService;
	  
	  private void getSeries(RoutingContext routingContext){
		  routingContext.response().putHeader("Content-Type", "application/json")
		  .end(Json.encodePrettily(serieService.findAll()));
		  
	  }
	  
	  
	  
	 
}
