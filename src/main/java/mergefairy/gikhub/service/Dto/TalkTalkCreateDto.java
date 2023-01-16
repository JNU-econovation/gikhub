package mergefairy.gikhub.service.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import mergefairy.gikhub.domain.TalkTalk;
import mergefairy.gikhub.domain.User;

@Data
@Builder
public class TalkTalkCreateDto {

    @NotBlank(message = "제목은 필수 입력 값입니다.")
    private String title;

    @NotBlank(message = "본문은 필수 입력 값입니다.")
    private String content;

    private User user;


    public TalkTalk toEntity(){
        return TalkTalk.builder()
                .title(this.title)
                .content(this.content)
                .user(this.user)
                .build();
    }
}
