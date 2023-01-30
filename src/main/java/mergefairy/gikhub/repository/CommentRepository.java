package mergefairy.gikhub.repository;

import mergefairy.gikhub.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CommentRepository extends JpaRepository<Comment, Long> {

    Comment save(Comment comment);
    List<Comment> findAllByTalkTalk(Long talkTalkNo);

    Comment findByTalkTalkNo(Long talkTalkNo);
}
