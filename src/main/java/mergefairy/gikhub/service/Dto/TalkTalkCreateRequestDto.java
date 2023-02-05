package mergefairy.gikhub.service.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import mergefairy.gikhub.domain.User;
import java.time.LocalDateTime;


@Data
@Builder
public class TalkTalkCreateRequestDto {
//제목, 내용, 작성자, 작성 시간
    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;

    @NotBlank(message = "본문은 필수 입력 값입니다.")
    private String content;

    private User user;

    private LocalDateTime createdDate;

}
