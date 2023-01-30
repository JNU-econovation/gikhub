package mergefairy.gikhub.repository;


import mergefairy.gikhub.domain.TalkTalk;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface TalkTalkRepository extends JpaRepository<TalkTalk, Long> {
    TalkTalk save(TalkTalk talkTalk);

    Optional<TalkTalk> findByTalkTalkNo(Long talkTalkNo);
    //Page<TalkTalk> findAllByOrderByCommentCountDesc(Pageable pageable);
    Slice<TalkTalk> findAllByOrderByCreatedDateDesc(Pageable pageable);
    Slice<TalkTalk> findAllByOrderByCommentCountDesc(Pageable pageable);
}

