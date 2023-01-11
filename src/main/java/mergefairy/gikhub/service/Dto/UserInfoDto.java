package mergefairy.gikhub.service.Dto;

import jakarta.persistence.Column;
import lombok.Data;
import mergefairy.gikhub.domain.User;

@Data
public class UserInfoDto {
    private String email;
    private String name;
    private String phoneNo;
    private String nickName;

    public UserInfoDto(User user){
        this.email = user.getEmail();
        this.name = user.getName();
        this.phoneNo = user.getPhoneNo();
        this.nickName = user.getNickName();
    }
}
