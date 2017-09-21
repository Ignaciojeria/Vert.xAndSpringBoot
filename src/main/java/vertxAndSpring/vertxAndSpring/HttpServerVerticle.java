package vertxAndSpring.vertxAndSpring;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import vertxAndSpring.vertxAndSpring.controller.HandlingReqAndCallingNextHandler;
import vertxAndSpring.vertxAndSpring.controller.HolaMundoController;
import vertxAndSpring.vertxAndSpring.controller.SerieController;
import vertxAndSpring.vertxAndSpring.service.SerieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class HttpServerVerticle extends AbstractVerticle {
	
	//logs
	private static final Logger LOGGER = LoggerFactory.getLogger(HttpServerVerticle.class);

    //Controladores
	@Autowired SerieController serieController;
	@Autowired HolaMundoController holaMundoController;
	@Autowired HandlingReqAndCallingNextHandler handlingReqAndCallingNextHandler;
	
	//Configuración
	@Autowired
	AppConfiguration configuration;
	 
	@Override
	  public void start(Future<Void> startFuture) throws Exception {
		Future<Void> steps = startHttpServer();
		steps.setHandler(startFuture.completer());  
	}
	
	private Future<Void> startHttpServer(){
		 Future<Void> future = Future.future();
		    HttpServer server = vertx.createHttpServer();   // <1>
		    
		    Router router= Router.router(vertx);
		  //  Forma A
		    holaMundoController.holaMundo(router);
		    holaMundoController.holaMundoWhitParams(router);
		    
		    //Forma B Inicializando los controladores.
		    handlingReqAndCallingNextHandler.cadena(router);
		    serieController.getSeries(router);
		    serieController.saveSerie(router);
		   // new SerieController(serieService).series(router);
		    
		    server.requestHandler(router::accept).listen(configuration.httpPort(),status->{
		    	if(status.succeeded()) {
		    		LOGGER.info("HTTP server running on port " + configuration.httpPort());
		    	}else {
		    		  LOGGER.error("Could not start a HTTP server", status.cause());
		    		  future.fail(status.cause());
		    	}
		    });
		    return future;
	}
}
