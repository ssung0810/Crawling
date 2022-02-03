package passwordModify;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;

public class ModifyBody {
    public void Naver(String id, String pw, String phone, String browser, String driverLocation) throws InterruptedException {
        try {
            System.setProperty(browser, driverLocation);
        } catch(Exception e) {
            e.printStackTrace();
        }

        // 2. WebDriver 옵션 설정
        ChromeOptions options = new ChromeOptions();
//	        options.addArguments("--start-maximized");          // 최대크기로
//        options.addArguments("--headless");                 // Browser를 띄우지 않음
        options.addArguments("--disable-gpu");              // GPU를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.
        options.addArguments("--no-sandbox");               // Sandbox 프로세스를 사용하지 않음, Linux에서 headless를 사용하는 경우 필요함.

        // 브라우저 실행
        WebDriver driver = new ChromeDriver(options);

//        driver.get("https://www.naver.com");
        driver.get("https://nid.naver.com/nidlogin.login?mode=form&url=https%3A%2F%2Fwww.naver.com");

        // 브라우저에서 아이디입력
        WebElement IdObj = driver.findElement(By.cssSelector("#id"));
        IdObj.clear();
        IdObj.click();

        /*** 자동방지 입력을 피하기 위한 코드 ***/
        // 클립보드에 아이디 복사
        StringSelection stringSelection = new StringSelection(id);
        Clipboard clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);

        // 클립보드에 복사된 아이디 붙여넣기
        Actions action =  new Actions(driver);
        action.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();


        // 브라우저에서 패스워드입력
        WebElement PwObj = driver.findElement(By.cssSelector("#pw"));
        PwObj.clear();
        PwObj.click();

        // 클립보드에 아이디 복사
        stringSelection = new StringSelection(pw);
        clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);

        // 클립보드에 복사된 아이디 붙여넣기
        action =  new Actions(driver);
        action.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();


        Thread.sleep(1000);		// 1초 휴식
        driver.findElement(By.xpath("//*[@id='log.login']")).click();	// 로그인버튼 클릭

        Thread.sleep(2000);		// 1초 휴식
        driver.findElement(By.xpath("//*[@id='new.dontsave']")).click();		// 등록안함 클릭


        // 휴대폰번호 변경하는 페이지로 이동
        driver.get("https://nid.naver.com/user2/help/changeUserInfo.nhn?menu=nid&lang=ko_KR");

        driver.findElement(By.xpath("//*[@id='p_phoneNo']/a")).click();

        // 휴대폰 번호 입력 후 인증번호 받기 클릭
        WebElement phoneInput = driver.findElement(By.cssSelector("#phoneNo"));
        phoneInput.clear();
        phoneInput.click();
        // 클립보드에 아이디 복사
        stringSelection = new StringSelection(phone);
        clpbrd = Toolkit.getDefaultToolkit().getSystemClipboard();
        clpbrd.setContents(stringSelection, null);

        // 클립보드에 복사된 아이디 붙여넣기
        action =  new Actions(driver);
        action.keyDown(Keys.CONTROL).sendKeys("v").keyUp(Keys.CONTROL).perform();


        Thread.sleep(1000);		// 1초 휴식
        driver.findElement(By.xpath("//*[@id='d_phoneNo']/p[2]/a")).click();
    }
}
