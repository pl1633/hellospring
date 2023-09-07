package Hello.hellospring.service;

import Hello.hellospring.domain.Member;
import Hello.hellospring.repository.MemberRepository;
import Hello.hellospring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Transactional
public class MemberService {

    private final MemberRepository memberRepository;

    @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원가입
    public Long join(Member member){
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    // 회원조희
    private void validateDuplicateMember(Member member) {
        memberRepository.findByName(member.getName())
                .ifPresent(m -> {
                    throw new IllegalStateException("이미 존재하는 회원입니다.");
                });
    }
    // ifPresent 메소드
    // optional 객체가 값을 가지고있으면 실행

    // 전체회원 조회
    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    // 회원 찾기
    public Optional<Member> findOne(Long memberId) {
        return memberRepository.findById(memberId);
    }
}
