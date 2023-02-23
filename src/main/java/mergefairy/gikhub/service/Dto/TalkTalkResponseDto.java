package mergefairy.gikhub.service.Dto;

import lombok.*;
import mergefairy.gikhub.domain.Comment;
import mergefairy.gikhub.domain.TalkTalk;
import mergefairy.gikhub.domain.User;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
public class TalkTalkResponseDto {
    //게시판: 제목, 내용, 작성자, 댓글수
    //게시글 1개: 제목, 내용, 작성자, 댓글수, 작성일시, 댓글들

    private String title;

    private String content;
    //writer
    private User user;

    private int commentCount;

    private LocalDateTime createdDate;

    private LocalDateTime modifiedDate;

    private List<Comment> commentList;

    public TalkTalkResponseDto(String title, User user, int commentCount) {
        this.title = title;
        this.user = user;
        this.commentCount = commentCount;
    }

    //게시글 생성시
    public static TalkTalkResponseDto GetCreatePost(TalkTalk talkTalk){
        return TalkTalkResponseDto.builder()
                .title(talkTalk.getTitle())
                .content(talkTalk.getTitle())
                .user(talkTalk.getUser())
                .commentCount(talkTalk.getCommentCount())
                .createdDate(talkTalk.getCreatedDate())
                .build();
    }

    //게시글 하나
    public TalkTalkResponseDto GetOnePost(TalkTalk talkTalk){
        return TalkTalkResponseDto.builder()
                .title(talkTalk.getTitle())
                .content(talkTalk.getContent())
                .user(talkTalk.getUser())
                .commentCount(talkTalk.getCommentCount())
                .createdDate(talkTalk.getCreatedDate())
                .modifiedDate(talkTalk.getModifiedDate())
                .commentList(talkTalk.getCommentList())
                .build();
    }
}
