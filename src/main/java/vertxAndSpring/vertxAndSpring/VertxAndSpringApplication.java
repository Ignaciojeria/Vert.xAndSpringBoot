package vertxAndSpring.vertxAndSpring;


import javax.annotation.PostConstruct;

import io.vertx.core.DeploymentOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import io.vertx.core.Vertx;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import vertxAndSpring.vertxAndSpring.Mock.MockSerie;


@SpringBootApplication
public class VertxAndSpringApplication {



    //es un mock de prueba para rellenar la BDD (no lo tomes en cuenta).
    @Autowired
    private MockSerie mockSerie;

    @Autowired
    private ServerLauncher serverLauncher;

    //Instanciando un objeto vertx
    private Vertx vertx = Vertx.vertx();

    @Autowired
    private ApplicationContext applicationContext;


    public static void main(String[] args) {
        ConfigurableApplicationContext context=SpringApplication.run(VertxAndSpringApplication.class, args);
    }

    @PostConstruct
    public void deployVerticle() {
        vertx.deployVerticle(serverLauncher);
        mockSerie.savemock();
    }
}
