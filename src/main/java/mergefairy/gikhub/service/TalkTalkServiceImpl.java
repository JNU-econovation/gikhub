package mergefairy.gikhub.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mergefairy.gikhub.domain.TalkTalk;
import mergefairy.gikhub.repository.TalkTalkRepository;
import mergefairy.gikhub.repository.UserRepository;
import mergefairy.gikhub.service.Dto.TalkTalkCreateRequestDto;
import mergefairy.gikhub.service.Dto.TalkTalkResponseDto;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class TalkTalkServiceImpl{

    private final TalkTalkRepository talkTalkRepository;
    private final UserRepository userRepository;


    //게시글 생성
    public TalkTalk createTalkTalk(TalkTalkCreateRequestDto talkTalkDto){
        return talkTalkRepository.save(TalkTalk.builder()
                .title(talkTalkDto.getTitle())
                .user(userRepository.findByEmail(talkTalkDto.getUser().getEmail()).get())
                .content(talkTalkDto.getContent())
                .build());
    }

    //게시글 1개 조회
    public TalkTalk getOneTalktalk(Long no){
        return talkTalkRepository.findByTalkTalkNo(no).orElseThrow(()-> new IllegalArgumentException("해당하는 게시글이 존재하지 않습니다."));
    }

    //게시글 목록 조회
    //정렬순 createdDate, commentCount

    //최신순
    public Slice<TalkTalkResponseDto> getTalkTalksSortedByCreateAt(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 7, Sort.by(Sort.Direction.DESC, "createdDate"));
        Slice<TalkTalk> createdSlice = talkTalkRepository.findAllByOrderByCreatedDateDesc(pageable);
        List<TalkTalkResponseDto> createDtoList = createdSlice.getContent()
                .stream()
                .map(talkTalk -> convertToDto(talkTalk))
                .collect(Collectors.toList());
        return new SliceImpl<>(createDtoList, pageable, createdSlice.hasNext());
    }

    //댓글순
    public Slice<TalkTalkResponseDto> getTalkTalksSortedByCommentCount(int pageNumber){
        Pageable pageable = PageRequest.of(pageNumber, 7, Sort.by(Sort.Direction.DESC, "commentCount"));

        Slice<TalkTalk> talkTalkSlice= talkTalkRepository.findAllByOrderByCommentCountDesc(pageable);

        List<TalkTalkResponseDto> talkTalkGetDtoList = talkTalkSlice.getContent()
                .stream()
                .map(talkTalk -> convertToDto(talkTalk))
                .collect(Collectors.toList());

        return new SliceImpl<>(talkTalkGetDtoList, pageable, talkTalkSlice.hasNext());
    }


    private TalkTalkResponseDto convertToDto(TalkTalk talkTalk){
        return new TalkTalkResponseDto(talkTalk.getTitle(), talkTalk.getUser(), talkTalk.getCommentCount());
    }

    //게시글 수정
    @Transactional
    public void updateTalkTalk(Long no, String title, String content){
        TalkTalk findTalkTalk = talkTalkRepository.findByTalkTalkNo(no).orElseThrow(()-> new IllegalArgumentException("해당하는 글이 없습니다."));
        findTalkTalk.update(title, content);
    }


    //게시글 삭제
    @Transactional
    public void deleteTalkTalk(Long no){
        TalkTalk deleteTalkTalk = talkTalkRepository.findById(no).orElseThrow(() -> new IllegalArgumentException("해당하는 글이 없습니다."));
        talkTalkRepository.delete(deleteTalkTalk);
    }
}
