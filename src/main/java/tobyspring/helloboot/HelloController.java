package tobyspring.helloboot;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController //이 어노테이션은 반환되는 문자열이 알아서 응답body에 들어감 (없으면 view를 찾으려고 함)
//@RequestMapping("/hello")
//@MyComponent
public class HelloController{
  private final HelloService helloService;
  private final ApplicationContext applicationContext;

  //application에서 등록된 bean 뒤져서 service 알아서 찾아냄.
  public HelloController(HelloService helloService, ApplicationContext applicationContext) {
    this.helloService = helloService;
    this.applicationContext = applicationContext;
  }

    @GetMapping("/hello")
    public String hello(String name){
      return helloService.sayHello(Objects.requireNonNull(name));
    }
}
