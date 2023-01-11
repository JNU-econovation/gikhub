package mergefairy.gikhub.web.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mergefairy.gikhub.domain.User;
import mergefairy.gikhub.web.login.LoginForm;
import mergefairy.gikhub.web.login.LoginService;
import mergefairy.gikhub.web.session.SessionConst;
import mergefairy.gikhub.web.session.SessionManager;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class LoginController {

    private final LoginService loginService;

    @GetMapping("/login")
    public String loginForm(@ModelAttribute("loginForm") LoginForm form){
        return "/login/loginForm"; //로그인 시도 화면 F(x)
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm form, BindingResult bindingResult, HttpServletRequest request) {

        //로그인 에러 있을시 다시 로그인 시도 화면
        if (bindingResult.hasErrors()) return "/login/loginForm";

        User loginUser = loginService.login(form.getEmail(), form.getPassword());
        log.info("login? {}", loginUser);

        if (loginUser == null) {
            bindingResult.reject("loginFail", "아이디 또는 비밀번호가 맞지 않습니다.");
            return "/login/loginForm";
        }

        // 로그인 성공 처리

        // 세션 있으면 세션 반환, 없으면 신규 세션 생성
        //getSession()의 디폴트 값은 true -> 세션이 없으면 새로운 세션 생성 후 반환
        HttpSession session = request.getSession();
        // 세션에 회원 정보 보관
        session.setAttribute(SessionConst.LOGIN_USER, loginUser);

        //로그인 성공 시 메인 화면으로 리다이렉트 F(x)
        return "redirect:/";
    }

    @PostMapping("/logout")
    public String logout(HttpServletRequest request){
        //세션 삭제
        HttpSession session = request.getSession(false);
        if(session != null) session.invalidate();

        return "redirect:/";
    }
}
