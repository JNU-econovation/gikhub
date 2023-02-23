package mergefairy.gikhub.service.Dto;

import lombok.Data;
import mergefairy.gikhub.domain.User;

@Data
public class UserEditRequestDto {
    private String password;
    private String nickName;

    public UserEditRequestDto(User user){
        this.password = user.getPassword();
        this.nickName = user.getNickName();
    }
}
