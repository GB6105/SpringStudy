package hello.hellospring.service;

import hello.hellospring.domain.Member;
import hello.hellospring.repository.MemberRepository;
import hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.http.converter.protobuf.ProtobufJsonFormatHttpMessageConverter;

import java.util.List;
import java.util.Optional;

public class MemberService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    //git 추가..
    
    /**
     * 회원 가입
     */
    public Long join(Member member) {
        // 같은 이름이 있는 중복 회원 x
//        Optional<Member> result = memberRepository.findByName(member.getName());
//        //Member member1 = result.orElse(member); optional에서 값을 직접 꺼낼 때 사용 가능
//        result.ifPresent(m ->{//optional이어서 사용할 수 있는 여러 기능
//                throw new IllegalStateException("이미 존재하는 회원입니다.");
//                });
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName()) // 반환 자체가 옵셔널임
                        .ifPresent(m ->{ // 바로 ifpresent가 사용 가능함
                            throw new IllegalStateException("이미 존재하는 회원입니다.");
                                });
    }

    public List<Member> findMembers(){//서비스 이름 자체 설정도 비즈니스 용어 사용을 권고
        return memberRepository.findAll();
    }

    public Optional<Member> findMember(Long id){
        return memberRepository.findById(id);
    }
}
