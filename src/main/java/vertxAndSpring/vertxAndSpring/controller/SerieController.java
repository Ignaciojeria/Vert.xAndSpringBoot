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
	
	public void getSeries(Router router){
		router.get("/series").handler(this::getSeries);
	}

	//Capturando los parametros tal cual como vienen en la petición del post.
	public void saveSeries(Router router) {
		router.post("/series/:param1/:param2")
		.handler(routingContext -> {
			  String param1 = routingContext.request().getParam("param1");
			  String param2=routingContext.request().getParam("param2");
			  routingContext.response().putHeader("Content-Type", "application/json")
			  .end(Json.encodePrettily("parametro1:"+param1+" parametro2:"+param2));
			  // Do something with them...
			});
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
