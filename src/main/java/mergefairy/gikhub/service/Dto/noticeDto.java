package mergefairy.gikhub.service.Dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

@Data
@Getter
@AllArgsConstructor
@Builder
public class noticeDto {
    //크롤링한 자료를 리스트 형태로 반환
    //해당 dto는 리스트의 자료형

    String title;
    String category;
    String content;

}
