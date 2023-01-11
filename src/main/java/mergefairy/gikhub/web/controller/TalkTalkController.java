package mergefairy.gikhub.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mergefairy.gikhub.domain.TalkTalk;
import mergefairy.gikhub.service.Dto.TalkTalkCreateDto;
import mergefairy.gikhub.service.TalkTalkSeviceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/talktalk")
public class TalkTalkController {
    private final TalkTalkSeviceImpl talkTalkService;

    //게시글 등록 화면
    @GetMapping("/addForm")
    public String openTalkTalkWrite(){
        return "등록 화면"; //fx
    }

    //게시글 등록
    @PostMapping("/add")
    public ResponseEntity createTalkTalk(@Valid TalkTalkCreateDto talkTalkDto){
        TalkTalk createdTalkTalk = talkTalkService.createTalkTalk(talkTalkDto);
        return new ResponseEntity(createdTalkTalk, HttpStatus.CREATED);
    }

    //게시글 삭제
    @DeleteMapping("/delete/{no}")
    public ResponseEntity deleteTalkTalk(Long no){
        talkTalkService.deleteTalkTalk(no);
        return new ResponseEntity("{}", HttpStatus.OK);
    }

//    //게시글 목록
//    @GetMapping("/list")
//    public
}
