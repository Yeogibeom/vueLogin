package example.vuelogin.controller;

import example.vuelogin.entity.Member;
import example.vuelogin.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    @PostMapping("/register")
    public String registeruser(@RequestBody Member member) {
        System.out.println("회원가입 요청: " + member);// 클라이언트에서 받은 데이터 확인
       var memberPassword = passwordEncoder.encode(member.getPassword());
       System.out.println("++++++++++++++++++"+memberPassword);
       member.setPassword(memberPassword);Member savedMember = memberRepository.save(member);  // 회원 정보 저장
       System.out.println("저장된 회원: " + savedMember);  //
        return "success";
    }
}
