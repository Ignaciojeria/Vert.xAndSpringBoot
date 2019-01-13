package vertxAndSpring.vertxAndSpring;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.DeploymentOptions;
import io.vertx.core.Future;
import io.vertx.ext.web.Router;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import vertxAndSpring.vertxAndSpring.HttpServerVerticle;
import vertxAndSpring.vertxAndSpring.controller.SerieController;

@Component
public class ServerLauncher extends AbstractVerticle {

    @Autowired
    private HttpServerVerticle httpServerVerticle;

    @Override
    public void start(Future<Void> done){
        int WORKER_POOL_SIZE = 100;

        DeploymentOptions opts = new DeploymentOptions().setWorkerPoolSize(WORKER_POOL_SIZE);
        opts.setInstances(2);


        vertx.deployVerticle(httpServerVerticle.getClass().getName(), opts, res -> {
            if(res.failed()){
                System.out.println("Failed to deploy verticle");
                done.fail(res.cause());
            }
            else {
                System.out.println("Successfully deployed verticle");
                done.complete();
            }
        });
    }
}