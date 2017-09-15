package vertxAndSpring.vertxAndSpring;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Component;

import controller.HandlingReqAndCallingNextHandler;
import controller.HolaMundoController;
import controller.SerieController;
import io.vertx.core.AbstractVerticle;
import io.vertx.core.Future;
import io.vertx.core.http.HttpServer;

import io.vertx.ext.web.Router;

import vertxAndSpring.vertxAndSpring.service.SerieService;

@Component
public class StaticServer extends AbstractVerticle {

	//Servicios
	@Autowired
	private SerieService serieService;

	//Configuración
	@Autowired
	AppConfiguration configuration;
	 
	  public void start(Future<Void> fut) throws Exception {
		  
		 // Future<Void> future = Future.future();
		    HttpServer server = vertx.createHttpServer();   // <1>
		    
		    Router router= Router.router(vertx);
		  //  Forma A
		    new HolaMundoController().holaMundo(router);
		    
		    //Forma B Inicializando los controladores.
		    new HandlingReqAndCallingNextHandler().cadena(router);
		    new SerieController(serieService).series(router);
		    
		    server.requestHandler(router::accept).listen(configuration.httpPort());
		 
	  }
	  
	  
}
