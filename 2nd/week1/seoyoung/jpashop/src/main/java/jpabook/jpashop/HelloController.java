package jpabook.jpashop;
import org.springframework.ui.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
//웹 요청 처리 : 컨트롤러
//스프링이 이 클래스를 빈으로 등록 -> url 매핑 연결 준비
public class HelloController {


    @GetMapping("hello")
    public String Hello (Model model) {
        model.addAttribute("data","hello!");
        return "hello";

    }
}
