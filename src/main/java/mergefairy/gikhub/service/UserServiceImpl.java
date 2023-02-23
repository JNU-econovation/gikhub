package mergefairy.gikhub.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mergefairy.gikhub.domain.User;
import mergefairy.gikhub.repository.UserRepository;
import mergefairy.gikhub.service.Dto.UserCreateRequestDto;
import mergefairy.gikhub.service.Dto.UserEditRequestDto;
import mergefairy.gikhub.service.Dto.UserResponseDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class UserServiceImpl{
    private final UserRepository userRepository;

    public User createUser(UserCreateRequestDto userCreateDto){
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

    public String checkEmailDuplicate(String email) {
        if(userRepository.existsByEmail(email)){
            return "true"; //중복되는 경우 true
        }
        return "false";
    }

    public String checkNickNameDuplicate(String nickName) {
        if(userRepository.existsByNickName(nickName)){
            return "true"; //중복되는 경우 true
        }
        return "false";
    }


    public UserResponseDto getMyInfo(String nickName){
        User findUser = userRepository.findByNickName(nickName).orElseThrow(()-> new IllegalArgumentException("사용자 정보를 가져올 수 없습니다."));
        return new UserResponseDto(findUser);
    }

    @Transactional
    public User update(UserEditRequestDto userEditDto, String nickName) {
        User updateUser = userRepository.findByNickName(nickName).orElseThrow(()-> new IllegalArgumentException("수정할 사용자의 정보가 없습니다."));
        //JPA 의 영속성 컨텍스트 덕분에 entity 객체의 값만 변경하면 자동으로 변경사항 반영
        updateUser.update(userEditDto.getPassword(), userEditDto.getNickName());
        return updateUser;
    }
}
