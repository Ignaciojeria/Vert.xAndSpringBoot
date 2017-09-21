package vertxAndSpring.vertxAndSpring;



import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.vertx.core.Vertx;
import vertxAndSpring.vertxAndSpring.Mock.MockSerie;



@SpringBootApplication
public class VertxAndSpringApplication{
	
	  @Autowired
	  private HttpServerVerticle staticServer;
	  
	  //es un mock de prueba para rellenar la BDD (no lo tomes en cuenta).
	  @Autowired private MockSerie mockSerie;
	
	  //Instanciando un objeto vertx
	  private Vertx vertx=Vertx.vertx();
	
	public static void main(String[] args) {
		SpringApplication.run(VertxAndSpringApplication.class, args);
	}
	  
	  @PostConstruct
	  public void deployVerticle() {
	  /*deployando la clase que implmenenta un abstractVerticle
	   y en su método implementado start crea un Servidor Http 
	   con uris que hacen persistencia a una BDD en myslq con JPA (luego estudiaremos JOOQ) */
		vertx.deployVerticle(staticServer);
	    mockSerie.savemock();
	  }
}
