package tobyspring.helloboot;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.catalina.startup.Tomcat;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.context.support.GenericWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import java.io.IOException;

//@SpringBootApplication
@Configuration //구성정보를 가지고 있는 클래스다! (-> 아 이 안에 bean이 붙은 factory 메소드가 있겠구나, 그걸 이용해서 bean객체 만들면 되겠다)
@ComponentScan //component 붙은애들 찾아서 bean등록
public class HellobootApplication {


	public static void main(String[] args) {
		//spring container 만들기
			AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
			@Override
			protected void onRefresh() {
				super.onRefresh();
				//servlet container
				TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
				WebServer webServer = serverFactory.getWebServer(servletContext -> {
					servletContext.addServlet("dispatcherServlet",
									new DispatcherServlet(this)
					).addMapping("/*");
				});
				webServer.start();
			}
		};

		applicationContext.register(HellobootApplication.class);
		applicationContext.refresh(); //초기화


		//SpringApplication.run(HellobootApplication.class, args);
	}

}
