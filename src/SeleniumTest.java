import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTest {

	public static void main(String[] args) {
		System.setProperty("webdriver.chrome.driver", "E:/Development/Crawling/Selenium/chromedriver.exe");
		
		// 브라우저 실행
		WebDriver driver = new ChromeDriver();
				
		// 구글 홈페이지로 이동
		driver.get("https://account.yanolja.bz/?redirectURL=%2F&returnURL=https%3A%2F%2Fpartner.yanolja.com%2Fauth%2Flogin&serviceType=PC");
	}

}
