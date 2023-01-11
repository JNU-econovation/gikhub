package mergefairy.gikhub.service;

import lombok.RequiredArgsConstructor;
import mergefairy.gikhub.domain.TalkTalk;
import mergefairy.gikhub.repository.TalkTalkRepository;
import mergefairy.gikhub.service.Dto.TalkTalkCreateDto;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TalkTalkSeviceImpl implements TalkTalkService{

    private final TalkTalkRepository talkTalkRepository;

    public TalkTalk createTalkTalk(TalkTalkCreateDto talkTalkDto){
        return talkTalkRepository.save(talkTalkDto.toEntity());
    }

    public void deleteTalkTalk(Long talkTalkNo){
        TalkTalk deleteTalkTalk = talkTalkRepository.findByTalkTalkNo(talkTalkNo).orElseThrow(() -> new IllegalArgumentException("해당하는 글이 없습니다."));
        talkTalkRepository.delete(deleteTalkTalk);
    }
}
