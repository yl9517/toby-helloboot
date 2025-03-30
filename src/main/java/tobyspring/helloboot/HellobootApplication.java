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
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import java.io.IOException;

//@SpringBootApplication
public class HellobootApplication {

	public static void main(String[] args) {
		//spring container 만들기
		GenericApplicationContext applicationContext = new GenericApplicationContext();
		applicationContext.registerBean(HelloController.class); // Bean 등록
		applicationContext.registerBean(SimpleHelloService.class); //Bean 등록
		applicationContext.refresh(); //초기화

		TomcatServletWebServerFactory serverFactory = new TomcatServletWebServerFactory();
		WebServer webServer = serverFactory.getWebServer(servletContext -> {

		//서블릿 등록
			servletContext.addServlet("front-controller", new HttpServlet() {
				@Override
				protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
					//front-controller에서 공통기능 처리
					if(req.getRequestURI().equals("/hello") && req.getMethod().equals(HttpMethod.GET.name())){
						String name = req.getParameter("name");

						HelloController helloController = applicationContext.getBean(HelloController.class); //Bean 가져오기
						String result = helloController.hello(name);

						resp.setContentType(MediaType.TEXT_PLAIN_VALUE);
						resp.getWriter().println(result);
					}else{
						resp.setStatus(HttpStatus.NOT_FOUND.value());
					}
				}
			}).addMapping("/*");
        });
		webServer.start();
		//SpringApplication.run(HellobootApplication.class, args);
	}

}
