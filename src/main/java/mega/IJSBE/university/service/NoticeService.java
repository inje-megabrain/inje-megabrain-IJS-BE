package mega.IJSBE.university.service;

import mega.IJSBE.university.entity.UniversityNotice;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Service
public class NoticeService {
    @PostConstruct
    public void getNotice() throws InterruptedException, IOException, ParseException {
        System.setProperty("Webdriver.chrome.driver","/usr/lib/chromium-browser/chromedriver.exe");
        WebDriver driver = new ChromeDriver();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--headless");
        driver.get("https://www.inje.ac.kr/kor/Template/Bsub_page.asp?Ltype=5&Ltype2=0&Ltype3=0&Tname=S_News&Ldir=board/S_News&SearchText=&SearchKey=&d1n=5&d2n=1&d3n=1&d4n=0&Lpage=Tboard_L&div=6");
        try{
            //*[@id="contents"]/div[3]/table/thead/tr/th[1]
               /* for (WebElement content : contents) {
                    UniversityNotice notice = UniversityNotice.builder()
                            .noticeId(content.findElement(By.cssSelector("td.num")).getText())
                            .authorNickname(driver.findElements(By.xpath("//*[@id=\"contents\"]/div[3]/table/tbody/tr[1]/td[4]/a")).toString())
                            .title(driver.findElements(By.xpath("//*[@id=\"contents\"]/div[3]/table/tbody/tr[1]/td[3]/a")).toString())
                            .writeAt(new Date())
                            .build();

                    System.out.println("notice.getNoticeId() = " + notice.getNoticeId());
                    System.out.println("notice.getNoticeId() = " + notice.getAuthorNickname());
                    System.out.println("notice.getNoticeId() = " + notice.getWriteAt());
                    System.out.println("notice.getNoticeId() = " + notice.getTitle());
                }*/
            

        }finally {
            driver.close();
            driver.quit();
        }
    }
}
