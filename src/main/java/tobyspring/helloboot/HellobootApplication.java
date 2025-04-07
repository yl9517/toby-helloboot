package tobyspring.helloboot;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

//@SpringBootApplication
@Configuration //구성정보를 가지고 있는 클래스다! (-> 아 이 안에 bean이 붙은 factory 메소드가 있겠구나, 그걸 이용해서 bean객체 만들면 되겠다)
@ComponentScan //component 붙은애들 찾아서 bean등록
public class HellobootApplication {
	@Bean
	public ServletWebServerFactory serverFactory () {
		return new TomcatServletWebServerFactory();
	}

	@Bean
	public DispatcherServlet dispatcherServlet () {
		return new DispatcherServlet();
	}

	public static void main(String[] args) {
		MySpringApplication.run(HellobootApplication.class, args);

		//SpringApplication.run(HellobootApplication.class, args);
	}

}
