package controller;

import org.springframework.stereotype.Controller;

import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import vertxAndSpring.vertxAndSpring.service.SerieService;

@Controller
public class SerieController {
	

	private SerieService serieService;
	
	public void series(Router router){
		router.route("/series").handler(this::getSeries);
	}
	
	public SerieController(SerieService serieService){
		this.serieService=serieService;		
	}

	  private void getSeries(RoutingContext routingContext){
		  routingContext.response().putHeader("Content-Type", "application/json")
		  .end(Json.encodePrettily(serieService.findAll()));	  
	  }

}
