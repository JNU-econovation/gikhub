package mergefairy.gikhub.repository;

import mergefairy.gikhub.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment save(Comment comment);

    //게시글에 해당하는 댓글 리스트
    List<Comment> findAllByTalkTalk(long talkTalk);

    //댓글 아이디에 따라 댓글 하나 반환
    Optional<Comment> findById(Long id);

    //댓글
    List<Comment> findAllByParent(long id);
}
