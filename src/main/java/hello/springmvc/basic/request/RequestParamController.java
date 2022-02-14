package hello.springmvc.basic.request;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Slf4j
@Controller
public class RequestParamController {

    @RequestMapping("/request-param-v1")
    public void requestParamV1(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        int age = Integer.parseInt(request.getParameter("age"));

        log.info("username={}, age={}", username, age);

        response.getWriter().write("ok");
    }

    @ResponseBody
    @RequestMapping("/request-param-v2")
    public String requestParamV2(@RequestParam("username") String memberName,
                               @RequestParam("age") int memberAge)  {
        log.info("username={}, age={}", memberName, memberAge);
        return "ok";
    }

    // http 파라미터 이름과 변수 이름 같을 경우 @RequestParam(name="xx") 생략 가능
    @ResponseBody
    @RequestMapping("/request-param-v3")
    public String requestParamV3(@RequestParam String username,
                                 @RequestParam int age)  {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // String, int, Integer 등 단순 타입일 경우 @RequestParam 생략 가능
    // @RequestParam 생략 시 required=false
    @ResponseBody
    @RequestMapping("/request-param-v4")
    public String requestParamV4(String username, int age)  {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // required=false 인 경우
    // primitive 타입을 쓰면 안됨..null이 들어가지 않아서 컴파일 에러
    // 또는 defaultValue 사용
    // required=true 인 경우 empty string 통과됨 주의!
    @ResponseBody
    @RequestMapping("/request-param-required")
    public String requestParamRequired(@RequestParam(required = true) String username,
                                       @RequestParam(required = false) Integer age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    // defaultValue를 지정해주면 empty string의 경우도 default value로 넣어줌
    @ResponseBody
    @RequestMapping("/request-param-default")
    public String requestParamDefault(@RequestParam(required = true, defaultValue = "guest") String username,
                                       @RequestParam(required = false, defaultValue = "-1") Integer age) {
        log.info("username={}, age={}", username, age);
        return "ok";
    }

    @ResponseBody
    @RequestMapping("/request-param-map")
    public String requestParamMap(@RequestParam Map<String, Object> paramMap) {
        log.info("username={}, age={}", paramMap.get("username"),
                paramMap.get("age"));
        return "ok";
    }
}
