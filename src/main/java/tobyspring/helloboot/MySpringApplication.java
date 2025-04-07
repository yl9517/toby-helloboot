package tobyspring.helloboot;

import org.springframework.boot.web.server.WebServer;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class MySpringApplication {


  public static void run(Class<?> applicationClass, String[] args) {
    //spring container 만들기
    AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext(){
      @Override
      protected void onRefresh() {
        super.onRefresh();
        //servlet container
        ServletWebServerFactory serverFactory = this.getBean(ServletWebServerFactory.class);
        DispatcherServlet dispatcherServlet = this.getBean(DispatcherServlet.class);
        // dispatcherServlet.setApplicationContext(this); 없어도 됨. 스프링 컨테이너가 알아서 넣어줌

        WebServer webServer = serverFactory.getWebServer(servletContext -> {
          servletContext.addServlet("dispatcherServlet",dispatcherServlet).addMapping("/*");
        });
        webServer.start();
      }
    };

    applicationContext.register(applicationClass);
    applicationContext.refresh(); //초기화

  }

}
