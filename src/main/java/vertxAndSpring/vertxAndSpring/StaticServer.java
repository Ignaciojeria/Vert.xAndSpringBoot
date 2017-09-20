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

@Component
public class StaticServer extends AbstractVerticle {

    //Controladores
	@Autowired SerieController serieController;
	@Autowired HolaMundoController holaMundoController;
	@Autowired HandlingReqAndCallingNextHandler handlingReqAndCallingNextHandler;
	
	//Configuración
	@Autowired
	AppConfiguration configuration;
	 
	@Override
	  public void start(Future<Void> fut) throws Exception {
		  
		 // Future<Void> future = Future.future();
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
		    
		    server.requestHandler(router::accept).listen(configuration.httpPort());
		 
	  }	  
}
