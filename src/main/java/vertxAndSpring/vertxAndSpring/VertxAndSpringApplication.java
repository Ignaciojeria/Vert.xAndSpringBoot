package vertxAndSpring.vertxAndSpring;



import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.vertx.core.Vertx;



@SpringBootApplication
public class VertxAndSpringApplication implements CommandLineRunner {
	
	  @Autowired
	  private StaticServer staticServer;
	
	public static void main(String[] args) {
		SpringApplication.run(VertxAndSpringApplication.class, args);
	}
	  

	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub
		  Vertx.vertx().deployVerticle(staticServer);
	}
}
