package hello.hellospring.controller;

import hello.hellospring.domain.Member;
import hello.hellospring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    private final MemberService memberService;

    @Autowired
    public MemberController(MemberService memberService){
        this.memberService = memberService;
    }
    //setter을 쓴느 이유는 set함수를 접근을 분가능하게 함으로써 변경을 막는
     //향후 리포지토리 연결의 변경이 굉장히 쉬워진다.

    @GetMapping("/members/new") //조회할때 쓰는 어노테이션
    public String createForm(){
        return "members/createMemberForm";
    }

    @PostMapping("/members/new") // 값을 넣을 때 스는 어노테이션
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        return "redirect:/";

    }
}
