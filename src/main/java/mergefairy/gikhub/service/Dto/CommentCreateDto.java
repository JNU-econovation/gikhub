package mergefairy.gikhub.service.Dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;
import mergefairy.gikhub.domain.Comment;
import mergefairy.gikhub.domain.TalkTalk;
import mergefairy.gikhub.domain.User;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentCreateDto {

    @NotBlank(message = "본문은 필수 입력 값입니다.")
    private String content;

    private TalkTalk talkTalk;
    private User user;
    private LocalDateTime createdDate;

    public Comment toEntity(){
        return Comment.builder()
                .content(this.getContent())
                .user(this.getUser())
                .talkTalk(this.getTalkTalk())
                .build();
    }
}

