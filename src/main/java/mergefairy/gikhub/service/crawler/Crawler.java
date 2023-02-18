package mergefairy.gikhub.service.crawler;

import lombok.extern.slf4j.Slf4j;
import mergefairy.gikhub.service.Dto.noticeDto;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class Crawler {
    public static List<noticeDto> getNotice() throws IOException{
        List<noticeDto> notices = new ArrayList<>();

        Document docs = Jsoup.connect("https://dormitory.jnu.ac.kr/Board/Board.aspx?BoardID=1").get();

        //공지사항 제목
        Elements titles = docs.select("a.tbl_title");

        //공지사항 카테고리
        Elements categories = docs.select("td.title span");

        //공지사항 본문 링크
        Elements linkElements = docs.select("a.tbl_title");

        for (int i =0; i< titles.size(); i++) {
            final String title = titles.get(i).text();
            //log.info("공지사항 제목: " + title);

            final String category = categories.get(i).text();
            //log.info("공지사항 카테고리: "+category);

            final String url = linkElements.get(i).attr("href");
            //log.info("공지사항 본문 링크:" + url);

            noticeDto noticeDto = new noticeDto(title, category, url);
            notices.add(noticeDto);
        }

        return notices;
    }
}
