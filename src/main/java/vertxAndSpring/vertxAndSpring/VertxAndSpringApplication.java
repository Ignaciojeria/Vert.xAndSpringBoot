package vertxAndSpring.vertxAndSpring;



import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.vertx.core.Vertx;



@SpringBootApplication
public class VertxAndSpringApplication{
	
	  @Autowired
	  private StaticServer staticServer;
	
	public static void main(String[] args) {
		SpringApplication.run(VertxAndSpringApplication.class, args);
	}
	  
	  @PostConstruct
	  public void deployVerticle() {
	    Vertx.vertx().deployVerticle(staticServer);
	  }
}
