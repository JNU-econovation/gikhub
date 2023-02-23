package mergefairy.gikhub.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mergefairy.gikhub.domain.User;
import mergefairy.gikhub.service.Dto.noticeDto;
import mergefairy.gikhub.service.crawler.Crawler;
import mergefairy.gikhub.web.session.SessionConst;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.io.IOException;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/")
public class HomeController {



    //로그인 하지 않은 사용자도 홈에 접근할 수 있기 때문에 required = false 를 사용
    @GetMapping("/login")
    public String homeLogin(@SessionAttribute(name = SessionConst.LOGIN_USER, required = false) User loginUser, Model model){

        //세션이 없을때
        if(loginUser == null) return "home";

        //세션이 유지되면 로그인홈으로
        model.addAttribute("user", loginUser);
        return "loginHome";
    }

    //공지사항
    @GetMapping("/notice")
    public ResponseEntity<List<noticeDto>> getNotice() throws IOException {
        List<noticeDto> notices = Crawler.getNotice();
        return new ResponseEntity(notices, HttpStatus.OK);
    }
}

