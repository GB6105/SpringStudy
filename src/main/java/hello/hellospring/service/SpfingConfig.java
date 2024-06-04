package hello.hellospring.service;

import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration // 해당 파일을 읽고 빈 을 연결해줌
public class SpfingConfig {

    @Bean // 멤버서비스라는 기능을 등록을 해줌
    public MemberService memberService() {
        return new MemberService(memberRepository());
    } // 해당 서비스는 member repository를 서비스에 넣어줌
    @Bean // 멤버리포지토리라는 기능을 등록을 해줌
    public MemberRepository memberRepository() {
        return new MemoryMemberRepository();
    }
}
