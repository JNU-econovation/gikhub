package mergefairy.gikhub.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mergefairy.gikhub.domain.User;
import mergefairy.gikhub.web.session.SessionConst;
import mergefairy.gikhub.repository.UserRepository;
import mergefairy.gikhub.web.session.SessionManager;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;

@Slf4j
@Controller
@RequiredArgsConstructor
public class HomeController {
    private final UserRepository userRepository;
    private final SessionManager sessionManager;


    //로그인 하지 않은 사용자도 홈에 접근할 수 있기 때문에 required = false 를 사용
    @GetMapping("/")
    public String homeLogin(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser, Model model){

        //세션이 없을때
        if(loginUser == null) return "home";

        //세션이 유지되면 로그인홈으로
        model.addAttribute("user", loginUser);
        return "loginHome";
    }
}

