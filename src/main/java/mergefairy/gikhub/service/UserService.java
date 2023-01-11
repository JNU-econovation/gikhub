package mergefairy.gikhub.service;


import mergefairy.gikhub.domain.User;
import mergefairy.gikhub.service.Dto.UserCreateDto;
import mergefairy.gikhub.service.Dto.UserInfoDto;
import org.springframework.stereotype.Service;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@Service
public interface UserService {
    public User createUser(UserCreateDto userCreateDto);
    public void deleteUser(String email);
    public Map<String, String> validateHandler(Errors errors);

    public Boolean checkEmailDuplicate(String email);

    public Boolean checkNickNameDuplicate(String nickName);

    public UserInfoDto getMyInfo(String nickName);
}
