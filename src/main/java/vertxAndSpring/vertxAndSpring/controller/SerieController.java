package vertxAndSpring.vertxAndSpring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import io.vertx.core.json.Json;
import io.vertx.ext.web.Router;
import io.vertx.ext.web.RoutingContext;
import vertxAndSpring.vertxAndSpring.service.SerieService;

@Controller
public class SerieController {
	
	@Autowired
	private SerieService serieService;
	
	public void series(Router router){
		router.route("/series").handler(this::getSeries);
	}
	
	public SerieController(){}
	
	public SerieController(SerieService serieService){
		this.serieService=serieService;		
	}

	  private void getSeries(RoutingContext routingContext){
		  routingContext.response().putHeader("Content-Type", "application/json")
		  .end(Json.encodePrettily(serieService.findAll()));	  
	  }

}
