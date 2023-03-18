package mergefairy.gikhub.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import mergefairy.gikhub.domain.Comment;
import mergefairy.gikhub.repository.CommentRepository;
import mergefairy.gikhub.service.Dto.CommentCreateRequestDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentServiceImpl {

    private final CommentRepository commentRepository;

    //댓글 저장 및 댓글 수 1 추가
    @Transactional
    public Comment save(Comment comment) {
        comment.getTalkTalk().addComment();
        return commentRepository.save(comment);
    }

    //게시글에 따른 댓글 조회
    public List<Comment> getComments(Long talkTalkNo) {
        return commentRepository.findAllByTalkTalk(talkTalkNo);
    }

    //댓글 삭제
    @Transactional
    public void deleteComment(Long commentId) {
        Comment deletedComment = commentRepository.findById(commentId).orElseThrow(() -> new IllegalArgumentException("삭제할 댓글이 존재하지 않습니다."));
        //댓글 삭제의 경우, 대댓글이 존재할 시 "댓글이 삭제되었습니다."로 대체

        //대댓글 존재하는 지 확인하고 없다면 삭제
        //댓글수 하나 빼기
        if(deletedComment.getChildren() == null) {
            commentRepository.deleteById(commentId);
            deletedComment.getTalkTalk().deleteComment();
        }
        else updateComment(commentId, "삭제된 댓글입니다.");
    }

    //댓글 수정
    @Transactional
    public Comment updateComment(Long commentId, String content) {
        Comment updatedComment = commentRepository.findById(commentId).orElseThrow(()-> new IllegalArgumentException("수정할 댓글이 존재하지 않습니다."));
        updatedComment.updateComment(content);
        return updatedComment;
    }


    //대댓글 저장
    @Transactional
    public Comment saveReComment(CommentCreateRequestDto commentCreateDto) {
        checkComment(commentCreateDto);
        checkTalkTalk(commentCreateDto);
        commentCreateDto.getTalkTalk().addComment();

        return commentRepository.save(commentCreateDto.toEntity());
    }

    //답글을 달 댓글이 없을 경우
    private void checkComment(CommentCreateRequestDto commentCreateDto) {
        if(commentCreateDto.getParent() == null){
            new IllegalArgumentException("답글을 달 댓글이 존재하지 않습니다.");
        }
    }

    //부모댓글의 게시글 번호와 생성할 댓글의 게시글 번호가 같은지 확인
    public void checkTalkTalk(CommentCreateRequestDto createRequestDto){
        if(createRequestDto.getParent().getTalkTalk()!= createRequestDto.toEntity().getTalkTalk()){
            new IllegalArgumentException("잘못된 접근입니다.");
        }
    }

    //대댓글 조회, 삭제, 수정 -> 댓글 삭제와 동일
}
