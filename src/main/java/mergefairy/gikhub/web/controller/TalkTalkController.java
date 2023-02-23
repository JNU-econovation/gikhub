package mergefairy.gikhub.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mergefairy.gikhub.domain.TalkTalk;
import mergefairy.gikhub.service.Dto.TalkTalkCreateRequestDto;
import mergefairy.gikhub.service.Dto.TalkTalkResponseDto;
import mergefairy.gikhub.service.TalkTalkServiceImpl;
import org.springframework.data.domain.Slice;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/talktalk")
public class TalkTalkController {
    private final TalkTalkServiceImpl talkTalkServiceImpl;

    //게시글 등록
    @PostMapping("/add")
    public ResponseEntity createTalkTalk(@Validated TalkTalkCreateRequestDto talkTalkDto){
        TalkTalk createdTalkTalk = talkTalkServiceImpl.createTalkTalk(talkTalkDto);
        return new ResponseEntity(TalkTalkResponseDto.GetCreatePost(createdTalkTalk), HttpStatus.CREATED);
    }

    //게시글 삭제
    @DeleteMapping("/delete/{talkTalkNo}")
    public ResponseEntity deleteTalkTalk(@PathVariable Long talkTalkNo){
        talkTalkServiceImpl.deleteTalkTalk(talkTalkNo);
        return new ResponseEntity("게시글 삭제", HttpStatus.OK);
    }

    //게시판
    //게시글 1개 조회
    @GetMapping("/{talktalkNo}")
    public ResponseEntity getOneTalkTalk(@Valid @PathVariable("talktalkNo") Long no){
        TalkTalk findTalkTalk = talkTalkServiceImpl.getOneTalktalk(no);
        return new ResponseEntity(findTalkTalk, HttpStatus.OK);
    }

    //게시글 목록 무한 스크롤으로 반환타입은 Slice
    //정렬순 : createdDate, commentCount
    //댓글순
    @GetMapping("/list/commentCount/{pageNumber}")
    public ResponseEntity<Slice<TalkTalkResponseDto>> getTalkTalksSortedByCommentCount(@PathVariable int pageNumber) {
        Slice<TalkTalkResponseDto> talkTalksByCommentCount = talkTalkServiceImpl.getTalkTalksSortedByCommentCount(pageNumber);
        return new ResponseEntity(talkTalksByCommentCount, HttpStatus.OK);
    }

    //최신순
    @GetMapping("/list/new/{pageNumber}")
    public ResponseEntity<Slice<TalkTalkResponseDto>> getTalkTalksSortedByCreateAt(@PathVariable int pageNumber) {
        Slice<TalkTalkResponseDto> talkTalksByCreateAt = talkTalkServiceImpl.getTalkTalksSortedByCreateAt(pageNumber);
        return new ResponseEntity(talkTalksByCreateAt, HttpStatus.OK);
    }
}
