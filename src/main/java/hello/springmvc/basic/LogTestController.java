package hello.springmvc.basic;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class LogTestController {

//    @Slf4j 어노테이션 쓰면 아래 코드 작성 안해도 됨
//    private final Logger log = LoggerFactory.getLogger(getClass());

    @RequestMapping("/log-test")
    public String logTest() {
        String name = "Spring";

        System.out.println("name = " + name);

        log.trace("trace log= {}", name);
        log.debug("debug log= {}", name);
        log.info("info log={}", name);
        log.warn("warn log= {}", name);
        log.error("error log= {}", name);
        return "ok";
    }
}
