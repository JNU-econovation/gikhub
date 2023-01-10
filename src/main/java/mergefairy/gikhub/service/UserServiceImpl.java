package mergefairy.gikhub.service;

import lombok.RequiredArgsConstructor;
import mergefairy.gikhub.domain.User;
import mergefairy.gikhub.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    public User createUser(UserCreateDto userCreateDto){
        return userRepository.save(userCreateDto.toEntity());
    };

    public void deleteUser(String email){
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new IllegalArgumentException("해당하는 회원이 없습니다."));
        userRepository.delete(user);
    }

    public Map<String, String> validateHandler(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();

        // 유효성 검사에 실패한 필드 목록을 받음
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    public Boolean checkEmailDuplicate(String email) {
        if(userRepository.existsByEmail(email)){
            return true; //중복되는 경우 true
        }
        return false;
    }

    public Boolean checkNickNameDuplicate(String nickName) {
        if(userRepository.existsByNickName(nickName)){
            return true; //중복되는 경우 true
        }
        return false;
    }
}
