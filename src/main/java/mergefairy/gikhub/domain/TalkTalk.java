package mergefairy.gikhub.domain;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Entity
@Builder
@RequiredArgsConstructor
public class TalkTalk extends BaseTimeEntity{
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "talktalk_id")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    //글번호
    @Column(nullable = false)
    private String talkTalkNo;

    //작성자
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 댓글 정렬
    @OneToMany(mappedBy = "talkTalk", fetch = FetchType.EAGER, cascade = CascadeType.REMOVE)
    @OrderBy("id asc")
    private List<Comment> comments;

    //게시글 수정 코드
    //앱에 추가할지 미정
    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
