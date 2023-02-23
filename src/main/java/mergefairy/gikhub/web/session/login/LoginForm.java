package mergefairy.gikhub.web.session.login;

import lombok.Data;
import lombok.Getter;

@Data
@Getter
public class LoginForm {
    public String email;
    public String password;
}
