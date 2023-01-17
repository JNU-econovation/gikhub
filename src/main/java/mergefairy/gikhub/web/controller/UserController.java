package mergefairy.gikhub.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mergefairy.gikhub.domain.User;
import mergefairy.gikhub.service.Dto.UserCreateDto;
import mergefairy.gikhub.service.Dto.UserEditDto;
import mergefairy.gikhub.service.Dto.UserInfoDto;
import mergefairy.gikhub.service.UserServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Slf4j
public class UserController {
    private final UserServiceImpl userServiceImpl;

    @GetMapping("/validation/join")
    public String joinForm(UserCreateDto userCreateDto) {

        // 여기서 UserCreateDto를 받아줘야 회원가입 실패시 그 입력값이 그대로 유지
        // 즉, 기존에 처음 페이지에 들어갈 때는 userDTO가 parameter로 들어오지 않으니 무시
        // 회원가입 실패시, UserCreateDto를 받은 Get요청이 이루어지면서 model을 통해 넘어온 값이 parameter 로 받아지게 된다.
        return "/user/joinForm"; //회원가입창
    }

    //회원가입
    @PostMapping("/validation/join")
    public String joinUser(@Validated UserCreateDto userCreateDto, Errors errors, Model model) {
        /* post요청시 넘어온 user 입력값에서 Validation에 걸리는 경우 */
        if (errors.hasErrors()) {
            /* 회원가입 실패시 입력 데이터 유지 */
            model.addAttribute("userCreateDto", userCreateDto);
            /* 회원가입 실패시 message 값들을 모델에 매핑해서 View로 전달 */
            Map<String, String> validateResult = userServiceImpl.validateHandler(errors);
            // map.keySet() -> 모든 key값을 갖고온다.
            // 그 갖고온 키로 반복문을 통해 키와 에러 메세지로 매핑
            for (String key : validateResult.keySet()) {
                // ex) model.addAtrribute("valid_id", "아이디는 필수 입력사항 입니다.")
                model.addAttribute(key, validateResult.get(key));
            }
            return "join 실패";
        }

        userServiceImpl.createUser(userCreateDto);
        log.info("join 성공");

        //로그인 화면으로 이동
        return "redirect:/api/login";
    }

    //회원가입 시 이메일 중복 확인
    //중복되는 경우 true
    @GetMapping("/{emailCheck}/exists")
    public ResponseEntity<Boolean> checkEmailDuplicacte(@PathVariable String email){
        log.info(email);
        return ResponseEntity.ok(userServiceImpl.checkEmailDuplicate(email));
    }

    //회원가입 시 닉네임 중복 확인
    @GetMapping("/{nickNameCheck}/exists")
    public ResponseEntity<Boolean> checkNickNameDuplicacte(@PathVariable String nickName){
        return ResponseEntity.ok(userServiceImpl.checkNickNameDuplicate(nickName));
    }

    //마이페이지의 내정보 보기
    @GetMapping("/{nikcName}")
    public ResponseEntity getMyInfo(@Valid @PathVariable("nickName") String nickName) throws Exception{
        UserInfoDto userInfoDto = userServiceImpl.getMyInfo(nickName);
        return new ResponseEntity(userInfoDto,HttpStatus.OK);
    }

    //회원 정보 수정
    @PutMapping("/{nickName}")
    public ResponseEntity updateMyInfo(@PathVariable("nickName") String nickName, UserEditDto userEditDto){
        User updatedUser = userServiceImpl.update(userEditDto, nickName);
        return new ResponseEntity(updatedUser, HttpStatus.OK);
    }

    //회원 삭제(탈퇴)
    @DeleteMapping("/{nickName}")
    @ResponseStatus(HttpStatus.OK)
    public void deleteUser(@Validated @RequestBody String email){
        userServiceImpl.deleteUser(email);
    }
}
