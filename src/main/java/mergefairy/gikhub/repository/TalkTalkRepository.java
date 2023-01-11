package mergefairy.gikhub.repository;


import mergefairy.gikhub.domain.TalkTalk;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TalkTalkRepository extends JpaRepository<TalkTalk, Long> {

    TalkTalk save(TalkTalk talkTalk);

    Optional<TalkTalk> findByTalkTalkNo(Long talkTalkNo);
}
