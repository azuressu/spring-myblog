package org.example.springbootdeveloper.controller;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDate;
import java.util.List;

@Controller
public class ExampleController {
    
    @GetMapping("/thymeleaf/example")
    // Model: 뷰 즉 HTML쪽으로 값을 넘겨주는 객체 (코드처럼 인자로 생성하기만 하면 스프링이 알아서 만들어줌)
    // addAttribute(): 모델에 값을 저장 (person이라는 키에 사람 정보, today라는 키에 날짜 정보 저장)
    // thymeleafexample 메서드가 반환하는 값은 example (클래스에 붙은 애너테이션이 @Controller이므로 뷰의 이름을 반환)
    public String thymeleafExample(Model model) { // 뷰로 데이터를 넘겨주는 모델 객체
        Person examplePerson = new Person();
        examplePerson.setId(1L);
        examplePerson.setName("홍길동");
        examplePerson.setAge(11);
        examplePerson.setHobbies(List.of("운동", "독서"));
        
        model.addAttribute("person", examplePerson); // Person 객체 저장
        model.addAttribute("today", LocalDate.now());

        return "example"; //example.html 뷰 조회
    }

    @Getter
    @Setter
    class Person {
        private Long id;
        private String name;
        private int age;
        private List<String> hobbies;
    }
}

