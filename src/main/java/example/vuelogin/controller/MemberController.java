package example.vuelogin.controller;

import example.vuelogin.entity.Member;
import example.vuelogin.jwt.JwtUtil;
import example.vuelogin.repository.MemberRepository;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {
    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;

    @PostMapping("/register")
    public String registeruser(@RequestBody Member member) {
        System.out.println("회원가입 요청: " + member);// 클라이언트에서 받은 데이터 확인
       var memberPassword = passwordEncoder.encode(member.getPassword());
       System.out.println("++++++++++++++++++"+memberPassword);
       member.setPassword(memberPassword);Member savedMember = memberRepository.save(member);
       System.out.println("저장된 회원: " + savedMember);
        return "success";
    }
//    @PostMapping("/login")
//    public String login(@RequestBody Member member) {
//
//        return "success";
//    }
    @PostMapping("/login")
    @ResponseBody
    public ResponseEntity<String> loginJWT(@RequestBody Map<String, String> data, HttpServletResponse response) throws IOException {
        var authToken = new UsernamePasswordAuthenticationToken(data.get("username"), data.get("password"));
        var auth =   authenticationManagerBuilder.getObject().authenticate(authToken);
        SecurityContextHolder.getContext().setAuthentication(auth);

        var jwt= JwtUtil.createToken(SecurityContextHolder.getContext().getAuthentication());
        var cookie= new Cookie("jwt",jwt);
        cookie.setHttpOnly(false);
        cookie.setMaxAge(100);
        cookie.setPath("/");
        response.addCookie(cookie);


        return ResponseEntity.status(HttpStatus.OK).body("로그인되었습니다.");
    }
}
