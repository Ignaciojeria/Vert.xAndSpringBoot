package vertxAndSpring.vertxAndSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import io.vertx.core.http.HttpServerResponse;
import io.vertx.core.json.Json;
import io.vertx.core.json.JsonObject;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import vertxAndSpring.vertxAndSpring.service.SerieService;

@Controller
public class SerieController {
	
	@Autowired
	private SerieService serieService;
	
	public void getSeries(Router router){
		router.get("/series").produces("application/json")
		.handler(this::getSeries);
	}

	//Capturando el body de la peticion post
	public void saveSerie(Router router) {
		router.post("/series").handler(this::save);
	}
	public SerieController(){}
	
	public SerieController(SerieService serieService){
		this.serieService=serieService;		
	}

	  private void getSeries(RoutingContext routingContext){
		 /*Forma A
		   routingContext.response()
		  .putHeader("Content-Type", "application/json")
		  .end(Json.encodePrettily(serieService.findAll()));*/
	//	  Forma B
		  HttpServerResponse response = routingContext.response();
		  response.putHeader("content-type", "application/json");
		  response.end(Json.encodePrettily(serieService.findAll()));
	  }
	  
	  //Método bizarro para comprobar la intercepción de los datos en json desde el cliente hasta la aplicación.
	  private void save( RoutingContext routingContext) {
		routingContext.request().bodyHandler(bodyHandler->{ 
		final JsonObject body = bodyHandler.toJsonObject();
		routingContext.response().end(Json.encodePrettily(body));});
	  }

}
