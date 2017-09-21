package vertxAndSpring.vertxAndSpring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

@Configuration
public class AppConfiguration {
	 
	  @Autowired
	  Environment environment;

	  int httpPort() {
	    return environment.getProperty("http.port", Integer.class, 8281);
	  }
	  
	  String wikiDBQueue() {
		return environment.getProperty("wikidb.queque",String.class,"wikidb.queque");
	  }
}
