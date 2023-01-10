package mergefairy.gikhub.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@Getter
@AllArgsConstructor
@Table(name = "users")
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

//이메일로 로그인 및 중복 계정 확인
//    @Column(nullable = false, length = 30)
//    private String accountId; //계정 아이디

    @Column(nullable = false, length = 50)
    private String email;

    @Column(nullable = false, length = 30)
    private String password;

    private String phoneNo;
    private String nickName; //닉네임
}
