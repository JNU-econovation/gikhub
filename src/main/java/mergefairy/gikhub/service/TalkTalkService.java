package mergefairy.gikhub.service;

import mergefairy.gikhub.domain.TalkTalk;
import mergefairy.gikhub.service.Dto.TalkTalkCreateDto;

public interface TalkTalkService {
    //게시글 등록
    public TalkTalk createTalkTalk(TalkTalkCreateDto talkTalkDto);

    //게시글 수정

    //게시글 삭제
    public void deleteTalkTalk(Long talkTalkNo);

    //게시글 조회

    //검색 조건에 따른 게시글 리스트 조회,페이징

}
