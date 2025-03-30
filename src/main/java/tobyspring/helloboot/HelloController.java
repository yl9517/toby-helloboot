package tobyspring.helloboot;

import java.util.Objects;

//@RestController
public class HelloController {
  private final HelloService helloService;

  //application에서 등록된 bean 뒤져서 service 알아서 찾아냄.
  public HelloController(HelloService helloService) {
    this.helloService = helloService;
  }

  //  @GetMapping("/hello")
    public String hello(String name){


      return helloService.sayHello(Objects.requireNonNull(name));

    }
}
