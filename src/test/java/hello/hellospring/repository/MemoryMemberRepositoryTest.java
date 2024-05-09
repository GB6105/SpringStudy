package hello.hellospring.repository;

import hello.hellospring.domain.Member;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;
import org.junit.jupiter.api.Test;

import java.util.List;

class MemoryMemberRepositoryTest {//굳이 퍼블릭이 아니어도 됨

    MemoryMemberRepository repository = new MemoryMemberRepository();
    // MemberRepository라는 객체 선언함 -> 이 객체를 자료형으로 가지는 repository라는 변수를
    // new로 동적할당과 함께 MemoryMembeRepository()로 저장

    @AfterEach//테스트 실행시마다 저장소나 공용 데이터를 깔끔하게 지워줌
    public void afterEach(){
        repository.clearStore();
    }

    @Test
    public void save(){ // save 기능 되는지 확인 부가적인 코드 없이 이 한줄만으로 호출 및 실행 가능
        Member member = new Member(); // Member라는 객체를 자료형으로 가지는 member 변수를 정의 하는데
                                        // 동적 할당을 위해 new 를 쓰고 Member()함수임을 명시
        member.setName("spring");

        repository.save(member);
        //reposiory에서 save를 실행해줌(save 자체가 지금 이미 짜여진 save()함수)

        Member result = repository.findById(member.getId()).get();
        System.out.println("result = " + (result == member));// result값이 true인지 확인하는 구문
        Assertions.assertEquals(member, result); // 무제 없이 돌아감
        //Assertions.assertEquals(member, NULL); // 강제 오류 발생
//        assertThat(memeber).isEqualTo(result);//이거 왜안되는지 모르겠음
        //alt + enter를 이용해서 assertions를 using namespace 할 수 있음

    }

    @Test // 이름으로 찾기
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member(); // 이름만 바꿔줄때는 shift + f6사용
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();

        assertThat(result).isEqualTo(member1);
    }
    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();

        assertThat(result.size()).isEqualTo(2);
    }
    //각각의 실행은 문제가 없으나 순서 의존적으로 코드가 구성되게 되면 오류가 발생

}
