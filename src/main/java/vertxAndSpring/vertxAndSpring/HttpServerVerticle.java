package vertxAndSpring.vertxAndSpring;

import io.vertx.core.*;
import io.vertx.core.http.HttpMethod;
import io.vertx.core.http.HttpServerOptions;
import io.vertx.core.http.HttpServerResponse;
import io.vertx.ext.web.handler.BodyHandler;
import io.vertx.ext.web.handler.CorsHandler;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.access.BeanFactoryLocator;
import org.springframework.beans.factory.access.BeanFactoryReference;
import org.springframework.beans.factory.access.SingletonBeanFactoryLocator;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Component;

import io.vertx.core.http.HttpServer;
import io.vertx.ext.web.Router;
import vertxAndSpring.vertxAndSpring.controller.HandlingReqAndCallingNextHandler;
import vertxAndSpring.vertxAndSpring.controller.HolaMundoController;
import vertxAndSpring.vertxAndSpring.controller.SerieController;
import vertxAndSpring.vertxAndSpring.entity.Serie;
import vertxAndSpring.vertxAndSpring.service.SerieService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Set;

@Component
public class HttpServerVerticle extends AbstractVerticle implements ApplicationContextAware {

    private static ApplicationContext context;

    @Override
    public void start(Future<Void> future) throws Exception {
        int PORT = 8181;
        //        HelloWorld service = new HelloWorld();
        //        HelloController controller = new HelloController(vertx, service);
        //        Router helloRouter = controller.getRouter();
        //	AlumnoController alumnoController=new AlumnoController();
        // Router alumnoRouter = alumnoController.getRouter();
        Router mainRouter = Router.router(vertx);
        // mainRouter.route().consumes("application/json");
        // mainRouter.route().produces("application/json");

        SerieController serieController= getBean(SerieController.class);

        serieController.getSeries(mainRouter);


        //alumnoController.findAll(mainRouter);

        Set<String> allowHeaders = getAllowedHeaders();
        Set<HttpMethod> allowMethods = getAllowedMethods();
        mainRouter.route().handler(BodyHandler.create());
        mainRouter.route().handler(CorsHandler.create("*")
                .allowedHeaders(allowHeaders)
                .allowedMethods(allowMethods));

        // mainRouter.mountSubRouter(Api.ALUMNO, alumnoRouter);
        //  mainRouter.get(API.LB_CHECK).handler(GlobalHandlers::lbCheck);
        // mainRouter.route().failureHandler(GlobalHandlers::error);

        // Create the http server and pass it the router
        vertx.createHttpServer()
                .requestHandler(mainRouter::accept)
                .listen(PORT, res -> {
                    if (res.succeeded()) {
                        System.out.println("Server listening on port " + PORT);
                        future.complete();
                    } else {
                        System.out.println("Failed to launch server");
                        future.fail(res.cause());
                    }
                });

    }

    public void holaMundo(Router router) {
        router.get("/holaMundo").handler(routingContext -> {
            HttpServerResponse response = routingContext.response();
            response.putHeader("content-type", "text/plain");
            response.end("Hola mundo desde vert.x");
        });
    }

    private Set<String> getAllowedHeaders() {
        Set<String> allowHeaders = new HashSet<>();
        allowHeaders.add("x-requested-with");
        allowHeaders.add("Access-Control-Allow-Origin");
        allowHeaders.add("origin");
        allowHeaders.add("Content-Type");
        allowHeaders.add("accept");
        return allowHeaders;
    }

    private Set<HttpMethod> getAllowedMethods() {
        Set<HttpMethod> allowMethods = new HashSet<>();
        allowMethods.add(HttpMethod.GET);
        allowMethods.add(HttpMethod.POST);
        allowMethods.add(HttpMethod.DELETE);
        allowMethods.add(HttpMethod.PATCH);
        return allowMethods;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }

}
